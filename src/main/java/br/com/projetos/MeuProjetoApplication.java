package br.com.projetos;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projetos.Domain.Categoria;
import br.com.projetos.repositories.CategoriaRepository;
import javassist.compiler.ast.ASTList;

@SpringBootApplication
public class MeuProjetoApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository obj;
	public static void main(String[] args) {
		SpringApplication.run(MeuProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		obj.saveAll(Arrays.asList(cat1,cat2));
				
	}
}
