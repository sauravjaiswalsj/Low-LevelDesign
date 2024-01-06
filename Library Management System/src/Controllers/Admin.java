package Controllers;

import Model.User;
import java.util.Scanner;

public class Admin {
    private User user;
    public void adminController(User user, Scanner scanner){
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
        System.out.println("1. Add a new Book");
        System.out.println("2. Remove a new Book");
        System.out.println("3. Update a new Book");
        System.out.println("4. Search a new Book");

        //Members
        System.out.println("5. Remove a new Member");
        System.out.println("6. Update a new Member");
        System.out.println("7. Search a Member");

        //transcations
        System.out.println("8. Check status of a new Book");
        System.out.println("9. Calculate Fine");
        System.out.println("10. Collect Fines");

        System.out.println("11. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        boolean isExitFalse = true;
            switch (choice) {
                case 1:
                    BookController.addBook(scanner);
                    break;
                case 2:
                    BookController.removeBook(scanner);
                    break;
                case 3:
                    BookController.updateBook(scanner);
                    break;
                case 4:
                    BookController.searchBook(scanner);
                    break;
                case 5:
                    MemberController.removeMember(scanner);
                    break;
                case 6:
                    MemberController.updateMember();
                    break;
                case 7:
                    MemberController.searchMember(scanner);
                    break;
                case 8:
                    TransactionController.checkStatusOfBooks(scanner);
                    break;
                case 9:
                    TransactionController.computeFines();
                    break;
                case 10:
                    TransactionController.collectFine();
                    break;
                case 11:
                    System.out.println("Exiting.");
                    isExitFalse = false;
                    break;
                default:
                    System.out.println("Try again:");
            }

        return isExitFalse;
    }
}
