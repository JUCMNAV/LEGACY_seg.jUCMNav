package seg.jUCMNav;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * The main plugin class to be used in the desktop.
 */
public class JUCMNavPlugin extends AbstractUIPlugin {
    // The shared instance.
    private static JUCMNavPlugin plugin;

    // Resource bundle.
    private ResourceBundle resourceBundle;

    private static HashMap imgDescriptorFactory;
    private static HashMap imgFactory;

    public static final String PLUGIN_ID = "seg.jUCMNav"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    public JUCMNavPlugin() {
        super();
        plugin = this;
        try {
            resourceBundle = ResourceBundle.getBundle("seg.jUCMNav.JUCMNavPluginResources"); //$NON-NLS-1$
        } catch (MissingResourceException x) {
            resourceBundle = null;
        }
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     */
    public static JUCMNavPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns the string from the plugin's resource bundle, or 'key' if not found.
     */
    public static String getResourceString(String key) {
        ResourceBundle bundle = JUCMNavPlugin.getDefault().getResourceBundle();
        try {
            return (bundle != null) ? bundle.getString(key) : key;
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * Returns the plugin's resource bundle,
     */
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static ImageDescriptor getImageDescriptor(String path) {
        if (imgDescriptorFactory == null)
            imgDescriptorFactory = new HashMap();

        if (!imgDescriptorFactory.containsKey(path)) {
            imgDescriptorFactory.put(path, ImageDescriptor.createFromFile(JUCMNavPlugin.class, path));
        }

        return (ImageDescriptor) imgDescriptorFactory.get(path);
    }

    public static Image getImage(String path) {
        if (imgFactory == null)
            imgFactory = new HashMap();

        if (!imgFactory.containsKey(path)) {
            imgFactory.put(path, ImageDescriptor.createFromFile(JUCMNavPlugin.class, path).createImage());
        }

        return ((Image) imgFactory.get(path));
    }

    public static Image getImage(ImageDescriptor descriptor) {
        if (imgFactory == null)
            imgFactory = new HashMap();
        if (!imgFactory.containsKey(descriptor)) {
            imgFactory.put(descriptor, descriptor.createImage());
        }

        return ((Image) imgFactory.get(descriptor));
    }

    public static boolean isInDebug() {
        return (GeneralPreferencePage.getAuthor() != null && "debug".equalsIgnoreCase(GeneralPreferencePage.getAuthor())); //$NON-NLS-1$
    }

}
