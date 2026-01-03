package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import java.io.Serializable;

public class RCarry extends Robot implements Serializable
{

	private double capacidadeCarga;
	private boolean temHolofote;

	public RCarry(String nome, String marca, String modelo, int ano, Zona zona, Bateria bat, double carga, boolean holofote)
	{
		super(nome, marca, modelo, ano, zona, bat);
		this.capacidadeCarga = carga;
		this.temHolofote = holofote;
	}

	// fui comer qualquer coisa, se nao correr , comenta a funcao recuperar dados no final do menu
	@Override
	public void adicionarMotor(Motor m)
	{
		if (motores.size() >= 4)
		{
			throw new IllegalStateException("R-Carry max 4 motores");
		}
		super.adicionarMotor(m);
	}

	@Override
	public void adicionarTecnico(Tecnico t)
	{
		if (this.equipa.size() >= 3)
		{
			throw new IllegalStateException("R-Carry max 3 tecnicos");
		}
		super.associarTecnico(t);
	}

	@Override
	public void removerTecnico(Tecnico t)
	{
		super.desassociarTecnico(t);
	}

	@Override
	public String toString() {
		return super.toString() + 
			" [Tipo: R-Carry | Carga: " + capacidadeCarga + "kg | Holofote: " + temHolofote + "]";
	}
}