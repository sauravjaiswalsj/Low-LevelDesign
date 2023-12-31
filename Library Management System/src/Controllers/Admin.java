package Controllers;

import Model.User;

import java.util.Scanner;

public class Admin {
    private User user;
    private Scanner scanner;
    public void adminController(User user, Scanner scanner){
        this.user = user;

        System.out.printf("Welcome %s %s to the Library.", user.getFirstName(), user.getLastName());
        boolean operation = true;
        do{
            operation = performOperations();
        }while (operation);
    }

    private boolean performOperations(){
        System.out.printf("Actions:");

        //Book level
        System.out.println("1. Add a new Book");
        System.out.println("2. Remove a new Book");
        System.out.println("3. Update a new Book");
        System.out.println("4. Search a new Book");

        //Members
        System.out.println("5. Remove a new Member");
        System.out.println("6. Update a new Member");
        System.out.println("7. Search a new Book");

        //transcations
        System.out.println("8. Check status of a new Book");
        System.out.println("9. Calculate Fine");
        System.out.printf("10. Collect Fines");

        System.out.println("11. Exit");

        int choice = scanner.nextInt();


        return false;
    }
}
