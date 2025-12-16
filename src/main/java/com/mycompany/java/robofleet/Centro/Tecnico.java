package com.mycompany.java.robofleet.Centro;
import java.util.List;
import java.util.Scanner;

import com.mycompany.java.robofleet.Robot.Especializacao;
import com.mycompany.java.robofleet.Robot.Motor;
import com.mycompany.java.robofleet.Robot.Robot;

public class Tecnico {
    private Scanner sc;

    private static int contadorIds = 1;
    
    private int id;
    private String nome;
    private int idade;
    private Especializacao especializacao;
    private int nif;
    private List<Robot> robotsAtribuidos; // Não sei como isto vai funcionar


    public Tecnico(String nome, int idade, Especializacao especializacao, int nif, List<Robot> robotsAtribuidos) {
        this.id = contadorIds++;
        if (nome == null)
        {
            throw new IllegalArgumentException("Nome nao pode ser nulo");
        }
        this.nome = nome;
        this.idade = idade;
        this.especializacao = especializacao;
        this.nif = nif;
        this.robotsAtribuidos = robotsAtribuidos;
    }

    public void criarTecnico(List<Robot> robotsAtribuidos) {
        sc = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Idade: ");
        int idade = sc.nextInt();

        System.out.print("NIF: ");
        int nif = sc.nextInt();

        System.out.print("Especializacao: ");
        String especializacaoInput = sc.nextLine();
        Especializacao especializacao;
        
        // Por alterar
        try {
            especializacao = Especializacao.valueOf(especializacaoInput.toUpperCase());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Especializacao invalida. Definindo como ROBOTICA por padrao.");
            especializacao = Especializacao.ROBOTICA;
        }

        

        // Por alterar
        Tecnico tecnico = new Tecnico(nome, idade, especializacao, nif, robotsAtribuidos);
        System.out.println("Tecnico criado com sucesso!" );
    }


}