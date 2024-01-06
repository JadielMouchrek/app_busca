/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;

import Telas.Tela_Principal;

import javax.swing.*;
import java.io.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 *
 * @author Jadiel
 */
public class Extrai_txt {
    
    public int Text(int id_usuario) throws SQLException, FileNotFoundException, UnsupportedEncodingException, IOException{
        
         System.out.println("Teste3");
        
           Conexao fab = new Conexao();
           fab.abrirConexao();
           
              long agora = System.currentTimeMillis();

               Date dtAgora = new Date (agora);
               
            final Calendar calendar = Calendar.getInstance();  
              calendar.setTime(dtAgora);  
  
            int hour = calendar.get(Calendar.HOUR_OF_DAY);  
            int minute = calendar.get(Calendar.MINUTE);  
            int second = calendar.get(Calendar.SECOND);

               String  hora;
               
              int contador = 0;
               
              DecimalFormat df = new DecimalFormat("00");
              
            
        hora = df.format(hour)+":"+df.format(minute)+":"+df.format(second);
                  
                
                 
                 
                 
             String sql = "insert into local_arquivo(diretorio, data_listagem, hora, id_usuario)values(?,?,?,?)";
             
             PreparedStatement ps = fab.con.prepareStatement(sql);
            
             String local_arq = "C:\\Users\\Jadiel\\Documents\\GitHub\\app_busca\\txtFile\\Teste_PDF.txt";
             
              DecimalFormat df2 = new DecimalFormat("");
           
        // loop que lê e imprime todas as linhas do arquivo
        try ( /* File file = new File(local_arq);
        try {
        Scanner arq = new Scanner(file);
        while (arq.hasNextLine()) {  */ /* Scanner in = new Scanner(new FileReader(local_arq)) {
        while (in.hasNextLine()) {
        String linha = in.nextLine();*/ BufferedReader myBuffer = new BufferedReader(new InputStreamReader(new FileInputStream(local_arq), "cp850"))) {
            // loop que lê e imprime todas as linhas do arquivo
            String linha = myBuffer.readLine();
            
            while (linha != null) {
                
                /*ps.setString(1,"C:\\Users\\Jadiel\\Desktop\\Pendrive\\Monografia\\Aplicação\\Teste_PDF\\" + linha);*/
 
                ps.setString(1,linha);
                ps.setDate(2, dtAgora);
                ps.setString(3,hora );
                ps.setInt(4,id_usuario);
                System.out.println(linha);
                linha = myBuffer.readLine();
                if (ps.executeUpdate() > 0) {
                    contador = contador +1;
                    System.out.println("Quantidade de Arquivos Inseridos: " + contador);
                    
                    Tela_Principal cont = new Tela_Principal();
                    
                    cont.setTexto("Quantidade de arquivos:" + df2.format(contador));
               
                    
                } else {
                    
                    System.out.println("Erro ao inerir");
                    
                    JOptionPane.showMessageDialog(null, "Erro ao Inserir");
                    
                    break;
                    
                }
            }
            myBuffer.close();
        }
        return contador;
        } 
            
            
        } 
       

    
  
   
  
    

