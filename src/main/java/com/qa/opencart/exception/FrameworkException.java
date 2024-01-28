package com.qa.opencart.exception;

public class FrameworkException extends RuntimeException{
	
	
	public FrameworkException(String mesg) {
		super(mesg); //It will call the constructor of the parent class(RuntimeException) and checking in RuntimeException we see it further calls the constructor of Exception class.
	}
	

}
