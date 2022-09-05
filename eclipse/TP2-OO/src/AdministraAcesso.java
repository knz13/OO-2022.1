import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class AdministraAcesso implements Administrador{

    @Override
    public void MostrarMenuPesquisa() {
        
        Acesso acesso = null;
        while (true){
            System.out.println("Digite o nome da placa do acesso que deseja visualizar ou digite '-1' para voltar: ");
            String placa = LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                if(input.equals("-1")){
                    Menu.MostrarInterface(this, "Acessos");
                    return false;
                }
                return true;
            });
            try {
                acesso = Registro.pesquisarAcesso(placa);
                break;
            }
            catch(ObjetoNaoEncontradoException err){
                System.out.println(err.getMessage() + "\nPor favor, tente novamente.");
            }
        }

        MostrarMenuAcesso(acesso);
    }

    public void MostrarMenuAtualizacao(Acesso acesso){
        System.out.println("O que voce gostaria de atualizar no acesso com placa '" + acesso.getPlaca() + "'?\n");
        ArrayList<Evento> eventosRolando = Registro.getEventosRolando(acesso.getDataEHorarioDeEntrada());
        System.out.println("1 - Placa | Atual => " + acesso.getPlaca());
        System.out.println("2 - Estacionamento | Atual => " + acesso.getEstacionamento().getNomeEstacionamento());
        String tipoAtual = "Acesso Normal";
        if (acesso instanceof AcessoMensalista){
            tipoAtual = "Acesso Mensalista";
        }
        int maximoDeOpcoes = tipoAtual.equals("Acesso Evento") && eventosRolando.size() > 1? 7 : 6;
        System.out.println("3 - Tipo de acesso | Atual => " + tipoAtual);
        System.out.println("4 - Data e horario de entrada | Atual => " + acesso.getDataEHorarioDeEntrada().format(LidaComInputs.getFormatterDateTime()));
        System.out.println("5 - Data e horario de saida | Atual => " + acesso.getDataEHorarioDeSaida().format(LidaComInputs.getFormatterDateTime()));
        if(tipoAtual.equals("Acesso Evento") && eventosRolando.size() > 1){
            System.out.println("6 - Evento | Atual => " + acesso.getDataEHorarioDeSaida().format(LidaComInputs.getFormatterDateTime()));
            System.out.println("7 - Voltar\n");
        }
        else {
            System.out.println("6 - Voltar\n");
        }  

        int numero = LidaComInputs.tentarPegarInputInteiroAteDarCerto((n) -> {
            if(n < 1 || n > maximoDeOpcoes){
                System.out.println("Por favor, escolha uma das opcoes");
                return false;
            }
            return true;
        });

        switch(numero){
        case 1:
            System.out.println("Digite o novo nome da placa ou -1 para voltar");
            String novaPlaca = LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                if(input.equals("-1")){
                    MostrarMenuAtualizacao(acesso);
                    return false;
                }
                return true;
            });
            acesso.setPlaca(novaPlaca);
            
            break;
        case 2:
            System.out.println("Digite o numero associado ao novo estacionamento ou -1 para voltar");
            System.out.println(Registro.listarEstacionamentos());
            LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                if(input.equals("-1")){
                    MostrarMenuAtualizacao(acesso);
                    return false;
                }

                try {
                    acesso.setEstacionamento(Registro.getBancoDeDados().getEstacionamentos().get(Integer.parseInt(input)));
                }
                catch(NumberFormatException err){
                    System.out.println("O numero digitado nao se encontra entre as opcoes, tente novamente.");
                    return false;
                }
                return true;
            });
            break;
        case 3:
            if(acesso instanceof AcessoMensalista){
                System.out.println("Gostaria de mudar para o plano normal? Digite 1 para sim e 0 para voltar.");
                int number = LidaComInputs.tentarPegarInputInteiroAteDarCerto(n -> {
                    if(n > 1) {
                        System.out.println("Por favor, escolha uma das opcoes");
                        return false;
                    }
                    return true;
                });
                if(number == 0){
                    MostrarMenuAtualizacao(acesso);
                }
                else {
                    Acesso acDois = new Acesso();
                    acesso.CopiarPara(acDois);
                    Registro.getBancoDeDados().getAcessos().remove(acesso);
                    Registro.getBancoDeDados().getAcessos().add(acDois);
                    MostrarMenuAtualizacao(acDois);
                    
                }
            }
            else {
                System.out.println("Gostaria de mudar para o plano mensalista?\nCusta apenas R$" + acesso.getEstacionamento().getValorMensalista() + " Digite 1 para sim e 0 para voltar.");
                int number = LidaComInputs.tentarPegarInputInteiroAteDarCerto(n -> {
                    if(n > 1) {
                        System.out.println("Por favor, escolha uma das opcoes");
                        return false;
                    }
                    return true;
                });
                if(number == 0){
                    MostrarMenuAtualizacao(acesso);
                }
                else {
                    AcessoMensalista acDois = new AcessoMensalista();
                    acesso.CopiarPara(acDois);
                    Registro.getBancoDeDados().getAcessos().remove(acesso);
                    Registro.getBancoDeDados().getAcessos().add(acDois);
                    MostrarMenuAtualizacao(acDois);
                }
            }
            break;
        case 4:
            System.out.println("Digite a nova data e horario de entrada no formato" + LidaComInputs.getExemploDeDataEHora() + " ou '-1' para voltar.\n");

            LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                if(input.equals("-1")){
                    MostrarMenuAtualizacao(acesso);
                    return false;
                }
                try {
                    LocalDateTime date = LocalDateTime.parse(input,LidaComInputs.getFormatterDateTime());

                    if(date.isAfter(acesso.getDataEHorarioDeSaida())){
                        System.out.println("A data e hora informados estao antes da data e hora informados para saida. Tente novamente.");
                        return false;
                    }
                    acesso.setDataEHorarioDeEntrada(date);
                    return true;

                }
                catch(DateTimeParseException err){
                    System.out.println("A data e hora informados nao sao validos. Tente novamente.");
                    return false;
                }
            });
            break;
        case 5:
            System.out.println("Digite a nova data e horario de saida no formato" + LidaComInputs.getExemploDeDataEHora() + " ou '-1' para voltar.\n");

            LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                if(input.equals("-1")){
                    MostrarMenuAtualizacao(acesso);
                    return false;
                }
                try {
                    LocalDateTime date = LocalDateTime.parse(input,LidaComInputs.getFormatterDateTime());

                    if(date.isBefore(acesso.getDataEHorarioDeEntrada())){
                        System.out.println("A data e hora informados estao depois da data e hora informados para entrada. Tente novamente.");
                        return false;
                    }
                    acesso.setDataEHorarioDeEntrada(date);
                    return true;

                }
                catch(DateTimeParseException err){
                    System.out.println("A data e hora informados nao sao validos. Tente novamente.");
                    return false;
                }
            });
            break;
        case 6:
            if(maximoDeOpcoes == 6){
                Menu.MostrarInterface(this, "Acessos");
            }
            else {
                
            }
            break;
        case 7:
            Menu.MostrarInterface(this, "Acessos");
            break;

        }
        MostrarMenuAtualizacao(acesso);
        
    }

    public void MostrarMenuAcesso(Acesso acesso) {
        System.out.println("O que voce gostaria de fazer com esse acesso?\n");

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
                MostrarMenuAtualizacao(acesso);
                break;
            case 2:
                Registro.removerAcesso(acesso.getPlaca());
                System.out.println("Acesso removido com sucesso!");
                Menu.MostrarInterface(this, "Acessos");
                break;
            case 3:
                Menu.MostrarMenuPrincipal();
                break;
        }
    }

    @Override
    public int GetNumeroDeObjetos() {
        return Registro.getBancoDeDados().getAcessos().size();
    }

    @Override
    public void MostrarMenuCriacao() {

        if(Registro.getBancoDeDados().getEstacionamentos().size() == 0){
            System.out.println("Nao ha nenhum estacionamento no banco de dados, portanto, nao e possivel criar um acesso...");
            return;
        }

        System.out.println("------ CRIACAO DE NOVO ACESSO ------");
		Acesso acesso = new Acesso();

		System.out.print("Digite a placa do carro: ");

        acesso.setPlaca(LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
            if(input.equals("-1")){
                System.out.println("Desculpe, o valor -1 esta reservado para uso interno. Por favor escolha outra placa.");
                return false;
            }
            return true;
        }));

        System.out.println(Registro.listarEstacionamentos());
        
		System.out.print("Digite o numero do estacionamento relacionado ao acesso: ");

        int index = LidaComInputs.tentarPegarInputInteiroAteDarCerto((input) -> {
            if(input > Registro.getBancoDeDados().getEstacionamentos().size() - 1){
                System.out.println("Por favor insira um numero valido para o estacionamento!");
                return false;
            }
            
            return true;
        });

        acesso.setEstacionamento(Registro.getBancoDeDados().getEstacionamentos().get(index));

        System.out.print("Digite a data e horario de entrada no formato " + LidaComInputs.getExemploDeDataEHora() + ": ");
        
        acesso.setDataEHorarioDeEntrada(LidaComInputs.tentarPegarInputDeDataEHora());
        
        System.out.print("Digite a data e horario de saida no formato " + LidaComInputs.getExemploDeDataEHora() + ": ");
        
        acesso.setDataEHorarioDeSaida(LidaComInputs.tentarPegarInputDeDataEHora((input) -> {
            if(input.isBefore(acesso.getDataEHorarioDeEntrada())){
                System.out.println("O horario de saida informado e anterior ao horario de entrada. Por favor, tente novamente.");
                return false;
            }
            return true;
        }));

        
        ArrayList<Evento> eventosRolando = Registro.getEventosRolando(acesso.getDataEHorarioDeEntrada());

        System.out.println("Gostaria de se filiar ao plano mensalista? Digite 1 para sim e 0 para nao.");

        int number = LidaComInputs.tentarPegarInputInteiroAteDarCerto(n -> {
            if(n > 1) {
                System.out.println("Por favor, escolha uma das opcoes");
                return false;
            }
            return true;
        });

        if(number == 1) {
            AcessoMensalista acMensalista = new AcessoMensalista();
            acesso.CopiarPara(acMensalista);

            Registro.AdicionarAcesso(acMensalista);
    
            System.out.println("ACESSO CRIADO COM SUCESSO!");
            return;
        }


        if(eventosRolando.size() > 0){
            System.out.println("Os seguintes eventos estavam acontecendo quando voce entrou, voce participou de algum?\n");
            for(Evento ev : eventosRolando){
                System.out.println(" - " + ev.getNomeDoEvento() + "\n\tPreço de entrada: R$" + ev.getValorDoEvento() + "\n\tHorario de inicio: " + ev.getHorarioDeAberturaDiario() + "\n\tHorario de termino: " + ev.getHorarioDeFechamentoDiario() + "\n");

            }

            System.out.println("Digite o nome do evento que deseja associar ou -1 para nenhum.");

            LidaComInputs.tentarPegarInputAteDarCerto((input) -> {
                if(input.equals("-1")){
                    return true;
                }
                try {
                    Evento ev = Registro.pesquisarEvento(input);
                    AcessoEvento acessoDois = new AcessoEvento();
                    acessoDois.setEventoAssociado(ev);
                    acesso.CopiarPara(acessoDois);
                    Registro.AdicionarAcesso(acessoDois);
                    System.out.println("ACESSO CRIADO COM SUCESSO!");
                }
                catch(ObjetoNaoEncontradoException err){
                    System.out.println("Nao foi encontrado nenhum evento com nome " + input + ". Tente novamente.");
                    return false;
                }
                return true;

            });
        }
        else {
            Registro.AdicionarAcesso(acesso);
    
            System.out.println("ACESSO CRIADO COM SUCESSO!");

        }



       

    } 
}
