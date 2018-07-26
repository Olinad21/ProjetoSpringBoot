package br.com.projetos.dto;

import java.io.Serializable;

import br.com.projetos.domains.Categoria;

public class CategoriasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public CategoriasDTO() {

	}

	public CategoriasDTO(Categoria obj) {
		this.id= obj.getId();
		this.nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
