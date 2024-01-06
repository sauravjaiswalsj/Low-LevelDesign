package Services.Database;

import Model.BookStats;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;

public class Stats {
    public static void createStatsTable(){
        try{
            Connection connection = DatabaseConnection.connect();
            if(!DatabaseConnection.tableExists(connection,"stats")){
                String stats = "CREATE TABLE IF NOT EXISTS stats ("+
                        "stats_id SERIAL PRIMARY KEY," +
                        "book_id INTEGER," +
                        "total_amount DECIMAL,"+
                        "available_copies INTEGER," +
                        "FOREIGN KEY (book_id) REFERENCES books(book_id)" +
                        ")";
                connection.prepareStatement(stats).execute();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void insertBookStats(long bookId, int totalCopies) {
        try {
            Connection connection = DatabaseConnection.connect();
            String insertStats = "INSERT INTO stats (book_id, total_copies, available_copies) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertStats)) {
                preparedStatement.setLong(1, bookId);
                preparedStatement.setInt(2, totalCopies);
                preparedStatement.setInt(3, totalCopies);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeBookStats(long bookId) {
        try {
            Connection connection = DatabaseConnection.connect();
            String updateStats = "Update stats SET available_copies = -1, total_copies = -1 where book_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateStats)) {
                preparedStatement.setLong(1, bookId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBookStats(long bookId, int availableBooks, int total) {
        try {
            Connection connection = DatabaseConnection.connect();
            String updateStats = "Update stats SET available_copies = ?, total_copies = ? where book_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateStats)) {
                preparedStatement.setInt(1, availableBooks);
                preparedStatement.setInt(2, total);
                preparedStatement.setLong(3, bookId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateAvailableBookStats(long bookId, int availableBooks) {
        try {
            Connection connection = DatabaseConnection.connect();
            String updateStats = "Update stats SET available_copies = ? where book_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateStats)) {
                preparedStatement.setInt(1, availableBooks);
                preparedStatement.setLong(2, bookId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static BookStats getBookStats(long bookId){
        BookStats bookStats = null;
        try{
            Connection connection = DatabaseConnection.connect();
            String query = "SELECT available_copies from stats where book_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setLong(1, bookId);
                try(ResultSet set = statement.executeQuery()){
                    if(set.next()){
                        bookStats = new BookStats(bookId,
                                set.getInt("available_copies"));
                    }
                    else{
                        System.out.println("No stats not found.");
                    }
                }

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return bookStats;
    }
    public static HashMap<String, Integer> generateStats () {
        HashMap<String, Integer> borrowedBooks = new HashMap<>();
        try {
            Connection connection = DatabaseConnection.connect();
            String updateStats = "SELECT b.book_id, b.title, COUNT(*) as borrow_count " +
                                    "FROM borrowed_books bb " +
                                    "JOIN books b ON bb.book_id = b.book_id " +
                                    "GROUP BY b.book_id, b.title " +
                                    "ORDER BY borrow_count DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateStats)) {
                try(ResultSet set = preparedStatement.executeQuery()) {
                    while (set.next()) {
                        String title = set.getString("title");
                        int count = set.getInt("borrow_count");

                        borrowedBooks.put(title,count);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedBooks;
    }
}

