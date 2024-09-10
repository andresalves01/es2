package com.jcg.hibernate.crud.operations;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Especialidade" + Matricula.matricula)
public class Especialidade implements Serializable {

  private static final long serialVersionUID = 1L;

  public Especialidade(String nome) {
    this.nome = nome;
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "nome")
  private String nome;

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
}