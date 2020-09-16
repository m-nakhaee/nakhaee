package data.entity;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "getCartByUserName", query = "from Cart where userName=:userName"),
        @NamedQuery(name = "getCartByUserNameAndProductID"
                , query = "from Cart where userName=:userName and productID=:productID")
})
public class Cart {
    @Id
    @GeneratedValue
    int id;
    @Column(name = "user_name")
    String userName;

    int productID; //TODO rewrite it by List of products

    @Column(name = "order_count")
    int orderCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}


