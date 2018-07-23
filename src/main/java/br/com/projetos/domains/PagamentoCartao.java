package br.com.projetos.domains;

import javax.persistence.Entity;

import br.com.projetos.domains.Enums.EstadoPagamento;
@Entity
public class PagamentoCartao extends Pagamento{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numeroParcela;

	

	public PagamentoCartao(Integer id, EstadoPagamento estado,Pedido pedido,Integer numeroParcela) {
		super(id, estado, pedido);
		this.numeroParcela = numeroParcela;
		// TODO Auto-generated constructor stub
	}

	public PagamentoCartao() {
		super();
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

}
