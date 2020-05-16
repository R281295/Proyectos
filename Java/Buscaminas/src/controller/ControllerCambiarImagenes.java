package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import util.Adapter;
import util.Constantes;
import view.VistaCambiarImagenes;
import view.VistaJuego;

public class ControllerCambiarImagenes extends Adapter implements ActionListener {
	
	private VistaJuego vistaJuego;
	private VistaCambiarImagenes vistaCambiarImagenes;
	
	public ControllerCambiarImagenes(VistaJuego vistaJuego, VistaCambiarImagenes vistaCambiarImagenes) {
		this.vistaJuego = vistaJuego;
		vistaJuego.setEnabled(false);
		this.vistaCambiarImagenes = vistaCambiarImagenes;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand().equals("Aceptar")) {
			int opcion = JOptionPane.showConfirmDialog(vistaCambiarImagenes, "Para aplicar los cambios deberá empezar un nuevo juego\n¿Desea continuar?", "Cambiar imagenes", JOptionPane.YES_NO_OPTION);
			if(opcion == 0) {
				vistaCambiarImagenes.dispose();
				vistaJuego.dispose();
				Main.run();
			}
		} else {
			int indice = Integer.parseInt(evt.getActionCommand());
			asignarImagen(indice);
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		vistaJuego.setEnabled(true);
	}
	
	private void asignarImagen(int indice) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de imagen (jpg, jpeg, png, bmp)", "jpg", "jpeg", "png", "bmp"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("(*.jpg, *.jpeg)", "jpg", "jpeg"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.bmp", "bmp"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.png", "png"));
		int option = fileChooser.showOpenDialog(vistaCambiarImagenes);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String path = file.getAbsolutePath();
			Constantes.IMG_CASILLAS[indice] = path;
			vistaCambiarImagenes.getBtnCasillas().get(indice).setIcon(new ImageIcon(Constantes.IMG_CASILLAS[indice]));
		}
	}
	
	public boolean isNuevoJuego() {
		return true;
	}
	
}
