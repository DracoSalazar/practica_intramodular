/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_intermodular;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author LucasRuiz
 * hacemos la conexion con la base de datos
 */

public class BBDD {
     static final String URL = "jdbc:mysql://localhost:3306/practica_intermodular";
     static final String USER = "root";
     static final String PASSWORD = "Med@c";

     
     
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
        
    }
}
