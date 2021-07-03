package thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import thrillio.constants.MovieGenre;
import thrillio.managers.BookmarkManager;

public class MovieTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// Test 1
		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);

		boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse("For Horror Genre - isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// Test 2
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5);

		isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse("For Thrillers Genre - isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// Test 3: movie is not horror and thriller - return true
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.CLASSICS,
				8.5);

		isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertTrue("For Genre other than Horror and Thrillers - isKidFriendlyEligible() must return true",
				isKidFriendlyEligible);
	}

}
