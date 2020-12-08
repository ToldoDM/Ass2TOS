////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import java.time.LocalTime;
import java.util.List;

import it.unipd.tos.business.User;

public class Order {
    private List<MenuItem> itemsOrdered;
    private User user;
    private LocalTime orderTime;
    private boolean isFreeGiveaway = false;

    public Order(List<MenuItem> itemsOrdered, User user, LocalTime orderTime) {
        if (itemsOrdered == null) {
            throw new IllegalArgumentException("Lista non valida");
        }
        if (itemsOrdered.isEmpty()) {
            throw new IllegalArgumentException("Lista vuota");
        }
        if (user == null) {
            throw new IllegalArgumentException("User non valido");
        }
        if (orderTime == null) {
            throw new IllegalArgumentException("Orario non valido");
        }
        this.itemsOrdered = itemsOrdered;
        this.user = user;
        this.orderTime = orderTime;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public User getUser() {
        return user;
    }

    public List<MenuItem> getItemsOrdered() {
        return itemsOrdered;
    }
    
    public boolean getGiveAway() {
        return isFreeGiveaway;
    }
    
    public boolean setGiveAway(boolean newValue) {
        isFreeGiveaway = newValue;
        return newValue;
    }
}
