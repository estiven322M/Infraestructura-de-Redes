
package com.mycompany.laboratorio_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor extends Conexion {
    
 public Servidor() throws IOException {
        super("servidor"); // Se usa el constructor para servidor de Conexion
    }

    public void startServer() {
        try {
            System.out.println("Esperando...");

            cs = ss.accept(); // Aceptar la conexión del cliente

            System.out.println("Cliente en línea");

            // Crear flujos de datos después de aceptar la conexión
            DataOutputStream salidaCliente = new DataOutputStream(cs.getOutputStream());
            DataInputStream entradaCliente = new DataInputStream(cs.getInputStream());

            // Enviar mensaje de bienvenida
            salidaCliente.writeUTF("Conexión establecida. Escribe 'salir' para cerrar la conexión.");
            salidaCliente.flush(); // Asegúrate de enviar el mensaje inmediatamente

            String mensaje;
            while (true) {
                // Leer mensaje del cliente
                mensaje = entradaCliente.readUTF();
                System.out.println("Cliente: " + mensaje);

                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("El cliente ha terminado la conexión.");
                    break;
                }
                
                String respuesta = "Servidor : recibido tu mensaje: "+mensaje;

                // Responder al cliente
                salidaCliente.writeUTF(respuesta);
                salidaCliente.flush(); // Asegúrate de enviar la respuesta inmediatamente
            }

            System.out.println("Fin de la conexión");
            entradaCliente.close(); // Cerrar DataInputStream
            salidaCliente.close(); // Cerrar DataOutputStream
            cs.close(); // Finaliza la conexión con el cliente
            ss.close(); // Cerrar ServerSocket

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(); // Mostrar el stack trace para depurar
        }
    }
}
