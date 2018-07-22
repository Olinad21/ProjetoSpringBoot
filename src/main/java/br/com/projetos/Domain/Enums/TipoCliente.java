package br.com.projetos.Domain.Enums;


public enum TipoCliente {
	
	PESSOAFISICA  (1,"Pessoa Física"  ),
	PESSOAJURIDICA(2,"Pessoa Jurídica");
	
	private Integer cod;
	private String desc;
	
	private TipoCliente(Integer cod, String desc) {
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
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod==null) {			
			return null;
		}
		
		for (TipoCliente cliente : TipoCliente.values()) {
			if(cod.equals(cliente.cod)) {
				
				return cliente;
			}
			
		}
		throw new IllegalArgumentException("Código Iválido: "+cod)	;		
	}
	
}
