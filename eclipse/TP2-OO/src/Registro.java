
public class Registro {

	private static BancoDeDados registros;

	public static BancoDeDados getBancoDeDados() {
		return Registro.registros;
	}

	public static void criarNovoAcesso() {

	}

	public static void criarNovoEstacionamento() {

	}

	public static void criarNovoEvento() {

	}

	public static Boolean removerAcesso(String placa){

		Acesso acesso = pesquisarAcesso(placa);
		if(acesso == null){
			return false;
		}
		else {
			registros.getAcessos().remove(acesso);
			return true;
		}
	}

	public static Evento pesquisarEvento(String nomeDoEvento) {
		
		

		return null;
	}

	public static Estacionamento pesquisarEstacionamento(String nomeDoEstacionamento) {
		//todo
		return null;
	}

	public static Acesso pesquisarAcesso(String placa) {
		//todo
		return null;
	}
}
