package clinica.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

import clinica.model.Controller;

public class Eliminar extends JFrame {
    
    private JPanel panel, panel_botones;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JLabel eliminarLabelId;
    private JTextField eliminarTextId;
    private JButton eliminarBtnAceptar, eliminarBtnCancelar;
    
    public Eliminar() {
        setTitle("Eliminar");
        setSize(350, 160);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //Se va a utilizar un GridBagLayout para colocar los componentes
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        panel.setLayout(layout);
        
        
        //Se crean todos los objetos y se añaden al JPanel con sus correspondientes restricciones
        
        /* ----ETIQUETAS---- */
        eliminarLabelId = new JLabel("ID");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(eliminarLabelId, constraints);
        
        
        /* ----TEXTFIELD---- */
        eliminarTextId = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        panel.add(eliminarTextId, constraints);
        
        
        /* ----BOTONES---- */
        panel_botones = new JPanel();
        eliminarBtnAceptar = new JButton("Aceptar");
        eliminarBtnCancelar = new JButton("Cancelar");
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        
        panel_botones.add(eliminarBtnAceptar);
        panel_botones.add(eliminarBtnCancelar);
        
        //Les añado un "nombre" propio a cada botón para distinguir que boton he pulsado
        //ya que en todas las ventanas se llaman igual
        eliminarBtnAceptar.setActionCommand("el_aceptar");
        eliminarBtnCancelar.setActionCommand("el_cancelar");
        
        panel.add(panel_botones, constraints);
        
        
        setContentPane(panel);
    }
    
    public void conectarControlador(Controller c) {
    	eliminarBtnAceptar.addActionListener(c);
        eliminarBtnCancelar.addActionListener(c);
    }
    
    public JTextField getEliminarTextId() {
    	return eliminarTextId;
    }
    

}
