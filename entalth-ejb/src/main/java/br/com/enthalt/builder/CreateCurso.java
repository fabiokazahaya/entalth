package br.com.enthalt.builder;

public class CreateCurso {
    
    private final long id;
    private final String nome;
    

    private CreateCurso(CursoBuilder builder) {
	this.id = builder.id;
	this.nome = builder.nome;
    }
    
    public long getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public static class CursoBuilder {
	
	private final long id;
	private String nome;
	
	public CursoBuilder(long id) {
	    this.id = id;
	}

	public CursoBuilder nome(String nome) {
	    this.nome = nome;
	    return this;
	}
	
	public CreateCurso build() {
	    CreateCurso curso = new CreateCurso(this);
	    validarCursoObjeto(curso);
	    return curso;
	}

	private void validarCursoObjeto(CreateCurso curso) {
	    
	    //validar
	}
    }
}



