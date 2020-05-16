package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ControllerCambiarImagenes;
import util.Constantes;

@SuppressWarnings("serial")
public class VistaCambiarImagenes extends JFrame {
	
	private BoxLayout boxLayout;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private JPanel panel, panelBtnAceptar, panelImagenes;
	private ArrayList<JButton> btnCasillas;
	private JButton btnAceptar;
	
	
	public VistaCambiarImagenes() {
		
		setTitle("Cambiar imagenes");
		setSize(320, 180);
		
		setLocationRelativeTo(null);
		

		panel = new JPanel();
		boxLayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxLayout);
		
		panelBtnAceptar = new JPanel();

		btnAceptar = new JButton("Aceptar");
		panelBtnAceptar.add(btnAceptar);

		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		panelImagenes = new JPanel();
		panelImagenes.setLayout(layout);
		

		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		
		btnCasillas = new ArrayList<>();
		int cont = 0;
		for(int i=0 ; i<6 ; i++) {
			constraints.gridx = i;
			for(int j=0 ; j<2 ; j++) {
				constraints.gridy = j;
				if(i != 0 || j != 1) {
					JButton btn = new JButton();
					btn.setPreferredSize(new Dimension(0, 0));
					btn.setIcon(new ImageIcon(Constantes.IMG_CASILLAS[cont]));
					
					btn.setText(""+cont++);
					btn.setFont(new Font("Verdana", 0, 0));
					
					
					panelImagenes.add(btn, constraints);
					

					btnCasillas.add(btn);
				}
			}
			
		}
		
		
		
		panel.add(panelImagenes);
		panel.add(panelBtnAceptar);
		
		setContentPane(panel);
		setVisible(true);
	}
	
	public void conectarControlador(ControllerCambiarImagenes c) {
		this.addWindowListener(c);
		for(int i=0 ; i<btnCasillas.size() ; i++) {
			btnCasillas.get(i).addActionListener(c);
		}
		
		btnAceptar.addActionListener(c);
	}
	
	public ArrayList<JButton> getBtnCasillas() {
		return btnCasillas;
	}

}
