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
     *
     * @param capacidade Capacidade da bateria em Ah.
     * @param autonomia Autonomia estimada em horas.
     */
    public Bateria(int capacidade, int autonomia) {
        this.capacidade = capacidade;
        this.autonomia = autonomia;
    }

    /**
     * Obtém a capacidade total da bateria.
     *
     * @return A capacidade em Ah.
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Obtém a autonomia nominal da bateria.
     *
     * @return A autonomia em horas.
     */
    public int getAutonomia() {
        return autonomia;
    }

    /**
     * Retorna uma representação textual da bateria.
     * Exemplo de formato: "200Ah (100h)".
     *
     * @return String formatada com os dados da bateria.
     */
    @Override
    public String toString() {
        return capacidade + "Ah (" + autonomia + "h)";
    }
}