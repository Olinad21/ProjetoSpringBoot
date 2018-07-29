package br.com.projetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import br.com.projetos.domains.Cidade;
import br.com.projetos.domains.Cliente;
import br.com.projetos.domains.Endereco;
import br.com.projetos.domains.Enums.TipoCliente;
import br.com.projetos.dto.ClienteDTO;
import br.com.projetos.dto.ClienteNewDTO;
import br.com.projetos.repositories.ClienteRepository;
import br.com.projetos.repositories.EnderecoRepository;
import br.com.projetos.service.exceptions.ConstraintViolationException;
import br.com.projetos.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente findById(Integer id) throws ObjectNotFoundException {

		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não econtrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
	    enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public List<Cliente> findAll() {

		return clienteRepository.findAll();

	}

	public Cliente update(Cliente obj) {

		Cliente newObj = findById(obj.getId());
		updateNewObj(obj, newObj);
		return clienteRepository.save(newObj);

	}

	/**
	 * Atualiza somente o nome e e-mail para o novo objeto
	 * 
	 * @param obj
	 * @param newObj
	 */
	private void updateNewObj(Cliente obj, Cliente newObj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

	/**
	 *
	 * @param id
	 */
	public void deleteById(Integer id) {
		findById(id);
		try {
			clienteRepository.deleteById(id);
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException("Não é possivel excluir Cliente");
		}

	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @param direction
	 * @param orderBy
	 * @return Page
	 */
	public Page<Cliente> findPage(Integer page, Integer size, String direction, String orderBy) {

		@SuppressWarnings("deprecation")
		PageRequest pageable = new PageRequest(page, size, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageable);

	}

	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO obj) {

		Cliente cliente = new Cliente(null, obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(),
				TipoCliente.toEnum(obj.getTipo()));
		Cidade cidade = new Cidade(obj.getCidadeId(), null, null);
		Endereco end = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemtento(), obj.getBairro(),
				obj.getCep(), cliente, cidade);

		cliente.getEnderecos().add(end);
		cliente.getTelefones().add(obj.getTelefone1());

		if (obj.getTelefone2() != null) {
			cliente.getTelefones().add(obj.getTelefone2());
		}
		if (obj.getTelefone3() != null) {
			cliente.getTelefones().add(obj.getTelefone3());
		}

		return cliente;
	}
}
