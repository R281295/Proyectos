package controller;

import bin.ConstValues;
import model.Criptografia;

public class Main {
	
	public static void main(String[] args) {
		String mensaje = "Probando encriptación de mensaje.";
		String mensajeOculto = "Ò|qìy7ósc?v6Z7c?/:Pkq:E7ela·G]Z7-$";

		Criptografia criptografia = new Criptografia();
		
		String mensajeEncriptado = criptografia.encripta(mensaje, ConstValues.KEY);
		String desencriptado = criptografia.desencripta(mensajeEncriptado, ConstValues.KEY);

		
		System.out.println("Mensaje original\t"+mensaje+"\nEncriptado\t\t"+mensajeEncriptado+"\n");
		System.out.println("Mensaje encriptado\t"+mensajeEncriptado+"\nDesencriptado\t\t"+desencriptado+"\n");
		
		/*	Descomentame para desvelar el mensaje oculto :-)
		String mensajeOcultoDesencriptado = criptografia.desencripta(mensajeOculto, ConstValues.KEY);
		System.out.println("Mensaje oculto\t\t"+mensajeOculto+"\nDesencriptado\t\t"+mensajeOcultoDesencriptado+"\n");
		*/
		
	}

}
