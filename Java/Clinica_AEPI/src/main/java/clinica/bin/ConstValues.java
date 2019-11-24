package clinica.bin;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConstValues {
	
	public final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("Persistence");
	
	/**
	 * Nombre de la tabla de BBDD
	 */
	public final static String CLINICA = "CLINICA";
	
	public final static String[] CAMPOS_CLIENTE = {	"ID",
													"Nombre",
													"Apellidos",
													"DNI",
													"Dirección",
													"Teléfono",
													"Fecha",
													"Especialista"};

	public final static String TITULO_VENTANA_VISUALIZAR = "Visualizar";
	public final static String TITULO_VENTANA_INSERTAR = "Insertar";
	public final static String TITULO_VENTANA_EDITAR = "Editar";
	public final static String TITULO_VENTANA_ELIMINAR = "Eliminar";

}
