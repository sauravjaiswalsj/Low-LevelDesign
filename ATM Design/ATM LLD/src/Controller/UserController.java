package Controller;
import Model.User;

import java.util.HashSet;

public class UserController {
    private static HashSet<User> users = new HashSet<>();

    public void AddUser(User customer){
        users.add(customer);
    }

    public static void displayUserInBank(){
        for (User user: users) {
            System.out.println(user.toString());
        }
    }

    public User getUser(int accountNumber){
        User customer = null;
       for (User user : users){
           if(user.getAccNumber() == accountNumber)
               customer = user;
       }
        return customer;
    }

    public boolean validateCustomerPin(int accountNumber, int pin){
        User user = this.getUser(accountNumber);
        return user.validatePin(pin);
    }
}
