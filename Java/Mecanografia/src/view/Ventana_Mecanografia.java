package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

import controller.Controller;

public class Ventana_Mecanografia extends JFrame {
    
    private JMenuBar menuBar;
    private JMenu aprendizaje;
    private JMenu leccion1, leccion2, leccion3, leccion4, leccion5, leccion6;
    private JMenuItem lec1_practica1, lec1_practica2, lec1_practica3, lec1_practica4, lec1_practica5;
    private JMenuItem lec2_practica1, lec2_practica2, lec2_practica3, lec2_practica4, lec2_practica5;
    private JMenuItem lec3_practica1, lec3_practica2, lec3_practica3, lec3_practica4, lec3_practica5;
    private JMenuItem lec4_practica1, lec4_practica2, lec4_practica3, lec4_practica4, lec4_practica5;
    private JMenuItem lec5_practica1, lec5_practica2, lec5_practica3, lec5_practica4, lec5_practica5;
    private JMenuItem lec6_practica1, lec6_practica2, lec6_practica3, lec6_practica4, lec6_practica5;
    private JMenu textos;
    private JMenuItem random;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JPanel panel;
    private JTextArea texto;
    private JLabel texto_label, tiempo;
    private JTextField escritura;
    
    public Ventana_Mecanografia() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        
//**************MENÚS****************************************************************************
        menuBar = new JMenuBar();
        aprendizaje = new JMenu("Aprendizaje");
        leccion1 = new JMenu("Lección 1 - Fila media");
        leccion2 = new JMenu("Lección 2 - Fila de arriba");
        leccion3 = new JMenu("Lección 3 - Fila media y arriba");
        leccion4 = new JMenu("Lección 4 - Fila de abajo");
        leccion5 = new JMenu("Lección 5 - Fila media y abajo");
        leccion6 = new JMenu("Lección 6 - Todas las filas");
        aprendizaje.add(leccion1);
        aprendizaje.add(leccion2);
        aprendizaje.add(leccion3);
        aprendizaje.add(leccion4);
        aprendizaje.add(leccion5);
        aprendizaje.add(leccion6);
        
        lec1_practica1 = new JMenuItem("Practica 1");
        lec1_practica2 = new JMenuItem("Practica 2");
        lec1_practica3 = new JMenuItem("Practica 3");
        lec1_practica4 = new JMenuItem("Practica 4");
        lec1_practica5 = new JMenuItem("Practica 5");
        leccion1.add(lec1_practica1);
        leccion1.add(lec1_practica2);
        leccion1.add(lec1_practica3);
        leccion1.add(lec1_practica4);
        leccion1.add(lec1_practica5);
        
        lec2_practica1 = new JMenuItem("Practica 1");
        lec2_practica2 = new JMenuItem("Practica 2");
        lec2_practica3 = new JMenuItem("Practica 3");
        lec2_practica4 = new JMenuItem("Practica 4");
        lec2_practica5 = new JMenuItem("Practica 5");
        leccion2.add(lec2_practica1);
        leccion2.add(lec2_practica2);
        leccion2.add(lec2_practica3);
        leccion2.add(lec2_practica4);
        leccion2.add(lec2_practica5);
        
        lec3_practica1 = new JMenuItem("Practica 1");
        lec3_practica2 = new JMenuItem("Practica 2");
        lec3_practica3 = new JMenuItem("Practica 3");
        lec3_practica4 = new JMenuItem("Practica 4");
        lec3_practica5 = new JMenuItem("Practica 5");
        leccion3.add(lec3_practica1);
        leccion3.add(lec3_practica2);
        leccion3.add(lec3_practica3);
        leccion3.add(lec3_practica4);
        leccion3.add(lec3_practica5);
        
        lec4_practica1 = new JMenuItem("Practica 1");
        lec4_practica2 = new JMenuItem("Practica 2");
        lec4_practica3 = new JMenuItem("Practica 3");
        lec4_practica4 = new JMenuItem("Practica 4");
        lec4_practica5 = new JMenuItem("Practica 5");
        leccion4.add(lec4_practica1);
        leccion4.add(lec4_practica2);
        leccion4.add(lec4_practica3);
        leccion4.add(lec4_practica4);
        leccion4.add(lec4_practica5);
        
        lec5_practica1 = new JMenuItem("Practica 1");
        lec5_practica2 = new JMenuItem("Practica 2");
        lec5_practica3 = new JMenuItem("Practica 3");
        lec5_practica4 = new JMenuItem("Practica 4");
        lec5_practica5 = new JMenuItem("Practica 5");
        leccion5.add(lec5_practica1);
        leccion5.add(lec5_practica2);
        leccion5.add(lec5_practica3);
        leccion5.add(lec5_practica4);
        leccion5.add(lec5_practica5);
        
        lec6_practica1 = new JMenuItem("Practica 1");
        lec6_practica2 = new JMenuItem("Practica 2");
        lec6_practica3 = new JMenuItem("Practica 3");
        lec6_practica4 = new JMenuItem("Practica 4");
        lec6_practica5 = new JMenuItem("Practica 5");
        leccion6.add(lec6_practica1);
        leccion6.add(lec6_practica2);
        leccion6.add(lec6_practica3);
        leccion6.add(lec6_practica4);
        leccion6.add(lec6_practica5);
        
        
        textos = new JMenu("Textos");
        random = new JMenuItem("Random");
        textos.add(random);
        
        setJMenuBar(menuBar);
        menuBar.add(aprendizaje);
        menuBar.add(textos);
        
//*****************FIN MENÚS******************************************************************************
        
        
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        panel.setBackground(new Color(200, 255, 200));
        panel.setLayout(layout);
        
        texto = new JTextArea(); //No se añade al JPanel puesto que es invisible

        tiempo = new JLabel("00:00");
        tiempo.setOpaque(true);
        tiempo.setBackground(Color.yellow);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tiempo, constraints);
        
        texto_label = new JLabel();
        texto_label.setFont(new java.awt.Font("Tahoma", 0, 26));
        texto_label.setOpaque(true);
        texto_label.setBackground(new Color(200, 255, 200));
        texto_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        constraints.gridy = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(texto_label, constraints);
        
        escritura = new JTextField();
        constraints.gridy = 2;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(escritura, constraints);
        
        setContentPane(panel);
        setVisible(true);
    }
    
    
    public void conectarControlador(Controller c) {
        escritura.addKeyListener(c);
        
        lec1_practica1.setActionCommand("lec1_practica1");
        lec1_practica2.setActionCommand("lec1_practica2");
        lec1_practica3.setActionCommand("lec1_practica3");
        lec1_practica4.setActionCommand("lec1_practica4");
        lec1_practica5.setActionCommand("lec1_practica5");
        
        lec2_practica1.setActionCommand("lec2_practica1");
        lec2_practica2.setActionCommand("lec2_practica2");
        lec2_practica3.setActionCommand("lec2_practica3");
        lec2_practica4.setActionCommand("lec2_practica4");
        lec2_practica5.setActionCommand("lec2_practica5");
        
        lec3_practica1.setActionCommand("lec3_practica1");
        lec3_practica2.setActionCommand("lec3_practica2");
        lec3_practica3.setActionCommand("lec3_practica3");
        lec3_practica4.setActionCommand("lec3_practica4");
        lec3_practica5.setActionCommand("lec3_practica5");
        
        lec4_practica1.setActionCommand("lec4_practica1");
        lec4_practica2.setActionCommand("lec4_practica2");
        lec4_practica3.setActionCommand("lec4_practica3");
        lec4_practica4.setActionCommand("lec4_practica4");
        lec4_practica5.setActionCommand("lec4_practica5");
        
        lec5_practica1.setActionCommand("lec5_practica1");
        lec5_practica2.setActionCommand("lec5_practica2");
        lec5_practica3.setActionCommand("lec5_practica3");
        lec5_practica4.setActionCommand("lec5_practica4");
        lec5_practica5.setActionCommand("lec5_practica5");
        
        lec6_practica1.setActionCommand("lec6_practica1");
        lec6_practica2.setActionCommand("lec6_practica2");
        lec6_practica3.setActionCommand("lec6_practica3");
        lec6_practica4.setActionCommand("lec6_practica4");
        lec6_practica5.setActionCommand("lec6_practica5");
        
        
        lec1_practica1.addActionListener(c);
        lec1_practica2.addActionListener(c);
        lec1_practica3.addActionListener(c);
        lec1_practica4.addActionListener(c);
        lec1_practica5.addActionListener(c);
        
        lec2_practica1.addActionListener(c);
        lec2_practica2.addActionListener(c);
        lec2_practica3.addActionListener(c);
        lec2_practica4.addActionListener(c);
        lec2_practica5.addActionListener(c);
        
        lec3_practica1.addActionListener(c);
        lec3_practica2.addActionListener(c);
        lec3_practica3.addActionListener(c);
        lec3_practica4.addActionListener(c);
        lec3_practica5.addActionListener(c);
        
        lec4_practica1.addActionListener(c);
        lec4_practica2.addActionListener(c);
        lec4_practica3.addActionListener(c);
        lec4_practica4.addActionListener(c);
        lec4_practica5.addActionListener(c);
        
        lec5_practica1.addActionListener(c);
        lec5_practica2.addActionListener(c);
        lec5_practica3.addActionListener(c);
        lec5_practica4.addActionListener(c);
        lec5_practica5.addActionListener(c);
        
        lec6_practica1.addActionListener(c);
        lec6_practica2.addActionListener(c);
        lec6_practica3.addActionListener(c);
        lec6_practica4.addActionListener(c);
        lec6_practica5.addActionListener(c);
        
        random.addActionListener(c);
    }
    
    
    public JTextArea getTexto() {
    	return texto;
    }
    
    public JLabel getTexto_label() {
    	return texto_label;
    }
    
    public JLabel getTiempo() {
    	return tiempo;
    }
    
    public JTextField getEscritura() {
    	return escritura;
    }

}
