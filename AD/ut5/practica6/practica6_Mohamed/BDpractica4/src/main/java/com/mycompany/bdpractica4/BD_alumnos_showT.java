/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bdpractica4;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author M24Y
 */
public class BD_alumnos_showT {
    
    private final String url;
    private final String usuario;
    private final String contraseña;
    public final Scanner scanner;

   
    public BD_alumnos_showT(String url, String usuario, String contraseña) {
        this.url = url;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.scanner = new Scanner(System.in);
    }

    public void listarTablas() {
        try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
            // Establecer conexión con la base de datos

            // Crear una declaración
            Statement statement = connection.createStatement();

            // Ejecutar la consulta para obtener las tablas
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            // Imprimir el nombre de cada tabla
            System.out.println("Tablas de la base de datos:");
            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                System.out.println("Tabla: " + tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listarTablasMETADATA() {
        try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
            // Obtener metadatos de la base de datos
            DatabaseMetaData metaData = connection.getMetaData();

            // Obtener el nombre de la base de datos actual
            String dbName = connection.getCatalog();
            System.out.println("Base de datos actual: " + dbName);

            // Obtener las tablas de la base de datos actual
            ResultSet resultSet = metaData.getTables(dbName, null, null, new String[]{"TABLE"});

            // Imprimir el nombre de cada tabla
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println("Tabla: " + tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public ResultSet obtenerTabla(String tabla) throws SQLException {
        Connection connection =  DriverManager.getConnection(url, usuario, contraseña);
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM " + tabla);
    }

    public void mostrarTabla(String tabla) {
        try {
            ResultSet resultSet = obtenerTabla(tabla);

            while (resultSet.next()) {
                // Aquí puedes adaptar la lógica según la estructura de tus tablas
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }
}
