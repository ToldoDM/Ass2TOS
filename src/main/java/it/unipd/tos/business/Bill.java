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

        double tot = 0;
        int countGelati = 0;
        MenuItem minMenuItem = new MenuItem("foo", 100000, ItemType.Gelati);

        for (MenuItem menuItem : itemsOrdered) {
            if (menuItem.getItemType().equals(ItemType.Gelati)) {
                countGelati++;
                if (menuItem.getPrice() < minMenuItem.getPrice()) {
                    minMenuItem = menuItem;
                }
            }
        }

        for (MenuItem menuItem : itemsOrdered) {
            if (countGelati > 5 && menuItem.equals(minMenuItem)) {
                tot += menuItem.getPrice() / 2;
            } else {
                tot += menuItem.getPrice();
            }
        }
        return tot;
    }

}
