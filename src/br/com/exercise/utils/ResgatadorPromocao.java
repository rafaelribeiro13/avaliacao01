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

		try (Scanner scanner = new Scanner(new File("detalhes_promocao.csv"), StandardCharsets.UTF_8)) {

			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();

				Scanner scannerDelimiter = new Scanner(linha);
				scannerDelimiter.useLocale(Locale.US);
				scannerDelimiter.useDelimiter(";");

				String tipo = scannerDelimiter.next();
				LocalDate dataInicio = LocalDate.parse(scannerDelimiter.next());
				LocalDate dataFim = LocalDate.parse(scannerDelimiter.next());
				int porcentagem = scannerDelimiter.nextInt();

				list.add(new Promocao(tipo, dataInicio, dataFim, porcentagem));
				scannerDelimiter.close();
			}

		}

		return Collections.unmodifiableList(list);
	}

}
