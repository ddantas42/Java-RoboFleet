package com.mycompany.java.robofleet.Robot;

public class Motor 
{
    private int potencia;

    public Motor(int potencia) 
    {
        this.potencia = potencia;
    }

    public int getPotencia() 
    { 
        return potencia; 
    }
    

    @Override
    public String toString() { return "Motor: " + potencia + "W"; }
}