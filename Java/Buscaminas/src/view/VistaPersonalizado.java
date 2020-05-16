package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerJuego;

@SuppressWarnings("serial")
public class VistaPersonalizado extends JFrame {

	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private JPanel panel;
	private JLabel lblAncho, lblAlto, lblMinas;
	private JTextField txtAncho, txtAlto, txtMinas;
	private JButton btnAceptar;
	
	
	public VistaPersonalizado() {
		
		setTitle("Personalizado");
		setSize(320, 180);
		
		setLocationRelativeTo(null);
		

		panel = new JPanel();
		layout = new GridBagLayout();
		panel.setLayout(layout);
		
		constraints = new GridBagConstraints();
		
		lblAncho = new JLabel("Ancho");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(5, 20, 5, 20);
		panel.add(lblAncho, constraints);
		
		lblAlto = new JLabel("Alto");
		constraints.gridy = 1;
		panel.add(lblAlto, constraints);
		
		lblMinas = new JLabel("Minas");
		constraints.gridy = 2;
		panel.add(lblMinas, constraints);
		
		txtAncho = new JTextField();
		constraints.gridx = 1;
		constraints.gridy = 0;
		panel.add(txtAncho, constraints);
		
		txtAlto = new JTextField();
		constraints.gridy = 1;
		panel.add(txtAlto, constraints);
		
		txtMinas = new JTextField();
		constraints.gridy = 2;
		panel.add(txtMinas, constraints);
		
		
		btnAceptar = new JButton("Aceptar");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		panel.add(btnAceptar, constraints);
		
		setContentPane(panel);
		setVisible(true);
	}
	
	public void conectarControlador(ControllerJuego c) {
		btnAceptar.setActionCommand("Personalizado" + "Aceptar");
		btnAceptar.addActionListener(c);
	}
	
	public int getAncho() throws NumberFormatException {
		return Integer.parseInt(txtAncho.getText());
	}

	public int getAlto() throws NumberFormatException {
		return Integer.parseInt(txtAlto.getText());
	}

	public int getMinas() throws NumberFormatException {
		return Integer.parseInt(txtMinas.getText());
	}
	

}
