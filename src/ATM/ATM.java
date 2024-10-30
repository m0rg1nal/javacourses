package ATM;

import javax.naming.InsufficientResourcesException;

public class ATM {
    private double balance;

    public ATM(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (balance < amount){
            throw new InsufficientFundsException("Not enough money to withdraw.");
        }
        else if(amount <= 0){
            throw new InvalidAmountException("Amount of withdrawal should be positive.");
        }
        else{
            balance -= amount;
            System.out.println("Money has been withdrawn successfully.\nBalance: " + balance);
        }
    }
    public void deposit(double amount) throws InvalidAmountException{
        if (amount <= 0){
            throw new InvalidAmountException("Amount of deposit should be positive.");
        }
        else{
            balance += amount;
            System.out.println("Deposit has been made successfully.\nBalance: " + balance);
        }
    }
    public double checkBalance(){
        return balance;
    }
}

class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message){
        super(message);
    }
}
class InvalidAmountException extends Exception{
    public InvalidAmountException(String message){
        super(message);
    }
}