package Model;

import java.time.LocalDate;

public class BorrowedBooks {
    private long bookId;
    private long userId;
    private LocalDate borrowedBookDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

    public BorrowedBooks(long bookId, long userId, LocalDate borrowedBookDate, LocalDate expectedReturnDate, LocalDate actualReturnDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.borrowedBookDate = borrowedBookDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
    }

    public LocalDate getBorrowedBookDate() {
        return borrowedBookDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }
}
