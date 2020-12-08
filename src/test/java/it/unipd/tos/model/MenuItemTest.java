////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MenuItemTest {

    private MenuItem mi = new MenuItem("Vaniglia", 2.5, ItemType.Gelati);
    
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testMenuItemNameNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Nome non valido");
        new MenuItem(null, 2.5, ItemType.Gelati);
    }

    @Test
    public void testMenuItemNameEmpty() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Nome vuoto");
        new MenuItem("", 2.5, ItemType.Gelati);
    }

    @Test
    public void testMenuItemPriceLess() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Prezzo zero o negativo");
        new MenuItem("Vaniglia", 0, ItemType.Gelati);
    }

    @Test
    public void testMenuItemTypeNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("itemType non valido");
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
