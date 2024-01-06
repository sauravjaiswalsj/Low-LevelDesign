package Services.Database;

import java.sql.*;

public class DatabaseConnection {
    //Since these methods are utility-like and don't depend on maintaining an instance-specific state, making them static simplifies their usage.
    public static Connection connect()throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Library";
        String user = "sauravjaiswal";
        String password = "#";
        return DriverManager.getConnection(url, user, password);
    }

    public static void initialize() throws SQLException {
        BookRepository.createBookTable();
        UserRepository.createUserTable();
        LendingBooks.createLendingTable();
        Fines.createPaymentTable();
        if(DatabaseConnection.tableExists(DatabaseConnection.connect(),"books"))
            Stats.createStatsTable();
    }
    public static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet set = metaData.getTables(null, null, tableName, null);
        return set.next();
    }
}
