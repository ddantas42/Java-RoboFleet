/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java.robofleet.Robot;

public class RCarry extends Robot
{

    private double capacidadeCarga;
    private boolean temHolofote;

    public RCarry(String nome, String marca, String modelo, int ano, Zona zona, Bateria bateria, double capacidadeCarga, boolean temHolofote)
    {
        super(nome, marca, modelo, ano, zona, bateria);
        this.capacidadeCarga = capacidadeCarga;
        this.temHolofote = temHolofote;
    }

    public void adicionarMotor(Motor m)
    {
        if (motores.size() < 4)
        {
            motores.add(m);
        } else
        {
            System.out.println("Erro: RCarry só pode ter até 4 motores.");
        }
    }

    @Override
    public boolean validarEquipa()
    {
        return true; 
    }

    @Override
    public String toString()
    {
        return super.toString() + " [Tipo: R-Carry] Carga: " + capacidadeCarga;
    }
}