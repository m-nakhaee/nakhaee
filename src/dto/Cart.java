package dto;

import dto.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<Product> productsOfCart = new ArrayList<>();

    public List<Product> getProductsOfCart() {
        return productsOfCart;
    }

    public void setProductsOfCart(List<Product> productsOfCart) {
        this.productsOfCart = productsOfCart;
    }
}
