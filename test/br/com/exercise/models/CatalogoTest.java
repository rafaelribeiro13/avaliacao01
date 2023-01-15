package br.com.exercise.models;

import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class CatalogoTest {

	@Test
	public void naoDevePermitirAlterarAColecao() {
		var catalogo = new Catalogo();
		var livro1 = new Livro("Lorem Ipsum", "Test", BigDecimal.valueOf(75.0));
		var livro2 = new Livro("Lorem Ipsum 2", "Test", BigDecimal.valueOf(70.0));
		var livro3 = new Livro("Lorem Ipsum 3", "Test", BigDecimal.valueOf(68.0));
		
		catalogo.adicionar(livro1);
		catalogo.adicionar(livro2);
		
		assertThrows(UnsupportedOperationException.class, () -> catalogo.getLivros().add(livro3));
		assertThrows(UnsupportedOperationException.class, () -> catalogo.getLivros().remove(livro2));
	}
	
}
