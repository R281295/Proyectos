package util;

import javax.swing.JButton;


public class UtilTablero {
	
	JButton[] btnCasillas;
	
	public UtilTablero(JButton[] btnCasillas) {
		this.btnCasillas = btnCasillas;
	}
	

	public int getValorCasilla(int indice) {
		return Integer.parseInt(btnCasillas[indice].getText().split(";")[1]);
	}
	
	
	public int getCasillaInferior(int i) {
		if(excluirUltimaFila(i)) {
			return i+1;
		} else {
			return Constantes.casillasTotales;
		}
	}
	
	public int getCasillaSuperior(int i) {
		if(excluirPrimeraFila(i)) {
			return i-1;
		} else {
			return Constantes.casillasTotales;
		}
	}
	
	public int getCasillaIzquierda(int i) {
		if(excluirPrimeraColumna(i)) {
			return i-Constantes.altoTabla;
		} else {
			return Constantes.casillasTotales;
		}
	}
	
	public int getCasillaDerecha(int i) {
		if(excluirUltimaColumna(i)) {
			return i+Constantes.altoTabla;
		} else {
			return Constantes.casillasTotales;
		}
	}
	
	public int getCasillaSuperiorIzquierda(int i) {
		if(excluirPrimeraColumna(i)
		&& excluirPrimeraFila(i)) {
			return i-Constantes.altoTabla-1;
		} else {
			return Constantes.casillasTotales;
		}
	}
	
	public int getCasillaSuperiorDerecha(int i) {
		if(excluirUltimaColumna(i)
		&& excluirPrimeraFila(i)) {
			return i+Constantes.altoTabla-1;
		} else {
			return Constantes.casillasTotales;
		}
	}
	
	public int getCasillaInferiorIzquierda(int i) {
		if(excluirPrimeraColumna(i)
		&& excluirUltimaFila(i)) {
			return i-Constantes.altoTabla+1;
		} else {
			return Constantes.casillasTotales;
		}
	}
	
	public int getCasillaInferiorDerecha(int i) {
		if(excluirUltimaColumna(i)
		&& excluirUltimaFila(i)) {
			return i+Constantes.altoTabla+1;
		} else {
			return Constantes.casillasTotales;
		}
	}
	
	
	public boolean excluirPrimeraFila(int i) {
		return !((i) % Constantes.altoTabla == 0);
	}
	
	public boolean excluirUltimaFila(int i) {
		return !((i+1) % Constantes.altoTabla == 0);
	}
	
	public boolean excluirPrimeraColumna(int i) {
		return (i > Constantes.altoTabla - 1);
	}
	
	public boolean excluirUltimaColumna(int i) {
		return (i < Constantes.casillasTotales - Constantes.altoTabla);
	}

}
