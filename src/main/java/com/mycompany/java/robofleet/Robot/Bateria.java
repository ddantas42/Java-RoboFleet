package com.mycompany.java.robofleet.Robot;

import java.io.Serializable;

/**
 * Representa a bateria de um Robot no sistema ROBOFLEET.
 * Armazena capacidade em Ah e autonomia em horas conforme enunciado.
 */
public class Bateria implements Serializable {
    private int capacidade; // [Ah]
    private int autonomia;  // [horas]

    public Bateria(int capacidade, int autonomia) {
        this.capacidade = capacidade;
        this.autonomia = autonomia;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getAutonomia() {
        return autonomia;
    }

    @Override
    public String toString() {
        return capacidade + "Ah (Autonomia base: " + autonomia + "h)";
    }
}