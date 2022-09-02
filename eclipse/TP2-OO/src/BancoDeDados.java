import java.util.ArrayList;

public class BancoDeDados {

	private ArrayList<Estacionamento> estacionamentos;
	private ArrayList<Evento> eventos;
	private ArrayList<Acesso> acessos;

	public BancoDeDados() {
		this.estacionamentos = new ArrayList<>();
		this.eventos = new ArrayList<>();
		this.acessos = new ArrayList<>();
	}

	public ArrayList<Estacionamento> getEstacionamentos() {
		return estacionamentos;
	}

	public void setEstacionamentos(ArrayList<Estacionamento> estacionamentos) {
		this.estacionamentos = estacionamentos;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

	public ArrayList<Acesso> getAcessos() {
		return acessos;
	}

	public void setAcessos(ArrayList<Acesso> acessos) {
		this.acessos = acessos;
	}

}


/*
 * NomeDoEstacionamento - {index}
 * 
 */