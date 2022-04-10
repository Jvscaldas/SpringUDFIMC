package br.com.joaosantana.SpringUDF.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.joaosantana.SpringUDF.model.Imc;

@Repository
public class ImcDao implements IImcDao {

	@Autowired
	GenericDao gDao;

	@Override
	public List<Imc> listaImc() throws SQLException, ClassNotFoundException {
		List<Imc> imcs = new ArrayList<Imc>();

		Connection c = gDao.getConnection();
		String sql = "SELECT faixa, condicao FROM imc";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Imc imc = new Imc();
			imc.setFaixa(rs.getString("faixa"));
			imc.setCondicao(rs.getString("condicao"));

			imcs.add(imc);
		}

		rs.close();
		ps.close();
		c.close();

		return imcs;

	}

}
