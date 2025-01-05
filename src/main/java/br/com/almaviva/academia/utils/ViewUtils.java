package br.com.almaviva.academia.utils;

import java.util.Map;

import br.com.almaviva.academia.model.Academia;

public class ViewUtils {
	public static void exibirAjuda() {
		System.out.println("Comandos disponíveis:");
		System.out.printf("%-40s %-50s\n", "SELECT * FROM academias;", "--> Lista todas as academias registradas.");
		System.out.printf("%-40s %-50s\n", "SELECT * FROM academias WHERE ID=<id>;",
				"--> Busca uma academia pelo ID.");
		System.out.printf("%-40s %-50s\n", "INSERT INTO academias;", "--> Insere uma nova academia.");
		System.out.printf("%-40s %-50s\n", "UPDATE academias;", "--> Atualiza uma academia existente.");
		System.out.printf("%-40s %-50s\n", "DELETE FROM academias WHERE ID=<id>;", "--> Remove uma academia pelo ID.");
		System.out.printf("%-40s %-50s\n", "EXIT;", "--> Encerra o programa.");
	}

	public static void limparTerminal() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			System.out.println("Não foi possível limpar o terminal.");
		}
	}
	
	public static String formatarTabela(Map<Integer, Academia> academias) {
	    StringBuilder tabela = new StringBuilder();
	    String linhaHorizontal = "+-----+----------------------+-----------------+----------------------+------------+------------+";
	    String cabecalho = String.format("| %-3s | %-20s | %-15s | %-20s | %-10s | %-10s |",
	            "ID", "Nome", "Telefone", "Sede", "Filiais", "PlanoMensal");

	    tabela.append(linhaHorizontal).append("\n");
	    tabela.append(cabecalho).append("\n");
	    tabela.append(linhaHorizontal).append("\n");

	    for (Academia academia : academias.values()) {
	        tabela.append(String.format("| %-3d | %-20s | %-15s | %-20s | %-10d | %-10.2f |\n",
	                academia.getId(),
	                academia.getNome(),
	                academia.getTelefone(),
	                academia.getSedePrincipal(),
	                academia.getQuantidadeDeFiliais(),
	                academia.getPlanoMensal()));
	    }

	    tabela.append(linhaHorizontal);
	    return tabela.toString();
	}

}
