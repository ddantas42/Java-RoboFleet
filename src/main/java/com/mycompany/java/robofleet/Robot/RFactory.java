/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java.robofleet.Robot;

public class RFactory extends Robot
{

    private int numBracosRoboticos;
    private boolean temOrientacaoLaser;

    public RFactory(String nome, String marca, String modelo, int ano, Zona zona, Bateria bateria, int numBracos)
    {
        super(nome, marca, modelo, ano, zona, bateria);

        if (zona != Zona.LINHA_PROD_1 && zona != Zona.LINHA_PROD_2 && zona != Zona.ESTACAO_CARGA)
        {
            throw new IllegalArgumentException("R-Factory só pode operar nas Linhas de Produção.");
        }

        this.numBracosRoboticos = numBracos;
        this.temOrientacaoLaser = true;
    }

    @Override
    public boolean validarEquipa()
    {
        return true;
    }
}