package br.com.almaviva.academia.view;

import java.util.Map;
import java.util.Scanner;

import br.com.almaviva.academia.controller.AcademiaController;
import br.com.almaviva.academia.model.Academia;
import br.com.almaviva.academia.utils.ViewUtils;

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
                    } else if (comando.startsWith("SELECT * FROM academias WHERE ID=")) {
                        buscarAcademiaPorId(controller, comando);
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
                        removerAcademia(controller, sc);
                    } else {
                        System.out.println("Comando DELETE inválido. Tente novamente.");
                    }
                    break;
                case "HELP":
                case "/H":
                    ViewUtils.exibirAjuda();
                    break;
                case "CLEAR":
                case "/C":
                    ViewUtils.limparTerminal();
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

    private static void buscarAcademiaPorId(AcademiaController controller, String comando) {
        int id = Integer.parseInt(comando.split("WHERE ID=")[1].trim());
        Academia academia = controller.buscarAcademiaPorId(id);

        if (academia != null) {
            System.out.println(ViewUtils.formatarTabela(Map.of(id, academia)));
        } else {
            System.out.println("Nenhuma academia encontrada com o ID: " + id);
        }
    }

    private static void listarAcademias(AcademiaController controller) {
        var academias = controller.listarAcademias();

        if (academias.isEmpty()) {
            System.out.println("Nenhuma academia cadastrada.");
        } else {
            System.out.println(ViewUtils.formatarTabela(academias));
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
}
