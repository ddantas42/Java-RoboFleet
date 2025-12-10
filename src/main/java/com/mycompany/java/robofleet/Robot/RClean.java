package com.mycompany.java.robofleet.Robot;

public class RClean extends Robot
{
    private String modoDeLimpeza;
    private boolean temLuzAuxiliar;

    public RClean(String nome, String marca, String modelo, int ano, Zona zona, Bateria bateria, String modoDeLimpeza, boolean temLuzAuxiliar){
        super(nome, marca, modelo, ano, zona, bateria);
        this.modoDeLimpeza = modoDeLimpeza;
        this.temLuzAuxiliar = temLuzAuxiliar;
    }
    

public void adicionarMotor(Motor m)
    {
        if (motores.size() < 2)
        {
            motores.add(m);
        } else
        {
            System.out.println("Erro: RClean só pode ter até 2 motores.");
        }
    }

    @Override
    public boolean validarEquipa()
    {
        return true; 
    }

        @Override
    public String toString()
    {
        return super.toString() + " [Tipo: R-Clean] Modo de Limpeza: " + modoDeLimpeza;
    }
}