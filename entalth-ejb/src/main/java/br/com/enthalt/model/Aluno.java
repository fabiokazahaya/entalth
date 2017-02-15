package br.com.enthalt.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

@Entity(name = "TBL_ALUNO")
@IdClass(AlunoId.class)
public class Aluno implements Serializable {

    private static final long serialVersionUID = 2225265274912287919L;

    @Id
    @Column(name = "CPF", nullable = false)
    private long cpf;

    @Id
    @Column(name = "MATRICULA", nullable = false)
    private long matricula;

    @Column(name = "NOME")
    private String nome;

    @OneToOne(cascade = CascadeType.MERGE, optional = true, fetch = FetchType.EAGER, orphanRemoval = true)
    private Curso curso;

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    
    
}



