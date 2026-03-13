import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM();

        while (true) {

            System.out.println("\n--- WELCOME TO ATM ---");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    atm.login();
                    break;

                case 2:
                    atm.createAccount();
                    break;

                case 3:
                    System.out.println("-----------------------------");
                    System.out.println(" Thank you for using ATM.");
                    System.out.println("         Exited");
                    System.out.println("-----------------------------");
                    return;

                default:
                    System.out.println("- Invalid choice!");
            }
        }
    }
}