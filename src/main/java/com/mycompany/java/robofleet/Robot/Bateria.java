package com.mycompany.java.robofleet.Robot;

import java.io.Serializable;

/**
 * Representa o sistema de bateria de um robô no complexo ROBOFLEET.
 * Armazena informações sobre a capacidade de carga e a autonomia nominal.
 */
public class Bateria implements Serializable {

    /** Capacidade da bateria medida em Ah (Ampere-hora). */
    private int capacidade;

    /** Autonomia estimada de funcionamento medida em horas. */
    private int autonomia;

    /**
     * Construtor da classe Bateria.
     */
    public Bateria(int capacidade, int autonomia) {
        this.capacidade = capacidade;
        this.autonomia = autonomia;
    }

    /**
     * Obtem a capacidade total da bateria.
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Obtem a autonomia nominal da bateria.
     */
    public int getAutonomia() {
        return autonomia;
    }

    /**
     * Retorna uma representação textual da bateria.
     */
    @Override
    public String toString() {
        return capacidade + "Ah (" + autonomia + "h)";
    }
}