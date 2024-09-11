
package com.mycompany.laboratorio_3;

import java.io.IOException;

public class MainCliente {
    
    public static void main(String[] args) throws IOException {
        
        try{
            Cliente cliente = new Cliente();
        System.out.println("Inciando cliente");
        cliente.startClient(); //incia el cliente
            
        }catch (IOException e){
            System.out.println("Error al iniciar el cliente: "+e.getMessage());
        }
        
        
        
    }
    
}
