package br.com.projetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.projetos.domains.Cliente;
import br.com.projetos.dto.ClienteDTO;
import br.com.projetos.exceptions.ConstraintViolationException;
import br.com.projetos.exceptions.ObjectNotFoundException;
import br.com.projetos.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findById(Integer id) throws ObjectNotFoundException {

		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não econtrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
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
}
