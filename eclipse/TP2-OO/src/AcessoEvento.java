




public class AcessoEvento extends Acesso {

    private Evento eventoAssociado;

    @Override
    public float calcularValor() {
        return 0;
    }

    public Evento getEventoAssociado() {
        return eventoAssociado;
    }

    public void setEventoAssociado(Evento eventoAssociado) {
        this.eventoAssociado = eventoAssociado;
    }

}
