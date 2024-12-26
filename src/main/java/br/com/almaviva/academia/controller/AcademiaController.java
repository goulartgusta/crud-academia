package br.com.almaviva.academia.controller;

import java.util.Set;

import br.com.almaviva.academia.model.Academia;
import br.com.almaviva.academia.services.AcademiaService;

public class AcademiaController {
    private final AcademiaService service = new AcademiaService();

    public void criarAcademia(Academia academia) {
            service.criarAcademia(academia);
    }

    public Set<Academia> listarAcademias() {
            return service.listarAcademias();
    }

    public void atualizarAcademia(Academia academia) {
            service.atualizarAcademia(academia);
    }

    public void removerAcademia(int id) {
            service.removerAcademia(id);
    }
}
