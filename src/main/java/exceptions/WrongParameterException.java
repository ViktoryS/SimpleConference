package exceptions;

/**
 * Created by Vikki on 10.01.2018.
 */
public class WrongParameterException extends RuntimeException {
    public WrongParameterException(String message){
        super(message);
    }
}
