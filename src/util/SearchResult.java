package util;

import model.LibraryItem;
import java.util.List;

public class SearchResult<T extends LibraryItem> {
    private List<T> results;
    public SearchResult(List<T> results) { this.results = results; }
    public void display() {
        if (results.isEmpty()) { System.out.println("Sonuc bulunamadi."); return; }
        results.forEach(LibraryItem::displayInfo);
    }
    public int getCount() { return results.size(); }
}