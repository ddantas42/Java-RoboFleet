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
					exportarDados();
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

			Especializacao esp;

			switch(op){
				case 1:
					esp = Especializacao.ROBOTICA;
					break;
				case 2:
					esp = Especializacao.MANUTENCAO;
					break;
				case 3:
					esp = Especializacao.SISTEMAS;
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
			if(t == null){
				System.out.println("Tecnico nao encontrado.");
				return;
			}
			
			System.out.println("Novo nome do Tecnico: ");
            String novo_nome = sc.nextLine();
			if (novo_nome == null || novo_nome == "") {
				novo_nome = t.getName();
			}	
			

			Especializacao nova_especialidade = alt_esp();

			t.adicionarEspecialidade(nova_especialidade);
			t.setName(novo_nome);

		}

		catch (Exception e) {
			System.out.println(e);
		}
		
	}

	private void associarTecnicoRobot() {
		try {
			System.out.println("ID do tecnico a associar: ");
			int idTecnico = lerInteiro();

			System.out.println("ID do Robo a associar: ");
			int idRobot = lerInteiro();

			centro.associarTecnicoRobot(idTecnico, idRobot);
		}
		catch (Exception e) {
			System.out.println("Erro na associacao: " + e.getMessage());
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
		System.out.println("Indique o nome do Robot: "); String nome = sc.nextLine();
		System.out.println("Indique a marca: "); String marca = sc.nextLine();
		System.out.println("Indique o modelo: "); String modelo = sc.nextLine();
		System.out.println("Indique o ano de fabrico: "); int ano = lerInteiro();

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

		System.out.println("Indique a potencia do motor (W): ");
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
			centro.adicionarRobot(novoRobot);
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
            int idRobot = lerInteiro();
            // procurar robot pelo id
            Robot r = centro.getRobotbyId(idRobot);

			int opcao;
			do {
				System.out.println("\n--- Edicao de Robot: " + r.getNome());
				System.out.println("(1) Editar Atributos Basicos (Nome, Marca, Modelo)");
				System.out.println("(2) Gerir Equipa Tecnica");
				System.out.println("(3) Gerir Motores");
				System.out.println("(0) Voltar");
				System.out.print("Opcao: ");
				
				opcao = lerInteiro();

				switch(opcao){
					case 1:
						editarAtributosRobot(r);
						break;
					case 2:
						gerirEquipa(r);
						break;
					case 3:
						substituirMotores(r);
						break;
					case 0:
						System.out.println("A voltar.");
						break;
					default:
						System.out.println("Opcao invalida.");
				}
			} while(opcao != 0);

		} catch(Exception e) {
			System.out.println("Erro: " + e.getMessage());
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

	private void gerirEquipa(Robot r){
		int op;
		
		do {
			System.out.println("Gestao de equipa do robot: " + r.getNome());
			System.out.println("(1) Adicionar Tecnico");
			System.out.println("(2) Remover Tecnico");
			System.out.println(("(0) Sair"));

			op = lerInteiro();

			switch (op) {
				case 1:
					System.out.println("Lista de Tecnicos Disponiveis (sem quipa):");
					boolean encontrouDisponivel = false;
					for (Tecnico t : centro.getTecnicos()) {
						if (!t.isInTeam()) {
							System.out.println(t.getId() + " - " + t.getName());
							encontrouDisponivel = true;
						}
					}

					if(!encontrouDisponivel){
						System.out.println("Nenhum tecnico disponivel");
					} else {
						System.out.println("ID do tecnico a associar: ");
						int idT = lerInteiro();
						try{
							centro.associarTecnicoRobot(idT, r.getId());
							System.out.println("Tecnico associado.");
						} 
						catch (Exception e){
							System.out.println(e);
						}	
					}	
					break;
				case 2:
					// Lógica para remover (percorrer r.getEquipa() e libertar o técnico)
					List<Tecnico> equipaAtual = r.getEquipa();
					if(equipaAtual.isEmpty()){
						System.out.println("O robot nao tem tecnicos associados.");
						break;
					} else {
						System.out.println("Lista de tecnicos na equipa: ");
						for(Tecnico t : equipaAtual){
							System.out.println("ID: " + t.getId() + " | Nome: " + t.getName());
						}
						System.out.println("ID do tecnico a remover: ");
						int idRemover = lerInteiro();
						try{
							centro.desassociarTecnicoRobot(idRemover, r.getId());
							System.out.println("Tecnico removido da equipa.");
						} catch(Exception e){
							System.out.println("Erro : " + e.getMessage());
						}
					}
					break;
				case 0: 
					System.out.println("A sair da gestao de equipa.");
					break;
				default:
					System.out.println("Opcao invalida.");
			}
		}while (op != 0);
    }


	// ========= DADOS ============
	private void exportarDados() {
		System.out.println("Nome do ficheiro para exportar (ex: ficheiro.txt):");
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
			System.out.println("(1) Consultar estado complexo");
			System.out.println("(2) Alterar zona de Robot");
			System.out.println("(3) Ativar Robot");
			System.out.println("(0) Voltar");
			System.out.print("Opcao: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao){
				case 1:
					centro.consultarEstadoComplexo();
					break;
					
				case 2:
					System.out.println("ID do Robot: ");
					int idZona = sc.nextInt();
					sc.nextLine();

					System.out.println("Zonas disponives: 1-ARMAZEM, 2-TRIAGEM, 3-LINHA_PROD_1, 4-LINHA_PROD_2, 5-ESTACAO_CARGA");
					System.out.println("Escolha a zona: ");
					int Z = sc.nextInt();
					sc.nextLine();

					Zona novaZonaZ;
					
					switch(Z){
						case 1: 
							novaZonaZ = Zona.ARMAZEM;
							break;
						case 2:
							novaZonaZ = Zona.TRIAGEM;
							break;
						case 3:
							novaZonaZ = Zona.LINHA_PROD_1;
							break;
						case 4:
							novaZonaZ = Zona.LINHA_PROD_2;
							break;
						default:
							novaZonaZ = Zona.ESTACAO_CARGA;
							break;
					};
					try{
						centro.definirZona(idZona, novaZonaZ);
						System.out.println("Zona atualizada.");
					} catch(Exception e){
						System.out.println(e);
					}
					break;
				case 3:
					System.out.println("ID do robot a ativar: ");
					int idRobot = sc.nextInt();
					sc.nextLine();
					if(centro.ativarRobot(idRobot)){
						System.out.println("Robot ativado com sucesso.");
					}
					else{
						System.out.println("Requisitos minimos nao atingidos ou IDRobot nao encontrado.");
					}
					break;
				case 0:
					System.out.println("A voltar ao menu principal.");
					break;
				default:
					System.out.println("Opcao invalida!");
			}

		} while(opcao != 0);
	}

    // ========== FUNÇÕES AUXILIARES ==========

    public Especializacao alt_esp() {

		System.out.println("Especialidade:");
		System.out.println("1 - Robotica");
		System.out.println("2 - Manutencao");
		System.out.println("3 - Sistemas");

		int op = sc.nextInt();
		sc.nextLine();

		Especializacao esp;

		switch(op){
			case 1:
				esp = Especializacao.ROBOTICA;
				break;
			case 2:
				esp = Especializacao.MANUTENCAO;
				break;
			case 3:
				esp = Especializacao.SISTEMAS;
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
					for(int i = 0; i < n; i++){
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

	private void substituirMotores(Robot r){
		r.limparMotores();
		int max = 1;
		if(r instanceof RCarry) max = 4;
		else if(r instanceof RClean || r instanceof RFactory) max = 2;

		System.out.printf("Substituir por quantos motores (1-%d)?\n", max);
		int n = lerInteiro();

		if(n < 1) n = 1;
		if(n > max) n = max;

		for(int i = 0; i < n; i++){
			System.out.printf("Nova potencia motor %d (W): \n", i + 1);
			int pot = lerInteiro();
			r.adicionarMotorChild(new Motor(pot));
		}
		System.out.println("Motores substituidos.");
	}

	private void associarTecnicoARobot(){
		System.out.println("ID do robot: ");
		int idR = lerInteiro();
		System.out.println("ID do tecnico: ");
		int idT = lerInteiro();

		try{
			centro.associarTecnicoRobot(idT, idR);
			System.out.println("Tecnico associado.");
		} catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private void adicionarMotorRobot(){
		System.out.println("ID do robot: ");
		int idR = lerInteiro();
		System.out.println("Potencia do motor (w): ");
		int pot = lerInteiro();

		try{
			Robot r = centro.buscarRobot(idR);
			r.adicionarMotorChild(new Motor(pot));
			System.out.println("Motor instalado.");
		} catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private int lerInteiro(){
		while(!sc.hasNextInt()){
			System.out.println("Insira um numero valido.");
			sc.nextLine();
		}
		int val = sc.nextInt();
		sc.nextLine();
		return val;
	}

	private Especializacao escolherEspecialidade(int op){
		Especializacao esp;

		switch (op) {
			case 1:
				esp = Especializacao.ROBOTICA;
				break;
			case 2:
				esp = Especializacao.MANUTENCAO;
				break;
			default:
				esp = Especializacao.SISTEMAS;
				break;
		};
		return esp;
	}

	private Zona escolherZona(int op){
		Zona zona;
		switch(op){
			case 1:
				zona = Zona.ARMAZEM;
				break;
			case 2:
				zona = Zona.TRIAGEM;
				break;
			case 3:
				zona = Zona.LINHA_PROD_1;
				break;
			case 4:
				zona = Zona.LINHA_PROD_2;
				break;
			default:
				zona = Zona.ESTACAO_CARGA;
				break;
		};

		return zona;
	}

	public void recuperarDados(){
		this.centro = GestorDeFicheiros.recuperarDadosBinario("dados.dat");
	}
}