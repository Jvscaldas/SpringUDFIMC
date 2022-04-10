package br.com.joaosantana.SpringUDF.persistence;

import java.sql.SQLException;
import java.util.List;

import br.com.joaosantana.SpringUDF.model.Imc;

public interface IImcDao {

	public List<Imc> listaImc() throws SQLException, ClassNotFoundException;

}
