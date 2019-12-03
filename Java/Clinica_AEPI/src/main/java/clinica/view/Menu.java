package clinica.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import clinica.model.Controller;
import clinica.model.Decorado;
import clinica.util.ConexionSQL;

public class Menu extends JFrame implements ActionListener {
    
    Decorado decorado = new Decorado();
    ConexionSQL connection;
    
    public Menu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        
        //Se crea el MenuBar y los dos menÃºs que va a contener
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menú");
        
        JMenu design = new JMenu("Diseño");
        
        //Se crean todos los items que va a contener el menÃº
        JMenuItem insertar = new JMenuItem("Insertar");
        JMenuItem visualizar = new JMenuItem("Visualizar");
        JMenuItem editar = new JMenuItem("Editar");
        JMenuItem eliminar = new JMenuItem("Eliminar");
        
        JMenuItem windows = new JMenuItem("Windows");
        JMenuItem nimbus = new JMenuItem("Nimbus");
        
        //Se asocian todos lo items a su menÃº correspondiente
        menu.add(insertar);
        menu.add(visualizar);
        menu.add(editar);
        menu.add(eliminar);
        
        design.add(windows);
        design.add(nimbus);
        
        //A cada item se le añade un Listener
        insertar.addActionListener(this);
        visualizar.addActionListener(this);
        editar.addActionListener(this);
        eliminar.addActionListener(this);
        
        windows.addActionListener(this);
        nimbus.addActionListener(this);
        
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menuBar.add(design);
        
        setContentPane(new JLabel(new ImageIcon("images/logo.jpg")));
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Controller controller;
        String opcion = e.getActionCommand();
        
        switch(opcion) {
            case "Insertar":
                Formulario_Insertar_Editar insertar = new Formulario_Insertar_Editar();
                controller = new Controller(insertar, null, null);
                insertar.conectarControlador(controller);
                insertar.setVisible(true);
                break;
            case "Editar":
                Formulario_Insertar_Editar editar = new Formulario_Insertar_Editar();
                editar.setTitle("Editar");
                controller = new Controller(editar, null, null);
                connection = new ConexionSQL();
                //TODO arreglar esto
                try {
                	int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID que deseas editar"));
                	if(connection.comprobarSiExisteID(id)) {
	                	controller.rellenarFormulario(id);
	                	editar.conectarControlador(controller);
	                    editar.setVisible(true);
                	} else {
                		JOptionPane.showMessageDialog(null, "El ID introducido no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                	}
                }catch(Exception ex) {
                	JOptionPane.showMessageDialog(null, "El ID introducido no es correcto: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
            case "Visualizar":
                Visualizar visualizar = new Visualizar();
                controller = new Controller(null, visualizar, null);
                visualizar.conectarControlador(controller);
                controller.mostrarDatosEnTabla();
                visualizar.setVisible(true);
                break;
            case "Eliminar":
                Eliminar eliminar = new Eliminar();
                controller = new Controller(null, null, eliminar);
                eliminar.conectarControlador(controller);
                eliminar.setVisible(true);
                break;
            case "Windows":
                cambiarDecorado(0);
                break;
            case "Nimbus":
                cambiarDecorado(1);
                break;
                
        }
    }
    
    private void cambiarDecorado(int decorado) {
    	setVisible(false);
        dispose();
        this.decorado.decorado(decorado);
        new Menu();
    }
    

}
