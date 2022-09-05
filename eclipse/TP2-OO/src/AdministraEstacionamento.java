import java.util.Iterator;

public class AdministraEstacionamento implements Administrador {


	public void MostrarMenuCriacao() {

		System.out.println("------ CRIACAO DE NOVO ESTACIONAMENTO ------");
		Estacionamento estacionamento = new Estacionamento();
		System.out.print("Digite o nome do estacionamento a ser criado: ");
		

		estacionamento.setNomeEstacionamento(LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
			if(input.equals("-1")){
				System.out.println("Desculpe, o valor -1 esta reservado para uso interno. Por favor, tente novamente.");
				return false;
			}
			return true;
		}));
		
		// preciso agora chamar metodos para definir todos os outros atributos obrigatorios!
		
		System.out.print("Digite a capacidade total do seu estacionamento: ");

		// metodo para definir a capacidade
		estacionamento.setCapacidadeEstacionamento(LidaComInputs.tentarPegarInputInteiroAteDarCerto());
		//System.out.println("Capacidade do estacionamento: " + estacionamento.capacidadeEstacionamento);

		System.out.println("Digite o horário de abertura no formato: " + LidaComInputs.getExemploDeHorario());

		estacionamento.setHorarioAbertura(LidaComInputs.tentarPegarInputDeHora());

		System.out.println("Digite o horário de fechamento do estacionamento no formato: " + LidaComInputs.getExemploDeHorario());
		estacionamento.setHorarioFechamento(LidaComInputs.tentarPegarInputDeHora((horario) -> {
			if(horario.isBefore(estacionamento.getHorarioAbertura())){
				System.out.println("O horario de fechamento inserido e anterior ao horario de abertura! Por favor, tente novamente.");
				return false;
			}
			return true;
		}));
		
		// metodos para definir valores cobrados
		System.out.println("Digite o valor cobrado por fracao de 15 minutos:");
		estacionamento.setFracaoQuinzeMinutos(LidaComInputs.tentarPegarInputDoubleAteDarCerto());
		//System.out.println("Valor cobrado por estadia de 15 minutos: R$" + this.fracaoQuinzeMinutos);
		
		System.out.println("Digite o valor de desconto de hora cheia:");
		estacionamento.setDescontoHoraCheia(LidaComInputs.tentarPegarInputDoubleAteDarCerto());
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
		
		System.out.println("Digite a porcentagem de retorno ao contratante entre 0 e 1:");
		estacionamento.setRetornoContratante(LidaComInputs.tentarPegarInputDoubleAteDarCerto((input) -> {
			if(input > 1){
				System.out.println("O valor de retorno ao contratante nao pode ser maior que 100%! Tente novamente.");
				return false;
			}
			return true;
		}));
		//System.out.println("Porcentagem do valor retornado ao contratante: " + this.retornoContratante);

  		//implementar forma de usuário confirmar se quer realmente criar o estacionamento
		Registro.AdicionarEstacionamento(estacionamento);
		System.out.println("ESTACIONAMENTO CRIADO COM SUCESSO!");
		
	}

	
	

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
