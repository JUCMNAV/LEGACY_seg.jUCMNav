package seg.jUCMNav.views.customCommonNavigator;

import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREInterface;
import ca.mcgill.sel.core.COREModel;
 
/**
 * Label provider for .core files
 * in the Custom Common Navigator view.
 * 
 * @author pboul037
 */
public class COREConcernNavigatorLabelProvider implements ILabelProvider {
 
    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        Image image = null;
        if( COREConcern.class.isInstance(element)){
        	image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Concern16.gif"));
       	}else if (COREModel.class.isInstance(element)){
       		// TODO: Add image for COREModel
       	}else if (COREFeature.class.isInstance(element)){
       		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Feature16.gif"));
       	}
        // else ignore the element
         
        return image;
    }
 
    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        String text = ""; //$NON-NLS-1$
        if (COREConcern.class.isInstance(element)) {
            text = ((COREConcern)element).getName();
    	}else if (COREModel.class.isInstance(element)){
    		if ( element instanceof COREFeatureModel)
    			text = "Feature Model";
    		else if( element instanceof COREImpactModel)
    			text = "Impact Model";
    		else{
    			//TODO: Implement for two other types of COREModel?
    		}
       	}else if (COREFeature.class.isInstance(element)){
       		text = ((COREFeature)element).getName();
       	}else if (COREInterface.class.isInstance(element)){
       		text = "Interface";
       	}else if (List.class.isInstance(element)) {
            List<?> elemList = (List<?>)element;
            for ( Object obj : elemList){
            	if( obj instanceof COREFeature){
            		text = "Selectable Features";
            	}else if (obj instanceof COREModel){
            		text = "Models";
            	}
            	break;
            }
        }else{
        	// ignore the element
        }
         
        return text;
    }
 
    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void addListener(ILabelProviderListener listener) {
        System.out.println("LabelProvider.addListener: " + listener.getClass().getName()); //$NON-NLS-1$
        // TODO Auto-generated method stub
 
    }
 
    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {
        System.out.println("LabelProvider.dispose"); //$NON-NLS-1$
        // TODO Auto-generated method stub
 
    }
 
    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean isLabelProperty(Object element, String property) {
        System.out.println("LabelProvider.isLabelProperty: " + element.getClass().getName()); //$NON-NLS-1$
        // TODO Auto-generated method stub
        return false;
    }
 
    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void removeListener(ILabelProviderListener listener) {
        System.out.println("LabelProvider.removeListener: " + listener.getClass().getName()); //$NON-NLS-1$
        // TODO Auto-generated method stub
 
    }
 
}