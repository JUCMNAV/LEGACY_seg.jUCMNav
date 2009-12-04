package seg.jUCMNav.views.stub;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.InBinding;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.UCMmap;

/**
 * Provide the icons and the text for each item in the list of plugins.
 * 
 * @author Etienne Tremblay
 */
public class PluginLabelProvider implements ILabelProvider {

    Image icon = (JUCMNavPlugin.getImage("icons/ucm16.gif")); //$NON-NLS-1$

    /**
     *  
     */
    public PluginLabelProvider() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(Object element) {
        return icon;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    public String getText(Object element) {
        if (element instanceof PluginBinding)
            return ((PluginBinding) element).getPlugin().getName();
        else if (element instanceof OutBinding)
            return ((UCMmap) ((OutBinding) element).getBinding().getStub().getDiagram()).getName()
                    + ": " + ((OutBinding) element).getBinding().getStub().getName(); //$NON-NLS-1$
        else
            // inbinding
            return ((UCMmap) ((InBinding) element).getBinding().getStub().getDiagram()).getName()
                    + ": " + ((InBinding) element).getBinding().getStub().getName(); //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {
        // icon.dispose();
    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void addListener(ILabelProviderListener listener) {
    }

    public void removeListener(ILabelProviderListener listener) {
    }
}