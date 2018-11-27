import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


public class Bank implements InterestListener{

	//new scanner
	Scanner input = new Scanner(System.in);

	private int balance;
	private Collection<BankAccount> accounts = new ArrayList<BankAccount>();
	private Collection<InterestListener> interestGainingAccounts = new ArrayList<InterestListener>();
	private double rate;

	Bank(){

	}

	Collection<BankAccount> getAccounts() throws AccountNotFoundException{
		if (accounts != null){
			return accounts;
		}
		else{
			AccountNotFoundException anfe = new AccountNotFoundException("Account not found");
			throw anfe;
		}
	}

	/*If there is not already an account with the specified name, then create a new instance of
    BankAccount. Dont forget to add the new instance to the collection of accounts.*/
	public BankAccount createAccount (String accountName) throws AccountNotFoundException {
		System.out.println("What would you like to name your new account?");
		BankAccount newAccount = new BankAccount(input.nextLine());
		accounts.add(newAccount);
		System.out.println(newAccount.getAccountName() + " has created a new account");
		System.out.println("Number of accounts: " + accounts.size());
		for (BankAccount account : accounts){
			if (newAccount.getAccountName() != null && newAccount.getAccountName().equals(accountName)){
				System.out.println("Account name already exists");
			}
		}
		return newAccount;
	}

	/*This service method will be used in several places (deposit, withdraw, inquiry and
    createAccount). Implement this method first. The method should search through the collection
    of accounts for a reference to a BankAccount instance with the given account name. If found,
    then the reference to the located account should be returned.*/
	BankAccount findAccount (String accountName) throws AccountNotFoundException {
		for (BankAccount account : accounts){
			if (account != null && account.getAccountName().equals(accountName)){
				return account;
			}
		} 
		AccountNotFoundException anfe = new AccountNotFoundException("Account not found.");
		throw anfe;
	}


	/*This Bank method must first try to locate the specified account (see findAccount). If the search
    is successful, then the deposit should occur. Otherwise, generate an appropriate error message.
    Of course, you will need to do the same sort of thing when implementing withdraw, inquiry and
    createAccount.*/
	public void deposit (String accountName, double credit) throws AccountNotFoundException, NegativeDepositException{
		findAccount(accountName).deposit(credit);   
		if (credit < 0){
			NegativeDepositException nde= new NegativeDepositException("Cannot deposit negative credit.");
			throw nde;
		}
		for (BankAccount account : accounts){
			if (account.getBalance() >= (1000 * 100)){
				postInterest(0.05);
			}
		}
	}

	//user can withdraw from desired account, method will search for desired account and allow withdrawal, throw insufficient funds exception, or account not found exception
	public void withdraw(String accountName, double debit) throws NegativeDepositException, InsufficientFundsException, AccountNotFoundException{
		findAccount(accountName).withdraw(debit);
		for (BankAccount account :  accounts) {
			if (account.getBalance() < (1000 * 100)){
				postInterest(0.00);
			}
		}
	}

	//total assets of all accounts in bank
	public void getTotalAssets() throws AccountNotFoundException{
		double total = 0;
		for (BankAccount account : accounts){
			if (findAccount(account.getAccountName()) != null){
				for (int i = 0; i < accounts.size(); i++){
				}	
			}
			total = (total + findAccount(account.getAccountName()).getBalance());
		}
		System.out.println("Total assets for all accounts found is $" + total);
		System.out.println("Number of accounts: " + accounts.size());
	}


	//report of all accounts and their balances, respectively
	public Collection<BankAccount> getReport() throws AccountNotFoundException{
		for (BankAccount account : accounts){
			if (findAccount(account.getAccountName()) != null){
			}
		}
		System.out.println((ArrayList<BankAccount>) accounts);
		System.out.println("Number of accounts: " + accounts.size());
		return accounts;
	}


	//add interest listener and post interest at desired user amount when balance is $1000 or greater 
	public void addInterestListener (InterestListener listener) throws AccountNotFoundException{
		for (BankAccount account : accounts){
			if (account.getBalance() >= (1000 * 100)){
				rate = input.nextDouble(); //in decimal, ex. 0.05 = 5%, user will receive example in prompt
				postInterest(rate);
			}
		}
	}

	//remove interest listener and post interest at 0% when balance is below $1000 
	public void removeInterestListener (InterestListener listener) throws AccountNotFoundException{
		for (BankAccount account : accounts){
			if (account.getBalance()  < (1000 * 100)){
				postInterest(rate);
			}
		}
	}

	//post interest to accounts that are considered interest gaining accounts
	public void postInterest (double rate) throws AccountNotFoundException{
		for (InterestListener listener : interestGainingAccounts) {
			listener.postInterest(rate);
			balance = (int) (balance * rate);
		}
	}

	public void addInterestListener(BankAccount holder) {
		addInterestListener(holder); 
	}

	public void removeInterestListener(BankAccount holder) {
		removeInterestListener(holder);
	}

}
