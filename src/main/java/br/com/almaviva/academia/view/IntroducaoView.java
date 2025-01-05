package br.com.almaviva.academia.view;

import java.util.Scanner;

public class IntroducaoView {
	public static void exibirIntroducao() {
		System.out.println("Digite seu usuário para entrar:");
		Scanner sc = new Scanner(System.in);
		String usuario = sc.nextLine();
		System.out.println("\nBem-vindo ao almaviva-pass sql, " + usuario);
		System.out.println("Aqui gerenciamos os dados de um sistema GymPass para a AlmavivA. "
				+ "Utilizamos comandos que remetem SQL para interagir com nosso arquivo.\n");

		System.out.println("Temos como entidades Academia, Plano e GymPass. "
				+ "A Academia é registrada, processada pela entidade Plano para regra de negócio e transformada em nosso GymPass.\n"
				+ "Você poderá interagir com a Entidade Academia e verá as tables Plano e GymPass.\n");

		System.out.println("Digite 'help' ou /h para conhecer os comandos. Digite /c para limpar o terminal.\n");

	}
}
