package com.cantel.java.demoapi1.Usuario;

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

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/listas")

public class UsuarioController {

	@Autowired
	private UsuarioRepository lista;

	@GetMapping
	public List<Usuario> getAll() {
		return lista.findAll();
	}

	@GetMapping(path = "/{id}")
	public Usuario getOne(@PathVariable Long id)  throws JsonProcessingException {
		if (lista.existsById(id)) {
			return lista.findById(id).get();
		}
		return null;
	}

	@PostMapping
	public Usuario post(@RequestBody Usuario usuario) throws JsonProcessingException {
		return lista.save(usuario);
	}

	@DeleteMapping(path = "/{id}", produces = "application/json")
	public String delete(@PathVariable Long id) {
		if (lista.existsById(id)) {
			lista.deleteById(id);
			return "{ \"status\" : \"deleted\" }";
		}
		return "{ \"status\" : \"error\" }";
	}

	@PutMapping(path = "/{id}", produces = "application/json")
	public String updateAll(@PathVariable Long id, @RequestBody Usuario usuario) throws JsonProcessingException {

		// Se o registro com o Id existe.
		if (lista.existsById(id)) {

			// Obtém o registro pelo Id e mapeia seus atributos para "temp".
			lista.findById(id).map(temp -> {

				// Atualiza os campos, 1 por 1 conforme os valores fornecidos por "treco".
				temp.setDate(usuario.getDate());
				temp.setName(usuario.getName());
				temp.setEmail(usuario.getEmail());
				temp.setPassword(usuario.getPassword());

				// Salva o registro atualizado.
				return repository.save(temp);

				// Se Se não consegue obter o registro, ex. Id errado...
			}).orElseGet(() -> {

				// Não faz nada.
				return null;
			});

			// Retorna o registro atualizado usando o método GET.
			// Nota: adicione "throws JsonProcessingException" ao método "updateAll()".
			return getOne(id);

		}

		// Se o registro não existe, retorna o JSON.
		return "{ \"status\" : \"not found\" }";

	}

	@PatchMapping(path = "/{id}", produces = "application/json")
	public String updatePartial(@PathVariable Long id, @RequestBody Usuario usuario) throws JsonProcessingException {

		// Se o registro com o Id existe.
		if (lista.existsById(id)) {

			// Obtém o registro do banco e armazena em "original".
			Usuario original = lista.findById(id).get();

			// Copia o Id no novo registro.
			usuario.setId(id);

			// Testa cada campo.
			// Se o campo não foi fornecido na requisição, pega o valor deste no banco de
			// dados.
			// Se o campo teve um novo valor fornecido., mantém este valor, ignorando o
			// original.
			if (usuario.getDate() == null || usuario.getDate().equals(""))
				usuario.setDate(original.getDate());
			if (usuario.getName() == null || usuario.getName().equals(""))
				usuario.setName(original.getName());
			if (usuario.getEmail() == null || usuario.getEmail().equals(""))
				usuario.setEmail(original.getEmail());
			if (usuario.getPassword() == null || usuario.getPassword().equals(""))
				usuario.setPassword(original.getPassword());

			// Salva o registro atualizado.
			lista.save(usuario);

			// Retorna o registro atualizado usando o método GET.
			// Nota: adicione "throws JsonProcessingException" ao método "updateAll()".
			return getOne(id);

		}

		// Se o registro não existe, retorna o JSON.
		return "{ \"status\" : \"not found\" }";

	}

}