package clinica.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import javax.swing.*;

import clinica.model.Controller;

public class Formulario_Insertar_Editar extends JFrame {
    
    private JPanel panel, panel_botones;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JLabel ins_l_nombre, ins_l_apellidos, ins_l_dni, ins_l_direccion, ins_l_telefono, ins_l_fecha, ins_l_especialista;
    public JTextField ins_t_nombre, ins_t_apellidos, ins_t_dni, ins_t_direccion, ins_t_telefono;
    public JComboBox ins_c_dia, ins_c_mes, ins_c_anho, ins_c_especialista;
    public JButton ins_b_aceptar, ins_b_cancelar;
    
    public Formulario_Insertar_Editar() {
        setTitle("Insertar");
        setSize(500, 350);
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
        ins_l_nombre = new JLabel("Nombre");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(ins_l_nombre, constraints);
        
        ins_l_apellidos = new JLabel("Apellidos");
        constraints.gridy = 1;
        panel.add(ins_l_apellidos, constraints);
        
        ins_l_dni = new JLabel("DNI");
        constraints.gridy = 2;
        panel.add(ins_l_dni, constraints);
        
        ins_l_direccion = new JLabel("Dirección");
        constraints.gridy = 3;
        panel.add(ins_l_direccion, constraints);
        
        ins_l_telefono = new JLabel("Teléfono");
        constraints.gridy = 4;
        panel.add(ins_l_telefono, constraints);
        
        ins_l_fecha = new JLabel("Fecha");
        constraints.gridy = 5;
        panel.add(ins_l_fecha, constraints);
        
        ins_l_especialista = new JLabel("Especialista");
        constraints.gridy = 6;
        constraints.ipadx = 20;
        panel.add(ins_l_especialista, constraints);
        
        
        /* ----TEXTFIELD---- */
        ins_t_nombre = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        panel.add(ins_t_nombre, constraints);
        
        ins_t_apellidos = new JTextField();
        constraints.gridy = 1;
        panel.add(ins_t_apellidos, constraints);
        
        ins_t_dni = new JTextField();
        constraints.gridy = 2;
        panel.add(ins_t_dni, constraints);
        
        ins_t_direccion = new JTextField();
        constraints.gridy = 3;
        panel.add(ins_t_direccion, constraints);
        
        ins_t_telefono = new JTextField();
        constraints.gridy = 4;
        panel.add(ins_t_telefono, constraints);
        
        
        /* ----COMBOBOX---- */
        ins_c_dia = new JComboBox();
        String[] dia = new String[32];   //Array que almacenará todos los días
        dia[0] = "Día";                  //El primer elemento se reserva para escribir "Día"
        for(int i = 1 ; i<=31 ; i++) {   //Y el buclo comienza en 1 porque el 0 ya está ocupado
            dia[i] = String.valueOf(i);
        }
        ins_c_dia.setModel(new DefaultComboBoxModel(dia)); //Se añaden todos los días al combo
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(ins_c_dia, constraints);
        
        ins_c_mes = new JComboBox();
        ins_c_mes.setModel(new DefaultComboBoxModel(new String[] {"Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
        constraints.gridx = 2;
        panel.add(ins_c_mes, constraints);
        
        ins_c_anho = new JComboBox();     //El combo del año siempre va a tener el año actual y el año siguiente, ya que no se van a dar citas para dentro de más de 1 año
        ins_c_anho.setModel(new DefaultComboBoxModel(new String[] {"Año", String.valueOf(LocalDate.now().getYear()), String.valueOf(LocalDate.now().getYear()+1)}));
        constraints.gridx = 3;
        panel.add(ins_c_anho, constraints);
        
        ins_c_especialista = new JComboBox();
        ins_c_especialista.setModel(new DefaultComboBoxModel(new String[] { "Oftalmólogo", "Odontólogo", "Otorrinolaringólogo", "Cardiólogo", "Dermatólogo", "Traumatólogo", "Reumatólogo", "Psiquiatra" }));
        constraints.gridy = 6;
        constraints.gridx = 1;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(ins_c_especialista, constraints);
        
        
        /* ----BOTONES---- */
        panel_botones = new JPanel();
        ins_b_aceptar = new JButton("Aceptar");
        ins_b_cancelar = new JButton("Cancelar");
        constraints.gridx = 3;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        
        //Les añado un "nombre" propio a cada botón para distinguir que boton he pulsado
        //ya que en todas las ventanas se llaman igual
        ins_b_aceptar.setActionCommand("ins_aceptar");
        ins_b_cancelar.setActionCommand("ins_cancelar");
        
        panel_botones.add(ins_b_aceptar);
        panel_botones.add(ins_b_cancelar);
        
        panel.add(panel_botones, constraints);
        

        setContentPane(panel);
    }
    
    public void conectarControlador(Controller c) {
        ins_b_aceptar.addActionListener(c);
        ins_b_cancelar.addActionListener(c);
    }

}
