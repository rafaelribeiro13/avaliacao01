package br.com.exercise.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Livro {
	
	private String nome;
	private String autor;
	private BigDecimal preco;
	private Promocao promocao;

	public Livro(String nome, String autor, BigDecimal preco) {
		this.setNome(nome);
		this.setAutor(autor);
		this.setPreco(preco);
	}

	public BigDecimal calcularPromocao() {
		double precoOriginal = preco.doubleValue();
		double desconto = precoOriginal * ( (double) promocao.getPorcentagem() / 100);
		BigDecimal precoPromocional = preco.subtract(BigDecimal.valueOf(desconto));
		return precoPromocional.setScale(2, RoundingMode.HALF_UP);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome nao informado para o livro");
		}
		
		this.nome = nome.trim();
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		if (autor == null || autor.isEmpty()) {
			throw new IllegalArgumentException("Autor nao informado para o livro");
		}
		
		this.autor = autor.trim();
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		if (preco == null) {
			throw new IllegalArgumentException("Preco nao informado para o livro");
		}
		
		if (preco.compareTo(BigDecimal.ZERO) == -1) {
			throw new IllegalArgumentException("Preco negativo para o livro");
		}
		
		this.preco = preco;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		if (promocao == null) {
			throw new IllegalArgumentException("Promocao nula para o livro");
		}
		
		this.promocao = promocao;
	}	

	@Override
	public int hashCode() {
		return Objects.hash(autor, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Nome: " + nome
			+ "\nAutor: " + autor
			+ "\nPreco Original: " + preco.setScale(2, RoundingMode.HALF_UP)
			+ "\nPreco promocional: " + calcularPromocao()
			+ "\nPorcentagem aplicada: " + promocao.getPorcentagem()
			+ "\nInicio promocao: " + promocao.getDataInicio()
			+ "\nFim promocao: " + promocao.getDataFim();
	}
	
}