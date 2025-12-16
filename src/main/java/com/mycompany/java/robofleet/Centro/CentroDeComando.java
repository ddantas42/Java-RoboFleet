/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java.robofleet.Centro;
import java.util.ArrayList;
import com.mycompany.java.robofleet.Robot.*;
import com.mycompany.java.robofleet.Centro.Tecnico;

/**
 *
 * @author migue
 */
public class CentroDeComando {
	private ArrayList<Tecnico> Tecnicos;
	private ArrayList<Robot> Robots;

	public CentroDeComando() {
		this.Tecnicos = new ArrayList<Tecnico>();
		this.Robots = new ArrayList<Robot>();
	}

//! ------------------------------- Tecnicos ------------------------------

	public void register(Tecnico tecnico) {
		this.Tecnicos.add(tecnico);
	}

	public void remove(Tecnico tecnico) {
		this.Tecnicos.remove(tecnico);
	}

	public void listTecnicos() {
		for (Tecnico tecnico : this.Tecnicos) {
			System.out.println(tecnico.getName());
		}
	}

//! -------------------------------- Robots -------------------------------
	public void register(Robot robot) {
		this.Robots.add(robot);
	}

	public void remove(Robot robot) {
		this.Robots.remove(robot);
	}

	public void listRobots() {
		for (Robot robot : this.Robots) {
			System.out.println(robot.getId());
		}
	}

	public void activateAllRobots() {
		for (Robot robot : this.Robots) {
			// TODO robot.activate(); Eventually!
		}
	}

	public ArrayList<Tecnico> getTecnicos() { return Tecnicos; }
	public ArrayList<Robot> getRobots() { return Robots; }
	 
}
