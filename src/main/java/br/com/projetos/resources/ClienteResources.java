package br.com.projetos.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetos.domains.Cliente;
import br.com.projetos.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	ClienteService clienteservice;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public ResponseEntity<Cliente> findAll(@PathVariable Integer id) {
		
		Cliente cliente = clienteservice.findById(id);
		return ResponseEntity.ok().body(cliente);

	}
}
