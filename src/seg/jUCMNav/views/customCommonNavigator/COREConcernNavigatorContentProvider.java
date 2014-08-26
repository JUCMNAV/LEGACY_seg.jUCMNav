package seg.jUCMNav.views.customCommonNavigator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import seg.jUCMNav.editors.resourceManagement.COREmodelManager;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREInterface;
import ca.mcgill.sel.core.COREModel;
 
/**
 * @author pboul037
 */
public class COREConcernNavigatorContentProvider implements ITreeContentProvider, IResourceChangeListener {
  
    private static final Object[]   NO_CHILDREN = {};
    Viewer _viewer;
    private COREmodelManager coreModelManager;
 
    public COREConcernNavigatorContentProvider() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
        coreModelManager = null;
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
        	IResource currentCoreResource = (IResource) parentElement;
        	COREmodelManager coreModelManager = new COREmodelManager();
        	
        	try{
        		coreModelManager.load(currentCoreResource.getFullPath());
        	}catch(IOException e){
        		e.printStackTrace();
        		//TODO: Implement a error handling mechanism.
        	}
        	
        	COREConcern concern = coreModelManager.getModel();

        	List<COREModel> models = new LinkedList<COREModel>();
        	List<Object> concernChildren = new LinkedList<Object>();
        	List<COREFeature> selectableChildren = new LinkedList<COREFeature>();	
        	
        if( concern.getInterface() != null){
        	COREInterface coreInterface = concern.getInterface();
        	concernChildren.add(concern.getInterface());
       		
       		if ( coreInterface.getSelectable()!= null && coreInterface.getSelectable().size() > 0){
       			for ( COREFeature coreFeature : (List<COREFeature>)coreInterface.getSelectable()){
       				selectableChildren.add(coreFeature);
       			}
       			if ( selectableChildren.size() > 0)
       				concernChildren.add(selectableChildren);
       		}
        }
        	
        if( concern.getModels() != null && concern.getModels().size() > 0){
        		for( COREModel coreModel : (List<COREModel>)concern.getModels()){
       				models.add(coreModel);
       			}
       			concernChildren.add(models);
       			children = concernChildren.toArray();
       	}
        		
       	}else  if (List.class.isInstance(parentElement)) {
            List<?> elemList = (List<?>)parentElement;
            for ( Object obj : elemList){
            	if( obj instanceof COREFeature){
            		children = ((List<COREFeature>)parentElement).toArray();
            	}else if (obj instanceof COREModel){
            		children = ((List<COREModel>)parentElement).toArray();
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
    	Object parent = null;	
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
        		if(currentFile.getFullPath().getFileExtension().compareTo("core") == 0)
        			hasChildren = true;
        			return hasChildren;
        	}
        }
        
        if (COREConcern.class.isInstance(element)) {
        	
        	COREConcern currentConcern = (COREConcern)element;
        	if( currentConcern.getInterface() != null){
        		hasChildren = true;
        	}
        	if( currentConcern.getModels() != null && currentConcern.getModels().size() > 0){
        		hasChildren = true;
        	}
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
