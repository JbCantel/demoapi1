package com.cantel.java.demoapi1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trecos")

public class TrecoController {

	@Autowired
	TrecoRepository trecoRepository;
	// private TrecoRepository trecoRepository;

	@GetMapping
	public List<Treco> getAll() {
		return trecoRepository.findAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public Treco getOne(@PathVariable Long id) {
		if (trecoRepository.existsById(id)) {
			return trecoRepository.findById(id).get();
		}
		return null;
	}
// método post
	@PostMapping
	public Treco post(@RequestBody Treco treco) {
		return trecoRepository.save(treco);
	}

	// @DeleteMapping(path = "/{id}")
	
}
