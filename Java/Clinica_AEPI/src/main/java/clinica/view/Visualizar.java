package clinica.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clinica.bin.ConstValues;
import clinica.model.Controller;
import clinica.util.ConexionSQL;

public class Visualizar extends JFrame {
    
    private JPanel panel, panel_botones;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JLabel visualizarLabelBuscar;
    private JTextField visualizarTextBuscar;
    private JTable tabla;
    private JScrollPane scroll;
    private JButton visualizarBtnAceptar, visualizarBtnCancelar;
    
    public Visualizar() {
        setTitle(ConstValues.TITULO_VENTANA_VISUALIZAR);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        panel.setLayout(layout);
        
        
        
        /* ----ETIQUETAS---- */
        visualizarLabelBuscar = new JLabel("Buscar:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST;
        
        constraints.ipadx = 20;
        panel.add(visualizarLabelBuscar, constraints);
        
        
        /* ----TEXTFIELD---- */
        visualizarTextBuscar = new JTextField();
        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(visualizarTextBuscar, constraints);
        
        /* ----TABLA---- */
        tabla = new JTable();
        
        ConexionSQL sql = new ConexionSQL();
        tabla.setModel(new DefaultTableModel(ConstValues.CAMPOS_CLIENTE, sql.cuentaRegistros()));
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        panel.add(tabla, constraints);
        
        /* ----SCROLL---- */
        scroll = new JScrollPane();
        scroll.setViewportView(tabla);
        panel.add(scroll, constraints);
        
        /* ----BOTONES---- */
        panel_botones = new JPanel();
        visualizarBtnAceptar = new JButton("Aceptar");
        visualizarBtnCancelar = new JButton("Cancelar");
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        
        panel_botones.add(visualizarBtnAceptar);
        panel_botones.add(visualizarBtnCancelar);
        
        visualizarBtnAceptar.setActionCommand("vis_aceptar");
        visualizarBtnCancelar.setActionCommand("vis_cancelar");
        
        panel.add(panel_botones, constraints);
        setContentPane(panel);
    }
    
    public void conectarControlador(Controller c) {
    	visualizarBtnAceptar.addActionListener(c);
    	visualizarBtnCancelar.addActionListener(c);
    }
    
    public JTextField getVisualizarTextBuscar() {
    	return visualizarTextBuscar;
    }
    
    public JTable getTabla() {
    	return tabla;
    }

}
