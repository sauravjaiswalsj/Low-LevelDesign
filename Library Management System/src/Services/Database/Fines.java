package Services.Database;
import java.sql.*;

public class Fines {
    public static void createPaymentTable(){
        try{
            Connection connection = DatabaseConnection.connect();
            if(!DatabaseConnection.tableExists(connection,"payment")){
                String fines = "CREATE TABLE IF NOT EXISTS payment ("+
                                "payment_id SERIAL PRIMARY KEY," +
                                "user_id INTEGER,"+
                                "book_id INTEGER," +
                                "amount DECIMAL,"+
                                "FOREIGN KEY (user_id) REFERENCES members(user_id)," +
                                "FOREIGN KEY (book_id) REFERENCES books(book_id))";
                connection.prepareStatement(fines).execute();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void insertFine(long userId, long bookId, double amount) {
        try (Connection connection = DatabaseConnection.connect()) {
            String insertQuery = "INSERT INTO payment (user_id, book_id, amount) " +
                    "VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setLong(1, userId);
                preparedStatement.setLong(2, bookId);
                preparedStatement.setDouble(3, amount);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getTotalFine(){
        double totalFine = 0;
        try{
            Connection connection = DatabaseConnection.connect();
            String query = "SELECT amount from payment";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){

                try(ResultSet set = preparedStatement.executeQuery()){
                    while(set.next()){
                        double fine = set.getDouble("amount");
                        totalFine += fine;
                    }
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return totalFine;
    }
}
