package spring.config;

import data.dao.CartDao;
import data.dao.LogDao;
import data.dao.ProductDao;
import data.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import service.*;

import java.util.Scanner;

@Configuration
@PropertySource("spring/config/properties.properties")
public class BeanConfig {
    @Autowired
    Environment env;

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    SessionFactory session() {
        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    @Bean
    LogDao logDao() {
        return new LogDao();
    }

    @Bean(destroyMethod = "closeSession")
    UserDao userDao() {
        return new UserDao();
    }

    @Bean
    ProductDao productDao() {
        return new ProductDao();
    }

    @Bean
    @Scope("prototype")
    @Lazy
    CartDao cartDao() {
        return new CartDao();
    }

    @Bean
    @Scope("prototype")
    @Lazy
    SignUpPanel signUpPanel(UserDao userDao, Scanner scanner) {
        return new SignUpPanel(userDao, scanner);
    }

    @Bean
    @Scope("prototype")
    @Lazy
    LogInPanel logInPanel(UserDao userDao, Scanner scanner) {
        return new LogInPanel(userDao, scanner);
    }

    @Bean
    @Scope("prototype")
    @Lazy
    ManagerPanel managerPanel(UserDao userDao, Scanner scanner) {
        return new ManagerPanel(userDao, scanner);
    }

    @Bean
    @Scope("prototype")
    @Lazy
    GetOrderPanel getOrderPanel(ProductDao productDao, CartDao cartDao, Scanner scanner) {
        return new GetOrderPanel(productDao, cartDao, scanner);
    }

    @Bean
    StoreManager storeManager(Scanner scanner) {
        return new StoreManager(scanner);
    }
}
