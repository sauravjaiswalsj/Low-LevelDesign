package Services.Database;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public static void createUserTable(){
        try{
            Connection connection = DatabaseConnection.connect();
            if(!DatabaseConnection.tableExists(connection,"members")){
                String user = "CREATE TABLE IF NOT EXISTS members ("+
                        "user_id SERIAL PRIMARY KEY, first_name VARCHAR(255)," +
                        "last_name VARCHAR(255), email VARCHAR(255)," +
                        "phone_no INTEGER, address VARCHAR(255), is_mod BOOLEAN, psswd VARCHAR(255))";
                connection.prepareStatement(user).execute();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void addUser(User user){
        try{
            Connection connection = DatabaseConnection.connect();
            String userData = "INSERT INTO members(first_name, last_name, email, phone_no, address, is_mod,psswd) VALUES(?, ?, ?, ?, ?, ?, ?)";
            String email = user.getEmail();
            long userId = getUserId(email);
            if(userId > 0){
                System.out.println("User already exists: "+ email + " user id: " + userId);
            }
            System.out.println("Creating User");
            try(PreparedStatement preparedStatement = connection.prepareStatement(userData)){
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, email);
                preparedStatement.setLong(4, user.getPhoneNumber());
                preparedStatement.setString(5, user.getAddress());
                preparedStatement.setBoolean(6, user.isMod());
                preparedStatement.setString(7, user.getPassword());

                preparedStatement.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static long getUserId(String email){
        try{
            Connection connection = DatabaseConnection.connect();
            String getUserId = "SELECT user_id from members where email = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(getUserId)){
                preparedStatement.setString(1, email);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    // Check if the ResultSet has any rows
                    if (rs.next()) {
                        // Move the cursor to the first row
                        return rs.getLong("user_id");
                    } else {
                        System.out.println("User not found with email: " + email);
                        return -1; // Or throw an exception or handle accordingly
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean authenticate(long userId, String password){
        String hashPassword = "";
        try{
            Connection connection = DatabaseConnection.connect();
            String getUserPassword  = "SELECT psswd from members where user_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(getUserPassword)){
                preparedStatement.setLong(1,userId);
                try(ResultSet set = preparedStatement.executeQuery()){
                    if(set.next()){
                        hashPassword +=  set.getString("psswd");
                    }else{
                        System.out.println("User not found with email: ");
                        return false; // Or throw an exception or handle accordingly
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password.equals(hashPassword);
    }

    public static User getUserInfo(long userId){
        try{
            Connection connection = DatabaseConnection.connect();
            String getUserPassword  = "SELECT user_id, email, first_name, last_name, is_mod, phone_no, address from members where user_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(getUserPassword)){
                preparedStatement.setLong(1,userId);
                try(ResultSet set = preparedStatement.executeQuery()){
                    if(set.next()){
                        User user = User.setLocalUser(userId,
                                set.getString("first_name"),
                                set.getString("last_name"),
                                set.getString("email"),
                                set.getLong("phone_no"),
                                set.getBoolean("is_mod"),
                                set.getString("address"));
                        return user;
                    }else{
                        System.out.println("User not found with email: ");
                        return null; // Or throw an exception or handle accordingly
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeUser(long userId){
        try {
            Connection connection = DatabaseConnection.connect();
            String query = "DELETE FROM members where user_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setLong(1, userId);

                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
