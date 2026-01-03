package com.mycompany.java.robofleet.Centro;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Tecnico implements Serializable {
    private int id;
    private String name;
    private int nif;
    private LocalDate dataNascimento;
    private EspecialidadeTecnico especialidade;

    public Tecnico(String name, int nif, LocalDate dataNascimento, EspecialidadeTecnico especialidade) {
        this.name = name;
        this.nif = nif;
        this.dataNascimento = dataNascimento;
        this.especialidade = especialidade;
    }

    public int getIdade() {
        return (int) ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
    }

    public boolean mesmoTecnico(Tecnico t) {
        return (this.nif == t.nif || this.name.equalsIgnoreCase(t.name));
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public int getNif() { return nif; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public EspecialidadeTecnico getEspecialidade() { return especialidade; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + name + " (" + getIdade() + " anos) | NIF: " + nif + " | Esp: " + especialidade;
    }
}