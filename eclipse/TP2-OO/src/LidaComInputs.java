import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


//Interfaces para testes sobre o input
interface FuncaoDeTesteData {
    public Boolean operation(LocalDate data);
}

interface FuncaoDeTesteDataEHorario {
    public Boolean operation(LocalDateTime data);
}

interface FuncaoDeTesteHorario {
    public Boolean operation(LocalTime data);
}

interface FuncaoDeTesteDoInput {
    public Boolean operation(String strQualquer);
}
interface FuncaoDeTesteDoInputInteiro {
    public Boolean operation(int intQualquer);
}
interface FuncaoDeTesteDoInputDouble {
    public Boolean operation(double doubleQualquer);
}





public class LidaComInputs {

    private static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
    private static DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    

    public static String getExemploDeHorario() {
        return LocalTime.now().format(formatterHora);
    }

    public static String getExemploDeData(){
        return LocalDate.now().format(formatterDate);
    }

    public static String getExemploDeDataEHora() {
        return LocalDateTime.now().format(formatterDateTime);
    }

    public static String lidarComInput(Scanner scanner){
		String stringRecebida = scanner.nextLine();
		if(stringRecebida.equals("")){
			throw new DescricaoEmBrancoException("Por favor, digite um valor para o campo especificado.");
		}
		return stringRecebida;
	}

    //funcao de teste do input pode ser usada para testes extras sobre cada input especifico, caso retorne falso, tentará pegar o input novamente!
	public static String tentarPegarInputAteDarCerto(FuncaoDeTesteDoInput func){
		String valor = null;

		while(valor == null){
			try {
				valor = lidarComInput(Registro.getScannerGeral());

                if(valor.contains((","))){
                    System.out.println("O input nao pode conter virgulas! Por favor, tente novamente.");
                    valor = null;
                    continue;
                }

                if(!func.operation(valor)){
                    valor = null;
                }
			}
			catch(DescricaoEmBrancoException err){
				System.out.println(err.getMessage());
			}
		}
        
        return valor;
	}
    
    public static String tentarPegarInputAteDarCerto(){
        return tentarPegarInputAteDarCerto((n) -> {return true;});
    }

    public static int tentarPegarInputInteiroAteDarCerto(FuncaoDeTesteDoInputInteiro func){
        String valor = null;
        Integer object = null;

		while(valor == null){
			try {
				valor = lidarComInput(Registro.getScannerGeral());

                if(valor.contains((","))){
                    System.out.println("O input nao pode conter virgulas! Por favor, tente novamente.");
                    valor = null;
                    continue;
                }

                
                object = Integer.parseInt(valor);
                
                if(object.intValue() < 0){
                    System.out.println("O valor inserido nao pode ser menor do que 0! Tente novamente.");
                    object = null;
                    valor = null;
                    continue;
                }
                
                if(!func.operation(object.intValue())){
                    valor = null;
                    object = null;
                    continue;
                }
			}
			catch(DescricaoEmBrancoException err){
                System.out.println(err.getMessage());
                valor = null;
                object = null;
			}
            catch(NumberFormatException err){
                System.out.println("O input nao pode ser identificado como inteiro. Por favor, tente novamente.");
                valor = null;
                object = null;
            }
		}
        
        return object.intValue();
    }

    public static int tentarPegarInputInteiroAteDarCerto() {
        return tentarPegarInputInteiroAteDarCerto((n) -> {return true;});
    }
    
    public static double tentarPegarInputDoubleAteDarCerto(FuncaoDeTesteDoInputDouble func){
        String valor = null;
        Double object = null;
        
		while(valor == null){
            try {
                valor = lidarComInput(Registro.getScannerGeral());
                
                if(valor.contains((","))){
                    System.out.println("O input nao pode conter virgulas! Por favor, tente novamente.");
                    valor = null;
                    continue;
                }
                
                object = Double.parseDouble(valor);
                
                if(object.intValue() < 0){
                    System.out.println("O valor inserido nao pode ser menor do que 0! Tente novamente.");
                    object = null;
                    valor = null;
                    continue;
                }
                
                if(!func.operation(object.doubleValue())){
                    valor = null;
                    object = null;
                    continue;
                }
			}
			catch(DescricaoEmBrancoException err){
				System.out.println(err.getMessage());
                valor = null;
                object = null;
			}
            catch(NumberFormatException err){
                System.out.println("O input nao pode ser identificado como numero decimal. Por favor, tente novamente.");
                valor = null;
                object = null;
            }
		}

        return object.doubleValue();
    }

    public static double tentarPegarInputDoubleAteDarCerto(){
        return tentarPegarInputDoubleAteDarCerto((n) -> {return true;});
    }
  

    public static LocalTime tentarPegarInputDeHora(FuncaoDeTesteHorario func){
        LocalTime time = null;
        String valor = "";
        while(time == null){
            try{
                valor = lidarComInput(Registro.getScannerGeral());

                time = LocalTime.parse(valor, formatterHora);

                if(!func.operation(time)){
                    time = null;
                }
            }
            catch(DescricaoEmBrancoException err){
				System.out.println(err.getMessage());
			}
            catch(DateTimeParseException err){
                System.out.println("valor de horário " + valor + " é inválido! Por favor, tente novamente.");
            }
        }

        return time;
    }

    public static LocalTime tentarPegarInputDeHora(){
        return tentarPegarInputDeHora((n) -> {return true;});
    }

    public static LocalDate tentarPegarInputDeData(FuncaoDeTesteData func){
        LocalDate date = null;
        String valor = "";
        while(date == null){
            try{
                valor = lidarComInput(Registro.getScannerGeral());

                date = LocalDate.parse(valor,formatterDate);

                if(!func.operation(date)){
                    date = null;
                }
            }
            catch(DescricaoEmBrancoException err){
				System.out.println(err.getMessage());
			}
            catch(DateTimeParseException err){
                System.out.println("valor de data " + valor + " é inválido! Por favor, tente novamente.");
            }
        }

        return date;
    }

    public static LocalDate tentarPegarInputDeData() {
        return tentarPegarInputDeData((n) -> {return true;});
    }

    public static LocalDateTime tentarPegarInputDeDataEHora(FuncaoDeTesteDataEHorario func){
        LocalDateTime date = null;
        String valor = "";
        while(date == null){
            try{
                valor = lidarComInput(Registro.getScannerGeral());

                date = LocalDateTime.parse(valor,formatterDateTime);

                if(!func.operation(date)){
                    date = null;
                }
            }
            catch(DescricaoEmBrancoException err){
				System.out.println(err.getMessage());
			}
            catch(DateTimeParseException err){
                System.out.println("valor de data e hora " + valor + " é inválido! Por favor, tente novamente.");
            }
        }

        return date;
    }

    public static LocalDateTime tentarPegarInputDeDataEHora(){
        return tentarPegarInputDeDataEHora((n) -> {return true;});
    }

    public static DateTimeFormatter getFormatterDateTime() {
        return formatterDateTime;
    }

    public static DateTimeFormatter getFormatterHora() {
        return formatterHora;
    }


    public static DateTimeFormatter getFormatterDate() {
        return formatterDate;
    }


    
}
