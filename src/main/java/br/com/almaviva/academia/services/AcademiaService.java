package br.com.almaviva.academia.services;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.almaviva.academia.model.Academia;
import br.com.almaviva.academia.repositories.AcademiaRepository;
import br.com.almaviva.academia.validation.Validacoes;

public class AcademiaService {
    private static final Logger logger = Logger.getLogger(AcademiaService.class.getName());
    private final AcademiaRepository repository = new AcademiaRepository();

    public void criarAcademia(Academia academia) {
        try {
            Validacoes.validarAcademia(academia);

            repository.salvar(academia);
            logger.info("Academia criada com sucesso: " + academia.getNome());
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Erro de validação ao criar academia: " + e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro inesperado ao criar academia: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar a academia.", e);
        }
    }

    public Set<Academia> listarAcademias() {
        try {
            Set<Academia> academias = repository.listarAcademiasDoArquivo();
            logger.info("Lista de academias recuperada com sucesso.");
            return academias;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar academias.", e);
        }
    }

    public void atualizarAcademia(Academia academia) {
        try {
            Validacoes.validarAcademia(academia);

            repository.atualizar(academia);
            logger.info("Academia atualizada com sucesso: " + academia.getNome());
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Erro de validação ao atualizar academia: " + e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro inesperado ao atualizar academia: " + e.getMessage());
        }
    }

    public void removerAcademia(int id) {
        try {
            repository.removerPorId(id);
            logger.info("Academia removida com sucesso: ID " + id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao remover academia: " + e.getMessage());
        }
    }
}
