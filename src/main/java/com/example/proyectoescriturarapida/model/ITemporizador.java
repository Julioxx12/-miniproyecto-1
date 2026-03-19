package com.example.proyectoescriturarapida.model;

/**
 * Interface for timer management logic.
 */
public interface ITemporizador {
    void iniciar(int segundos);
    void detener();
    int getTiempoRestante();
}