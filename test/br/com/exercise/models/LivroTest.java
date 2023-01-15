package br.com.exercise.models;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class LivroTest {

	@Test
	public void naoDeveAceitarLivroSemNome() {
		assertThrows(IllegalArgumentException.class, () -> new Livro(null, "Test", BigDecimal.valueOf(50.0)));
		assertThrows(IllegalArgumentException.class, () -> new Livro("", "Test", BigDecimal.valueOf(50.0)));
	}
	
	@Test
	public void naoDeveAceitarLivroSemAutor() {
		assertThrows(IllegalArgumentException.class, () -> new Livro("Lorem Ipsum", null, BigDecimal.valueOf(50.0)));
		assertThrows(IllegalArgumentException.class, () -> new Livro("Lorem Ipsum", "", BigDecimal.valueOf(50.0)));
	}
	
	@Test
	public void naoDeveAceitarPrecoNulo() {
		assertThrows(IllegalArgumentException.class, () -> new Livro("Lorem Ipsum", "Test", null));
	}
	
	@Test
	public void naoDeveAceitarLivroComPrecoNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Livro("Lorem Ipsum", "Test", BigDecimal.valueOf(-1.0)));
		assertThrows(IllegalArgumentException.class, () -> new Livro("Lorem Ipsum", "Test", BigDecimal.valueOf(-20.0)));
	}
	
	@Test
	public void naoDeveAceitarPromocaoNulo() {
		Livro livro = new Livro("Lorem Ipsum", "Test", BigDecimal.valueOf(75.0));
		assertThrows(IllegalArgumentException.class, () -> livro.setPromocao(null));
		
	}
	
	@Test
	public void deveCalcularValorPromocionalCorreto() {
		var livro = new Livro("Lorem Ipsum", "Test", BigDecimal.valueOf(75.0));
		var promocaoA = new Promocao("A", LocalDate.parse("2023-01-10"), LocalDate.parse("2023-01-15"), 5);
		var promocaoB = new Promocao("B", LocalDate.parse("2023-01-10"), LocalDate.parse("2023-01-20"), 10);
		var promocaoC = new Promocao("C", LocalDate.parse("2023-01-11"), LocalDate.parse("2023-01-30"), 15);
		
		BigDecimal valorPromocional = BigDecimal.ZERO;
		
		// test para a porcentagem igual 5
		livro.setPromocao(promocaoA);
		valorPromocional = livro.calcularPromocao();
		assertEquals(71.25, valorPromocional.doubleValue(), 0.0001);
		
		// test para a porcentagem igual 10
		livro.setPromocao(promocaoB);
		valorPromocional = livro.calcularPromocao();
		assertEquals(67.50, valorPromocional.doubleValue(), 0.0001);
		
		// test para a porcentagem igual 15
		livro.setPromocao(promocaoC);
		valorPromocional = livro.calcularPromocao();
		assertEquals(63.75, valorPromocional.doubleValue(), 0.0001);
	}
	
}
