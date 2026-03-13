import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private int accountCounter = 1000;
    private HashMap<String, User> users = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public ATM() {
        loadAccounts();
        accountCounter = users.size() + 1000;
    }

    public void createAccount() {

        sc.nextLine();

        System.out.print("Enter Full Name: ");
        String fullName = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.next();

        String accountNo = "ACC" + (++accountCounter);

        User user = new User(accountNo, fullName, password);
        users.put(accountNo, user);

        saveAccounts();

        System.out.println("\nAccount Created Successfully!");
        System.out.println("Your Account Number: " + accountNo);
    }


    public void login() {

        System.out.print("Enter Account Number: ");
        String accNo = sc.next();

        if (!users.containsKey(accNo)) {
            System.out.println("Account not found!");
            return;
        }

        User user = users.get(accNo);

        if (user.attempts >= 3) {
            System.out.println("Account locked due to 3 wrong attempts!");
            return;
        }

        System.out.print("Enter Password: ");
        String password = sc.next();

        if (user.checkPassword(password)) {

            user.attempts = 0;
            saveAccounts();

            System.out.println("\nLogin Successful!");
            System.out.println("Welcome " + user.fullName);

            userMenu(user);

        } else {

            user.attempts++;
            saveAccounts();

            System.out.println("Wrong password! Attempts left: " + (3 - user.attempts));
        }
    }

    private void userMenu(User user) {

        while (true) {

            System.out.println("\n---- ATM MENU ----");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print(" Enter amount: ");
                    double dep = sc.nextDouble();

                    user.deposit(dep);
                    saveAccounts();
                    break;

                case 2:
                    System.out.print(" Enter amount: ");
                    double wit = sc.nextDouble();

                    user.withdraw(wit);
                    saveAccounts();
                    break;

                case 3:
                    user.checkBalance();
                    break;

                case 4:
                    System.out.println("\n-- Logged out successfully! --");
                    return;

                default:
                    System.out.println("- Invalid choice!");
            }
        }
    }

    public void saveAccounts() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            
            oos.writeObject(users);

        } catch (Exception e) {
            System.out.println("- Error saving accounts. -");
        }
    }

    @SuppressWarnings("unchecked")

    public void loadAccounts() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            
            users = (HashMap<String, User>) ois.readObject();

        } catch (Exception e) {
            users = new HashMap<>();
        }
    }
}