package seg.jUCMNav.actions.hyperlinks;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WorkbenchBrowserSupport;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.model.util.MetadataHelper;
import urn.URNspec;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * Action for navigating a hyperlink
 * 
 * @author damyot
 * 
 */
public class NavigateHyperlinkAction extends SelectionAction {

    public static final String NAVIGATEHYPERLINK = "seg.jUCMNav.NavigateHyperlinkAction"; //$NON-NLS-1$

    private URNmodelElement element;
    private Metadata hyperlink;
    private URNspec urnspec;

    public NavigateHyperlinkAction(IWorkbenchPart part) {
        super(part);
        setId(NAVIGATEHYPERLINK);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/golink16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we have selected a valid URNmodelElement with hyperlink. Also uses definitions when references are selected.
     */
    protected boolean calculateEnabled() {
        List objects = getSelectedObjects();

        if (objects.size() != 1)
            return false;

        SelectionHelper sel = new SelectionHelper(objects);
        urnspec = sel.getUrnspec();
        element = HyperlinkUtils.findURNmodelElement(sel);

        if (element != null) {
            // True when there is a hyperlink metadata for this URN model element
            hyperlink = MetadataHelper.getMetaDataObj(element, HyperlinkUtils.HYPERLINK);
            return (hyperlink != null);
        } else
            return false;
    }

    /**
     * Launches a new browser window with the hyperlink of the selected URN model element. No command on the stack needed.
     * 
     */
    public void run() {
        IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
        IWebBrowser browser;
        try {
            browser = browserSupport.createBrowser(WorkbenchBrowserSupport.AS_EDITOR | WorkbenchBrowserSupport.LOCATION_BAR
                    | IWorkbenchBrowserSupport.NAVIGATION_BAR, null, null, null);
            URL findMorePluginsURL = new URL(hyperlink.getValue());
            browser.openURL(findMorePluginsURL);
        } catch (PartInitException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
