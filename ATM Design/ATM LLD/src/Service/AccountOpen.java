package Service;

import Model.User;
import Controller.UserController;

public class AccountOpen {
    private User user;
    private UserController userController;
    public enum AccountType {
        SAVINGS,
        CURRENT
    }
    private int accountNumber;
    private void openNewAccount(int accNumber, int pin, double balance, String accType){
       user = new User(accNumber,pin, balance, accType);
       this.userController.AddUser(user);
    }

    private boolean generateAccountNumber() throws Exception{
        try{
            this.accountNumber = (int) ((Math.random()*237) * (Math.random() *3739));
        }catch (Exception ex){
            throw new Exception("Cannot create account");
        }
        return true;
    }

    public AccountOpen(UserController userController,int pin, double balance,int accountType) throws Exception {
        this.userController = userController;
        AccountType accType = accountType == 1 ? AccountType.SAVINGS : AccountType.CURRENT;
        try {
            if (generateAccountNumber()) {
                openNewAccount(this.accountNumber, pin, balance, String.valueOf(accType));
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void display() {
        System.out.println("Congratulations, your account has been created. Acc no.: "+this.accountNumber);
    }
}
