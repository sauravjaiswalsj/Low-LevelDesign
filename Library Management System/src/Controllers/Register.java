package Controllers;

import Model.User;
import Util.HashPassword;

import java.util.Scanner;
public class Register {
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String address;
    private boolean isMod;
    public void registerMembers(Scanner scanner){
        System.out.println("User Registration form");
        scanner.nextLine();
        System.out.println("Enter your first name?");
        firstName = scanner.nextLine();

        System.out.println("Enter your last name");
        lastName = scanner.nextLine();

        System.out.println("Enter your email?");
        email = scanner.nextLine();
        System.out.println("Enter your address?");
        address = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Are you Librarian?");
        isMod = scanner.nextBoolean();
        System.out.println("Enter your phone number?");
        phoneNumber = scanner.nextLong();
        password = HashPassword.getHashPsswd(password);
        User user =  User.createUser(firstName,lastName,email,phoneNumber,address,isMod,password);

        System.out.println("User Id: "+user.getUserId());
    }
}
