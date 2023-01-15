package br.com.exercise.models;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PromocaoTest {

	@Test
	public void naoDeveAceitarPromocaoSemTipo() {
		assertThrows(IllegalArgumentException.class, () -> new Promocao(null, LocalDate.now(), LocalDate.now(), 5));
		assertThrows(IllegalArgumentException.class, () -> new Promocao("", LocalDate.now(), LocalDate.now(), 5));
	}
	
	@Test
	public void naoDeveAceitarPromocaoSemDataInicio() {
		assertThrows(IllegalArgumentException.class, () -> new Promocao("A", null, LocalDate.now(), 5));
	}
	
	@Test
	public void naoDeveAceitarPromocaoSemDataFim() {
		assertThrows(IllegalArgumentException.class, () -> new Promocao("A", LocalDate.now(), null, 5));
	}
	
	@Test
	public void naoDeveAceitarDataFimAntesDaDataInicio() {
		assertThrows(IllegalArgumentException.class, () -> new Promocao("A"
				, LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 9), 5));
	}
	
	@Test
	public void naoDeveAceitarPorcentagemNegativa() {
		assertThrows(IllegalArgumentException.class, () -> new Promocao("A"
				, LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 11), -1));

		assertThrows(IllegalArgumentException.class, () -> new Promocao("A"
				, LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 11), -15));
	}
	
}
