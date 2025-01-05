package br.com.almaviva.academia.controller;

import br.com.almaviva.academia.model.Academia;
import br.com.almaviva.academia.services.AcademiaService;

import java.util.Map;

public class AcademiaController {

    private final AcademiaService service;

    public AcademiaController() {
        this.service = new AcademiaService();
    }

    public Map<Integer, Academia> listarAcademias() {
        return service.listarAcademias();
    }

    public Academia buscarAcademiaPorId(int id) {
        return service.buscarAcademiaPorId(id);
    }

    public void criarAcademia(Academia academia) {
        service.criarAcademia(academia);
    }

    public void atualizarAcademia(Academia academia) {
        service.atualizarAcademia(academia);
    }

    public void removerAcademia(int id) {
        service.removerAcademia(id);
    }
}
