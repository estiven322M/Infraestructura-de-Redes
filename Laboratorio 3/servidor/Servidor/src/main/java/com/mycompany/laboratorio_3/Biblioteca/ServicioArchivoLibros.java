
package com.mycompany.laboratorio_3.Biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class ServicioArchivoLibros implements IServicioLibros {private final String NOMBRE_ARCHIVO = "Libros.txt";

    public ServicioArchivoLibros() {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            if (archivo.exists()) {
                System.out.println("El archivo ya existe");
                cargarLibrosSiEsNecesario(archivo);
            } else {
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se creó el archivo");
                agregarLibrosPredeterminados(archivo);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al abrir el archivo " + e.getMessage());
        }
    }

    private void cargarLibrosSiEsNecesario(File archivo) throws Exception {
        var entrada = new BufferedReader(new FileReader(archivo));
        if (entrada.readLine() == null) {
            // Si el archivo está vacío, se agregan libros predeterminados
            agregarLibrosPredeterminados(archivo);
        }
        entrada.close();
    }

    private void agregarLibrosPredeterminados(File archivo) throws Exception {
        var salida = new PrintWriter(new FileWriter(archivo, true)); // Anexar
        String[] librosPredeterminados = {
            "Cálculo de una variable",
            "Domina Java",
            "Algoritmos y estructuras de datos",
            "Criptografía y seguridad en redes",
            "Introducción a la inteligencia artificial",
            "Programación en C",
            "Bases de datos: Diseño y administración",
            "Desarrollo web con Java",
            "Sistemas operativos modernos",
            "Redes de computadoras"
        };

        for (String libro : librosPredeterminados) {
            salida.println(libro);
        }
        salida.close();
        System.out.println("Se agregaron libros predeterminados al archivo.");
    }

    @Override
    public void listarLibros() {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            System.out.println("LISTADO DE LIBROS");
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = entrada.readLine();
            while (linea != null) {
                var libro = new Libro(linea);
                System.out.println(libro);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo " + e.getMessage());
        }
    }

    @Override
    public void agregarLibro(Libro libro) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, true)); // Anexar
            salida.println(libro);
            salida.close();
            System.out.println("Se agregó el libro: " + libro);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al agregar el libro " + e.getMessage());
        }
    }

    @Override
    public void buscarLibro(Libro libro) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            boolean encontrado = false;
            int indice = 1;
            while ((lineaTexto = entrada.readLine()) != null) {
                if (libro.getNombre().equalsIgnoreCase(lineaTexto)) {
                    encontrado = true;
                    break;
                }
                indice++;
            }
            if (encontrado) {
                System.out.println("Libro " + lineaTexto + " encontrado en la posición " + indice);
            } else {
                System.out.println("No se encontró el libro " + libro.getNombre());
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println("Ocurrió un error buscando el libro " + e.getMessage());
        }
    }
}
