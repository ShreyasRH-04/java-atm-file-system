import java.io.Serializable;

public class User implements Serializable {
    String accountNumber;
    String fullName;
    String password;
    double balance;
    int attempts = 0;

    public User(String accountNumber, String fullName, String password) {
        this.accountNumber = accountNumber; 
        this.fullName = fullName;
        this.password = password;
        this.balance = 0;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("- Insufficient balance!");
        } 
        else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }
    public boolean checkPassword(String inputPassword) {
    return password.equals(inputPassword);
    }
}