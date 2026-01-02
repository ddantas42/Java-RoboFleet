package com.mycompany.java.robofleet.Centro;

import java.util.ArrayList;
import com.mycompany.java.robofleet.Robot.*;
import com.mycompany.java.robofleet.Centro.Tecnico;

public class CentroDeComando {

	private ArrayList<Tecnico>	Tecnicos;
	private ArrayList<Robot>	Robots;
	private static int			id = 1;
	private int 				Ordens;

	public CentroDeComando() {
		this.Ordens = 0;
		this.id = id++;
		this.Tecnicos = new ArrayList<Tecnico>();
		this.Robots = new ArrayList<Robot>();
	}

//! ------------------------------- Tecnicos ------------------------------

	public void registarTecnico(Tecnico tecnico) {

		// Ver se tecnico tem mais de 30 anos
		if (tecnico.getIdade() < 30) {
			throw new IllegalArgumentException("Tecnico deve ter pelo menos 30 anos.");
		}

		// Garantir que o seu ID, nome ou NIF nao existe ja
		for (Tecnico t : this.Tecnicos) {
			if (t.mesmoTecnico(tecnico) == true) {
				throw new IllegalArgumentException("Tecnico ja existente.");
			}
		}

		// Po-lo por ordem alfabetica do nome da lista
		this.Tecnicos.add(tecnico);
		this.Tecnicos.sort((t1, t2) -> t1.getName().compareToIgnoreCase(t2.getName()));

	}

	public void removerTecnicoPorNome(String nomeTecnico) {
		for (Tecnico t : this.Tecnicos) {
			if (t.getName().equalsIgnoreCase(nomeTecnico)) {
				this.Tecnicos.remove(t);
				return;
			}
		}
		throw new IllegalArgumentException("Tecnico nao encontrado.");
	}

	public void removerTecnicoPorId(int idTecnico) {

		for (Tecnico t : this.Tecnicos) {
			if (t.getId() == idTecnico) {
				this.Tecnicos.remove(t);
				return;
			}
		}
		throw new IllegalArgumentException("Tecnico nao encontrado.");

	}

	// Lista os Tecnicos por ordem alfabetica porque ja estao ordenados
	public void listarTecnicos() {
		for (Tecnico tecnico : this.Tecnicos) {
			System.out.println(tecnico.toString());
		}
	}

	

//! -------------------------------- Robots -------------------------------
	public void registarRobot(Robot robot) {
		this.Robots.add(robot);
	}

	public boolean removerRobotPorId(int idRobot) {
		Robot r = this.buscarRobot(idRobot);
		if (r != null) {
			return this.Robots.remove(r);
		}
		return true;
	}

	public void listRobots() {
		for (Robot robot : this.Robots) {
			System.out.println(robot.getId());
		}
	}

	public Robot buscarRobot(int idRobot){
		for (Robot r : this.Robots){
			if(r.getId() == idRobot){
				return r;
			}
		}
		return null;
	}

	public void activateAllRobots() {
		for (Robot robot : this.Robots) {
			// TODO robot.activate(); Eventually!
		}
	}

	private void incrementarOrdens() {
		this.Ordens++;
	}

//! -------------------------------- Getters ------------------------------

	public ArrayList<Tecnico> getTecnicos() { return this.Tecnicos; }
	public Tecnico getTecnicobyId(int id) {
		for (Tecnico t : this.Tecnicos) {
			if (t.getId() == id) {
				return t;
			}
		}
		throw new IllegalArgumentException("Tecnico nao encontrado.");
	}
	public ArrayList<Robot> getRobots() { return this.Robots; }
	public int getOrdens() { return this.Ordens; }
	public int getId() { return this.id; }
	 
}
