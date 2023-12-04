import java.util.Scanner;
import java.util.logging.*;
public class ATM {
    private static final Logger logger = Logger.getLogger(ATM.class.getName());
    public static void main(String[] args) throws Exception {

        AtmInteractions interactions = new AtmInteractions();
        Scanner scanner = new Scanner(System.in);
        logger.info("Application started.");
        // Simulate options for the user
        System.out.println("Welcome to the ATM!");
        boolean stopBanking = true;
        do {
            System.out.println("1. Insert Card");
            System.out.println("2. Create an Account");
            System.out.println("3. Admin");
            System.out.println("4.Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter your account number");
                    interactions.insertCard(scanner);
                    break;
                case 2:
                    System.out.println("Welcome, to open your account.");
                    interactions.createAccount(scanner);
                    break;
                case 3:
                    System.out.println("Welcome Admin");
                    interactions.admin(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for banking with us.");
                    stopBanking = false;
                    break;
                default:
                    System.out.println("Oops. Invalid option.");
            }
        }while(stopBanking);
        scanner.close();
    }
}
