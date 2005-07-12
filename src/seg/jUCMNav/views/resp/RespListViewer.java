package seg.jUCMNav.views.resp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import seg.jUCMNav.views.compositeList.CompositeListControl;
import seg.jUCMNav.views.compositeList.CompositeListItem;
import ucm.UcmPackage;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urncore.Responsibility;


/**
 * @author Etienne Tremblay
 *
 */
public class RespListViewer extends StructuredViewer implements Adapter, ISelectionChangedListener {
	
	private CompositeListControl list;
	private ScrolledComposite scrolled;
	
	public RespListViewer(Composite parent, int style){
		super();
//		scrolled = new ScrolledComposite(parent, SWT.V_SCROLL);
		list = new CompositeListControl(parent, style);
		list.addSelectionChangedListener(this);
		
//		list.setSize(new Point(400, 400));
//		
//		scrolled.setContent(list);
//		scrolled.setMinSize(400, 400);
//		scrolled.setExpandHorizontal(true);
//		scrolled.setExpandVertical(true);
//		scrolled.setMinWidth(400);
//		scrolled.setMinHeight(400);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.Viewer#getControl()
	 */
	public Control getControl() {
		return list;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {		
		return new StructuredSelection(new Object());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
	 */
	public void setSelection(ISelection selection, boolean reveal) {
		StructuredSelection sel = (StructuredSelection)selection;
		ArrayList items = new ArrayList();
		for (Iterator i = sel.iterator(); i.hasNext();) {
			PathNode node = (PathNode) i.next();
			if(node instanceof RespRef) {
				CompositeListItem item = (CompositeListItem)doFindItem(node);
				items.add(item);
			}
		}
		sel = new StructuredSelection(items);
		
		list.setSelection(sel);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#doFindInputItem(java.lang.Object)
	 */
	protected Widget doFindInputItem(Object element) {
		if (equals(element, getRoot()))
			return list;
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#doFindItem(java.lang.Object)
	 */
	protected Widget doFindItem(Object element) {
		Object[] children = list.getItems();
		for (int i = 0; i < children.length; i++) {
			RespListItem item = (RespListItem)children[i];
			Object data = item.getData();
			if (data != null && equals(data, element))
				return item;
		}
		return null;
	}
	
	protected ArrayList doFindItems(Object element) {
		Object[] children = list.getItems();
		ArrayList items = new ArrayList();
		for (int i = 0; i < children.length; i++) {
			RespListItem item = (RespListItem)children[i];
			RespRef data = (RespRef)item.getData();
			if (data != null && equals(data.getRespDef(), element))
				items.add(item);
		}
		return items;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#doUpdateItem(org.eclipse.swt.widgets.Widget, java.lang.Object, boolean)
	 */
	protected void doUpdateItem(Widget item, Object element, boolean fullMap) {
//		 remember element we are showing
		if (fullMap) {
			associate(element, item);
		} else {
			item.setData(element);
			mapElement(element, item);	
		}
		
		RespListItem respItem = (RespListItem)item;
		
		ITableLabelProvider provider = (ITableLabelProvider)getLabelProvider();
		
		respItem.setRespName(provider.getColumnText(element, 0));
		respItem.setDescription(provider.getColumnText(element, 1));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#getSelectionFromWidget()
	 */
	protected List getSelectionFromWidget() {
		StructuredSelection sel = (StructuredSelection)list.getSelection();
		ArrayList li = new ArrayList();
//		for (Iterator i = sel.iterator(); i.hasNext();) {
//			Widget item = (Widget) i.next();
//		}
		return li;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#internalRefresh(java.lang.Object)
	 */
	protected void internalRefresh(Object element) {
		list.removeAll();
		
		Object[] children = getSortedChildren(getRoot());
		for (int i = 0; i < children.length; i++) {
			RespRef resp = (RespRef)children[i];
			resp.eAdapters().add(this);
			resp.getRespDef().eAdapters().add(this);
			RespListItem item = new RespListItem(list, SWT.NONE);
			updateItem(item, resp);
		}
		list.layout();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#reveal(java.lang.Object)
	 */
	public void reveal(Object element) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#setSelectionToWidget(java.util.List, boolean)
	 */
	protected void setSelectionToWidget(List l, boolean reveal) {
		if(list == null) {
			list.deselectAll();
			return;
		}
		
		ArrayList items = new ArrayList();
		
		for (Iterator i = l.iterator(); i.hasNext();) {
			Object ref = (Object) i.next();
			RespListItem item = (RespListItem)findItem(ref);
			items.add(item);
		}
		
		StructuredSelection sel = new StructuredSelection(items);
		list.setSelection(sel);
	}
	
	protected void associate(Object element, Widget item) {
		Object data = item.getData();
		if (data != element) {
			if (data != null)
				disassociate(item);
			item.setData(element);
		}
		mapElement(element, item);
	}

	protected void disassociate(Widget item) {
		Object element = item.getData();
		Assert.isNotNull(element);
		//Clear the map before we clear the data
		unmapElement(element, item);
		item.setData(null);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
	 */
	protected void inputChanged(Object input, Object oldInput) {
		List list = (List)input;
		
		if(oldInput != null) {
			List oldList = (List)oldInput;
			if(oldList.size() > 0) {
				PathNode node = (PathNode)oldList.get(0);
				node.getPathGraph().eAdapters().remove(this);
			}
			
			for (Iterator i = oldList.iterator(); i.hasNext();) {
				PathNode node = (PathNode) i.next();
				if(node instanceof RespRef) {
					RespRef ref = (RespRef)node;
					if(!list.contains(node)) {
						ref.eAdapters().remove(this);
						ref.getRespDef().eAdapters().remove(this);
					}
				}
			}
		}
		
		if(list.size() > 0) {
			PathNode node = (PathNode)list.get(0);
			node.getPathGraph().eAdapters().add(this);
		}
		refresh();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		EObject notifier = (EObject)notification.getNotifier();
		
		if(notifier instanceof RespRef) {
			RespRef resp = (RespRef)notifier;
			
			if(doFindItem(resp) != null) {
				RespListItem item = (RespListItem)doFindItem(resp);
				item.setRespName(resp.getRespDef().getName());
				if(resp.getDescription() != null)
					item.setDescription(resp.getDescription());
			}
		}
		else if(notifier instanceof Responsibility) {
			Responsibility resp = (Responsibility)notifier;
			
			ArrayList items = doFindItems(resp);
			
			for (Iterator i = items.iterator(); i.hasNext();) {
				RespListItem item = (RespListItem)i.next();
				item.setRespName(resp.getName());
				if(resp.getDescription() != null)
					item.setDescription(resp.getDescription());
			}
		}
		else if(notifier instanceof PathGraph) {
			int type = notification.getEventType();
	        int featureId = notification.getFeatureID(UcmPackage.class);
	        switch (type) {
	        case Notification.ADD:
	        case Notification.ADD_MANY:
	        	if(notification.getNewValue() instanceof RespRef)
	        		refresh();
	        	break;
	        case Notification.REMOVE:
	        case Notification.REMOVE_MANY:
	        	if(notification.getOldValue() instanceof RespRef)
	        		refresh();
	        	break;
	        }
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		StructuredSelection sel = (StructuredSelection)event.getSelection();
		
		ArrayList items = new ArrayList();
		for (Iterator i = sel.iterator(); i.hasNext();) {
			CompositeListItem item = (CompositeListItem) i.next();
			RespRef ref = (RespRef)item.getData();
			items.add(ref);
		}
		sel = new StructuredSelection(items);
		
		fireSelectionChanged(new SelectionChangedEvent(this, sel));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
	 */
	public Notifier getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		// TODO Auto-generated method stub
		return false;
	}
}
