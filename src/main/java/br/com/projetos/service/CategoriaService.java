package br.com.projetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.projetos.domains.Categoria;
import br.com.projetos.dto.CategoriaDTO;
import br.com.projetos.exceptions.*;
import br.com.projetos.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	/**
	 * 
	 * @param id
	 * @return Categoria
	 * @throws ObjectNotFoundException
	 */
	public Categoria findById(Integer id) throws ObjectNotFoundException {

		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não econtrado! Id: " + id + " Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll(){		
		return categoriaRepository.findAll();
	}

	/**
	 * 
	 * @param obj
	 * @return Categoria
	 */
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	/**
	 * 
	 * @param obj
	 * @return Categoria
	 */
	public Categoria update(Categoria obj) {
		findById(obj.getId());
		// o metodo save serve tanto para salvar tanto para atualizar
		// a diferença esta no id, se estiver nulo ele salva, se ele estiver preeenchido
		// ele atualiza;
		return categoriaRepository.save(obj);
	}
	
	/**
	 *
	 * @param id
	 */
	public void  deleteById(Integer id){
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException("Não é possivel excluir Categoria que possui produtos");
		}
		
		
	}
	
	public Page<Categoria> findPage(Integer page,Integer size,String direction, String orderBy) {
		
		@SuppressWarnings("deprecation")
		PageRequest pageable = new PageRequest(page, size, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageable);
		
	}
}
