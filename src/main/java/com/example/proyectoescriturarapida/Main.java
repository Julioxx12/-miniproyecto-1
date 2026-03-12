package com.example.proyectoescriturarapida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class.getResource("EscrituraRapida.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 499, 400);

        stage.setTitle("ESCRITURA RAPIDA");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


