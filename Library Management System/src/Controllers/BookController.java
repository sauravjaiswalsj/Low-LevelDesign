package Controllers;

import Model.Book;
import Services.Activity.Books.AddBooks;
import Services.Activity.Books.RemoveBooks;
import Services.Activity.Books.SearchBooks;
import Services.Activity.Books.UpdateBooks;

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

        System.out.println("Available Copies");
        int availableCopies = scanner.nextInt();

        System.out.println("Total Copies");
        int totalCopies = scanner.nextInt();

        return Book.createLocalBook(-1, title, genre, author, availableCopies, totalCopies);
    }

    public static void removeBook(Scanner scanner){
        System.out.println("Enter Book Name");
        String title = scanner.nextLine();

        System.out.println("Author");
        String author = scanner.nextLine();

        new RemoveBooks(title, author);
    }

    public static void updateBook(Scanner scanner){
        Book book = inputBooks(scanner);
        new UpdateBooks(book);
    }

    public static void searchBook(Scanner scanner){
        System.out.println("Enter Book Name");
        String title = scanner.nextLine();

        System.out.println("Author");
        String author = scanner.nextLine();

        new SearchBooks(title, author);
    }
}
