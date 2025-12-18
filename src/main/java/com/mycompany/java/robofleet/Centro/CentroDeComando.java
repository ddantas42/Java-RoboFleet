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
		this.Tecnicos.add(tecnico);
	}

	public void removerTecnicoPorNome(String nomeTecnico) {
		// Todo procurar tecnico pelo nome
		// this.Tecnicos.remove(tecnico);
	}

	public void removerTecnicoPorId(int idTecnico) {
		// Todo procurar tecnico pelo id
		// this.Tecnicos.remove(tecnico);
	}

	public void listTecnicos() {
		for (Tecnico tecnico : this.Tecnicos) {
			System.out.println(tecnico.getName());
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
	public ArrayList<Robot> getRobots() { return this.Robots; }
	public int getOrdens() { return this.Ordens; }
	public int getId() { return this.id; }
	 
}
