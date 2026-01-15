package com.mycompany.java.robofleet.Gestao;
import com.mycompany.java.robofleet.Centro.CentroDeComando;
import com.mycompany.java.robofleet.Centro.Tecnico;
import com.mycompany.java.robofleet.Robot.Robot;
import java.util.ArrayList;
import java.io.*;

public class GestorDeFicheiros {

	// guarda o estado do centro de comando num ficheiro binario
	public static void guardarDadosBinario(CentroDeComando centro, String nomeFicheiro){
		// cria o ficheiro no pc
		try{
			FileOutputStream ficheiro = new FileOutputStream(nomeFicheiro);
			// cria o gravador
			ObjectOutputStream gravador = new ObjectOutputStream(ficheiro);

			// grava tudo de uma vez
			gravador.writeObject(centro);

			gravador.close();
			
			System.out.println("Dados guardados com sucesso.");

		} catch( Exception e){
			System.out.println("Nao foi possivel gravar: " + e.getMessage());
		}
	}

	// le o ficheiro binario e devolve o objeto centrodecomando
	public static CentroDeComando recuperarDadosBinario(String nomeFicheiro){
		try(ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
			CentroDeComando centroRecuperado = (CentroDeComando) leitor.readObject();

			if(centroRecuperado != null){
				System.out.println("Sistema recuperado com sucesso.");
				return centroRecuperado;
			}

		} catch(FileNotFoundException e){
			System.out.println("Ficheiro nao encontrado. A iniciar sistema novo.");

		}catch(Exception e){
			System.out.println("Erro ao recuperar dados: " + e.getMessage());
		}
		return new CentroDeComando(); // devolve um novo de falhar
	}

	private static void sincronizarIds(CentroDeComando centro){
		// sincroniza ids dos robots
		int maxIdRobot = 0;
		for(Robot r : centro.getRobots()){
			if(r.getId() > maxIdRobot){
				maxIdRobot = r.getId();
			}
		}
		Robot.setContadorIds(maxIdRobot + 1);

		// sincronizar ids tecnicos
		int maxIdTec = 0;
		for(Tecnico t : centro.getTecnicos()){
			if(t.getId() > maxIdTec){
				maxIdTec = t.getId();
			}
		}
		//Tecnico.setContadorIds(maxIdTec + 1);
	}

	public static void exportarRobotsParaTexto(ArrayList<Robot> robots, String nomeFicheiro){
		try(PrintWriter pw = new PrintWriter(new FileWriter(nomeFicheiro))){
			pw.println("Frota Robots:");
			pw.println("Data de exportacao: " + java.time.LocalDate.now());
			
			if(robots == null || robots.isEmpty()){
				pw.println("Nenhum robot registado.");
			} else {
				for(Robot r : robots){
					pw.println(r.toString());
					pw.println("\n");
				}
			}
			System.out.println("Dados exportados para " + nomeFicheiro + " com sucesso.");
		} catch(IOException e){
			System.out.println("Erro ao exportar ficheiro de texto: " + e.getMessage());
		}
	}
}