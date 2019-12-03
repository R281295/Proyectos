package model;

import view.Ventana_Mecanografia;

public class Cronometro implements Runnable {
    
    private Ventana_Mecanografia ventana_mecanografia;
    private boolean activo = false;
    private int segundos = 0;
    private int minutos = 0;
    
    public Cronometro(Ventana_Mecanografia ventana_mecanografia) {
        this.ventana_mecanografia = ventana_mecanografia;
    }
    
    @Override
    public void run() {
        try {
            while(activo) {
                Thread.sleep(1000);
                segundos++;
                if(segundos == 60) {
                    minutos++;
                    segundos = 0;
                }
                ventana_mecanografia.getTiempo().setText(minutos+":"+segundos);
            }
        } catch(Exception e) {
            System.out.println("Error con el cronometro: "+e.toString());
        }
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public int getSegundos() {
    	return segundos;
    }
    
    public int getMinutos() {
    	return minutos;
    }
    
    
    
}
