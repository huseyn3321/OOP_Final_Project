package model;

public class Thesis extends LibraryItem {
    public Thesis(String id, String title, String author) { super(id, title, author); }
    @Override public String getItemType() { return "Tez"; }
    @Override public int getMaxLoanDays() { return 21; }
    @Override public double calculateFine(int overdueDays) { return overdueDays <= 0 ? 0.0 : overdueDays * 0.50; }
}