package com.cantel.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public User getOne(@PathVariable Long id) {
		if (userRepository.existsById(id)) {
			return userRepository.findById(id).get();
		}
		return null;
	}

	@PostMapping
	public User post(@RequestBody User user) {
		return userRepository.save(user);
	}

	@DeleteMapping(path = "/{id}", produces = "application/json")
	public String delete(@PathVariable Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "{ \"status\" : \"deleted\" }";
		}
		return "{ \"status\" : \"error\" }";
	}

	@PutMapping(path = "/{id}")
	public User put(@PathVariable Long id, @RequestBody User user) {
		return null;
	}

	@PatchMapping(path = "/{id}")
	public User patch(@PathVariable Long id, @RequestBody User user) {
		return null;
	}

}