package ads.pipoca.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ads.pipoca.model.entity.Filme;
import ads.pipoca.model.entity.Genero;
import ads.pipoca.model.service.FilmeService;
import ads.pipoca.model.service.GeneroService;

public class ManterFilmes {

	public void listarFilme(int id) throws IOException {
		Filme filme;
		FilmeService service = new FilmeService();
		filme = new Filme();
		filme = service.buscarFilme(id);
		System.out.println(filme);
	}

	public void listarGeneros() throws IOException {
		GeneroService genero;
		genero = new GeneroService();
		ArrayList<Genero> generos = genero.listarGeneros();
		System.out.println(generos);
	}

	public int inserirFilme() throws IOException {
		Filme filme;
		Genero genero;
		FilmeService service = new FilmeService();
		
		filme = new Filme();
		filme.setTitulo("O Náufrago");
		filme.setDescricao("Chuck Noland (Tom Hanks) um inspetor da Federal Express (FedEx), "
				+ "multinacional encarregada de enviar cargas e correspondências, que tem por "
				+ "função checar vários escritórios da empresa pelo planeta. Porém, em uma de "
				+ "suas costumeiras viagens ocorre um acidente, que o deixa preso em uma ilha "
				+ "completamente deserta por 4 anos. Com sua noiva (Helen Hunt) e seus amigos "
				+ "imaginando que ele morrera no acidente, Chuck precisa lutar para sobreviver, "
				+ "tanto fisicamente quanto emocionalmente, a fim de que um dia consiga retornar "
				+ "civilização.");
		filme.setDiretor("Robert Zemeckis");

		genero = new Genero();
		genero.setId(12);
		genero.setNome("Aventura");
		filme.setGenero(genero);

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			filme.setDataLancamento(formatter.parse("26/01/2001"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			filme.setDataLancamento(null);
		}

		filme.setPopularidade(100.25);
		filme.setPosterPath("img/naufrago.jpg");

		int id = service.inserirFilme(filme);
		System.out.println("Id gerado: "+id);
		return id;
	}

}
