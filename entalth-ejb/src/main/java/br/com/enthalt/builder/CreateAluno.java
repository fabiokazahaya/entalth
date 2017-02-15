package br.com.enthalt.builder;

import br.com.enthalt.model.Curso;


public class CreateAluno {
    
    private final long cpf;
    private final long matricula;
    private final String nome;
    private final Curso curso;

    private CreateAluno(AlunoBuilder builder) {
	this.cpf = builder.cpf;
	this.matricula = builder.matricula;
	this.nome = builder.nome;
	this.curso = builder.curso;
    }

    public long getCpf() {
	return cpf;
    }

    public long getMatricula() {
	return matricula;
    }

    public String getNome() {
	return nome;
    }

    public Curso getCurso() {
	return curso;
    }

    public static class AlunoBuilder {
	
	private final long cpf;
	private final long matricula;
	private String nome;
	private Curso curso;

	public AlunoBuilder(long cpf, long matricula) {
	    this.cpf = cpf;
	    this.matricula = matricula;
	}

	public AlunoBuilder nome(String nome) {
	    this.nome = nome;
	    return this;
	}

	public AlunoBuilder curso(Curso curso) {
	    this.curso = curso;
	    return this;
	}

	public CreateAluno build() {
	    CreateAluno user = new CreateAluno(this);
	    validarAlunoObjeto(user);
	    return user;
	}

	private void validarAlunoObjeto(CreateAluno user) {
	    
	    //validar
	}
    }
}


