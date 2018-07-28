package br.com.projetos.email;

import java.util.Date;

public class StoreMail {

	private String Certificado;
	private String Cpf;
	private String Tipo;
	private Date Data;
	private String ValorContribuicao;
	private String Subject;

	public String getCertificado() {
		return Certificado;
	}

	public void setCertificado(String certificado) {
		Certificado = certificado;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public String getValorContribuicao() {
		return ValorContribuicao;
	}

	public void setValorContribuicao(String valorContribuicao) {
		ValorContribuicao = valorContribuicao;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Cpf == null) ? 0 : Cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreMail other = (StoreMail) obj;
		if (Cpf == null) {
			if (other.Cpf != null)
				return false;
		} else if (!Cpf.equals(other.Cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoreMail [Certificado=" + Certificado + ", Cpf=" + Cpf + ", Tipo=" + Tipo + ", Data=" + Data
				+ ", ValorContribuicao=" + ValorContribuicao + ", Subject=" + Subject + "]";
	}

}
