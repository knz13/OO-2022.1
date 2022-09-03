import java.time.LocalDateTime;

public class AdministraEvento implements Administrador{

    @Override
    public void MostrarMenuPesquisa() {
        System.out.println(Registro.listarEventosSemIndex());

        Evento evento = null;
		while(true){
			System.out.print("Digite o nome do evento que voce deseja visualizar ou digite '-1' para voltar: ");
			String nome = LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                if(input.equals("-1")){
                    Menu.MostrarInterface(this, "Eventos");
                    return false;
                }
                return true;
            });

			try {
				evento = Registro.pesquisarEvento(nome);
				break;
			}
			catch(ObjetoNaoEncontradoException err){
				System.out.println(err.getMessage() + "\nPor favor, tente novamente.");
			}
			
		}

        MostrarMenuDeObjeto(evento);

    }

    public void MostrarMenuDeObjeto(Evento evento){

        System.out.println("O que voce gostaria de fazer com esse evento?\n");

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
                break;
            case 3:
                Menu.MostrarMenuPrincipal();
                break;
        }

    }

    
    @Override
    public int GetNumeroDeObjetos() {
        // TODO Auto-generated method stub
        return Registro.getBancoDeDados().getEventos().size();
    }


    @Override
    public void MostrarMenuCriacao() {

        if(Registro.getBancoDeDados().getEstacionamentos().size() == 0){
            System.out.println("Nao ha nenhum estacionamento no banco de dados, portanto, nao e possivel criar um evento...");
            return;
        }


        System.out.println("------ CRIACAO DE NOVO EVENTO ------");
		Evento evento = new Evento();

		System.out.print("Digite o nome do evento a ser criado: ");
        
        evento.setNomeDoEvento(LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
            if(input.equals("-1")){
                System.out.println("Desculpe, o valor -1 esta reservado para uso interno. Por favor, tente novamente.");
                return false;
            }
            return true;
        }));
        
        System.out.println(Registro.listarEstacionamentos());
        
		System.out.print("Digite o numero do estacionamento relacionado ao evento: ");

        int index = LidaComInputs.tentarPegarInputInteiroAteDarCerto((input) -> {
            if(input > Registro.getBancoDeDados().getEstacionamentos().size() - 1){
                System.out.println("Por favor insira um numero valido para o estacionamento!");
                return false;
            } 

            return true;
        });

        evento.setEstacionamento(Registro.getBancoDeDados().getEstacionamentos().get(index));

		System.out.print("Digite a hora de abertura do evento durante o dia " + LidaComInputs.getExemploDeHorario() + ": ");
        
        evento.setHorarioDeAberturaDiario(LidaComInputs.tentarPegarInputDeHora());
        
		System.out.print("Digite a hora de fechamento do evento durante o dia " + LidaComInputs.getExemploDeHorario() + ": ");
        
        evento.setHorarioDeAberturaDiario(LidaComInputs.tentarPegarInputDeHora((hora) -> {
            if(hora.isBefore(evento.getHorarioDeAberturaDiario())){
                System.out.println("o horario de abertura do evento nao pode ser depois do horario de fechamento! Por favor, tente novamente.");
                return false;
            }
            
            return true;
        }));
        
		System.out.print("Digite a data e hora de inicio do evento no formato " + LidaComInputs.getExemploDeDataEHora() + ": ");
        
        
        evento.setDataEHorarioDeInicio(LidaComInputs.tentarPegarInputDeDataEHora((dataEHora) -> {
            if(LocalDateTime.now().isAfter(dataEHora)){
                System.out.println("A data inserida ja passou! Tente novamente.");
                return false;
            }
            return true;
        }));
        
		System.out.print("Digite a data e hora de término do evento no formato " + LidaComInputs.getExemploDeDataEHora()+ ": ");
        
        evento.setDataEHorarioDeFim(LidaComInputs.tentarPegarInputDeDataEHora((dataEHora) -> {
            if(LocalDateTime.now().isAfter(dataEHora)){
                System.out.println("A data inserida ja passou! Tente novamente.");
                return false;
            }
            if(dataEHora.isBefore(evento.getDataEHorarioDeInicio())){
                System.out.println("A data inserida para termino do evento e anterior a data de inicio! Tente novamente.");
                return false;
            }
            return true;
        }));
        
		System.out.print("Digite o valor para entrada no evento: ");

        evento.setValorDoEvento(LidaComInputs.tentarPegarInputDoubleAteDarCerto());

        //implementar forma de usuário confirmar se quer realmente criar o evento

        Registro.AdicionarEvento(evento);
		System.out.println("EVENTO CRIADO COM SUCESSO!");

    }

}


/*
 * ACESSO - 1
 * EVENTO - 2
 * ESTACIONAMENTO - 3
 */


/*
 * //Parte dos acessos
 * ADICIONAR - 1
 * ATUALIZAR - 2
 * PESQUISAR - 3
 * REMOVER - 4
 */