package com.mycompany.java.robofleet.Centro;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.java.robofleet.Robot.*;

public class CentroDeComando {
     private static int contadorIds = 1;
     private int id;
     private ArrayList<Robot> frota;
     private int totalOrdens;
     
     public CentroDeComando(){
        this.id = contadorIds++;
        this.frota = new ArrayList<>();
        this.totalOrdens = 0;
     }

     public void adicionarRobot(Robot robot){
        if (!isNomeUnico(robot.getNome())){
            throw new IllegalArgumentException("Ja existe um robot com esse nome.");
        }

        if (frota.contains(robot)){
            throw new IllegalArgumentException("Esse robot ja existe na frota.");
        }
        this.frota.add(robot);
     }

     

     public boolean removerRobot(int idRobot){
        Robot r = buscarRobot(idRobot);

        if(r != null){
            return this.frota.remove(r);
        }
        return false;
     }

     public Robot buscarRobot(int idRobot){
        for (Robot r : frota){
            if(r.getId() == idRobot){
                return r;
            }
        }
        return null; // se n encontrar retorna null
     }

     public boolean isNomeUnico(String nome){
        for(Robot r : frota){
            if(r.getNome().equalsIgnoreCase(nome)){
                return false;
            }
        }
        return true;
     }

     public List<Robot> getFrota(){
        return this.frota;
     }

     public int getTotalOrdens(){
        return totalOrdens;
     }

     public void incrementarOrdens(){
        this.totalOrdens++;
     }
}