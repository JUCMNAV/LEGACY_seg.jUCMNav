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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Component;
import urncore.Concern;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.URNdefinition;
 
/**
 * Content provider for .jucm files
 * in the Custom Common Navigator view.
 * 
 * @author pboul037
 */
public class JUCMConcernNavigatorContentProvider implements ITreeContentProvider, IResourceChangeListener {
  
    private static final Object[]   NO_CHILDREN = {};
    Viewer _viewer;
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
    @SuppressWarnings("unchecked")
    public Object[] getChildren(Object parentElement) {
        Object[] children = null;
        
        if (IResource.class.isInstance(parentElement)) {
        	// .jucm file Resource
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
        	List<Object> fmElements = new LinkedList<Object>();
        	List<Object> imElements = new LinkedList<Object>();
        	List<Object> ucmElements = new LinkedList<Object>();
        	List<List<Object>> modelsChildren = new LinkedList<List<Object>>();
        	
        	// Fills fmElements list for this and the next tree level to keep track of currentConcern.
        	if( currentConcern.getUrndefinition().getUrnspec().getGrlspec() != null){
        		GRLspec grlspec = currentConcern.getUrndefinition().getUrnspec().getGrlspec();
        		if (grlspec.getFeatureModel() != null){
        			FeatureModel fm = grlspec.getFeatureModel();
        			fmElements.add(fm);
        			
        			List<FeatureDiagram> fmDiagramChildren = new LinkedList<FeatureDiagram>();
                    List<Feature> fmFeatureChildren = new LinkedList<Feature>();
                    
               		for ( IURNDiagram diag : (List<IURNDiagram>)currentConcern.getSpecDiagrams()){
               			if( diag instanceof FeatureDiagram){
               				fmDiagramChildren.add((FeatureDiagram)diag);
               			}
               		}
               		for (IntentionalElement intElem : (List<IntentionalElement>)fm.getGrlspec().getIntElements()){
               			if (intElem instanceof Feature)
               				fmFeatureChildren.add((Feature)intElem);
            		}
               		if (fmDiagramChildren.size() > 0 )
              			fmElements.add(fmDiagramChildren);
               		if (fmFeatureChildren.size() > 0 )
          				fmElements.add(fmFeatureChildren);	
               			
               		modelsChildren.add(fmElements);
        		}
        		
        		// Fills imElements list for this and the next tree level to keep track of currentConcern.
        		if (grlspec.getImpactModel() != null){
        			ImpactModel im = grlspec.getImpactModel();
        			imElements.add(im);
        			
        			List<GRLGraph> imDiagramChildren = new LinkedList<GRLGraph>();
                    List<IntentionalElement> imIntElemChildren = new LinkedList<IntentionalElement>();
                    List<Belief> imBeliefChildren = new LinkedList<Belief>();
                    List<Actor> imActorChildren = new LinkedList<Actor>();

               		for ( IURNDiagram diag : (List<IURNDiagram>)currentConcern.getSpecDiagrams()){
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
           				imElements.add(imDiagramChildren);
           			if (imIntElemChildren.size() > 0 )
           				imElements.add(imIntElemChildren);
           			if (imActorChildren.size() > 0 )
           				imElements.add(imActorChildren);
           			if (imBeliefChildren.size() > 0 )
           				imElements.add(imBeliefChildren);
           			
           			modelsChildren.add(imElements);
        		}
        	}
        	
        	if( currentConcern.getUrndefinition() != null){
        		URNdefinition urnDef = currentConcern.getUrndefinition();
        		ucmElements.add(urnDef);
        		
           		
           		List<Component> componentChildren = new LinkedList<Component>();
                List<Responsibility> responsibilityChildren = new LinkedList<Responsibility>();
                List<UCMmap> ucmMapChildren = new LinkedList<UCMmap>();
                
            	if ( currentConcern.getSpecDiagrams() != null){
        			for ( IURNDiagram diag : (List<IURNDiagram>)currentConcern.getSpecDiagrams()){
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
       				ucmElements.add(ucmMapChildren);
     			if (responsibilityChildren.size() > 0 )
       				ucmElements.add(responsibilityChildren);
       			if (componentChildren.size() > 0 )
       				ucmElements.add(componentChildren);
       			if( ucmElements.size() > 0)	
       				modelsChildren.add(ucmElements);
        	}
        	
        	if ( modelsChildren.size() > 0)
        		children = modelsChildren.toArray();
        	
        }else if (List.class.isInstance(parentElement)) {
            List<?> elemList = (List<?>)parentElement;
            boolean modelsList = false;
            
            for ( Object obj : elemList){
            	if( (obj instanceof FeatureModel || obj instanceof ImpactModel || obj instanceof URNdefinition) && !(obj instanceof UCMmap) ){
            		if ( elemList.size() > 1)
            			modelsList = true;
            	}else if( obj instanceof List<?>){
            		children = getChildren(obj);
            	}else if( obj instanceof FeatureDiagram){
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
            if( modelsList){
            	List<Object> modelsListElements = new LinkedList<Object>();
            	for ( int ctr = 1; ctr < elemList.size(); ctr++){
            		modelsListElements.add(elemList.get(ctr));
            	}
            	children = modelsListElements.toArray();
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
    @SuppressWarnings("unchecked")
    public boolean hasChildren(Object element) {
        boolean hasChildren = false;
       
        if (IResource.class.isInstance(element)) {
        	if( IFile.class.isInstance(element)){
        		IFile currentFile = (IFile)element;
        		if(currentFile.getFullPath().getFileExtension().compareTo("jucm") == 0 ||
        				currentFile.getFullPath().getFileExtension().compareTo("core") == 0)
        			hasChildren = true;
        	}
        }else if (Concern.class.isInstance(element)) {
        	
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
            for (Object obj : elemList){
	            if( (obj instanceof FeatureModel || obj instanceof ImpactModel || obj instanceof URNdefinition) && !(obj instanceof UCMmap) ){
	        		if ( elemList.size() > 1)
	        			hasChildren = true;
	        	}else if ( elemList.size() > 0){
	            	hasChildren = true;
	        	}
	            break;
            }
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
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
            	_viewer.refresh();
            }
         });   
    }
 
}