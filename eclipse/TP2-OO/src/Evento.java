import java.time.LocalDateTime;

public class Evento {

	
	private LocalDateTime horarioDeInicio;
	private LocalDateTime horarioDeFim;
	private String nomeDoEvento;
	private double valorDoEvento;
	private Estacionamento estacionamento;
	

	public LocalDateTime getHorarioDeInicio() {
		return horarioDeInicio;
	}
	public void setHorarioDeInicio(LocalDateTime horarioDeInicio) {
		this.horarioDeInicio = horarioDeInicio;
	}
	public LocalDateTime getHorarioDeFim() {
		return horarioDeFim;
	}
	public void setHorarioDeFim(LocalDateTime horarioDeFim) {
		this.horarioDeFim = horarioDeFim;
	}
	public String getNomeDoEvento() {
		return nomeDoEvento;
	}
	public void setNomeDoEvento(String nomeDoEvento) {
		this.nomeDoEvento = nomeDoEvento;
	}
	public double getValorDoEvento() {
		return valorDoEvento;
	}
	public void setValorDoEvento(double valorDoEvento) {
		this.valorDoEvento = valorDoEvento;
	}
	public Estacionamento getEstacionamento() {
		return estacionamento;
	}
	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}



}
