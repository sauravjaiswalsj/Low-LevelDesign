package Model;
import java.util.logging.*;
public class User {
    private static final Logger logger = Logger.getLogger(User.class.getName());

    private final int accNumber;
    private double balance;
    private final int pin;
    private final String accountType;

    public User(int accNumber, int pin, double balance, String accType) {
        this.accountType = accType;
        this.accNumber = accNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public boolean withdraw(double amount){
        try{
            if(this.balance < amount){
                logger.info("withdraw: Insufficient Balance: "+this.balance + "Amount trying to debit: "+ amount);
                throw new Exception("Insufficient balance" +this.balance);
            }
            this.balance = balance - amount;
            logger.info("withdraw: Account Number: "+this.getAccNumber() + "Balance: " +this.balance + "Amount trying to debit: "+ amount);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public boolean deposit(double amount, int accountNumber){
        try{
            if(this.getAccNumber() != accountNumber){
                logger.info("deposit: Account not Found "+ accountNumber + "Amount trying to debit: "+ amount);
                throw new Exception("User account not found.");
            }
            this.balance += amount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        logger.info("deposit: Account Number: "+ accountNumber + "Balance: " + this.balance + "Amount trying to credit: "+ amount);
        return true;
    }

    @Override
    public String toString(){
       return String.format("User Account: %d, balance: %.2f, Acc type: %s", this.accNumber, this.balance, this.accountType);
    }
}
