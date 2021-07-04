package thrillio;

import thrillio.constants.KidFriendlyStatus;
import thrillio.constants.UserType;
import thrillio.controllers.BookmarkController;
import thrillio.entities.Bookmark;
import thrillio.entities.User;
import thrillio.partner.Shareable;

public class View {
	public static void browse(User user, Bookmark[][] bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items ...");
		int bookmarkCount = 0;

		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				if (bookmarkCount < DataStore.TOTAL_USER_COUNT) {
					boolean isBookmarked = getBookmarkDecision(bookmark);

					if (isBookmarked) {
						bookmarkCount++;
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						System.out.println("New item Bookmarked -- " + bookmark);
					}
				}

				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					// Mark as kid-friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						String kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
						}
					}

					// Sharing!!
					if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
							&& bookmark instanceof Shareable) {
						boolean isShared = getShareDecision();
						if (isShared) {
							BookmarkController.getInstance().share(user, bookmark);
						}
					}
				}
			}
		}

	}

	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true : false;
	}

	private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
		double randomNumber = Math.random();
		return randomNumber < 0.4 ? KidFriendlyStatus.APPROVED
				: (randomNumber >= 0.4 && randomNumber < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;
	}

	// public static void bookmark(User user, Bookmark[][] bookmarks) {
	// System.out.println("\n" + user.getEmail() + " is bookmarking.");
	// for (int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; ++i) {
	// int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
	// int bookmarkOffset = (int) (Math.random() *
	// DataStore.BOOKMARK_COUNT_PER_TYPE);
	//
	// Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
	//
	// BookmarkController.getInstance().saveUserBookmark(user, bookmark);
	//
	// System.out.println(bookmark);
	// }
	// }

}
