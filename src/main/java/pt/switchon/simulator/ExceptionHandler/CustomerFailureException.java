package pt.switchon.simulator.ExceptionHandler;

public class CustomerFailureException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerFailureException(String message) {
		super(message);
	}
}
