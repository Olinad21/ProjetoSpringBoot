package br.com.projetos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.domains.Cliente;
import br.com.projetos.exceptions.ObjectNotFoundException;
import br.com.projetos.repositories.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findById(Integer id) throws ObjectNotFoundException {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não econtrado! Id: " +id+ " Tipo: " + Cliente.class.getName()));
	}
}
