
package com.mycompany.laboratorio_3;

 import java.io.IOException;

public class MainServidor {
    
    public static void main(String[] args) throws IOException{
        
        try{
            var servidor = new Servidor();
            servidor.startServer();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //Servidor serv = new Servidor(); //Se crea el servidor

        //System.out.println("Iniciando servidor\n");
        //serv.startServer(); //Se inicia el servidor
        
    }
    
    
    
}
