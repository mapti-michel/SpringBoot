package br.acc.webflux.service.excepticon;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException (String message){
        super(message);
    }


}
