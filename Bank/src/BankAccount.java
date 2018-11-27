public class BankAccount implements InterestListener{

    //data fields
    private String accountName;
    private int balance = 0; //number of pennies, not dollars. Ex. if int balance is 100, then balance is $1.00

    //convert balance in dollars(double) to cents(int) and vice versa
    private double centsToDollars (int cents){ 
        //divide by either 0.01 or 100.0 to convert pennies to dollars 
        return (cents * .01);
    }

    private int dollarsToCents (double dollars){
        //multiply dollar by 100 then implement Math.round(double)
        return ((int)Math.round(dollars * 100.0));
    }

    //constructor
    public BankAccount (){

    }

    //get account name and balance methods
    public String getAccountName() throws AccountNotFoundException{
        if (accountName != null){
            return accountName;
        }
        else if (accountName == null){
            throw new AccountNotFoundException("Account not found");
        }
		return accountName;
    }

    public double getBalance(){
        return centsToDollars(balance);
    }

    double getInterestRate(double rate){
        return rate;
    }

    public void setInterestRate(double rate){
        balance = balance + (int) (balance * rate);
    }

    //deposit method
    public void deposit (double credit) throws NegativeDepositException{
        balance = balance + dollarsToCents(credit);
    }

    //withdraw method throws insufficient funds exception
    public void withdraw (double debit) throws InsufficientFundsException, NegativeDepositException{
        int debitInCents = dollarsToCents(debit);
        if (debitInCents <= balance) {
            balance = (balance - debitInCents);
        }
        else if(debitInCents >= balance){
        	InsufficientFundsException ife = new InsufficientFundsException("Insufficient funds");
        	throw ife;
        }
        else if(debitInCents < 0){
        	NegativeDepositException nde = new NegativeDepositException("Cannot withdraw a negative amount");
			throw nde;
        }
    }

    public BankAccount (String accountName){
        this.accountName = accountName;
    }

    public String toString(){
        try {
            return String.format ("Account \"%s\" has a balance of $%.2f", getAccountName(), getBalance());
        } catch (AccountNotFoundException e) {
            System.out.println("Account not found.");
        }
        return accountName;
    }

    @Override
    public void postInterest(double rate) throws AccountNotFoundException {
        Bank bank = new Bank();
        if (balance >= (1000 * 100)){
            ((Bank) bank.getAccounts()).addInterestListener(this);
        }
        if (balance < (1000 * 100)){
            ((Bank) bank.getAccounts()).removeInterestListener(this);
        }
    }
}
