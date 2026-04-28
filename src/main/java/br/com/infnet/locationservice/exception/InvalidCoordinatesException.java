package br.com.infnet.locationservice.exception;

public class InvalidCoordinatesException extends RuntimeException{
    public InvalidCoordinatesException(String message) {
        super(message);
    }
}
