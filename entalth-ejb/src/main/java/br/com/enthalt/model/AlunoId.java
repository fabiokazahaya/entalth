package br.com.enthalt.model;

import java.io.Serializable;

public class AlunoId implements Serializable {

    private static final long serialVersionUID = 4518917574217106800L;

    private long cpf;
    private long matricula;

    public AlunoId() {

    }

    public AlunoId(long cpf, long matricula) {
	this.cpf = cpf;
	this.matricula = matricula;
    }

    public long getCpf() {
	return cpf;
    }

    public void setCpf(long cpf) {
	this.cpf = cpf;
    }

    public long getMatricula() {
	return matricula;
    }

    public void setMatricula(long matricula) {
	this.matricula = matricula;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (cpf ^ (cpf >>> 32));
	result = prime * result + (int) (matricula ^ (matricula >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AlunoId other = (AlunoId) obj;
	if (cpf != other.cpf)
	    return false;
	if (matricula != other.matricula)
	    return false;
	return true;
    }

}
