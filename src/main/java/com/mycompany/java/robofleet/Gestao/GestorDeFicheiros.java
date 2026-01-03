package com.mycompany.java.robofleet.Gestao;
import com.mycompany.java.robofleet.Centro.CentroDeComando;
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
		// tenta abrir o ficheiro criado antes
		try{
			FileInputStream ficheiro = new FileInputStream(nomeFicheiro);

			// cria o translator 
			ObjectInputStream leitor = new ObjectInputStream(ficheiro);

			CentroDeComando centroRecuperado = (CentroDeComando) leitor.readObject();

			leitor.close();

			System.out.println("Sistema recuperado com sucesso.");
			return centroRecuperado;
		} catch(Exception e){
			// se n existir um antigo, ele cria um novo
			System.out.println(e);
			return new CentroDeComando();
		}


	}
}