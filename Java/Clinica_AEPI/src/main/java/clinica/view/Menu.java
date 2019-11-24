package clinica.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import clinica.bin.ConstValues;
import clinica.model.Controller;
import clinica.model.Decorado;
import clinica.util.ConexionSQL;

public class Menu extends JFrame implements ActionListener {
    
    Decorado decorado = new Decorado();
    ConexionSQL sql;
    
    public Menu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        
        //Se crea el MenuBar y los dos menús que va a contener
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menú");
        
        JMenu design = new JMenu("Diseño");
        
        //Se crean todos los items que va a contener el menú
        JMenuItem insertar = new JMenuItem("Insertar");
        JMenuItem visualizar = new JMenuItem("Visualizar");
        JMenuItem editar = new JMenuItem("Editar");
        JMenuItem eliminar = new JMenuItem("Eliminar");
        
        JMenuItem windows = new JMenuItem("Windows");
        JMenuItem nimbus = new JMenuItem("Nimbus");
        JMenuItem upperEssential = new JMenuItem("UpperEssential");
        JMenuItem mac = new JMenuItem("Mac");
        JMenuItem aero = new JMenuItem("Aero");
        JMenuItem bernstein = new JMenuItem("Bernstein");
        JMenuItem noire = new JMenuItem("Noire");
        
        //Se asocian todos lo items a su menú correspondiente
        menu.add(insertar);
        menu.add(visualizar);
        menu.add(editar);
        menu.add(eliminar);
        
        design.add(windows);
        design.add(nimbus);
        design.add(upperEssential);
        design.add(mac);
        design.add(aero);
        design.add(bernstein);
        design.add(noire);
        
        //A cada item se le añade un Listener
        insertar.addActionListener(this);
        visualizar.addActionListener(this);
        editar.addActionListener(this);
        eliminar.addActionListener(this);
        
        windows.addActionListener(this);
        nimbus.addActionListener(this);
        upperEssential.addActionListener(this);
        mac.addActionListener(this);
        aero.addActionListener(this);
        bernstein.addActionListener(this);
        noire.addActionListener(this);
        
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
                sql = new ConexionSQL();
                try {
                	int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID que deseas editar"));
                	if(sql.comprobarSiExisteID(id)) {
	                	controller.rellenarFormulario(id);
	                	editar.conectarControlador(controller);
	                    editar.setVisible(true);
                	} else {
                		System.out.println("El ID introducido no existe."); //TODO Hacer JOptionPane
                	}
                }catch(Exception ex) {
                	System.out.println("El ID introducido no es correcto: "+ex.getMessage()); //TODO Hacer JOptionPane
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
                setVisible(false);
                dispose();
                decorado.decorado(0);
                Menu windows = new Menu();
                break;
            case "Nimbus":
                setVisible(false);
                dispose();
                decorado.decorado(1);
                Menu nimbus = new Menu();
                break;
            case "UpperEssential":
                setVisible(false);
                dispose();
                decorado.decorado(2);
                Menu upperEssential = new Menu();
                break;
            case "Mac":
                setVisible(false);
                dispose();
                decorado.decorado(3);
                Menu mac = new Menu();
                break;
            case "Aero":
                setVisible(false);
                dispose();
                decorado.decorado(4);
                Menu aero = new Menu();
                break;
            case "Bernstein":
                setVisible(false);
                dispose();
                decorado.decorado(5);
                Menu bernstein = new Menu();
                break;
            case "Noire":
                setVisible(false);
                dispose();
                decorado.decorado(6);
                Menu noire = new Menu();
                break;
                
        }
    }
    

}
