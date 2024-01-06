/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jadiel
 */
public class Pdf_Reader {
    
    public int lista_diretorio(int id_usuario) throws IOException, SQLException{
        
       
        int contador;
        Exec_cmd cmd= new Exec_cmd();
       
        //Executa cmd
        cmd.exec_comandos();
        
        
        Extrai_txt extrai= new Extrai_txt();
        
        contador = extrai.Text(id_usuario);
         
        System.out.println("Dados Extraidos do TXT");
        
        
        return contador;
        
       
             
       /*String result;
         Exec_cmd.execCommand("cmd /c DEL C:\\Users\\Jadiel\\Desktop\\Teste.txt");
       
         Exec_cmd.execCommand("cmd /c cd C:\\Users\\Jadiel\\Desktop\\Pendrive\\Monografia\\Aplicação\\Teste_PDF");
       
       result =  Exec_cmd.execCommand("cmd /c dir /b /o:n >Teste.pdf");
      return result;
       
        */
    }
       
  
   public String extrai_dados(int id_usuario){
       

        Lista_principal lista_arq = new Lista_principal();
        Lista_dados_principal list_dados = new Lista_dados_principal();
        System.out.println("Teste4");



            
            List<Lista_principal> lista = list_dados.lista_dados(id_usuario);
            
            if (lista != null) {
                for (Lista_principal arq : lista) {
                    try {
                        System.out.println("Dados da lista: " + arq.getlocal_arquivo());

                        //Carregando o arquivo PDF
                        PdfReader pdfReader = new PdfReader(arq.getlocal_arquivo());


                        String s = "";
                        //Extraindo de página em página e jogando numa String

                        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
                            try {
                            s = s.concat(PdfTextExtractor.getTextFromPage(pdfReader, i) + "\n\n");
                            }  catch (Exception e) {
                                // Tratar a exceção se necessário
                                e.printStackTrace();  // ou outro tratamento
                            }
                            //       System.out.println(s);
                        }
                        // Identificando PDF com caracteres menores que a quantidade expecificada na condição e inserindo o dados na tabla
                        if (s.length() < 4194304) {
                            lista_arq.setlocal_arquivo(arq.getlocal_arquivo());
                            lista_arq.setdata_listagem(arq.getdata_listagem());
                            lista_arq.sethora(arq.gethora());

                            lista_arq.setconteudo(s);
                            /*System.out.println(s.length()); */
                            System.out.println(list_dados.inserir(lista_arq, id_usuario));

                        } else {
                            arq = null;


                        }
                    }  catch (Exception e) {
                        // Tratar a exceção se necessário
                        e.printStackTrace();  // ou outro tratamento
                        continue;
                    }
                }
            } else{
                System.out.println("Lista Vazia");
            }

        return null;
        
        
    }
   
    
     
     public void deleta_dados( int id_usuario) throws SQLException{
         
            
             
             Conexao fab= new Conexao();
                fab.abrirConexao();
             String sql = "delete from local_arquivo where local_arquivo.id_usuario="+id_usuario+"";
             PreparedStatement ps;
            
             ps = fab.con.prepareStatement(sql);
            try{
              if (ps.executeUpdate() > 0) {

                 System.out.println("Local do arquivo excluído com sucesso ");

            } else {

                System.out.println("Erro ao excluir ");

            }
              
              
            
             String sql2 = "delete from arquivos where arquivos.id_usuario="+id_usuario+"";
             PreparedStatement ps2;
            
             ps2 = fab.con.prepareStatement(sql2);
            
              if (ps2.executeUpdate() > 0) {

                 System.out.println("Dados dos arquivos excluídos com sucesso ");
                 
               

            } else {

                System.out.println("Erro ao excluir ");

            }
              
               } catch (SQLException e) {

           System.out.println(e.getMessage());

        }
         
     }
    
    
         public void compara_dados(String palavra_chave1, String palavra_chave2, int id_usuario){
                 
               String arquivo_str;
               int indice ;
               
               String arquivo_str2;
               int indice2 ;
         
                 Lista_dados_principal list_dados = new Lista_dados_principal();
                 List<Lista_principal> lista = list_dados.lista_dados_compara(id_usuario);
                 Lista_principal lista_arq = new Lista_principal(); 
                 if (lista != null && !lista.isEmpty()) {
                     for(int i = 0; i < lista.size(); i++) {
                         Lista_principal p = lista.get(i);
                         arquivo_str = p.getconteuto();
                         
                         indice = arquivo_str.indexOf(palavra_chave1);
                         
                         if(indice<0)
                         {
                             i=0;
                             // Remove.
                             lista.remove(p);
                         }
                     }
                     
                 } else {
                     JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
                 }
                 if (lista != null && !lista.isEmpty()) {
                     
                     for(int i = 0; i < lista.size(); i++) {
                         
                         Lista_principal p2 = lista.get(i);
                             
                         arquivo_str2 = p2.getconteuto();
                         
                         indice2 = arquivo_str2.indexOf(palavra_chave2);
                                  
                         if (indice2<0){
                             i = 0;
                             lista.remove(p2);
                         }
                     }
                     
                 } else{

                         JOptionPane.showMessageDialog(null, "Primeira palavra não encontrado");

                 }
                 if (lista != null && !lista.isEmpty()){
                     for (Lista_principal arq : lista) {
                         
                         lista_arq.setlocal_arquivo(arq.getlocal_arquivo());
                         lista_arq.setdata_listagem(arq.getdata_listagem());
                         lista_arq.sethora(arq.gethora());
                         lista_arq.setid_usuario(id_usuario);
                         list_dados.inserir_compara(lista_arq);
                         
                         System.out.println(""+arq.getlocal_arquivo());
                         
                     }
                     
               } else{
                     JOptionPane.showMessageDialog(null, "Segunda palavra não encontrado");
                 }
                  
}           
        

}
         
         

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    /*static void main(String[] args) throws IOException {
        
         
    }*/
         
           /*}*/

