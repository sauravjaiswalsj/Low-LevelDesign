package Model;

import Services.Database.BookRepository;

public class BookStats extends Book{

    private int availableBooks;

    private Book book;

    public int getAvailableBooks() {
        return availableBooks;
    }

    public BookStats(long bookId, int availableBooks){
        this.availableBooks = availableBooks;
        book = BookRepository.getBookById(bookId);
    }
}
