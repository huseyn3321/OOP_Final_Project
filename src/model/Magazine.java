package model;

public class Magazine extends LibraryItem {
    public Magazine(String id, String title, String author) { super(id, title, author); }
    @Override public String getItemType() { return "Dergi"; }
    @Override public int getMaxLoanDays() { return 7; }
    @Override public double calculateFine(int overdueDays) { return overdueDays <= 0 ? 0.0 : overdueDays * 0.50; }
}