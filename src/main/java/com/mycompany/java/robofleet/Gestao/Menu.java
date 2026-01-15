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
					menuComplexo();
					break;
				case 4:
					centro.radar();
					break;
				case 5:
					//exportarDados();
					break;
				case 0:
					System.out.println("A gravar dados.");
					GestorDeFicheiros.guardarDadosBinario(this.centro, "dados.dat");
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
			System.out.println("(5) Associar tecnico a robot");
			System.out.println("(6) desassociar tecnico a robot");
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
					editarTecnico(); 
					break;
				case 5:
					associarTecnicoRobot();
					break;
				case 6:
					desassociarTecnicoRobot();
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

		try {

			System.out.println("ID do tecnico a associar: ");
			int idTecnico = sc.nextInt();
			sc.nextLine();

			System.out.println("ID do Robo a associar: ");
			int idRobot = sc.nextInt();
			sc.nextLine();

			centro.associarTecnicoRobot(idTecnico, idRobot);

		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

	private void desassociarTecnicoRobot() {
		try {

			System.out.println("ID do tecnico a desassociar: ");
			int idTecnico = sc.nextInt();
			sc.nextLine();

			System.out.println("ID do Robo a desassociar: ");
			int idRobot = sc.nextInt();
			sc.nextLine();

			centro.desassociarTecnicoRobot(idTecnico, idRobot);

		}
		catch (Exception e) {
			System.out.println(e);
		}
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
					editarRobot();
					break;
				case 3:
					removerRobot();
					break;
				case 4:
					if(centro.getRobots().isEmpty()){
						System.out.println("Nenhum robot registado.");
						break;
					}
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

		Zona zonaSelecionada = Zona.ARMAZEM;
	
		System.out.println("A usar ARMAZEM por defeito.");
		
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

			
			System.out.printf("Indique o numero de motores (0 - %d): ", maxMotores);
			int n = sc.nextInt();
			if(n < 1) n = 1;
			if(n > maxMotores) n = maxMotores;

			for(int i = 0; i < n; i++){
				System.out.printf("Potencia do motor %d (W): \n", i + 1);
				novoRobot.adicionarMotorChild(new Motor(sc.nextInt()));
			}
			sc.nextLine();
			centro.registarRobot(novoRobot);
			System.out.println("Robot criado com sucesso.");

		} catch (Exception e){
			System.out.println("Erro: " + e);
			sc.nextLine();
		}
	}



    private void editarRobot(){
        System.out.println("Editar robot:");
        try {

            System.out.println("ID do robot a editar: ");
            int idRobot = sc.nextInt();
            sc.nextLine();

            // procurar robot pelo id
            Robot r = centro.getRobotbyId(idRobot);
            
			editarAtributosRobot(r);

		} catch(Exception e) {
			System.out.println(e);
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

	// ========= DADOS ============
	private void exportarDados() {
		System.out.println("Nome do ficheiro para exportar: ");
		String nomeFicheiro = sc.nextLine();

		if(nomeFicheiro.trim().isEmpty()){
			nomeFicheiro = "robotsLista.txt";
		}
		GestorDeFicheiros.exportarRobotsParaTexto(centro.getRobots(), nomeFicheiro);
	}


	// ================= COMPLEXO =================
	private void menuComplexo() {
		int opcao;
		do {
			System.out.println("\nGestao de Complexo\n");
			System.out.println("(1) Ver Estatisticas (Ordens)");
			System.out.println("(0) Voltar");
			System.out.print("Opcao: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao){
				case 1:
					System.out.println("Total de Ordens executadas: " + centro.getOrdens());
					break;
				case 0:
					break;
				default:
					System.out.println("Opcao invalida!");
			}
		} while(opcao != 0);
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

    public void editarAtributosRobot(Robot r){
        int opcao;
        do {
            System.out.println("\nEditar Robot: " + r.getNome());
            System.out.println("1 - Alterar nome");
            System.out.println("2 - Alterar marca");
            System.out.println("3 - Alterar modelo");
			System.out.println("4 - Substituir motores");
            System.out.println("0 - Sair");
            System.out.print("opcao: ");
			opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();

                    if (!novoNome.isEmpty()) {
                        r.setNome(novoNome);
                        System.out.println("Nome atualizado com sucesso.");
                    } else {
                        System.out.println("Nome invalido. Nome nao alterado.");
                    }
                    break;

                case 2:
                    System.out.print("Nova marca: ");
                    String novaMarca = sc.nextLine();
                    if (
						!novaMarca.isEmpty()) {
                        r.setMarca(novaMarca);
                        System.out.println("Marca atualizada com sucesso.");
                    } else {
                        System.out.println("Marca invalida. Nao alterada.");
                    }
                    break;

				case 3:
                    System.out.print("Novo modelo: ");
                    String novoModelo = sc.nextLine();
                    if (!novoModelo.isEmpty()) {
                        r.setModelo(novoModelo);
                        System.out.println("Modelo atualizado com sucesso.");
                    } else {
                        System.out.println("Modelo invalido. Nao alterado.");
                    }
                    break;

                case 4:
                    r.limparMotores();

					int max = 1;
					if(r instanceof RCarry) max = 4;
					else if(r instanceof RClean || r instanceof RFactory) max = 2;

					System.out.printf("Substituir por quantos motores (1-%d)?\n", max);
					int n = sc.nextInt();
					if(n < 1) n = 1;
					if(n > max) n = max;
					for(int i = 0; i < max; i++){
						System.out.printf("Nova potencia motor %d: \n", i + 1);
						r.adicionarMotorChild(new Motor(sc.nextInt()));
					}
					sc.nextLine();
					System.out.println("Motores substituidos.");
					break;

                case 0:
                    System.out.println("A sair da edicao...");
                	break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

    }

	public void recuperarDados(){
		this.centro = GestorDeFicheiros.recuperarDadosBinario("dados.dat");
	}
}