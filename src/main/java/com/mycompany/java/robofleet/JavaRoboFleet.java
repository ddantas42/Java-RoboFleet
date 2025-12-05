/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.java.robofleet;

import com.mycompany.java.robofleet.Centro.*;
import com.mycompany.java.robofleet.Robot.*;
import com.mycompany.java.robofleet.Gestao.*;



/**
 *
 * @author hiper
 */
public class JavaRoboFleet {

    public static void main(String[] args) {

		int ola = 4;
        
		CentroDeComando centro = new CentroDeComando();
		Robot robot = new Robot();
		Menu menu = new Menu(ola);



		System.out.println("Bem-vindo ao RoboFleet!");

    }
}
