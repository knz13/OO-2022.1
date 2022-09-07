import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

public class AdministraEstacionamento implements Administrador {


	public void MostrarMenuCriacao() {

		System.out.println("------ CRIACAO DE NOVO ESTACIONAMENTO ------");
		Estacionamento estacionamento = new Estacionamento();
		System.out.print("Digite o nome do estacionamento a ser criado: ");
		

		estacionamento.setNomeEstacionamento(LidaComInputs.tentarPegarInputAteDarCerto());
		
		// preciso agora chamar metodos para definir todos os outros atributos obrigatorios!
		
		System.out.print("Digite a capacidade total do seu estacionamento: ");

		// metodo para definir a capacidade
		estacionamento.setCapacidadeEstacionamento(LidaComInputs.tentarPegarInputInteiroAteDarCerto());
		//System.out.println("Capacidade do estacionamento: " + estacionamento.capacidadeEstacionamento);

		System.out.println("Digite o horário de abertura no formato: " + LidaComInputs.getExemploDeHorario());

		estacionamento.setHorarioAbertura(LidaComInputs.tentarPegarInputDeHora());

		System.out.println("Digite o horário de fechamento do estacionamento no formato: " + LidaComInputs.getExemploDeHorario());
		estacionamento.setHorarioFechamento(LidaComInputs.tentarPegarInputDeHora((horario) -> {
			// if(horario.isBefore(estacionamento.getHorarioAbertura())){
			// 	System.out.println("O horario de fechamento inserido e anterior ao horario de abertura! Por favor, tente novamente.");
			// 	return false;
			// }
			return true;
		}));
		
		// metodos para definir valores cobrados
		System.out.println("Digite o valor cobrado por fracao de 15 minutos:");
		estacionamento.setFracaoQuinzeMinutos(LidaComInputs.tentarPegarInputDoubleAteDarCerto());
		//System.out.println("Valor cobrado por estadia de 15 minutos: R$" + this.fracaoQuinzeMinutos);
		
		System.out.println("Digite o valor,em porcentagem (0 a 100), do desconto a cada hora cheia:");
		estacionamento.setDescontoHoraCheia(LidaComInputs.tentarPegarInputDoubleAteDarCerto((n) -> {
			if(n > 100){
				System.out.println("Por favor, digite um numero entre 0 e 100.");
				return false;
			}
			return true;
		})/100.0);
		//System.out.println("Desconto aplicado a cada hora cheia: " + this.descontoHoraCheia);
		//System.out.println("Valor cobrado pela hora cheia: R$" + this.valorHoraCheia);
		
		System.out.println("Digite o valor da diária diurna:");
		estacionamento.setDiariaDiurna(LidaComInputs.tentarPegarInputDoubleAteDarCerto());
		//System.out.println("Valor cobrado pela di�ria diurna: R$" + this.diariaDiurna);
		
		System.out.println("Digite o valor da diária noturna:");
		estacionamento.setDiariaNoturna(LidaComInputs.tentarPegarInputDoubleAteDarCerto());
		//System.out.println("Valor cobrado pela di�ria noturna: R$" + this.diariaNoturna);
		
		System.out.println("Digite o valor da mensalidade:");
		estacionamento.setValorMensalista(LidaComInputs.tentarPegarInputDoubleAteDarCerto());
		//System.out.println("Valor cobrado para mensalistas: R$" + this.valorMensalista);
		
		System.out.println("Digite a porcentagem de retorno ao contratante entre 0 e 100:");
		estacionamento.setRetornoContratante(LidaComInputs.tentarPegarInputDoubleAteDarCerto((input) -> {
			if(input > 100){
				System.out.println("O valor de retorno ao contratante nao pode ser maior que 100%! Tente novamente.");
				return false;
			}
			return true;
		})/100.0);
		
  		//implementar forma de usuário confirmar se quer realmente criar o estacionamento
		Registro.AdicionarEstacionamento(estacionamento);
		System.out.println("ESTACIONAMENTO CRIADO COM SUCESSO!");
		
	}

	public void MostrarMenuAtualizacao(Estacionamento estacionamento){

		System.out.println("O que voce gostaria de atualizar no estacionamento '" + estacionamento.getNomeEstacionamento() 	+ "'?\n");

		System.out.println("1 - Nome | Atual => " + estacionamento.getNomeEstacionamento());
		System.out.println("2 - Capacidade | Atual => " + estacionamento.getCapacidadeEstacionamento());

		String tipo = "Normal";
		int index = 12;
		if(estacionamento instanceof Estacionamento24h){
			tipo = "24h";
			index = 10;
		}

		final int indexMaximo = index;

		System.out.println("3 - Tipo | Atual => " + tipo);
		System.out.println("4 - Valor fracao 15 minutos | Atual => " + estacionamento.getFracaoQuinzeMinutos());
		System.out.println("5 - Desconto hora cheia | Atual => " + estacionamento.getDescontoHoraCheia()*100 + "%");
		System.out.println("6 - Valor diaria diurna | Atual => " + estacionamento.getDiariaDiurna());
		System.out.println("7 - Valor diaria noturna | Atual => " + estacionamento.getDiariaNoturna());
		System.out.println("8 - Valor mensalista | Atual => " + estacionamento.getValorMensalista());
		System.out.println("9 - Retorno contratante | Atual => " + estacionamento.getRetornoContratante() * 100 + "%" );
		if(!(estacionamento instanceof Estacionamento24h)){
			System.out.println("10 - Horario de abertura | Atual => " + estacionamento.getHorarioAbertura());
			System.out.println("11 - Horario de fechamento | Atual => " + estacionamento.getHorarioFechamento());
		}
		System.out.println(indexMaximo + " - Voltar");	

		int numero = LidaComInputs.tentarPegarInputInteiroAteDarCerto((n) -> {
			if(n < 1 || n > indexMaximo){
				System.out.println("Por favor, escolha uma das opcoes");
				return false;
			}
			return true;
		});

		switch(numero){
			case 1:
				System.out.println("Digite o novo nome do estacionamento ou -1 para voltar");
				String novoNome = LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
					if(input.equals("-1")){
						MostrarMenuAtualizacao(estacionamento);
						return false;
					}
					return true;
				});
				estacionamento.setNomeEstacionamento(novoNome);
				break;
			case 2:
				System.out.println("Digite a nova capacidade do estacionamento ou -1 para voltar");
				LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
					if(input.equals("-1")){
						MostrarMenuAtualizacao(estacionamento);
						return false;
					}

					try {
						int capacidade = Integer.parseInt(input);

						estacionamento.setCapacidadeEstacionamento(capacidade);

						MostrarMenuAtualizacao(estacionamento);
					}catch (NumberFormatException err){
						System.out.println("O input '" + input + "' nao foi entendido como um numero, tente novamente.");
						return false;
					}

					return true;
				});
				break;
			case 3:
				if(estacionamento instanceof Estacionamento24h){
					System.out.println("Gostaria de mudar para o estacionamento normal? Digite 1 para confirmar e 0 para voltar");

					int opcao = LidaComInputs.tentarPegarInputInteiroAteDarCerto((n) -> {
						if(n > 1) {
							System.out.println("Por favor, escolha uma das opcoes.");
							return false;
						}
						return true;
					});
					if(opcao == 0){
						MostrarMenuAtualizacao(estacionamento);
					}
					else {
						Estacionamento est = new Estacionamento();
						estacionamento.CopiarPara(est);
						Registro.removerEstacionamento(estacionamento.getNomeEstacionamento());
						Registro.AdicionarEstacionamento(est);
						MostrarMenuAtualizacao(est);
					}
				}
				else {
					System.out.println("Gostaria de mudar para o estacionamento 24h? Digite 1 para confirmar e 0 para voltar");

					int opcao = LidaComInputs.tentarPegarInputInteiroAteDarCerto((n) -> {
						if(n > 1) {
							System.out.println("Por favor, escolha uma das opcoes.");
							return false;
						}
						return true;
					});
					if(opcao == 0){
						MostrarMenuAtualizacao(estacionamento);
					}
					else {
						Estacionamento24h est = new Estacionamento24h();
						estacionamento.CopiarPara(est);
						Registro.removerEstacionamento(estacionamento.getNomeEstacionamento());
						Registro.AdicionarEstacionamento(est);
						MostrarMenuAtualizacao(est);
					}
				}
				break;
			case 4:
				System.out.println("Digite o novo valor para a fracao de 15 minutos ou -1 para voltar");
				LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
					if(input.equals("-1")){
						MostrarMenuAtualizacao(estacionamento);
						return false;
					}

					try {
						double fracao = Double.parseDouble(input);

						estacionamento.setFracaoQuinzeMinutos(fracao);

						MostrarMenuAtualizacao(estacionamento);
					}catch (NumberFormatException err){
						System.out.println("O input '" + input + "' nao foi entendido como um numero decimal, tente novamente.");
						return false;
					}
					return true;
				});
				break;
			case 5:
				System.out.println("Digite o novo valor para o desconto de hora cheia, em porcentagem (0 a 100), ou -1 para voltar");
				LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
					if(input.equals("-1")){
						MostrarMenuAtualizacao(estacionamento);
						return false;
					}

					try {
						double fracao = Double.parseDouble(input);

						if(fracao > 100){
							System.out.println("O numero escolhido é maior do que 100, por favor tente novamente.");
							return false;
						}

						estacionamento.setDescontoHoraCheia(fracao/100);

						MostrarMenuAtualizacao(estacionamento);
					}catch (NumberFormatException err){
						System.out.println("O input '" + input + "' nao foi entendido como um numero decimal, tente novamente.");
						return false;
					}
					return true;
				});
				break;
			case 6:
				System.out.println("Digite o novo valor para a diaria diurna ou -1 para voltar");
				LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
					if(input.equals("-1")){
						MostrarMenuAtualizacao(estacionamento);
						return false;
					}

					try {
						double fracao = Double.parseDouble(input);

						estacionamento.setDiariaDiurna(fracao);

						MostrarMenuAtualizacao(estacionamento);
					}catch (NumberFormatException err){
						System.out.println("O input '" + input + "' nao foi entendido como um numero decimal, tente novamente.");
						return false;
					}
					return true;
				});
				break;
			case 7:
				System.out.println("Digite o novo valor para a diaria noturna ou -1 para voltar");
				LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
					if(input.equals("-1")){
						MostrarMenuAtualizacao(estacionamento);
						return false;
					}

					try {
						double fracao = Double.parseDouble(input);

						estacionamento.setDiariaNoturna(fracao);

						MostrarMenuAtualizacao(estacionamento);
					}catch (NumberFormatException err){
						System.out.println("O input '" + input + "' nao foi entendido como um numero decimal, tente novamente.");
						return false;
					}
					return true;
				});
				break;
			case 8:
				System.out.println("Digite o novo valor o valor cobrado aos mensalistas ou -1 para voltar");
				LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
					if(input.equals("-1")){
						MostrarMenuAtualizacao(estacionamento);
						return false;
					}

					try {
						double fracao = Double.parseDouble(input);

						estacionamento.setValorMensalista(fracao);

						MostrarMenuAtualizacao(estacionamento);
					}catch (NumberFormatException err){
						System.out.println("O input '" + input + "' nao foi entendido como um numero, tente novamente.");
						return false;
					}
					return true;
				});
				break;
			case 9:	
				System.out.println("Digite a nova porcentagem de retorno ao contratante (0 a 100) ou -1 para voltar");
				LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
					if(input.equals("-1")){
						MostrarMenuAtualizacao(estacionamento);
						return false;
					}

					try {
						double fracao = Double.parseDouble(input);

						if(fracao > 100){
							System.out.println("O valor da porcentagem de retorno nao pode ser maior que 100%! Por favor, tente novamente.");
							return false;
						}

						estacionamento.setRetornoContratante(fracao/100);

						MostrarMenuAtualizacao(estacionamento);
					}catch (NumberFormatException err){
						System.out.println("O input '" + input + "' nao foi entendido como um numero, tente novamente.");
						return false;
					}
					return true;
				});
				break;
			case 10:
				if(estacionamento instanceof Estacionamento24h){
					MostrarMenuDeObjeto(estacionamento);
				}
				else {
					System.out.println("Digite o novo horario de abertura no formato" + LidaComInputs.getExemploDeHorario() + " ou '-1' para voltar.\n");
					LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
						if(input.equals("-1")){
							MostrarMenuAtualizacao(estacionamento);
							return false;
						}
						try {
							LocalTime time = LocalTime.parse(input,LidaComInputs.getFormatterHora());
							estacionamento.setHorarioAbertura(time);
							return true;

						}
						catch(DateTimeParseException err){
							System.out.println("A hora informada nao e valida. Tente novamente.");
							return false;
						}
					});
				}
				break;
			case 11:
				System.out.println("Digite o novo horario de fechamento no formato" + LidaComInputs.getExemploDeHorario() + " ou '-1' para voltar.\n");
				LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
					if(input.equals("-1")){
						MostrarMenuAtualizacao(estacionamento);
						return false;
					}
					try {
						LocalTime time = LocalTime.parse(input,LidaComInputs.getFormatterHora());
						estacionamento.setHorarioFechamento(time);
						return true;

					}
					catch(DateTimeParseException err){
						System.out.println("A hora informada nao e valida. Tente novamente.");
						return false;
					}
				});
				break;
			case 12:
				MostrarMenuDeObjeto(estacionamento);
				
		}

	};
	

	@Override
	public int GetNumeroDeObjetos() {
		return Registro.getBancoDeDados().getEstacionamentos().size();
	}


	public void MostrarMenuDeObjeto(Estacionamento estacionamento){
		
		System.out.println("O que voce gostaria de fazer com esse estacionamento?\n");

        System.out.println("1 - Atualizar");
        System.out.println("2 - Remover");
        System.out.println("3 - Voltar");

        int numero = LidaComInputs.tentarPegarInputInteiroAteDarCerto((n) -> {
            if(n > 3 || n < 1){
                System.out.println("Por favor, escolha uma das opções.");
                return false;
            }
            return true;
        });

        switch(numero){
            case 1:
				MostrarMenuAtualizacao(estacionamento);
                break;
            case 2:

				System.out.println("Tem certeza que deseja remover o estacionamento " + estacionamento.getNomeEstacionamento() + "? Digite 1 para deletar e 0 para cancelar.\nAviso: todos os eventos e acessos associados serao removidos.");
				
				int outroNumero = LidaComInputs.tentarPegarInputInteiroAteDarCerto((n) -> {
					if(n > 1 || n < 0){
						System.out.println("Por favor, escolha uma das opções.");
						return false;
					}
					return true;
				});

				if(outroNumero == 0){
					MostrarMenuDeObjeto(estacionamento);
				}


				Iterator<Evento> it = Registro.getBancoDeDados().getEventos().iterator();

				while(it.hasNext()){
					Evento ev = it.next();

					if(ev.getEstacionamento() == estacionamento){
						it.remove();
					}
				}

				Iterator<Acesso> acessoIt = Registro.getBancoDeDados().getAcessos().iterator();

				while(acessoIt.hasNext()){
					Acesso ac = acessoIt.next();

					if(ac.getEstacionamento() == estacionamento){
						acessoIt.remove();
					}
				}
				

				Registro.removerEstacionamento(estacionamento.getNomeEstacionamento());
				System.out.println("Estacionamento removido com sucesso!");
				Menu.MostrarInterface(this, "Estacionamentos");
                break;
            case 3:
                Menu.MostrarMenuPrincipal();
                break;
        }

	}	

	@Override
	public void MostrarMenuPesquisa() {
		System.out.println(Registro.listarEstacionamentosSemIndex());

		
		Estacionamento estacionamento = null;
		while(true){
			System.out.print("Digite o nome do estacionamento que voce deseja visualizar ou digite '-1' para voltar: ");
			String nome = LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
				if(input.equals("-1")){
					Menu.MostrarInterface(this, "Estacionamentos");
					return false;
				}
				return true;
			});

			try {
				estacionamento = Registro.pesquisarEstacionamento(nome);
				
				break;
			}
			catch(ObjetoNaoEncontradoException err){
				System.out.println(err.getMessage() + "\nPor favor, tente novamente.");
			}
			
		}
		MostrarMenuDeObjeto(estacionamento);

	}
	
}
