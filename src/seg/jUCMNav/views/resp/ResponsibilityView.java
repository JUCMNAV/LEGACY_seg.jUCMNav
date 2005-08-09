package seg.jUCMNav.views.resp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.IPageChangeListener;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UcmEditor;
import ucm.map.Map;
import ucm.map.PathNode;
import ucm.map.RespRef;

/**
 * The actual responsibility view. 
 * 
 * @author jkealey
 *
 */
public class ResponsibilityView extends ViewPart implements IPartListener2, ISelectionChangedListener, IPageChangeListener, ISelectionProvider {
    private RespListViewer viewer;
    private Action action1;
    private Action action2;
    private Action doubleClickAction;
    private Map input;
    private UCMNavMultiPageEditor editor;

    class RespContentProvider implements IStructuredContentProvider {
        private List input;

        public void inputChanged(Viewer v, Object oldInput, Object newInput) {
            input = (List) newInput;
        }

        public void dispose() {
        }

        public Object[] getElements(Object parent) {
            ArrayList list = new ArrayList();
            for (Iterator i = input.iterator(); i.hasNext();) {
                PathNode node = (PathNode) i.next();
                if (node instanceof RespRef)
                    list.add(node);
            }
            return list.toArray();
        }
    }

    class RespLabelProvider extends LabelProvider implements ITableLabelProvider {
        public String getColumnText(Object obj, int index) {
            RespRef resp = (RespRef) obj;
            String toReturn=null;
            if (resp.getRespDef() != null) {
                if (index == 0)
                    toReturn = resp.getRespDef().getName();
                else
                    toReturn = resp.getRespDef().getDescription();
            }
            if (toReturn == null)
                return ""; //$NON-NLS-1$
            else
                return toReturn;
        }

        public Image getColumnImage(Object obj, int index) {
            return null;
        }
    }

    /**
     * The constructor.
     */
    public ResponsibilityView() {
    }

    /**
     * This is a callback that will allow us to create the viewer and initialize it.
     */
    public void createPartControl(Composite parent) {
        viewer = new RespListViewer(parent, SWT.NONE);
        viewer.addSelectionChangedListener(this);
        viewer.setContentProvider(new RespContentProvider());
        viewer.setLabelProvider(new RespLabelProvider());
        
        if(input != null)
        	viewer.setInput(input.getPathGraph().getPathNodes());

        getSite().getPage().addPartListener(this);
    }

    private void hookDoubleClickAction() {

    }

    private void showMessage(String message) {
        MessageDialog.openInformation(viewer.getControl().getShell(), Messages.getString("ResponsibilityView.Responsibilities"), message); //$NON-NLS-1$
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partActivated(IWorkbenchPartReference partRef) {
    	if (partRef.getPart(false) == this || partRef.getPart(false) instanceof UCMNavMultiPageEditor) {
        	setEditor(partRef);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partBroughtToTop(IWorkbenchPartReference partRef) {
        // If the part brought to top is this view, then update the input.
        if (partRef.getPart(false) == this) {
        	setEditor(partRef);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partClosed(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) instanceof UCMNavMultiPageEditor && partRef.getPage().getActiveEditor() == null) {
            setInput(null);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partDeactivated(IWorkbenchPartReference partRef) {
//    	if (partRef.getPart(false) instanceof UCMNavMultiPageEditor)
//          setInput(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partOpened(IWorkbenchPartReference partRef) {
    	if(partRef.getPart(false) instanceof UCMNavMultiPageEditor || partRef.getPart(false) == this)
    		setEditor(partRef);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partHidden(IWorkbenchPartReference partRef) {
    	
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partVisible(IWorkbenchPartReference partRef) {
    	if(partRef.getPart(false) instanceof UCMNavMultiPageEditor || partRef.getPart(false) == this)
    		setEditor(partRef);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partInputChanged(IWorkbenchPartReference partRef) {

    }
    
    private void setEditor(IWorkbenchPartReference partRef) {
    	if (partRef.getPage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            setEditor((UCMNavMultiPageEditor) partRef.getPage().getActiveEditor());
            setInput(((UcmEditor)editor.getCurrentPage()).getModel());
        }
    }
    
    /**
     * @param editor
     */
    private void setEditor(UCMNavMultiPageEditor editor) {
        if (this.editor != null) {
            this.editor.removePageChangeListener(this);
        }
        this.editor = editor;
        editor.getCurrentPage().getGraphicalViewer().addSelectionChangedListener(this);
        editor.addPageChangeListener(this);
    }

    private void setInput(Map input) {
        if (viewer != null) {
            if (input == null)
                viewer.setInput(new ArrayList());
            else {
            	if(input != this.input)
            		viewer.setInput(input.getPathGraph().getPathNodes());
            }
        }
        this.input = input;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IViewPart#init(org.eclipse.ui.IViewSite)
     */
    public void init(IViewSite site) throws PartInitException {
        super.init(site);

        if (getSite().getPage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            setEditor((UCMNavMultiPageEditor) getSite().getPage().getActiveEditor());
            setInput(((UcmEditor)editor.getCurrentPage()).getModel());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
    	StructuredSelection sel = (StructuredSelection)event.getSelection();
    	
    	if(event.getSource() instanceof RespListViewer) {
    		ArrayList items = new ArrayList();
    		if(editor != null && editor.getCurrentPage() != null) {
	    		HashMap registry = (HashMap)editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry();
	    		
		    	for (Iterator i = sel.iterator(); i.hasNext();) {
					Object obj = (Object) i.next();
					if(obj instanceof RespRef) {
						RespRef ref = (RespRef) obj;
						EditPart part = (EditPart)registry.get(ref);
						if(part != null)
							editor.getCurrentPage().getGraphicalViewer().select(part);
					}
				}
    		}
//	    	sel = new StructuredSelection(items);
    		
			
//	    	if(editor != null)
//	    		editor.getSelectionSynchronizer().selectionChanged(new SelectionChangedEvent(this, sel));
    	}
    	else {
	    	ArrayList items = new ArrayList();
	    	for (Iterator i = sel.iterator(); i.hasNext();) {
				EditPart part = (EditPart) i.next();
				Object model = part.getModel();
				if(model instanceof RespRef)
					items.add(model);
			}
	    	sel = new StructuredSelection(items);
			
			viewer.setSelection(sel);
    	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editors.IPageChangeListener#pageChanged()
     */
    public void pageChanged() {
        setInput(((UcmEditor)editor.getCurrentPage()).getModel());
    }

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub
		
	}
}