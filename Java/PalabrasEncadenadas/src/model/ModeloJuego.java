package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import util.Diccionario;

public class ModeloJuego {
	
	Diccionario diccionario = new Diccionario();
	private int numeroAleatorio = (int)(Math.random()*diccionario.getDiccionario().size());
	String palabraGenerada = "";
	String palabraIntroducida = "";
	boolean laMaquinaHaPerdido = false;
	ArrayList<String> palabrasDichas = new ArrayList<>();
	
	public String obtenerPalabraAlAzar() {
		palabraGenerada = diccionario.getDiccionario().get(numeroAleatorio);
		System.out.println(palabraGenerada);
		return palabraGenerada;
	}
	
	public void pedirPalabra() {
		try {
			System.out.print("Tu turno: ");
			palabraIntroducida = new BufferedReader(new InputStreamReader(System.in)).readLine();
		}catch(Exception e) {
			
		}
	}
	
	public void comprobarPalabra() {
		String fin = palabraGenerada.substring(palabraGenerada.length()-2, palabraGenerada.length());
		String principio = palabraIntroducida.substring(0, 2);
		if(fin.equals(principio)) {
			if(comprobarSiExiste(palabraIntroducida)) {
				if(comprobarSiSeHaDicho(palabraIntroducida)) {
					System.out.println("Palabra ya dicha");
				} else {
					palabrasDichas.add(palabraIntroducida);
					System.out.println(buscaPalabra(palabraIntroducida.substring(palabraIntroducida.length()-2, palabraIntroducida.length())));
				}
			} else {
				System.out.println("Palabra no reconocida. No existe en mi diccionario.");
			}
		} else {
			System.out.println("La palabra debe empezar por las dos ultimas letras");
		}
	}
	
	public boolean comprobarSiExiste(String palabra) {
		return diccionario.getDiccionario().contains(palabra);
	}
	
	public String buscaPalabra(String prefijo) {
		ArrayList<String> encontradas = new ArrayList<String>();
		for(String palabra : diccionario.getDiccionario()) {
			if(palabra.length() != 0) {
				if(palabra.substring(0, prefijo.length()).equals(prefijo)) {
					if(!comprobarSiSeHaDicho(palabra)) {
						encontradas.add(palabra);
					}
				}
			}
		}
		if(encontradas.size() != 0) {
			int numeroAleatorio = (int)(Math.random()*encontradas.size());
			palabraGenerada = encontradas.get(numeroAleatorio);
			palabrasDichas.add(palabraGenerada);
			return palabraGenerada;
		} else {
			laMaquinaHaPerdido = true;
			return "No he encontrado ninguna palabra que empiece por <>. Has ganado";
		}
		
	}
	
	public boolean getLaMaquinaHaPerdido() {
		return laMaquinaHaPerdido;
	}
	
	public boolean comprobarSiSeHaDicho(String palabra) {
		if(palabrasDichas.contains(palabra)) {
			return true;
		} else {
			return false;
		}
	}


}
