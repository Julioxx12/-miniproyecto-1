package com.example.proyectoescriturarapida.view;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Visual effects and animations for the UI components.
 * Provides zoom and shake transitions for feedback.
 * @author Julio Cesar
 */
public class Animaciones {

    /**
     * Applies a zoom-in animation to a specific node.
     * @param nodo The UI element to animate.
     */
    public static void zoom(Node nodo) {
        ScaleTransition st = new ScaleTransition(Duration.millis(300), nodo);
        st.setFromX(0.5);
        st.setFromY(0.5);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
    }

    /**
     * Applies a shake animation to a specific node.
     * @param nodo The UI element to animate.
     */
    public static void sacudon(Node nodo) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(50), nodo);
        tt.setFromX(-5);
        tt.setToX(5);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.play();
    }
}