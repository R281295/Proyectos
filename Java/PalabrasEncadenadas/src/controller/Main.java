package controller;

import model.ModeloJuego;

public class Main {
	
	public static void main(String[] args) {
		ModeloJuego juego = new ModeloJuego();
		juego.obtenerPalabraAlAzar();
		
		while(!juego.getLaMaquinaHaPerdido()) {
			juego.pedirPalabra();
			juego.comprobarPalabra();
		}
		
		
	}

}
