package seg.jUCMNav.figures;

import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.preferences.ColorManagementPreferencePage;

/**
 * This is a helper class that manages instances of the {@link Color} class. We're only creating one instance of each color, to prevent using up useless
 * handles.
 * 
 * @author jkealey, gunterm, pchen
 * 
 */
public class ColorManager {

    public static final Color BLACK = new Color(null, 0, 0, 0);
    public static final Color BLUE = new Color(null, 0, 102, 204);
    public static final Color LIGHTBLUE = new Color(null, 145, 185, 255);
    public static final Color LIGHTGRAY = new Color(null, 230, 230, 230);
    public static final Color LIGHTYELLOW = new Color(null, 255, 255, 200);
    public static final Color GRAY = new Color(null, 200, 200, 200);
    public static final Color DARKGRAY = new Color(null, 96, 96, 96);
    public static final Color PURPLE = new Color(null, 150, 0, 150);
    public static final Color VERYDARKGRAY = new Color(null, 48, 48, 48);
    public static final Color RED = new Color(null, 255, 0, 0);
    public static final Color WHITE = new Color(null, 255, 255, 255);
    public static final Color YELLOW = new Color(null, 255, 255, 0);
    public static final Color AQUA = new Color(null, 0, 100, 100);

    public static Color LINE;
    public static Color SELECTED;
    public static Color HOVER;
    public static Color TRAVERSAL;
    public static Color POINTCUTBORDER;
    public static Color FILL = WHITE;
    public static Color FILL_COMMENTS = LIGHTYELLOW;
    public static Color STUBLABEL;
    public static Color LINKREFLABEL;
    public static Color KPIMODELLINKREFLABEL;
    public static Color CONDITIONLABEL;
    public static Color FAILUREPOINT = RED;

    static {
        refresh();
    }

    public static void refresh() {

        // if (LINE!=null)
        // {
        // LINE.dispose();
        // LINE=null;
        // }
        RGB rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_LINECOLOR);
        LINE = new Color(null, rgb.red, rgb.green, rgb.blue);

        // if (SELECTED!=null)
        // {
        // SELECTED.dispose();
        // SELECTED=null;
        // }
        rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_SELECTEDCOLOR);
        SELECTED = new Color(null, rgb.red, rgb.green, rgb.blue);

        // if (HOVER!=null)
        // {
        // HOVER.dispose();
        // HOVER=null;
        // }
        rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_HOVERCOLOR);
        HOVER = new Color(null, rgb.red, rgb.green, rgb.blue);

        // if (TRAVERSAL!=null)
        // {
        // TRAVERSAL.dispose();
        // TRAVERSAL=null;
        // }
        rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_TRAVERSALCOLOR);
        TRAVERSAL = new Color(null, rgb.red, rgb.green, rgb.blue);

        rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_POINTCUTBORDERCOLOR);
        POINTCUTBORDER = new Color(null, rgb.red, rgb.green, rgb.blue);

        // if (FILL!=null)
        // {
        // FILL.dispose();
        // FILL=null;
        // }
        rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_FILLCOLOR);
        FILL = new Color(null, rgb.red, rgb.green, rgb.blue);

        // if (LINKREFLABEL!=null)
        // {
        // LINKREFLABEL.dispose();
        // LINKREFLABEL=null;
        // }
        rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_LINKREFLABELCOLOR);
        LINKREFLABEL = new Color(null, rgb.red, rgb.green, rgb.blue);

        // if (KPIMODELLINKREFLABEL!=null)
        // {
        // KPIMODELLINKREFLABEL.dispose();
        // KPIMODELLINKREFLABEL=null;
        // }
        rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_KPIMODELLINKREFLABELCOLOR);
        KPIMODELLINKREFLABEL = new Color(null, rgb.red, rgb.green, rgb.blue);

        // if (STUBLABEL!=null)
        // {
        // STUBLABEL.dispose();
        // STUBLABEL=null;
        // }
        rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_STUBLABELCOLOR);
        STUBLABEL = new Color(null, rgb.red, rgb.green, rgb.blue);

        // if (CONDITIONLABEL!=null)
        // {
        // CONDITIONLABEL.dispose();
        // CONDITIONLABEL=null;
        // }
        rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(), ColorManagementPreferencePage.PREF_CONDITIONLABELCOLOR);
        CONDITIONLABEL = new Color(null, rgb.red, rgb.green, rgb.blue);
    }
}
