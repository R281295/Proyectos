package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControllerJuego;
import util.UtilJSON;

@SuppressWarnings("serial")
public class VistaEstadisticas extends JFrame {
	
	private UtilJSON utilJSON;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private JPanel panel;
	private JLabel lblpartidasJugadas, lblpartidasPerdidas, lblpartidasGanadas, lblpartidasSinAcabar;
	private JLabel lblpartidasJugadasCont, lblpartidasPerdidasCont, lblpartidasGanadasCont, lblpartidasSinAcabarCont;
	private JButton btnAceptar;
	
	public VistaEstadisticas() {
		utilJSON = new UtilJSON();
		
		setTitle("Estadisticas");
		setSize(210, 220);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		panel.setLayout(layout);
		

		lblpartidasJugadas = new JLabel("Partidas Jugadas: ");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(5, 10, 5, 10);
		panel.add(lblpartidasJugadas, constraints);
		
		lblpartidasPerdidas = new JLabel("Partidas Perdidas: ");
		constraints.gridy = 1;
		panel.add(lblpartidasPerdidas, constraints);
		
		lblpartidasGanadas = new JLabel("Partidas Ganadas: ");
		constraints.gridy = 2;
		panel.add(lblpartidasGanadas, constraints);
		
		lblpartidasSinAcabar = new JLabel("Partidas Sin Acabar");
		constraints.gridy = 3;
		panel.add(lblpartidasSinAcabar, constraints);

		
		lblpartidasJugadasCont = new JLabel(utilJSON.getParam("Jugadas"));
		constraints.gridx = 1;
		constraints.gridy = 0;
		panel.add(lblpartidasJugadasCont, constraints);
		
		lblpartidasPerdidasCont = new JLabel(utilJSON.getParam("Perdidas"));
		constraints.gridy = 1;
		panel.add(lblpartidasPerdidasCont, constraints);
		
		lblpartidasGanadasCont = new JLabel(utilJSON.getParam("Ganadas"));
		constraints.gridy = 2;
		panel.add(lblpartidasGanadasCont, constraints);
		
		lblpartidasSinAcabarCont = new JLabel(utilJSON.getParam("SinAcabar"));
		constraints.gridy = 3;
		panel.add(lblpartidasSinAcabarCont, constraints);

		
		btnAceptar = new JButton("Aceptar");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(20, 50, 5, 50);
		panel.add(btnAceptar, constraints);
		
		
		setContentPane(panel);
		setVisible(true);
	}
	
	public void conectarControlador(ControllerJuego c) {
		btnAceptar.setActionCommand("Estadisticas" + "Aceptar");
		btnAceptar.addActionListener(c);
	}
}
