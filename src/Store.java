import products.*;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<Integer, Product> codeProductMap = new HashMap<>();
    private Map<Electrical, Integer> electricalMap = new HashMap();
    private Map<Shoe, Integer> shoesMap = new HashMap();
    private Map<ReadableStuff, Integer> readableMap = new HashMap();
    private static Store singleToneInstance = new Store();

    private Store() {
        createProducts();
    }

    public static Store getInstance(){
        return singleToneInstance;
    }

    public Map<Integer, Product> getCodeProductMap() {return codeProductMap;}

    public Map<Electrical, Integer> getElectricalMap() {
        return electricalMap;
    }

    public Map<Shoe, Integer> getShoesMap() {
        return shoesMap;
    }

    public Map<ReadableStuff, Integer> getReadableMap() {
        return readableMap;
    }

    private void createProducts() {
        createElectrical();
        createShoes();
        createReadableStuffs();
    }

    private void createElectrical() {
        Radio radio1 = new Radio(4, RadioType.Analog);
        radio1.setPrice(100.34);
        radio1.setName("Taha");
        radio1.setCode(01);
        codeProductMap.put(radio1.getCode(), radio1);
        electricalMap.put(radio1, 3);
        Radio radio2 = new Radio(20, RadioType.Digital);
        radio2.setPrice(1020);
        radio2.setName("Bahram");
        radio2.setCode(02);
        codeProductMap.put(radio2.getCode(), radio2);
        electricalMap.put(radio2, 10);
        TV tv = new TV(42, TVType.Curve);
        tv.setPrice(5000);
        tv.setName("Wow");
        tv.setCode(03);
        codeProductMap.put(tv.getCode(), tv);
        electricalMap.put(tv, 5);
    }

    private void createShoes() {
        SportShoe sportShoes = new SportShoe(SportType.Climbing);
        sportShoes.setPrice(200);
        sportShoes.setName("tanTAk");
        sportShoes.setCode(11);
        codeProductMap.put(sportShoes.getCode(), sportShoes);
        shoesMap.put(sportShoes, 10);
        FormalShoe formalShoes = new FormalShoe(FormalType.HighHeel);
        formalShoes.setPrice(230.12);
        formalShoes.setName("Milad");
        formalShoes.setCode(12);
        codeProductMap.put(formalShoes.getCode(), formalShoes);
        shoesMap.put(formalShoes, 10);
        FormalShoe formalShoes1 = new FormalShoe(FormalType.HighHeel);
        formalShoes1.setPrice(200);
        formalShoes1.setName("Orkide");
        formalShoes1.setCode(13);
        codeProductMap.put(formalShoes1.getCode(), formalShoes1);
        shoesMap.put(formalShoes1, 12);
    }

    private void createReadableStuffs() {
        Book book1 = new Book("Ahmad Maghami");
        book1.setPrice(12);
        book1.setName("فریاد");
        book1.setCode(20);
        codeProductMap.put(book1.getCode(), book1);
        readableMap.put(book1, 20);
        Book book2 = new Book("Sara Nami");
        book2.setPrice(15);
        book2.setName("زنان و دنیای مد");
        book2.setCode(21);
        codeProductMap.put(book2.getCode(), book2);
        readableMap.put(book2, 13);
        Book book3 = new Book("Eghbal Lahooti");
        book3.setPrice(20);
        book3.setName("اشعار");
        book3.setCode(22);
        codeProductMap.put(book3.getCode(), book3);
        readableMap.put(book3, 15);
        Magazine magazine1 = new Magazine("Gol Parvar", MagazineType.Monthly);
        magazine1.setPrice(5);
        magazine1.setName("رایحه");
        magazine1.setCode(23);
        codeProductMap.put(magazine1.getCode(), magazine1);
        readableMap.put(magazine1, 40);
        Magazine magazine2 = new Magazine("Simin Nashr", MagazineType.Weekly);
        magazine2.setPrice(6);
        magazine2.setName("سرگرمی");
        magazine2.setCode(24);
        codeProductMap.put(magazine2.getCode(), magazine2);
        readableMap.put(magazine2, 32);
    }
}
