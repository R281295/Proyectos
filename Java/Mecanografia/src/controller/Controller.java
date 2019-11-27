package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

import model.Cronometro;
import view.Ventana_Mecanografia;

public class Controller implements KeyListener, ActionListener {
    
    private Ventana_Mecanografia ventana_mecanografia;
    private Cronometro cronometro;
    private Thread hiloCrono;
    private boolean cronoActivo = false;
    private int cont = 0;
    private int errores = 0;
    private int pulsacionesTotales = 0;
    
    public Controller(Ventana_Mecanografia ventana_mecanografia) {
        this.ventana_mecanografia = ventana_mecanografia;
    }
    
    
    @Override
    public void keyPressed(KeyEvent evt) {
        
    }
    
    @Override
    public void keyTyped(KeyEvent evt) {
        
        pulsacionesTotales++;
        
        if(cronoActivo == false) {
            cronometro = new Cronometro(ventana_mecanografia);
            cronometro.setActivo(true);
            hiloCrono = new Thread(cronometro);
            hiloCrono.start();
            cronoActivo = true;
        }
        
          try {
            //El siguiente if controla que la letra que se haya pulsado sea la correcta
            if(evt.getKeyChar() == ventana_mecanografia.getTexto().getText(cont, 1).charAt(0)) {
                ventana_mecanografia.getEscritura().setEditable(true);
                cont++; //Sirve para contar los caracteres introducidos, y detectar cuando se ha terminado de escribir todo el texto
                escribeLabel();
            } else {
                    errores++;
                    ventana_mecanografia.getEscritura().setEditable(false);
            }   
        } catch (Exception e) {
            System.out.println("Error al leer caracteres del texto: "+e.getMessage());
        }
          
        //El siguiente if detecta cuando se ha terminado de escribir todo el texto
        if(ventana_mecanografia.getTexto().getText().length()-1 == cont) {
            //Se para el cronómetro.
            if(cronoActivo == true) {
                cronometro.setActivo(false);
                cronoActivo = false;
            }
            try {
                Thread.sleep(1000); //Esperamos un segundo porque al estar usando el teclado, se le pueda dar a Aceptar del JOptionPane con la barra espaciadora o intro sin querer.
            } catch(Exception e) {
                JOptionPane.showMessageDialog(ventana_mecanografia, "Error en Thread.sleep: "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(ventana_mecanografia, "Tiempo: "+ventana_mecanografia.getTiempo().getText()+"\nPulsaciones: "+pulsacionesTotales+"\nP.P.M.: "+((pulsacionesTotales-errores)*60/(cronometro.getSegundos()+cronometro.getMinutos()*60))+"\nErrores: "+errores*100/pulsacionesTotales+"% ("+errores+")", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
            pulsacionesTotales = 0;
        }    
    }
    
    @Override
    public void keyReleased(KeyEvent evt) {
        //Se borra el cuadro de texto cada vez que se escribe un espacio o un enter
        if(ventana_mecanografia.getEscritura().isEditable() && (evt.getKeyChar() == ' ' || evt.getKeyChar() == '\n')) {
            ventana_mecanografia.getEscritura().setText("");
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getActionCommand() == "Random") {
            String[] archivos = new File("random").list(); //Obtiene todos los archivos que se encuentran en la carpeta random
            int numeroArchivos = archivos.length; //Cuenta cuántos archivos son
            int rand = (int)(Math.random()*numeroArchivos+1); //Genera un numero aleatorio entre el 1 y el número de archivos total
            leerArchivo("random/"+rand);
            ventana_mecanografia.setTitle("Random: "+rand);
        } else {
            leerArchivo("textos/"+evt.getActionCommand());
            ventana_mecanografia.setTitle(evt.getActionCommand());
        }
    }
    
    
    
    public void leerArchivo(String nombreArchivo) {
        ventana_mecanografia.getTexto().setText("");
        ventana_mecanografia.getEscritura().setText("");
        ventana_mecanografia.getTiempo().setText("00:00");
        cont = 0;
        errores = 0;
        
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
            archivo = new File(nombreArchivo+".txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while((linea  = br.readLine()) != null) {
                ventana_mecanografia.getTexto().append(linea);
                ventana_mecanografia.getTexto().append("\n");
            }
            escribeLabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar/leer el archivo\n"+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                fr.close();
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo\n"+e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    
    
    
    //Se va escribiendo en el Label el texto, indicando la letra que se tiene que pulsar
    public void escribeLabel() {
        if("<".equals(ventana_mecanografia.getTexto().getText().substring(cont, cont+1))) {
            cont--;
        }
        String principio = "<html><body>"+ventana_mecanografia.getTexto().getText().substring(0, cont);
        String letra = "<font style=\"background-color:#FF3333\">"+ventana_mecanografia.getTexto().getText().substring(cont, cont+1)+"</font>";
        String final_ = ventana_mecanografia.getTexto().getText().substring(cont+1, ventana_mecanografia.getTexto().getText().length())+"</body></html>";
        ventana_mecanografia.getTexto_label().setText(principio+letra+final_);
    }
    
}
