package Services.Activity.Books;

import Model.Book;
import Services.Database.BookRepository;

import java.util.List;

public class GetBookInfo {
    public static long getBookIdByTitleAndName(String title, String author){
        try {
            Book book = BookRepository.getBook(title, author);
            return book.getBookId();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Book not found");
            return -1;
        }
    }
}
