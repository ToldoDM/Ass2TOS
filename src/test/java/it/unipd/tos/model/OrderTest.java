package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import it.unipd.tos.business.User;

public class OrderTest {

    private Order order;
    private List<MenuItem> itemsOrdered;
    private User testUser;
    private LocalTime time;
    
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
    @Before
    public void config() {
        testUser = new User("Damiano", "Bertoldo", 24);
        time = LocalTime.of(18, 30);
        itemsOrdered = new ArrayList<MenuItem>();
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        itemsOrdered.add(new MenuItem("Birra", 5, ItemType.Bevande));
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        itemsOrdered.add(new MenuItem("Vaniglia", 2.5, ItemType.Budini));
        
        order = new Order(itemsOrdered, testUser, time);
    }
    
    @Test
    public void testListNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Lista non valida");
        new Order(null, testUser, time);
    }
    
    @Test
    public void testListEmpty() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Lista vuota");
        new Order(new ArrayList<MenuItem>(), testUser, time);
    }

    @Test
    public void testUserNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("User non valido");
        new Order(itemsOrdered, null, time);
    }
    
    @Test
    public void testTimeNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Orario non valido");
        new Order(itemsOrdered, testUser, null);
    }
    
    @Test
    public void testGetOrderTime() {
        assertEquals(LocalTime.of(18, 30), order.getOrderTime());
    }

    @Test
    public void testGetUser() {
        assertEquals(testUser, order.getUser());
    }

    @Test
    public void testGetItemsOrdered() {
        assertEquals(itemsOrdered, order.getItemsOrdered());
    }

    @Test
    public void testGetGiveAway() {
        assertEquals(false, order.getGiveAway());
    }

    @Test
    public void testSetGiveAway() {
        assertEquals(true, order.setGiveAway(true));        
    }

}
