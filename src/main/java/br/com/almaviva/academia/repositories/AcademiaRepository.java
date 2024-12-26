package br.com.almaviva.academia.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.almaviva.academia.model.Academia;
import br.com.almaviva.academia.utils.FileUtils;

public class AcademiaRepository {
	private static final String ARQUIVO_ACADEMIAS = "src/main/resources/Academias.txt";

	public Set<Academia> listarAcademiasDoArquivo() {
		Set<Academia> academias = new LinkedHashSet<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ACADEMIAS))) {
			String linha;
			reader.readLine();
			while ((linha = reader.readLine()) != null) {
				Academia academia = FileUtils.converteLinhaParaAcademia(linha);
				if (academia != null)
					academias.add(academia);
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}
		return academias;
	}

	public void salvar(Academia academia) {
		Set<Academia> academias = listarAcademiasDoArquivo();
		FileUtils.setarIdAcademia(academia, academias);

		if (academias.add(academia))
			FileUtils.salvarAcademiasNoArquivo(academias);
		else
			throw new IllegalArgumentException("Academia já existente: " + academia.getNome());
	}

	public void atualizar(Academia academiaAtualizada) {
		Set<Academia> academias = listarAcademiasDoArquivo();
		academias.removeIf(academia -> academia.getId() == academiaAtualizada.getId());
		academias.add(academiaAtualizada);
		FileUtils.salvarAcademiasNoArquivo(academias);
	}

	public void removerPorId(int id) {
		Set<Academia> academias = listarAcademiasDoArquivo();
		if (academias.removeIf(academia -> academia.getId() == id))
			FileUtils.salvarAcademiasNoArquivo(academias);
		else
			throw new IllegalArgumentException("Academia com ID " + id + " não encontrada para remoção.");

	}



}
