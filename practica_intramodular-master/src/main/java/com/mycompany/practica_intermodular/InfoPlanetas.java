/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica_intermodular;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author LucasRuiz
 * sacamos la información de los planetas y añadimos a la tabla los satelites
 */
public class InfoPlanetas {
    public static void addSelectionListener(JComboBox<String> comboBox, JLabel[] planetLabels, JTable satelliteTable) {
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPlanet = (String) comboBox.getSelectedItem();
                if (selectedPlanet != null) {
                    updatePlanetInfo(selectedPlanet, planetLabels);
                    updateSatelliteTable(selectedPlanet, satelliteTable);
                }
            }
        });
    }

    private static void updatePlanetInfo(String planetName, JLabel[] labels) {
        try (Connection connection = BBDD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Planeta WHERE nombre = '" + planetName + "'")) {

            // añadimos los datos al array
            if (resultSet.next()) {
                labels[0].setText(resultSet.getString("radio_km"));
                labels[1].setText(resultSet.getString("distancia_sol"));
                labels[2].setText(resultSet.getString("periodo_orbital"));
                labels[3].setText(resultSet.getString("temperatura_media"));
                labels[4].setText(resultSet.getString("tipo"));
                labels[5].setText(resultSet.getString("numero_satelites"));
                labels[6].setText(resultSet.getString("fecha_creacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateSatelliteTable(String planetName, JTable table) {
        try (Connection connection = BBDD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Satelite WHERE planeta_id = (SELECT id FROM Planeta WHERE nombre = '" + planetName + "')")) {

            String[] columnNames = {"Nombre", "Radio", "Distancia al Planeta", "Periodo Orbital", "Temperatura Media", "fecha_creacion"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                double radio = resultSet.getDouble("radio_km");
                double distanciaPlaneta = resultSet.getDouble("distancia_planeta");
                double periodoOrbital = resultSet.getDouble("periodo_orbital");
                double temperaturaMedia = resultSet.getDouble("temperatura_media");
                String fecha_creacion = resultSet.getString("fecha_creacion");
                model.addRow(new Object[]{nombre, radio, distanciaPlaneta, periodoOrbital, temperaturaMedia, fecha_creacion});
            }

            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
