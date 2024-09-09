package Biblioteca;

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

}
