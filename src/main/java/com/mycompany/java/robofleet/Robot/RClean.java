package com.mycompany.java.robofleet.Robot;

public class RClean extends Robot
{

    private boolean sistemaSuccao;
    private boolean luzInspecao;

    public RClean(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, boolean succao, boolean luz)
    {
        super(nome, marca, modelo, ano, zona, bat);
        this.sistemaSuccao = succao;
        this.luzInspecao = luz;
    }
/* 
    @Override
    public boolean validarEquipa()
    {
        if (equipa.size() < 1 || equipa.size() > 2)
        {
            return false;
        }
        return equipa.stream().anyMatch(t -> t.temEspecializacao(Especializacao.MANUTENCAO));
    }*/
}