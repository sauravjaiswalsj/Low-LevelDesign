package Services.Activity.Books;

import Model.Book;
import Services.Database.BookRepository;
import Services.Database.Stats;

public class RemoveBooks {
    public RemoveBooks(String title, String author){
        Book newBook = BookRepository.getBook(title,author);
        BookRepository.removeBookById(newBook.getBookId());
        Stats.removeBookStats(newBook.getBookId());
        System.out.println("Mentioned book is removed from the library.");
    }
}
