package com.restservices.demo.restcontroller;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restservices.demo.bean.User;
import com.restservices.demo.exception.UserNotFoundException;
import com.restservices.demo.repository.UserRepository;

@RestController
public class UserRestController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getAllUsers() {

			return userRepository.findAll();
	}
	
	
	@GetMapping("/users/{id}")
	public User retriveUser(@PathVariable("id")int id) {

			User findOne = userRepository.findOne(id);
			if(null == findOne)
			{
				throw new UserNotFoundException("UserNotFound");
			}
			return findOne;
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable("id")int id) {

			User deleteById = userRepository.deleteById(id);
			if(null == deleteById)
			{
				throw new UserNotFoundException("UserNotFound");
			}
			return deleteById;
			
	}
	
	
	@PostMapping("/user")
	public ResponseEntity<Object > saveUser(@Valid @RequestBody User user)
	{
		 User save = userRepository.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	

}
