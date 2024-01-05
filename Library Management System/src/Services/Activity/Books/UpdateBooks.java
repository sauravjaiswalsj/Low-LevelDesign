package Services.Activity.Books;

import Model.Book;
import Services.Database.BookRepository;

public class UpdateBooks {
    public UpdateBooks(Book book){
        BookRepository.updateBook(book);
    }
}
