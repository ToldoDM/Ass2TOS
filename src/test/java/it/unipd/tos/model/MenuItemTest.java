////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MenuItemTest {

    private MenuItem mi = new MenuItem("Vaniglia", 2.5, ItemType.Gelati);

    @Test(expected = IllegalArgumentException.class)
    public void testMenuItemNameNull() {
        new MenuItem(null, 2.5, ItemType.Gelati);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMenuItemNameEmpty() {
        new MenuItem("", 2.5, ItemType.Gelati);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMenuItemPriceLess() {
        new MenuItem("Vaniglia", 0, ItemType.Gelati);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMenuItemTypeNull() {
        new MenuItem("Vaniglia", 2.5, null);
    }

    @Test
    public void testGetPrice() {
        assertEquals(2.5, mi.getPrice(), 0);
    }

    @Test
    public void testGetName() {
        assertEquals("Vaniglia", mi.getName());
    }

    @Test
    public void testGetItemType() {
        assertEquals(ItemType.Gelati, mi.getItemType());
    }

}
