package br.com.enthalt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "TBL_CURSO")
public class Curso implements Serializable {

    private static final long serialVersionUID = -2610081822565474895L;

    @Id
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "NOME")
    private String nome;

    @OneToOne(mappedBy = "curso")
    Aluno aluno;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Aluno getAluno() {
	return aluno;
    }

    public void setAluno(Aluno aluno) {
	this.aluno = aluno;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

}
