package br.com.enthalt.exception;

public class DuplicatedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicatedException() {
	super();
    }

    public DuplicatedException(String message) {
	super(message);
    }

    public DuplicatedException(String message, Throwable cause) {
	super(message, cause);
    }

    public DuplicatedException(Throwable cause) {
	super(cause);
    }
}


