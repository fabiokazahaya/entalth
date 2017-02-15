package br.com.enthalt.business;

import java.util.List;

import javax.ejb.Local;

import br.com.enthalt.model.Curso;

@Local
public interface CursoJdbc {
    
    public void adicionarCurso(Curso curso);
    
    public List<Curso> recuperarCursos(Curso curso);
    
    public void removerCurso(Curso curso);
    
    public void editarCurso(Curso curso);

}


