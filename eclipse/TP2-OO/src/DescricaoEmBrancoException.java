

public class DescricaoEmBrancoException extends RuntimeException {
    public DescricaoEmBrancoException(String errorMsg,Throwable err){
        super(errorMsg,err);
    }
}