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
    
    Formulario_Insertar_Editar ins;
    Visualizar vis;
    Eliminar el;
    ConexionSQL sql;
    private int idEditandose;
    
    public Controller(Formulario_Insertar_Editar ins, Visualizar vis, Eliminar el) {
        this.ins = ins;
        this.vis = vis;
        this.el = el;
    }
    

    public void mostrarDatosEnTabla() {
        try {
            sql = new ConexionSQL();
            HashMap<Integer, ArrayList<String>> allData = sql.getAllData();
            for(int fila = 0 ; fila <allData.size() ; fila++) {
                for(int columna = 0 ; columna < allData.get(fila).size() ; columna++) {
                    vis.vis_table.setValueAt(allData.get(fila).get(columna), fila, columna);
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
            	if(ins.getTitle().equals(ConstValues.tituloVentanaInsertar)) {
	                try { //Este try valida que la fecha introducida no sea incorrecta como por ejemplo un 30 de febrero
	                    LocalDate fecha = LocalDate.of(Integer.parseInt(String.valueOf(ins.ins_c_anho.getSelectedItem())), ins.ins_c_mes.getSelectedIndex(), ins.ins_c_dia.getSelectedIndex());
	                    if(0 > fecha.compareTo(LocalDate.now())){ //Se comprueba que la fecha introducida no sea anterior a la fecha actual
	                        JOptionPane.showMessageDialog(ins, "La fecha introducida es anterior a la fecha actual", "ERROR", JOptionPane.ERROR_MESSAGE);
	                    } else {
	                        try {
	                            sql = new ConexionSQL();
	                            if (sql.Insertar(ins.ins_t_nombre.getText(), ins.ins_t_apellidos.getText(), ins.ins_t_dni.getText(), ins.ins_t_direccion.getText(), ins.ins_t_telefono.getText(), fecha.toString(), ins.ins_c_especialista.getSelectedItem().toString())) { 
	                                JOptionPane.showMessageDialog(null, "Cliente insertado correctamente", ConstValues.tituloVentanaInsertar, JOptionPane.INFORMATION_MESSAGE);
	                                ins.ins_t_nombre.setText("");
	                                ins.ins_t_apellidos.setText("");
	                                ins.ins_t_dni.setText("");
	                                ins.ins_t_direccion.setText("");
	                                ins.ins_t_telefono.setText("");
	                            }
	                        } catch (Exception ex) {
	                            System.out.println("Error: "+ex.toString());
	                        }
	                    }
	                } catch(Exception ex) {
	                    System.out.println("Funciona");
	                    JOptionPane.showMessageDialog(ins, "La fecha introducida no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
	                }
            	} else {
            		
            		
            		
            		try { //Este try valida que la fecha introducida no sea incorrecta como por ejemplo un 30 de febrero
	                    LocalDate fecha = LocalDate.of(Integer.parseInt(String.valueOf(ins.ins_c_anho.getSelectedItem())), ins.ins_c_mes.getSelectedIndex(), ins.ins_c_dia.getSelectedIndex());
	                    if(0 > fecha.compareTo(LocalDate.now())){ //Se comprueba que la fecha introducida no sea anterior a la fecha actual
	                        JOptionPane.showMessageDialog(ins, "La fecha introducida es anterior a la fecha actual", "ERROR", JOptionPane.ERROR_MESSAGE);
	                    } else {
	                        try {
	                            sql = new ConexionSQL();
	                            if (sql.Editar(idEditandose, ins.ins_t_nombre.getText(), ins.ins_t_apellidos.getText(), ins.ins_t_dni.getText(), ins.ins_t_direccion.getText(), ins.ins_t_telefono.getText(), fecha.toString(), ins.ins_c_especialista.getSelectedItem().toString())) { 
	                                JOptionPane.showMessageDialog(null, "Cliente editado correctamente", ConstValues.tituloVentanaEditar, JOptionPane.INFORMATION_MESSAGE);
//	                                ins.setVisible(false);
	                                ins.dispose();
	                            }
	                        } catch (Exception ex) {
	                            System.out.println("Error: "+ex.toString());
	                        }
	                    }
	                } catch(Exception ex) {
	                    JOptionPane.showMessageDialog(ins, "La fecha introducida no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
	                }
            		
            		
            		
            	}
                break;
            case "ins_cancelar":
                ins.setVisible(false);
                ins.dispose();
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
                DefaultTableModel model = (DefaultTableModel) vis.vis_table.getModel();
                TableRowSorter<DefaultTableModel> filtra = new TableRowSorter<>(model);
                vis.vis_table.setRowSorter(filtra);
                filtra.setRowFilter(RowFilter.regexFilter(vis.vis_t_nombre.getText()));
                break;
            case "vis_cancelar":
                vis.setVisible(false);
                vis.dispose();
                break;
            case "el_aceptar":
                try {
                    sql = new ConexionSQL();
                    try {
                    	int id = Integer.parseInt(el.el_t_id.getText());
                    	if(sql.comprobarSiExisteID(id)) {
	                    	sql.Eliminar(id);
	                        JOptionPane.showMessageDialog(ins, "Cliente eliminado correctamente", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
	                        el.el_t_id.setText("");
                    	} else {
                    		System.out.println("El ID introducido no existe"); //TODO JOptionPane
                    	}
                    } catch(Exception ex) {
                    	System.out.println("El ID introducido no es correcto."); //TODO JOptionPane
                    }
                } catch (Exception ex) {
                    System.out.println("Error desconocido: "+ex.toString());
                }
                break;
            case "el_cancelar":
                el.setVisible(false);
                el.dispose();
                break;
                
        }
    }
    
    public void rellenarFormulario(int id) {
    	idEditandose = id;
    	sql = new ConexionSQL();
    	Cliente cliente = sql.getCliente(id);
    	ins.ins_t_nombre.setText(cliente.getNombre());
    	ins.ins_t_apellidos.setText(cliente.getApellidos());
    	ins.ins_t_dni.setText(cliente.getDni());
    	ins.ins_t_direccion.setText(cliente.getDireccion());
    	ins.ins_t_telefono.setText(cliente.getTelefono());
    	//2020-04-03
    	String[] fecha = cliente.getFecha().split("-");
    	ins.ins_c_dia.setSelectedIndex(Integer.parseInt(fecha[2]));
    	ins.ins_c_mes.setSelectedIndex(Integer.parseInt(fecha[1]));
    	ins.ins_c_anho.setSelectedItem(fecha[0]);
    	ins.ins_c_especialista.setSelectedItem(cliente.getEspecialista());
    }

}
