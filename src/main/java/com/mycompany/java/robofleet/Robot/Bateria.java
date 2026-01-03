package com.mycompany.java.robofleet.Robot;

import java.io.Serializable;

public class Bateria implements Serializable
{
    private int capacidade;
    private int autonomia;

    public Bateria(int capacidade, int autonomia) 
    {
        this.capacidade = capacidade;
        this.autonomia = autonomia;
    }

    public int getCapacidade(){
        return capacidade;
    }

    @Override
    public String toString() { return capacidade + "Ah (" + autonomia + "h)"; }
}