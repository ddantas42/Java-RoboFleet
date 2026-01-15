package com.mycompany.java.robofleet.Robot;

import com.mycompany.java.robofleet.Centro.Tecnico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que serve de base para todos os robôs do sistema ROBOFLEET.
 * Esta classe define os atributos comuns a todos os modelos, gere a atribuição
 * de identificadores únicos sequenciais e mantém a contagem total da frota.
 */
public abstract class Robot implements Serializable {

    /** Contador estático para geração de IDs únicos e sequenciais. */
    private static int contadorIds = 1;

    /** Contador do número total de robôs instanciados no complexo industrial. */
    private static int totalRobotsExistentes = 0;

    /** Identificador único do robô. */
    protected int id;

    /** Nome identificador do robô. */
    protected String nome;

    /** Marca do fabricante do robô. */
    protected String marca;

    /** Modelo específico do robô. */
    protected String modelo;

    /** Ano em que o robô foi fabricado. */
    protected int anoFabrico;

    /** Zona operacional onde o robô está atualmente alocado. */
    protected Zona zona;

    /** Sistema de bateria instalado no robô. */
    protected Bateria bateria;

    /** Lista de motores que compõem o sistema de propulsão ou operação. */
    protected List<Motor> motores;

    /** Equipa de técnicos atualmente associada à operação ou manutenção do robô. */
    protected List<Tecnico> equipa;

    /** Estado de funcionamento do robô. */
    protected boolean ativo = false;

    /**
     * Construtor da classe Robot.
     * Inicializa os dados base, atribui um ID automático e incrementa o total da frota.
     *
     * @param nome       O nome do robô (obrigatório).
     * @param marca      A marca do fabricante.
     * @param modelo     O modelo do robô.
     * @param anoFabrico O ano de fabrico.
     * @param zona       A zona operacional inicial.
     * @param bateria    O objeto Bateria associado ao robô.
     * @throws IllegalArgumentException Se o nome for nulo ou estiver vazio.
     */
    public Robot(String nome, String marca, String modelo, int anoFabrico, Zona zona, Bateria bateria) {
        
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do robot é obrigatório.");
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
     * Essencial para garantir a continuidade da numeração após o restauro de dados.
     *
     * @param novoValor O valor para o próximo ID a ser atribuído.
     */
    public static void setContadorIds(int novoValor) {
        contadorIds = novoValor;
    }

    /**
     * Devolve o valor atual do contador de IDs.
     *
     * @return O valor inteiro do contador global de IDs.
     */
    public static int getContadorIds() {
        return contadorIds;
    }

    /**
     * Devolve o ano em que o robô foi fabricado.
     * * @return O ano de fabrico (ex: 2024).
     */
    public int getAnoFabrico() {
        return anoFabrico;
    }

    /**
     * Atualiza o ano de fabrico do robô.
     * * @param anoFabrico O novo ano de fabrico a registar.
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
     * Devolve o número total de robôs existentes no sistema.
     *
     * @return O total acumulado de instâncias de robôs.
     */
    public static int getTotalRobots() {
        return totalRobotsExistentes;
    }

    /**
     * Método abstrato para adicionar motores, devendo respeitar os limites 
     * específicos definidos em cada subclasse.
     *
     * @param m O motor a ser adicionado.
     */
    public abstract void adicionarMotorChild(Motor m);

    /**
     * Método abstrato para associar técnicos à equipa, respeitando as regras 
     * e limites de cada modelo de robô.
     *
     * @param t O técnico a associar.
     */
    public abstract void adicionarTecnico(Tecnico t);

    /**
     * Método abstrato para remover um técnico da equipa associada ao robô.
     *
     * @param t O técnico a remover.
     */
    public abstract void removerTecnico(Tecnico t);

    /**
     * Valida se o robô cumpre todos os requisitos (motores e equipa) para entrar em funcionamento.
     *
     * @return {@code true} se o robô puder ser ativado, {@code false} caso contrário.
     */
    public abstract boolean podeSerAtivado();

    /**
     * Adiciona um motor à lista interna de componentes do robô.
     *
     * @param m O motor a adicionar.
     */
    protected void adicionarMotor(Motor m) {
        this.motores.add(m);
    }

    /**
     * Associa um técnico à equipa do robô. Verifica se o técnico já não faz parte da equipa.
     *
     * @param t O técnico a associar.
     * @throws IllegalArgumentException Se o técnico já estiver presente na lista de equipa.
     */
    protected void associarTecnico(Tecnico t) {
        for (Tecnico tecnico : equipa) {
            if (tecnico.getId() == t.getId()) {
                throw new IllegalArgumentException("Técnico já associado ao robô.");
            }
        }
        equipa.add(t);
    }

    /**
     * Realiza a remoção física de um técnico da lista de equipa.
     *
     * @param t O técnico a desassociar.
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
     * Compara este robô com outro objeto para verificar igualdade baseada no ID único.
     *
     * @param obj O objeto a comparar.
     * @return {@code true} se os objetos forem o mesmo ou possuírem o mesmo ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Robot other = (Robot) obj;
        return this.id == other.id;
    }

    /**
     * Gera o código hash para o robô baseado no seu ID único.
     *
     * @return O valor de hash code.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
    
    /** @return O identificador único do robô. */
    public int getId() { return id; }

    /** @return O nome do robô. */
    public String getNome() { return nome; }

    /** @return A zona operacional atual. */
    public Zona getZona() { return zona; }

    /** @param zona Define a nova zona operacional. */
    public void setZona(Zona zona) { this.zona = zona; }

    /** @param nome Define o novo nome do robô. */
    public void setNome(String nome) { this.nome = nome; }

    /** @param marca Define a marca do fabricante. */
    public void setMarca(String marca) { this.marca = marca; }

    /** @param modelo Define o modelo do robô. */
    public void setModelo(String modelo) { this.modelo = modelo; }

    /** @return A marca do fabricante. */
    public String getMarca() { return marca; }

    /** @return O modelo do robô. */
    public String getModelo() { return modelo; }

    /** @return A lista de motores instalados. */
    public List<Motor> getMotores() { return motores; }

    /** @return A equipa de técnicos associada. */
    public List<Tecnico> getEquipa() { return equipa; }

    /**
     * Calcula o consumo total de energia do robô somando a potência de todos os motores.
     *
     * @return O consumo total em Watts (W).
     */
    public double getConsumoTotal() {
        double consumoTotal = 0;
        for (Motor m : motores) {
            consumoTotal += m.getPotencia();
        }
        return consumoTotal;
    }

    /**
     * Calcula a estimativa de autonomia operacional em horas.
     * @return Uma string formatada com as horas de autonomia ou mensagem de consumo zero.
     */
    public String calcularAutonomia() {
        double consumo = getConsumoTotal();
        
        if (consumo == 0) {
            return "Infinito (Sem consumo)";
        }
        
        double autonomia = (double) bateria.getCapacidade() / consumo;
        return String.format("%.2f horas", autonomia);
    }
    
    /**
     * Gera uma representação textual detalhada do robô, incluindo dados da bateria e equipa.
     *
     * @return String com as informações formatadas do robô.
     */
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