import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
                MostrarMenuAtualizacao(evento);
                break;
            case 2:
                break;
            case 3:
                Menu.MostrarMenuPrincipal();
                break;
        }

    }

    public void MostrarMenuAtualizacao(Evento evento){

        System.out.println("O que voce gostaria de atualizar no evento '" + evento.getNomeDoEvento() + "'?\n");

        System.out.println("1 - Nome");
        System.out.println("2 - Valor");
        System.out.println("3 - Estacionamento associado");
        System.out.println("4 - Data de inicio");
        System.out.println("5 - Data de termino");
        System.out.println("6 - Horario de abertura diario");
        System.out.println("7 - Horario de fechamento diario");
        System.out.println("8 - Voltar");

        int number = LidaComInputs.tentarPegarInputInteiroAteDarCerto((n) -> {
            if(n > 8 || n < 1){
                System.out.println("Por favor, escolha uma das opcoes");
                return false;
            }
            return true;
        });

        switch(number){
            case 1:
                System.out.println("Digite o novo nome do evento ou '-1' para voltar.");
                String nome = LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                    if(input.equals("-1")){
                        MostrarMenuAtualizacao(evento);
                        return false;
                    }
                    return true;
                });
                evento.setNomeDoEvento(nome);
                break;
            case 2:
                System.out.println("Digite o novo valor do evento ou '-1' para voltar.");
                LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                    if(input.equals("-1")){
                        MostrarMenuAtualizacao(evento);
                        return false;
                    }
                    try {
                        if(Double.parseDouble(input) < 0){
                            System.out.println("O valor nao pode ser negativo! Tente novamente.");
                            return false;
                        }
                        evento.setValorDoEvento(Double.parseDouble(input));
                        return true;
                    }
                    catch(NumberFormatException err){
                        System.out.println("o valor " + input + " nao e um numero valido! Tente novamente.");
                        return false;
                    }
                });
                break;
            case 3:
                System.out.println("Digite o numero associado ao novo estacionamento ou '-1' para voltar.\n");
                System.out.println(Registro.listarEstacionamentos());

                LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                    if(input.equals("-1")){
                        MostrarMenuAtualizacao(evento);
                        return false;
                    }
                    try {
                        int numero = Integer.parseInt(input);
                        if(numero < 0 || numero >= Registro.getBancoDeDados().getEstacionamentos().size()){
                            System.out.println("Por favor, escolha um dos estacionamentos acima.");
                            return false;
                        }
                        evento.setEstacionamento(Registro.getBancoDeDados().getEstacionamentos().get(numero));
                    }
                    catch(NumberFormatException err){
                        System.out.println("o numero " + input + " nao e um numero valido! Tente novamente.");
                        return false;
                    }
                    return true;
                });

                
                break;
            case 4:
                System.out.println("Digite a nova data de inicio no formato" + LidaComInputs.getExemploDeDataEHora() + " ou '-1' para voltar.\n");

                LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                    if(input.equals("-1")){
                        MostrarMenuAtualizacao(evento);
                        return false;
                    }
                    try {
                        LocalDateTime date = LocalDateTime.parse(input,LidaComInputs.getFormatterDateTime());

                        if(date.isAfter(evento.getDataEHorarioDeFim())){
                            System.out.println("A data e hora informados estao antes da data e hora informados para termino do evento. Tente novamente.");
                            return false;
                        }
                        evento.setDataEHorarioDeInicio(date);
                        return true;

                    }
                    catch(DateTimeParseException err){
                        System.out.println("A data e hora informados nao sao validos. Tente novamente.");
                        return false;
                    }
                });
                break;
            case 5:
                System.out.println("Digite a nova data de termino no formato" + LidaComInputs.getExemploDeDataEHora() + " ou '-1' para voltar.\n");

                LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                    if(input.equals("-1")){
                        MostrarMenuAtualizacao(evento);
                        return false;
                    }
                    try {
                        LocalDateTime date = LocalDateTime.parse(input,LidaComInputs.getFormatterDateTime());

                        if(date.isBefore(evento.getDataEHorarioDeInicio())){
                            System.out.println("A data e hora informados estao depois da data e hora informados para o inicio do evento. Tente novamente.");
                            return false;
                        }
                        evento.setDataEHorarioDeFim(date);
                        return true;

                    }
                    catch(DateTimeParseException err){
                        System.out.println("A data e hora informados nao sao validos. Tente novamente.");
                        return false;
                    }
                });
                break;
            case 6:
                System.out.println("Digite o novo horario de abertura diario no formato" + LidaComInputs.getExemploDeHorario() + " ou '-1' para voltar.\n");
                LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                    if(input.equals("-1")){
                        MostrarMenuAtualizacao(evento);
                        return false;
                    }
                    try {
                        LocalTime time = LocalTime.parse(input,LidaComInputs.getFormatterHora());
                        evento.setHorarioDeAberturaDiario(time);
                        return true;

                    }
                    catch(DateTimeParseException err){
                        System.out.println("A hora informada nao e valida. Tente novamente.");
                        return false;
                    }
                });
                break;
            case 7:
                System.out.println("Digite o novo horario de fechamento diario no formato" + LidaComInputs.getExemploDeHorario() + " ou '-1' para voltar.\n");
                LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                    if(input.equals("-1")){
                        MostrarMenuAtualizacao(evento);
                        return false;
                    }
                    try {
                        LocalTime time = LocalTime.parse(input,LidaComInputs.getFormatterHora());
                        evento.setHorarioDeFechamentoDiario(time);
                        return true;

                    }
                    catch(DateTimeParseException err){
                        System.out.println("A hora informada nao e valida. Tente novamente.");
                        return false;
                    }
                });
                break;
            case 8:
                MostrarMenuDeObjeto(evento);
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

		System.out.print("Digite a hora de abertura do evento durante o dia no formato " + LidaComInputs.getExemploDeHorario() + ": ");
        
        evento.setHorarioDeAberturaDiario(LidaComInputs.tentarPegarInputDeHora());
        
		System.out.print("Digite a hora de fechamento do evento durante o dia no formato " + LidaComInputs.getExemploDeHorario() + ": ");
        
        evento.setHorarioDeFechamentoDiario(LidaComInputs.tentarPegarInputDeHora());
        
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