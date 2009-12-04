package seg.jUCMNav.views;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Defines what our execution perspective should look like.
 * 
 * @author jkealey
 */
public class UCMPerspectiveFactoryExecution implements IPerspectiveFactory {

    public final static String JUCMNAV_PERSPECTIVE_ID = "seg.jUCMNav.UCMEditorExecution"; //$NON-NLS-1$

    /**
     * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
     */
    public void createInitialLayout(IPageLayout layout) {
        // Get the editor area.
        String editorArea = layout.getEditorArea();

        IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.20f, editorArea); //$NON-NLS-1$
        left.addView("seg.jUCMNav.views.StrategiesView"); //$NON-NLS-1$
        left.addView(IPageLayout.ID_RES_NAV);

        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.67f, "left"); //$NON-NLS-1$ //$NON-NLS-2$
        bottomLeft.addView(IPageLayout.ID_PROP_SHEET);
        bottomLeft.addView(IPageLayout.ID_OUTLINE);
        bottomLeft.addView("seg.jUCMNav.views.ElementView"); //$NON-NLS-1$

        IFolderLayout bottomMiddle = layout.createFolder("bottomMiddle", IPageLayout.BOTTOM, 0.80f, editorArea); //$NON-NLS-1$
        bottomMiddle.addView(IPageLayout.ID_PROBLEM_VIEW);

        layout.addPerspectiveShortcut(UCMPerspectiveFactory.JUCMNAV_PERSPECTIVE_ID);
    }

}