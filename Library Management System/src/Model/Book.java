package Model;

import Services.Database.BookRepository;

import java.sql.Struct;

public class Book {
    private long bookId;
    private String title;
    private String genre;
    private int availableCopies;
    private int totalCopies;
    private String author;

    private Book(long bookId,String title, String genre, int availableCopies, int totalCopies, String author){
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
        this.author = author;
    }
    public static Book createLocalBook(long bookId, String title, String genre, String author, int availableCopies, int totalCopies){
        Book book =  new Book(bookId ,title,genre,availableCopies,totalCopies,author);
        return book;
    }

    public long getBookId() {
        return bookId;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }
}
