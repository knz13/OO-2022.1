import java.time.LocalTime;

public class Estacionamento {

	// atributos de estacionamento
	private String nomeEstacionamento;
	private double fracaoQuinzeMinutos;
	private double descontoHoraCheia;
	private double valorHoraCheia;
	private double diariaDiurna; // sempre que o tempo de estadia do veiculo durar mais de 9 horas!
	private double diariaNoturna; // valor percentual aplicado sobre o valor da diaria diurna
	private double valorMensalista;
	private double retornoContratante; // valor percentual sobre o lucro total que retornara ao contratante do servico
	private int capacidadeEstacionamento;
	private LocalTime horarioAbertura;
	private LocalTime horarioFechamento;


	// criando o construtor padrao para um estacionamento!
	
	public Estacionamento() { 

	}

	public String getNomeEstacionamento() {
		return nomeEstacionamento;
	}

	public void setNomeEstacionamento(String nomeEstacionamento) {
		this.nomeEstacionamento = nomeEstacionamento;
	}

	public double getFracaoQuinzeMinutos() {
		return fracaoQuinzeMinutos;
	}

	public void setFracaoQuinzeMinutos(double fracao) {
		
		this.fracaoQuinzeMinutos = fracao;
	}

	public double getDescontoHoraCheia() {
		return descontoHoraCheia;
	}

	// implementacao de um algoritmo para ja calcular o valor da hora cheia em vez de me retornar toda hora
	// um desconto aleatorio
	public void setDescontoHoraCheia(double desconto) {
		this.descontoHoraCheia = desconto;
	}

	public double getDiariaDiurna() {
		return diariaDiurna;
	}

	public void setDiariaDiurna(double diaria) {
		this.diariaDiurna = diaria;
	}

	public double getDiariaNoturna() {
		return diariaNoturna;
	}

	public void setDiariaNoturna(double diaria) {
		this.diariaNoturna = diaria;
	}

	public double getValorMensalista() {
		return valorMensalista;
	}

	public void setValorMensalista(double valor) {
		this.valorMensalista = valor;
	}

	public double getRetornoContratante() {
		return retornoContratante;
	}

	public void setRetornoContratante(double retorno) {
		this.retornoContratante = retorno;
	}

	public int getCapacidadeEstacionamento() {
		return capacidadeEstacionamento;
	}

	public void setCapacidadeEstacionamento(int capacidade) {
		this.capacidadeEstacionamento = capacidade;
	}
	
	public double getValorHoraCheia() {
		return valorHoraCheia;
	}

	public void setValorHoraCheia(double valorHoraCheia) {
		this.valorHoraCheia = valorHoraCheia;
	}

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(LocalTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

}
