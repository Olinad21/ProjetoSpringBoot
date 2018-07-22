package br.com.projetos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetos.Domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
