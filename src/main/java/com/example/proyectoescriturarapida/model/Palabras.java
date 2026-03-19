package com.example.proyectoescriturarapida.model;

import java.util.Random;

/**
 * Model class that manages the dictionary of words for the game.
 * Implements the IPalabras interface to provide random word selection.
 * @author Julio Cesar
 */
public class Palabras implements IPalabras {
    private String[] lista = {"JavaFX", "Univalle", "Programming", "Interface", "Controller", "Logic"};
    private Random random = new Random();

    /**
     * Selects and returns a random word from the predefined list.
     * @return A randomly selected String.
     */
    @Override
    public String obtenerAleatoria() {
        return lista[random.nextInt(lista.length)];
    }
}