package com.mycompany.java.robofleet;

import com.mycompany.java.robofleet.Centro.*;
import com.mycompany.java.robofleet.Robot.*;
import com.mycompany.java.robofleet.Gestao.*;
import java.util.Scanner;
import java.time.LocalDate;

public class JavaRoboFleet {

	public static void main(String[] args) {
		System.out.println("Bem-vindo ao RoboFleet!");
		
		//CentroDeComando centro = new CentroDeComando();
		//Robot robot = new Robot();

		//testes menu
		Scanner sc = new Scanner(System.in);

		try{
			Menu menu = new Menu(sc);
			menu.menuInicialString();
		}catch(Exception e){
			System.out.println(e);
		}finally{
			if(sc != null){
				sc.close();
			}
			System.out.println("TESTE: A terminar main.");
		}

	}
}