/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jadiel
 */
public class Lista_dados_principal { 
     public List<Lista_principal> lista_dados(int id_usuario){
       
        Conexao fab = new Conexao();
        fab.abrirConexao();
        
         String sql = "select data_listagem, diretorio, hora from local_arquivo where local_arquivo.id_usuario ="+id_usuario+"";

        List<Lista_principal> lista_arquivos_dados = new ArrayList<Lista_principal>();

        try {
             fab.rs = fab.stmt.executeQuery(sql);
             /*fab.rs.first();*/

            if (fab.rs != null) {
                while (fab.rs.next()) {
                    Lista_principal lista = new Lista_principal();
                    lista.setdata_listagem(fab.rs.getDate(1));
                    lista.setlocal_arquivo(fab.rs.getString(2)); 
                    lista.sethora(fab.rs.getString(3)); 
                    lista_arquivos_dados.add(lista);
                }

                return lista_arquivos_dados;

            } else {

                return null;

            }
        } catch (SQLException e) {

             System.out.println(e.getMessage());

        }
         return null;

        
    }
     
     
     
     public String inserir(Lista_principal arq, int id_usuario) {

        String sql = "insert into arquivos(id_usuario,doc_conteudo,doc_local,data_listagem,hora)values(?,?,?,?,?)";
        
                  

                    Conexao fab = new Conexao();
                    fab.abrirConexao();

        try {

            PreparedStatement ps = fab.con.prepareStatement(sql);

 
            ps.setInt(1, id_usuario);
            ps.setString(2, arq.getconteuto());
            ps.setString(3, arq.getlocal_arquivo());
            ps.setDate(4, arq.getdata_listagem());
             ps.setString(5, arq.gethora());
           
            if (ps.executeUpdate() > 0) {

                return "Inserido com sucesso.";
                
                

            } else {

                return "Erro ao inserir";

            }

        } catch (SQLException e) {

              System.out.println(e.getMessage());

        }
return null;
    }
     
     public List<Lista_principal> lista_dados_compara (int id_usuario){
         
         Conexao fab = new Conexao();
         fab.abrirConexao();
        
         String sql = "select doc_conteudo, doc_local,data_listagem,hora from arquivos where arquivos.id_usuario = "+id_usuario+"";

         List<Lista_principal> lista_arquivos_compara = new ArrayList<Lista_principal>();

         try {
             fab.rs = fab.stmt.executeQuery(sql);
             /*fab.rs.first();*/
                   
            if (fab.rs != null) {
                while (fab.rs.next()) {
                    Lista_principal lista = new Lista_principal();
                    lista.setconteudo(fab.rs.getString(1));
                    lista.setlocal_arquivo(fab.rs.getString(2));
                    lista.setdata_listagem(fab.rs.getDate(3));
                    lista.sethora(fab.rs.getString(4));
                    lista_arquivos_compara.add(lista);
                }
               
                  System.out.println(""+lista_arquivos_compara);
                return lista_arquivos_compara;

            } else {

                return null;

            }
         } catch (SQLException e) {

           System.out.println(e.getMessage());

        }
         return null;
         
         
         
         
         
         
     }
     
      public String inserir_compara (Lista_principal arq) {

        String sql = "insert into controle_dados (id_usuario, arquivo_pesquisado,data_pesquisa,hora)values(?,?,?,?)";
        
                  

                    Conexao fab = new Conexao();
                    fab.abrirConexao();

        try {

            PreparedStatement ps = fab.con.prepareStatement(sql);

 

            ps.setInt(1, arq.getid_usuario());
            ps.setString(2, arq.getlocal_arquivo());
            ps.setDate(3, arq.getdata_listagem());
            
             ps.setString(4, arq.gethora());
           
            if (ps.executeUpdate() > 0) {

                return "Inserido com sucesso.";
                
                
             
            } else {

                return "Erro ao inserir";

            }

        } catch (SQLException e) {

           System.out.println(e.getMessage());

        }
         return null;
         
   

    }
     
            protected String converteData(java.util.Date dtData){  
                   SimpleDateFormat formatBra;     
                   formatBra = new SimpleDateFormat("dd/MM/yyyy");  
                   try {  
                      java.util.Date newData = formatBra.parse(dtData.toString());  
                      return (formatBra.format(newData));  
                   } catch (ParseException Ex) {  
                      return "Erro na conversão da data";  
                   }  
       }  

    
}
