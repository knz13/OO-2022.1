import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Registro {

	private static BancoDeDados registros = new BancoDeDados();

	private static Scanner scannerGeral = new Scanner(System.in);


	
	public static BancoDeDados getBancoDeDados() {
		return Registro.registros;
	}


	public static Boolean SaveToFile(String path) {
		try {
			FileWriter file = new FileWriter(path,false);

			for(Estacionamento estacionamento : getBancoDeDados().getEstacionamentos()){
				file.append("1,");
				if(estacionamento instanceof Estacionamento24h){
					file.append("24h,");
				}
				else {
					file.append("Normal,");
				}
				file.append(estacionamento.getNomeEstacionamento() + ",");
				file.append(estacionamento.getCapacidadeEstacionamento() + ",");
				file.append(estacionamento.getFracaoQuinzeMinutos() + ",");
				file.append(estacionamento.getDescontoHoraCheia() + ",");
				file.append(estacionamento.getDiariaDiurna() + ",");
				file.append(estacionamento.getDiariaNoturna() + ",");
				file.append(estacionamento.getValorMensalista() + ",");
				file.append(estacionamento.getRetornoContratante() + ",");
				file.append(estacionamento.getHorarioAbertura().format(LidaComInputs.getFormatterHora()) + ",");
				file.append(estacionamento.getHorarioFechamento().format(LidaComInputs.getFormatterHora()) + ",");
				file.append("\n");
			}
			for(Evento evento : getBancoDeDados().getEventos()){
				file.append("2,");
				file.append(evento.getNomeDoEvento() + ",");
				file.append(evento.getDataEHorarioDeInicio().format(LidaComInputs.getFormatterDateTime()) + ",");
				file.append(evento.getDataEHorarioDeFim().format(LidaComInputs.getFormatterDateTime()) + ",");
				file.append(evento.getValorDoEvento() + ",");
				file.append(evento.getEstacionamento().getNomeEstacionamento() + ",");
				file.append(evento.getHorarioDeAberturaDiario() + ",");
				file.append(evento.getHorarioDeFechamentoDiario() + ",");
				file.append("\n");
			}

			for(Acesso acesso : getBancoDeDados().getAcessos()){
				file.append("3,");
				if(acesso instanceof AcessoEvento){
					file.append("AcessoEvento,");
				}
				else if (acesso instanceof AcessoMensalista){
					file.append("AcessoMensalista,");
				}
				else {
					file.append("AcessoNormal,");
				}
				file.append(acesso.getPlaca() + ",");
				file.append( acesso.getDataEHorarioDeEntrada().format(LidaComInputs.getFormatterDateTime()) + ",");
				file.append( acesso.getDataEHorarioDeSaida().format(LidaComInputs.getFormatterDateTime()) + ",");
				file.append( acesso.getEstacionamento().getNomeEstacionamento() + ",");
				if(acesso instanceof AcessoEvento){
					AcessoEvento acEvento = (AcessoEvento)acesso;
					file.append(acEvento.getEventoAssociado().getNomeDoEvento() + ",");
				}
				file.append("\n");
			}



			file.close();
		}
		catch(IOException err){
			System.out.println("Ocorreu um erro ao salvar os dados do banco de dados!");
			return false;
		}

		return true;
	}

	public static Boolean LoadFromFile(String path) {

		if(!new File(path).exists() || new File(path).isDirectory()){
			return false;
		}

		try {
			List<String> lines = Files.readAllLines(Path.of(path));

			for(String line : lines){
				try {
					switch(Integer.parseInt(Character.toString(line.charAt(0)))){
						case 1:
							//estacionamento
							String[] dados = line.split(",");
							Estacionamento estacionamento = null;
							if(dados[1].equals("24h")){
								estacionamento = new Estacionamento24h();
							}
							else {
								estacionamento = new Estacionamento();
							}
							estacionamento.setNomeEstacionamento(dados[2]);
							estacionamento.setCapacidadeEstacionamento(Integer.parseInt(dados[3]));
							estacionamento.setFracaoQuinzeMinutos(Double.parseDouble(dados[4]));
							estacionamento.setDescontoHoraCheia(Double.parseDouble(dados[5]));
							estacionamento.setDiariaDiurna(Double.parseDouble(dados[6]));
							estacionamento.setDiariaNoturna(Double.parseDouble(dados[7]));
							estacionamento.setValorMensalista(Double.parseDouble(dados[8]));
							estacionamento.setRetornoContratante(Double.parseDouble(dados[9]));
							estacionamento.setHorarioAbertura(LocalTime.parse(dados[10],LidaComInputs.getFormatterHora()));
							estacionamento.setHorarioFechamento(LocalTime.parse(dados[11],LidaComInputs.getFormatterHora()));
							Registro.AdicionarEstacionamento(estacionamento);
							break;
						case 2:
							//evento
							String[] dadosEvento = line.split(",");
							Evento evento = new Evento();
							evento.setNomeDoEvento(dadosEvento[1]);
							evento.setDataEHorarioDeInicio(LocalDateTime.parse(dadosEvento[2],LidaComInputs.getFormatterDateTime()));
							evento.setDataEHorarioDeFim(LocalDateTime.parse(dadosEvento[3], LidaComInputs.getFormatterDateTime()));
							evento.setValorDoEvento(Double.parseDouble(dadosEvento[4]));
							evento.setEstacionamento(Registro.pesquisarEstacionamento(dadosEvento[5]));
							evento.setHorarioDeAberturaDiario(LocalTime.parse(dadosEvento[6],LidaComInputs.getFormatterHora()));
							evento.setHorarioDeFechamentoDiario(LocalTime.parse(dadosEvento[7],LidaComInputs.getFormatterHora()));
							Registro.AdicionarEvento(evento);
							break;

						case 3:
							//acesso
							String[] dadosAcesso = line.split(",");
							Acesso acesso = null;
							if(dadosAcesso[1].equals("AcessoNormal")){
								acesso = new Acesso();
							}
							else if (dadosAcesso[1].equals("AcessoMensalista")){
								acesso = new AcessoMensalista();
							}
							else if (dadosAcesso[1].equals("AcessoEvento")){
								acesso = new AcessoEvento();
							}
							acesso.setPlaca(dadosAcesso[2]);
							acesso.setDataEHorarioDeEntrada(LocalDateTime.parse(dadosAcesso[3],LidaComInputs.getFormatterDateTime()));
							acesso.setDataEHorarioDeSaida(LocalDateTime.parse(dadosAcesso[4],LidaComInputs.getFormatterDateTime()));
							acesso.setEstacionamento(Registro.pesquisarEstacionamento(dadosAcesso[5]));
							if(dadosAcesso.length > 6){
								AcessoEvento acEvento = (AcessoEvento)acesso;
								acEvento.setEventoAssociado(Registro.pesquisarEvento(dadosAcesso[6]));
							}
							Registro.AdicionarAcesso(acesso);
							break;



					}
				}
				catch(NumberFormatException err) {
					continue;
				}
			}
		}
		catch(IOException err){
			System.out.println("Ocorreu um erro ao carregar os dados do banco de dados!");
			return false;
		}

		return true;
	}

	public static String listarEstacionamentos() {
		String valor = "Lista de Estacionamentos:\n";
		int index = 0;
		for(Estacionamento estacionamento : registros.getEstacionamentos()){
			valor += index + " - " + estacionamento.getNomeEstacionamento() + "\n";
			index++;
		}
		return valor;
	}
	public static String listarEstacionamentosSemIndex() {
		String valor = "Lista de Estacionamentos:\n";
		for(Estacionamento estacionamento : registros.getEstacionamentos()){
			valor += " - " + estacionamento.getNomeEstacionamento() + "\n";
		}	
		return valor;

	}

	public static String listarEventosSemIndex() {
		String valor = "Lista de Eventos:\n";
		for(Evento evento : registros.getEventos()){
			valor += " - " + evento.getNomeDoEvento() + "\n";
		}
		return valor;
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
			if(ev.getNomeDoEvento().equals(nomeDoEvento)){
				evento = ev;
			}
		}
		if(evento == null){
			throw new ObjetoNaoEncontradoException("Evento com nome " + nomeDoEvento + " nao encontrado!");
		}

		return evento;
	}

	public static ArrayList<Evento> getEventosRolando(LocalDateTime data) {
		ArrayList<Evento> eventosRolando = new ArrayList<>();
		Registro.getBancoDeDados().getEventos().forEach((evento) -> {
            if(evento.getDataEHorarioDeInicio().isBefore(data) && evento.getDataEHorarioDeFim().isAfter(data)){
                if(evento.getHorarioDeAberturaDiario().isBefore(data.toLocalTime()) && evento.getHorarioDeFechamentoDiario().isAfter(data.toLocalTime())){
                    //sabemos que entrou durante as datas do evento e esta no periodo do evento!
                    eventosRolando.add(evento);
                }
            }
        });
		return eventosRolando;
	};

	public static Estacionamento pesquisarEstacionamento(String nomeDoEstacionamento) {
		Estacionamento estacionamento = null;
		for(Estacionamento est : registros.getEstacionamentos()){
			if(est.getNomeEstacionamento().equals(nomeDoEstacionamento)){
				estacionamento = est;
			}
		}
		if(estacionamento == null){
			throw new ObjetoNaoEncontradoException("Estacionamento com nome " + nomeDoEstacionamento + " nao encontrado!");
		}

		return estacionamento;
	}

	public static Acesso pesquisarAcesso(String placa) {
		Acesso acesso = null;
		for(Acesso ac : registros.getAcessos()){
			if(ac.getPlaca().equals(placa)){
				acesso = ac;
			}
		}
		if(acesso == null){
			throw new ObjetoNaoEncontradoException("Acesso com placa " + placa + " nao encontrado!");
		}

		return acesso;
	}


    public static BancoDeDados getRegistros() {
        return registros;
    }


    public static void setRegistros(BancoDeDados registros) {
        Registro.registros = registros;
    }


    public static Scanner getScannerGeral() {
        return scannerGeral;
    }
}
