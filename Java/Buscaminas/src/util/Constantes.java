package util;

public class Constantes {
	
	public static int anchoTabla = 8;
	public static int altoTabla = 8;
	public static int casillasTotales = anchoTabla * altoTabla;
	public static int numeroMinas = 10;

	public final static String MENU_NUEVO = "Nuevo";
	public final static String MENU_CAMBIAR_DIFICULTAD= "Cambiar dificultad";
	public final static String MENU_FACIL = "Facil";
	public final static String MENU_MEDIO = "Medio";
	public final static String MENU_DIFICIL = "Dificil";
	public final static String MENU_PERSONALIZADO = "Personalizado...";
	public final static String MENU_ESTADISTICAS = "Estadisticas";
	public final static String MENU_SALIR = "Salir";
	public final static String MENU_PREFERENCIAS = "Preferencias";
	public final static String MENU_CAMBIAR_IMAGEN = "Cambiar imagenes";

	public final static String TEXTO_LBL_MINAS_RESTANTES = "Minas restantes: ";
	
	public final static String[] IMG_CASILLAS = {"images/cero.png",
										"images/uno.png",
										"images/dos.png",
										"images/tres.png",
										"images/cuatro.png",
										"images/cinco.png",
										"images/seis.png",
										"images/siete.png",
										"images/ocho.png",
										"images/bandera.png",
										"images/mina.png"};
	
	public final static String JSON = "resources/estadisticas.json";
}
