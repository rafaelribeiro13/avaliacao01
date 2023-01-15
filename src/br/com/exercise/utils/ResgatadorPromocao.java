package br.com.exercise.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import br.com.exercise.models.Promocao;

public class ResgatadorPromocao {

	public static List<Promocao> resgatar() throws IOException {
		List<Promocao> list = new ArrayList<>();

		try (Scanner sc = new Scanner(new File("detalhes_promocao.csv"), StandardCharsets.UTF_8)) {

			sc.nextLine();
			while (sc.hasNextLine()) {
				String linha = sc.nextLine();

				Scanner scanner = new Scanner(linha);
				scanner.useLocale(Locale.US);
				scanner.useDelimiter(";");

				String tipo = scanner.next();
				LocalDate dataInicio = LocalDate.parse(scanner.next());
				LocalDate dataFim = LocalDate.parse(scanner.next());
				int porcentagem = scanner.nextInt();

				list.add(new Promocao(tipo, dataInicio, dataFim, porcentagem));
				scanner.close();
			}

		}

		return Collections.unmodifiableList(list);
	}

}
