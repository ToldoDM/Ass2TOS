////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;

public class BillTest {

    private Bill bill;
    private List<MenuItem> itemsOrdered;
    private User testUser;

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

        assertEquals(58, bill.getOrderPrice(itemsOrdered, testUser), 0);
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

    @Test(expected = TakeAwayBillException.class)
    public void testGetOrderPriceListNull() throws TakeAwayBillException {
        bill.getOrderPrice(null, testUser);
    }

    @Test(expected = TakeAwayBillException.class)
    public void testGetOrderPriceListEmpty() throws TakeAwayBillException {
        bill.getOrderPrice(new ArrayList<MenuItem>(), testUser);
    }

    @Test(expected = TakeAwayBillException.class)
    public void testGetOrderPriceUserNull() throws TakeAwayBillException {
        bill.getOrderPrice(itemsOrdered, null);
    }

}
