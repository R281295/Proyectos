package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.ControllerJuego;
import util.Constantes;


@SuppressWarnings("serial")
public class VistaJuego extends JFrame {
	
	private BoxLayout boxLayout;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private JPanel panel, panelCasillas, panelInfo;
	private JMenu menu, submenu;
	private JMenuBar menuBar;
	private ArrayList<JMenuItem> listMenuItem;
	private JButton[] btnCasillas;
	private JLabel minasRestantes;
	
	public VistaJuego() {
		setTitle("Buscaminas");
		setSize(400+Constantes.anchoTabla*2, 400+Constantes.altoTabla*4);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addMenu();
		

		panel = new JPanel();
		boxLayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxLayout);
		
		panelInfo = new JPanel();

		minasRestantes = new JLabel(Constantes.TEXTO_LBL_MINAS_RESTANTES + Constantes.numeroMinas);
		panelInfo.add(minasRestantes);

		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		panelCasillas = new JPanel();
		panelCasillas.setLayout(layout);
		

		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		
		btnCasillas = new JButton[Constantes.casillasTotales+1]; //+1 para tener un boton de mas, y usarlo como "comodin" para evitar errores
		int cont=0;
		for(int i=0 ; i<Constantes.anchoTabla ; i++) {
			constraints.gridx = i;
			for(int j=0 ; j<Constantes.altoTabla ; j++) {
				constraints.gridy = j;
				btnCasillas[cont] = new JButton("0");
				btnCasillas[cont].setPreferredSize(new Dimension(0, 0));
				panelCasillas.add(btnCasillas[cont], constraints);
				cont++;
			}
			
		}
		
		btnCasillas[Constantes.casillasTotales] = new JButton("64;0"); //Boton ficticio
		
		
		panel.add(panelInfo);
		panel.add(panelCasillas);
		
		setContentPane(panel);
		setVisible(true);
	}
	
	private void addMenu() {
		listMenuItem = new ArrayList<>();
		int cont = 0;
		menuBar = new JMenuBar();
			menu = new JMenu("Juego");
			listMenuItem.add(new JMenuItem(Constantes.MENU_NUEVO));
			menu.add(listMenuItem.get(cont++));
				submenu = new JMenu(Constantes.MENU_CAMBIAR_DIFICULTAD);
				listMenuItem.add(new JMenuItem(Constantes.MENU_FACIL));
				submenu.add(listMenuItem.get(cont++));
				listMenuItem.add(new JMenuItem(Constantes.MENU_MEDIO));
				submenu.add(listMenuItem.get(cont++));
				listMenuItem.add(new JMenuItem(Constantes.MENU_DIFICIL));
				submenu.add(listMenuItem.get(cont++));
				listMenuItem.add(new JMenuItem(Constantes.MENU_PERSONALIZADO));
				submenu.add(listMenuItem.get(cont++));
				menu.add(submenu);
		menu.addSeparator();
			listMenuItem.add(new JMenuItem(Constantes.MENU_ESTADISTICAS));
			menu.add(listMenuItem.get(cont++));
			listMenuItem.add(new JMenuItem(Constantes.MENU_SALIR));
			menu.add(listMenuItem.get(cont++));
		menuBar.add(menu);
			menu = new JMenu(Constantes.MENU_PREFERENCIAS);
			listMenuItem.add(new JMenuItem(Constantes.MENU_CAMBIAR_IMAGEN));
			menu.add(listMenuItem.get(cont++));
			menuBar.add(menu);
		setJMenuBar(menuBar);
	}
	
	public void conectarControlador(ControllerJuego c) {
		for(int i=0 ; i<btnCasillas.length ; i++) {
			btnCasillas[i].addMouseListener(c);
		}
		
		for(int i=0 ; i<listMenuItem.size() ; i++) {
			listMenuItem.get(i).addActionListener(c);
		}
		addComponentListener(c);
	}
	
	public JButton[] getBtnCasillas() {
		return btnCasillas;
	}
	
	public JLabel getLblMinasRestantes() {
		return minasRestantes;
	}

}
