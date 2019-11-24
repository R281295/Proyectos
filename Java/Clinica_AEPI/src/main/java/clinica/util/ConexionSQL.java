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
    
    
    public HashMap<Integer, ArrayList<String>> getAllData() {
    	HashMap<Integer, ArrayList<String>> allData = new HashMap<>();
    	
    	EntityManager manager = ConstValues.EMF.createEntityManager();
    	
    	ArrayList<Cliente> clientes = (ArrayList<Cliente>) manager.createQuery("FROM "+ConstValues.CLINICA).getResultList();
    	
    	for(int i=0 ; i<clientes.size() ; i++) {
    		allData.put(i, clientes.get(i).getClienteArray());
    	}
    	
    	return allData;
    }
    
    public boolean insertarClienteBBDD(String nombre, String apellidos, String dni, String direccion, String telefono, String fecha, String especialista) {
    	boolean insertadoCorrectamente = false;
    	
    	try {
	    	EntityManager manager = ConstValues.EMF.createEntityManager();
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
    
    
    public boolean editarClienteBBDD(int id, String nombre, String apellidos, String dni, String direccion, String telefono, String fecha, String especialista) {
    	boolean editadoCorrectamente = false;
    	try {
	    	EntityManager manager = ConstValues.EMF.createEntityManager();
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
    
    
    public void eliminarClienteBBDD(int id) {
    	try {
    		EntityManager manager = ConstValues.EMF.createEntityManager();
	    	manager.getTransaction().begin();
	    	manager.remove(manager.find(Cliente.class, id));
	    	manager.getTransaction().commit();
    	}catch(Exception e) {
    		System.out.println("Error al eliminar cliente: "+e.getMessage());
    	}
    }
    

    public int cuentaRegistros() {
    	int filas = 0;
    	
    	EntityManager manager = ConstValues.EMF.createEntityManager();
    	filas = Integer.parseInt(manager.createQuery("SELECT COUNT(*) FROM "+ConstValues.CLINICA).getResultList().get(0).toString());
    	
        return filas;
    }
    
    public Cliente getClienteOfBBDD(int id) {
    	EntityManager manager = ConstValues.EMF.createEntityManager();
    	Cliente cliente = manager.find(Cliente.class, id);
    	return cliente;
    }
    
    public boolean comprobarSiExisteID(int id) {
    	boolean existe = false;
    	EntityManager manager = ConstValues.EMF.createEntityManager();
    	Cliente cliente = manager.find(Cliente.class, id);
    	if(cliente != null) {
    		existe = true;
    	}
    	return existe;
    }
    
}
