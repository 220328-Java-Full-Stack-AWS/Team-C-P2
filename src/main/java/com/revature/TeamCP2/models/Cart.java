package com.revature.TeamCP2.models;

import com.revature.TeamCP2.interfaces.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart", schema = "public")
public class Cart implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "user-id")
    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_item")
    private List<CartItem> cartItems;

    @Column
    private Double total;

    public Cart() {
    }

    public Cart(Integer userId, Double total) {
        this.user = user;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItem = null;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(Integer userId) {
        this.user = user;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
