package br.com.enthalt.converters;

import br.com.enthalt.model.Aluno;

public class AlunoBuilderToModel {
    
    public static Aluno convert(br.com.enthalt.builder.CreateAluno alunoBuilder) {
	
	Aluno alunoModel = new Aluno();
	alunoModel.setCpf(alunoBuilder.getCpf());
	alunoModel.setMatricula(alunoBuilder.getMatricula());
	alunoModel.setNome(alunoBuilder.getNome());
	alunoModel.setCurso(alunoBuilder.getCurso());
	return alunoModel;
	
    }
}


