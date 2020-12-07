////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
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
        for (MenuItem menuItem : itemsOrdered) {
            tot += menuItem.getPrice();
        }
        return tot;
    }

}
