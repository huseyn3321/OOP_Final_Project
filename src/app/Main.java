package app;

import model.*;
import service.Library;
import util.SearchResult;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library system = new Library();
        Scanner scanner = new Scanner(System.in);

        // Test Verileri
        system.addItem(new Book("B1", "Effective Java", "Joshua Bloch"));
        system.addItem(new Magazine("M1", "National Geographic", "Various"));
        system.addItem(new Thesis("T1", "AI Architecture", "Dr. Aliyev"));
        system.registerMember(new StandardMember("S1", "Alice"));
        system.registerMember(new VIPMember("V1", "Emin"));

        while (true) {
            System.out.println("\n===== Kutuphane Yonetim Sistemi =====");
            System.out.println("1. Yeni Urun Ekle");
            System.out.println("2. Yeni Uye Kaydet");
            System.out.println("3. Odunc Al");
            System.out.println("4. Iade Et");
            System.out.println("5. Arama Yap");
            System.out.println("6. Uye Raporu Goruntule");
            System.out.println("7. Musait Tum Urunleri Listele");
            System.out.println("8. Cikis");
            System.out.print("Seciminiz: ");

            String input = scanner.nextLine().trim();
            if (input.equals("8")) { System.out.println("Sistem kapatiliyor..."); break; }

            switch (input) {
                case "1":
                    System.out.print("Tur (1: Kitap, 2: Dergi, 3: Tez): ");
                    String type = scanner.nextLine();
                    System.out.print("ID: "); String id = scanner.nextLine();
                    System.out.print("Baslik: "); String title = scanner.nextLine();
                    System.out.print("Yazar: "); String author = scanner.nextLine();
                    if (type.equals("1")) system.addItem(new Book(id, title, author));
                    else if (type.equals("2")) system.addItem(new Magazine(id, title, author));
                    else if (type.equals("3")) system.addItem(new Thesis(id, title, author));
                    break;
                case "2":
                    System.out.print("Tier (1: Standard, 2: Premium, 3: VIP): ");
                    String tier = scanner.nextLine();
                    System.out.print("Uye ID: "); String mId = scanner.nextLine();
                    System.out.print("Isim: "); String name = scanner.nextLine();
                    if (tier.equals("1")) system.registerMember(new StandardMember(mId, name));
                    else if (tier.equals("2")) system.registerMember(new PremiumMember(mId, name));
                    else if (tier.equals("3")) system.registerMember(new VIPMember(mId, name));
                    break;
                case "3":
                    System.out.print("Uye ID: "); String bMem = scanner.nextLine();
                    System.out.print("Urun ID: "); String bItem = scanner.nextLine();
                    system.borrowItem(bMem, bItem);
                    break;
                case "4":
                    System.out.print("Uye ID: "); String rMem = scanner.nextLine();
                    System.out.print("Urun ID: "); String rItem = scanner.nextLine();
                    System.out.print("Geciken Gun Sayisi (Yoksa 0): ");
                    int days = Integer.parseInt(scanner.nextLine());
                    system.returnItem(rMem, rItem, days);
                    break;
                case "5":
                    System.out.print("Tur (1: Basliga gore, 2: Yazara gore): ");
                    String sType = scanner.nextLine();
                    System.out.print("Kelime: "); String keyword = scanner.nextLine();
                    if (sType.equals("1")) new SearchResult<>(system.searchByTitle(keyword)).display();
                    else new SearchResult<>(system.searchByAuthor(keyword)).display();
                    break;
                case "6":
                    System.out.print("Uye ID: "); String qId = scanner.nextLine();
                    system.getMemberReport(qId);
                    break;
                case "7":
                    system.listAllAvailable();
                    break;
                default:
                    System.out.println("Gecersiz secim.");
            }
        }
        scanner.close();
    }
}