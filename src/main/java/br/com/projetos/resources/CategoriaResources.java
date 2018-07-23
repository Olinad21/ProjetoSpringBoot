package br.com.projetos.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetos.Domain.Categoria;
import br.com.projetos.exceptions.ObjectNotFoundException;
import br.com.projetos.service.CategoriaService;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) throws ObjectNotFoundException {

		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok().body(categoria);

	}
}
