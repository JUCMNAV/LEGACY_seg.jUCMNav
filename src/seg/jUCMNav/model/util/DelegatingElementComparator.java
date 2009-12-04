package seg.jUCMNav.model.util;

import java.io.Serializable;
import java.util.Comparator;

import seg.jUCMNav.views.preferences.DisplayPreferences;

/**
 * Orders EObjects depending on preferences.
 * 
 * @author jkealey
 * 
 */
public class DelegatingElementComparator implements Comparator, Serializable {

    protected Comparator internal;

    public DelegatingElementComparator() {
        internal = new EObjectClassNameComparator(); // default

        if (DisplayPreferences.getInstance().isGlobalFilterEnabled()) {
            if (DisplayPreferences.SORT_DEFAULT.equals(DisplayPreferences.getInstance().getSort()))
                internal = new EObjectClassNameComparator();
            else if (DisplayPreferences.SORT_ID.equals(DisplayPreferences.getInstance().getSort()))
                internal = new URNmodelElementIDComparator(false);
            else if (DisplayPreferences.SORT_NAME.equals(DisplayPreferences.getInstance().getSort()))
                internal = new URNmodelElementNameComparator();
        }
    }

    private static final long serialVersionUID = 1L;

    public int compare(Object arg0, Object arg1) {
        return internal.compare(arg0, arg1);
    }

}