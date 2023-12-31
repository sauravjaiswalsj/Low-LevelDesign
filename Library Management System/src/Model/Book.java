package Model;

import Services.Database.BookRepository;

public class Book {
    private long bookId;
    private String title;
    private String genre;
    private int availableCopies;
    private int totalCopies;

    private Book(String title, String genre, int availableCopies, int totalCopies){
        this.title = title;
        this.genre = genre;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }

    public static Book createBookData(String title, String genre, int availableCopies, int totalCopies){
       Book book =  new Book(title,genre,availableCopies,totalCopies);
       BookRepository.insertBook(book);
       return book;
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
