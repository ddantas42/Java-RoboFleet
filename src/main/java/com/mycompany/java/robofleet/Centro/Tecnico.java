package com.mycompany.java.robofleet.Centro;

import com.mycompany.java.robofleet.Centro.EspecialidadeTecnico;

import java.time.LocalDate;

public class Tecnico {

	private static int id = 1;

	private String name;
	private int nif;
	private LocalDate dataNascimento;
	private EspecialidadeTecnico especialidade;

	public Tecnico(String name, int nif, LocalDate dataNascimento, EspecialidadeTecnico especialidade) {

		this.name = name;
		this.nif = nif;
		this.dataNascimento = dataNascimento;
		this.especialidade = especialidade;
		this.id = id++;

	}

	// Requesitos Funcionais
	// 3.1 Técnicos Obter nome, e idade
	public String getName() {
		return name;
	}
	public int getIdade() {
		int years = LocalDate.now().getYear() - dataNascimento.getYear();

		if (LocalDate.now().getDayOfYear() >= dataNascimento.getDayOfYear()) {
			years--;
		}
		return years;
	}

	public EspecialidadeTecnico getEspecialidade() {
		return especialidade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public boolean mesmoTecnico(Tecnico t) {
		return (this.id == t.id || this.nif == t.nif || this.name.equals(t.name));
	}

}