package Services.Activity.Transaction;

import Services.Database.LendingBooks;

public class ReturnBooks {
    public ReturnBooks(String title, String author, long userId ){
        LendingBooks.returnBorrowedBook(userId);
        new ComputeFines(userId);
    }
}
