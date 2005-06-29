package seg.jUCMNav.views;

import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import urncore.Responsibility;


/**
 * @author Etienne Tremblay
 *
 */
public class RespListViewer extends StructuredViewer implements Adapter {
	
	private RespListControl list;
	
	public RespListViewer(Composite parent, int style){
		this(new RespListControl(parent, style));
	}
	
	/**
	 * @param list
	 */
	public RespListViewer(RespListControl list) {
		super();
		this.list = list;
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StructuredViewer#internalRefresh(java.lang.Object)
	 */
	protected void internalRefresh(Object element) {
		list.removeAll();
		
		Object[] children = getSortedChildren(getRoot());
		for (int i = 0; i < children.length; i++) {
			Responsibility resp = (Responsibility)children[i];
			resp.eAdapters().add(this);
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
		// TODO Auto-generated method stub
		
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
		refresh();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		Responsibility resp = (Responsibility)notification.getNotifier();
		
		if(doFindItem(resp) != null) {
			RespListItem item = (RespListItem)doFindItem(resp);
			item.setRespName(resp.getName());
			item.setDescription(resp.getDescription());
		}
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
