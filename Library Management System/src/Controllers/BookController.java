package Controllers;

import Model.Book;
import Services.Activity.Books.*;

import java.util.Scanner;

public class BookController {
    public static void addBook(Scanner scanner){
        Book book = inputBooks(scanner);
        new AddBooks(book);

    }

    private static Book inputBooks(Scanner scanner){
        System.out.println("Enter Book Name");
        String title = scanner.nextLine();

        System.out.println("Genre");
        String genre = scanner.nextLine();

        System.out.println("Author");
        String author = scanner.nextLine();

        System.out.println("Total Copies");
        int totalCopies = scanner.nextInt();

        return Book.createLocalBook(-1, title, genre, author, totalCopies);
    }

    public static void removeBook(Scanner scanner){
        System.out.println("Enter Book Name");
        String title = scanner.nextLine();

        System.out.println("Author");
        String author = scanner.nextLine();

        long id = GetBookInfo.getBookIdByTitleAndName(title,author);

        new RemoveBooks(id);
    }

    public static void updateBook(Scanner scanner){
        System.out.println("Enter the old details.");

        System.out.println("Enter Book Name");
        String title = scanner.nextLine();

        System.out.println("Author");
        String author = scanner.nextLine();

        System.out.println("Enter the details you want to be updated.");
        Book book = inputBooks(scanner);
        new UpdateBooks(title, author, book);
    }

    public static void searchBook(Scanner scanner){
        System.out.println("Enter Book Name");
        String title = scanner.nextLine();

        new SearchBooks(title);
    }
}
