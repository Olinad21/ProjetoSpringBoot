package br.com.projetos.domains.Enums;


public enum EstadoPagamento {
	
	PENDENTE  (1,"Pendente"  ),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado"),;
	
	private Integer cod;
	private String desc;
	
	private EstadoPagamento(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;				
		
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod==null) {			
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.cod)) {
				
				return x;
			}
			
		}
		throw new IllegalArgumentException("Código Iválido: "+cod)	;		
	}
	
}
