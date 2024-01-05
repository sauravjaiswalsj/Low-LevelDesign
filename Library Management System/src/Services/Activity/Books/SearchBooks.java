package Services.Activity.Books;

import Model.Book;
import Services.Database.BookRepository;

public class SearchBooks {
    public SearchBooks(String title, String author){
        Book book = BookRepository.getBook(title,author);

        System.out.println("Book Info: ");
        System.out.printf("Book Id: "+ book.getBookId());
        System.out.println("Title: "+book.getTitle());
        System.out.println("Genre = " + book.getGenre());
        System.out.println("Author() = " + book.getAuthor());
        System.out.println("AvailableCopies() = " + book.getAvailableCopies());
        System.out.println("TotalCopies() = " + book.getTotalCopies());
    }
}
