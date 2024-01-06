package Services.Activity.Books;

import Model.Book;
import Model.BookStats;
import Services.Database.BookRepository;
import Services.Database.Stats;

public class UpdateBooks {
    public UpdateBooks(String title, String author, Book book){
        Book getOldBook = BookRepository.getBook(title, author);
        book.setBookId(getOldBook.getBookId());
        BookRepository.updateBook(book);
        BookStats bookStats = Stats.getBookStats(getOldBook.getBookId());

        int oldTotalCopies = getOldBook.getTotalCopies();
        int newTotalCopies = book.getTotalCopies();

        if(oldTotalCopies != newTotalCopies){
            int diff = oldTotalCopies > newTotalCopies ?  newTotalCopies - oldTotalCopies : Math.abs(oldTotalCopies - newTotalCopies);

            int available = bookStats.getAvailableBooks() + diff;
            Stats.updateBookStats(getOldBook.getBookId(), available, newTotalCopies);
        }

        System.out.printf("%s is now updated \n", book.getTitle());
    }
}
