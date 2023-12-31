package Controllers;
import Model.User;
import Services.Activity.Members.Register;
import Services.Database.UserRepository;
import Util.HashPassword;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryLogin {
    private Admin admin = new Admin();
    private Student student = new Student();
    private Register register;
    private long userId;
    private String password;
    private String email;

    private boolean isMod;

    public void memberLogin(Scanner scanner, Register register){
        this.register = register;
        int option;
        boolean isTrue = true;
        try {
            do {
                System.out.println("1. Login via user id:");
                System.out.println("2. Login via email:");
                System.out.println("3. Register user");
                System.out.println("4. Exit");
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        System.out.println("Enter your userId:");
                        this.userId = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("Enter your password:");
                        this.password = HashPassword.getHashPsswd(scanner.nextLine());
                        login(userId, password,scanner);
                        break;
                    case 2:
                        System.out.println("Enter your Email");
                        this.email = scanner.nextLine();
                        System.out.println("Enter your password:");
                        this.password = HashPassword.getHashPsswd(scanner.nextLine());
                        login(email,password,scanner);
                        break;
                    case 3:
                        isTrue = false;
                        register.registerMembers(scanner);
                        break;
                    case 4:
                        System.out.println("Bye");
                        isTrue = false;
                        break;
                    default:
                        System.out.println("Please re-enter your choice");
                }
            } while (isTrue);
        }catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); // consume the invalid input
        }
    }

    private void login(long userId, String password, Scanner scanner){
        if(UserRepository.authenticate(userId,password)){
            checkIfUserIsAdmin(userId,scanner);
        }else{
            System.out.println("Failed to login");
        }
    }

    private void checkIfUserIsAdmin(long userId, Scanner scanner) {
        User user = UserRepository.getUserInfo(userId);
        if(user.isMod()){
            admin.adminController(user, scanner);
        }
        else{
            student.studentController(user, scanner);
        }
    }

    private void login(String email, String password, Scanner scanner){
        this.userId = UserRepository.getUserId(email);
        if(UserRepository.authenticate(userId,password)){
            checkIfUserIsAdmin(userId, scanner);
        }else {
            System.out.println("Failed to login");
        }
    }
}
