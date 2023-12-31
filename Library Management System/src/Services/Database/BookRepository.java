package Services.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Book;

public class BookRepository {
    public static void createBookTable(){
        try{
            Connection connection = DatabaseConnection.connect();
            if(!DatabaseConnection.tableExists(connection,"books")) {
                String book = "CREATE TABLE IF NOT EXISTS books (" +
                        "book_id SERIAL PRIMARY KEY, title VARCHAR(255)," +
                        "genre VARCHAR(255), available_copies INTEGER, total_copies INTEGER" +
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
            String bookData = "INSERT INTO books (title, genre, available_copies, total_copies) VALUES(?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getGenre());
                preparedStatement.setInt(3, book.getAvailableCopies());
                preparedStatement.setInt(4, book.getTotalCopies());

                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeBook(Book book){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "DELETE FROM books where title = (title) VALUES(?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1, book.getTitle());

                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
