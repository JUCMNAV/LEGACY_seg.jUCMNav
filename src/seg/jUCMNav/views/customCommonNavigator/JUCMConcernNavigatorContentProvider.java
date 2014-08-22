package seg.jUCMNav.views.customCommonNavigator;

import fm.Feature;
import fm.FeatureDiagram;
import fm.FeatureModel;
import grl.Actor;
import grl.Belief;
import grl.GRLGraph;
import grl.GRLNode;
import grl.GRLspec;
import grl.ImpactModel;
import grl.IntentionalElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import ucm.UCMspec;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Component;
import urncore.Concern;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.URNdefinition;
import urncore.URNmodelElement;
 
/**
 * @author pboul037
 */
public class JUCMConcernNavigatorContentProvider implements ITreeContentProvider, IResourceChangeListener {
 
    private static final Object[]   NO_CHILDREN = {};
    Viewer _viewer;
    private int _count = 1;
    private UrnModelManager jucmModelManager;
    private HashMap<Object, Concern> concerns;
 
    public JUCMConcernNavigatorContentProvider() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
        jucmModelManager = null;
        concerns = new HashMap<Object, Concern>();
    }
 
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object parentElement) {
        System.out.println("ContentProvider.getChildren: " + parentElement.getClass().getName()); //$NON-NLS-1$
        Object[] children = null;
        
        
        // .jucm file Resource
        if (IResource.class.isInstance(parentElement)) {
        	
        	IResource currentJucmResource = (IResource)parentElement;
        	jucmModelManager = new UrnModelManager();
        	try{
        		jucmModelManager.load(currentJucmResource.getFullPath());
        	}catch ( IOException e){
        		e.printStackTrace();
        		//TODO: Implement an error handling mechanism here.
        	}
        	URNspec urnspec = jucmModelManager.getModel();
        	
        	children = urnspec.getUrndef().getConcerns().toArray();
        	
        }else if (Concern.class.isInstance(parentElement)) {
        	
        	Concern currentConcern = (Concern)parentElement;
        	List<Object> models = new LinkedList<Object>();
        	
        	if( currentConcern.getUrndefinition().getUrnspec().getGrlspec() != null){
        		GRLspec grlspec = currentConcern.getUrndefinition().getUrnspec().getGrlspec();
        		if (grlspec.getFeatureModel() != null){
        			FeatureModel fm = grlspec.getFeatureModel();
        			models.add(fm);
        			concerns.put(fm, currentConcern);
        		}
        		if (grlspec.getImpactModel() != null){
        			ImpactModel im = grlspec.getImpactModel();
        			models.add(im);
        			concerns.put(im, currentConcern);
        		}
        	}
        	
        	if( currentConcern.getUrndefinition() != null){
        		URNdefinition urnDef = currentConcern.getUrndefinition();
        		models.add(urnDef);
        		concerns.put(urnDef, currentConcern);
        	}
        	if ( models.size() > 0)
        		children = models.toArray();
        	
        }else if (FeatureModel.class.isInstance(parentElement)){
           
        	List<FeatureDiagram> fmDiagramChildren = new LinkedList<FeatureDiagram>();
            List<Feature> fmFeatureChildren = new LinkedList<Feature>();
            List<Object> fmChildren = new LinkedList<Object>();
            
        	FeatureModel fm = (FeatureModel)parentElement;
       		Concern concern = concerns.get(fm);
       		for ( IURNDiagram diag : (List<IURNDiagram>)concern.getSpecDiagrams()){
       			if( diag instanceof FeatureDiagram){
       				fmDiagramChildren.add((FeatureDiagram)diag);
       			}
       		}
       		for (IntentionalElement intElem : (List<IntentionalElement>)fm.getGrlspec().getIntElements()){
       			if (intElem instanceof Feature)
       				fmFeatureChildren.add((Feature)intElem);
    		}
       		if (fmDiagramChildren.size() > 0 )
      			fmChildren.add(fmDiagramChildren);
       		if (fmFeatureChildren.size() > 0 )
  				fmChildren.add(fmFeatureChildren);
       		if( fmChildren.size() > 0)	
       			children = fmChildren.toArray();
 	
       	}else if (ImpactModel.class.isInstance(parentElement)){
       		
        	List<GRLGraph> imDiagramChildren = new LinkedList<GRLGraph>();
            List<IntentionalElement> imIntElemChildren = new LinkedList<IntentionalElement>();
            List<Belief> imBeliefChildren = new LinkedList<Belief>();
            List<Actor> imActorChildren = new LinkedList<Actor>();
            List<Object> imChildren = new LinkedList<Object>();
       		
       		ImpactModel im = (ImpactModel)parentElement;
       		Concern concern = concerns.get(im);
       		for ( IURNDiagram diag : (List<IURNDiagram>)concern.getSpecDiagrams()){
       			if( diag instanceof GRLGraph && !(diag instanceof FeatureDiagram)){
       				GRLGraph grlGraph = (GRLGraph)diag;
       				for (GRLNode node : (List<GRLNode>)grlGraph.getNodes()){
       					if ( node instanceof Belief){
       						imBeliefChildren.add((Belief)node);
       					}
       				}
       				imDiagramChildren.add((GRLGraph)diag);
       			}
       		}
    		for (IntentionalElement intElem : (List<IntentionalElement>)im.getGrlspec().getIntElements()){
       			if (!(intElem instanceof Feature))
       				imIntElemChildren.add((IntentionalElement)intElem);
    		}
    		
    		for (Actor actor : (List<Actor>)im.getGrlspec().getActors()){
       				imActorChildren.add(actor);
    		}
    		
   			if (imDiagramChildren.size() > 0 )
   				imChildren.add(imDiagramChildren);
   			if (imIntElemChildren.size() > 0 )
   				imChildren.add(imIntElemChildren);
   			if (imActorChildren.size() > 0 )
   				imChildren.add(imActorChildren);
   			if (imBeliefChildren.size() > 0 )
   				imChildren.add(imBeliefChildren);
   			if( imChildren.size() > 0)	
   				children = imChildren.toArray();
       	
       	}else if (URNdefinition.class.isInstance(parentElement)){
       		URNdefinition urnDef = (URNdefinition)parentElement;
       		Concern concern = concerns.get(urnDef);
       		
       		List<Component> componentChildren = new LinkedList<Component>();
            List<Responsibility> responsibilityChildren = new LinkedList<Responsibility>();
            List<UCMmap> ucmMapChildren = new LinkedList<UCMmap>();
            List<Object> ucmChildren = new LinkedList<Object>();
            
        	if ( urnDef.getSpecDiagrams() != null){
    			for ( IURNDiagram diag : (List<IURNDiagram>)urnDef.getSpecDiagrams()){
    				if( diag instanceof UCMmap){
    					ucmMapChildren.add((UCMmap)diag);
    				}
    			}
    		}
            
    		if ( urnDef.getResponsibilities() != null && urnDef.getResponsibilities().size() > 0){
    			for ( Responsibility resp : (List<Responsibility>)urnDef.getResponsibilities()){
    				responsibilityChildren.add(resp);
    			}
    		}
    		if ( urnDef.getComponents() != null && urnDef.getComponents().size() > 0){
    			for ( Component comp : (List<Component>)urnDef.getComponents()){
    				componentChildren.add(comp);
    			}
    		}
    		if (ucmMapChildren.size() > 0 )
   				ucmChildren.add(ucmMapChildren);
 			if (responsibilityChildren.size() > 0 )
   				ucmChildren.add(responsibilityChildren);
   			if (componentChildren.size() > 0 )
   				ucmChildren.add(componentChildren);
   			if( ucmChildren.size() > 0)	
   				children = ucmChildren.toArray();
   			
       	}else  if (List.class.isInstance(parentElement)) {
            List<?> elemList = (List<?>)parentElement;
            for ( Object obj : elemList){
            	if( obj instanceof FeatureDiagram){
            		children = ((List<FeatureDiagram>)parentElement).toArray();
            	}else if (obj instanceof GRLGraph && !(obj instanceof FeatureDiagram)){
            		children = ((List<GRLGraph>)parentElement).toArray();
            	}else if (obj instanceof UCMmap){
            		children = ((List<UCMmap>)parentElement).toArray();
            	}else if (obj instanceof Feature){
            		children = ((List<Feature>)parentElement).toArray();
            	}else if (obj instanceof Actor){
            		children = ((List<Actor>)parentElement).toArray();
            	}else if (obj instanceof IntentionalElement && !(obj instanceof Feature)){
            		children = ((List<IntentionalElement>)parentElement).toArray();
            	}else if (obj instanceof Belief){
            		children = ((List<Belief>)parentElement).toArray();
            	}else if (obj instanceof Component){
            		children = ((List<Component>)parentElement).toArray();
            	}else if (obj instanceof Responsibility){
            		children = ((List<Responsibility>)parentElement).toArray();
            	}
            	break;
            }
        }else {
            children = NO_CHILDREN;
        }
 
        return children;
    }
 
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    @Override
    public Object getParent(Object element) {
        System.out.println("ContentProvider.getParent: " + element.getClass().getName()); //$NON-NLS-1$
        Object parent = null;
 
        if (List.class.isInstance(element)) {
            List<?> elemList = (List<?>)element;
            for ( Object obj : elemList){
            	if( obj instanceof FeatureDiagram){
            		parent = ((IURNDiagram)element).getUrndefinition().getUrnspec().getGrlspec().getFeatureModel();
            	}else if (obj instanceof GRLGraph && !(obj instanceof FeatureDiagram)){
            		parent = ((IURNDiagram)element).getUrndefinition().getUrnspec().getGrlspec().getImpactModel();
            	}else if (obj instanceof UCMmap){
            		// TODO: Implement this.
            	}else if (obj instanceof Feature){
            		parent = ((IntentionalElement)element).getGrlspec().getFeatureModel();
            	}else if (obj instanceof Actor){
            		parent = ((Actor)element).getGrlspec().getImpactModel();
            	}else if (obj instanceof IntentionalElement && !(obj instanceof Feature)){
            		parent = ((IntentionalElement)element).getGrlspec().getImpactModel();
            	}else if (obj instanceof Belief){
            		parent = ((Belief)element).getDiagram().getUrndefinition().getUrnspec().getGrlspec().getImpactModel();
            	}else if (obj instanceof Component){
            		// TODO: Implement this.
            	}else if (obj instanceof Responsibility){
            		// TODO: Implement this.
            	}
            	break;
            }
        } // else parent = null if anything else
 
        return parent;
    }
 
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(Object element) {
        System.out.println("ContentProvider.hasChildren: " + element.getClass().getName()); //$NON-NLS-1$
        boolean hasChildren = false;
       
        if (Concern.class.isInstance(element)) {
        	
        	Concern currentConcern = (Concern)element;
        	if( currentConcern.getUrndefinition().getUrnspec().getGrlspec() != null){
        		GRLspec grlspec = currentConcern.getUrndefinition().getUrnspec().getGrlspec();
        		hasChildren = grlspec.getImpactModel() != null || grlspec.getFeatureModel() != null;
        	}
        	if( currentConcern.getUrndefinition() != null){
        		URNdefinition urnDef = currentConcern.getUrndefinition();
        		if ( urnDef.getResponsibilities() != null && urnDef.getResponsibilities().size() > 0)
        			hasChildren = true;
        		if ( urnDef.getComponents() != null && urnDef.getComponents().size() > 0)
        			hasChildren = true;
        	}
        }else if (FeatureModel.class.isInstance(element)){
       		FeatureModel fm = (FeatureModel)element;
       		Concern concern = concerns.get(fm);
       		for ( IURNDiagram diag : (List<IURNDiagram>)concern.getSpecDiagrams()){
       			if( diag instanceof FeatureDiagram){
       				hasChildren = true;
       				break;
       			}
       		}
       		for (IntentionalElement intElem : (List<IntentionalElement>)fm.getGrlspec().getIntElements()){
       			if (intElem instanceof Feature){
       				hasChildren = true;
       				break;
       			}
       		}
       	}else if (ImpactModel.class.isInstance(element)){
       		ImpactModel im = (ImpactModel)element;
       		Concern concern = concerns.get(im);
       		for ( IURNDiagram diag : (List<IURNDiagram>)concern.getSpecDiagrams()){
       			if( diag instanceof GRLGraph && !(diag instanceof FeatureDiagram)){
    				GRLGraph grlGraph = (GRLGraph)diag;
       				for (GRLNode node : (List<GRLNode>)grlGraph.getNodes()){
       					if ( node instanceof Belief){
       						hasChildren = true;
       						break;
       					}
       				}
       				hasChildren = true;
       				break;
       			}	
       		}
    		for (IntentionalElement intElem : (List<IntentionalElement>)im.getGrlspec().getIntElements()){
       			if (!(intElem instanceof Feature)){
       				hasChildren = true;
       				break;
       			}
       		}
       	}else if (URNdefinition.class.isInstance(element)){
       		URNdefinition urnDef = (URNdefinition)element;
    		
       		if ( urnDef.getSpecDiagrams() != null){
    			for ( IURNDiagram diag : (List<IURNDiagram>)urnDef.getSpecDiagrams()){
    				if( diag instanceof UCMmap){
    					hasChildren = true;
    					break;
    				}
    			}
    		}
       		if ( urnDef.getResponsibilities() != null && urnDef.getResponsibilities().size() > 0)
    			hasChildren = true;
    		if ( urnDef.getComponents() != null && urnDef.getComponents().size() > 0)
    			hasChildren = true;
    		
       	}else if (List.class.isInstance(element)) {
            List<?> elemList = (List<?>)element;
            if ( elemList.size() > 0)
            	hasChildren = true;
        }
 
        return hasChildren;
    }
 
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(Object inputElement) {
        // This is the same as getChildren() so we will call that instead
        System.out.println("ContentProvider.getElements: " + inputElement.getClass().getName()); //$NON-NLS-1$
        return getChildren(inputElement);
    }
 
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
    }
 
    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        _viewer = viewer;
    }
 
    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        _viewer.refresh();
    }
 
}