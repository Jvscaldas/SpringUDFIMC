package br.com.joaosantana.SpringUDF.persistence;

import java.sql.SQLException;
import java.util.List;

import br.com.joaosantana.SpringUDF.model.Aluno;

public interface IAlunoDao {

	public List<Aluno> findAlunosCondicao() throws SQLException, ClassNotFoundException;

	public Aluno findAlunoCondicao(Aluno al) throws SQLException, ClassNotFoundException;

}
