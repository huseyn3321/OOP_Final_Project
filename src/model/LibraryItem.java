package model;

public abstract class LibraryItem implements Borrowable {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;

    public LibraryItem(String id, String title, String author) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        this.isAvailable = true;
    }

    public abstract String getItemType();
    public abstract int getMaxLoanDays();

    public String getId() { return id; }
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("ID bos olamaz.");
        this.id = id;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Baslik bos olamaz.");
        this.title = title;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) throw new IllegalArgumentException("Yazar bos olamaz.");
        this.author = author;
    }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    public void displayInfo() {
        System.out.printf("[%s] ID: %s | Baslik: '%s' | Yazar: %s | Durum: %s (Max Sure: %d Gun)%n",
                getItemType(), id, title, author, isAvailable ? "Musait" : "Odunc Verildi", getMaxLoanDays());
    }

    @Override
    public void borrow(Member member) { this.isAvailable = false; }

    @Override
    public void returnItem(Member member) { this.isAvailable = true; }
}