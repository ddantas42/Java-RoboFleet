/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java.robofleet.Robot;

/**
 *
 * @author migue
 */

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