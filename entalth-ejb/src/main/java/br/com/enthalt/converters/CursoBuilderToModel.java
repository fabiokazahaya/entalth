package br.com.enthalt.converters;

import br.com.enthalt.builder.CreateCurso;

public class CursoBuilderToModel {
    
    public static br.com.enthalt.model.Curso convert(CreateCurso cursoBuilder) {
	
	br.com.enthalt.model.Curso cursoModel = new br.com.enthalt.model.Curso();
	cursoModel.setId(cursoBuilder.getId());
	cursoModel.setNome(cursoBuilder.getNome());
	return cursoModel;

    }
}


