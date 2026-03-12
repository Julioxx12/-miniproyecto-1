package com.example.proyectoescriturarapida;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class PerdistesController {

    @FXML private Button btnReintentar;

    @FXML
    void handleRestart() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EscrituraRapida.fxml"));

            Stage stage = (Stage) btnReintentar.getScene().getWindow();

            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
