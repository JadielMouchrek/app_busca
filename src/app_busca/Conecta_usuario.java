/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;

import Telas.Tela_Acesso;
import Telas.Tela_Principal;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Jadiel
 */
public class Conecta_usuario {
    

    public int acessar_app(JTextField login_acesso, JPasswordField senha_acesso) throws SQLException {
            
        Conexao fab= new Conexao();
             fab.abrirConexao();
             
             int test_dispose = 0 ;
             Tela_Principal principal = new Tela_Principal();
             
             fab.stmt = fab.con.createStatement();
             
             String sql = "select *from usuario where usuario.login ='"+login_acesso.getText()+"'";
             fab.rs = fab.stmt.executeQuery(sql);
             fab.rs.first();
             
             try{
             if(login_acesso.getText().equals(fab.rs.getString("login"))
                && senha_acesso.getText().equals(fab.rs.getString("senha"))){
                 
                
                  JOptionPane.showMessageDialog(null,"Acesso Permitido");    
                 
                 principal.show();
                 new Tela_Acesso().dispose();
                 
                  String usuarioLogado = fab.rs.getString("login");  
                  int id_usuario =fab.rs.getInt("id_usuario");
                  principal.log_usuario.setText("Seja bem-vindo, "+usuarioLogado);  
                  principal.id_usuario_label.setText(String.format("", id_usuario));
                  
                  principal.lista_dados_historico(id_usuario);
                        System.out.println("Teste0");
          
                           test_dispose = 1;       
                          
             }else{
                 JOptionPane.showMessageDialog(null, "Login e/ou Senha Incorreta");
                 
                
                 
             }
             
               } catch (Exception e) {

                  System.out.println(e.getMessage());

            }
             
        return test_dispose;
             
        
    
    }
    
}
