package br.com.projetos.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetos.domains.Cliente;
import br.com.projetos.dto.ClienteDTO;
import br.com.projetos.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(cliente);

	}
	
	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> clientes = clienteService.findAll();
		List<ClienteDTO> clienteDTO = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(clienteDTO);

	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
		Cliente obj= clienteService.fromDTO(objDTO);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		clienteService.deleteById(id);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		
		
		Page<Cliente> list = clienteService.findPage(page, size, direction, orderBy);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);

	}
}
