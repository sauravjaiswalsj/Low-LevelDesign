package Services.Activity.Books;

import Model.Book;
import Services.Database.BookRepository;

public class AddBooks {
    public AddBooks(Book book){
        BookRepository.insertBook(book);
    }
}
