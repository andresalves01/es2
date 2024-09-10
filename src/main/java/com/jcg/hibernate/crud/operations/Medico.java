package com.jcg.hibernate.crud.operations;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Medico" + Matricula.matricula)
public class Medico implements Serializable {

	@Id
	@Column(name = "CRM")
	private String CRM;

	@Column(name = "nome")
	private String nome;

	@Column(name = "salario")
	private double salario;

	@ManyToOne
	@JoinColumn(name = "id_especialidade")
	private Especialidade especialidade;

	public Medico() {
	}

	public Medico(String cRM, String nome, double salario, Especialidade especialidade) {
		CRM = cRM;
		this.nome = nome;
		this.salario = salario;
		this.especialidade = especialidade;
	}

	public String getCRM() {
		return this.CRM;
	}

	public void setCRM(String CRM) {
		this.CRM = CRM;
	}

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Especialidade getEspecialidade() {
		return this.especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;

	}

	@Override
	public String toString() {
		return "Contato" + Matricula.matricula + " Details?= CRM: " + this.CRM + ", nome: " + this.nome + ", salario: "
				+ this.salario + ", especialidade: " + (this.especialidade != null ? this.especialidade.getId() : "N/A");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}