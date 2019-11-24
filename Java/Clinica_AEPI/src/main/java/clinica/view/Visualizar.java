package clinica.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clinica.bin.ConstValues;
import clinica.model.Controller;
import clinica.util.ConexionSQL;

public class Visualizar extends JFrame {
    
    private JPanel panel, panel_botones;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JLabel vis_l_nombre;
    public JTextField vis_t_nombre;
    public JTable vis_table;
    public JScrollPane vis_scroll;
    public JButton vis_b_aceptar, vis_b_cancelar;
    
    public Visualizar() {
        setTitle("Visualizar");
        setSize(1000, 600);
//        setResizable(false);
        setLocationRelativeTo(null);
        
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        panel.setLayout(layout);
        
        
        
        /* ----ETIQUETAS---- */
        vis_l_nombre = new JLabel("Buscar:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST;
        
        constraints.ipadx = 20;
        panel.add(vis_l_nombre, constraints);
        
        
        /* ----TEXTFIELD---- */
        vis_t_nombre = new JTextField();
        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(vis_t_nombre, constraints);
        
        /* ----TABLA---- */
        vis_table = new JTable();
        
        ConexionSQL sql = new ConexionSQL();
        vis_table.setModel(new DefaultTableModel(ConstValues.camposCliente, sql.cuentaRegistros()));
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        panel.add(vis_table, constraints);
        
        /* ----SCROLL---- */
        vis_scroll = new JScrollPane();
        vis_scroll.setViewportView(vis_table);
        panel.add(vis_scroll, constraints);
        
        /* ----BOTONES---- */
        panel_botones = new JPanel();
        vis_b_aceptar = new JButton("Aceptar");
        vis_b_cancelar = new JButton("Cancelar");
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        
        panel_botones.add(vis_b_aceptar);
        panel_botones.add(vis_b_cancelar);
        
        vis_b_aceptar.setActionCommand("vis_aceptar");
        vis_b_cancelar.setActionCommand("vis_cancelar");
        
        panel.add(panel_botones, constraints);
        setContentPane(panel);
    }
    
    public void conectarControlador(Controller c) {
        vis_b_aceptar.addActionListener(c);
        vis_b_cancelar.addActionListener(c);
    }

}
