package br.com.almaviva.academia;

import br.com.almaviva.academia.controller.AcademiaController;
import br.com.almaviva.academia.view.IntroducaoView;
import br.com.almaviva.academia.view.TerminalView;

public class Main {
    public static void main(String[] args) {
    	AcademiaController controller = new AcademiaController();
        IntroducaoView.exibirIntroducao();
        TerminalView.iniciarTerminal(controller);
    }
}