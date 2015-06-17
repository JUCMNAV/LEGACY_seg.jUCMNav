package seg.jUCMNav.views.customCommonNavigator;

import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import fm.Feature;
import fm.FeatureDiagram;
import fm.FeatureModel;
import grl.Actor;
import grl.Belief;
import grl.GRLGraph;
import grl.ImpactModel;
import grl.IntentionalElement;
import grl.IntentionalElementType;
import seg.jUCMNav.JUCMNavPlugin;
import ucm.UCMspec;
import ucm.map.UCMmap;
import urncore.Component;
import urncore.Concern;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.URNdefinition;
 
/**
 * Label provider for .core files
 * in the Custom Common Navigator view.
 * 
 * @author pboul037
 */
public class JUCMConcernNavigatorLabelProvider implements ILabelProvider {
 
    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        Image image = null;
        if( Concern.class.isInstance(element)){
        	image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Concern16.gif"));
       	}else if (IURNDiagram.class.isInstance(element)){
       		IURNDiagram currentDiagram = (IURNDiagram)element;
       		if ( currentDiagram instanceof FeatureDiagram){
       			image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/fmd16.gif"));
       		}else if ( currentDiagram instanceof GRLGraph){
       			image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/grl16.gif"));
       		}else if ( currentDiagram instanceof UCMmap){
       			image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/ucm16.gif"));
       		}
       	}else if (Feature.class.isInstance(element)){
       		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Feature16.gif"));
       	}else if (IntentionalElement.class.isInstance(element) && !Feature.class.isInstance(element)){
         	IntentionalElement intElem = (IntentionalElement)element;
         	if( intElem.getType().getValue() == 0){
           		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Softgoal16.gif"));
         	}else if ( intElem.getType().getValue() == 1){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Goal16.gif"));
         	}else if ( intElem.getType().getValue() == 2){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Task16.gif"));
         	}else if ( intElem.getType().getValue() == 3){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Resource16.gif"));
         	}else if ( intElem.getType().getValue() == 4){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Indicator16.gif"));
         	}else if ( intElem instanceof Belief){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Belief16.gif"));
         	}
       		
       	}else if (Belief.class.isInstance(element)){
       		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Belief16.gif"));
       	}else if ( Actor.class.isInstance(element)){
         	image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Actor16.gif"));
       	}else if ( Component.class.isInstance(element)){
       		Component comp = (Component)element;
       	 	if( comp.getKind().getValue() == 0){
           		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Component16.gif"));
         	}else if ( comp.getKind().getValue() == 1){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Object16.gif"));
         	}else if ( comp.getKind().getValue() == 2){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Process16.gif"));
         	}else if ( comp.getKind().getValue() == 3){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Agent16.gif"));
         	}else if ( comp.getKind().getValue() == 4){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Actor16.gif"));
         	}else if ( comp.getKind().getValue() == 5){
         		image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Component16.gif"));
         	}
       	}else if ( Responsibility.class.isInstance(element)){
         	image = JUCMNavPlugin.getImage(JUCMNavPlugin.getImageDescriptor("icons/Resp16.gif"));
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
        if (Concern.class.isInstance(element)) {
            text = ((Concern)element).getName() + " (" +((Concern)element).getId() + ")";
    	}else if (IURNDiagram.class.isInstance(element)){
       		IURNDiagram currentDiagram = (IURNDiagram)element;
       		if ( currentDiagram instanceof FeatureDiagram){
       			text = ((FeatureDiagram)element).getName() + " (" +((FeatureDiagram)element).getId() + ")";
       		}else if ( currentDiagram instanceof GRLGraph){
       			text = ((GRLGraph)element).getName() + " (" +((GRLGraph)element).getId() + ")";
       		}else if ( currentDiagram instanceof UCMmap){
       			text = ((UCMmap)element).getName() + " (" +((UCMmap)element).getId() + ")";
       		}
       	}else if (Feature.class.isInstance(element)){
       		text = ((Feature)element).getName() + " (" +((Feature)element).getId() + ")";
       	}else if (IntentionalElement.class.isInstance(element) && !Feature.class.isInstance(element)){
       		text = ((IntentionalElement)element).getName() + " (" +((IntentionalElement)element).getId() + ")";
       	}else if (Belief.class.isInstance(element)){
       		text = ((Belief)element).getName() + " (" +((Belief)element).getId() + ")";
       	}else if (Actor.class.isInstance(element)){
       		text = ((Actor)element).getName() + " (" +((Actor)element).getId() + ")";
       	}else if (Component.class.isInstance(element)){
       		text = ((Component)element).getName() + " (" +((Component)element).getId() + ")";
       	}else if (Responsibility.class.isInstance(element)){
       		text = ((Responsibility)element).getName() + " (" +((Responsibility)element).getId() + ")";
       	}else if (List.class.isInstance(element)) {
            List<?> elemList = (List<?>)element;
            for ( Object obj : elemList){
            	if (FeatureModel.class.isInstance(obj)){
               		text = "Feature Model";
               	}else if (ImpactModel.class.isInstance(obj)){
               		text = "Impact Model";
               	}else if (URNdefinition.class.isInstance(obj)){
               		text = "Scenario Model";
               	}else if( obj instanceof FeatureDiagram){
            		text = "Feature Diagrams";
            	}else if (obj instanceof GRLGraph && !(obj instanceof FeatureDiagram)){
            		text = "Impact Diagrams";
            	}else if (obj instanceof UCMmap){
            		text = "Use Case Maps";
            	}else if (obj instanceof Feature){
            		text = "Features";
            	}else if (obj instanceof Actor){
            		text = "Actors";
            	}else if (obj instanceof IntentionalElement && !(obj instanceof Feature)){
            		text = "Intentional Elements";
            	}else if (obj instanceof Belief){
            		text = "Beliefs";
            	}else if (obj instanceof Component){
            		text = "Components";
            	}else if (obj instanceof Responsibility){
            		text = "Responsibilities";
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
       System.out.println("This is added to listen to the tabbed .jucm file");
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
