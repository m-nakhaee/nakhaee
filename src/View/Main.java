package View;

import exception.ExitStoreException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.StoreManager;
import spring.config.BeanConfig;

public class Main {
    public static ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

    public static void main(String[] args) {
        StoreManager storeManager = context.getBean("storeManager", StoreManager.class);
        try {
            storeManager.getStart();
        } catch (ExitStoreException e) {
            ((AnnotationConfigApplicationContext)context).close();
        }
    }
}

