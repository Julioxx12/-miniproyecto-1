package com.example.proyectoescriturarapida;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class controller {
    @FXML private Label targetWordLabel; // La palabra que sale en el medio
    @FXML private Label timerLabel;      // EL RELOJ (Asegúrate que el fx:id en Scene Builder sea este)
    @FXML private TextField typingField;

    private String[] laspalabras = {"Carro", "Casa", "Univalle", "Programacion", "JavaFX", "Algoritmos"};
    private Random random = new Random();

    // VARIABLES PARA EL TIEMPO (HU-2)
    private Timeline cronometro;
    private int tiempoRestante = 20;

    // 1. EL BOTÓN DE INICIAR (Ahora sí arranca el reloj)
    @FXML
    void handleStartGame(ActionEvent event) {
        indiceAleatorio();
        typingField.setDisable(false);
        typingField.requestFocus();
        iniciarTemporizador(); // Arrancamos los 20 segundos
    }

    // 2. LÓGICA DEL RELOJ
    private void iniciarTemporizador() {
        tiempoRestante = 20; // Reset a 20
        if (timerLabel != null) timerLabel.setText(String.valueOf(tiempoRestante));

        // Si ya había un reloj corriendo, lo paramos para no crear mil relojes
        if (cronometro != null) cronometro.stop();

        cronometro = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tiempoRestante--;
            timerLabel.setText(String.valueOf(tiempoRestante));

            if (tiempoRestante <= 0) {
                cronometro.stop();
                handleSetWord(null); // Si llega a 0, valida automáticamente
            }
        }));
        cronometro.setCycleCount(Timeline.INDEFINITE);
        cronometro.play();
    }

    private void indiceAleatorio() {
        int r = random.nextInt(laspalabras.length);
        targetWordLabel.setText(laspalabras[r]);
        typingField.clear();
    }

    // 3. BOTÓN DE ENVIAR
    @FXML
    void handleSetWord(ActionEvent event) {
        String objetivo = targetWordLabel.getText();
        String escrito = typingField.getText();

        if (escrito.equals(objetivo) && tiempoRestante > 0) {
            // ACIERTO: Nueva palabra y reinicia el reloj
            indiceAleatorio();
            iniciarTemporizador();
        } else {
            // FALLO O TIEMPO AGOTADO
            if (cronometro != null) cronometro.stop();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Perdistes.fxml"));
                Scene scene = new Scene(loader.load ());
                Stage stage = (Stage) typingField.getScene().getWindow();
                stage.setScene(scene);
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("No se pudo cargar la pantalla de Perdiste, bro");

            }
            targetWordLabel.setText("GAME OVER");
            typingField.setDisable(true);
        }
    }
}