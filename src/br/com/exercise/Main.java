package br.com.exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import br.com.exercise.models.Catalogo;
import br.com.exercise.models.Livro;
import br.com.exercise.models.Promocao;
import br.com.exercise.utils.ResgatadorPromocao;

public class Main {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Catalogo catalogo = new Catalogo();
		
		try (Scanner scanner = new Scanner(new File("livros.csv"), StandardCharsets.UTF_8)) {
			
			List<Promocao> list = ResgatadorPromocao.resgatar();
			
			scanner.nextLine();
			while (scanner.hasNextLine()) {
					
					String linha = scanner.nextLine();
					Livro livro = instanciarLivro(linha, list);
					catalogo.adicionar(livro);
					
			}
			
			catalogo.exibirLivros();
			catalogo.gerarRelatorio();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	private static Livro instanciarLivro(String linha, List<Promocao> list) throws FileNotFoundException {
		Scanner scanner = new Scanner(linha);
		scanner.useLocale(Locale.US);
		scanner.useDelimiter(";");
		
		String nome = scanner.next();
		String autor = scanner.next();
		double preco = Double.parseDouble(scanner.next());
		String tipoPromocao = scanner.next().trim();
		
		scanner.close();

		// recuperando promocao
		Promocao promo = list.stream()
				.filter(item -> item.getTipo().equals(tipoPromocao))
				.toList().get(0);
		
		Livro livro = new Livro(nome, autor, BigDecimal.valueOf(preco));
		livro.setPromocao(promo);
		return livro;
	}

}
