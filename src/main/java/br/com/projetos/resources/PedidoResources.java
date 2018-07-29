package br.com.projetos.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.projetos.domains.Pedido;
import br.com.projetos.service.PedidoService;
import br.com.projetos.service.exceptions.ObjectNotFoundException;


@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResources {

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) throws ObjectNotFoundException {

		Pedido pedido = pedidoService.findById(id);
		return ResponseEntity.ok().body(pedido);

	}
}
