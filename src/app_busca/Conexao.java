/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jadiel
 */
public class Conexao {
    public Connection con;
    public Statement stmt;
    public ResultSet rs;
     
    String url = "jdbc:mysql://localhost:3306/app_busca?useTimezone=true&serverTimezone=UTC";
    String driver  = "com.mysql.cj.jdbc.Driver";
    String user = "root";
    String password = "12345";
    
    public Connection abrirConexao(){
        
          con = null;
        try{
        Class.forName(driver);
                con = DriverManager.getConnection(url, user, password);
         stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch (Exception e){ System.out.println(e);
        }
       return con;
    }
    
   public static void main (String[] Args){
        
          Conexao fab = new Conexao();
        fab.abrirConexao();
    }
   
     
}
