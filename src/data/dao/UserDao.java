package data.dao;

import data.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDao {
    @Autowired
    SessionFactory sessionFactory;

    public void closeSession(){
        System.out.println("*** hope see you again! good bye! ***");
    }

    public void insertUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save("shop_user", user);
        transaction.commit();
        session.close();
    }

    public User search(String userName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from data.entity.User where userName =:userName");
        query.setParameter("userName", userName);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from data.entity.User");
        List<User> list = query.list();
        session.close();
        return list;
    }
}
