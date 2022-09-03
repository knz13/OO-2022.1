import java.util.Scanner;

public class AdministraAcesso implements Administrador{

    @Override
    public void MostrarMenuPesquisa() {
        
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
		Scanner sc = new Scanner(System.in);
		Acesso acesso = new Acesso();

		System.out.print("Digite a placa do carro: ");

        acesso.setPlaca(LidaComInputs.tentarPegarInputAteDarCerto(sc));

        System.out.println(Registro.listarEstacionamentos());
        
		System.out.print("Digite o numero do estacionamento relacionado ao acesso: ");

        int index = LidaComInputs.tentarPegarInputInteiroAteDarCerto(sc,(input) -> {
            if(input > Registro.getBancoDeDados().getEstacionamentos().size() - 1){
                System.out.println("Por favor insira um numero valido para o estacionamento!");
                return false;
            }
            
            return true;
        });

        acesso.setEstacionamento(Registro.getBancoDeDados().getEstacionamentos().get(index));

        System.out.print("Digite a data e horario de entrada no formato " + LidaComInputs.getExemploDeDataEHora() + ": ");
        
        acesso.setDataEHorarioDeEntrada(LidaComInputs.tentarPegarInputDeDataEHora(sc));
        
        System.out.print("Digite a data e horario de saida no formato " + LidaComInputs.getExemploDeDataEHora() + ": ");
        
        acesso.setDataEHorarioDeSaida(LidaComInputs.tentarPegarInputDeDataEHora(sc,(input) -> {
            if(input.isBefore(acesso.getDataEHorarioDeEntrada())){
                System.out.println("O horario de saida informado e anterior ao horario de entrada. Por favor, tente novamente.");
                return false;
            }
            return true;
        }));

        Registro.AdicionarAcesso(acesso);

        System.out.println("ACESSO CRIADO COM SUCESSO!");

    } 
}
