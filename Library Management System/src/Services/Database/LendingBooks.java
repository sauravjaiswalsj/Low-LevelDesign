package Services.Database;

import Model.BorrowedBooks;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;

public class LendingBooks {
    public static void createLendingTable(){
        try{
            Connection connection = DatabaseConnection.connect();
            if(!DatabaseConnection.tableExists(connection,"borrowed_books")){
                String borrowedBooks = "CREATE TABLE IF NOT EXISTS borrowed_books (" +
                        "borrow_id SERIAL PRIMARY KEY," +
                        "user_id INTEGER," +
                        "book_id INTEGER," +
                        "borrow_date DATE," +
                        "expected_return_date DATE," +
                        "return_date DATE," +
                        "FOREIGN KEY (user_id) REFERENCES members(user_id)," +
                        "FOREIGN KEY (book_id) REFERENCES books(book_id)" +
                        ")";
                connection.prepareStatement(borrowedBooks).execute();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void insertBorrowedBook(long userId, long bookId) {
        try (Connection connection = DatabaseConnection.connect()) {
            String insertQuery = "INSERT INTO borrowed_books (user_id, book_id, borrow_date, expected_return_date, return_date) " +
                    "VALUES (?, ?, ?, ?, ?)";

            LocalDate borrowDate = LocalDate.now();
            LocalDate expectedReturnDate = borrowDate.plusDays(30);
            LocalDate returnDate = null; // return date is initially null

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setLong(1, userId);
                preparedStatement.setLong(2, bookId);
                preparedStatement.setDate(3, Date.valueOf(borrowDate));
                preparedStatement.setDate(4, Date.valueOf(expectedReturnDate));
                preparedStatement.setDate(5, returnDate != null ? Date.valueOf(returnDate) : null);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void returnBorrowedBook(long userId){
        try (Connection connection = DatabaseConnection.connect()) {
            String insertQuery = "UPDATE borrowed_books SET return_date = ? where user_id = ?";

            LocalDate returnDate = LocalDate.now();

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setDate(1, Date.valueOf(returnDate));
                preparedStatement.setLong(2, userId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static BorrowedBooks getBorrowedBookData(long userId){
        try{
            Connection connection = DatabaseConnection.connect();
            String query = "SELECT * from borrowed_books where user_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setLong(1,userId);

                try(ResultSet set = preparedStatement.executeQuery()){
                    while(set.next()){
                        return new BorrowedBooks(
                                set.getLong("book_id"),
                                userId,
                                set.getDate("borrow_date").toLocalDate(),
                                set.getDate("expected_return_date").toLocalDate(),
                                set.getDate("return_date").toLocalDate()
                                );
                    }
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("No data exists");
        return null;
    }
}
