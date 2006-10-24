package seg.jUCMNav.figures;

import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;

public class ColorManager {

	public static Color LINE;
	public static Color SELECTED;
	public static Color HOVER;
	public static Color TRAVERSAL;
	public static Color FILL;
	public static Color STUBLABEL;
	public static Color LINKREFLABEL;
	public static Color CONDITIONLABEL;
	
	
	public static final Color BLACK = new Color(null,0,0,0);
	public static final Color BLUE = new Color(null, 0,102,204);
	public static final Color LIGHTBLUE = new Color(null, 145,185,255);
	public static final Color LIGHTGRAY = new Color(null, 230,230,230);
	public static final Color GRAY = new Color(null, 200,200,200);
	public static final Color DARKGRAY = new Color(null, 150,150,150);
	public static final Color PURPLE = new Color(null, 150,0,150);
	public static final Color VERYDARKGRAY = new Color(null, 100,100,100);
	public static final Color RED = new Color(null, 255,0,0);
	public static final Color WHITE = new Color(null, 255,255,255);
	
	
	static {
		refresh();
	}
	
	
	public static void refresh() {
		
//		if (LINE!=null)
//		{
//			LINE.dispose();
//			LINE=null;
//		}
		RGB rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_LINECOLOR );
        LINE = new Color(null, rgb.red, rgb.green, rgb.blue);
        
		
//		if (SELECTED!=null)
//		{
//			SELECTED.dispose();
//			SELECTED=null;
//		}
		rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_SELECTEDCOLOR );
		SELECTED = new Color(null, rgb.red, rgb.green, rgb.blue);
		
//		if (HOVER!=null)
//		{
//			HOVER.dispose();
//			HOVER=null;
//		}
		rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_HOVERCOLOR );
		HOVER = new Color(null, rgb.red, rgb.green, rgb.blue);
		
//		if (TRAVERSAL!=null)
//		{
//			TRAVERSAL.dispose();
//			TRAVERSAL=null;
//		}
		rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_TRAVERSALCOLOR );
		TRAVERSAL = new Color(null, rgb.red, rgb.green, rgb.blue);
		
//		if (FILL!=null)
//		{
//			FILL.dispose();
//			FILL=null;
//		}
		rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_FILLCOLOR );
		FILL = new Color(null, rgb.red, rgb.green, rgb.blue);
		
		
//		if (LINKREFLABEL!=null)
//		{
//			LINKREFLABEL.dispose();
//			LINKREFLABEL=null;
//		}
		rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_LINKREFLABELCOLOR );
        LINKREFLABEL = new Color(null, rgb.red, rgb.green, rgb.blue);

//		if (STUBLABEL!=null)
//		{
//			STUBLABEL.dispose();
//			STUBLABEL=null;
//		}
		rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_STUBLABELCOLOR );
		STUBLABEL = new Color(null, rgb.red, rgb.green, rgb.blue);
        
//		if (CONDITIONLABEL!=null)
//		{
//			CONDITIONLABEL.dispose();
//			CONDITIONLABEL=null;
//		}
		rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_CONDITIONLABELCOLOR );
		CONDITIONLABEL = new Color(null, rgb.red, rgb.green, rgb.blue);
		
	}
}
