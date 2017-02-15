package br.com.enthalt.business;

import java.util.List;

import javax.ejb.Local;

@Local
public interface Curso {
    
    public List<br.com.enthalt.model.Curso> recuperarCursos();
    
    public br.com.enthalt.model.Curso recuperarCurso(br.com.enthalt.model.Curso curso);
    
}


