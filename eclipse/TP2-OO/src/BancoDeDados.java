
public class BancoDeDados {

	private static Estacionamento estacionamentos[];
	private static Evento eventos[];
	private static Registro registros[];
	private static Acesso acessos[];

	public Estacionamento[] getEstacionamentos() {
		return estacionamentos;
	}

	public void setEstacionamentos(Estacionamento[] estacionamentos) {
		this.estacionamentos = estacionamentos;
	}

	public Evento[] getEventos() {
		return eventos;
	}

	public void setEventos(Evento[] eventos) {
		this.eventos = eventos;
	}

	public Registro[] getRegistros() {
		return registros;
	}

	public void setRegistros(Registro[] registros) {
		this.registros = registros;
	}

	public Acesso[] getAcessos() {
		return acessos;
	}

	public void setAcessos(Acesso[] acessos) {
		this.acessos = acessos;
	}

}
