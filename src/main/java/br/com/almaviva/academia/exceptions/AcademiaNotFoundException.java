package br.com.almaviva.academia.exceptions;

public class AcademiaNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public AcademiaNotFoundException(String message) {
        super(message);
    }
	
	public AcademiaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}