package clinica.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import javax.swing.*;

import clinica.bin.ConstValues;
import clinica.model.Controller;

public class Formulario_Insertar_Editar extends JFrame {
    
    private JPanel panel, panel_botones;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JLabel formLabelNombre, formLabelApellidos, formLabelDni, formLabelDireccion, formLabelTelefono, formLabelFecha, formLabelEspecialista;
    private JTextField formTextNombre, formTextApellidos, formTextDni, formTextDireccion, formTextTelefono;
    private JComboBox formComboDia, formComboMes, formComboAnho, formComboEspecialista;
    private JButton formBtnAceptar, formBtnCancelar;
    
    public Formulario_Insertar_Editar() {
//        setTitle(ConstValues.TITULO_VENTANA_INSERTAR);
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
        formLabelNombre = new JLabel("Nombre");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(formLabelNombre, constraints);
        
        formLabelApellidos = new JLabel("Apellidos");
        constraints.gridy = 1;
        panel.add(formLabelApellidos, constraints);
        
        formLabelDni = new JLabel("DNI");
        constraints.gridy = 2;
        panel.add(formLabelDni, constraints);
        
        formLabelDireccion = new JLabel("Dirección");
        constraints.gridy = 3;
        panel.add(formLabelDireccion, constraints);
        
        formLabelTelefono = new JLabel("Teléfono");
        constraints.gridy = 4;
        panel.add(formLabelTelefono, constraints);
        
        formLabelFecha = new JLabel("Fecha");
        constraints.gridy = 5;
        panel.add(formLabelFecha, constraints);
        
        formLabelEspecialista = new JLabel("Especialista");
        constraints.gridy = 6;
        constraints.ipadx = 20;
        panel.add(formLabelEspecialista, constraints);
        
        
        /* ----TEXTFIELD---- */
        formTextNombre = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        panel.add(formTextNombre, constraints);
        
        formTextApellidos = new JTextField();
        constraints.gridy = 1;
        panel.add(formTextApellidos, constraints);
        
        formTextDni = new JTextField();
        constraints.gridy = 2;
        panel.add(formTextDni, constraints);
        
        formTextDireccion = new JTextField();
        constraints.gridy = 3;
        panel.add(formTextDireccion, constraints);
        
        formTextTelefono = new JTextField();
        constraints.gridy = 4;
        panel.add(formTextTelefono, constraints);
        
        
        /* ----COMBOBOX---- */
        formComboDia = new JComboBox();
        String[] dia = new String[32];   //Array que almacenará todos los días
        dia[0] = "Día";                  //El primer elemento se reserva para escribir "Día"
        for(int i = 1 ; i<=31 ; i++) {   //Y el buclo comienza en 1 porque el 0 ya está ocupado
            dia[i] = String.valueOf(i);
        }
        formComboDia.setModel(new DefaultComboBoxModel(dia)); //Se añaden todos los días al combo
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(formComboDia, constraints);
        
        formComboMes = new JComboBox();
        formComboMes.setModel(new DefaultComboBoxModel(new String[] {"Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
        constraints.gridx = 2;
        panel.add(formComboMes, constraints);
        
        formComboAnho = new JComboBox();     //El combo del año siempre va a tener el año actual y el año siguiente, ya que no se van a dar citas para dentro de más de 1 año
        formComboAnho.setModel(new DefaultComboBoxModel(new String[] {"Año", String.valueOf(LocalDate.now().getYear()), String.valueOf(LocalDate.now().getYear()+1)}));
        constraints.gridx = 3;
        panel.add(formComboAnho, constraints);
        
        formComboEspecialista = new JComboBox();
        formComboEspecialista.setModel(new DefaultComboBoxModel(new String[] { "Oftalmólogo", "Odontólogo", "Otorrinolaringólogo", "Cardiólogo", "Dermatólogo", "Traumatólogo", "Reumatólogo", "Psiquiatra" }));
        constraints.gridy = 6;
        constraints.gridx = 1;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(formComboEspecialista, constraints);
        
        
        /* ----BOTONES---- */
        panel_botones = new JPanel();
        formBtnAceptar = new JButton("Aceptar");
        formBtnCancelar = new JButton("Cancelar");
        constraints.gridx = 3;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        
        //Les añado un "nombre" propio a cada botón para distinguir que boton he pulsado
        //ya que en todas las ventanas se llaman igual
        formBtnAceptar.setActionCommand("ins_aceptar");
        formBtnCancelar.setActionCommand("ins_cancelar");
        
        panel_botones.add(formBtnAceptar);
        panel_botones.add(formBtnCancelar);
        
        panel.add(panel_botones, constraints);
        

        setContentPane(panel);
    }
    
    public void conectarControlador(Controller c) {
    	formBtnAceptar.addActionListener(c);
    	formBtnCancelar.addActionListener(c);
    }
    
    public JTextField getFormTextNombre() {
    	return formTextNombre;
    }
    
	public JTextField getFormTextApellidos() {
		return formTextApellidos;
	}
	
	public JTextField getFormTextDni() {
		return formTextDni;
	}
	
	public JTextField getFormTextDireccion() {
		return formTextDireccion;
	}
	
	public JTextField getFormTextTelefono() {
		return formTextTelefono;
	}
	
	public JComboBox<String> getFormComboDia() {
		return formComboDia;
	}
	
	public JComboBox<String> getFormComboMes() {
		return formComboMes;
	}
	
	public JComboBox<String> getFormComboAnho() {
		return formComboAnho;
	}
	
	public JComboBox<String> getFormComboEspecialista() {
		return formComboEspecialista;
	}

}
