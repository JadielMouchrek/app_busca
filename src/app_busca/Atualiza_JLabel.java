/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;

import Telas.Tela_Principal;
import javax.swing.JLabel;

/**
 *
 * @author Jadiel
 */
public class Atualiza_JLabel {
    
     public static void start(){  
        Thread atualizaHora = new Thread(new Runnable() {

            public void run() {
                try {
                    while (true) {
                     
                        Tela_Principal atualiza = new Tela_Principal();
                        
                       atualiza.aviso.setText("Pesquisando...");
                        Thread.sleep(1000);
                        atualiza.aviso.revalidate();
                    }
                } catch (InterruptedException ex) {
                }   }
        });
  
        atualizaHora.start();  
    }  
    
}
