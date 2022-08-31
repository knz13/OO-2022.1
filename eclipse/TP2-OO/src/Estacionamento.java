import javax.swing.JOptionPane;

public class Estacionamento {

	// atributos de estacionamento
	private String nomeEstacionamento;
	private double fracaoQuinzeMinutos;
	private double descontoHoraCheia;
	private double diariaDiurna; // sempre que o tempo de estadia do veiculo durar mais de 9 horas!
	private double diariaNoturna; // valor percentual aplicado sobre o valor da diaria diurna
	private double valorMensalista;
	private double retornoContratante; // valor percentual sobre o lucro total que retornara ao contratante do servico
	private int capacidadeEstacionamento;
	private Evento evento; // essa classe precisa ter acesso a classe de eventos
	private Horas horarioFuncionamento;
	private Horas funcionamentoNoturno;
	
	// o construtor para criar novos objetos do tipo estacionamento
	// faltam as horas! ainda tenho que mexer na classe de horas!
	// public Estacionamento(String nomeEstacionamento, double fracaoQuinzeMinutos, double descontoHoraCheia, double diariaDiurna, double diariaNoturna, double valorMensalista, double retornoContratante, int capacidadeEstacionamento) { 
	// isso aqui ta meio paia	
	// }

	public String getNomeEstacionamento() {
		return nomeEstacionamento;
	}

	public boolean setNomeEstacionamento(String nomeEstacionamento) {
		if (nomeEstacionamento == null) {
			JOptionPane.showMessageDialog(null, "Nome do estacionamento não pode ser um valor nulo!");
			return false;
		} else {
			this.nomeEstacionamento = nomeEstacionamento;
			return true;
		}
	}

	public double getFracaoQuinzeMinutos() {
		return fracaoQuinzeMinutos;
	}

	public void setFracaoQuinzeMinutos(double fracaoQuinzeMinutos) {
		this.fracaoQuinzeMinutos = fracaoQuinzeMinutos;
	}

	public double getDescontoHoraCheia() {
		return descontoHoraCheia;
	}

	public void setDescontoHoraCheia(double descontoHoraCheia) {
		this.descontoHoraCheia = descontoHoraCheia;
	}

	public double getDiariaDiurna() {
		return diariaDiurna;
	}

	public void setDiariaDiurna(double diariaDiurna) {
		this.diariaDiurna = diariaDiurna;
	}

	public double getDiariaNoturna() {
		return diariaNoturna;
	}

	public void setDiariaNoturna(double diariaNoturna) {
		this.diariaNoturna = diariaNoturna;
	}

	public double getValorMensalista() {
		return valorMensalista;
	}

	public void setValorMensalista(double valorMensalista) {
		this.valorMensalista = valorMensalista;
	}

	public double getRetornoContratante() {
		return retornoContratante;
	}

	public void setRetornoContratante(double retornoContratante) {
		this.retornoContratante = retornoContratante;
	}

	public int getCapacidadeEstacionamento() {
		return capacidadeEstacionamento;
	}

	public void setCapacidadeEstacionamento(int capacidadeEstacionamento) {
		this.capacidadeEstacionamento = capacidadeEstacionamento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Horas getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(Horas horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	public Horas getFuncionamentoNoturno() {
		return funcionamentoNoturno;
	}

	public void setFuncionamentoNoturno(Horas funcionamentoNoturno) {
		this.funcionamentoNoturno = funcionamentoNoturno;
	}

}
