
package br.com.almaviva.academia.view;

import br.com.almaviva.academia.controller.AcademiaController;
import br.com.almaviva.academia.model.Academia;

import java.util.Scanner;

public class TerminalView {

	public static void iniciarTerminal(AcademiaController controller) {
		Scanner sc = new Scanner(System.in);
		String comando;

		do {
			System.out.print("almaviva_pass> ");
			comando = sc.nextLine();

			switch (comando.split(" ")[0].toUpperCase()) {
			case "SELECT":
				if (comando.equalsIgnoreCase("SELECT * FROM academias")) {
					listarAcademias(controller);
				} else {
					System.out.println("Comando SELECT inválido. Tente novamente.");
				}
				break;
			case "INSERT":
				if (comando.equalsIgnoreCase("INSERT INTO academias")) {
					criarAcademia(controller, sc);
				} else {
					System.out.println("Comando INSERT inválido. Tente novamente.");
				}
				break;
			case "UPDATE":
				if (comando.startsWith("UPDATE academias")) {
					atualizarAcademia(controller, sc);
				} else {
					System.out.println("Comando UPDATE inválido. Tente novamente.");
				}
				break;
			case "DELETE":
				if (comando.startsWith("DELETE FROM academias WHERE ID =")) {
					removerAcademia(controller,sc);
				} else {
					System.out.println("Comando DELETE inválido. Tente novamente.");
				}
				break;
			case "HELP":
			case "/H":
				exibirAjuda();
				break;
			case "CLEAR":
			case "/C":
				limparTerminal();
				break;
			case "EXIT":
				System.out.println("Encerrando o sistema. Até mais!");
				break;
			default:
				System.out.println("Comando não reconhecido. Tente novamente.");
			}
		} while (!comando.equalsIgnoreCase("EXIT"));

		sc.close();
	}

	private static void exibirAjuda() {
        System.out.println("Comandos disponíveis:");
        System.out.printf("%-40s %-50s\n", "SELECT * FROM academias;", "--> Lista todas as academias registradas.");
        System.out.printf("%-40s %-50s\n", "INSERT INTO academias;", "--> Insere uma nova academia.");
        System.out.printf("%-40s %-50s\n", "UPDATE academias;", "--> Atualiza uma academia existente.");
        System.out.printf("%-40s %-50s\n", "DELETE FROM academias WHERE ID = <id>;", "--> Remove uma academia pelo ID.");
        System.out.printf("%-40s %-50s\n", "EXIT;", "--> Encerra o programa.");
	}

	private static void listarAcademias(AcademiaController controller) {
	    var academias = controller.listarAcademias();

	    if (academias.isEmpty()) {
	        System.out.println("Nenhuma academia cadastrada.");
	    } else {
	        String linhaHorizontal = "+-----+----------------------+-----------------+----------------------+------------+------------+";
	        System.out.println(linhaHorizontal);
	        System.out.printf("| %-3s | %-20s | %-15s | %-20s | %-10s | %-10s |\n",
	                "ID", "Nome", "Telefone", "Sede", "Filiais", "PlanoMensal");
	        System.out.println(linhaHorizontal);

	        for (Academia academia : academias) {
	            System.out.printf("| %-3d | %-20s | %-15s | %-20s | %-10d | %-10.2f |\n",
	                    academia.getId(),
	                    academia.getNome(),
	                    academia.getTelefone(),
	                    academia.getSedePrincipal(),
	                    academia.getQuantidadeDeFiliais(),
	                    academia.getPlanoMensal());
	        }

	        // Fechar a tabela
	        System.out.println(linhaHorizontal);
	    }
	}

	private static void criarAcademia(AcademiaController controller, Scanner sc) {
		System.out.println("Digite os dados da nova academia:");
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		System.out.print("Telefone (formato: (XX)XXXXX-XXXX): ");
		String telefone = sc.nextLine();
		System.out.print("Sede Principal: ");
		String sede = sc.nextLine();
		System.out.print("Quantidade de Filiais: ");
		int filiais = Integer.parseInt(sc.nextLine());
		System.out.print("Plano Mensal: ");
		double planoMensal = Double.parseDouble(sc.nextLine());

		controller.criarAcademia(new Academia(0, nome, telefone, sede, filiais, planoMensal));
	}

	private static void atualizarAcademia(AcademiaController controller, Scanner scanner) {
		System.out.print("Digite o ID da academia a ser atualizada: ");
		int id = Integer.parseInt(scanner.nextLine());

		System.out.println("Digite os novos dados (pressione ENTER para manter o valor atual):");
		System.out.print("Novo Nome: ");
		String nome = scanner.nextLine();
		System.out.print("Novo Telefone (formato: (XX)XXXXX-XXXX): ");
		String telefone = scanner.nextLine();
		System.out.print("Nova Sede Principal: ");
		String sede = scanner.nextLine();
		System.out.print("Nova Quantidade de Filiais: ");
		String filiaisStr = scanner.nextLine();
		System.out.print("Novo Plano Mensal: ");
		String planoMensalStr = scanner.nextLine();

		int filiais = filiaisStr.isBlank() ? -1 : Integer.parseInt(filiaisStr);
		double planoMensal = planoMensalStr.isBlank() ? -1 : Double.parseDouble(planoMensalStr);

		controller.atualizarAcademia(new Academia(id, nome, telefone, sede, filiais, planoMensal));
	}

	private static void removerAcademia(AcademiaController controller, Scanner sc) {
		System.out.print("Digite o ID da academia a ser removida: ");
		int id = Integer.parseInt(sc.nextLine());

		controller.removerAcademia(id);
	}

	private static void limparTerminal() {
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
}