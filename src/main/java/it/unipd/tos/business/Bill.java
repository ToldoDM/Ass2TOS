////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Order;

public class Bill implements TakeAwayBill {

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {

        // eseguo controlli integrita' variabili
        if (itemsOrdered == null) {
            throw new TakeAwayBillException("Lista ordini non valida");
        }
        if (itemsOrdered.isEmpty()) {
            throw new TakeAwayBillException("Lista ordini vuota");
        }
        if (user == null) {
            throw new TakeAwayBillException("Utente non valido");
        }
        if (itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("Max 30 elementi per ordine");
        }

        double tot = 0;
        int countGelati = 0;
        int countBudini = 0;
        double totGelBud = 0;
        MenuItem minMenuItem = new MenuItem("foo", 100000, ItemType.Gelati);

        for (MenuItem menuItem : itemsOrdered) {
            if (menuItem.getItemType().equals(ItemType.Gelati)) {
                countGelati++;
                totGelBud += menuItem.getPrice();
                if (menuItem.getPrice() < minMenuItem.getPrice()) {
                    minMenuItem = menuItem;
                }
            } else if (menuItem.getItemType().equals(ItemType.Budini)) {
                countBudini++;
                totGelBud += menuItem.getPrice();
            }
        }

        // Sconto50 se ci sono piu di 5 gelati
        for (MenuItem menuItem : itemsOrdered) {
            if (countGelati > 5 && menuItem.equals(minMenuItem)) {
                tot += menuItem.getPrice() / 2;
            } else {
                tot += menuItem.getPrice();
            }
        }

        // sconto10 se la somma tra gelati e budini supera 50
        if (totGelBud > 50) {
            tot = tot - (tot * 0.1);
        }

        // se l'importo e' inferiore a 10 euro, ci aggiungo la commissione
        if (tot < 10) {
            tot += 0.5;
        }

        return tot;
    }

    public int freeGiveAwayOrder(List<Order> orders){
        
        if (orders == null) {
            throw new IllegalArgumentException("Lista ordini non valida");
        }
        if (orders.isEmpty()) {
            throw new IllegalArgumentException("Lista ordini vuota");
        }

        ArrayList<Order> eligibleOrders = new ArrayList<Order>();

        // Mi prendo gli ordini idonei
        for (Order order : orders) {
            if (order.getOrderTime().isAfter(LocalTime.of(18, 00))
                    && order.getOrderTime().isBefore(LocalTime.of(19, 00)) && order.getUser().getUserage() <= 18) {

                boolean isInside = false;
                for (Order eo : eligibleOrders) {
                    if (eo.getUser().equals(order.getUser())) {
                        isInside = true;
                    }
                }
                if (!isInside) {
                    eligibleOrders.add(order);
                }
            }
        }

        // Se ci sono 10 o piu' ordini, ne regalo 10
        int giveAwayDone = 0;
        if (eligibleOrders.size() >= 10) {
            Random rand = new Random();

            int numberOfElements = 10;

            for (int i = 0; i < numberOfElements; i++) {
                int randomIndex = rand.nextInt(eligibleOrders.size());
                Order randomElement = eligibleOrders.get(randomIndex);
                randomElement.setGiveAway(true);
                eligibleOrders.remove(randomIndex);
                giveAwayDone++;
            }
        }     
        return giveAwayDone;
    }

}
