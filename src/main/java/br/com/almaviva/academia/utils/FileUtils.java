package br.com.almaviva.academia.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.almaviva.academia.model.Academia;

public class FileUtils {
    private static final Logger logger = Logger.getLogger(FileUtils.class.getName());
    private static final String ARQUIVO_ACADEMIAS = "/academia-crud/src/main/resources/Academias.txt";

    public static void salvarAcademiasNoArquivo(Set<Academia> academias) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ACADEMIAS))) {
            writer.write("ID,Nome,Telefone,Sede,Filiais,PlanoMensal");
            writer.newLine();
            for (Academia academia : academias) {
                writer.write(String.format("%d,%s,%s,%s,%d,%.2f",
                        academia.getId(),
                        academia.getNome(),
                        academia.getTelefone(),
                        academia.getSedePrincipal(),
                        academia.getQuantidadeDeFiliais(),
                        academia.getPlanoMensal()));
                writer.newLine();
            }
            logger.info("Academias salvas no arquivo com sucesso. Total de academias: " + academias.size());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao escrever no arquivo: " + ARQUIVO_ACADEMIAS, e);
        }
    }

    public static Academia converteLinhaParaAcademia(String linha) {
        try {
            String[] partes = linha.split(",");

            int id = Integer.parseInt(partes[0]);
            String nome = partes[1];
            String telefone = partes[2];
            String sede = partes[3];
            int filiais = Integer.parseInt(partes[4]);
            double planoMensal = Double.parseDouble(partes[5]);

            return new Academia(id, nome, telefone, sede, filiais, planoMensal);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Erro ao processar linha: " + linha, e);
            return null;
        }
    }

    public static void setarIdAcademia(Academia academia, Set<Academia> academias) {
        int maiorId = 0;
        for (Academia acad : academias) {
            if (acad.getId() > maiorId) {
                maiorId = acad.getId();
            }
        }
        academia.setId(maiorId + 1);
        logger.info("Novo ID setado para academia: " + academia.getId());
    }
}
