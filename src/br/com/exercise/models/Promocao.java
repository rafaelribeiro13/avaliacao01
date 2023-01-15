package br.com.exercise.models;

import java.time.LocalDate;

public class Promocao {

	private String tipo;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int porcentagem;
	
	public Promocao(String tipo, LocalDate dataInicio, LocalDate dataFim, int porcentagem) {
		this.setTipo(tipo);
		this.setDataInicio(dataInicio);
		this.setDataFim(dataFim);
		this.setPorcentagem(porcentagem);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (tipo == null || tipo.isEmpty()) {
			throw new IllegalArgumentException("Tipo nao especificado para a promocao");
		}
		
		this.tipo = tipo.trim();
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		if (dataInicio == null) {
			throw new IllegalArgumentException("Data de inicio nao especificado para a promocao");
		}
		
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		if (dataFim == null) {
			throw new IllegalArgumentException("Data final nao especificado para a promocao");
		}
		
		if (dataFim.isBefore(dataInicio)) {
			throw new IllegalArgumentException("Data final para a promocao antes da data inicial");
		}
		
		this.dataFim = dataFim;
	}

	public int getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(int porcentagem) {
		if (porcentagem < 0) {
			throw new IllegalArgumentException("Porcentagem negativa para a promocao");
		}
		
		this.porcentagem = porcentagem;
	}

	@Override
	public String toString() {
		return "Promocao [tipo=" + tipo + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", porcentagem="
				+ porcentagem + "]";
	}
	
}
