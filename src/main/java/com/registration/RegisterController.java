package com.registration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

import freemarker.template.TemplateException;
/**
 * Controller for registration
 *
 */
@RestController
public class RegisterController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	HashRepository hashRepository;

	@Autowired
	private EmailService emailService;

	/**
	 * Rest endpoint that handles a GET request and
	 * checks if the email is already in use
	 * @param email
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
	 * Rest endpoint that handles a POST request,
	 * receives a {@link UserForm} which contains the user's data and
	 * based on this data, builds the {@link User} and sends an email
	 * with a verification link to the user's completed email address.
	 * @param userForm
	 * @return the user's email address.
	 */
	@RequestMapping(value = "/registered", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String register(@RequestBody UserForm userForm) {
		//Inserts user into database
		User saveUser = new User();
		saveUser.setFirstName(userForm.getFirstName());
		saveUser.setLastName(userForm.getLastName());
		saveUser.setEmail(userForm.getEmail());
		saveUser.setPassword(userForm.getPassword());
		saveUser.setStatus("New");
		saveUser.setRole("ROLE_USER");
		userRepository.save(saveUser);

		Hash hash = new Hash();
		hash.setHashValue(String.valueOf(saveUser.hashCode()));
		hash.setUserId(saveUser.getUserId());

		hashRepository.save(hash);
		//Constructs the account verification email 
		Mail mail = new Mail();
		mail.setFrom("no-reply@sync.ro");
		mail.setTo(userForm.getEmail());
		mail.setSubject("Account Verification");
		
		Map model = new HashMap();
		model.put("name", userForm.getFirstName() + userForm.getLastName());
		model.put("location", "Unknown location");
		model.put("signature", "https://www.sync.ro");
		model.put("hash", hash.hashValue);
		model.put("id", saveUser.getUserId());
		mail.setModel(model);
		//Sends the email
		try {
			emailService.sendSimpleMessage(mail);
		} catch (MessagingException | IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saveUser.getEmail();
	}
	
	/**
	 * Rest endpoint that handles a GET request,
	 * receives the verification hash sent in the user's email and checks
	 * if there is a record of it in the database. If there is, the user is validated.
	 * @param hashValue
	 * @return true if the hash is correct, false otherwise.
	 */
	@Transactional
	@RequestMapping(value = "/verification/{hash}", method = RequestMethod.GET)
	public boolean emailVerification(@PathVariable(value = "hash") String hashValue) {
		//Checks if the hash is in the database
		if (hashRepository.existsByHashValue(hashValue)) {
			//Stores in userId the hash's corresponding user's ID.
			List<Long> userId = hashRepository.findByHashValue(hashValue);
			//Handles the case in which there's no user ID corresponding to the hash
			if (userId.isEmpty()) {
				return false;
			} else {
				//Selects the user based on its ID inserts it into the database and deletes its hash
				Optional<User> x = userRepository.findById(userId.get(0));
				User user = x.get();
				if (user != null) {
					hashRepository.deleteByHashValue(hashValue);
					user.setStatus("Approved");
					userRepository.save(user);
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
