package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import java.io.Serializable;

public class RFactory extends Robot implements Serializable
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
	public void adicionarMotorChild(Motor m)
	{
		if (motores.size() >= 2)
		{
			throw new IllegalStateException("R-Carry max 2 motores");
		}
		super.adicionarMotor(m);
	}

	@Override
	public void adicionarTecnico(Tecnico t)
	{
		if (this.equipa.size() >= 3)
		{
			throw new IllegalStateException("R-Factory max 3 tecnicos");
		}
		super.associarTecnico(t);
	}

	@Override
	public void removerTecnico(Tecnico t)
	{
		super.desassociarTecnico(t);
	}
    
}