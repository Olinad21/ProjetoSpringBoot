package br.com.projetos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetos.domains.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
