public class AccountNotFoundException extends Exception {
	public AccountNotFoundException (String getAccountName){ 
		super (getAccountName);
	}
}
