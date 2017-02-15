package br.com.enthalt.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.enthalt.builder.CreateAluno;
import br.com.enthalt.builder.CreateCurso;
import br.com.enthalt.business.Aluno;
import br.com.enthalt.business.Curso;
import br.com.enthalt.converters.AlunoBuilderToModel;
import br.com.enthalt.converters.ConverterException;
import br.com.enthalt.converters.CursoBuilderToModel;
import br.com.enthalt.exception.DuplicatedException;
import br.com.enthalt.utils.MessageUtils;

@ManagedBean(name = "alunoMB", eager = true)
@ApplicationScoped
public class AlunoMB {

    private static final Logger LOG = LoggerFactory.getLogger(AlunoMB.class);

    @EJB
    private Aluno alunoInterface;

    @EJB
    private Curso curso;

    private br.com.enthalt.model.Aluno aluno;

    private List<br.com.enthalt.model.Aluno> alunos;

    private List<br.com.enthalt.model.Curso> cursos;

    private br.com.enthalt.model.Curso selectedCurso;

    public AlunoMB() {
	super();
    }
    
    public void adicionarAluno() {

	try {

	    usuarioCadastrado(aluno.getCpf());

	    if (aluno.getCurso() == null) {

		LOG.info("Adicionando aluno sem Curso");

		CreateAluno createdAluno = new CreateAluno.AlunoBuilder(aluno.getCpf(), aluno.getMatricula()).nome(aluno.getNome()).build();
		alunoInterface.adicionarAluno(AlunoBuilderToModel.convert(createdAluno));

	    } else {

		LOG.info("Adicionando aluno sem Curso");

		CreateCurso createdCurso = new CreateCurso.CursoBuilder(aluno.getCurso().getId()).nome(aluno.getCurso().getNome()).build();
		br.com.enthalt.model.Curso c = CursoBuilderToModel.convert(createdCurso);

		br.com.enthalt.model.Curso cc = curso.recuperarCurso(c);

		CreateAluno createdAluno = new CreateAluno.AlunoBuilder(aluno.getCpf(), aluno.getMatricula()).nome(aluno.getNome()).curso(cc).build();
		alunoInterface.adicionarAluno(AlunoBuilderToModel.convert(createdAluno));

	    }

	    LOG.info("Aluno registrado com sucesso");
	    MessageUtils.createMessage(FacesMessage.SEVERITY_INFO, "Aluno registrado com sucesso");

	} catch (ConverterException e) {

	    MessageUtils.createMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar aluno");
	    LOG.error(e.getMessage(), e);

	} catch (DuplicatedException e) {

	    MessageUtils.createMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
	    LOG.error(e.getMessage(), e);

	}

	System.out.println("Adicionando usuario");
    }
    
    public void removerAluno(br.com.enthalt.model.Aluno aluno){
	
	alunoInterface.removerAluno(aluno);
	alunoInterface.recuperarAlunos();
	MessageUtils.createMessage(FacesMessage.SEVERITY_INFO, "Usuário removido com sucesso");
	LOG.info("Usuário removido com sucesso");
	
    }

    public void usuarioCadastrado(long cpf) {

	List<br.com.enthalt.model.Aluno> al = alunoInterface.recuperarAlunos();
	for (br.com.enthalt.model.Aluno aluno : al) {
	    if (aluno.getCpf() == cpf){
		throw new DuplicatedException("Aluno já cadastrado");
	    }
	}
    }

    @PostConstruct
    public void init() {
	this.aluno = new br.com.enthalt.model.Aluno();
    }

    public void onRowEdit(RowEditEvent event) {
	alunoInterface.atualizarAluno((br.com.enthalt.model.Aluno) event.getObject());
	FacesMessage msg = new FacesMessage("Aluno editado",
		((br.com.enthalt.model.Aluno) event.getObject()).getNome());
	FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
	FacesMessage msg = new FacesMessage("Edição cancelada",
		((br.com.enthalt.model.Aluno) event.getObject()).getNome());
	FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
	Object oldValue = event.getOldValue();
	Object newValue = event.getNewValue();

	if (newValue != null && !newValue.equals(oldValue)) {
	    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno atualizado",
		    "Antigo: " + oldValue + ", Novo:" + newValue);
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	}
    }

    public List<br.com.enthalt.model.Curso> getCursos() {
	if (this.cursos == null)
	    cursos = curso.recuperarCursos();
	return cursos;
    }

    public void setCursos(List<br.com.enthalt.model.Curso> cursos) {
	this.cursos = cursos;
    }

    public List<br.com.enthalt.model.Aluno> getAlunos() {
	if (this.alunos == null)
	    alunos = alunoInterface.recuperarAlunos();
	return alunos;
    }

    public void setAlunos(List<br.com.enthalt.model.Aluno> alunos) {
	this.alunos = alunos;
    }

    public List<SelectItem> getEmpresas() {
	List<SelectItem> items = new ArrayList<SelectItem>();
	for (br.com.enthalt.model.Curso c : this.cursos) {
	    items.add(new SelectItem(c, c.getNome()));
	}
	return items;
    }

    public br.com.enthalt.model.Curso getSelectedCurso() {
	return selectedCurso;
    }

    public void setSelectedCurso(br.com.enthalt.model.Curso selectedCurso) {
	this.selectedCurso = selectedCurso;
    }

    public Curso getCurso() {
	return curso;
    }

    public void setCurso(Curso curso) {
	this.curso = curso;
    }

    public br.com.enthalt.model.Aluno getAluno() {
	if (aluno == null) {
	    aluno = new br.com.enthalt.model.Aluno();
	}
	return aluno;
    }

    public void setAluno(br.com.enthalt.model.Aluno aluno) {
	this.aluno = aluno;
    }
}



