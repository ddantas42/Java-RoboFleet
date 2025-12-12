package com.mycompany.java.robofleet.Robot;

public class Bateria 
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