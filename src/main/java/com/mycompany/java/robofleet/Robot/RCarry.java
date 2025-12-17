package com.mycompany.java.robofleet.Robot;

public class RCarry extends Robot
{

    private double capacidadeCarga;
    private boolean temHolofote;

    public RCarry(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, double carga, boolean holofote)
    {
        super(nome, marca, modelo, ano, zona, bat);
        this.capacidadeCarga = carga;
        this.temHolofote = holofote;
    }

    @Override
    public void adicionarMotor(Motor m)
    {
        if (motores.size() >= 4)
        {
            throw new IllegalStateException("R-Carry max 4 motores");
        }
        super.adicionarMotor(m);
    }

    /* 
    @Override
    public boolean validarEquipa()
    {
        if (equipa.size() < 1 || equipa.size() > 3)
        {
            return false;
        }
        return equipa.stream().anyMatch(t -> t.temEspecializacao(Especializacao.MANUTENCAO));
    }
        */

    @Override
    public String toString() {
        return super.toString() + 
            " [Tipo: R-Carry | Carga: " + capacidadeCarga + "kg | Holofote: " + temHolofote + "]";
    }
}