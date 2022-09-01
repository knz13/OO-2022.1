import java.util.Scanner;

public class CriaEstacionamento {

	public static void main(String[] args) {

		System.out.println("------ CRIAÇÃO DE NOVO ESTACIONAMENTO ------");
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o nome do estacionamento a ser criado: ");
		String nomeEstacionamento = sc.useDelimiter("\n").next();
		Estacionamento estacionamento = new Estacionamento(nomeEstacionamento);
		sc.close();
		System.out.println("ESTACIONAMENTO CRIADO COM SUCESSO!");
	}
	
}
