package controller;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import util.Adapter;
import util.Constantes;
import util.UtilJSON;
import util.UtilTablero;
import view.VistaEstadisticas;
import view.VistaCambiarImagenes;
import view.VistaJuego;
import view.VistaPersonalizado;

public class ControllerJuego extends Adapter implements ActionListener, ComponentListener {
	
	private VistaJuego vistaJuego;
	private VistaPersonalizado vistaPersonalizado;
	private VistaEstadisticas vistaEstadisticas;
	private UtilTablero utilTablero;
	private HashSet<Integer> casillaColindanteVacia;
	private UtilJSON utilJSON;
	private HashMap<Integer, String> imagenesCasillas;
	
	public ControllerJuego(VistaJuego vistaJuego) {
		this.vistaJuego = vistaJuego;
		utilTablero = new UtilTablero(vistaJuego.getBtnCasillas());
		utilJSON = new UtilJSON();
		utilJSON.incrementarJugadas();
		imagenesCasillas = new HashMap<>();
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		String action = evt.getActionCommand();
		switch(action) {
		case Constantes.MENU_NUEVO:
			nuevoJuego(0, 0, 0);
			break;
		case Constantes.MENU_FACIL:
			nuevoJuego(8, 8, 10);
			break;
		case Constantes.MENU_MEDIO:
			nuevoJuego(16, 16, 40);
			break;
		case Constantes.MENU_DIFICIL:
			nuevoJuego(30, 26, 99);
			break;
		case Constantes.MENU_PERSONALIZADO:
			vistaPersonalizado = new VistaPersonalizado();
			vistaPersonalizado.conectarControlador(this);
			break;
		case Constantes.MENU_ESTADISTICAS:
			utilJSON.incrementarSinAcabar();
			vistaEstadisticas = new VistaEstadisticas();
			vistaEstadisticas.conectarControlador(this);
			break;
		case Constantes.MENU_CAMBIAR_IMAGEN:
			VistaCambiarImagenes vistaCambiarImagenes = new VistaCambiarImagenes();
			ControllerCambiarImagenes cambiarImagenes = new ControllerCambiarImagenes(vistaJuego, vistaCambiarImagenes);
			vistaCambiarImagenes.conectarControlador(cambiarImagenes);
			break;
		case Constantes.MENU_SALIR:
			vistaJuego.dispose();
			break;
		case "PersonalizadoAceptar":
			try {
				nuevoJuego(vistaPersonalizado.getAncho(), vistaPersonalizado.getAlto(), vistaPersonalizado.getMinas());
				vistaPersonalizado.dispose();
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Los campos deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "EstadisticasAceptar":
			vistaEstadisticas.dispose();
			break;
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent evt) {
		String contenidoCasilla = evt.getComponent().toString();
		contenidoCasilla = contenidoCasilla.substring(contenidoCasilla.indexOf("text=")+5, contenidoCasilla.indexOf(",defaultCapable="));

		String[] contenidoCasillaSeparado = contenidoCasilla.split(";"); //Indice de btn ; Numero
		int indice = Integer.parseInt(contenidoCasillaSeparado[0]);
		int casilla = Integer.parseInt(contenidoCasillaSeparado[1]);
		
		if(SwingUtilities.isRightMouseButton(evt)) {
			ponerBandera(indice);
		} else {
			if(isMina(indice) && !isBandera(indice)) {
				desvelarTodasLasMinas();
				JOptionPane.showMessageDialog(null, "Has perdido", "¡¡Mina encontrada!!", JOptionPane.WARNING_MESSAGE);
				utilJSON.incrementarPerdidas();
				preguntarOtraPartida();
			}
		
			if(casilla != -1 && !isBandera(indice)) {
				setIcono(indice, new ImageIcon(Constantes.IMG_CASILLAS[casilla]));
				vistaJuego.getBtnCasillas()[indice].setFocusable(false);
				if(casilla == 0) {
					desvelarCasillasAlrededor(indice);
				}
				comprobarSiHaGanado();
			}
		}
	}
	
	@Override
	public void componentResized(ComponentEvent cevt) {
		for(int i=0 ; i<Constantes.casillasTotales ; i++) {
			if(vistaJuego.getBtnCasillas()[i].getIcon() != null) {
				ImageIcon icon = (ImageIcon) vistaJuego.getBtnCasillas()[i].getIcon();
				icon = new ImageIcon(imagenesCasillas.get(i));
				setIcono(i, icon);
			}
		}
	}
	
	public ArrayList<String> generarNumerosAleatorios() {
		ArrayList<String> listaCasillas = new ArrayList<>();
		for(int i=0 ; i<Constantes.casillasTotales ; i++) {
			if(i < Constantes.numeroMinas) {
				listaCasillas.add("-1");
			} else {
				listaCasillas.add("0");
			}
		}
		return listaCasillas;
	}
	
	public void generarMinas(ArrayList<String> listaCasillas) {
		Collections.shuffle(listaCasillas);
		for(int i=0 ; i<listaCasillas.size() ; i++) {
			vistaJuego.getBtnCasillas()[i].setText(listaCasillas.get(i));
			vistaJuego.getBtnCasillas()[i].setFont(new Font("Verdana", 0, 0)); //Pone el tamaño del texto a 0 para que no se vea
//			vistaJuego.getBtnCasillas()[i].setIcon(new ImageIcon(Constantes.imgNumeros[0]));
		}
	}
	
	/**
	 * Busca todas las minas existentes e incrementa en 1 todas las casillas de alrededor
	 * @param listaCasillas
	 * @return ArrayList<String>
	 */
	public void generarNumeros(ArrayList<String> listaCasillas) {
		for(int i=0 ; i<listaCasillas.size() ; i++) {
			if(listaCasillas.get(i).split(";")[1].equals("-1")) {
				
				if(!isMina(utilTablero.getCasillaInferior(i))) {
					//Incrementar casilla inferior
					incrementarCasilla(listaCasillas, utilTablero.getCasillaInferior(i));
				}
		
				if(i != 0
				&& !isMina(utilTablero.getCasillaSuperior(i))) {
					//Incrementar casilla superior
					incrementarCasilla(listaCasillas, utilTablero.getCasillaSuperior(i));
				}
		
				if(!isMina(utilTablero.getCasillaIzquierda(i))) {
					//Incrementar casilla izquierda
					incrementarCasilla(listaCasillas, utilTablero.getCasillaIzquierda(i));
				}
		
				if(!isMina(utilTablero.getCasillaDerecha(i))) {
					//Incrementar casilla derecha
					incrementarCasilla(listaCasillas, utilTablero.getCasillaDerecha(i));
				}
		
				if(!isMina(utilTablero.getCasillaSuperiorIzquierda(i))) {
					//Incrementar casilla superior izquierda
					incrementarCasilla(listaCasillas, utilTablero.getCasillaSuperiorIzquierda(i));
				}
		
				if(i != 0
				&& !isMina(utilTablero.getCasillaSuperiorDerecha(i))) {
					//Incrementar casilla superior derecha
					incrementarCasilla(listaCasillas, utilTablero.getCasillaSuperiorDerecha(i));
				}
				
				if(!isMina(utilTablero.getCasillaInferiorIzquierda(i))) {
					//Incrementar casilla inferior izquierda
					incrementarCasilla(listaCasillas, utilTablero.getCasillaInferiorIzquierda(i));
				}
		
				if(!isMina(utilTablero.getCasillaInferiorDerecha(i))) {
					//Incrementar casilla inferior derecha
					incrementarCasilla(listaCasillas, utilTablero.getCasillaInferiorDerecha(i));
				}
			}
		}
	}
	
	public void incrementarCasilla(ArrayList<String> listaCasillas, int indice) {
		if(indice != Constantes.casillasTotales) {
			int numeroActual = Integer.parseInt(listaCasillas.get(indice).split(";")[1]);
			vistaJuego.getBtnCasillas()[indice].setText(indice+";"+(numeroActual+1));
			listaCasillas.set(indice, indice+";"+(numeroActual+1));
		}
	}
	
	public boolean isMina(int indice) {
		if(indice != Constantes.casillasTotales) {
			int valorCasilla = Integer.parseInt(vistaJuego.getBtnCasillas()[indice].getText().split(";")[1]);
			if(valorCasilla == -1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isBandera(int indice) {
		boolean isBandera = false;
		if(vistaJuego.getBtnCasillas()[indice].getIcon() != null) {
			if(vistaJuego.getBtnCasillas()[indice].getIcon().toString().contains(Constantes.IMG_CASILLAS[9])) {
				isBandera = true;
			}
		}
		return isBandera;
	}
	
	
	public void addIndicesCasillas(ArrayList<String> listaCasillas) {
		for(int i=0 ; i<listaCasillas.size() ; i++) {
			listaCasillas.set(i, i+";"+listaCasillas.get(i));
			vistaJuego.getBtnCasillas()[i].setText(listaCasillas.get(i));
		}
	}
	
	public void ponerBandera(int indice) {
		if(vistaJuego.getBtnCasillas()[indice].getIcon() == null) {
			setIcono(indice, new ImageIcon(Constantes.IMG_CASILLAS[9]));
			modInfoMinasRestantes(-1);
		} else if(vistaJuego.getBtnCasillas()[indice].getIcon() != null) {
			vistaJuego.getBtnCasillas()[indice].setIcon(null);
			modInfoMinasRestantes(1);
		}
	}
	
	public void setIcono(int indice, ImageIcon icon) {
		int ancho = vistaJuego.getBtnCasillas()[indice].getWidth();
		int alto = vistaJuego.getBtnCasillas()[indice].getHeight();

		if(icon.toString().contains("/")) {
			imagenesCasillas.put(indice, icon.toString());
		}
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(++ancho, ++alto, Image.SCALE_DEFAULT));
		vistaJuego.getBtnCasillas()[indice].setIcon(icono);
	}
	
	public void modInfoMinasRestantes(int num) {
		String minasRestantes = vistaJuego.getLblMinasRestantes().getText();
		minasRestantes = minasRestantes.replace(Constantes.TEXTO_LBL_MINAS_RESTANTES, "");
		int intMinasRestantes = Integer.parseInt(minasRestantes);
		intMinasRestantes += num;
		vistaJuego.getLblMinasRestantes().setText(Constantes.TEXTO_LBL_MINAS_RESTANTES + intMinasRestantes);
	}
	
	
	public void desvelarCasillasAlrededor(int i) {
		if(i != Constantes.casillasTotales) {
			casillaColindanteVacia = new HashSet<>();
			desvelarCasilla(utilTablero.getCasillaInferior(i));
			desvelarCasilla(utilTablero.getCasillaSuperior(i));
			desvelarCasilla(utilTablero.getCasillaIzquierda(i));
			desvelarCasilla(utilTablero.getCasillaDerecha(i));
			desvelarCasilla(utilTablero.getCasillaSuperiorIzquierda(i));
			desvelarCasilla(utilTablero.getCasillaSuperiorDerecha(i));
			desvelarCasilla(utilTablero.getCasillaInferiorIzquierda(i));
			desvelarCasilla(utilTablero.getCasillaInferiorDerecha(i));
			
			for(int casillaVacia : casillaColindanteVacia) {
				desvelarCasillasAlrededor(casillaVacia);
			}
		}
	}
	
	public HashSet<Integer> desvelarCasilla(int i) {
		setIcono(i, new ImageIcon(Constantes.IMG_CASILLAS[utilTablero.getValorCasilla(i)]));
		if(utilTablero.getValorCasilla(i) == 0 && vistaJuego.getBtnCasillas()[i].isFocusable()) {
			casillaColindanteVacia.add(i);
		}
		vistaJuego.getBtnCasillas()[i].setFocusable(false);
		return casillaColindanteVacia;
	}

	public void desvelarTodasLasMinas() {
		for(int i=0 ; i<vistaJuego.getBtnCasillas().length ; i++) {
			if(utilTablero.getValorCasilla(i) == -1) {
				setIcono(i, new ImageIcon(Constantes.IMG_CASILLAS[10]));
			}
		}
	}
	
	public void comprobarSiHaGanado() {
		int casillasRestantes = Constantes.casillasTotales+1 - Constantes.numeroMinas;
		for(int i=0 ; i<vistaJuego.getBtnCasillas().length ; i++) {
			if(!vistaJuego.getBtnCasillas()[i].isFocusable()) {
				casillasRestantes--;
			}
		}
		if(casillasRestantes == 0) {
			JOptionPane.showMessageDialog(null, "Enhorabuena! :D", "Has ganado", JOptionPane.WARNING_MESSAGE);
			utilJSON.incrementarGanadas();
			preguntarOtraPartida();
		}
	}
	
	public void preguntarOtraPartida() {
		int opcion = JOptionPane.showConfirmDialog(null, "¿Otra partida?", "Reiniciar", JOptionPane.YES_NO_OPTION);
		switch(opcion) {
		case 0:
			nuevoJuego(0, 0, 0);
			break;
		case 1:
			vistaJuego.dispose();
			break;
		}
	}
	
	public void nuevoJuego(int ancho, int alto, int minas) {
		if(ancho > 0 && alto > 0 && minas > 0) {
			Constantes.anchoTabla = ancho;
			Constantes.altoTabla = alto;
			Constantes.casillasTotales = Constantes.anchoTabla * Constantes.altoTabla;
			Constantes.numeroMinas = minas;
		}
		vistaJuego.dispose();
		Main.run();
	}
	
}
