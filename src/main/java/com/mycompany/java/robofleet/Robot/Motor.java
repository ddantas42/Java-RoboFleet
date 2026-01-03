package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Gestao.*;
import java.io.Serializable;

public class Motor implements Serializable
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