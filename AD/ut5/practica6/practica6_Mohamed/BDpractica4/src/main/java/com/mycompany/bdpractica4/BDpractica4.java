/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bdpractica4;


/**
 *
 * @author M24Y
 */
public class BDpractica4 {

    public static void main(String[] args) {
         // Configurar los detalles de conexión
        String url = "jdbc:mysql://localhost:3306/bd_alumnos";
        String usuario = "root";
        String contraseña = "";

        // Crear una instancia de la clase BD_alumnos_showT
        BD_alumnos_showT bdAlumnosShowT = new BD_alumnos_showT(url, usuario, contraseña);

        // Llamar al método para listar las tablas
        // bdAlumnosShowT.listarTablas();
        
        // Llamar al método para listar las tablas con la interfaz DatabaseMetaData
        //bdAlumnosShowT.listarTablasMETADATA();
        
        // Menú principal
        int opcion;
        do {
            System.out.println("\nTablas de la base de datos:");
            bdAlumnosShowT.listarTablas();

            opcion = bdAlumnosShowT.leerEntero("\nOpcion (1 - 3): ");
            if (opcion >= 1 && opcion <= 3) {
                String tablaSeleccionada;
                switch (opcion) {
                    case 1:
                        tablaSeleccionada = "alumnos";
                        break;
                    case 2:
                        tablaSeleccionada = "alumnos_asignaturas";
                        break;
                    case 3:
                        tablaSeleccionada = "asignaturas";
                        break;
                    default:
                        tablaSeleccionada = "";
                        break;
                }

                if (!tablaSeleccionada.isEmpty()) {
                    System.out.println("\nMostrando datos de la tabla " + tablaSeleccionada + ":");
                    bdAlumnosShowT.mostrarTabla(tablaSeleccionada);
                } else {
                    System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        // Cerrar el scanner al final
        bdAlumnosShowT.scanner.close();
    }
}
