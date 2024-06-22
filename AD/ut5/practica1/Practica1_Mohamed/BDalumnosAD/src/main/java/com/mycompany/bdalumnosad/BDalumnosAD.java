/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bdalumnosad;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author M24Y
 */
public class BDalumnosAD {

    public static void main(String[] args) {
        /* 1.0
        AlumnoInterface objetoDAO = FactoriaAlumnos.getAlumnoDao();
        AlumnoInterface alumno1 = objetoDAO.getNuevoAlumno("105", "ENRIQUETA", "3434343", "ASIR");
        
        // Mostrar los datos antes de la modificación
        System.out.println("Datos antes de la modificación:");
        mostrarDatos(alumno1);

        // Modificar los datos del alumno
        alumno1.setNombre("BLANCA");
        alumno1.setDni("555555555");
        alumno1.setCiclo("DAM");

        // Mostrar los datos después de la modificación
        System.out.println("\nDatos después de la modificación:");
        mostrarDatos(alumno1);
        */
        /* 2.0
        // Obtener la colección de alumnos en el ciclo DAM
        AlumnoInterface objetoDAO = FactoriaAlumnos.getAlumnoDao();
        java.util.Collection<AlumnoInterface> alumnosEnDAM = objetoDAO.getAlumnoPorCiclo("DAM");

        // Verificar si la colección está vacía
        if (alumnosEnDAM.isEmpty()) {
            System.out.println("No hay alumnos matriculados en el ciclo DAM.");
        } else {
            System.out.println("Alumnos matriculados en el ciclo DAM:");

            // Iterar sobre la colección utilizando un Iterator
            Iterator<AlumnoInterface> iterator = alumnosEnDAM.iterator();
            while (iterator.hasNext()) {
                AlumnoInterface alumno = iterator.next();
                mostrarDatos(alumno);
            }
        }*/
        /* 3.0
        // Obtener la colección de alumnos con el nombre "Juan"
        AlumnoInterface objetoDAO = FactoriaAlumnos.getAlumnoDao();
        java.util.Collection<AlumnoInterface> alumnosJuan = objetoDAO.getAlumnoPorNombre("Juan");

        // Verificar si la colección es null o está vacía
        if (alumnosJuan == null || alumnosJuan.isEmpty()) {
            System.out.println("No hay alumnos con el nombre 'Juan'.");
        } else {
            System.out.println("Alumnos con el nombre 'Juan':");

            // Iterar sobre la colección utilizando un Iterator
            Iterator<AlumnoInterface> iterator = alumnosJuan.iterator();
            while (iterator.hasNext()) {
                AlumnoInterface alumno = iterator.next();
                mostrarDatos(alumno);
            }
        }*/
        /* 4.0
        // Obtener el alumno por número de expediente
        AlumnoInterface objetoDAO = FactoriaAlumnos.getAlumnoDao();
        AlumnoInterface alumno = objetoDAO.getAlumnoPorNUMEXPDTE("1");

        // Verificar si el alumno es null
        if (alumno == null) {
            System.out.println("No se encontró un alumno con el número de expediente '1'.");
        } else {
            System.out.println("Datos del alumno con el número de expediente '1':");
            mostrarDatos(alumno);
        }*/
        
        AlumnoInterface objetoDAO = FactoriaAlumnos.getAlumnoDao();
        java.util.Collection<AlumnoInterface> alumnosJuan = objetoDAO.getAlumnoPorNombre("Juan");

        if (alumnosJuan == null || alumnosJuan.isEmpty()) {
            System.out.println("No hay alumnos con el nombre 'Juan' para borrar.");
        } else {
            System.out.println("Borrando alumnos con el nombre 'Juan'...");

            // Llama al método delete para borrar la colección de alumnos
            objetoDAO.delete(alumnosJuan);

            System.out.println("Alumnos borrados.");
        }
}

    private static void mostrarDatos(AlumnoInterface alumno) {
        System.out.println("Nombre: " + alumno.getNombre());
        System.out.println("DNI: " + alumno.getDni());
        System.out.println("Ciclo: " + alumno.getCiclo());
        System.out.println("Num expdte: " + alumno.getNumexpdte());
        System.out.println("-------------------------");
    }
}
