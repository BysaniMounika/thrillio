package thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import thrillio.constants.BookGenre;
import thrillio.managers.BookmarkManager;

public class BookTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// Test 1
		Book book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);

		boolean isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse("For Philosophy Genre - isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// Test 2
		book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);

		isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse("For SelfHelp Genre - isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// Test 3
		book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.BIOGRAPHY, 4.3);

		isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertTrue("For Genre other than SelfHelp and Philosophy - isKidFriendlyEligible() must return true",
				isKidFriendlyEligible);
	}

}
