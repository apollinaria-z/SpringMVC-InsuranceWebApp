package by.zolotaya.app.dao;

public class DAOException extends RuntimeException {
    public DAOException(){
        super();
    }

    public DAOException(String message, Throwable cause){
        super(message,cause);
    }

    public DAOException(String message){
        super(message);
    }
}
