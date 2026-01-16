package com.mycompany.java.robofleet.Robot;

import java.io.Serializable;

/**
 * Representa um motor eletrico utilizado pelos robôs.
 * Define a potência do motor, que influencia o consumo e a autonomia do robô.
 */
public class Motor implements Serializable {
    private int potencia;

    /**
     * Construtor da classe Motor.
     */
    public Motor(int potencia) {
        this.potencia = potencia;
    }

    public int getPotencia() { 
        return potencia; 
    }

    @Override
    public String toString() { 
        return "Motor: " + potencia + "W"; 
    }
}