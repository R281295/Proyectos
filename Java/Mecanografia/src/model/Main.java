package model;

import controller.Controller;
import view.Ventana_Mecanografia;

public class Main {
    
    public static void main(String[] args) {
        
        Ventana_Mecanografia ventana_mecanografia = new Ventana_Mecanografia();
        Controller controller = new Controller(ventana_mecanografia);
        ventana_mecanografia.conectarControlador(controller);
        
    }
    
}
