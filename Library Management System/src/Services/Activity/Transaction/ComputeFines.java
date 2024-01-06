package Services.Activity.Transaction;

import Model.BorrowedBooks;
import Services.Database.Fines;
import Services.Database.LendingBooks;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ComputeFines {
    public ComputeFines(long userId){
        try{
            BorrowedBooks borrowedBooks =  LendingBooks.getBorrowedBookData(userId);

            LocalDate expectedReturn =  borrowedBooks.getExpectedReturnDate();
            LocalDate returnDate = borrowedBooks.getActualReturnDate();


            if(!isFineApplicable(expectedReturn,returnDate)){
                System.out.println("Your book is return with no dues");
            }else{
                long daysDifference = ChronoUnit.DAYS.between(expectedReturn, returnDate);
                double fine = getFine(daysDifference);
                System.out.println("Collect Fine: " + fine);
                Fines.insertFine(userId,borrowedBooks.getBookId(), fine);
                System.out.println("Fine Collected" + fine);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private boolean isFineApplicable(LocalDate expectedReturn, LocalDate returnDate){
        return returnDate.isAfter(expectedReturn);
    }

    private double getFine(long days){
        if(days == 1 ){
            return 5.0;
        }
        else if(days >= 1 && days < 2){
            return 10.0;
        }else if(days > 1 && days < 5){
            return 1.5 * days;
        }else{
            return 2.5* days;
        }
    }
}
