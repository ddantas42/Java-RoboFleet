package com.mycompany.java.robofleet.Gestao;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.mycompany.java.robofleet.Centro.*;
import com.mycompany.java.robofleet.Robot.*;

public class Menu {

	private Scanner sc;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private CentroDeComando centro;

	public Menu(Scanner sc){
		this.sc = sc;
		this.centro = new CentroDeComando();
	}

	// ================= MENU PRINCIPAL =================
	public void menuInicialString(){
		int opcao;

		do {
			System.out.println("\nMenu Gestao\n");
			System.out.println("(1) Gerir tecnicos");
			System.out.println("(2) Gerir Robots");
			System.out.println("(3) Gerir complexo");
			System.out.println("(4) Utilizar radar");
			System.out.println("(5) Exportar dados");
			System.out.println("(0) Sair");
			System.out.print("Opcao: ");
			
			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao) {
				case 1:
					menuTecnicos();
					break;
				case 2:
					menuRobots();
					break;
				case 3:
					//menuComplexo();
					break;
				case 4:
					//menuRadar();
					break;
				case 5:
					//exportarDados();
					break;
				case 0:
					System.out.println("A sair...");
					break;
				default:
					System.out.println("Opcao invalida!");
			}

		} while(opcao != 0);
	}

	// ================= TECNICOS =================
	private void menuTecnicos() {
		int opcao;

		do {
			System.out.println("\nGestao de Tecnicos\n");
			System.out.println("(1) Criar tecnico");
			System.out.println("(2) Remover tecnico");
			System.out.println("(3) Listar tecnicos");
			System.out.println("(4) Editar tecnico");
			System.out.println("(5) Associar/desassociar tecnico a robot");
			System.out.println("(0) Voltar");
			System.out.print("Opcao: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao){
				case 1:
					criarTecnico();
					break;
				case 2:
					removerTecnico();
					break;
				case 3:
					centro.listarTecnicos();
					break;
				case 4:
					editarTecnico(); // diaduiahdiawd
					break;
				case 5:
					associarTecnicoRobot();
					break;
				case 0:
					System.out.println("A voltar...");
					break;
				default:
					System.out.println("Opcao invalida!");
			}

		} while(opcao != 0);
	}

	private void criarTecnico() {
		try {
			System.out.print("Nome: ");
			String nome = sc.nextLine();

			System.out.print("NIF: ");
			int nif = sc.nextInt();
			sc.nextLine();

			System.out.print("Data de nascimento (AAAA-MM-DD): ");
			LocalDate dataNascimento = LocalDate.parse(sc.nextLine());

			System.out.println("Especialidade:");
			System.out.println("1 - Robotica");
			System.out.println("2 - Manutencao");
			System.out.println("3 - Sistemas");

			int op = sc.nextInt();
			sc.nextLine();

			EspecialidadeTecnico esp;

			switch(op){
				case 1:
					esp = EspecialidadeTecnico.ROBOTICA;
					break;
				case 2:
					esp = EspecialidadeTecnico.MANUTENCAO;
					break;
				case 3:
					esp = EspecialidadeTecnico.SISTEMAS;
					break;
				default:
					System.out.println("Opcao invalida.");
					return;
			}

			Tecnico t = new Tecnico(nome, nif, dataNascimento, esp);
			centro.registarTecnico(t);

			System.out.println("Tecnico criado com sucesso.");

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			sc.nextLine();
		}
	}

	private void removerTecnico() {
		try {
			System.out.print("ID do tecnico a remover: ");
			int id = sc.nextInt();
			sc.nextLine();

			centro.removerTecnicoPorId(id);
			System.out.println("Tecnico removido com sucesso.");

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			sc.nextLine();
		}
	}

	private void editarTecnico() { 
		System.out.println("Editar tecnico:");
        try {

            System.out.println("ID do tecnico a editar: ");
            int idTecnico = sc.nextInt();
            sc.nextLine();

            // procurar tec pelo id
            Tecnico t = centro.getTecnicobyId(idTecnico);
			
			System.out.println("Novo nome do Técnico: ");
            String novo_nome = sc.nextLine();
			if (novo_nome == null || novo_nome == "") {
				novo_nome = t.getName();
			}	
			

			EspecialidadeTecnico nova_especialidade = alt_esp();

			t.setEspecialidade(nova_especialidade);
			t.setName(novo_nome);

		}

		catch (Exception e) {
			System.out.println(e);
		}
		
	}

	private void associarTecnicoRobot() {
		System.out.println("Associar/desassociar tecnico a robot..."); 
	}

	// ================= ROBOTS =================
	private void menuRobots() {
		int opcao;
		do {
			System.out.println("\nGestao de Robots\n");
			System.out.println("(1) Criar robot");
			System.out.println("(2) Editar robot");
			System.out.println("(3) Remover robot");
			System.out.println("(4) Listar robots");
			System.out.println("(0) Voltar ao menu principal");
			System.out.print("Opcao: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao){
				case 1:
					criarRobot();
					break;
				case 2:
					//editarRobot();
					break;
				case 3:
					removerRobot();
					break;
				case 4:
					centro.listRobots();
					break;
				case 0:
					System.out.println("A regressar ao menu principal.");
					break;
				default:
					System.out.println("Opcao invalida!");
			}

		} while(opcao != 0);
	}

	private void criarRobot() { 
		System.out.println("Indique o nome do Robot: ");
		String nome = sc.nextLine();

		System.out.println("Indique a marca: ");
		String marca = sc.nextLine();

		System.out.println("Indique o modelo: ");
		String modelo = sc.nextLine();

		System.out.println("Indique o ano de fabrico: ");
		int ano;
		try { 
			ano = sc.nextInt(); 
		} catch (java.util.InputMismatchException e){
			System.out.println("Ano de fabrico invalido. Acao cancelada");
			sc.nextLine();
			return;
		}
		sc.nextLine();

		System.out.println("Indique a capacidade da bateria: ");
		int cap;
		try{ 
			cap = sc.nextInt(); 
		} catch (java.util.InputMismatchException e) {
			System.out.println("Capacidade para a bateria invalido. Acao cancelada");
			sc.nextLine();
			return;
		}
		sc.nextLine();

		System.out.println("Indique a potência do motor (W): ");
		int potenciaMotor;
		try{
			potenciaMotor = sc.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("Potência do motor invalida. Acao cancelada");
			sc.nextLine();
			return;
		}
		sc.nextLine();

		Bateria bateria = new Bateria(cap, 100);

		System.out.println("Indique a zona de operacao:");
		Zona[] zonasDisponiveis = Zona.values();
		for(int i = 0; i < zonasDisponiveis.length; i++){
			System.out.println("(" + (i + 1) + ") " + zonasDisponiveis[i]);
		}
		System.out.println("Opcao de zona: ");
		int indiceZona = sc.nextInt() - 1;
		sc.nextLine();

		Zona zonaSelecionada = Zona.ARMAZEM;
		if(indiceZona >= 0 && indiceZona < zonasDisponiveis.length){
			zonaSelecionada = zonasDisponiveis[indiceZona];
		} else {
			System.out.println("Zona invalida. A usar ARMAZEM por defeito.");
		}

		System.out.println("\nQual o tipo de Robot?");
		System.out.println("(1) R-Carry (Transporte)");
		System.out.println("(2) R-Clean (Limpeza)");
		System.out.println("(3) R-Factory (Producao)");
		System.out.println("(4) R-Inspect (Inspecao)");
		System.out.print("Opcao: ");
		
		int tipoRobot = sc.nextInt();
		sc.nextLine(); 

		Robot novoRobot = null;

		try {
			switch(tipoRobot){
				case 1:
					System.out.println("Capacidade carga (kg): ");
					double carga = sc.nextDouble();
					sc.nextLine();
					System.out.println("Tem holofote (s/n): ");
					boolean holofote = sc.nextLine().equalsIgnoreCase("s");

					novoRobot = new RCarry(nome, marca, modelo, ano, zonaSelecionada, bateria, carga, holofote);
					break;
				case 2:
					System.out.println("Modo de limpeza (ex: aspirar, secar): ");
					String modo = sc.nextLine();
					System.out.println("Tem luz auxiliar? (s/n): ");
					boolean luz = sc.nextLine().equalsIgnoreCase("s");
					System.out.println("Tem succao (s/n): ");
					boolean succao = sc.nextLine().equalsIgnoreCase("s");

					novoRobot = new RClean(nome, marca, modelo, ano, zonaSelecionada, bateria, succao, luz);
					break;
				case 3:
					System.out.println("Numero de bracos roboticos: ");
					int bracos = sc.nextInt();
					sc.nextLine();
					novoRobot = new RFactory(nome, marca, modelo, ano, zonaSelecionada, bateria, bracos);
					break;
				case 4:
					novoRobot = new RInspect(nome, marca, modelo, ano, zonaSelecionada, bateria);
					break;
				default:
					System.out.println("Tipo de robot  inválido.");
					return;
			}

			// Adicionar motores
			int maxMotores = 1;
			if(novoRobot instanceof RCarry) maxMotores = 4;
			else if (novoRobot instanceof RClean || novoRobot instanceof RFactory) maxMotores = 2;

			int numMotores = 0;
			do {
				System.out.println("Indique o numero de motores: ");
				try {
					numMotores = sc.nextInt();
					sc.nextLine();
				} catch (java.util.InputMismatchException e){
					System.out.println("Numero invalido. Tente novamente.");
					sc.nextLine();
					numMotores = 0;
				}
			} while(numMotores < 1 || numMotores > maxMotores);

			for(int i = 0; i < numMotores; i++){
				if (novoRobot instanceof RCarry){
						((RCarry) novoRobot).adicionarMotor(new Motor(potenciaMotor));
				} else if (novoRobot instanceof RClean) {
						((RClean) novoRobot).adicionarMotor(new Motor(potenciaMotor));
				} else if (novoRobot instanceof RFactory) {
						((RFactory) novoRobot).adicionarMotor(new Motor(potenciaMotor));
				} else if (novoRobot instanceof RInspect) {
						((RInspect) novoRobot).adicionarMotor(new Motor(potenciaMotor));
				}
			}

			centro.registarRobot(novoRobot);
			System.out.println("Robot criado com sucesso.");

		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao criar robot: " + e.getMessage());
		} catch(Exception e){
			System.out.println("Erro inesperado: " + e.getMessage());
		}
	}

	private void removerRobot() {
		try {
			System.out.print("ID do robot a remover: ");
			int idRobot = sc.nextInt();
			sc.nextLine();

			boolean removido = centro.removerRobotPorId(idRobot);

			if(removido) {
				System.out.println("Robot removido com sucesso.");
			} else {
				System.out.println("Robot nao encontrado.");
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			sc.nextLine();
		}
	}
    // ========== FUNÇÕES AUXILIARES ==========

    public EspecialidadeTecnico alt_esp() {

		System.out.println("Especialidade:");
		System.out.println("1 - Robotica");
		System.out.println("2 - Manutencao");
		System.out.println("3 - Sistemas");

		int op = sc.nextInt();
		sc.nextLine();

		EspecialidadeTecnico esp;

		switch(op){
			case 1:
				esp = EspecialidadeTecnico.ROBOTICA;
				break;
			case 2:
				esp = EspecialidadeTecnico.MANUTENCAO;
				break;
			case 3:
				esp = EspecialidadeTecnico.SISTEMAS;
				break;
			default:
				throw new IllegalArgumentException("Opcao invalida.");
		}

		return esp;
    }
}






