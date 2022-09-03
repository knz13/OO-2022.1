import java.time.LocalDateTime;
import java.util.Scanner;

public class CriaEvento {
    
    public static void MostrarNoMenu() {

        if(Registro.getBancoDeDados().getEstacionamentos().size() == 0){
            System.out.println("Nao ha nenhum estacionamento no banco de dados, portanto, nao e possivel criar um evento...");
            return;
        }


        System.out.println("------ CRIACAO DE NOVO EVENTO ------");
		Scanner sc = new Scanner(System.in);
		Evento evento = new Evento();

		System.out.print("Digite o nome do evento a ser criado: ");
        
        evento.setNomeDoEvento(LidaComInputs.tentarPegarInputAteDarCerto(sc));
        
        System.out.println(Registro.listarEstacionamentos());
        
		System.out.print("Digite o numero do estacionamento relacionado ao evento: ");

        int index = LidaComInputs.tentarPegarInputInteiroAteDarCerto(sc,(input) -> {
            if(input > Registro.getBancoDeDados().getEstacionamentos().size() - 1){
                System.out.println("Por favor insira um numero valido para o estacionamento!");
                return false;
            } 

            return true;
        });

        evento.setEstacionamento(Registro.getBancoDeDados().getEstacionamentos().get(index));

		System.out.print("Digite a hora de abertura do evento durante o dia " + LidaComInputs.getExemploDeHorario() + ": ");
        
        evento.setHorarioDeAberturaDiario(LidaComInputs.tentarPegarInputDeHora(sc));
        
		System.out.print("Digite a hora de fechamento do evento durante o dia " + LidaComInputs.getExemploDeHorario() + ": ");
        
        evento.setHorarioDeAberturaDiario(LidaComInputs.tentarPegarInputDeHora(sc,(hora) -> {
            if(hora.isBefore(evento.getHorarioDeAberturaDiario())){
                System.out.println("o horario de abertura do evento nao pode ser depois do horario de fechamento! Por favor, tente novamente.");
                return false;
            }
            
            return true;
        }));
        
		System.out.print("Digite a data e hora de inicio do evento no formato " + LidaComInputs.getExemploDeDataEHora() + ": ");
        
        
        evento.setDataEHorarioDeInicio(LidaComInputs.tentarPegarInputDeDataEHora(sc,(dataEHora) -> {
            if(LocalDateTime.now().isAfter(dataEHora)){
                System.out.println("A data inserida ja passou! Tente novamente.");
                return false;
            }
            return true;
        }));
        
		System.out.print("Digite a data e hora de término do evento no formato " + LidaComInputs.getExemploDeDataEHora()+ ": ");
        
        evento.setDataEHorarioDeFim(LidaComInputs.tentarPegarInputDeDataEHora(sc,(dataEHora) -> {
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

        evento.setValorDoEvento(LidaComInputs.tentarPegarInputDoubleAteDarCerto(sc));

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