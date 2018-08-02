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

import freemarker.template.TemplateException;

@RestController
public class RegisterController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HashRepository hashRepository;
	
	@Autowired
    private EmailService emailService;


	// Check if username already exists returns true if it does, false otherwise
	@RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
	@ResponseBody
	public UserValidation checkUsername(@PathVariable(value = "email") String email) {
		UserValidation userValidation = new UserValidation();
		if (userRepository.existsByEmail(email)) {
			userValidation.setEmailExists(true);
		}
		return userValidation;
	}
	
	@RequestMapping(value = "/registered", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String register(@RequestBody UserForm userForm) {
		User saveUser = new User();
		saveUser.setFirstName(userForm.getFirstName());
		saveUser.setLastName(userForm.getLastName());
		//saveUser.setUsername(userForm.getUsername());
		saveUser.setEmail(userForm.getEmail());
		saveUser.setPassword(userForm.getPassword());
		saveUser.setStatus("New");
		saveUser.setRole("ROLE_USER");
		userRepository.save(saveUser);
		
		Hash hash = new Hash();
		hash.setHashValue("sabinhash");
		hash.setUserId(saveUser.getUserId());
		
		hashRepository.save(hash);
		
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
        try {
			emailService.sendSimpleMessage(mail);
		} catch (MessagingException | IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saveUser.getEmail();
	}
	
	
	
	@Transactional
	@RequestMapping(value = "/verification/{hash}", method = RequestMethod.GET)
	public boolean emailVerification(@PathVariable(value = "hash") String hashValue) {
		if(hashRepository.existsByHashValue(hashValue)) {
			Optional<User> x = userRepository.findById(hashRepository.findByHashValue(hashValue).get(0));
			User user = x.get();
			if (user != null) {
				hashRepository.deleteByHashValue(hashValue);
				user.setStatus("Approved");
				userRepository.save(user);
			}
//			
//			updateUser.setEmail(x.get().getEmail());
//			updateUser.setFirstName(x.get().getFirstName());
//			updateUser.setLastName(x.get().getLastName());
//			updateUser.setPassword(x.get().getPassword());
//			updateUser.setUserId(x.get().getUserId());
//			updateUser.setStatus("Approved");
//			userRepository.save(updateUser);
			return true;
		}else {
			return false;
		}
	}
	

}
