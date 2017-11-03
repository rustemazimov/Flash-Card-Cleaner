/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flash.card.cleaner;

import com.sun.java.swing.plaf.windows.resources.windows;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rustem
 */
public class FlashCardCleaner {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new FlashCardCleaner();
    }
    private FlashCardCleaner(){
        MyFrame window = new MyFrame("Cleaner");
        window.setVisible(true);
        window.addActionListenerToCleanButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rootName = window.getSelectedRoot();
                if((!rootName.equals("No Removable Disk")) && window.rootExists(rootName))
                {
                    executeCommand(rootName);
                }
            }
        });
    }
    private void executeCommand(String rootName){
        try {
            new ProcessBuilder("cmd.exe", "/c", "attrib -h -r -s /s /d " +  rootName + "*.*").start();
            showMessage("The Flash Card was cleaned", "Info");
        } catch (IOException ex) {
            showMessage("Contact us", "Error");
        }
    }
    private void showMessage(String txt, String title){
        JOptionPane.showMessageDialog(null, txt, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
