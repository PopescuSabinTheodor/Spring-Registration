package com.registration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import freemarker.template.TemplateException;

/**
 * Controller for registration
 *
 */
@RestController
public class RegisterController {
	
	/**
	 * Bean used for working with our user container
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Bean used for working with our hash container
	 */
	@Autowired
	private HashRepository hashRepository;
	
	/**
	 * Bean used for working with our updateKey container
	 */
	@Autowired
	private UpdateKeyRepository updateKeyRepository;
	
	/**
	 * Bean used for sending emails
	 */
	@Autowired
	private EmailService emailService;

	/**
	 * Rest endpoint that handles a GET request and checks if the email address is already
	 * in use
	 * 
	 * @param email email address
	 * @return Object of type {@link UserValidation}
	 */
	@RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
	@ResponseBody
	public UserValidation checkUsername(@PathVariable(value = "email") String email) {
		UserValidation userValidation = new UserValidation();
		if (userRepository.existsByEmail(email)) {
			userValidation.setEmailExists(true);
		}
		return userValidation;
	}

	/**
	 * Rest endpoint that handles a POST request, receives a {@link UserForm} which
	 * contains the user's data and based on this data, builds the {@link User} and
	 * sends an email with a verification link to the user's completed email
	 * address.
	 * 
	 * @param userForm user data collected using a form
	 * @return the user's email address.
	 */
	@RequestMapping(value = "/registered", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String register(@RequestBody UserForm userForm) {
		// If user not null, inserts user into database
		if (userForm == null) {
			return "Failed";
		} else {
			String template = "email-template.ftl";
			User saveUser = new User();
			saveUser.setFirstName(userForm.getFirstName());
			saveUser.setLastName(userForm.getLastName());
			saveUser.setEmail(userForm.getEmail());
			saveUser.setPassword(userForm.getPassword());
			saveUser.setStatus("New");
			saveUser.setRole("ROLE_USER");
			userRepository.save(saveUser);

			Hash hash = new Hash(saveUser.getUserId());

			hashRepository.save(hash);
			// Constructs the account verification email
			Mail mail = new Mail();
			mail.setFrom("no-reply@sync.ro");
			mail.setTo(userForm.getEmail());
			mail.setSubject("Account Verification");

			Map<String, String> model = new HashMap<String, String>();
			model.put("name", userForm.getFirstName() + " " + userForm.getLastName());
			model.put("signature", "https://www.sync.ro");
			model.put("hash", hash.getHashKey());
			model.put("link", "verification");
			mail.setModel(model);
			// Sends the email
			try {
				emailService.sendSimpleMessage(mail, template);
			} catch (MessagingException | IOException | TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return saveUser.getEmail();
		}
	}

	/**
	 * Rest endpoint that handles a GET request, receives the verification hash sent
	 * in the user's email and checks if there is a record of it in the database. If
	 * there is, the user is validated.
	 * 
	 * @param hashValue unique string used to identify the user to be activated
	 * @return true if the hash is correct, false otherwise.
	 */
	@Transactional
	@RequestMapping(value = "/verification/{hash}", method = RequestMethod.GET)
	public RedirectView emailVerification(@PathVariable(value = "hash") String hashKey) {
		RedirectView redirectView = new RedirectView();
		// Stores in userId the hash's corresponding user's ID.
		Optional<Hash> hashOpt = hashRepository.findById(hashKey);
		Hash hash = hashOpt.get();
		// Handles the case in which there's no user ID corresponding to the hash
		if (hash != null) {
			// Selects the user based on its ID 
			// inserts it into the database and deletes its hash
			Optional<User> x = userRepository.findById(hash.getUserId());
			User user = x.get();
			if (user != null) {
				hashRepository.delete(hash);
				user.setStatus("Approved");
				userRepository.save(user);
			}
			
		    redirectView.setUrl("http://localhost:8082/login?activated");
		    return redirectView;
		} else {
			redirectView.setUrl("www.yahoo.com");
			return redirectView;
		}
	}
	/**
	 * Rest endpoint that handles a POST request, receives an {@link EmailDTO} which
	 * contains the email address to where an email with an activation link will be sent.
	 * @param email email address
	 * @return a boolean that is true if the user's email address is present in the database
	 */
	@RequestMapping(value = "/recover", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public UserValidation recoverPassword(@RequestBody EmailDTO email) {
		UserValidation userValidation = new UserValidation();
		if (userRepository.existsByEmail(email.getEmail())) {
			//Sets the email template 
			String template = "password-recover-email.ftl";
			userValidation.setEmailExists(true);
			Optional<User> optional = userRepository.findOneByEmail(email.getEmail());
			User user = optional.get();
			
			UpdateKey updateKey = new UpdateKey(email.getEmail());
			updateKeyRepository.save(updateKey);
			
			//Constructs the email
			Mail mail = new Mail();
			mail.setFrom("no-reply@sync.ro");
			mail.setTo(email.getEmail());
			mail.setSubject("Recover password");
			
			Map<String, String> model = new HashMap<String, String>();
			model.put("name", user.getFirstName() + " " + user.getLastName());
			model.put("signature", "https://www.sync.ro");
			model.put("key", updateKey.getUpdateKey());
			model.put("link", "change-password");
			mail.setModel(model);
			// Sends the email
			try {
				emailService.sendSimpleMessage(mail, template);
			} catch (MessagingException | IOException | TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userValidation;
	}
	
	/**
	 * Rest endpoint that handles a POST request, receives a {@link PasswordForm} which contains the user's
	 * new password choice and a unique key used to identify each instance of the recover password feature.
	 * Based on this, a user is able to change it's password only once using the same key.
	 * @param passwordForm contains the password choice and the action's instance key identifier.
	 * @return a boolean that is true if the user's password is successfully updated
	 */
	@RequestMapping(value = "/recoverPassword", method = RequestMethod.POST)
	public PasswordUpdate updatePassword(@RequestBody PasswordForm passwordForm ) {
		PasswordUpdate passwordUpdate = new PasswordUpdate();
		Optional<UpdateKey> optional = updateKeyRepository.findById(passwordForm.getUpdateKey());
		//Selects the user to which the password will be updated, by its email address
		if (optional.isPresent()) {
			UpdateKey updateKey = optional.get();
			Optional<User> optionalUser = userRepository.findOneByEmail(updateKey.getEmail());
			//Updates the user's password in the database
			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				user.setPassword(passwordForm.getPassword());
				userRepository.save(user);
				//Deletes the key which has been used
				updateKeyRepository.delete(updateKey);
				passwordUpdate.setPasswordUpdated(true);
			}
		}
		return passwordUpdate;
	}

}
