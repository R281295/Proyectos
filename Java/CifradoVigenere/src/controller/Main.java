package controller;

import bin.ConstValues;

public class Main {
	
	public static void main(String[] args) {
		String texto = "Texto de prueba";
		String key = "ba";
		String encriptado = "";
		
		
		for(int i=0, j=0 ; i<texto.length() ; i++, j++) {
			if(j == key.length()) {
				j = 0;
			}
			
			int suma = ConstValues.diccionario.indexOf(texto.charAt(i))+ConstValues.diccionario.indexOf(key.charAt(j));
			
			if(suma > ConstValues.diccionario.length()-1) {
				suma = suma - ConstValues.diccionario.length();
			}
			
			encriptado += ConstValues.diccionario.charAt(suma);
		}
		
		System.out.println(encriptado);
		
		
	}

}
