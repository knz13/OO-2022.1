
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

        Registro.AdicionarAcesso(acesso);

        System.out.println("ACESSO CRIADO COM SUCESSO!");

    } 
}
