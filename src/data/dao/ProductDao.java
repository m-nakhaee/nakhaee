package data.dao;

import data.entity.products.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    @Autowired
    SessionFactory sessionFactory;

    public Product search(int id) {  //TODO rewrite it by value object
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, id);
        if (id >= 1000 && id < 1500) product = session.get(TV.class, id);
        if (id >= 1500 && id < 2000) product = session.get(Radio.class, id);
        if (id >= 2000 && id < 2500) product = session.get(SportShoes.class, id);
        if (id >= 2500 && id < 3000) product = session.get(FormalShoes.class, id);
        if (id >= 3000 && id < 3500) product = session.get(Book.class, id);
        if (id >= 3500 && id < 4000) product = session.get(Magazine.class, id);
        session.close();
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.addAll(getTVs());
        productList.addAll(getRadios());
        productList.addAll(getSportShoes());
        productList.addAll(getFormalShoes());
        productList.addAll(getBooks());
        productList.addAll(getMagazines());
        return productList;
    }

    private List<TV> getTVs() {
        Session session = sessionFactory.openSession();
        Query tvQuery = session.createQuery("from data.entity.products.TV");
        List<TV> tvList = tvQuery.list();
        session.close();
        return tvList;
    }

    private List<Radio> getRadios() {
        Session session = sessionFactory.openSession();
        Query radioQuery = session.createQuery("from data.entity.products.Radio");
        List<Radio> radioList = radioQuery.list();
        session.close();
        return radioList;
    }

    private List<SportShoes> getSportShoes() {
        Session session = sessionFactory.openSession();
        Query sportQuery = session.createQuery("from data.entity.products.SportShoes");
        List<SportShoes> sportShoesList = sportQuery.list();
        session.close();
        return sportShoesList;
    }

    private List<FormalShoes> getFormalShoes() {
        Session session = sessionFactory.openSession();
        Query formalShoesQuery = session.createQuery("from data.entity.products.FormalShoes");
        List<FormalShoes> formalShoesList = formalShoesQuery.list();
        session.close();
        return formalShoesList;
    }

    private List<Book> getBooks() {
        Session session = sessionFactory.openSession();
        Query bookQuery = session.createQuery("from data.entity.products.Book");
        List<Book> bookList = bookQuery.list();
        session.close();
        return bookList;
    }

    private List<Magazine> getMagazines() {
        Session session = sessionFactory.openSession();
        Query magazineQuery = session.createQuery("from data.entity.products.Magazine");
        List<Magazine> magazineList = magazineQuery.list();
        session.close();
        return magazineList;
    }

    public void updateCount(int id, int count) {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, id);
        int oldCount = product.getCount();
        product.setCount(oldCount - count);
        session.close();
    }


}
