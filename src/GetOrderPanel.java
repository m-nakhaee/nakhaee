import dao.*;
import dto.products.*;
import exception.ReturnException;

import java.util.List;

public class GetOrderPanel {
    static BookDao bookDao = BookDao.getInstance();
    static MagazineDao magazineDao = MagazineDao.getInstance();
    static FormalShoesDao formalShoesDao = FormalShoesDao.getInstance();
    static SportShoesDao sportShoesDao = SportShoesDao.getInstance();
    static TVDao tvDao = TVDao.getInstance();
    static RadioDao radioDao = RadioDao.getInstance();
    static CartDao cartDao;

    public static void getOrder(String userName) {
        showProducts();
        showPurchasingCommands();
        cartDao = new CartDao(userName);
        getPurchasingCommand();
    }

    private static void getPurchasingCommand() {
        while (true) {
            System.out.println("************");
            String command = StoreManager.scanner.nextLine();
            try {
                if (command.equals("add")) addProduct();
                if (command.equals("remove")) removeProduct();
            } catch (Exception e) {
            }
            if (command.equals("show cart")) showCart();
            if (command.equals("show bill")) showBill();
            if (command.equals("continue"))
                if (finalizePurchase()) return;
            if (command.equals("exit")) return;
        }
    }


    private static void showProducts() {
        System.out.println("---------------------------------");
        System.out.println("1-READABLE STUFFS:");
        System.out.println("***1-1 BOOK ***");
        System.out.println(bookDao.getEntireTable("book"));
        System.out.println("***1-2 MAGAZINE ***");
        System.out.println(magazineDao.getEntireTable("magazine"));
        System.out.println("---------------------------------");
        System.out.println("2-SHOES:");
        System.out.println("***2-1 SPORT SHOES ***");
        System.out.println(sportShoesDao.getEntireTable("sport_shoes"));
        System.out.println("***2-2 FORMAL SHOES ***");
        System.out.println(formalShoesDao.getEntireTable("formal_shoes"));
        System.out.println("---------------------------------");
        System.out.println("3-ELECTRICAL PRODUCTS:");
        System.out.println("***3-1 RADIO ***");
        System.out.println(radioDao.getEntireTable("radio"));
        System.out.println("***3-2 TV ***");
        System.out.println(tvDao.getEntireTable("tv"));
        System.out.println("---------------------------------");
    }

    private static void showBill() {
        System.out.println("total cost of your bill: " + cartDao.getTotalCost());
    }

    private static void showCart() {
        System.out.println(cartDao.getEntireTable("cart"));
    }

    private static void addProduct() throws ReturnException {
        Product product = getProductToAdd();
        while (product == null) {
            System.out.println("-- this product ID is not available --");
            product = getProductToAdd();
        }
        cartDao.addToCart(product.getId());
    }

    private static void removeProduct() throws ReturnException {
        int productId = getId();
        while (!cartDao.removeFromCart(productId)) {
            System.out.println("-- this is not in your Cart --");
            productId = getId();
        }
    }

    private static Product getProductToAdd() throws ReturnException {
        int productId = getId();
        System.out.println(productId);
        if (productId >= 1000 && productId <= 1999)
            return getElectricalProduct(productId);
        if (productId >= 2000 && productId <= 2999)
            return getShoesProduct(productId);
        if (productId >= 3000 && productId <= 3999)
            return getReadableProduct(productId);
        return null;
    }

    private static int getId() throws ReturnException {
        String inputString;
        do {
            System.out.println("enter the product id");
            System.out.println("enter \'return' to return");
            inputString = StoreManager.scanner.nextLine();
            if (inputString.equals("return")) throw new ReturnException();
        } while (!StoreManager.isNumber(inputString));
        return Integer.parseInt(inputString);
    }

    private static Product getReadableProduct(int productId) {
        if (productId <= 3500)
            return (Book) bookDao.search(productId, "book");
        else return (Magazine) magazineDao.search(productId, "magazine");
    }

    private static Product getShoesProduct(int productId) {
        if (productId <= 2500)
            return (FormalShoes) formalShoesDao.search(productId, "formal_shoes");
        else return (SportShoes) sportShoesDao.search(productId, "sport_shoes");
    }

    private static Product getElectricalProduct(int productId) {
        if (productId <= 1500)
            return (TV) tvDao.search(productId, "tv");
        else return (Radio) radioDao.search(productId, "radio");
    }

    private static void showPurchasingCommands() {
        System.out.println("\"add\" --> add a product to your cart");
        System.out.println("\"remove\" --> remove a product from to your cart");
        System.out.println("\"show cart\" --> show your cart");
        System.out.println("\"show bill\" --> show total cost of your cart");
        System.out.println("\"continue\" --> finalize the purchase");
        System.out.println("\"exit\" --> exit the store");
    }

    private static boolean finalizePurchase() {
        boolean returnValue = true;
        List<Object> productList = cartDao.getEntireTable("cart");
        for (Object product : productList) {
            try {
                checkWarehouseInventory((Product) product);
            } catch (Exception e) {
                returnValue = false;
                System.out.println(e.getMessage());
            }
        }
        return returnValue;
    }

    private static void checkWarehouseInventory(Product orderedProduct)
            throws Exception {
        checkBookWarehouse(orderedProduct);
        checkMagazineWarehouse(orderedProduct);
        checkRadioWarehouse(orderedProduct);
        checkTVWarehouse(orderedProduct);
        checkSportShoesWarehouse(orderedProduct);
        checkFormalShoesWarehouse(orderedProduct);
    }

    private static void checkFormalShoesWarehouse(Product orderedProduct)
            throws Exception {
        int id = orderedProduct.getId();
        if (orderedProduct instanceof FormalShoes) {
            FormalShoes formalShoes = (FormalShoes) formalShoesDao.search(id, "formal_shoes");
            checkShelfInventory(orderedProduct, formalShoes);
        }
    }

    private static void checkSportShoesWarehouse(Product orderedProduct)
            throws Exception {
        int id = orderedProduct.getId();
        if (orderedProduct instanceof SportShoes) {
            SportShoes sportShoes = (SportShoes) sportShoesDao.search(id, "sport_shoes");
            checkShelfInventory(orderedProduct, sportShoes);
        }
    }

    private static void checkTVWarehouse(Product orderedProduct) throws Exception {
        int id = orderedProduct.getId();
        if (orderedProduct instanceof TV) {
            TV tv = (TV) tvDao.search(id, "tv");
            checkShelfInventory(orderedProduct, tv);
        }
    }

    private static void checkRadioWarehouse(Product orderedProduct) throws Exception {
        int id = orderedProduct.getId();
        if (orderedProduct instanceof Radio) {
            Radio radio = (Radio) radioDao.search(id, "radio");
            checkShelfInventory(orderedProduct, radio);
        }
    }

    private static void checkMagazineWarehouse(Product orderedProduct) throws Exception {
        int id = orderedProduct.getId();
        if (orderedProduct instanceof Magazine) {
            Magazine magazine = (Magazine) magazineDao.search(id, "magazine");
            checkShelfInventory(orderedProduct, magazine);
        }
    }

    private static void checkBookWarehouse(Product orderedProduct) throws Exception {
        int id = orderedProduct.getId();
        if (orderedProduct instanceof Book) {
            Book book = (Book) bookDao.search(id, "book");
            checkShelfInventory(orderedProduct, book);
        }
    }

    private static void checkShelfInventory(Product orderedProduct, Product availableProduct)
            throws Exception {
        if (availableProduct == null) throw new Exception(orderedProduct.getName() + " has finished");
        if (availableProduct.getCount() < orderedProduct.getCount())
            throw new Exception("** the stock number of " + orderedProduct.getName()
                    + " is less than your ordered count! **");
    }

}
