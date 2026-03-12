module com.example.proyectoescriturarapida {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.proyectoescriturarapida to javafx.fxml;
    exports com.example.proyectoescriturarapida;
}