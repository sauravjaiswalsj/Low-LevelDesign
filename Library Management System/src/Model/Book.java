package Model;

public class Book {
    private long bookId;
    private String title;
    private String genre;
    private int totalCopies;
    private String author;
    public Book(){

    }
    private Book(long bookId, String title, String genre, String author, int totalCopies ){
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.totalCopies = totalCopies;
    }
    public static Book createLocalBook(long bookId, String title, String genre, String author, int totalCopies){
        Book book =  new Book(bookId ,title,genre, author,totalCopies);
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

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    @Override
    public String toString() {

        String sb = "Book Id: " + this.getBookId() +
                    " Title: " + this.getTitle() +
                    " Genre = " + this.getGenre()+
                    " Author = " + this.getAuthor() +
                    " TotalCopies = " + this.getTotalCopies();
        return sb;
    }
}
