package com.example.proyectoescriturarapida.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for the Game Over screen.
 * Displays final performance metrics and handles the game restart logic.
 * @author Julio Cesar
 * @version 1.0
 */
public class PerdistesController {

    /** Label to display the final level reached by the player. */
    @FXML private Label lblNiveles;

    /** Label to display the total points accumulated. */
    @FXML private Label lblPuntos;

    /**
     * Updates the UI labels with the statistics received from the main controller.
     * @param nivel Final level reached.
     * @param puntos Total score.
     */
    public void mostrarEstadisticas(int nivel, int puntos) {
        if (lblNiveles != null) {
            lblNiveles.setText("Level Reached: " + nivel);
        }
        if (lblPuntos != null) {
            lblPuntos.setText("Total Score: " + puntos);
        }
    }

    /**
     * Handles the restart button action to return to the main game scene.
     * @param event ActionEvent triggered by the restart button.
     */
    @FXML
    void handleRestart(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectoescriturarapida/EscrituraRapida.fxml"));
            if (loader.getLocation() == null) {
                loader = new FXMLLoader(getClass().getResource("/EscrituraRapida.fxml"));
            }
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.err.println("Error: Could not restart the game.");
            e.printStackTrace();
        }
    }
}