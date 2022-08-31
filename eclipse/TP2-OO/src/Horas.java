
public class Horas {

	int hora;
	int minuto;
	String horas;

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		if (hora >= 0 && hora <= 23) {
			System.out.println("hora válida");
			this.hora = hora;
		} else {
			System.out.println("hora inválida");
		}

	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		if (minuto >= 0 && minuto <= 59) {
			System.out.println("minuto válido");
			this.minuto = minuto;
		} else {
			System.out.println("minuto inválido");
		}

	}
	
	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

}
