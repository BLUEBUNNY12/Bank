import java.util.Scanner;

public class Teller {
    public static void main(String[] args) throws AccountNotFoundException, InsufficientFundsException, NegativeDepositException{
        Teller menu = new Teller();
        menu.display();
    }

    Bank bank = new Bank();
    BankAccount bankAccount = new BankAccount();
    Scanner input = new Scanner(System.in);

    public void display() throws AccountNotFoundException, InsufficientFundsException, NegativeDepositException {
        System.out.println("--Please enter a number from the following menu--");
        System.out.println("1) Open a new account.\n" +
                "2) Generate report of bank accounts.\n" +
                "3) See total assets of all bank accounts.\n" +
                "4) Exit.");

        int number1 = input.nextInt();

        //continue to ask for user input until user specifically asks to exit
        if (number1 == 1) {
            bankAccount = bank.createAccount(input.nextLine());
            display2();
        }
        else if (number1 == 2){
            bank.getReport();
            display2();
        }
        else if (number1 == 3){
            bank.getTotalAssets();
            display2();
        }
        else if (number1 == 4){ 
            System.out.println("Goodbye.");
            System.exit(0);
        }
        else {
            System.out.println("Invalid selection.");
            display();
        }
    }

    public void display2() throws AccountNotFoundException, InsufficientFundsException, NegativeDepositException {
        while (true){
            //second menu
            System.out.println("--Please enter a number from the following list--");
            System.out.println("1) Withdraw from account\n" +
                    "2) Deposit to account\n" +
                    "3) Check account balance\n" +
                    "4) Choose interest rate for your account\n" +
                    "5) Go back\n" +
                    "6) Exit");

            int number2 = input.nextInt();
            if (number2 == 1){
                //withdraw method including subtracting input from balance
                System.out.println("Please enter the amount you would like to withdraw");
                double debit = input.nextDouble();
                bank.findAccount(bankAccount.getAccountName()).withdraw(debit);
                System.out.println("Your new balance is: " + bank.findAccount(bankAccount.getAccountName()).getBalance());
            }
            else if (number2 == 2){
                //deposit method including converting input to pennies
                System.out.println("Please enter the amount would like to deposit, in a whole number.");
                int depositAmount = input.nextInt();
                bank.findAccount(bankAccount.getAccountName()).deposit(depositAmount);
                System.out.println("Your new balance is: " + bank.findAccount(bankAccount.getAccountName()).getBalance());
            }
            else if (number2 == 3){
                System.out.println("Your balance is " + bank.findAccount(bankAccount.getAccountName()).getBalance());
            }
            else if (number2 == 4){
                System.out.println("Please enter your desired interest rate. Example: 0.05 is 5%. Your balance won't reflect your interest rate if you currently have less than $1000");
                double rate = input.nextDouble();
                bank.findAccount(bankAccount.getAccountName()).setInterestRate(rate);
                System.out.println("Your new interest rate is " + bank.findAccount(bankAccount.getAccountName()).getInterestRate(rate));
            }
            else if (number2 == 5){
                display();
            }
            else if (number2 == 6){
                System.out.println("Goodbye.");
                System.exit(0);
            }
            else{
                System.out.println("Invalid selection.");
                display2();

            }
        }
    }
}
