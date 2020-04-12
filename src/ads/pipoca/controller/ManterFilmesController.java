package ads.pipoca.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.pipoca.model.entity.Filme;
import ads.pipoca.model.entity.Genero;
import ads.pipoca.model.service.FilmeService;
import ads.pipoca.model.service.GeneroService;

@WebServlet("/manter_filmes.do")
public class ManterFilmesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		Filme filme = null;
		Genero genero = null;
		FilmeService fService = new FilmeService();
		GeneroService gService = new GeneroService();
		String saida = null, titulo = null, descricao = null, diretor = null, posterPath = null;
		double popularidade;
		int idGenero, idFilme;
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dataLanc = null;
		ArrayList<Genero> generos = new ArrayList<Genero>();
		ArrayList<Filme> filmes = new ArrayList<Filme>();

		switch (acao) {
		case "filmes":
			filmes = fService.listarFilmes();
			request.setAttribute("filmes", filmes);
			saida = "ListarFilmes.jsp";
			break;
		case "generos":
			generos = gService.listarGeneros();
			request.setAttribute("generos", generos);
			saida = "InserirFilme.jsp";
			break;
		case "mostrar":
			String id_filme = request.getParameter("id_filme");
			idFilme = Integer.parseInt(id_filme);
			filme = fService.buscarFilme(idFilme);
			System.out.println(filme);
			request.setAttribute("filme", filme);
			saida = "Filme.jsp";
			break;
		case "inserir":
			titulo = request.getParameter("titulo");
			descricao = request.getParameter("descricao");
			diretor = request.getParameter("diretor");
			idGenero = Integer.parseInt(request.getParameter("genero"));
			popularidade = Double.parseDouble(request.getParameter("popularidade"));
			posterPath = request.getParameter("poster_path");
			filme = new Filme();
			filme.setTitulo(titulo);
			filme.setDescricao(descricao);
			filme.setDiretor(diretor);

			try {
				dataLanc = formater.parse(request.getParameter("data_lancamento"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filme.setDataLancamento(dataLanc);
			filme.setPopularidade(popularidade);
			filme.setPosterPath(posterPath);
			genero = gService.buscarGenero(idGenero);
			filme.setGenero(genero);
			int id = fService.inserirFilme(filme);
			filme.setId(id);
			System.out.println(filme);
			request.setAttribute("filme", filme);
			saida = "Filme.jsp";
			break;

		case "page_atualizar":
			idFilme = Integer.parseInt(request.getParameter("id_atualizar"));
			filme = fService.buscarFilme(idFilme);
			generos = gService.listarGeneros();
			request.setAttribute("generos", generos);
			request.setAttribute("filme", filme);
			saida = "AtualizarFilme.jsp";

			break;

		case "atualizar":
			idFilme = Integer.parseInt(request.getParameter("id_filme"));

			titulo = request.getParameter("titulo");
			descricao = request.getParameter("descricao");
			diretor = request.getParameter("diretor");
			idGenero = Integer.parseInt(request.getParameter("genero"));
			popularidade = Double.parseDouble(request.getParameter("popularidade"));
			posterPath = request.getParameter("poster_path");
			filme = new Filme();
			filme.setTitulo(titulo);
			filme.setDescricao(descricao);
			filme.setDiretor(diretor);

			try {
				dataLanc = formater.parse(request.getParameter("data_lancamento"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filme.setDataLancamento(dataLanc);
			filme.setPopularidade(popularidade);
			filme.setPosterPath(posterPath);
			genero = gService.buscarGenero(idGenero);
			filme.setGenero(genero);
			filme.setId(idFilme);
			Filme filmeAtualizado = fService.atualizarFilme(filme);
			request.setAttribute("filme", filmeAtualizado);
			saida = "Filme.jsp";
			break;

		case "excluir":

			idFilme = Integer.parseInt(request.getParameter("id_excluir"));

			filme = fService.buscarFilme(idFilme);
			if (filme != null) {

				int result = fService.excluirFilme(idFilme);
				request.setAttribute("filme", filme);
				saida = "Filme.jsp";

			} else {
				saida = "nExiste.html";
			}

			break;
		}
		RequestDispatcher view = request.getRequestDispatcher(saida);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
