package seg.jUCMNav;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Etienne Tremblay, Jason Kealey
 * 
 */
public class Messages {
    private static final String BUNDLE_NAME = "seg.jUCMNav.messages";//$NON-NLS-1$

    // private static final String BUNDLE_NAME_FALLBACK = "seg.jUCMNav.messages_en";//$NON-NLS-1$

    private static ResourceBundle RESOURCE_BUNDLE;
    static {
        RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    }

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