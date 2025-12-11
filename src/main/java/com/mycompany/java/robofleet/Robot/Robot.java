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

    public Robot(String nome, String marca, String modelo, int anoFabrico, Zona zona, Bateria bateria)
    {

        if (nome == null)
        {
            throw new IllegalArgumentException("Nome nao pode ser nulo");
        }

        this.id = contadorIds++;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabrico = anoFabrico;
        this.zona = zona;
        this.bateria = bateria;
        this.motores = new ArrayList<>();
    }

    public static int getContadorIds()
    {
        return contadorIds;
    }

    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public int getAnoFabrico()
    {
        return anoFabrico;
    }

    public void setAnoFabrico(int anoFabrico)
    {
        this.anoFabrico = anoFabrico;
    }

    public Bateria getBateria()
    {
        return bateria;
    }

    public void setBateria(Bateria bateria)
    {
        this.bateria = bateria;
    }

    public List<Motor> getMotores()
    {
        return motores;
    }

    public void setMotores(List<Motor> motores)
    {
        this.motores = motores;
    }

    public abstract boolean validarEquipa();

    public int getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public Zona getZona()
    {
        return zona;
    }

    @Override
    public String toString()
    {
        return "ID: " + id + " | " + nome + " | " + zona;
    }
}