package br.com.projetos;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projetos.domains.Categoria;
import br.com.projetos.domains.Cidade;
import br.com.projetos.domains.Cliente;
import br.com.projetos.domains.Endereco;
import br.com.projetos.domains.Estado;
import br.com.projetos.domains.ItemPedido;
import br.com.projetos.domains.Pagamento;
import br.com.projetos.domains.PagamentoBoleto;
import br.com.projetos.domains.PagamentoCartao;
import br.com.projetos.domains.Pedido;
import br.com.projetos.domains.Produto;
import br.com.projetos.domains.Enums.EstadoPagamento;
import br.com.projetos.domains.Enums.TipoCliente;
import br.com.projetos.repositories.CategoriaRepository;
import br.com.projetos.repositories.CidadeRepository;
import br.com.projetos.repositories.ClienteRepository;
import br.com.projetos.repositories.EnderecoRepository;
import br.com.projetos.repositories.EstadoRepository;
import br.com.projetos.repositories.ItemPedidoRepository;
import br.com.projetos.repositories.PagamentoRepository;
import br.com.projetos.repositories.PedidoRepository;
import br.com.projetos.repositories.ProdutoRepository;

@SpringBootApplication
public class MeuProjetoApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	
	public static void main(String[] args) {
		SpringApplication.run(MeuProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Tecnologia");
		Categoria cat4 = new Categoria(null, "casa mesa e banho");
		Categoria cat5 = new Categoria(null, "Cozinha");
		Categoria cat6 = new Categoria(null, "Quarto");
		Categoria cat7 = new Categoria(null, "Roupas Masculina");
		Categoria cat8 = new Categoria(null, "Roupas Feminas");
		Categoria cat9 = new Categoria(null, "Intimos");
		Categoria cat10 = new Categoria(null, "Escola");
		Categoria cat11= new Categoria(null, "Banheiro");
		Categoria cat12 = new Categoria(null, "Sala");
		Categoria cat13 = new Categoria(null, "Carros");
		Categoria cat14 = new Categoria(null, "Decorações de Sala");
		Categoria cat15 = new Categoria(null, "Decoraçoes de quarto");
		Categoria cat16 = new Categoria(null, "Decoraçoes de cozinha");
		
		
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 60.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(
				Arrays.asList(cat1, cat2,cat3,cat4, cat5,cat6,
							  cat7, cat8,cat9,cat10, cat11,cat12,
							  cat13, cat14,cat15,cat16));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "MINAS GERAIS");
		Estado est2 = new Estado(null, "SAO PAULO");
		Estado est3 = new Estado(null, "Brasília");

		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "Sao Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		Cidade cid4 = new Cidade(null, "Taguatinga", est3);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		est3.getCidades().addAll(Arrays.asList(cid4));

		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3, cid4));

		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "076.234.234-99",
				TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("(61) 98465-9623", "(62) 3155-5055"));

		Cliente cliente2 = new Cliente(null, "Danilo Oliveira", "danilooliveira79@gmail.com", "803.504.400-21",
				TipoCliente.PESSOAFISICA);
		cliente2.getTelefones().addAll(Arrays.asList("(61) 98465-9636", ""));

		Endereco e1 = new Endereco(null, "Rua Flores", "203", "Ap 802", "Jardim", "72029-219", cliente1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777-023", cliente1, cid2);
		Endereco e3 = new Endereco(null, "Comercial Norte", "203", "Apartamento", "Taguatniga Norte", "72110-300",
				cliente2, cid4);

		cliente1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cliente2.getEnderecos().addAll(Arrays.asList(e3));

		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("21/07/2018 21:54"), e3, cliente2);
		Pedido ped2 = new Pedido(null, sdf.parse("23/07/2018 00:54"), e2, cliente1);

		Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 5);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("25/07/2018 23:59"), null);
		ped2.setPagamento(pag2);

		cliente1.getPedidos().addAll(Arrays.asList(ped2));
		cliente2.getPedidos().addAll(Arrays.asList(ped1));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
	
	
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped1.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
