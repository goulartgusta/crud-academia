package br.com.almaviva.academia.validation;

import java.util.List;

import br.com.almaviva.academia.exceptions.UserInputException;
import br.com.almaviva.academia.model.Academia;

public class Validacoes {
	private static final List<String> CIDADES_VALIDAS = List.of("São Paulo", "Rio de Janeiro", "Belo Horizonte",
			"Curitiba", "Porto Alegre", "Brasília", "Salvador", "Fortaleza", "Recife", "Florianópolis");

	public static void validarAcademiaParaCriacao(Academia academia) {
		try {
			validarNome(academia.getNome());
			validarTelefone(academia.getTelefone());
			validarSedePrincipal(academia.getSedePrincipal());
			validarQuantidadeDeFiliais(academia.getQuantidadeDeFiliais());
			validarPlanoMensal(academia.getPlanoMensal());
		} catch (IllegalArgumentException e) {
			throw new UserInputException("Erro inesperado durante a validação.", e);
		}
	}

	public static void validarAcademiaParaAtualizacao(Academia academia) {
		try {
			if (academia.getNome() != null && !academia.getNome().isBlank()) {
				validarNome(academia.getNome());
			}
			if (academia.getTelefone() != null && !academia.getTelefone().isBlank()) {
				validarTelefone(academia.getTelefone());
			}
			if (academia.getSedePrincipal() != null && !academia.getSedePrincipal().isBlank()) {
				validarSedePrincipal(academia.getSedePrincipal());
			}
			if (academia.getQuantidadeDeFiliais() >= 0) {
				validarQuantidadeDeFiliais(academia.getQuantidadeDeFiliais());
			}
			if (academia.getPlanoMensal() > 0) {
				validarPlanoMensal(academia.getPlanoMensal());
			}
		} catch (IllegalArgumentException e) {
			throw new UserInputException("Erro inesperado durante a validação.", e);
		}
	}

	private static void validarNome(String nome) {
		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("O nome da academia é obrigatório.");
		}
	}

	private static void validarTelefone(String telefone) {
		if (telefone == null || !telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) {
			throw new IllegalArgumentException("O telefone deve estar no formato (XX)XXXXX-XXXX.");
		}
	}

	private static void validarSedePrincipal(String sedePrincipal) {
		if (sedePrincipal == null || sedePrincipal.isBlank()) {
			throw new IllegalArgumentException("A sede principal é obrigatória.");
		} else if (!CIDADES_VALIDAS.contains(sedePrincipal)) {
			throw new IllegalArgumentException(
					"A sede principal deve ser uma das cidades válidas: " + String.join(", ", CIDADES_VALIDAS));
		}
	}

	private static void validarQuantidadeDeFiliais(int quantidadeDeFiliais) {
		if (quantidadeDeFiliais < 0) {
			throw new IllegalArgumentException("A quantidade de filiais não pode ser negativa.");
		}
	}

	private static void validarPlanoMensal(double planoMensal) {
		if (planoMensal <= 0 || planoMensal > 1000) {
			throw new IllegalArgumentException("O plano mensal deve ser maior que 0 e menor ou igual a 1000.");
		}
	}
}
