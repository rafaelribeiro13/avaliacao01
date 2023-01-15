package br.com.exercise.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Catalogo {

	private Set<Livro> livros = new HashSet<>();
	
	public void adicionar(Livro livro) {
		this.livros.add(livro);
	}
	
	public void exibirLivros() {
		livros.forEach(livro -> System.out.println(livro + System.lineSeparator()));
	}
	
	public Set<Livro> getLivros() {
		return Collections.unmodifiableSet(livros);
	}
	
}
