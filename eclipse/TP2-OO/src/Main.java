
public class Main {
	
	public static void main (String[] args) {
		Registro.LoadFromFile("dados.txt");
		
		Menu.MostrarMenuPrincipal();

		Registro.SaveToFile("dados.txt");

	}
	
}
