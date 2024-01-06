package Services.Activity.Books;

import Model.Book;
import Services.Database.BookRepository;
import Services.Database.Stats;

public class RemoveBooks {
    public RemoveBooks(long bookId){
        try{
            BookRepository.removeBookById(bookId);
            Stats.removeBookStats(bookId);
            System.out.println("Mentioned book is removed from the library.");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
