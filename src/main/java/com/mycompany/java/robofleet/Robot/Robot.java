package com.mycompany.java.robofleet.Robot;

import java.util.ArrayList;
import java.util.List;

public abstract class Robot
{
    private static int contadorIds = 1;

    protected int id;
    protected String nome;
    protected String marca;
    protected String modelo;
    protected int anoFabrico;
    protected Zona zona;
    protected Bateria bateria;
    protected List<Motor> motores;
    //protected List<Tecnico> equipa;

    public Robot(String nome, String marca, String modelo, int anoFabrico, Zona zona, Bateria bateria)
    {
        if (nome == null || nome.isEmpty())
        {
            throw new IllegalArgumentException("Nome inválido");
        }

        this.id = contadorIds++;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabrico = anoFabrico;
        this.zona = zona;
        this.bateria = bateria;
        this.motores = new ArrayList<>();
        //this.equipa = new ArrayList<>();
    }

    public void adicionarMotor(Motor m)
    {
        this.motores.add(m);
    }

    /*
    public void associarTecnico(Tecnico t)
    {
        if (!equipa.contains(t))
        {
            equipa.add(t);
        }
    }

    public void desassociarTecnico(Tecnico t)
    {
        equipa.remove(t);
    }

    public abstract boolean validarEquipa();
*/
    public int getId()
    {
        return id;
    } 

    public String getNome()
    {
        return nome;
    }

    public Zona getZona()
    {
        return zona;
    }

    public void setZona(Zona zona){
        this.zona = zona;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }

    public List<Motor> getMotores() {
        return motores;
    }
    
    public double getConsumoTotal() {
        double consumoTotal = 0;
        for (Motor m : motores) {
            consumoTotal += m.getPotencia();
        }
        return consumoTotal;
    }

    public String calcularAutonomia() {
        double consumo = getConsumoTotal();
        
        if (consumo == 0) {
            return "Infinito (Sem consumo)";
        }
        
        
        double autonomia = bateria.getCapacidade() / consumo; 
        
        // Formata para 2 casas decimais
        return String.format("%.2f horas", autonomia);
    }

    @Override
    public String toString()
    {
        return "ID: " + id + " | " + nome + " | Zona: " + zona + " | Autonomia: " + calcularAutonomia();
    }
}