package Service;
import Controller.UserController;

public class Authorisation {
    public boolean validatePin(UserController userController, int accountNumber, int pin){
        return userController.validateCustomerPin(accountNumber,pin);
    }
}
