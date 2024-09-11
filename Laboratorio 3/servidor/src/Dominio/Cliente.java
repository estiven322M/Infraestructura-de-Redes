package Dominio;

import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

public class Cliente {
	//Cliente: test de conexiòn a un servidor de tipo ServerSocket
	//El servidor correspondiente es Servidor
	
	//Este programa abre la conexión, lee el mensaje de bienvenida del servidor
	// y envia un mensaje y se cierra
	
	public static void main(String[] args) {
		Socket socketCliente;
        BufferedReader entrada;
        PrintWriter salida;
        String linea;
        
        try
        {
            socketCliente = new Socket("localhost", 9090);
            try
            {
                salida = new PrintWriter(
                    new OutputStreamWriter(
                        socketCliente.getOutputStream(), "UTF-8"));

                entrada= new BufferedReader(
                    new InputStreamReader(
                        socketCliente.getInputStream(), "UTF-8"));

// --------------------------------------------------------------------------
// Lectura del mensaje home
// --------------------------------------------------------------------------
                //System.out.println("\n");
                linea = entrada.readLine();
                JOptionPane.showMessageDialog(null, linea);
                
             // Solicitar correo y clave al usuario con JOptionPane
                String email = JOptionPane.showInputDialog("Ingrese su correo electrónico:");
                String password = JOptionPane.showInputDialog("Ingrese su clave personal:");
                
                //Enviar correo y clave al servidor
                salida.println(email);
                salida.println(password);
                salida.flush();
                
                //Leer la respuesta del servidor
                linea=entrada.readLine();
                JOptionPane.showMessageDialog(null, linea);


               
            }
            finally
            {
                socketCliente.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("El servidor no responde !");
        }
        
	}
	 private static void mostrarMenu(PrintWriter salida, BufferedReader entrada) throws IOException {
	        boolean salir = false;
	        while (!salir) {
	            String menu = """
	                    \nMenú de opciones:
	                    1. Buscar libro
	                    2. Reservar libro
	                    3. Cambiar clave
	                    4. Cerrar sesión
	                    Seleccione una opción:
	                    """;
	            String opcion = JOptionPane.showInputDialog(menu);
	            salida.println(opcion);
	            salida.flush();

	            switch (opcion) {
	                case "1" -> buscarLibro(salida, entrada);
	                case "2" -> reservarLibro(salida, entrada);
	                case "3" -> cambiarClave(salida, entrada);
	                case "4" -> {
	                    JOptionPane.showMessageDialog(null, "Sesión cerrada.");
	                    salir = true;
	                }
	                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
	            }
	        }
	    }
	 
	 private static void buscarLibro(PrintWriter salida, BufferedReader entrada) throws IOException {
	        String criterio = JOptionPane.showInputDialog("Ingrese el criterio de búsqueda (nombre, autor, tema):");
	        salida.println("BUSCAR_LIBRO");
	        salida.println(criterio);
	        salida.flush();
	        String respuesta = entrada.readLine();
	        JOptionPane.showMessageDialog(null, "Resultados:\n" + respuesta);
	    }
	 
	 private static void reservarLibro(PrintWriter salida, BufferedReader entrada) throws IOException {
	        String libro = JOptionPane.showInputDialog("Ingrese el nombre del libro a reservar:");
	        salida.println("RESERVAR_LIBRO");
	        salida.println(libro);
	        salida.flush();
	        String respuesta = entrada.readLine();
	        JOptionPane.showMessageDialog(null, respuesta);
	    }
	 private static void cambiarClave(PrintWriter salida, BufferedReader entrada) throws IOException {
	        String nuevaClave = JOptionPane.showInputDialog("Ingrese la nueva clave:");
	        salida.println("CAMBIAR_CLAVE");
	        salida.println(nuevaClave);
	        salida.flush();
	        String respuesta = entrada.readLine();
	        JOptionPane.showMessageDialog(null, respuesta);
	    }

}
