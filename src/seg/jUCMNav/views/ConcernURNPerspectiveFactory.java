package seg.jUCMNav.views;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IFolderLayout;

/**
 * Defines what Concern-Oriented URN perspective should look like.
 * 
 * @author Jiaying 
 */

public class ConcernURNPerspectiveFactory implements IPerspectiveFactory {

	public final static String JUCMNAV_PERSPECTIVE_ID = "seg.jUCMNav.ConcernURNEditor"; //$NON-NLS-1$
	
	/**
     * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
     */
	
	public void createInitialLayout(IPageLayout layout) {
		// Get the editor area.
		String editorArea = layout.getEditorArea();

        IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.20f, editorArea); //$NON-NLS-1$
        left.addView("seg.jUCMNav.CustomCommonNavigator");
        left.addView(IPageLayout.ID_OUTLINE);
        left.addView("seg.jUCMNav.views.StrategiesView");
        
        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f,editorArea ); //$NON-NLS-1$ //$NON-NLS-2$
        bottom.addView(IPageLayout.ID_PROP_SHEET);
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
        
        layout.addPerspectiveShortcut(UCMPerspectiveFactory.JUCMNAV_PERSPECTIVE_ID);		
	}

}
