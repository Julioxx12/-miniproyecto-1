package com.example.proyectoescriturarapida.controller;

import com.example.proyectoescriturarapida.view.Animaciones;
import com.example.proyectoescriturarapida.model.IPalabras;
import com.example.proyectoescriturarapida.model.Palabras;
import com.example.proyectoescriturarapida.model.ITemporizador;
import com.example.proyectoescriturarapida.model.Temporizador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.net.URL;

/**
 * Main controller class for the Fast Writing Game logic.
 * Manages the interaction between the user, the countdown timer, and the scoring system.
 * <p>This class handles word validation, UI updates via JavaFX, and scene transitions
 * between the main gameplay and the Game Over screen.</p>
 * * @author Julio Cesar
 * @version 1.1
 */
public class controller {

    /** Label that displays the target word the user must type. */
    @FXML private Label targetWordLabel;

    /** Label that displays the remaining time in seconds. */
    @FXML private Label timerLabel;

    /** Text field where the user enters their input. */
    @FXML private TextField typingField;

    /** Feedback label for visual cues such as "CORRECT" or "GAME OVER". */
    @FXML private Label feedbackLabel;

    /** Button used to start the game session. */
    @FXML private Button btnIniciar;

    /** Player's current score based on correctly typed words. */
    private int puntos = 0;

    /** Current game level, increments with every correct word. */
    private int nivelActual = 1;

    /** Initial base time granted per word; decreases as difficulty increases. */
    private int tiempoBase = 20;

    /** Reference to the model managing the word list. */
    private IPalabras misPalabras = new Palabras();

    /** Reference to the game's timer system. */
    private ITemporizador miTemporizador;

    /**
     * Initializes a new game session.
     * Disables the start button to prevent multiple instances, clears the UI,
     * and sets up the timer.
     * * @param event ActionEvent triggered by the start button.
     */
    @FXML
    void handleStartGame(ActionEvent event) {
        btnIniciar.setDisable(true);
        typingField.clear();
        if(feedbackLabel != null) feedbackLabel.setText("");

        // Timer callback triggers Game Over if time runs out
        miTemporizador = new Temporizador(timerLabel, () -> handleSetWord(null));

        indiceAleatorio();
        typingField.setDisable(false);
        typingField.requestFocus();
        miTemporizador.iniciar(tiempoBase);
        Animaciones.zoom(targetWordLabel);
    }

    /**
     * Retrieves a random word from the model and updates the UI display.
     * Applies a zoom animation to emphasize the word change.
     */
    private void indiceAleatorio() {
        String nuevaPalabra = misPalabras.obtenerAleatoria();
        targetWordLabel.setText(nuevaPalabra);
        typingField.clear();
        Animaciones.zoom(targetWordLabel);
    }

    /**
     * Displays a pop-up window with the game rules and instructions.
     * Uses a JavaFX Alert dialog with an information icon.
     * * @param event ActionEvent triggered by the "Reglas" button.
     */
    @FXML
    void handleMostrarReglas(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reglas del Juego");
        alert.setHeaderText("¿Cómo jugar Escritura Rápida?");
        alert.setContentText(
                "1. Presiona 'INICIAR JUEGO' para iniciar el cronómetro.\n" +
                        "2. Escribe la palabra que aparece y pulsa ENTER.\n" +
                        "3. ¡Cada 5 aciertos, la velocidad aumenta!\n" +
                        "4. Pierdes si el tiempo llega a 0 o si fallas la palabra.\n\n" +
                        "¡Demuestra tu agilidad!"
        );
        try {
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("/com/example/proyectoescriturarapida/images/Reglas.png")));
        } catch (Exception e) {
            System.out.println("No se pudo cargar el icono en la ventana de reglas.");
        }
        alert.showAndWait();
    }

    /**
     * Validates user input against the target word.
     * <p>If correct: Increments score and resets timer with difficulty logic.</p>
     * <p>If incorrect or time expires: Stops the timer and switches to the Game Over scene.</p>
     * * @param event ActionEvent triggered by the Enter key or timer callback.
     */
    @FXML
    void handleSetWord(ActionEvent event) {
        String objetivo = targetWordLabel.getText();
        String escrito = typingField.getText().trim();

        // Prevents processing empty strings from keyboard events
        if (escrito.isEmpty() && event != null) return;

        // Validation of correct input within the time limit
        if (escrito.equals(objetivo) && miTemporizador != null && miTemporizador.getTiempoRestante() > 0) {
            feedbackLabel.setText("CORRECT");
            Animaciones.zoom(feedbackLabel);
            nivelActual++;

            // Difficulty logic: Reduce time every 5 levels
            if (nivelActual % 5 == 0 && tiempoBase > 5) {
                tiempoBase -= 2;
            }

            puntos++;
            indiceAleatorio();
            miTemporizador.iniciar(tiempoBase);
        } else {
            // Game Over logic
            if (miTemporizador != null) miTemporizador.detener();

            try {
                URL fxmlLocation = getClass().getResource("/com/example/proyectoescriturarapida/Perdistes.fxml");
                if (fxmlLocation == null) fxmlLocation = getClass().getResource("/Perdistes.fxml");

                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                Parent root = loader.load();

                PerdistesController escenaPerder = loader.getController();
                if (escenaPerder != null) {
                    escenaPerder.mostrarEstadisticas(nivelActual - 1, puntos);
                }

                Scene scene = new Scene(root);
                Stage stage = (Stage) typingField.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.err.println("Critical Error: Could not load the Game Over scene.");
                e.printStackTrace();
            }
            typingField.setDisable(true);
        }
    }
}