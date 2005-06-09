package seg.jUCMNav;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Etienne Tremblay
 *
 */
public class Messages {
	private static final String BUNDLE_NAME = "seg.jUCMNav.messages";//$NON-NLS-1$
	private static final String BUNDLE_NAME_FALLBACK = "seg.jUCMNav.messages_en";//$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME) == null ? ResourceBundle.getBundle(BUNDLE_NAME_FALLBACK) : ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}