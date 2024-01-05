package Services.Activity.Books;

import Model.Book;
import Services.Database.BookRepository;

public class RemoveBooks {
    public RemoveBooks(String title, String author){
        BookRepository.removeBook(title,author);
    }
}
