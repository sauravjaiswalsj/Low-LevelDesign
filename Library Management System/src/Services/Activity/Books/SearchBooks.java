package Services.Activity.Books;

import Model.Book;
import Services.Database.BookRepository;
import Services.Database.Stats;

import java.util.List;

public class SearchBooks {
    public SearchBooks(String title){
        try{
            List<Book> books = BookRepository.getBooks(title);
            if(books == null){
                System.out.println("Book not found in the library");
                return;
            }

            System.out.println("Book Info: ");
            System.out.println("------------------------------------------------");

            for(Book book : books){
                System.out.println(book.toString());
                System.out.println("------------------------------------------------");
                System.out.println();
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
