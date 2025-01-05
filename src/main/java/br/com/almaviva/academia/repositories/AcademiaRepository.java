package br.com.almaviva.academia.repositories;

import br.com.almaviva.academia.model.Academia;
import br.com.almaviva.academia.utils.FileUtils;

import java.util.Map;
import java.util.HashMap;

public class AcademiaRepository {

    private final Map<Integer, Academia> academias;

    public AcademiaRepository() {
        this.academias = FileUtils.carregarAcademias();
    }

    public Map<Integer, Academia> listar() {
        return new HashMap<>(academias);
    }

    public Academia buscarPorId(int id) {
        if (!academias.containsKey(id)) {
            throw new IllegalArgumentException("Academia com ID " + id + " não encontrada.");
        }
        return academias.get(id);
    }

    public void adicionar(Academia academia) {
        int novoId = 0;
        for (int id : academias.keySet()) {
            if (id > novoId) {
                novoId = id;
            }
        }
        novoId++;
        academia.setId(novoId);
        academias.put(novoId, academia);
        salvar();
    }


    public void atualizar(Academia academiaAtualizada) {
        academias.put(academiaAtualizada.getId(), academiaAtualizada);
        salvar();
    }

    public void remover(int id) {
        academias.remove(id);
        salvar();
    }

    private void salvar() {
        FileUtils.salvarAcademias(academias);
    }
}
