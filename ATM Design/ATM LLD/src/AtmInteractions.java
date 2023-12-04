import Service.AccountOpen;
import Controller.UserController;
import java.util.Scanner;
import Service.Authorisation;
import Service.Transaction;

public class AtmInteractions {
    private Authorisation authorisation = new Authorisation();
    private UserController userController = new UserController();

    private Transaction transaction = new Transaction(authorisation, userController);
    public void createAccount(Scanner scanner) throws Exception {
        int pin; double balance; int accountType;
        int rePin;
        System.out.println("Please Enter the type of account to be created");
        System.out.println("1. Savings");
        System.out.println("2. Current");

        accountType = scanner.nextInt();

        while (accountType != 1 && accountType != 2) {
            System.out.println("Invalid account type. Please enter 1 for Savings or 2 for Current");
            accountType = scanner.nextInt();
        }

        do {
            System.out.println("Enter the 4-digit Pin");
            pin = scanner.nextInt();
            System.out.println("Confirm the 4-digit Pin");
            rePin = scanner.nextInt();
        }while(pin != rePin || String.valueOf(pin).length() != 4);


        System.out.println("Enter the amount to deposit for initial balance.");
        balance = scanner.nextDouble();
        //Dependency injection of UserController
        AccountOpen accountOpen = new AccountOpen(userController,pin, balance, accountType);
        accountOpen.display();
    }
    public void admin(Scanner scanner){
        int retry = 3;
        int password;
        do{
            System.out.println("Validate Password");
            password = scanner.nextInt();

            if (retry < 0)
                return;
        }while (retry-- >= 0 && password != 7777);

        UserController.displayUserInBank();
    }

    public void insertCard(Scanner scanner) {
        int accountNumber = scanner.nextInt();
        int pin;
        do {
            System.out.println("Enter pin");
            pin = scanner.nextInt();
        }while (!authorisation.validatePin(userController,accountNumber, pin));
        int choice, retry = 3;
        do{
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. Cancel");

            choice = scanner.nextInt();
            --retry;
        }while (choice >4 && retry > 0);

        if (choice == 4)
            return;
        if (choice == 3){
            transaction.setTransactionData(accountNumber,pin,0);
            transaction.balanceEnquiry();
            return;
        }
        System.out.print("Enter amount: ");

        double amount = scanner.nextDouble();
        transaction.setTransactionData(accountNumber, pin, amount);
        transaction(choice);

    }

    private void transaction(int transactionType){
        if(transactionType == 1)
            transaction.withdrawMoney();
        else if (transactionType == 2)
            transaction.depositMoney();
        else return;
    }
}
