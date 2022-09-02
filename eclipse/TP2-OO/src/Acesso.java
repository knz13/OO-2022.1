
public class Acesso {

	private String placa;
	private Estacionamento estacionamento;
	private Datas dataEntrada;
	private Datas dataSaida;
	private Horas horaEntrada;
	private Horas horaSaida;



	//public double CalcularValor() lidar depois

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Datas getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Datas dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Datas getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Datas dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Horas getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Horas horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Horas getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Horas horaSaida) {
		this.horaSaida = horaSaida;
	}

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

}
