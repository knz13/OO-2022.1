import java.time.LocalDateTime;

public class Acesso {

	private String placa;
	private Estacionamento estacionamento;
	private LocalDateTime dataEHorarioDeEntrada;
	private LocalDateTime dataEHorarioDeSaida;


	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Estacionamento getEstacionamento() {
		return estacionamento;
	}
	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}
	public LocalDateTime getDataEHorarioDeEntrada() {
		return dataEHorarioDeEntrada;
	}
	public void setDataEHorarioDeEntrada(LocalDateTime dataEHorarioDeEntrada) {
		this.dataEHorarioDeEntrada = dataEHorarioDeEntrada;
	}
	public LocalDateTime getDataEHorarioDeSaida() {
		return dataEHorarioDeSaida;
	}
	public void setDataEHorarioDeSaida(LocalDateTime dataEHorarioDeSaida) {
		this.dataEHorarioDeSaida = dataEHorarioDeSaida;
	}

	//public double CalcularValor() lidar depois

	

}
