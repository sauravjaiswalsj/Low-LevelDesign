package Controllers;

import Model.User;

import java.util.Scanner;

public class Student {
    private User user;
    public void studentController(User user, Scanner scanner){
        this.user = user;

        System.out.printf("Welcome %s %s to the Library. \n", user.getFirstName(), user.getLastName());
        boolean operation = true;
        do{
            operation = performOperations(scanner);
        }while (operation);
    }

    private boolean performOperations(Scanner scanner){
        System.out.println("Actions:");

        //Book level
        System.out.println("1. Search a new Book");

        //Transaction
        System.out.println("2. Borrow Books");
        System.out.println("3. Return Books");
        System.out.println("4. Check status of a Book");

        System.out.println("5. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();
        long userId = user.getUserId();
        boolean isExitFalse = true;
        switch (choice) {
            case 1:
                BookController.searchBook(scanner);
                break;
            case 2:
                TransactionController.borrowBooks(scanner, userId );
                break;
            case 3:
                TransactionController.returnBooks(scanner, userId);
                break;
            case 4:
                TransactionController.checkStatusOfBooks(scanner);
                break;
            case 5:
                System.out.println("Exiting.");
                isExitFalse = false;
                break;
            default:
                System.out.println("Try again:");
        }

        return isExitFalse;
    }
}
