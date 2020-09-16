package data.dao;

import data.entity.Cart;
import data.entity.products.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class CartDao {
    @Autowired
    SessionFactory sessionFactory;

    private String userName; //TODO current user...

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void emptyCart() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("getCartByUserName");
        query.setParameter("userName", userName);
        List<Cart> list = query.list();
        for (Cart cart : list)
            session.delete(cart);
        session.close();
    }

    public void addToCart(int productID) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("getCartByUserNameAndProductID");
        query.setParameter("userName", userName);
        query.setParameter("productID", productID);
        Cart cartResult = (Cart) query.uniqueResult();
        if (Objects.isNull(cartResult)) {
            Cart cart = createCart(productID);
            session.save(cart);
        } else increaseCount(cartResult);
        session.close();
    }

    private Cart createCart(int productID) {
        Cart cart = new Cart();
        cart.setOrderCount(1);
        cart.setProductID(productID);
        cart.setUserName(userName);
        return cart;
    }

    private void increaseCount(Cart cartResult) {
        int count = cartResult.getOrderCount();
        count = count + 1;
        cartResult.setOrderCount(count);
    }

    public boolean removeFromCart(int productID) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("getCartByUserNameAndProductID");
        query.setParameter("userName", userName);
        query.setParameter("productID", productID);
        Cart cart = (Cart) query.uniqueResult();
        session.close();
        if (cart == null)
            return false;
        else return decreaseCount(cart);
    }

    private boolean decreaseCount(Cart cart) {
        int count = cart.getOrderCount();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        count = count - 1;
        if (count == 0) {
            session.delete(cart);
        } else {
            cart.setOrderCount(count);
            transaction.commit();
        }
        session.close();
        return true;
    }

    public double getTotalCost() {
        Session session = sessionFactory.openSession();
        Query query = session
                .createQuery("select c.orderCount, p.price from Cart c , Product p " +
                        "where c.productID = p.id and c.userName =:userName");
        query.setParameter("userName", userName);
        List<Object[]> resultList = query.getResultList();
        session.close();
        double costOfBill = calculateCostOfBill(resultList);
        return costOfBill;
    }

    private double calculateCostOfBill(List<Object[]> resultList) {
        double priceOfProduct;
        int countOfProduct;
        double costOfCart = 0;
        for (Object[] result : resultList) {
            countOfProduct = (int) result[0];
            priceOfProduct = (double) result[1];
            costOfCart = costOfCart + countOfProduct * priceOfProduct;
        }
        return costOfCart;
    }

    public List<Product> getProducts() {
        Session session = sessionFactory.openSession();
        Query query = session
                .createQuery("select p from Cart c , Product p " +
                        "where c.productID = p.id and c.userName =:userName");
        query.setParameter("userName", userName);
        List<Product> productList = query.list();
        session.close();
        return productList;
    }

}
