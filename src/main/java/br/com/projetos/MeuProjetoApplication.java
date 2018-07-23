package br.com.projetos;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projetos.Domain.Categoria;
import br.com.projetos.Domain.Cidade;
import br.com.projetos.Domain.Cliente;
import br.com.projetos.Domain.Endereco;
import br.com.projetos.Domain.Estado;
import br.com.projetos.Domain.Produto;
import br.com.projetos.Domain.Enums.TipoCliente;
import br.com.projetos.repositories.CategoriaRepository;
import br.com.projetos.repositories.CidadeRepository;
import br.com.projetos.repositories.ClienteRepository;
import br.com.projetos.repositories.EnderecoRepository;
import br.com.projetos.repositories.EstadoRepository;
import br.com.projetos.repositories.ProdutoRepository;
import javassist.compiler.ast.ASTList;

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
	
	public static void main(String[] args) {
		SpringApplication.run(MeuProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritório");
		Produto p1 = new Produto(null,"Computador",2000.0);
		Produto p2 = new Produto(null,"Impressora",800.0);
		Produto p3 = new Produto(null,"Mouse",60.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.  saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"MINAS GERAIS");
		Estado est2 = new Estado(null,"SAO PAULO");
		Estado est3 = new Estado(null,"Brasília");
		
		Cidade cid1 = new Cidade(null,"Uberlândia",est1);
		Cidade cid2 = new Cidade(null,"Sao Paulo",est2);
		Cidade cid3 = new Cidade(null,"Campinas",est2);
		Cidade cid4 = new Cidade(null,"Taguatinga",est3);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		est3.getCidades().addAll(Arrays.asList(cid4));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2,est3));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3,cid4));
		
		Cliente cliente1 = new Cliente(null,"Maria Silva","maria@gmail.com","076.234.234-99",TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("(61) 98465-9623","(62) 3155-5055"));
		
		Cliente cliente2 = new Cliente(null,"Danilo Oliveira","danilooliveira79@gmail.com","803.504.400-21",TipoCliente.PESSOAFISICA);
		cliente2.getTelefones().addAll(Arrays.asList("(61) 98465-9636",""));
		
		Endereco e1 = new Endereco(null,"Rua Flores","203","Ap 802","Jardim","72029-219",cliente1,cid1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777-023",cliente1,cid2);
		Endereco e3 = new Endereco(null,"Comercial Norte","203","Apartamento","Taguatniga Norte","72110-300",cliente2,cid4);
		
		cliente1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cliente2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
		
	}
}
