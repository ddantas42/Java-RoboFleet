package com.mycompany.java.robofleet.Centro;

import java.io.Serializable;
import java.util.ArrayList;
import com.mycompany.java.robofleet.Robot.Robot;
import com.mycompany.java.robofleet.Centro.Tecnico;
import com.mycompany.java.robofleet.Robot.Zona;
import java.util.Scanner;


public class CentroDeComando implements Serializable{

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
		Scanner sc = new Scanner(System.in);
		ArrayList<Tecnico> sortedList = new ArrayList<>(this.Tecnicos);

		System.out.println("\n(1) Nome | (2) ID | (3) Idade");
		int opcao = sc.nextInt();

		switch (opcao) {
			case 1:
				sortedList.sort((t1, t2) -> t1.getName().compareToIgnoreCase(t2.getName()));
				break;
			case 2:
				sortedList.sort((t1, t2) -> Integer.compare(t1.getId(), t2.getId()));
				break;
			case 3:
				sortedList.sort((t1, t2) -> Integer.compare(t1.getIdade(), t2.getIdade()));
				break;
		}

		for (Tecnico t : sortedList) {
			System.out.println(t);
		}

	}

	
	
	public void associarTecnicoRobot(int idTecnico, int idRobot) {

        Robot robo = this.getRobotbyId(idRobot);
        Tecnico tec = this.getTecnicobyId(idTecnico);

        if (tec.isInTeam()) {
            throw new IllegalArgumentException("Erro: O tecnico " + tec.getName() + " já faz parte de uma equipa.");
        }

        robo.adicionarTecnico(tec);
        
        tec.setInTeam(true);
        
        System.out.println("Tecnico " + tec.getName() + " associado com sucesso ao Robot " + robo.getNome());
    }

	public void desassociarTecnicoRobot(int idTecnico, int idRobot) {

	Robot robo = this.getRobotbyId(idRobot);
	Tecnico tec = this.getTecnicobyId(idTecnico);

	robo.removerTecnico(tec);
	tec.setInTeam(false);
	}
	

//! -------------------------------- Robots -------------------------------
	public void adicionarRobot(Robot novo) {
        for (Robot r : Robots) {
            if (r.getNome().equalsIgnoreCase(novo.getNome())) {
                throw new IllegalArgumentException("Erro: Já existe um robot com o nome " + novo.getNome());
            }
        }
        this.Robots.add(novo);
    }
	
	public void listarRobots() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nMenu Listar Robots\n");
		System.out.println("(1) Listar por ID dentro de uma Zona");
		System.out.println("(2) Listar por Ordem de Marca (Alfabetica)");
		System.out.println("(3) Listar por Ano de fabrico (Anos e Meses)");
		System.out.print("Opcao: ");

		int opcao = sc.nextInt();
		sc.nextLine();

		ArrayList<Robot> sortedList = new ArrayList<>(this.Robots);

		switch (opcao) {
			case 1:
				// Primeiro perguntamos a zona
				System.out.println("Escolha a Zona (ARMAZEM, TRIAGEM, LINHA_PROD_1, LINHA_PROD_2, ESTACAO_CARGA):");
				String zonaInput = sc.nextLine().toUpperCase();
				
				// Filtramos apenas os daquela zona e depois ordenamos por ID
				sortedList.removeIf(r -> !r.getZona().toString().equals(zonaInput));
				sortedList.sort((r1, r2) -> Integer.compare(r1.getId(), r2.getId()));
				break;

			case 2:
				// Ordenação por Marca (A-Z)
				sortedList.sort((r1, r2) -> r1.getMarca().compareToIgnoreCase(r2.getMarca()));
				break;

			case 3:
				// Ordenação por Ano de Fabrico (Decrescente - do mais novo para o mais velho)
				sortedList.sort((r1, r2) -> Integer.compare(r2.getAnoFabrico(), r1.getAnoFabrico()));
				break;
				
			default:
				System.out.println("Opção inválida.");
				break;
		}
		
		// Impressão dos resultados
		System.out.println("\nResultados da Listagem");
		java.time.LocalDate hoje = java.time.LocalDate.now();

		for (Robot robot : sortedList) {
			if (opcao == 3) {
				
				int anos = hoje.getYear() - robot.getAnoFabrico();
				int meses = hoje.getMonthValue(); // Meses desde o início do ano atual
				
				System.out.println(robot.toString());
				System.out.println("\tTempo desde o fabrico: " + anos + " anos e " + meses + " meses.");
			} else {
				System.out.println(robot.toString());
			}
		}
	}

	public boolean removerRobotPorId(int idRobot) {
		Robot r = this.buscarRobot(idRobot);
		
		if (r != null) {
			for (Tecnico t : r.getEquipa()) {
				t.setInTeam(false); 
			}
			
			System.out.println("Robot " + r.getNome() + " removido e equipa libertada.");
			return this.Robots.remove(r);
		}
		
		System.out.println("Erro: Robot com ID " + idRobot + " não encontrado.");
		return false;
	}

	public void listRobots() {
		for (Robot robot : this.Robots) {
			System.out.println(robot.toString());
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

	/**
	 * ativa um robot especifico e incrementa a contagem de ordens
	 */
	public boolean ativarRobot(int idRobot){
		Robot r = this.buscarRobot(idRobot);
		
		if(r != null && r.podeSerAtivado()){
			this.incrementarOrdens();
			System.out.println("Ordem enviada. Robot " + r.getNome() + " ativado.");
			return true;
		}
		return false;
	}

	public void definirZona(int idRobot, Zona novaZona) {
		Robot r = this.buscarRobot(idRobot);
		
		if (r == null) {
			throw new IllegalArgumentException("Erro: Robot com ID " + idRobot + " não encontrado.");
		}
		try {
			r.setZona(novaZona); 
			System.out.println("Sucesso: Robot " + r.getNome() + " movido para " + novaZona);
			
		} catch (IllegalArgumentException e) {
			System.out.println("Operacao Negada: " + e.getMessage());
		}
	}

	public void activateAllRobots() {
		int ativadosAgora = 0;
		for (Robot robot : this.Robots) {
			// Verifica se cumpre os requisitos (motores/equipa) e se já não está ativo
			if (robot.podeSerAtivado() && !robot.isAtivo()) {
				robot.setAtivo(true);
				this.incrementarOrdens();
				ativadosAgora++;
			}
		}
		System.out.println("Processo concluído. " + ativadosAgora + " novos robots ativados.");
	}

	public void radar() {
		for (Robot robot : this.Robots) {
			System.out.println("Robot ID: " + robot.getId() + " - Zona: " + robot.getZona());
		}
	}

	private void incrementarOrdens() {
		this.Ordens++;
	}

//! -------------------------------- Complexo -----------------------------

	public void consultarEstadoComplexo(){
		System.out.println("Estado complexo: ");
		System.out.println("Total de ordens: " + this.Ordens);
		System.out.println("Frota atual: ");
		if(this.Robots.isEmpty()){
			System.out.println("Nenhum robot registado.");
		} else {
			for(Robot r : this.Robots){
				System.out.println("Nome: " + r.getNome() + " | ID: " + r.getId() + " | Localizacao: " + r.getZona());
			}
		}
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
	
	public Robot getRobotbyId(int id) {
		for (Robot r : this.Robots) {
			if (r.getId() == id) {
				return r;
			}
		}
		throw new IllegalArgumentException("Robo nao encontrado.");
	}
	
	public int getOrdens() { return this.Ordens; }
	public int getId() { return this.id; }
}