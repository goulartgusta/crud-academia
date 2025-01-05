package br.com.almaviva.academia.services;

import java.util.Map;
import java.util.logging.Logger;

import br.com.almaviva.academia.exceptions.AcademiaNotFoundException;
import br.com.almaviva.academia.exceptions.UserInputException;
import br.com.almaviva.academia.model.Academia;
import br.com.almaviva.academia.repositories.AcademiaRepository;
import br.com.almaviva.academia.validation.Validacoes;

public class AcademiaService {
    private final AcademiaRepository repository;
    private static final Logger logger = Logger.getLogger(AcademiaService.class.getName());

    public AcademiaService() {
        this.repository = new AcademiaRepository();
    }

    public Map<Integer, Academia> listarAcademias() {
        logger.info("Listagem de todas as academias solicitada.");
        return repository.listar();
    }

    public Academia buscarAcademiaPorId(int id) {
        try {
            return repository.buscarPorId(id);
        } catch (AcademiaNotFoundException e) {
            logger.warning("Academia não encontrada: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.severe("Erro inesperado ao buscar academia: " + e.getMessage());
            throw new UserInputException("Erro inesperado ao buscar academia.", e);
        }
    }

    public void criarAcademia(Academia academia) {
        try {
            Validacoes.validarAcademiaParaCriacao(academia);
            repository.adicionar(academia);
            logger.info("Academia criada com sucesso: " + academia.getNome());
        } catch (Exception e) {
            logger.severe("Erro inesperado ao criar academia: " + e.getMessage());
            throw new UserInputException("Erro inesperado ao criar academia.", e);
        }
    }

    public void atualizarAcademia(Academia academia) {
        Validacoes.validarAcademiaParaAtualizacao(academia);
        try {
            repository.atualizar(academia);
            logger.info("Academia atualizada com sucesso: " + academia.getId());

        } catch (Exception e) {
            logger.severe("Erro inesperado ao atualizar academia: " + e.getMessage());
            throw new UserInputException("Erro ao atualizar academia:", e);
        }
    }

    public void removerAcademia(int id) {
        try {
            repository.remover(id);
            logger.info("Academia removida com sucesso: ID " + id);
        } catch (AcademiaNotFoundException e) {
            logger.warning("Erro ao remover academia: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.severe("Erro inesperado ao remover academia: " + e.getMessage());
            throw new UserInputException("Erro inesperado ao remover academia.", e);
        }
    }
}
