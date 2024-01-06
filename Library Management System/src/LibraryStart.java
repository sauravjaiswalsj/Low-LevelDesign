import Controllers.LibraryLogin;
import Services.Activity.Members.Register;
import Services.Database.DatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;

public class LibraryStart {
    private static final LibraryLogin login = new LibraryLogin();
    private static final Register register = new Register();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Library Management System");

        //establish connection with db
        communication();
        boolean startStop = true;
        do{
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3.Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    register.registerMembers(scanner);
                    break;
                case 2:
                    login.memberLogin(scanner, register);
                    break;
                case 3:
                    startStop = false;
                    break;
                default:
                    System.out.println("Oops. Invalid option.");
            }
        }while(startStop);
        scanner.close();
    }

    public static void communication(){
        try{
            DatabaseConnection.connect();
            DatabaseConnection.initialize();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Could not connect to Database");
        }
    }
}