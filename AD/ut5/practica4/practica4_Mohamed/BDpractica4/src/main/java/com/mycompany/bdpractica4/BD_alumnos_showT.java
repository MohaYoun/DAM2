/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bdpractica4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author M24Y
 */
public class BD_alumnos_showT {
    
    private final String url;
    private final String usuario;
    private final String contraseña;

    public BD_alumnos_showT(String url, String usuario, String contraseña) {
        this.url = url;
        this.usuario = usuario;
        this.contraseña = contraseña;
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
}
