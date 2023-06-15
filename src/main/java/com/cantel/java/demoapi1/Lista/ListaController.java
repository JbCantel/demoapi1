package com.cantel.java.demoapi1.Lista;

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
@RequestMapping("/listas")

public class ListaController {

	@Autowired
	private ListaRepository listaRepository;

	@GetMapping
	public List<Lista> getAll() {
		return listaRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public Lista getOne(@PathVariable Long id) {
		if (listaRepository.existsById(id)) {
			return listaRepository.findById(id).get();
		}
		return null;
	}

	@PostMapping
	public Lista post(@RequestBody Lista lista) {
		return listaRepository.save(lista);
	}

	@DeleteMapping(path = "/{id}", produces = "application/json")
	public String delete(@PathVariable Long id) {
		if (listaRepository.existsById(id)) {
			listaRepository.deleteById(id);
			return "{ \"status\" : \"deleted\" }";
		}
		return "{ \"status\" : \"error\" }";
	}

	@PutMapping(path = "/{id}")
	public Lista put(@PathVariable Long id, @RequestBody Lista lista) {
		return null;
	}

	@PatchMapping(path = "/{id}")
	public Lista patch(@PathVariable Long id, @RequestBody Lista lista) {
		return null;
	}

}