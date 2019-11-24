package clinica.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

import clinica.model.Controller;

public class Eliminar extends JFrame {
    
    private JPanel panel, panel_botones;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JLabel el_l_id;
    public JTextField el_t_id;
    public JButton el_b_aceptar, el_b_cancelar;
    
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
        el_l_id = new JLabel("ID");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(el_l_id, constraints);
        
        
        /* ----TEXTFIELD---- */
        el_t_id = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        panel.add(el_t_id, constraints);
        
        
        /* ----BOTONES---- */
        panel_botones = new JPanel();
        el_b_aceptar = new JButton("Aceptar");
        el_b_cancelar = new JButton("Cancelar");
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        
        panel_botones.add(el_b_aceptar);
        panel_botones.add(el_b_cancelar);
        
        //Les añado un "nombre" propio a cada botón para distinguir que boton he pulsado
        //ya que en todas las ventanas se llaman igual
        el_b_aceptar.setActionCommand("el_aceptar");
        el_b_cancelar.setActionCommand("el_cancelar");
        
        panel.add(panel_botones, constraints);
        
        
        setContentPane(panel);
    }
    
    public void conectarControlador(Controller c) {
        el_b_aceptar.addActionListener(c);
        el_b_cancelar.addActionListener(c);
    }
    

}
