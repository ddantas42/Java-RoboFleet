package com.mycompany.java.robofleet.Robot;
import com.mycompany.java.robofleet.Centro.Tecnico;

import java.io.Serializable;

public class RClean extends Robot implements Serializable
{

    private boolean sistemaSuccao;
    private boolean luzInspecao;

    public RClean(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, boolean succao, boolean luz)
    {
        super(nome, marca, modelo, ano, zona, bat);
        this.sistemaSuccao = succao;
        this.luzInspecao = luz;
    }

	@Override
	public void adicionarTecnico(Tecnico t)
	{
		if (this.equipa.size() >= 2)
		{
			throw new IllegalStateException("R-Clean max 2 tecnicos");
		}
		super.associarTecnico(t);
	}

	@Override
	public void removerTecnico(Tecnico t)
	{
		super.desassociarTecnico(t);
	}
}