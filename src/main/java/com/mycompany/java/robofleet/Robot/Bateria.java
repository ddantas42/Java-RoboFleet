package com.mycompany.java.robofleet.Robot;

import java.io.Serializable;

public class Bateria implements Serializable {
    private int capacidade;

    public Bateria(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return capacidade + " Wh";
    }
}