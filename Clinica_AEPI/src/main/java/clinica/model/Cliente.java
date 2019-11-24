package clinica.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity(name = "CLINICA")
public class Cliente {
	
	@Id
	@GenericGenerator(name="auto-increment" , strategy="increment")
	@GeneratedValue(generator="auto-increment")
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "APELLIDOS")
	private String apellidos;
	
	@Column(name = "DNI")
	private String dni;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "FECHA")
	private String fecha;
	
	@Column(name = "ESPECIALISTA")
	private String especialista;
	
	public Cliente() {
		
	}

	public Cliente(String nombre, String apellidos, String dni, String direccion, String telefono, String fecha, String especialista) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.fecha = fecha;
		this.especialista = especialista;
	}
	
	public ArrayList<String> getClienteArray() {
		ArrayList<String> clienteArray = new ArrayList<>();
		clienteArray.add(String.valueOf(id));
		clienteArray.add(nombre);
		clienteArray.add(apellidos);
		clienteArray.add(dni);
		clienteArray.add(direccion);
		clienteArray.add(telefono);
		clienteArray.add(fecha);
		clienteArray.add(especialista);
		return clienteArray;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEspecialista() {
		return especialista;
	}

	public void setEspecialista(String especialista) {
		this.especialista = especialista;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", direccion="
				+ direccion + ", telefono=" + telefono + ", fecha=" + fecha + ", especialista=" + especialista + "]";
	}
	
	
	
}
