package br.com.projetos;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projetos.Domain.Categoria;
import br.com.projetos.Domain.Produto;
import br.com.projetos.repositories.CategoriaRepository;
import br.com.projetos.repositories.ProdutoRepository;
import javassist.compiler.ast.ASTList;

@SpringBootApplication
public class MeuProjetoApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoria;
	@Autowired
	private ProdutoRepository produto;
	
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
		
		
		categoria.saveAll(Arrays.asList(cat1,cat2));
		produto.  saveAll(Arrays.asList(p1,p2,p3));
				
	}
}
