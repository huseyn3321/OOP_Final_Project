package service;

import exception.BorrowLimitExceededException;
import exception.ItemNotAvailableException;
import model.LibraryItem;
import model.Member;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Map<String, LibraryItem> catalog = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();

    public void addItem(LibraryItem item) {
        if (catalog.containsKey(item.getId())) return;
        catalog.put(item.getId(), item);
        System.out.println("Urun eklendi: " + item.getTitle());
    }

    public void registerMember(Member member) {
        if (members.containsKey(member.getMemberId())) return;
        members.put(member.getMemberId(), member);
        System.out.println("Uye kaydedildi: " + member.getName());
    }

    public void borrowItem(String memberId, String itemId) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);
        if (member == null || item == null) {
            System.out.println("Islem basarisiz: Kullanici veya Urun bulunamadi.");
            return;
        }
        try {
            member.borrowItem(item);
            System.out.println("Basarili! " + member.getName() + " '" + item.getTitle() + "' odunc aldi.");
        } catch (ItemNotAvailableException | BorrowLimitExceededException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    public void returnItem(String memberId, String itemId, int overdueDays) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);
        if (member == null || item == null || !member.getBorrowedItems().contains(item)) {
            System.out.println("Islem basarisiz: Kayit eslesmedi.");
            return;
        }
        member.returnItem(item);
        double fine = item.calculateFine(overdueDays) * member.getFineMultiplier();
        System.out.println("Basarili! '" + item.getTitle() + "' iade edildi.");
        if (overdueDays > 0) System.out.printf("⚠️ Gecikme Cezasi: %.2f AZN%n", fine);
    }

    public List<LibraryItem> searchByTitle(String keyword) {
        return catalog.values().stream().filter(i -> i.getTitle().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }

    public List<LibraryItem> searchByAuthor(String keyword) {
        return catalog.values().stream().filter(i -> i.getAuthor().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }

    public void listAllAvailable() {
        catalog.values().stream().filter(LibraryItem::isAvailable).forEach(LibraryItem::displayInfo);
    }

    public void getMemberReport(String memberId) {
        Member m = members.get(memberId);
        if (m == null) return;
        System.out.println("\n--- Uye Raporu ---");
        System.out.println("Isim: " + m.getName() + " | Tier: " + m.getTierName());
        System.out.println("Mevcut Kitap Sayisi: " + m.getBorrowedItems().size() + " / " + m.getMaxBorrowLimit());
    }
}