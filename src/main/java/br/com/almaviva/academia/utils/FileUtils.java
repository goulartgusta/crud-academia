package br.com.almaviva.academia.utils;

import br.com.almaviva.academia.exceptions.FileOperationException;
import br.com.almaviva.academia.exceptions.UserInputException;
import br.com.almaviva.academia.model.Academia;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

	private static final String ARQUIVO_ACADEMIAS = "src/main/resources/Academias.txt";

	public static Map<Integer, Academia> carregarAcademias() {
		Map<Integer, Academia> academias = new HashMap<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ACADEMIAS))) {
			String linha = reader.readLine();
			while ((linha = reader.readLine()) != null) {
				if (linha.trim().startsWith("ID") || linha.isBlank()) {
					continue;
				}

				Academia academia = converteLinhaParaAcademia(linha);
				academias.put(academia.getId(), academia);
			}
		} catch (IOException e) {
			throw new FileOperationException("Erro ao carregar academias do arquivo.", e);
		} catch (UserInputException e) {
			throw new FileOperationException("Erro ao processar uma linha inválida no arquivo: " + e.getMessage(), e);
		}

		return academias;
	}

	public static void salvarAcademias(Map<Integer, Academia> academias) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ACADEMIAS))) {
			writer.write("ID;Nome;Telefone;Sede;Filiais;PlanoMensal"); // Cabeçalho
			writer.newLine();
			for (Academia academia : academias.values()) {
				writer.write(converteAcademiaParaLinha(academia));
				writer.newLine();
			}
		} catch (IOException e) {
			throw new FileOperationException("Erro ao salvar academias no arquivo.", e);
		}
	}

	private static Academia converteLinhaParaAcademia(String linha) {
		try {
			String[] partes = linha.split(";");
			if (partes.length != 6) {
				throw new UserInputException("Linha inválida no arquivo: " + linha);
			}

			int id = Integer.parseInt(partes[0].trim());
			String nome = partes[1].trim();
			String telefone = partes[2].trim();
			String sedePrincipal = partes[3].trim();
			int quantidadeDeFiliais = Integer.parseInt(partes[4].trim());
			double planoMensal = Double.parseDouble(partes[5].trim());

			return new Academia(id, nome, telefone, sedePrincipal, quantidadeDeFiliais, planoMensal);
		} catch (Exception e) {
			throw new FileOperationException("Erro inesperado ao converter linha para Academia: " + linha, e);
		}
	}

	private static String converteAcademiaParaLinha(Academia academia) {
		try {
			return String.format("%d;%s;%s;%s;%d;%.2f", academia.getId(), academia.getNome(), academia.getTelefone(),
					academia.getSedePrincipal(), academia.getQuantidadeDeFiliais(), academia.getPlanoMensal());
		} catch (Exception e) {
			throw new FileOperationException("Erro ao converter academia para linha: " + academia, e);
		}
	}
}
