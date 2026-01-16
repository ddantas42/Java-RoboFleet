package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que serve de base para todos os robôs do sistema ROBOFLEET.
 * Esta classe define os atributos comuns a todos os modelos, gere a atribuição
 * de identificadores unicos sequenciais e mantem a contagem total da frota.
 */
public abstract class Robot implements Serializable {

    /** Contador estatico para geração de IDs unicos e sequenciais. */
    private static int contadorIds = 1;

    /** Contador do numero total de robôs instanciados no complexo industrial. */
    private static int totalRobotsExistentes = 0;

    /** Identificador unico do robô. */
    protected int id;

    /** Nome identificador do robô. */
    protected String nome;

    /** Marca do fabricante do robô. */
    protected String marca;

    /** Modelo especifico do robô. */
    protected String modelo;

    /** Ano em que o robô foi fabricado. */
    protected int anoFabrico;

    /** Zona operacional onde o robô esta atualmente alocado. */
    protected Zona zona;

    /** Sistema de bateria instalado no robô. */
    protected Bateria bateria;

    /** Lista de motores que compõem o sistema de propulsão ou operação. */
    protected List<Motor> motores;

    /** Equipa de tecnicos atualmente associada à operação ou manutenção do robô. */
    protected List<Tecnico> equipa;

    /** Estado de funcionamento do robô. */
    protected boolean ativo = false;

    /**
     * Construtor da classe Robot.
     * Inicializa os dados base, atribui um ID automatico e incrementa o total da frota.
     */
    public Robot(String nome, String marca, String modelo, int anoFabrico, Zona zona, Bateria bateria) {
        
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do robot e obrigatorio.");
        }

        this.id = contadorIds++;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabrico = anoFabrico;
        this.zona = zona;
        this.bateria = bateria;
        this.motores = new ArrayList<>();
        this.equipa = new ArrayList<>();
        
        totalRobotsExistentes++;
    }

    /**
     * Define o ponto de partida para novos IDs. 
     * Essencial para garantir a continuidade da numeração apos o restauro de dados.
     */
    public static void setContadorIds(int novoValor) {
        contadorIds = novoValor;
    }

    /**
     * Devolve o valor atual do contador de IDs.
     */
    public static int getContadorIds() {
        return contadorIds;
    }

    /**
     * Devolve o ano em que o robô foi fabricado.
     */
    public int getAnoFabrico() {
        return anoFabrico;
    }

    /**
     * Atualiza o ano de fabrico do robô.
     */
    public void setAnoFabrico(int anoFabrico) {
        this.anoFabrico = anoFabrico;
    }

    public boolean isAtivo() { 
        return ativo; 
    }

    public void setAtivo(boolean ativo) { 
        this.ativo = ativo; 
    }

    /**
     * Devolve o numero total de robôs existentes no sistema.
     */
    public static int getTotalRobots() {
        return totalRobotsExistentes;
    }

    /**
     * Metodo abstrato para adicionar motores, devendo respeitar os limites 
     * especificos definidos em cada subclasse.
     */
    public abstract void adicionarMotorChild(Motor m);

    /**
     * Metodo abstrato para associar tecnicos à equipa, respeitando as regras 
     * e limites de cada modelo de robô.
     */
    public abstract void adicionarTecnico(Tecnico t);

    /**
     * Metodo abstrato para remover um tecnico da equipa associada ao robô.
     */
    public abstract void removerTecnico(Tecnico t);

    /**
     * Valida se o robô cumpre todos os requisitos (motores e equipa) para entrar em funcionamento.
     */
    public abstract boolean podeSerAtivado();

    /**
     * Adiciona um motor à lista interna de componentes do robô.
     */
    protected void adicionarMotor(Motor m) {
        this.motores.add(m);
    }

    /**
     * Associa um tecnico à equipa do robô. Verifica se o tecnico ja não faz parte da equipa.
     */
    protected void associarTecnico(Tecnico t) {
        for (Tecnico tecnico : equipa) {
            if (tecnico.getId() == t.getId()) {
                throw new IllegalArgumentException("Tecnico ja associado ao robô.");
            }
        }
        equipa.add(t);
    }

    /**
     * Realiza a remoção fisica de um tecnico da lista de equipa.
     */
    protected void desassociarTecnico(Tecnico t) {
        equipa.remove(t);
    }

    /**
     * Remove todos os motores instalados no robô, limpando a lista interna.
     */
    public void limparMotores() {
        this.motores.clear();
    }

    /**
     * Compara este robô com outro objeto para verificar igualdade baseada no ID unico.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Robot other = (Robot) obj;
        return this.id == other.id;
    }

    /**
     * Gera o codigo hash para o robô baseado no seu ID unico.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
    
    public int getId() { return id; }
    public String getNome() { return nome; }
    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }
    public void setNome(String nome) { this.nome = nome; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public List<Motor> getMotores() { return motores; }
    public List<Tecnico> getEquipa() { return equipa; }

    
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
        
        double autonomia = (double) bateria.getCapacidade() / consumo;
        return String.format("%.2f horas", autonomia);
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String status = ativo ? "[ON]" : "[OFF]";

        sb.append(status).append(" ID: ").append(id).append("\n");
        sb.append("\tNome: ").append(nome).append("\n");
        sb.append("\tMarca: ").append(marca).append("\n");
        sb.append("\tModelo: ").append(modelo).append("\n");
        sb.append("\tAno Fabrico: ").append(anoFabrico).append("\n");
        sb.append("\tZona: ").append(zona).append("\n");
        sb.append("\tTipo de robot: ").append(this.getClass().getSimpleName()).append("\n");
        sb.append("\tBateria: ").append(bateria).append("\n");
        sb.append("\tAutonomia: ").append(calcularAutonomia()).append("\n");
        sb.append("\tEquipa: ");
        
        if (equipa.isEmpty()) {
            sb.append("Vazia");
        } else {
            for (Tecnico t : equipa) {
                sb.append(t.getName()).append("; ");
            }
        }
        
        return sb.toString();
    }
}