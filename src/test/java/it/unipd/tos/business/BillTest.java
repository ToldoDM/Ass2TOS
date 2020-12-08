////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Order;

public class BillTest {

    private Bill bill;
    private List<MenuItem> itemsOrdered;
    private User testUser;
    private List<Order> orders;
    private User foo;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void config() {
        bill = new Bill();
        testUser = new User("Damiano", "Bertoldo", 24);
        itemsOrdered = new ArrayList<MenuItem>();
        orders = new ArrayList<Order>();
        foo = new User("Nome", "Cognome", 16);

        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        itemsOrdered.add(new MenuItem("Birra", 5, ItemType.Bevande));
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
    }
    
    @Test
    public void testGivAwayMore10() {
        orders.add(new Order(itemsOrdered, foo, LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, foo, LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));      
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));      
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));      
        
        assertEquals(10, bill.freeGiveAwayOrder(orders));
    }
    
    @Test
    public void testGivAwayLess10() {
        orders.add(new Order(itemsOrdered, foo, LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, foo, LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));
        orders.add(new Order(itemsOrdered, new User("Nome", "Cognome", 16), LocalTime.of(18, 30)));    
        
        assertEquals(0, bill.freeGiveAwayOrder(orders));
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
    public void testGivawayListNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Lista ordini non valida");
        bill.freeGiveAwayOrder(null);
    }
    
    @Test
    public void testGivawayListEmpty() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Lista ordini vuota");
        bill.freeGiveAwayOrder(new ArrayList<Order>());
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
