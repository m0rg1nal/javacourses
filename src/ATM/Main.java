package ATM;

public class Main {
    public static void main(String[] args) throws InsufficientFundsException, InvalidAmountException {
        ATM atm = new ATM(2000);
        atm.withdraw(500);
//        atm.withdraw(2500); - InsufficientFundsException
//        atm.deposit(0); - InvalidAmountException
        atm.deposit(100);
        System.out.println(atm.checkBalance());
    }
}
