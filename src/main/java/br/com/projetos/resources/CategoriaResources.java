package br.com.projetos.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.projetos.domains.Categoria;
import br.com.projetos.dto.CategoriasDTO;
import br.com.projetos.exceptions.ObjectNotFoundException;
import br.com.projetos.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) throws ObjectNotFoundException {

		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok().body(categoria);

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriasDTO>> findAll(){
		List<Categoria> list = categoriaService.findAll();	
		List<CategoriasDTO> listDTO = list.stream().
				map(obj -> new CategoriasDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj = categoriaService.update(obj);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		categoriaService.deleteById(id);
		return ResponseEntity.noContent().build();

	}
}
