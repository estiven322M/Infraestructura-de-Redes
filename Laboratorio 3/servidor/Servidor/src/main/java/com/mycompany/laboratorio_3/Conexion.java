
package com.mycompany.laboratorio_3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion {
    
    private final int PUERTO = 1400; // Puerto para la conexión
    private final String HOST = "localhost"; // Host para la conexión
    protected String mensajeServidor; // Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket ss; // Socket del servidor
    protected Socket cs; // Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente; // Flujo de datos de salida

    public Conexion(String tipo) throws IOException // Constructor
    {
        if(tipo.equalsIgnoreCase("servidor"))
        {
            ss = new ServerSocket(PUERTO); // Se crea el socket para el servidor en puerto 1234
        }
        else if (tipo.equalsIgnoreCase("cliente"))
        {
            cs = new Socket(HOST, PUERTO); // Socket para el cliente en localhost en puerto 1234
        }
    }

    // Métodos para cerrar los recursos
    public void close() {
        try {
            if (salidaServidor != null) salidaServidor.close();
            if (salidaCliente != null) salidaCliente.close();
            if (cs != null) cs.close();
            if (ss != null) ss.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
    
    
}
