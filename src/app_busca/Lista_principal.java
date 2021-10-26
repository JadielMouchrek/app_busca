/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;

import java.sql.Date;

/**
 *
 * @author Jadiel
 */
public class Lista_principal {
    
     private String local_arquivo;
    private Date data_listagem;
    private String data_txt;
   private String conteudo;
   private String hora;
   private int id_usuario;
   
    public String getlocal_arquivo() {

        return local_arquivo;

    }
    public void setlocal_arquivo(String local_arquivo) {

        this.local_arquivo = local_arquivo;

    }
    public Date getdata_listagem() {

        return data_listagem;

    }
    public void setdata_listagem(Date data_listagem) {

        this.data_listagem = data_listagem;

    }
     public String getconteuto() {

        return conteudo;

    }

    public void setconteudo(String conteudo) {

        this.conteudo = conteudo;

    }
    
     public int getid_usuario() {

        return id_usuario;

    }

    public void setid_usuario(int id_usuario) {

        this.id_usuario = id_usuario;

    }
    
    public String gethora() {

        return hora;

    }

    public void sethora(String hora) {

        this.hora = hora;

    }
    
    public void setdata_txt(String data_txt) {

        this.data_txt = data_txt;

    }
    
}
