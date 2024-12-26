package br.com.almaviva.academia.model;

public class Academia {
	private int id;
	private String nome;
	private String telefone;
	private String sedePrincipal;
	private int quantidadeDeFiliais;
	private double planoMensal;

	public Academia(int id, String nome, String telefone, String sedePrincipal, int quantidadeDeFiliais,
			double planoMensal) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.sedePrincipal = sedePrincipal;
		this.quantidadeDeFiliais = quantidadeDeFiliais;
		this.planoMensal = planoMensal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSedePrincipal() {
		return sedePrincipal;
	}

	public void setSedePrincipal(String sedePrincipal) {
		this.sedePrincipal = sedePrincipal;
	}

	public int getQuantidadeDeFiliais() {
		return quantidadeDeFiliais;
	}

	public void setQuantidadeDeFiliais(int quantidadeDeFiliais) {
		this.quantidadeDeFiliais = quantidadeDeFiliais;
	}

	public double getPlanoMensal() {
		return planoMensal;
	}

	public void setPlanoMensal(double planoMensal) {
		this.planoMensal = planoMensal;
	}

	@Override
	public String toString() {
		return "Academia {" + "ID=" + id + ", Nome='" + nome + '\'' + ", Telefone='" + telefone + '\''
				+ ", Sede Principal='" + sedePrincipal + '\'' + ", Quantidade de Filiais=" + quantidadeDeFiliais
				+ ", Plano Mensal=" + planoMensal + '}';
	}
}
