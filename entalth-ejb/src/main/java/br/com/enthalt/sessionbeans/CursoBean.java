package br.com.enthalt.sessionbeans;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.enthalt.business.Curso;

@Stateless
@Local(Curso.class)
public class CursoBean implements Curso {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<br.com.enthalt.model.Curso> recuperarCursos() {
	Query query = em.createQuery("Select e from TBL_CURSO e");
	return (List<br.com.enthalt.model.Curso>) query.getResultList();
    }

    @Override
    public br.com.enthalt.model.Curso recuperarCurso(br.com.enthalt.model.Curso curso) {
	return null;
    }
}
