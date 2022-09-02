import java.util.Scanner;

public class CriaEstacionamento {


	public static void MostrarNoMenu() {

		System.out.println("------ CRIACAO DE NOVO ESTACIONAMENTO ------");
		Scanner sc = new Scanner(System.in);
		Estacionamento estacionamento = new Estacionamento();
		System.out.print("Digite o nome do estacionamento a ser criado: ");
		

		estacionamento.setNomeEstacionamento(LidaComInputs.tentarPegarInputAteDarCerto(sc));
		
		// preciso agora chamar metodos para definir todos os outros atributos obrigatorios!
		
		System.out.print("Digite a capacidade total do seu estacionamento: ");

		// metodo para definir a capacidade
		estacionamento.setCapacidadeEstacionamento(LidaComInputs.tentarPegarInputInteiroAteDarCerto(sc));
		//System.out.println("Capacidade do estacionamento: " + estacionamento.capacidadeEstacionamento);

		System.out.println("Digite o horário de abertura no formato: " + LidaComInputs.getExemploDeHorario());

		estacionamento.setHorarioAbertura(LidaComInputs.tentarPegarInputDeHora(sc));

		System.out.println("Digite o horário de fechamento do estacionamento no formato: " + LidaComInputs.getExemploDeHorario());
		estacionamento.setHorarioFechamento(LidaComInputs.tentarPegarInputDeHora(sc,(horario) -> {
			if(horario.isBefore(estacionamento.getHorarioAbertura())){
				System.out.println("O horario de fechamento inserido e anterior ao horario de abertura! Por favor, tente novamente.");
				return false;
			}
			return true;
		}));
		
		// metodos para definir valores cobrados
		System.out.println("Digite o valor cobrado por fração de 15 minutos:");
		estacionamento.setFracaoQuinzeMinutos(LidaComInputs.tentarPegarInputDoubleAteDarCerto(sc));
		//System.out.println("Valor cobrado por estadia de 15 minutos: R$" + this.fracaoQuinzeMinutos);
		
		System.out.println("Digite o valor de desconto de hora cheia:");
		estacionamento.setDescontoHoraCheia(LidaComInputs.tentarPegarInputDoubleAteDarCerto(sc));
		//System.out.println("Desconto aplicado a cada hora cheia: " + this.descontoHoraCheia);
		//System.out.println("Valor cobrado pela hora cheia: R$" + this.valorHoraCheia);
		
		System.out.println("Digite o valor da diária diurna:");
		estacionamento.setDiariaDiurna(LidaComInputs.tentarPegarInputDoubleAteDarCerto(sc));
		//System.out.println("Valor cobrado pela di�ria diurna: R$" + this.diariaDiurna);
		
		System.out.println("Digite o valor da diária noturna:");
		estacionamento.setDiariaNoturna(LidaComInputs.tentarPegarInputDoubleAteDarCerto(sc));
		//System.out.println("Valor cobrado pela di�ria noturna: R$" + this.diariaNoturna);
		
		System.out.println("Digite o valor da mensalidade:");
		estacionamento.setValorMensalista(LidaComInputs.tentarPegarInputDoubleAteDarCerto(sc));
		//System.out.println("Valor cobrado para mensalistas: R$" + this.valorMensalista);
		
		System.out.println("Digite a porcentagem de retorno ao contratante entre 0 e 1:");
		estacionamento.setRetornoContratante(LidaComInputs.tentarPegarInputDoubleAteDarCerto(sc,(input) -> {
			try {
				if(Double.parseDouble(input) > 1){
					System.out.println("O valor de retorno ao contratante nao pode ser maior que 100%! Tente novamente.");
					return false;
				}
			}
			catch(NumberFormatException err) {
				; // o erro ja vai ser tratado após a função
			}
			return true;
		}));
		//System.out.println("Porcentagem do valor retornado ao contratante: " + this.retornoContratante);

  		//implementar forma de usuário confirmar se quer realmente criar o estacionamento
		Registro.AdicionarEstacionamento(estacionamento);
		sc.close();
		System.out.println("ESTACIONAMENTO CRIADO COM SUCESSO!");
		
	}
	
}
