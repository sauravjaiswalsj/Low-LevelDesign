package Service;
import java.util.logging.*;
import Controller.UserController;
import Model.User;
public class Transaction {
    private final  Authorisation authorisation;
    private final UserController userController;
    private int accountNumber;
    private int pin;
    private double amount;

    private static final Logger logger = Logger.getLogger(User.class.getName());
    public Transaction(Authorisation authorisation, UserController userController){
        this.authorisation = authorisation;
        this.userController = userController;
    }
    public void setTransactionData(int accountNumber, int pin, double amount){
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.amount = amount;
        logger.info("Setting TransactionData: account: "+ accountNumber + "pin: " + pin + "amount: " + amount);
    }

    private boolean auth(){
        return authorisation.validatePin(userController,accountNumber, pin);
    }

    public void balanceEnquiry(){
        if(!auth())
            return;
       User user = userController.getUser(this.accountNumber);
       logger.info("Balance Enquiry"+user.toString() );
       if (user != null){
           double balance = user.getBalance();
           System.out.println("Balance for account: " + this.accountNumber + " is: " + balance);
           logger.info("Balance Enquiry for account: " + this.accountNumber + " is: " + balance );
       }else{
           System.out.println("User not found");
       }
    }

    public void withdrawMoney(){
        if(!auth())
            return;
        User user = userController.getUser(this.accountNumber);
        logger.info("withdrawMoney"+user.toString() );
        if (user != null){
            user.withdraw(this.amount);
            System.out.println("Balance for account: " + this.accountNumber + " is: " + user.getBalance());
            logger.info("withdrawMoney for account: " + this.accountNumber + " is: " + user.getBalance() );
        }else{
            System.out.println("User not found");
        }
    }

    public void depositMoney(){
        if(!auth())
            return;
        User user = userController.getUser(this.accountNumber);
        logger.info("withdrawMoney"+user.toString() );
        if (user != null){
            user.deposit(this.amount, this.accountNumber);
            System.out.println("Balance for account: " + this.accountNumber + " is: " + user.getBalance());
            logger.info("depositMoney for account: " + this.accountNumber + " is: " + user.getBalance() );
        }else{
            System.out.println("User not found");
        }
    }
}
