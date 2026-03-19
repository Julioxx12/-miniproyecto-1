package com.example.proyectoescriturarapida.model;

import javafx.application.Platform;
import javafx.scene.control.Label;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Handles the countdown timer logic for the gameplay.
 * Updates the UI label in real-time and executes a callback when time expires.
 * @author Julio Cesar
 */
public class Temporizador implements ITemporizador {
    private Timer timer;
    private int tiempoRestante;
    private Label labelTiempo;
    private Runnable alTerminar;

    /**
     * Constructor for the Timer.
     * @param labelTiempo UI Label where the countdown is displayed.
     * @param alTerminar Callback function to execute when time reaches zero.
     */
    public Temporizador(Label labelTiempo, Runnable alTerminar) {
        this.labelTiempo = labelTiempo;
        this.alTerminar = alTerminar;
    }

    /**
     * Starts the countdown from a specific value.
     * @param segundos Initial time in seconds.
     */
    @Override
    public void iniciar(int segundos) {
        if (timer != null) timer.cancel();
        this.tiempoRestante = segundos;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (tiempoRestante > 0) {
                        tiempoRestante--;
                        labelTiempo.setText("Time: " + tiempoRestante);
                    } else {
                        detener();
                        alTerminar.run();
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    public void detener() {
        if (timer != null) timer.cancel();
    }

    @Override
    public int getTiempoRestante() {
        return tiempoRestante;
    }
}