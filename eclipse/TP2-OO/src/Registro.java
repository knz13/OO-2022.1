
public class Registro {

	private static BancoDeDados registros = new BancoDeDados();

	public static BancoDeDados getBancoDeDados() {
		return Registro.registros;
	}

	public static void AdicionarAcesso(Acesso acesso) {
		registros.getAcessos().add(acesso);
	}

	public static void AdicionarEstacionamento(Estacionamento estacionamento) {
		registros.getEstacionamentos().add(estacionamento);
	}

	public static void AdicionarEvento(Evento evento) {
		registros.getEventos().add(evento);
	}

	public static void removerAcesso(String placa){
		try {
			Acesso acesso = pesquisarAcesso(placa);
			registros.getAcessos().remove(acesso);
		}
		catch(ObjetoNaoEncontradoException err) {
			throw err;
		}
	}

	public static void removerEvento(String nomeDoEvento){
		try {
			Evento evento = pesquisarEvento(nomeDoEvento);
			registros.getEventos().remove(evento);
		}
		catch(ObjetoNaoEncontradoException err) {
			throw err;
		}
	}

	public static void removerEstacionamento(String nomeDoEstacionamento){
		try {
			Estacionamento estacionamento = pesquisarEstacionamento(nomeDoEstacionamento);
			registros.getEstacionamentos().remove(estacionamento);
		}
		catch(ObjetoNaoEncontradoException err) {
			throw err;
		}
	}

	public static Evento pesquisarEvento(String nomeDoEvento) {
		Evento evento = null;
		for(Evento ev : registros.getEventos()){
			if(ev.getNomeDoEvento() == nomeDoEvento){
				evento = ev;
			}
		}
		if(evento == null){
			throw new ObjetoNaoEncontradoException("Evento com nome " + nomeDoEvento + " não encontrado!");
		}

		return evento;
	}

	public static Estacionamento pesquisarEstacionamento(String nomeDoEstacionamento) {
		Estacionamento estacionamento = null;
		for(Estacionamento est : registros.getEstacionamentos()){
			if(est.getNomeEstacionamento() == nomeDoEstacionamento){
				estacionamento = est;
			}
		}
		if(estacionamento == null){
			throw new ObjetoNaoEncontradoException("Estacionamento com nome " + nomeDoEstacionamento + " não encontrado!");
		}

		return estacionamento;
	}

	public static Acesso pesquisarAcesso(String placa) {
		Acesso acesso = null;
		for(Acesso ac : registros.getAcessos()){
			if(ac.getPlaca() == placa){
				acesso = ac;
			}
		}
		if(acesso == null){
			throw new ObjetoNaoEncontradoException("Estacionamento com nome " + placa + " não encontrado!");
		}

		return acesso;
	}
}
