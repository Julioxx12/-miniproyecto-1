package com.example.proyectoescriturarapida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Entry point of the application.
 * Loads the main FXML scene and sets up the primary stage.
 * @author Julio Cesar
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application.
     * @param stage The primary stage for this application.
     * @throws Exception if the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EscrituraRapida.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 667, 580 );
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/proyectoescriturarapida/images/Lapiz.png")));

        stage.setTitle("ESCRITURA RAPIDA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}