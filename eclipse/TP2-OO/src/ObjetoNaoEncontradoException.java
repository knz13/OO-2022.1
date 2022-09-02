



public class ObjetoNaoEncontradoException extends RuntimeException {
    public ObjetoNaoEncontradoException(String errorMsg,Throwable err) {
        super(errorMsg,err);
    }
    public ObjetoNaoEncontradoException(String errorMsg){
        super(errorMsg);
    }
}
