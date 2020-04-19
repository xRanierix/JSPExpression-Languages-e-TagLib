package ads.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;

import ads.pipoca.model.dao.FilmeDAO;
import ads.pipoca.model.entity.Filme;

//Faz o meio de campo entre o banco e o controller
public class FilmeService { 

	public int inserirFilme(Filme filme) throws IOException {
		FilmeDAO dao = new FilmeDAO();
		return dao.inserirFilme(filme);
	}
	
	public Filme atualizarFilme(Filme filme) throws IOException {
		FilmeDAO dao = new FilmeDAO();
		return dao.atualizarFilme(filme);
	}

	public Filme buscarFilme(int id) throws IOException { //só chama o DAO
		FilmeDAO dao = new FilmeDAO(); //instaciei o DAO puramente para poder utilizar os métodos dele
		return dao.buscarFilme(id); //o benedito que eu preciso de fato
	}
	
	public int excluirFilme(int id) throws IOException {
		FilmeDAO dao = new FilmeDAO();
		int result = dao.excluirFilme(id);
		return result;
	}
	
	public ArrayList<Filme> listarFilmes() throws IOException {
		FilmeDAO dao = new FilmeDAO();
		return dao.listarFilmes();
	}
}
