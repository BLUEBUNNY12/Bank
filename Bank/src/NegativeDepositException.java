public class NegativeDepositException extends Exception {
	public NegativeDepositException (String deposit){ 
		super(deposit);
	}
}
