package com.mycompany.java.robofleet.Centro;

import com.mycompany.java.robofleet.Robot.*;
import java.io.Serializable;
import com.mycompany.java.robofleet.Centro.EspecialidadeTecnico;
import java.time.LocalDate;

public class Tecnico implements Serializable {

	private static int id = 1;

	private String name;
	private int nif;
	private LocalDate dataNascimento;
	private EspecialidadeTecnico especialidade;
	private boolean inTeam;

	public Tecnico(String name, int nif, LocalDate dataNascimento, EspecialidadeTecnico especialidade) {

		this.name = name;
		this.nif = nif;
		this.dataNascimento = dataNascimento;
		this.especialidade = especialidade;
		this.id = id++;
		this.inTeam = false;

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

	public void setEspecialidade(EspecialidadeTecnico nova_especialidade) {
		this.especialidade = nova_especialidade;
	}

	public void setName(String novo_nome) {
		this.name = novo_nome;
	}

	public EspecialidadeTecnico getEspecialidade() {
		return especialidade;
	}
	
	public int getId(){
		return id;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public boolean mesmoTecnico(Tecnico t) {
		return (this.id == t.id || this.nif == t.nif || this.name.equals(t.name));
	}

	public boolean setInTeam(boolean inTeam) {
		this.inTeam = inTeam;
		return this.inTeam;
	}

	public String toString() {
		return "ID: " + id + ", Nome: " + name + ", NIF: " + nif + ", Data de Nascimento: " + dataNascimento
				+ ", Especialidade: " + especialidade;
	}

}