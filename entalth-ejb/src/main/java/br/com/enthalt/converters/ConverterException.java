package br.com.enthalt.converters;

public class ConverterException extends RuntimeException {

    private static final long serialVersionUID = 8650049984830953796L;

    public ConverterException() {
	super();
    }

    public ConverterException(String message) {
	super(message);
    }

    public ConverterException(String message, Throwable cause) {
	super(message, cause);
    }

    public ConverterException(Throwable cause) {
	super(cause);
    }

}


