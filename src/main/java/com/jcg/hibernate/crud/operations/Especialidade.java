package com.jcg.hibernate.crud.operations;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Especialidade806191")
public class Especialidade implements Serializable {

  public Especialidade() {
  }

  public Especialidade(String nome) {
    this.nome = nome;
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "nome")
  private String nome;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return "Especialidade [id=" + id + ", nome=" + nome + "]";
  }

}