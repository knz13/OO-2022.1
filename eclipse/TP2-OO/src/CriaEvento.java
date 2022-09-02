import java.time.LocalDateTime;
import java.util.Scanner;

public class CriaEvento {
    
    public static void MostrarNoMenu() {
        System.out.println("------ CRIACAO DE NOVO EVENTO ------");
		Scanner sc = new Scanner(System.in);
		Evento evento = new Evento();
		System.out.print("Digite o nome do evento a ser criado: ");
        
        evento.setNomeDoEvento(LidaComInputs.tentarPegarInputAteDarCerto(sc));
        
		System.out.print("Digite a data e hora de inicio do evento no formato " + LidaComInputs.getExemploDeDataEHora() + ": ");
        
        
        evento.setHorarioDeInicio(LidaComInputs.tentarPegarInputDeDataEHora(sc,(dataEHora) -> {
            if(LocalDateTime.now().isAfter(dataEHora)){
                System.out.println("A data inserida ja passou! Tente novamente.");
                return false;
            }
            return true;
        }));
        
		System.out.print("Digite a data e hora de término do evento no formato " + LidaComInputs.getExemploDeDataEHora()+ ": ");
        
        evento.setHorarioDeFim(LidaComInputs.tentarPegarInputDeDataEHora(sc,(dataEHora) -> {
            if(LocalDateTime.now().isAfter(dataEHora)){
                System.out.println("A data inserida ja passou! Tente novamente.");
                return false;
            }
            if(dataEHora.isBefore(evento.getHorarioDeInicio())){
                System.out.println("A data inserida para termino do evento e anterior a data de inicio! Tente novamente.");
                return false;
            }
            return true;
        }));
        
		System.out.print("Digite o valor para entrada no evento: ");

        evento.setValorDoEvento(LidaComInputs.tentarPegarInputDoubleAteDarCerto(sc));

        

        //implementar forma de usuário confirmar se quer realmente criar o evento

        Registro.AdicionarEvento(evento);
        sc.close();
		System.out.println("EVENTO CRIADO COM SUCESSO!");

    }

}
