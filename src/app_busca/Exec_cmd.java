/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jadiel
 */
public class Exec_cmd {
  
    
 public synchronized static String execCommand(final String commandLine) throws IOException {  
  
    boolean success = false;  
    String result;  
  
    Process p;  
    BufferedReader input;  
    StringBuffer cmdOut = new StringBuffer();  
    String lineOut = null;  
    int numberOfOutline = 0;  
  
    try {  
  
        p = Runtime.getRuntime().exec(commandLine);  
  
        input = new BufferedReader(new InputStreamReader(p.getInputStream()));  
  
        while ((lineOut = input.readLine()) != null) {  
            if (numberOfOutline > 0) {  
                cmdOut.append("\n");  
            }  
            cmdOut.append(lineOut);  
            numberOfOutline++;  
        }  
  
        result = cmdOut.toString();  
  
        success = true;  
  
        input.close();  
          
    } catch (IOException e) {  
        result = String.format("Falha ao executar comando %s. Erro: %s", commandLine, e.toString());  
    }  
  
    // Se não executou com sucesso, lança a falha  
    if (!success) {  
        throw new IOException(result);  
    }  
  
    return result;  
  
  
    }
    
   static final Runtime run = Runtime.getRuntime();
    static Process pro;
    static BufferedReader read;

    public static void exec_comandos()
    {
        String[] cmds = {
            /*"cmd /k chcp 1252"*/
           "DEL C:\\Users\\Jadiel\\Desktop\\Pendrive\\Monografia\\Aplicacao\\Teste_PDF\\Teste_PDF.txt",
           /*"cd C:\\Users\\Jadiel\\Desktop\\Pendrive\\Monografia\\Aplicacao\\Teste_PDF",
            "dir /s *.pdf /b /o:n >C:\\Users\\Jadiel\\Desktop\\Pendrive\\Monografia\\Aplicacao\\Teste_PDF.txt" ,*/
           
          "cd C:\\Users\\Jadiel\\Desktop\\Pendrive\\Monografia\\Aplicacao\\Teste_PDF\\",
           "dir /s *.pdf /b /o:n >C:\\Users\\Jadiel\\Desktop\\Pendrive\\Monografia\\Aplicacao\\Teste_PDF\\Teste_PDF.txt"
        };

        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
                String.join("& ", cmds));

            builder.redirectErrorStream(true);

            Process p = builder.start();

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }

                System.out.println(line);
            }
        } catch(Exception e) {
            System.err.println(e);
         
        }
    }

    

  
    

 
   
    }

 
   /* public static void main(String[] args)
    {
       /*Exec_cmd exec = new Exec_cmd();
       exec.cmd();
    }*/

