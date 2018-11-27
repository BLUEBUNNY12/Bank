public class InsufficientFundsException extends Exception {
	public InsufficientFundsException (String withdraw) {
		super(withdraw);
	}
}