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
        bdAlumnosShowT.listarTablas();
    }
}
