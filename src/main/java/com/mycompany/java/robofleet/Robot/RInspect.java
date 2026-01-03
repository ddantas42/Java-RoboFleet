package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import java.io.Serializable;

public class RInspect extends Robot implements Serializable
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
	public void adicionarTecnico(Tecnico t)
	{
		if (this.equipa.size() >= 2)
		{
			throw new IllegalStateException("R-Inspect max 2 tecnicos");
		}
		super.associarTecnico(t);
	}

	@Override
	public void removerTecnico(Tecnico t)
	{
		super.desassociarTecnico(t);
	}
}