package Services.Activity.Transaction;

import Model.Book;
import Model.BookStats;
import Services.Database.BookRepository;
import Services.Database.LendingBooks;

public class BorrowBooks {
    public BorrowBooks(String title, String author, long userId){
        Book book = BookRepository.getBook(title, author);

        System.out.println("Book Info: ");
        System.out.println("------------------------------------------------");
        System.out.println(book.toString());
        System.out.println("------------------------------------------------");

        LendingBooks.insertBorrowedBook(userId,book.getBookId());

    }
}
