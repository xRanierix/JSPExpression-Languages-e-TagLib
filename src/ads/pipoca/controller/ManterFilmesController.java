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

@WebServlet("/manter_filmes.do")   // recebe os dados encaminhados das páginas do front (web content)
public class ManterFilmesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) //começa a ação com os dados recebidos
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao"); //criou a  variável acao no java que recebe a variáveis "acao" do html
		
		Filme filme = null;               //criando variáveis para serem usadas depois
		Genero genero = null;
		FilmeService fService = new FilmeService();
		GeneroService gService = new GeneroService();
		String saida = null, titulo = null, descricao = null, diretor = null, posterPath = null;
		double popularidade;
		int idGenero, idFilme;
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd"); //formatando a data pro formato sql
		java.util.Date dataLanc = null;
		ArrayList<Genero> generos = new ArrayList<Genero>(); //criando arrays para serem usadas depois
		ArrayList<Filme> filmes = new ArrayList<Filme>();

		switch (acao) {  //verifica qual foi a acao selecionada
		case "filmes":
			filmes = fService.listarFilmes(); //o objeto da array da linha 39 recebe a array com a função de listar os filmes
			request.setAttribute("filmes", filmes); // "filmes" corresponde a var do jsp e filmes a var do java
			saida = "ListarFilmes.jsp"; //arquivo jsp que desejo carregar
			break;
		case "generos":
			generos = gService.listarGeneros(); //puxando as informações de gênero do banco e colocando no objetivo da minha array criada na linha 38
			request.setAttribute("generos", generos); //jogando pro jsp
			saida = "InserirFilme.jsp"; //este jsp específicamente
			break;
		case "mostrar":
		  
			idFilme = Integer.parseInt(request.getParameter("id_filme"));  // o valor do "id_filme" do jsp será carregado no IdFilme do java
			// e convertido pra int
			filme = fService.buscarFilme(idFilme);	//chamando método de buscar filme passando a Id (idFilme
			if(filme == null) { //caso não encontre
				saida = "nExiste.html";
				break;
			}
			System.out.println(filme); //filme recebido da linha 56
			request.setAttribute("filme", filme); //pega a var do java e joga na var do jsp
			saida = "Filme.jsp"; //endereço onde será enviado
			break;
		case "inserir":
			titulo = request.getParameter("titulo");  //recebe a var "titulo" do html e coloca ela na var do java titulo
			descricao = request.getParameter("descricao");
			diretor = request.getParameter("diretor");
			idGenero = Integer.parseInt(request.getParameter("genero")); // mesmo passo da linha 66 porém convertendo para int
			popularidade = Double.parseDouble(request.getParameter("popularidade")); // mesmo passo da linha 66 porém convertendo para double
			posterPath = request.getParameter("poster_path");
			
			//Convertendo Data
			try {
				dataLanc = formater.parse(request.getParameter("data_lancamento")); // mesmo passo da linha 66 porém convertendo pra data, o formato da data foi definido na linha 36
			} catch (ParseException e) {											
				e.printStackTrace();
			}
			
			//busca no banco o genero a partir da id que o usuário inseriu, colocando ela na var genero
			genero = gService.buscarGenero(idGenero);  
			
			//Mandando pro banco
			filme = new Filme(); //instanciei o objeto que será recebido no método inserirFilme
			filme.setTitulo(titulo);
			filme.setDescricao(descricao);
			filme.setDiretor(diretor);		
			filme.setDataLancamento(dataLanc);
			filme.setPopularidade(popularidade);
			filme.setPosterPath(posterPath);	
			filme.setGenero(genero);
			
			//chamar método para inserir o filme
			// toda vez que um metodo tem um retorno, uma var tem que receber ele.
			int id = fService.inserirFilme(filme); //chamando o metodo inserirFilme, inserindo ele no banco 
			filme.setId(id); //pego o id da linha 95 que o banco me retornou e insere no objeto filme
			request.setAttribute("filme", filme); //passando o objeto filme para o "filme" do jsp
			saida = "Filme.jsp";
			break;

		case "page_atualizar":
			idFilme = Integer.parseInt(request.getParameter("id_atualizar")); //"id_atualizar" é convertido pra int e jogado no idFilme
			filme = fService.buscarFilme(idFilme); //filme recebe o resultado do buscar filme, utilizando o idFilme como referência
			generos = gService.listarGeneros(); //generos recebe a lista de gêneros do banco via array
			request.setAttribute("generos", generos); // relaciona o item do jsp e o do java
			request.setAttribute("filme", filme); // mesma coisa da linha acima 
			saida = "AtualizarFilme.jsp"; //joga o resultado no jsp.

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
			
			//Atualizando o filme do banco com as informações novas que alterei acima
			Filme filmeAtualizado = fService.atualizarFilme(filme); //o filmeAtualizado recebe do banco o filme que acabei de atualizar
			
			request.setAttribute("filme", filmeAtualizado); // "filme" corresponde a variável do jsp. e filmeAtualizado corresponde a var. do java
			saida = "Filme.jsp"; //saida via jsp
			break;

		case "excluir":

			idFilme = Integer.parseInt(request.getParameter("id_excluir"));

			filme = fService.buscarFilme(idFilme);
			if (filme != null) {

				fService.excluirFilme(idFilme);
				request.setAttribute("filme", filme); // da linha 148
				saida = "Filme.jsp"; // saída via jsp.

			} else {
				saida = "nExiste.html";
			}

			break;
		}
		RequestDispatcher view = request.getRequestDispatcher(saida); // o view recebeu da var saida a ação 
		view.forward(request, response); // E o view forward emite a visualização

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) //Chama doGet
			throws ServletException, IOException {

		doGet(request, response);
	}

}
