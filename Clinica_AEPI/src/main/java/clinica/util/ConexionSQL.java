package clinica.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;

import clinica.bin.ConstValues;
import clinica.model.Cliente;

public class ConexionSQL {
    
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    
    public ConexionSQL() {
    	
    }
    
    public String Visualizar(int ID, int COL) {
    	String[] datos = new String[7];
    	try {
    		conn = DriverManager.getConnection("jdbc:sqlite:data/clinica.db");
	        String query = "SELECT * FROM CLINICA WHERE ID = "+ID;
	        pst = conn.prepareStatement(query);
	        rs = pst.executeQuery();
	        
	        if(rs.next()) {
	        	if(rs != null) {
		            datos[0] = rs.getString(2);
		            datos[1] = rs.getString(3);
		            datos[2] = rs.getString(4);
		            datos[3] = rs.getString(5);
		            datos[4] = rs.getString(6);
		            datos[5] = rs.getString(7);
		            datos[6] = rs.getString(8);
	        	}
	        }
	        rs.close();
	        pst.close();
	        conn.close();
    	}catch(Exception e) {
    		System.out.println("Error al visualizar los datos: "+e.getMessage());
    	}
    	return datos[COL];
        
    }
    
    public HashMap<Integer, ArrayList<String>> getAllData() {
    	HashMap<Integer, ArrayList<String>> allData = new HashMap<>();
    	
    	EntityManager manager = ConstValues.emf.createEntityManager();
    	
    	ArrayList<Cliente> clientes = (ArrayList<Cliente>) manager.createQuery("FROM CLINICA").getResultList();
    	
    	for(int i=0 ; i<clientes.size() ; i++) {
    		allData.put(i, clientes.get(i).getClienteArray());
    	}
    	
    	return allData;
    }
    
    public boolean Insertar(String nombre, String apellidos, String dni, String direccion, String telefono, String fecha, String especialista) {
    	boolean insertadoCorrectamente = false;
    	
    	try {
	    	EntityManager manager = ConstValues.emf.createEntityManager();
	    	Cliente cliente = new Cliente(nombre, apellidos, dni, direccion, telefono, fecha, especialista);
	    	
	    	manager.getTransaction().begin();
	    	manager.persist(cliente);
	    	manager.getTransaction().commit();
	    	
	    	manager.close();
	    	
	    	insertadoCorrectamente = true;
    	}catch(Exception e) {
    		System.out.println("Error al insertar cliente: "+e.getMessage());
    	}
    	
    	return insertadoCorrectamente;
    }
    
    
    public boolean Editar(int id, String nombre, String apellidos, String dni, String direccion, String telefono, String fecha, String especialista) {
    	boolean editadoCorrectamente = false;
    	try {
	    	EntityManager manager = ConstValues.emf.createEntityManager();
	    	Cliente cliente = manager.find(Cliente.class, id);
	    	
	    	manager.getTransaction().begin();
	    	cliente.setNombre(nombre);
	    	cliente.setApellidos(apellidos);
	    	cliente.setDni(dni);
	    	cliente.setDireccion(direccion);
	    	cliente.setTelefono(telefono);
	    	cliente.setFecha(fecha);
	    	cliente.setEspecialista(especialista);
	    	manager.getTransaction().commit();
	    	
	    	manager.close();
	    	
	    	editadoCorrectamente = true;
    	}catch(Exception e) {
    		System.out.println("Error al editar cliente: "+e.getMessage());
    	}
    	return editadoCorrectamente;
    }
    
    
    public void Eliminar(int id) {
    	try {
//	        conn = DriverManager.getConnection("jdbc:sqlite:data/clinica.db");
//	        
//	        String query = "DELETE FROM CLINICA WHERE ID = '"+id+"'";
//	        PreparedStatement pst = conn.prepareStatement(query);
//	        pst.executeUpdate();
//	        
//	        pst.close();
//	        conn.close();
	        
	        
    		EntityManager manager = ConstValues.emf.createEntityManager();
	    	manager.getTransaction().begin();
	    	manager.remove(manager.find(Cliente.class, id));
	    	manager.getTransaction().commit();
	        
	        
    	}catch(Exception e) {
    		System.out.println("Error al eliminar cliente: "+e.getMessage());
    	}
    }
    

    public int cuentaRegistros() {
    	int filas = 0;
    	try {
	        conn = DriverManager.getConnection("jdbc:sqlite:data/clinica.db");
	        String query = "SELECT COUNT(*) FROM CLINICA";
	        PreparedStatement pst = conn.prepareStatement(query);
	        ResultSet rs = pst.executeQuery();
	        filas = rs.getInt(1);
	        rs.close();
	        pst.close();
	        conn.close();
    	}catch(Exception e) {
    		System.out.println("Error al contar filas: "+e.getMessage());
    	}
        return filas;
    }
    
    public Cliente getCliente(int id) {
    	EntityManager manager = ConstValues.emf.createEntityManager();
    	Cliente cliente = manager.find(Cliente.class, id);
    	return cliente;
    }
    
    public boolean comprobarSiExisteID(int id) {
    	boolean existe = false;
    	EntityManager manager = ConstValues.emf.createEntityManager();
    	Cliente cliente = manager.find(Cliente.class, id);
    	if(cliente != null) {
    		existe = true;
    	}
    	return existe;
    }
    
}
