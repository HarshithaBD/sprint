package com.mts.exception;

public class ApplicantNotFoundException extends Exception  {

	private static final long serialVersionUID = 1L;

	
	public ApplicantNotFoundException() {}
	public ApplicantNotFoundException(String msg)
	{
		super(msg);
	}
}
