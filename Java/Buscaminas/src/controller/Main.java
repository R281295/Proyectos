package controller;

import java.util.ArrayList;

import view.VistaJuego;

public class Main {

	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		VistaJuego vistaJuego = new VistaJuego();
		ControllerJuego controller = new ControllerJuego(vistaJuego);
		vistaJuego.conectarControlador(controller);
		
		
		ArrayList<String> listaCasillas = controller.generarNumerosAleatorios();
		controller.generarMinas(listaCasillas);
		
		
		controller.addIndicesCasillas(listaCasillas);
		
		controller.generarNumeros(listaCasillas);
	}

}
