////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;

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
        if(itemsOrdered.size() > 30) {
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

        //Sconto50 se ci sono piu di 5 gelati
        for (MenuItem menuItem : itemsOrdered) {
            if (countGelati > 5 && menuItem.equals(minMenuItem)) {
                tot += menuItem.getPrice() / 2;
            } else {
                tot += menuItem.getPrice();
            }
        }

        //sconto10 se la somma tra gelati e budini supera 50
        if (totGelBud > 50) {
            tot = tot - (tot * 0.1);
        }
        
        //se l'importo e' inferiore a 10 euro, ci aggiungo la commissione
        if(tot < 10) {
            tot += 0.5;
        }

        return tot;
    }

}
