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

    public void setPotencia(int potencia)
    {
        this.potencia = potencia;
    }
}