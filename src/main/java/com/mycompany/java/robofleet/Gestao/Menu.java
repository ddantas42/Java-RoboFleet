/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.java.robofleet.Gestao;
import java.util.Scanner;

/**
 *
 * @author migue
 */
public class Menu {
    private Scanner sc;

    // construtor
    public Menu(Scanner sc){
        this.sc = sc;
    }

    public void menuInicialString(){
        int opcao;

        do {
            System.out.println("Menu Gestao\n");
            System.out.println("(1) Gerir tecnicos"); // criar, editar, remover, listar, associar/desassociar
            System.out.println("(2) Gerir Robots"); // criar, editar, remover, listar
            System.out.println("(3) Gerir zonas");
            System.out.println("(4) Gerir complexo");
            System.out.println("(5) Utilizar radar");
            System.out.println("(6) Exportar dados");
            System.out.println("(0) Sair");
            System.out.println("Opcao: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); // consume \n

        } while(opcao != 0);
    }

}
