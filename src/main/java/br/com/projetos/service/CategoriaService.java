package br.com.projetos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.domains.Categoria;
import br.com.projetos.exceptions.ObjectNotFoundException;
import br.com.projetos.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria findById(Integer id) throws ObjectNotFoundException {

		Optional<Categoria> obj = categoriaRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não econtrado! Id: " + id + " Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findById(obj.getId());
		// o metodo save serve tanto para salvar tanto para atualizar
		// a diferença esta no id, se estiver nulo ele salva, se ele estiver preeenchido
		// ele atualiza;
		return categoriaRepository.save(obj);
	}
	
	public String  delete(Categoria obj) {
		categoriaRepository.delete(obj);
		return "deleted";
	}
}
