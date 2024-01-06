package Services.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Book;

public class BookRepository {
    public static void createBookTable(){
        try{
            Connection connection = DatabaseConnection.connect();
            if(!DatabaseConnection.tableExists(connection,"books")) {
                String book = "CREATE TABLE IF NOT EXISTS books (" +
                        "book_id SERIAL PRIMARY KEY, title VARCHAR(255)," +
                        "genre VARCHAR(255), author VARCHAR(255), " +
                        "total_copies INTEGER" +
                        ")";
                connection.prepareStatement(book).execute();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertBook(Book book){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "INSERT INTO books (title, genre, author, total_copies) VALUES(?, ?, ?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1, book.getTitle().toLowerCase());
                preparedStatement.setString(2, book.getGenre().toLowerCase());
                preparedStatement.setString(3, book.getAuthor().toLowerCase());
                preparedStatement.setInt(4, book.getTotalCopies());

                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeBook(String title, String author){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "UPDATE books SET total_copies = -1 where title = ? AND author = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1, title.toLowerCase());
                preparedStatement.setString(2, author.toLowerCase());
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeBookById(long id){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "UPDATE books SET total_copies = -1 where book_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Book> getBooks(String title){
        List<Book> books = new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "SELECT * from books where title = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1,title.toLowerCase());
                try(ResultSet set = preparedStatement.executeQuery()){
                    while (set.next()) {
                        books.add(Book.createLocalBook(
                                set.getLong("book_id"),
                                set.getString("title"),
                                set.getString("genre"),
                                set.getString("author"),
                                set.getInt("total_copies")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public static Book getBook(String title, String author){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "SELECT * from books where title = ? AND author = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1,title.toLowerCase());
                preparedStatement.setString(2, author.toLowerCase());
                try(ResultSet set = preparedStatement.executeQuery()){
                    if(set.next()){
                        Book book= Book.createLocalBook(
                                set.getLong("book_id"),
                                set.getString("title"),
                                set.getString("genre"),
                                set.getString("author"),
                                set.getInt("total_copies")
                        );
                        System.out.println(book.getTitle());
                        return book;
                    }else{
                        System.out.println("Book Not found in the repository");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Book getBookById(long bookId){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "SELECT * from books where book_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setLong(1,bookId);
                try(ResultSet set = preparedStatement.executeQuery()){
                    if(set.next()){
                        Book book= Book.createLocalBook(
                                set.getLong("book_id"),
                                set.getString("title"),
                                set.getString("genre"),
                                set.getString("author"),
                                set.getInt("total_copies")
                        );
                        System.out.println(book.getTitle());
                        return book;
                    }else{
                        System.out.println("Book Not found in the repository");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Book updateBook(Book book){
        try{
            Connection connection = DatabaseConnection.connect();
            if(book == null && book.getBookId() == -1){
                System.out.println("Book not found");
            }
            String query = "UPDATE books SET title = ?, genre = ?, author =  ?, total_copies = ? where book_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, book.getTitle().toLowerCase());
                preparedStatement.setString(2, book.getGenre().toLowerCase());
                preparedStatement.setString(3, book.getAuthor().toLowerCase());
                preparedStatement.setInt(4, book.getTotalCopies());
                preparedStatement.setLong(5,book.getBookId());
                preparedStatement.executeUpdate();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

}
