package Services.Activity.Transaction;

import Model.BookStats;
import Services.Database.Stats;

public class CheckStatusOfBooks {
    private static BookStats stats;

    private static void setStats(long bookId){
        stats = Stats.getBookStats(bookId);
    }
    public static boolean isBookAvailable(long bookId){
        setStats(bookId);
        return stats.getAvailableBooks() > 0;
    }

    public static int getAvailableBooks(long bookId){
        setStats(bookId);
        return stats.getAvailableBooks();
    }
}
