/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.java.robofleet;

import com.mycompany.java.robofleet.Centro.*;
import com.mycompany.java.robofleet.Robot.*;
import com.mycompany.java.robofleet.Gestao.*;

// bibliotecas
import java.util.Scanner;



/**
 *
 * @author hiper
 */
public class JavaRoboFleet {

    public static void main(String[] args) {
        
		//CentroDeComando centro = new CentroDeComando();
		//Robot robot = new Robot();

		// testes menu
		Scanner sc = new Scanner(System.in);
		
		Menu menu = new Menu(sc);
		menu.menuInicialString();

		sc.close();


		System.out.println("Bem-vindo ao RoboFleet!");

    }
}
