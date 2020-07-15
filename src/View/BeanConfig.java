package View;

import data.dao.CartDao;
import data.dao.ProductDao;
import data.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import service.GetOrderPanel;
import service.LogInPanel;
import service.ManagerPanel;
import service.SignUpPanel;

import java.util.Scanner;

@Configuration
public class BeanConfig {

    @Bean
    UserDao userDao() {
        return new UserDao();
    }

    @Bean
    ProductDao productDao() {
        return new ProductDao();
    }

    @Bean
    CartDao cartDao() {
        return new CartDao();
    }

    @Bean
    @Scope("prototype")
    @Lazy
    SignUpPanel signUpPanel(UserDao userDao) {
        return new SignUpPanel(userDao);
    }

    @Bean
    @Scope("prototype")
    @Lazy
    LogInPanel logInPanel(UserDao userDao) {
        return new LogInPanel(userDao);
    }

    @Bean
    @Scope("prototype")
    @Lazy
    ManagerPanel managerPanel(UserDao userDao) {
        return new ManagerPanel(userDao);
    }

    @Bean
    @Scope("prototype")
    @Lazy
    GetOrderPanel getOrderPanel(ProductDao productDao, CartDao cartDao) {
        return new GetOrderPanel(productDao, cartDao);
    }
}
