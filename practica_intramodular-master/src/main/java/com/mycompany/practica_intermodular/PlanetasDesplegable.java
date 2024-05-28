/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_intermodular;
import javax.swing.JComboBox;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author LucasRuiz
 * programacion del comboBox
 */
public class PlanetasDesplegable {
    public static void populateComboBox(JComboBox<String> comboBox) {
        try (Connection connection = BBDD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT nombre FROM Planeta")) {

            while (resultSet.next()) {
                comboBox.addItem(resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
