package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Diccionario {
	
	private static ArrayList<String> diccionario = new ArrayList<>();
	
	public Diccionario() {
		char letra = 'A';
		while(letra != 'Z'+1) {
			try {
				File file = new File("diccionario/palabras_"+letra);
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String linea = "";
				while((linea = br.readLine()) != null) {
					diccionario.add(linea);
				}
			}catch(Exception e) {
				System.out.println("Error al leer los diccionarios: "+e.getMessage());
			}
			letra++;
		}
		System.out.println("Tamaño del diccionario: "+diccionario.size());
		
	}
	
	public ArrayList<String> getDiccionario() {
		return diccionario;
	}

}
