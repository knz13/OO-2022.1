import java.time.LocalDateTime;
import java.time.LocalTime;

public class Evento {

	
	private LocalDateTime dataEHorarioDeInicio;
	private LocalDateTime dataEHorarioDeFim;

	private LocalTime horarioDeAberturaDiario;
	private LocalTime horarioDeFechamentoDiario;

	private String nomeDoEvento;
	private double valorDoEvento;
	private Estacionamento estacionamento;


	
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
    public LocalDateTime getDataEHorarioDeInicio() {
        return dataEHorarioDeInicio;
    }
    public void setDataEHorarioDeInicio(LocalDateTime dataEHorarioDeInicio) {
        this.dataEHorarioDeInicio = dataEHorarioDeInicio;
    }
    public LocalDateTime getDataEHorarioDeFim() {
        return dataEHorarioDeFim;
    }
    public void setDataEHorarioDeFim(LocalDateTime dataEHorarioDeFim) {
        this.dataEHorarioDeFim = dataEHorarioDeFim;
    }
	public LocalTime getHorarioDeAberturaDiario() {
		return horarioDeAberturaDiario;
	}
	public void setHorarioDeAberturaDiario(LocalTime horarioDeAberturaDiario) {
		this.horarioDeAberturaDiario = horarioDeAberturaDiario;
	}
	public LocalTime getHorarioDeFechamentoDiario() {
		return horarioDeFechamentoDiario;
	}
	public void setHorarioDeFechamentoDiario(LocalTime horarioDeFechamentoDiario) {
		this.horarioDeFechamentoDiario = horarioDeFechamentoDiario;
	}


}
