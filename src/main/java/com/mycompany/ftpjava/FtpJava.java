/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ftpjava;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Felipe
 */
public class FtpJava {

    public static void main(String[] args) throws IOException{
 
        String host = "192.168.80.126";
        String directory = "/ln/prod";
        String LOCAL_FILE = "/upload/ln/";
        String fileEscasses = "Bases";
        String nomeArquivo = "";
        
        FTPClient ftp = new FTPClient();
        
        ftp.connect(host);
        
        if(ftp.login("vasques", "04129215")){
            System.out.println("Login feito com sucesso!");
        }else{
            System.out.println("Login e senha errada!");
        }
                        
        ftp.changeWorkingDirectory(directory);
        
        String[] arquivos = ftp.listNames();
                   
        System.out.println("tamanho: " + arquivos.length);
        
        if(arquivos != null){
            Arrays.sort(arquivos);
            
            for(int i = arquivos.length - 1; i >= 0; i--){
                
                if(arquivos[i].endsWith(".txt")){
                    nomeArquivo = arquivos[i];
                    
                    if(!fileEscasses.trim().equals(nomeArquivo.trim())){
                        FileOutputStream fos = new FileOutputStream(LOCAL_FILE + nomeArquivo);
                        
                        if(ftp.retrieveFile(arquivos[i], fos)){
                            fos.close();
                            fileEscasses = nomeArquivo;
                        }
                    }
                    
                }
                
            }
            
        }
        
        for(String f: arquivos){
            System.out.println(f);
        }
        
        ftp.disconnect();
        
    }
}
