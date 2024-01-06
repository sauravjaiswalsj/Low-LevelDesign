package Services.Activity.Transaction;

import Services.Database.LendingBooks;

public class ReturnBooks {
    public ReturnBooks(String title, String author, long userId ){
        try{
            LendingBooks.returnBorrowedBook(userId);
            new ComputeFines(userId);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
