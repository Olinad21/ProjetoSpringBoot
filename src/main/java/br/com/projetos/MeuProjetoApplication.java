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
import br.com.projetos.Domain.Estado;
import br.com.projetos.Domain.Produto;
import br.com.projetos.repositories.CategoriaRepository;
import br.com.projetos.repositories.CidadeRepository;
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
		
		Estado est1 = new Estado(null,"Sao Paulo");
		Estado est2 = new Estado(null,"Bahia");
		
		Cidade cid1 = new Cidade(null,"Osasco",est1);
		Cidade cid2 = new Cidade(null,"Salvador",est2);
		Cidade cid3 = new Cidade(null,"Barra-BA",est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
				
	}
}
