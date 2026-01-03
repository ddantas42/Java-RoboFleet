package com.mycompany.java.robofleet.Gestao;

import com.mycompany.java.robofleet.Centro.CentroDeComando;
import com.mycompany.java.robofleet.Robot.Robot;
import java.io.*;
import java.util.ArrayList;

public class GestorDeFicheiros {

    /**
     * Guarda o estado completo do Centro de Comando em formato binário.
     * Cumpre o requisito de preservação de estado entre execuções.
     */
    public static void guardarDadosBinario(CentroDeComando centro, String nomeFicheiro) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFicheiro))) {
            oos.writeObject(centro);
            System.out.println("Dados do sistema guardados com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro crítico ao gravar ficheiro binário: " + e.getMessage());
        }
    }

    /**
     * Recupera o estado do Centro de Comando do ficheiro binário.
     * Implementa a função RECOVER exigida pelo enunciado.
     */
    public static CentroDeComando recuperarDadosBinario(String nomeFicheiro) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
            CentroDeComando centroRecuperado = (CentroDeComando) ois.readObject();
            System.out.println("Sistema recuperado com sucesso.");
            return centroRecuperado;
        } catch (FileNotFoundException e) {
            System.out.println("Aviso: Ficheiro de dados não encontrado. Iniciando novo sistema.");
            return new CentroDeComando();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao recuperar dados: " + e.getMessage());
            return new CentroDeComando();
        }
    }

    /**
     * Exporta a lista de robôs para um ficheiro de texto formatado (.txt).
     * Cumpre o requisito funcional de exportação de dados[cite: 73, 85].
     */
    public static void exportarRobotsTxt(ArrayList<Robot> robots, String nomeFicheiro) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeFicheiro))) {
            writer.println("==================================================");
            writer.println("           LISTAGEM DE FROTA ROBOFLEET           ");
            writer.println("==================================================");
            writer.println("Data de Exportação: " + java.time.LocalDateTime.now());
            writer.println();

            if (robots.isEmpty()) {
                writer.println("Nenhum robô registado no sistema.");
            } else {
                for (Robot r : robots) {
                    writer.println(r.toString());
                    writer.println("--------------------------------------------------");
                }
            }
            System.out.println("Lista de robôs exportada com sucesso para: " + nomeFicheiro);
        } catch (IOException e) {
            System.err.println("Erro ao exportar ficheiro de texto: " + e.getMessage());
        }
    }
}