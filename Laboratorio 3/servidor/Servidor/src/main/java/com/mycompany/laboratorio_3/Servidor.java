
package com.mycompany.laboratorio_3;
import com.mycompany.laboratorio_3.Biblioteca.*;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor extends Conexion {
    
    private IServicioLibros servicioLibros;

    public Servidor() throws IOException {
        super("servidor");
        servicioLibros = new ServicioArchivoLibros();  // O ServicioDeLibros
    }

    public void startServer() {
        try {
            System.out.println("Esperando...");
            cs = ss.accept();
            System.out.println("Cliente en línea");

            DataOutputStream salidaCliente = new DataOutputStream(cs.getOutputStream());
            DataInputStream entradaCliente = new DataInputStream(cs.getInputStream());

            String mensaje;
            while (true) {
                mensaje = entradaCliente.readUTF();
                System.out.println("Cliente: " + mensaje);

                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("El cliente ha terminado la conexión.");
                    break;
                }

                if (mensaje.startsWith("agregar:")) {
                    String nombreLibro = mensaje.substring(8).trim();
                    servicioLibros.agregarLibro(new Libro(nombreLibro));
                    salidaCliente.writeUTF("Libro agregado: " + nombreLibro);
                } else if (mensaje.equalsIgnoreCase("listar")) {
                    salidaCliente.writeUTF("Listado de libros:");
                    servicioLibros.listarLibros();
                } else if (mensaje.startsWith("buscar:")) {
                    String nombreLibro = mensaje.substring(7).trim();
                    servicioLibros.buscarLibro(new Libro(nombreLibro));
                    salidaCliente.writeUTF("Resultado de la búsqueda: " + nombreLibro);
                } else {
                    salidaCliente.writeUTF("Comando no reconocido.");
                }

                salidaCliente.flush();
            }

            entradaCliente.close();
            salidaCliente.close();
            cs.close();
            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
}
