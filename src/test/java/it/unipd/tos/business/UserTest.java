////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserTest {
	
	private User testUser = new User("Damiano", "Bertoldo", 24);
	
	@Rule
    public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void testUserNameNull() {
	    expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Nome non valido");
		new User(null, "Bertoldo", 24);
	}
	
	@Test
	public void testUserNameEmpty() {
	    expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Nome vuoto");
		new User("", "Bertoldo", 24);
	}
		
	@Test
	public void testUserSurnameNull() {
	    expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Cognome non valido");
		new User("Damiano", null, 24);
	}
	
	@Test
	public void testUserSurnameEmpty() {
	    expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Cognome vuoto");
		new User("Damiano", "", 24);
	}
		
	@Test
	public void testUserUserAgeLess() {
	    expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("eta non valida");
		new User("Damiano", "Bertoldo", 0);
	}
	
	@Test
	public void testUserUserAgeMore() {
	    expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("eta non valida");
		new User("Damiano", "Bertoldo", 121);
	}

	@Test
	public void testGetName() {
		 assertEquals("Damiano", testUser.getName());
	}

	@Test
	public void testGetSurname() {
		 assertEquals("Bertoldo", testUser.getSurname());
	}

	@Test
	public void testGetUserage() {
		 assertEquals(24, testUser.getUserage());
	}

}
