////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigourous Test :-)
     */
	@Test 
    public void testApp()
    {
		App app = new App();
		int sum = app.sum(1, 1);
        assertEquals(2, sum);
    }
}
