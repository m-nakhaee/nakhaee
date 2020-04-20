import products.*;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<Electrical, Integer> electricalMap = new HashMap();
    private Map<Shoes, Integer> shoesMap = new HashMap();
    private Map<ReadableStuffs, Integer> readableMap = new HashMap();
    private static Store singleToneInstance = new Store();

    private Store() {
        createProducts();
    }

    public static Store getInstance(){
        return singleToneInstance;
    }

    private void createProducts() {
        createElectrical();
        createShoes();
        createReadableStuffs();
    }

    private void createElectrical() {
        Radio radio1 = new Radio(4, RadioType.Analog);
        radio1.setPrice(100.34);
        electricalMap.put(radio1, 3);
        Radio radio2 = new Radio(20, RadioType.Digital);
        radio2.setPrice(1020);
        electricalMap.put(radio2, 10);
        TV tv = new TV(42, TVType.Curve);
        tv.setPrice(5000);
        electricalMap.put(tv, 5);
    }

    private void createShoes() {
        SportShoes sportShoes = new SportShoes(SportType.Climbing);
        sportShoes.setPrice(200);
        shoesMap.put(sportShoes, 10);
        FormalShoes formalShoes = new FormalShoes(FormalType.HighHeel);
        formalShoes.setPrice(230.12);
        shoesMap.put(formalShoes, 10);
        FormalShoes formalShoes1 = new FormalShoes(FormalType.HighHeel);
        formalShoes1.setPrice(200);
        shoesMap.put(formalShoes1, 12);
    }

    private void createReadableStuffs() {
        Book book1 = new Book("Ahmad Maghami");
        book1.setPrice(12);
        readableMap.put(book1, 20);
        Book book2 = new Book("Sara Nami");
        book2.setPrice(15);
        readableMap.put(book2, 13);
        Book book3 = new Book("Eghbal Lahooti");
        book3.setPrice(22);
        readableMap.put(book3, 15);
        Magazine magazine1 = new Magazine("Gol Parvar", MagazineType.Monthly);
        magazine1.setPrice(5);
        readableMap.put(magazine1, 40);
        Magazine magazine2 = new Magazine("Simin Nashr", MagazineType.Weekly);
        magazine2.setPrice(6);
        readableMap.put(magazine2, 32);
    }
}
