////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {
	
	private User testUser = new User("Damiano", "Bertoldo", 24);
	
	@Test (expected = IllegalArgumentException.class)
	public void testUserNameNull() {
		new User(null, "Bertoldo", 24);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testUserNameEmpty() {
		new User("", "Bertoldo", 24);
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void testUserSurnameNull() {
		new User("Damiano", null, 24);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testUserSurnameEmpty() {
		new User("Damiano", "", 24);
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void testUserUserAgeLess() {
		new User("Damiano", "Bertoldo", 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testUserUserAgeMore() {
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
