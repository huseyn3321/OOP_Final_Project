package model;

import exception.BorrowLimitExceededException;
import exception.ItemNotAvailableException;
import java.util.ArrayList;
import java.util.List;

public abstract class Member {
    private String memberId;
    private String name;
    private List<LibraryItem> borrowedItems;

    public Member(String memberId, String name) {
        setMemberId(memberId);
        setName(name);
        this.borrowedItems = new ArrayList<>();
    }

    public abstract int getMaxBorrowLimit();
    public abstract double getFineMultiplier();
    public abstract String getTierName();

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) throw new IllegalArgumentException("Uye ID bos olamaz.");
        this.memberId = memberId;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Isim bos olamaz.");
        this.name = name;
    }

    public List<LibraryItem> getBorrowedItems() { return borrowedItems; }

    public void borrowItem(LibraryItem item) {
        if (!item.isAvailable()) throw new ItemNotAvailableException("Urun '" + item.getTitle() + "' zaten alinmis.");
        if (borrowedItems.size() >= getMaxBorrowLimit()) throw new BorrowLimitExceededException("Uye maksimum limite ulasti: " + getMaxBorrowLimit());
        borrowedItems.add(item);
        item.borrow(this);
    }

    public void returnItem(LibraryItem item) {
        if (borrowedItems.remove(item)) {
            item.returnItem(this);
        }
    }
}