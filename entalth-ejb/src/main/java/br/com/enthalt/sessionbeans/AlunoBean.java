package br.com.enthalt.sessionbeans;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.enthalt.business.Aluno;

@Stateless
@Local(Aluno.class)
public class AlunoBean implements Aluno {

    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<br.com.enthalt.model.Aluno> recuperarAlunos() {

	Query query = em.createQuery("Select e from TBL_ALUNO e");
	return (List<br.com.enthalt.model.Aluno>) query.getResultList();
    }

    @Override
    public void atualizarAluno(br.com.enthalt.model.Aluno aluno) {
	em.merge(aluno);
    }

    @Override
    public void adicionarAluno(br.com.enthalt.model.Aluno aluno) {
	em.persist(aluno);
    }

    @Override
    public void removerAluno(br.com.enthalt.model.Aluno aluno) {
	br.com.enthalt.model.Aluno a = em.merge(aluno);
	em.remove(a);
    }
}
