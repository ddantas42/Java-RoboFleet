/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java.robofleet.Robot;

/**
 *
 * @author migue
 */

public class Bateria 
{
    private int capacidade;
    private int autonomia;

    public Bateria(int capacidade, int autonomia) 
    {
        this.capacidade = capacidade;
        this.autonomia = autonomia;
    }

    public int getCapacidade()
    {
        return capacidade;
    }

    public void setCapacidade(int capacidade)
    {
        this.capacidade = capacidade;
    }

    public int getAutonomia()
    {
        return autonomia;
    }

    public void setAutonomia(int autonomia)
    {
        this.autonomia = autonomia;
    }
    
    @Override
    public String toString() {
        return "Bateria: " + capacidade + "Ah, " + autonomia + "h";
    }
}