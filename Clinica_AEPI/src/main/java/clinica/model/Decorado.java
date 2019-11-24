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
             case 2:
                try {
                UIManager.setLookAndFeel("UpperEssential.UpperEssentialLookAndFeel");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Vaya!..\nNo se pudo poner esta apariencia.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 3:
                try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Vaya!..\nNo se pudo poner esta apariencia.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 4:
                try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Vaya!..\nNo se pudo poner esta apariencia.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 5:
                try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Vaya!..\nNo se pudo poner esta apariencia.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 6:
                try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Vaya!..\nNo se pudo poner esta apariencia.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
         }
        
    }
}
