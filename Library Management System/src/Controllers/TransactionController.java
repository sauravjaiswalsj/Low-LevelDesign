package Controllers;

import Services.Activity.Transaction.BorrowBooks;
import Services.Activity.Transaction.ReturnBooks;

import java.util.Scanner;

public class TransactionController {

    public static void checkStatusOfBooks(Scanner scanner){

    }

    public static void collectFine(){

    }

    public static void computeFines(){

    }

    public static void borrowBooks(Scanner scanner, long userId){
        System.out.println("Enter Book Name");
        String title = scanner.nextLine();

        System.out.println("Author");
        String author = scanner.nextLine();

        new BorrowBooks(title,author, userId);
    }

    public static void returnBooks(Scanner scanner, long userId){
        System.out.println("Enter Book Name");
        String title = scanner.nextLine();

        System.out.println("Author");
        String author = scanner.nextLine();

        new ReturnBooks(title,author, userId);
    }
    public static void payFine(Scanner scanner, long userId){

    }

}
