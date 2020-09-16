package service;

import data.dao.CartDao;
import data.dao.ProductDao;
import enumPackage.OperationEnum;
import data.entity.products.Product;
import exception.LogOutException;
import exception.ReturnException;
import exception.StockNumberException;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GetOrderPanel {
    private final ProductDao productDao;
    private final CartDao cartDao;
    private final Scanner scanner;

    public GetOrderPanel(ProductDao productDao, CartDao cartDao, Scanner scanner) {
        this.productDao = productDao;
        this.cartDao = cartDao;
        this.scanner = scanner;
    }

    public void getOrder(String userName) throws LogOutException {
        showProducts();
        showPurchasingCommands();
        cartDao.setUserName(userName);
        getPurchasingCommand();
    }

    private void getPurchasingCommand() throws LogOutException {
        while (true) {
            System.out.println("****** enter a command ******");
            String command = scanner.nextLine();
            try {
                if (command.equals("add")) {
                    addProduct();
                    continue;
                }
                if (command.equals("remove")) {
                    removeProduct();
                    continue;
                }
                if (command.equals("show cart")) {
                    showCart();
                    continue;
                }
                if (command.equals("show sorted cart")) {
                    showSortedCart();
                    continue;
                }
                if (command.equals("show bill")) {
                    showBill();
                    continue;
                }
                if (command.equals("continue")) {
                    finalizePurchase();
                    continue;
                }
                if (command.equals("log out")) throw new LogOutException("goodBye");
            } catch (StockNumberException | ReturnException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("invalid command! try again");
        }
    }

    private void showSortedCart() {
        List<Product> products = cartDao.getProducts();
        Comparator<Product> comparator = (p1, p2) -> p1.getPrice() < p2.getPrice() ? -1 : 1;
        products.stream().sorted(comparator).forEach(System.out::println);
    }

    private void showProducts() {
        System.out.println(productDao.getAllProducts());
    }

    private void showBill() {
        System.out.println("total cost of your bill: " + cartDao.getTotalCost());
    }

    private void showCart() {
        System.out.println(cartDao.getProducts());
    }

    private void addProduct() throws ReturnException {
        System.out.println("enter \'return' to return");
        int productId = getId();
        Product product = productDao.search(productId);
        while (product == null) {
            System.out.println("-- this product ID is not available --");
            productId = getId();
            product = productDao.search(productId);
        }
        cartDao.addToCart(product.getId());
        UsefulMethods.recordLog(OperationEnum.addToCart, cartDao.getUserName());
    }

    private void removeProduct() throws ReturnException {
        System.out.println("enter \'return' to return");
        int productId = getId();
        while (!cartDao.removeFromCart(productId)) {
            System.out.println("-- this is not in your Cart --");
            productId = getId();
        }
        UsefulMethods.recordLog(OperationEnum.removeFromCart, cartDao.getUserName());
    }

    private int getId() throws ReturnException {
        String inputString;
        do {
            System.out.println("enter the product id");
            inputString = scanner.nextLine();
            if (inputString.equals("return")) throw new ReturnException();
        } while (!UsefulMethods.isNumber(inputString));
        return Integer.parseInt(inputString);
    }

    private void showPurchasingCommands() {
        System.out.println("\"add\" --> add a product to your cart");
        System.out.println("\"remove\" --> remove a product from to your cart");
        System.out.println("\"show cart\" --> show your cart");
        System.out.println("\"show sorted cart\" --> show your sorted cart by product price");
        System.out.println("\"show bill\" --> show total cost of your cart");
        System.out.println("\"continue\" --> finalize the purchase");
        System.out.println("\"log out\" --> log out from the the store");
    }

    private void finalizePurchase() throws StockNumberException, ReturnException {
        List<Product> orderedProducts = cartDao.getProducts();
        if (orderedProducts.isEmpty()) throw new ReturnException("the cart is empty");
        checkWarehouseInventory(orderedProducts);
        updateStore(orderedProducts);
        showSortedCart();
        showBill();
        emptyCart();
        System.out.println("\nthanks for purchasing");
        UsefulMethods.recordLog(OperationEnum.purchase, cartDao.getUserName());
    }

    private void checkWarehouseInventory(List<Product> orderedProducts)
            throws StockNumberException {
        for (Product orderedProduct : orderedProducts) {
            int id = orderedProduct.getId();
            Product product = productDao.search(id);
            if (product == null) throw new StockNumberException("sorry! the product " + orderedProduct.getName()
                    + " has finished");
            if (product.getCount() < orderedProduct.getCount())
                throw new StockNumberException("** the stock number of " + orderedProduct.getName()
                        + " is less than your ordered count! **");
        }
    }

    private void emptyCart() {
        cartDao.emptyCart();
    }

    private void updateStore(List<Product> orderedProducts) {
        int id;
        int count;
        for (Product product : orderedProducts) {
            id = product.getId();
            count = product.getCount();
            productDao.updateCount(id, count);
        }
    }
}
