package Controllers;

import Services.Activity.Books.GetBookInfo;
import Services.Activity.Transaction.BorrowBooks;
import Services.Activity.Transaction.CheckStatusOfBooks;
import Services.Activity.Transaction.GenerateStats;
import Services.Activity.Transaction.ReturnBooks;

import java.util.Scanner;

public class TransactionController {

    public static void checkStatusOfBooks(Scanner scanner){
        System.out.println("Enter Book Name");
        String title = scanner.nextLine();

        System.out.println("Author");
        String author = scanner.nextLine();
        long id = GetBookInfo.getBookIdByTitleAndName(title,author);

        System.out.println(CheckStatusOfBooks.getAvailableBooks(id));
    }

    public static void generateReports(){
        new GenerateStats();
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
}
