package Services.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Book;

public class BookRepository {
    public static void createBookTable(){
        try{
            Connection connection = DatabaseConnection.connect();
            if(!DatabaseConnection.tableExists(connection,"books")) {
                String book = "CREATE TABLE IF NOT EXISTS books (" +
                        "book_id SERIAL PRIMARY KEY, title VARCHAR(255)," +
                        "genre VARCHAR(255), author VARCHAR(255), available_copies INTEGER, " +
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
            String bookData = "INSERT INTO books (title, genre, author, available_copies, total_copies) VALUES(?, ?, ?, ?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getGenre());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setInt(4, book.getAvailableCopies());
                preparedStatement.setInt(5, book.getTotalCopies());

                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeBook(String title, String author){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "DELETE FROM books where title = ? AND author = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, author);
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeBookById(long id){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "DELETE FROM books where book_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Book getBook(String title, String author){
        try{
            Connection connection = DatabaseConnection.connect();
            String bookData = "SELECT * from books where title = ? AND author = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(bookData)){
                preparedStatement.setString(1,title);
                preparedStatement.setString(2,author);
                try(ResultSet set = preparedStatement.executeQuery()){
                    if(set.next()){
                        Book book= Book.createLocalBook(
                                set.getLong("book_id"),
                                set.getString("title"),
                                set.getString("genre"),
                                set.getString("author"),
                                set.getInt("available_copies"),
                                set.getInt("total_copies")
                        );
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
            Book temp = getBook(book.getTitle(),book.getAuthor());
            if(temp == null && temp.getTitle() == null){
                System.out.println("Book not found");
            }
            String query = "UPDATE books SET title = ?, genre = ?, author =  ?, available_copies = ?, total_copies = ? where book_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getGenre());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setInt(4, book.getAvailableCopies());
                preparedStatement.setInt(5, book.getTotalCopies());
                preparedStatement.setLong(6,temp.getBookId());
                preparedStatement.executeUpdate();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

}
