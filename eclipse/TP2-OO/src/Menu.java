import java.util.Scanner;

public class Menu {
    public static void MostrarMenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ola, bem vindo ao programa de administracao de estacionamentos!\n");
        System.out.println("Digite um dos numeros abaixo para navegar pelo programa!\n");
        
        System.out.println("1 - Estacionamentos");
        System.out.println("2 - Acessos");
        System.out.println("3 - Eventos");

        int numero = LidaComInputs.tentarPegarInputInteiroAteDarCerto(sc,(n) -> {
            if(n > 3 || n < 1) {
                System.out.println("Por favor escolha entre as opcoes disponibilizadas!");
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
                break;
        }
    }    
}
