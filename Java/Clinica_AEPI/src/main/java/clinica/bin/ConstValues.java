package clinica.bin;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConstValues {
	
	public final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");
	
	public final static String[] camposCliente = {	"ID",
													"Nombre",
													"Apellidos",
													"DNI",
													"Dirección",
													"Teléfono",
													"Fecha",
													"Especialista"};

	public final static String tituloVentanaVisualizar = "Visualizar";
	public final static String tituloVentanaInsertar = "Insertar";
	public final static String tituloVentanaEditar = "Editar";
	public final static String tituloVentanaEliminar = "Eliminar";

}
