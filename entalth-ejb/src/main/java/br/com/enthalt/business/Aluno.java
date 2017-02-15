package br.com.enthalt.business;

import java.util.List;

import javax.ejb.Local;

@Local
public interface Aluno {
    
    public List<br.com.enthalt.model.Aluno> recuperarAlunos();

    public void atualizarAluno(br.com.enthalt.model.Aluno aluno);

    public void adicionarAluno(br.com.enthalt.model.Aluno aluno);
    
    public void removerAluno(br.com.enthalt.model.Aluno aluno);
    
}


