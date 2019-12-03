package clinica.model;

import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class Decorado {
    
    
    public void decorado(int i) {
         switch(i) {
             case 0:
                try {
                	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Vaya!..\nNo se pudo poner esta apariencia.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
             case 1:
                try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Vaya!..\nNo se pudo poner esta apariencia.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
         }
        
    }
}
