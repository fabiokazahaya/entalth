package br.com.enthalt.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.enthalt.business.CursoJdbc;
import br.com.enthalt.exception.JdbcException;
import br.com.enthalt.model.Curso;

@ManagedBean(name = "cursoMB", eager = true)
@ApplicationScoped
public class CursoMB {
    
    @EJB
    CursoJdbc cursoJdbc;

    private static final Logger LOG = LoggerFactory.getLogger(CursoMB.class);

    private br.com.enthalt.model.Curso curso;
    
    private List<Curso> cursos;

    public void adicionarCurso() {
	try {
	    cursoJdbc.adicionarCurso(curso);	    
	} catch (JdbcException e) {
	    LOG.error(e.getMessage());
	}
    }
    
    public void removerCurso(Curso curso){
	try {
	    cursoJdbc.removerCurso(curso);    
	} catch (Exception e) {
	    LOG.error(e.getMessage());
	}
    }
    
    public void onRowEdit(RowEditEvent event) {
	cursoJdbc.editarCurso((br.com.enthalt.model.Curso) event.getObject());
	FacesMessage msg = new FacesMessage("Curso editado", ((br.com.enthalt.model.Curso) event.getObject()).getNome());
	FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
	FacesMessage msg = new FacesMessage("Edição cancelada",((br.com.enthalt.model.Curso) event.getObject()).getNome());
	FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
	Object oldValue = event.getOldValue();
	Object newValue = event.getNewValue();

	if (newValue != null && !newValue.equals(oldValue)) {
	    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso atualizado", "Antigo: " + oldValue + ", Novo:" + newValue);
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	}
    }

    public br.com.enthalt.model.Curso getCurso() {
	if (curso == null){
	    curso = new br.com.enthalt.model.Curso();
	}
        return curso;
    }

    public void setCurso(br.com.enthalt.model.Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getCursos() {
	try {
	    if (cursos == null){
		cursos = cursoJdbc.recuperarCursos(curso);
	    }    
	} catch (Exception e) {
	    LOG.error(e.getMessage());
	}
	return cursos;
    }

    public void setCursos(List<Curso> cursos) {
	this.cursos = cursos;
    }
}


