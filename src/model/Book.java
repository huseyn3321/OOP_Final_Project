package model;

public class Book extends LibraryItem {
    public Book(String id, String title, String author) { super(id, title, author); }
    @Override public String getItemType() { return "Kitap"; }
    @Override public int getMaxLoanDays() { return 14; }
    @Override public double calculateFine(int overdueDays) { return overdueDays <= 0 ? 0.0 : overdueDays * 0.50; }
}