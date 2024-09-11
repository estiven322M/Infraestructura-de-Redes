
package com.mycompany.laboratorio_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;

public class Conexion {
    
    private final int PUERTO=1234; // puerto para la conexion
    private final String HOST="192.168.1.54"; //direcciòn IP
    protected ServerSocket ss; // socket del servidor
    protected Socket cs; //socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente; //Flujo de datos
    
    public Conexion (String tipo) throws IOException{
        if(tipo.equalsIgnoreCase("servidor")){
            ss = new ServerSocket (PUERTO); //Se crea el socket para el servidor en puerto
            
        }else{
            cs = new Socket (HOST, PUERTO); // Se crea el socket para el cliente en puerto
        }
    }
    
}
