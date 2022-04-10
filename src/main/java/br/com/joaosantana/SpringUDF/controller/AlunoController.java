package br.com.joaosantana.SpringUDF.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.joaosantana.SpringUDF.model.Aluno;
import br.com.joaosantana.SpringUDF.model.Imc;
import br.com.joaosantana.SpringUDF.persistence.AlunoDao;
import br.com.joaosantana.SpringUDF.persistence.ImcDao;

@Controller
public class AlunoController {

	@Autowired
	ImcDao iDao;

	@Autowired
	AlunoDao aDao;

	@RequestMapping(name = "aluno", value = "/aluno", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		String erro = "";
		List<Imc> imcs = new ArrayList<Imc>();

		try {
			imcs = listaImc();
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("imcs", imcs);
		}

		return new ModelAndView("aluno");
	}

	@RequestMapping(name = "aluno", value = "/aluno", method = RequestMethod.POST)
	public ModelAndView alunos(ModelMap model, @RequestParam Map<String, String> allParam) {
		String cod = allParam.get("cod");
		String botao = allParam.get("botao");

		Aluno aluno = new Aluno();
		List<Aluno> alunos = new ArrayList<Aluno>();
		String erro = "";
		List<Imc> imcs = new ArrayList<Imc>();

		try {
			imcs = listaImc();
			if (botao.equals("Listar")) {
				alunos = aDao.findAlunosCondicao();
				aluno = null;
			} else {
				if (botao.equals("Consultar") && !cod.equals("")) {
					aluno.setCod(Integer.parseInt(cod));
					aluno = aDao.findAlunoCondicao(aluno);
				} else {
					erro = "Preencha os campos corretamente!";
					aluno = null;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("imcs", imcs);
			model.addAttribute("aluno", aluno);
			model.addAttribute("alunos", alunos);
		}

		return new ModelAndView("aluno");

	}

	private List<Imc> listaImc() throws ClassNotFoundException, SQLException {
		List<Imc> imcs = iDao.listaImc();
		return imcs;
	}

}
