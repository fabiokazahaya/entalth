package br.com.enthalt.sessionbeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import br.com.enthalt.business.CursoJdbc;
import br.com.enthalt.exception.JdbcException;
import br.com.enthalt.model.Curso;
import oracle.jdbc.internal.OracleTypes;

@Stateless
public class CursoJdbcBean implements CursoJdbc {

    @PersistenceContext
    EntityManager em;

    @Resource(mappedName = "jdbc/entalth")
    DataSource dataSource;

    @Override
    public List<Curso> recuperarCursos(Curso curso) {

	PreparedStatement ps = null;
	Connection con = null;

	try {

	    List<Curso> cursos = new ArrayList<Curso>();

	    con = dataSource.getConnection();

	    CallableStatement st = null;
	    ResultSet rs = null;
	    Connection conn = em.unwrap(Connection.class);
	    st = conn.prepareCall("begin  GET_CURSOS(?,?); end;");
	    st.setString(1, curso.getNome());
	    st.registerOutParameter(2, OracleTypes.CURSOR);
	    st.executeUpdate();
	    rs = (ResultSet) st.getObject(2);

	    while (rs.next()) {
		Curso temp = new Curso();
		temp.setId(Long.parseLong(rs.getString("ID")));
		temp.setNome(rs.getString("NOME"));
		cursos.add(temp);
	    }

	    return cursos;

	} catch (SQLException sql) {
	    throw new JdbcException("Erro ao recuperar cursos", sql);
	} finally {
	    try {
		if (ps != null)
		    ps.close();
	    } catch (Exception e) {
		throw new JdbcException("Erro ao fechar a conexão", e);
	    }
	    try {
		if (con != null)
		    con.close();
	    } catch (Exception e) {
		throw new JdbcException("Erro ao fechar a conexão", e);
	    }
	}
    }

    @Override
    public void removerCurso(Curso curso) {

	PreparedStatement ps = null;
	Connection con = null;

	try {

	    con = dataSource.getConnection();

	    String deletarSQL = "DELETE FROM TBL_CURSO WHERE ID = ?";

	    Connection conn = em.unwrap(Connection.class);
	    ps = conn.prepareStatement(deletarSQL);
	    ps.setLong(1, curso.getId());
	    ps.executeUpdate();

	} catch (SQLException sql) {
	    throw new JdbcException("Erro ao remover curso", sql);
	} finally {
	    try {
		if (ps != null)
		    ps.close();
	    } catch (Exception e) {
		throw new JdbcException("Erro ao fechar a conexão", e);
	    }
	    try {
		if (con != null)
		    con.close();
	    } catch (Exception e) {
		throw new JdbcException("Erro ao fechar a conexão", e);
	    }
	}

    }

    @Override
    public void adicionarCurso(Curso curso) {

	PreparedStatement ps = null;
	Connection con = null;

	try {

	    con = dataSource.getConnection();

	    CallableStatement st = null;
	    Connection conn = em.unwrap(Connection.class);
	    st = conn.prepareCall("begin  INSERT_OR_UPDATE_CURSO(?,?); end;");
	    st.setLong(1, curso.getId());
	    st.setString(2, curso.getNome());
	    st.executeUpdate();

	} catch (SQLException sql) {
	    throw new JdbcException("Erro ao recuperar adicionar curso", sql);
	} finally {
	    try {
		if (ps != null)
		    ps.close();
	    } catch (Exception e) {
		throw new JdbcException("Erro ao fechar a conexão", e);
	    }
	    try {
		if (con != null)
		    con.close();
	    } catch (Exception e) {
		throw new JdbcException("Erro ao fechar a conexão", e);
	    }
	}

    }

    @Override
    public void editarCurso(Curso curso) {

	PreparedStatement ps = null;
	Connection con = null;

	try {

	    con = dataSource.getConnection();

	    CallableStatement st = null;
	    Connection conn = em.unwrap(Connection.class);
	    st = conn.prepareCall("begin  INSERT_OR_UPDATE_CURSO(?,?); end;");
	    st.setLong(1, curso.getId());
	    st.setString(2, curso.getNome());
	    st.executeUpdate();

	} catch (SQLException sql) {
	    throw new JdbcException("Erro ao editar curso", sql);
	} finally {
	    try {
		if (ps != null)
		    ps.close();
	    } catch (Exception e) {
		throw new JdbcException("Erro ao fechar a conexão", e);
	    }
	    try {
		if (con != null)
		    con.close();
	    } catch (Exception e) {
		throw new JdbcException("Erro ao fechar a conexão", e);
	    }
	}
    }
}
