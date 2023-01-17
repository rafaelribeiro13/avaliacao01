package br.com.exercise.models;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVWriter;

public class Catalogo {

	private Set<Livro> livros = new HashSet<>();
	
	public void adicionar(Livro livro) {
		this.livros.add(livro);
	}
	
	public void exibirLivros() {
		livros.forEach(livro -> System.out.println(livro + System.lineSeparator()));
	}
	
	public void gerarRelatorio() throws IOException {
		String[] cabecalho = {"nome_livro", "autor", "preco_original"
				, "preco_promocional", "porcentagem", "data_inicio", "data_fim"};

		List<String[]> linhas = new ArrayList<>();

		for (Livro livro : livros) {
			String nome = livro.getNome();
			String autor = livro.getAutor();
			String precoOriginal = livro.getPreco().toString();
			String precoPromocional = livro.calcularPromocao().toString();
			String porcentagem = String.valueOf(livro.getPromocao().getPorcentagem());
			String dataInicio = livro.getPromocao().getDataInicio().toString();
			String dataFim = livro.getPromocao().getDataFim().toString();
			
			linhas.add(new String[]{nome, autor, precoOriginal, precoPromocional
					, porcentagem, dataInicio, dataFim});
		}
		
		String dataAtual = LocalDateTime.now().toString().replaceAll("[^a-z A-Z 0-9]", "");
        Writer writer = Files.newBufferedWriter(Paths.get("relatoriofinal_" + dataAtual + ".csv"), StandardCharsets.UTF_8);
      
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
			csvWriter.writeNext(cabecalho);
			csvWriter.writeAll(linhas);
			
			csvWriter.flush();
			System.out.println("*** Arquivo CSV do catalogo exportado com sucesso "
					+ "na raiz do projeto! ***");
		}

	}
	
	public Set<Livro> getLivros() {
		return Collections.unmodifiableSet(livros);
	}
	
}
