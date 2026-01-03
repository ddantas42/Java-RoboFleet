package com.mycompany.java.robofleet;

import com.mycompany.java.robofleet.Gestao.Menu;
import java.util.Scanner;

public class JavaRoboFleet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(sc);
        menu.menuInicialString();
        sc.close();
    }
}