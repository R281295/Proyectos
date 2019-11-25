package model;

import bin.ConstValues;

public class Criptografia {

    public String encripta(String message, String key) {
        String encriptado = "";
        for(int i=0, j=0 ; i<message.length() ; i++, j++) {
			if(j == key.length()) {
				j = 0;
			}
			
			int suma = ConstValues.DICCIONARIO.indexOf(message.charAt(i))+ConstValues.DICCIONARIO.indexOf(key.charAt(j));
			
			if(suma > ConstValues.DICCIONARIO.length()-1) {
				suma = suma - ConstValues.DICCIONARIO.length();
			}
			
			encriptado += ConstValues.DICCIONARIO.charAt(suma);
        }
        return encriptado;
    }

    public String desencripta(String message, String key) {
        String desencriptado = "";
        for(int i=0, j=0 ; i<message.length() ; i++, j++) {
			if(j == key.length()) {
				j = 0;
			}
			
			int suma = ConstValues.DICCIONARIO.indexOf(message.charAt(i))-ConstValues.DICCIONARIO.indexOf(key.charAt(j));
			
			if(suma < 0) {
				suma = suma + ConstValues.DICCIONARIO.length();
			}
			
			desencriptado += ConstValues.DICCIONARIO.charAt(suma);
        }
        return desencriptado;
    }

}
