package Services.Activity.Books;

import Model.Book;
import Services.Database.BookRepository;
import Services.Database.Stats;

public class AddBooks {
    public AddBooks(Book book){
        try{
            BookRepository.insertBook(book);
            Book newBook = BookRepository.getBook(book.getTitle(), book.getAuthor());
            Stats.insertBookStats(newBook.getBookId(), book.getTotalCopies());
            System.out.println("Book is now added to the library " + book.getTitle());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
