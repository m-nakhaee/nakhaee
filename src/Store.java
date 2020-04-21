import products.*;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<Electrical, Integer> electricalMap = new HashMap();
    private Map<Shoe, Integer> shoesMap = new HashMap();
    private Map<ReadableStuff, Integer> readableMap = new HashMap();
    private static Store singleToneInstance = new Store();

    private Store() {
        createProducts();
    }

    public Map<Electrical, Integer> getElectricalMap() {
        return electricalMap;
    }

    public Map<Shoe, Integer> getShoesMap() {
        return shoesMap;
    }

    public Map<ReadableStuff, Integer> getReadableMap() {
        return readableMap;
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
        SportShoe sportShoes = new SportShoe(SportType.Climbing);
        sportShoes.setPrice(200);
        shoesMap.put(sportShoes, 10);
        FormalShoe formalShoes = new FormalShoe(FormalType.HighHeel);
        formalShoes.setPrice(230.12);
        shoesMap.put(formalShoes, 10);
        FormalShoe formalShoes1 = new FormalShoe(FormalType.HighHeel);
        formalShoes1.setPrice(200);
        shoesMap.put(formalShoes1, 12);
    }

    private void createReadableStuffs() {
        Book book1 = new Book("Ahmad Maghami");
        book1.setPrice(12);
        book1.setName("فریاد");
        readableMap.put(book1, 20);
        Book book2 = new Book("Sara Nami");
        book2.setPrice(15);
        book2.setName("زنان و دنیای مد");
        readableMap.put(book2, 13);
        Book book3 = new Book("Eghbal Lahooti");
        book3.setPrice(22);
        book3.setName("اشعار");
        readableMap.put(book3, 15);
        Magazine magazine1 = new Magazine("Gol Parvar", MagazineType.Monthly);
        magazine1.setPrice(5);
        magazine1.setName("رایحه");
        readableMap.put(magazine1, 40);
        Magazine magazine2 = new Magazine("Simin Nashr", MagazineType.Weekly);
        magazine2.setPrice(6);
        magazine2.setName("سرگرمی");
        readableMap.put(magazine2, 32);
    }
}
