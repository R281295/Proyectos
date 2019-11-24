package clinica.model;

import javax.swing.UIManager;

import clinica.view.Eliminar;
import clinica.view.Formulario_Insertar_Editar;
import clinica.view.Menu;
import clinica.view.Visualizar;


public class Main {
    
    
    public static void main(String[] args) {
        
        new Decorado();
        
        new Menu();
        
        
        Formulario_Insertar_Editar ins = new Formulario_Insertar_Editar();
        Visualizar vis = new Visualizar();
        Eliminar el = new Eliminar();
        
        Controller controler = new Controller(ins, vis, el);
        ins.conectarControlador(controler);
        vis.conectarControlador(controler);
        el.conectarControlador(controler);
        
        
        
    }
    
}
