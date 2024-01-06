package Services.Activity.Transaction;

import Model.Book;
import Model.BookStats;
import Services.Database.BookRepository;
import Services.Database.LendingBooks;
import Services.Database.Stats;

public class BorrowBooks {
    public BorrowBooks(String title, String author, long userId){
        try{
            Book book = BookRepository.getBook(title, author);
            long id = book.getBookId();
            if(CheckStatusOfBooks.isBookAvailable(id)){
                System.out.println("Book Info: ");
                System.out.println("------------------------------------------------");
                System.out.println(book.toString());
                System.out.println("------------------------------------------------");

                LendingBooks.insertBorrowedBook(userId,id);
                int available = CheckStatusOfBooks.getAvailableBooks(id);
                Stats.updateAvailableBookStats(id,available -1);
            }else{
                System.out.println("No book available." + book.getBookId());
            }


        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
