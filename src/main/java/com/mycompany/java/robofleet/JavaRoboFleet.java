package com.mycompany.java.robofleet;

import com.mycompany.java.robofleet.Centro.*;
import com.mycompany.java.robofleet.Robot.*;
import com.mycompany.java.robofleet.Gestao.*;
import java.util.Scanner;

public class JavaRoboFleet {

    public static void main(String[] args) {
		System.out.println("Bem-vindo ao RoboFleet!");
        
		//CentroDeComando centro = new CentroDeComando();
		//Robot robot = new Robot();

		//testes menu
		Scanner sc = new Scanner(System.in);

		Menu menu = new Menu(sc);
		CentroDeComando centro = menu.getCentro();

		for (int i = 1; i <= 5; i++) {
            
            // 1. Criar R-Carry (Transporte)
            RCarry carry = new RCarry("CarryBot-" + i, "Logistics", "V1", 2024, Zona.ARMAZEM, new Bateria(800, 100), 500.0, true);
            carry.adicionarMotor(new Motor(200));
            carry.adicionarMotor(new Motor(200));
            centro.adicionarRobot(carry);

            // 2. Criar R-Clean (Limpeza)
            RClean clean = new RClean("CleanBot-" + i, "Sanit", "X2", 2023, Zona.TRIAGEM, new Bateria(400, 100), true, true);
            clean.adicionarMotor(new Motor(150));
            centro.adicionarRobot(clean);

            // 3. Criar R-Factory (Produção) - Apenas em Linhas de Produção
            RFactory factory = new RFactory("FactoryBot-" + i, "Indus", "P9", 2022, Zona.LINHA_PROD_1, new Bateria(1000, 100), 4);
            factory.adicionarMotor(new Motor(500));
            factory.adicionarMotor(new Motor(500));
            centro.adicionarRobot(factory);

            // 4. Criar R-Inspect (Inspeção) - Apenas 1 motor (conforme a tua classe)
            RInspect inspect = new RInspect("InspectBot-" + i, "Vision", "S4", 2024, Zona.ARMAZEM, new Bateria(300, 100));
            inspect.adicionarMotor(new Motor(100));
            centro.adicionarRobot(inspect);
        }
		menu.menuInicialString();

		sc.close();


    }
}