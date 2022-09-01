import java.util.Scanner;

public class Estacionamento {

	public static Scanner sc = new Scanner(System.in);
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
	private Evento evento; // essa classe precisa ter acesso a classe de eventos
	private Horas horarioFuncionamento;
	private Horas funcionamentoNoturno;
	
	// criando o construtor padrao para um estacionamento!
	
	public Estacionamento(String nomeEstacionamento) { 
		setNomeEstacionamento(nomeEstacionamento);
		System.out.print("Estacionamento criado: " + this.nomeEstacionamento);
		
		// preciso agora chamar metodos para definir todos os outros atributos obrigatorios!
		
		// metodo para definir a capacidade
		setCapacidadeEstacionamento();
		System.out.println("Capacidade do estacionamento: " + this.capacidadeEstacionamento);
		
		// metodos para definir valores cobrados
		setFracaoQuinzeMinutos();
		System.out.println("Valor cobrado por estadia de 15 minutos: R$" + this.fracaoQuinzeMinutos);
		
		setDescontoHoraCheia();
		System.out.println("Desconto aplicado a cada hora cheia: " + this.descontoHoraCheia);
		System.out.println("Valor cobrado pela hora cheia: R$" + this.valorHoraCheia);
		
		setDiariaDiurna();
		System.out.println("Valor cobrado pela diária diurna: R$");
		
		setDiariaNoturna();
		System.out.println("Valor cobrado pela diária noturna: R$");
		
		setValorMensalista();
		System.out.println("Valor cobrado para mensalistas: R$");
		
		setRetornoContratante();
		System.out.println("Porcentagem do valor retornado ao contratante: ");
		
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

	public void setFracaoQuinzeMinutos() {
		System.out.print("Digite o valor cobrado pela estadia de 15 minutos: ");
		double fracaoQuinzeMinutos = sc.nextDouble(); // atentar-se ao ponto e a virgula (decimais)
		this.fracaoQuinzeMinutos = fracaoQuinzeMinutos;
	}

	public double getDescontoHoraCheia() {
		return descontoHoraCheia;
	}

	// implementacao de um algoritmo para ja calcular o valor da hora cheia em vez de me retornar toda hora
	// um desconto aleatorio
	public void setDescontoHoraCheia() {
		System.out.print("Digite o desconto aplicado a cada hora cheia: ");
		double descontoHoraCheia = sc.nextDouble(); // atentar-se ao ponto e a virgula (decimais)
		this.descontoHoraCheia = descontoHoraCheia;
		double valorHoraCheia = this.fracaoQuinzeMinutos * 4 * this.descontoHoraCheia;
		setValorHoraCheia(valorHoraCheia); 
	}

	public double getDiariaDiurna() {
		return diariaDiurna;
	}

	public void setDiariaDiurna() {
		System.out.print("Digite o valor cobrado pela diária diurna: ");
		double diariaDiurna = sc.nextDouble(); // atentar-se ao ponto e a virgula (decimais)
		this.diariaDiurna = diariaDiurna;
	}

	public double getDiariaNoturna() {
		return diariaNoturna;
	}

	public void setDiariaNoturna() {
		System.out.print("Digite o valor cobrado pela diária noturna: ");
		double diariaNoturna = sc.nextDouble(); // atentar-se ao ponto e a virgula (decimais)
		this.diariaNoturna = diariaNoturna;
	}

	public double getValorMensalista() {
		return valorMensalista;
	}

	public void setValorMensalista() {
		System.out.print("Digite o valor cobrado para mensalistas: ");
		double valorMensalista = sc.nextDouble(); // atentar-se ao ponto e a virgula (decimais)
		this.valorMensalista = valorMensalista;
	}

	public double getRetornoContratante() {
		return retornoContratante;
	}

	public void setRetornoContratante() {
		System.out.print("Digite, em decimal, o valor retornado ao contratante: ");
		double retornoContratante = sc.nextDouble(); // atentar-se ao ponto e a virgula (decimais)
		this.retornoContratante = retornoContratante;
	}

	public int getCapacidadeEstacionamento() {
		return capacidadeEstacionamento;
	}

	public void setCapacidadeEstacionamento() {
		System.out.print("Digite a capacidade total do seu estacionamento: ");
		int capacidadeEstacionamento = sc.nextInt();
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

	public double getValorHoraCheia() {
		return valorHoraCheia;
	}

	public void setValorHoraCheia(double valorHoraCheia) {
		this.valorHoraCheia = valorHoraCheia;
	}

}
