package com.mycompany.java.robofleet.Robot;

public class RFactory extends Robot
{
    private int numBracos;
    private boolean orientacaoLaser;

    public RFactory(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, int bracos)
    {
        super(nome, marca, modelo, ano, zona, bat);
        if (zona != Zona.LINHA_PROD_1 && zona != Zona.LINHA_PROD_2 && zona != Zona.ESTACAO_CARGA)
        {
            throw new IllegalArgumentException("RFactory só permitido em Linhas de Produção");
        }
        this.numBracos = bracos;
        this.orientacaoLaser = true;
    }

    @Override
    public boolean validarEquipa()
    {
        if (equipa.size() < 2 || equipa.size() > 3)
        {
            return false;
        }
        return equipa.stream().anyMatch(t -> t.temEspecializacao(Especializacao.ROBOTICA));
    }
}