package com.mycompany.java.robofleet.Centro;

import com.mycompany.java.robofleet.Robot.Especializacao;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Tecnico implements Serializable {

    private static int contadorIds = 1;

    private int id;
    private String name;
    private int nif;
    private LocalDate dataNascimento;
    private List<Especializacao> especialidades;
    private boolean inTeam;

    public Tecnico(String name, int nif, LocalDate dataNascimento, Especializacao especialidadeInicial) {
        this.id = contadorIds++;
        this.name = name;
        this.nif = nif;
        this.dataNascimento = dataNascimento;
        this.especialidades = new ArrayList<>();
        if (especialidadeInicial != null) {
            this.especialidades.add(especialidadeInicial);
        }
        this.inTeam = false;
    }

    public Tecnico(String name, int nif, LocalDate dataNascimento) {
        this(name, nif, dataNascimento, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String novo_nome) {
        this.name = novo_nome;
    }

    public int getIdade() {
        if (dataNascimento == null) return 0;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public List<Especializacao> getEspecialidades() {
        return new ArrayList<>(especialidades);
    }

    public void adicionarEspecialidade(Especializacao e) {
        if (e != null && !this.especialidades.contains(e)) {
            this.especialidades.add(e);
        }
    }

    public int getId() {
        return id;
    }

    public static void setContadorIds(int valor) {
        contadorIds = valor;
    }

    public int getNif() {
        return nif;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public boolean isInTeam() {
        return inTeam;
    }

    public void setInTeam(boolean inTeam) {
        this.inTeam = inTeam;
    }

    public boolean mesmoTecnico(Tecnico t) {
        if (t == null) return false;
        return (this.nif == t.nif);
    }

    @Override
    public String toString() {
        return "ID: " + id + 
               ", Nome: " + name + 
               ", NIF: " + nif + 
               ", Idade: " + getIdade() + 
               ", Especialidades: " + especialidades;
    }
}