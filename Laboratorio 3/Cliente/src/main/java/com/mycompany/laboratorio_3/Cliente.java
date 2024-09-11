
package com.mycompany.laboratorio_3;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;

public class Cliente  extends Conexion{
    
    public Cliente () throws IOException{
        super("cliente"); //se usa el constructor para cliente de Conexion
    }
    
    public void startClient(){
        try{
            //Flujo de datos hacia el servidor
            DataOutputStream salidaServidor = new DataOutputStream(cs.getOutputStream());
            DataInputStream entradaServidor = new DataInputStream(cs.getInputStream());
            BufferedReader teclado = new BufferedReader ( new InputStreamReader(System.in));
            
            String mensaje;
            System.out.println("Escribe tus mensaje. Escribe 'salir' para terminar conexion " );
            
            //Bucle para enviar mensajes hasta que el usuario escriba "salir"
            while (true){
                System.out.println("Tu: ");
                mensaje = teclado.readLine();
                
                //Enviar mensaje al servidor
                
                salidaServidor.writeUTF(mensaje);
                salidaServidor.flush();
                
                
                
                if(mensaje.equalsIgnoreCase("salir")){
                    System.out.println("Cerrando conexion");
                    break;
                }
                
                //Leer respuesta del servidor
                String respuesta = entradaServidor.readUTF();
                //System.out.println("Servidor: "+respuesta);
                //Leer respuesta del servidor
                System.out.println("Servidor: "+respuesta);
            }
            teclado.close(); //cierra el BufferedReader
            salidaServidor.close(); //Cerrar el DataOutputStream
            entradaServidor.close(); //cierra DataInputStream
            cs.close(); //fin de la conexion
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
