package com.mycompany.java.robofleet;

import com.mycompany.java.robofleet.Gestao.Menu;
import java.util.Scanner;
import java.io.File;
/* the main */
public class JavaRoboFleet {

    /**
     * Ponto de entrada principal da aplicação.
     * * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao RoboFleet!");

        Scanner sc = new Scanner(System.in);
        String ficheiroDados = "dados.dat";

        try {
            // Inicialização da interface de Menu
            Menu menu = new Menu(sc);

            File f = new File(ficheiroDados);
            if (f.exists()) {
                System.out.println("Detecao de dados anteriores. A restaurar sistema...");
                
                menu.recuperarDados();
            }

            // Início do ciclo de vida da interface textual
            menu.menuInicialString();

        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado no arranque do sistema: " + e.getMessage());
        } finally {
            // Fecho do Scanner para evitar fugas de recursos
            sc.close();
            System.out.println("Sistema RoboFleet encerrado.");
        }
    }
}