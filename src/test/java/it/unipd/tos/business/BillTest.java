////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;

public class BillTest {

    private Bill bill;
    private List<MenuItem> itemsOrdered;
    private User testUser;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void config() {
        bill = new Bill();
        testUser = new User("Damiano", "Bertoldo", 24);
        itemsOrdered = new ArrayList<MenuItem>();

        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        itemsOrdered.add(new MenuItem("Birra", 5, ItemType.Bevande));
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
    }

    @Test
    public void testOrderLess10euro() throws TakeAwayBillException {
        itemsOrdered.clear();
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        assertEquals(8, bill.getOrderPrice(itemsOrdered, testUser), 0);
    } 
    
    @Test
    public void testGetOrderPrice() throws TakeAwayBillException {
        assertEquals(12.5, bill.getOrderPrice(itemsOrdered, testUser), 0);
    }

    @Test
    public void testOrderDiscount10() throws TakeAwayBillException {
        itemsOrdered.clear();
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 20, ItemType.Gelati));

        assertEquals(54, bill.getOrderPrice(itemsOrdered, testUser), 0);
    }

    @Test
    public void testOrderDiscount10and50() throws TakeAwayBillException {
        itemsOrdered.clear();
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 20, ItemType.Gelati));

        assertEquals(58.5, bill.getOrderPrice(itemsOrdered, testUser), 0);
    }

    @Test
    public void testOrderDiscount50() throws TakeAwayBillException {
        itemsOrdered.clear();
        itemsOrdered.add(new MenuItem("Vaniglia", 5, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 5, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 5, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 10, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Gelati));
        itemsOrdered.add(new MenuItem("Vaniglia", 20, ItemType.Gelati));

        assertEquals(46.25, bill.getOrderPrice(itemsOrdered, testUser), 0);
    }

    @Test
    public void testGetOrderPriceListNull() throws TakeAwayBillException {
        expectedEx.expect(TakeAwayBillException.class);
        expectedEx.expectMessage("Lista ordini non valida");
        bill.getOrderPrice(null, testUser);
    }

    @Test
    public void testGetOrderPriceListEmpty() throws TakeAwayBillException {
        expectedEx.expect(TakeAwayBillException.class);
        expectedEx.expectMessage("Lista ordini vuota");
        bill.getOrderPrice(new ArrayList<MenuItem>(), testUser);
    }

    @Test
    public void testGetOrderPriceUserNull() throws TakeAwayBillException {
        expectedEx.expect(TakeAwayBillException.class);
        expectedEx.expectMessage("Utente non valido");
        bill.getOrderPrice(itemsOrdered, null);
    }

    @Test
    public void testGetOrderListAbove30() throws TakeAwayBillException {
        expectedEx.expect(TakeAwayBillException.class);
        expectedEx.expectMessage("Max 30 elementi per ordine");
        
        itemsOrdered.clear();
        for (int i = 0; i < 31; i++) {
            itemsOrdered.add(new MenuItem("Vaniglia", 5, ItemType.Gelati));
        }
        bill.getOrderPrice(itemsOrdered, testUser);
    }

}
