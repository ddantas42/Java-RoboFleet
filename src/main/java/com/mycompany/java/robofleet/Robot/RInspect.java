package com.mycompany.java.robofleet.Robot;

public class RInspect extends Robot
{

    private boolean temCamara;
    private boolean scannerTermico;

    public RInspect(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat)
    {
        super(nome, marca, modelo, ano, zona, bat);
        this.temCamara = true;
        this.scannerTermico = true;
    }

    @Override
    public void adicionarMotor(Motor m)
    {
        if (motores.size() >= 1)
        {
            throw new IllegalStateException("R-Inspect só tem 1 motor");
        }
        super.adicionarMotor(m);
    }
/* 
    @Override
    public boolean validarEquipa()
    {
        return equipa.size() >= 1 && equipa.size() <= 2;
    }*/
}