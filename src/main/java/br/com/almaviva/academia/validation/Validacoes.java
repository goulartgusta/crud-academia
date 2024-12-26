package br.com.almaviva.academia.validation;

import br.com.almaviva.academia.model.Academia;

import java.util.List;
import java.util.logging.Logger;

public class Validacoes {
    private static final Logger logger = Logger.getLogger(Validacoes.class.getName());
    private static final List<String> CIDADES_VALIDAS = List.of(
            "São Paulo", "Rio de Janeiro", "Belo Horizonte", "Curitiba", "Porto Alegre",
            "Brasília", "Salvador", "Fortaleza", "Recife", "Florianópolis"
    );

    public static void validarAcademia(Academia academia) {
        StringBuilder erro = new StringBuilder();

        validarNome(academia.getNome(), erro);
        validarTelefone(academia.getTelefone(), erro);
        validarSedePrincipal(academia.getSedePrincipal(), erro);
        validarQuantidadeDeFiliais(academia.getQuantidadeDeFiliais(), erro);
        validarPlanoMensal(academia.getPlanoMensal(), erro);

        if (erro.length() > 0) {
            logger.warning("Erros de validação: \n" + erro);
            throw new IllegalArgumentException(erro.toString());
        }
    }

    private static void validarNome(String nome, StringBuilder erros) {
        if (nome == null || nome.isBlank()) {
            erros.append("O nome não pode ser vazio.\n");
        }
    }

    private static void validarTelefone(String telefone, StringBuilder erros) {
        if (telefone == null || telefone.isBlank() || !telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) {
            erros.append("O telefone deve ser válido e conter o formato (XX)XXXXX-XXXX.\n");
        }
    }

    private static void validarSedePrincipal(String sedePrincipal, StringBuilder erros) {
        if (sedePrincipal == null || sedePrincipal.isBlank()) {
            erros.append("A sede principal não pode ser vazia.\n");
        } else if (!CIDADES_VALIDAS.contains(sedePrincipal)) {
            erros.append("A sede principal deve estar entre as cidades principais para o ramo de academias: ")
                 .append(String.join(", ", CIDADES_VALIDAS))
                 .append(".\n");
        }
    }

    private static void validarQuantidadeDeFiliais(int quantidadeDeFiliais, StringBuilder erros) {
        if (quantidadeDeFiliais < 0) {
            erros.append("A quantidade de filiais deve ser positiva.\n");
        }
    }

    private static void validarPlanoMensal(double planoMensal, StringBuilder erros) {
        if (planoMensal <= 0 || planoMensal > 1000) {
            erros.append("O plano mensal deve ser maior que zero e não pode exceder 1000.\n");
        }
    }
}
