package br.com.projetos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetos.domains.Pedido;
import br.com.projetos.exceptions.ObjectNotFoundException;
import br.com.projetos.repositories.PedidoRepository;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido findById(Integer id) throws ObjectNotFoundException {
		
		Optional<Pedido> obj = pedidoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não econtrado! Id: " +id+ " Tipo: " + Pedido.class.getName()));
	}
}
