package br.com.hadryan.triply.api.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
