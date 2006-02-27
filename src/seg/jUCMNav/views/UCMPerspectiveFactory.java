package seg.jUCMNav.views;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Defines what our perspective should look like.
 * 
 * @author Etienne Tremblay
 */
public class UCMPerspectiveFactory implements IPerspectiveFactory {

	public final static String JUCMNAV_PERSPECTIVE_ID = "seg.jUCMNav.UCMEditor";


	/**
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		// Get the editor area.
		String editorArea = layout.getEditorArea();

		// IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT,
		// 0.80f, editorArea); //$NON-NLS-1$
		// right.addView(IPageLayout.ID_PROP_SHEET);

		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.20f, editorArea); //$NON-NLS-1$
		left.addView(IPageLayout.ID_RES_NAV);

		IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.50f, "left"); //$NON-NLS-1$ //$NON-NLS-2$
		bottomLeft.addView(IPageLayout.ID_OUTLINE);
		bottomLeft.addView("seg.jUCMNav.views.ElementView"); //$NON-NLS-1$

		IFolderLayout bottomMiddle = layout.createFolder("bottomMiddle", IPageLayout.BOTTOM, 0.75f, editorArea);
		bottomMiddle.addView("seg.jUCMNav.views.StrategiesView"); //$NON-NLS-1$

		IFolderLayout bottomRight = layout.createFolder("bottomRight", IPageLayout.RIGHT, 0.34f, "bottomMiddle");
		bottomRight.addView(IPageLayout.ID_PROP_SHEET);

	}

}