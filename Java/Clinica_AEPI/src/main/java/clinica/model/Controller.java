package clinica.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import clinica.bin.ConstValues;
import clinica.util.ConexionSQL;
import clinica.view.Eliminar;
import clinica.view.Formulario_Insertar_Editar;
import clinica.view.Visualizar;

public class Controller implements ActionListener {
    
    Formulario_Insertar_Editar formInsertEdit;
    Visualizar visualizar;
    Eliminar eliminar;
    ConexionSQL connection;
    private int idEditandose;
    
    public Controller(Formulario_Insertar_Editar formInsertEdit, Visualizar visualizar, Eliminar eliminar) {
        this.formInsertEdit = formInsertEdit;
        this.visualizar = visualizar;
        this.eliminar = eliminar;
    }
    

    public void mostrarDatosEnTabla() {
        try {
        	connection = new ConexionSQL();
            HashMap<Integer, ArrayList<String>> allData = connection.getAllData();
            for(int fila = 0 ; fila <allData.size() ; fila++) {
                for(int columna = 0 ; columna < allData.get(fila).size() ; columna++) {
                    visualizar.getTabla().setValueAt(allData.get(fila).get(columna), fila, columna);
                }
            }
        } catch(Exception e) {
        	System.out.println("Error: "+e.getMessage());
        }
    }
    
 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String boton = e.getActionCommand();
        switch(boton) {
            case "ins_aceptar":
            	if(formInsertEdit.getTitle().equals(ConstValues.TITULO_VENTANA_INSERTAR)) {
	                try { //Este try valida que la fecha introducida no sea incorrecta como por ejemplo un 30 de febrero
	                    LocalDate fecha = LocalDate.of(Integer.parseInt(String.valueOf(formInsertEdit.getFormComboAnho().getSelectedItem())), formInsertEdit.getFormComboMes().getSelectedIndex(), formInsertEdit.getFormComboDia().getSelectedIndex());
	                    if(0 > fecha.compareTo(LocalDate.now())){ //Se comprueba que la fecha introducida no sea anterior a la fecha actual
	                        JOptionPane.showMessageDialog(formInsertEdit, "La fecha introducida es anterior a la fecha actual", "ERROR", JOptionPane.ERROR_MESSAGE);
	                    } else {
	                        try {
	                        	connection = new ConexionSQL();
	                            if (connection.insertarClienteBBDD(formInsertEdit.getFormTextNombre().getText(), formInsertEdit.getFormTextApellidos().getText(), formInsertEdit.getFormTextDni().getText(), formInsertEdit.getFormTextDireccion().getText(), formInsertEdit.getFormTextTelefono().getText(), fecha.toString(), formInsertEdit.getFormComboEspecialista().getSelectedItem().toString())) { 
	                                JOptionPane.showMessageDialog(null, "Cliente insertado correctamente", ConstValues.TITULO_VENTANA_INSERTAR, JOptionPane.INFORMATION_MESSAGE);
	                                formInsertEdit.getFormTextNombre().setText("");
	                                formInsertEdit.getFormTextApellidos().setText("");
	                                formInsertEdit.getFormTextDni().setText("");
	                                formInsertEdit.getFormTextDireccion().setText("");
	                                formInsertEdit.getFormTextTelefono().setText("");
	                            }
	                        } catch (Exception ex) {
	                            System.out.println("Error: "+ex.toString());
	                        }
	                    }
	                } catch(Exception ex) {
	                    System.out.println("Funciona");
	                    JOptionPane.showMessageDialog(formInsertEdit, "La fecha introducida no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
	                }
            	} else {
            		
            		
            		
            		try { //Este try valida que la fecha introducida no sea incorrecta como por ejemplo un 30 de febrero
	                    LocalDate fecha = LocalDate.of(Integer.parseInt(String.valueOf(formInsertEdit.getFormComboAnho().getSelectedItem())), formInsertEdit.getFormComboMes().getSelectedIndex(), formInsertEdit.getFormComboDia().getSelectedIndex());
	                    if(0 > fecha.compareTo(LocalDate.now())){ //Se comprueba que la fecha introducida no sea anterior a la fecha actual
	                        JOptionPane.showMessageDialog(formInsertEdit, "La fecha introducida es anterior a la fecha actual", "ERROR", JOptionPane.ERROR_MESSAGE);
	                    } else {
	                        try {
	                        	connection = new ConexionSQL();
	                            if (connection.editarClienteBBDD(idEditandose, formInsertEdit.getFormTextNombre().getText(), formInsertEdit.getFormTextApellidos().getText(), formInsertEdit.getFormTextDni().getText(), formInsertEdit.getFormTextDireccion().getText(), formInsertEdit.getFormTextTelefono().getText(), fecha.toString(), formInsertEdit.getFormComboEspecialista().getSelectedItem().toString())) { 
	                                JOptionPane.showMessageDialog(null, "Cliente editado correctamente", ConstValues.TITULO_VENTANA_EDITAR, JOptionPane.INFORMATION_MESSAGE);
//	                                ins.setVisible(false);
	                                formInsertEdit.dispose();
	                            }
	                        } catch (Exception ex) {
	                            System.out.println("Error: "+ex.toString());
	                        }
	                    }
	                } catch(Exception ex) {
	                    JOptionPane.showMessageDialog(formInsertEdit, "La fecha introducida no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
	                }
            		
            		
            		
            	}
                break;
            case "ins_cancelar":
                formInsertEdit.setVisible(false);
                formInsertEdit.dispose();
                break;
            case "vis_aceptar":
                /*   Con esto se filtran los nombres en la tabla.
                No hace falta poner el nombre completo. Por ejemplo,
                si se busca 'Ma' podrían aparecer los siguientes nombres:
                    - María
                    - Mauricio
                    - Macarena
                      ... ...
                No sólo busca por nombre, si no también por apellidos, id, teléfono... cualquier campo.
                */
                DefaultTableModel model = (DefaultTableModel) visualizar.getTabla().getModel();
                TableRowSorter<DefaultTableModel> filtra = new TableRowSorter<>(model);
                visualizar.getTabla().setRowSorter(filtra);
                filtra.setRowFilter(RowFilter.regexFilter(visualizar.getVisualizarTextBuscar().getText()));
                break;
            case "vis_cancelar":
                visualizar.setVisible(false);
                visualizar.dispose();
                break;
            case "el_aceptar":
                eliminarCliente();
                break;
            case "el_cancelar":
                eliminar.setVisible(false);
                eliminar.dispose();
                break;
                
        }
    }
    
    public void rellenarFormulario(int id) {
    	idEditandose = id;
    	connection = new ConexionSQL();
    	Cliente cliente = connection.getClienteOfBBDD(id);
    	formInsertEdit.getFormTextNombre().setText(cliente.getNombre());
    	formInsertEdit.getFormTextApellidos().setText(cliente.getApellidos());
    	formInsertEdit.getFormTextDni().setText(cliente.getDni());
    	formInsertEdit.getFormTextDireccion().setText(cliente.getDireccion());
    	formInsertEdit.getFormTextTelefono().setText(cliente.getTelefono());
    	//2020-04-03
    	String[] fecha = cliente.getFecha().split("-");
    	formInsertEdit.getFormComboDia().setSelectedIndex(Integer.parseInt(fecha[2]));
    	formInsertEdit.getFormComboMes().setSelectedIndex(Integer.parseInt(fecha[1]));
    	formInsertEdit.getFormComboAnho().setSelectedItem(fecha[0]);
    	formInsertEdit.getFormComboEspecialista().setSelectedItem(cliente.getEspecialista());
    }
    
    public void eliminarCliente() {
    	connection = new ConexionSQL();
        try {
        	int id = Integer.parseInt(eliminar.getEliminarTextId().getText());
        	if(connection.comprobarSiExisteID(id)) {
        		connection.eliminarClienteBBDD(id);
                JOptionPane.showMessageDialog(formInsertEdit, "Cliente eliminado correctamente", ConstValues.TITULO_VENTANA_ELIMINAR, JOptionPane.INFORMATION_MESSAGE);
                eliminar.getEliminarTextId().setText("");
        	} else {
        		JOptionPane.showMessageDialog(formInsertEdit, "El ID introducido no existe", ConstValues.TITULO_VENTANA_ELIMINAR, JOptionPane.INFORMATION_MESSAGE);
        	}
        } catch(Exception ex) {
        	JOptionPane.showMessageDialog(formInsertEdit, "El ID introducido no es correcto.", ConstValues.TITULO_VENTANA_ELIMINAR, JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
