/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java.robofleet.Gestao;

/**
 *
 * @author migue
 */
public class Menu {
    private int opc;

    // construtor
    public Menu(int opc){
        this.opc = opc;
    }

    public int getOpc(){
        return opc;
    }

    public void setOpc(int opc){
        this.opc = opc;
    }

    public String menuInicialString(){
        return "\nMenu de gestao\n(1) Gerir tecnicos\n(2) Gerir robots\n(3) Gerir zonas\n(4) Gerir complexo\n(5) Exportar dados\nOpcao: ";
    }

}
