package de.nms.database.exep;

public class NotImplementedException extends Exception{
    String message;
    public NotImplementedException(String message){
        this.message = message;
    }

    public NotImplementedException(){
        this.message = "ERROR: A NotImplementedException was thrown! Seems like a feature was not implemented, but accessed";
    }
    public String msg() {
        return message;
    }
}
