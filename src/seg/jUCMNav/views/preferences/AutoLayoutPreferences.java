package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Encapsulates load/save of the autolayout preferences.
 * 
 * @author jkealey
 * 
 */
public class AutoLayoutPreferences {

	public final static String DEFAULTDOTPATH = "c:\\program files\\ATT\\GraphViz\\bin\\dot.exe"; //$NON-NLS-1$
	public final static String DEFAULTNONWINDOWSDOTPATH = ""; //$NON-NLS-1$
	public final static double DEFAULTHEIGHT = 11;
	public final static String DEFAULTORIENTATION = "TB"; //$NON-NLS-1$
	public final static double DEFAULTWIDTH = 8.5;
	public final static boolean DEFAULTEMPTYPOINTS = true;
	public final static String PREF_DOTPATH = "seg.jUCMNav.AutoLayout.DotPath"; //$NON-NLS-1$
	public final static String PREF_HEIGHT = "seg.jUCMNav.AutoLayout.Height"; //$NON-NLS-1$
	public final static String PREF_ORIENTATION = "seg.jUCMNav.AutoLayout.Orientation"; //$NON-NLS-1$
	public final static String PREF_WIDTH = "seg.jUCMNav.AutoLayout.Width"; //$NON-NLS-1$
	public final static String PREF_EMPTYPOINTS = "seg.jUCMNav.AutoLayout.EmptyPoints"; //$NON-NLS-1$
	public final static String URNODEPREFIX = "UrnNode"; //$NON-NLS-1$
	public final static String DIAGPREFIX = "UrnDiag"; //$NON-NLS-1$
	// must start with cluster if we want them rendered.
	public final static String CONTAINERPREFIX = "cluster_ContainerRef"; //$NON-NLS-1$

	/**
	 * 
	 * @return Preference store where the properties are stored.
	 */
	public static IPreferenceStore getPreferenceStore() {
		return JUCMNavPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * Sets the default values in the preference store.
	 */
	public static void createPreferences() {
		if (System.getProperty("os.name").startsWith("Windows")) //$NON-NLS-1$ //$NON-NLS-2$
		{
			// Default only provided to Windows. See bug #561
			getPreferenceStore().setDefault(AutoLayoutPreferences.PREF_DOTPATH, AutoLayoutPreferences.DEFAULTDOTPATH);
		} else {
			getPreferenceStore().setDefault(AutoLayoutPreferences.PREF_DOTPATH, AutoLayoutPreferences.DEFAULTNONWINDOWSDOTPATH);
		}

		getPreferenceStore().setDefault(AutoLayoutPreferences.PREF_ORIENTATION, AutoLayoutPreferences.DEFAULTORIENTATION);
		getPreferenceStore().setDefault(AutoLayoutPreferences.PREF_WIDTH, AutoLayoutPreferences.DEFAULTWIDTH);
		getPreferenceStore().setDefault(AutoLayoutPreferences.PREF_HEIGHT, AutoLayoutPreferences.DEFAULTHEIGHT);
		getPreferenceStore().setDefault(AutoLayoutPreferences.PREF_EMPTYPOINTS, AutoLayoutPreferences.DEFAULTEMPTYPOINTS);
	}

	/**
	 * 
	 * @return the path where Graphviz dot is located
	 */
	public static String getDotPath() {
		return getPreferenceStore().getString(PREF_DOTPATH);
	}

	/**
	 * 
	 * @return the height parameter to give dot
	 */
	public static String getHeight() {
		return getPreferenceStore().getString(PREF_HEIGHT);
	}

	/**
	 * 
	 * @return the orientation (TB, LR)
	 */
	public static String getOrientation() {
		return getPreferenceStore().getString(PREF_ORIENTATION);
	}

	/**
	 * 
	 * @return the width parameter to give dot
	 */
	public static String getWidth() {
		return getPreferenceStore().getString(PREF_WIDTH);
	}

	/**
	 * 
	 * @return should our empty points be manipulated during the transformation
	 */
	public static boolean getEmptyPoints() {
		return getPreferenceStore().getBoolean(PREF_EMPTYPOINTS);
	}

	/**
	 * 
	 * @param path
	 *            the path where Graphviz dot is located
	 */
	public static void setDotPath(String path) {
		getPreferenceStore().setValue(PREF_DOTPATH, path);
	}

	/**
	 * 
	 * @param height
	 *            the height parameter to give dot
	 */
	public static void setHeight(String height) {
		String s;
		// want to make sure it is convertible.
		try {
			double d = Double.parseDouble(height);
			s = Double.toString(d);
		} catch (Exception e) {
			s = "0"; //$NON-NLS-1$
		}

		getPreferenceStore().setValue(PREF_HEIGHT, s);
	}

	/**
	 * 
	 * @param str
	 *            the orientation (TB, LR)
	 */
	public static void setOrientation(String str) {
		getPreferenceStore().setValue(PREF_ORIENTATION, str);
	}

	/**
	 * 
	 * @param width
	 *            the width parameter to give dot
	 */
	public static void setWidth(String width) {
		String s;
		// want to make sure it is convertible.
		try {
			double d = Double.parseDouble(width);
			s = Double.toString(d);
		} catch (Exception e) {
			s = "0"; //$NON-NLS-1$
		}

		getPreferenceStore().setValue(PREF_WIDTH, s);
	}

	/**
	 * 
	 * @param b
	 *            should our empty points be manipulated during the transformation
	 */
	public static void setEmptyPoints(boolean b) {
		getPreferenceStore().setValue(PREF_EMPTYPOINTS, b);
	}

}
