package seg.jUCMNav.views.compositeList;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.util.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import seg.jUCMNav.views.resp.RespListItem;
/**
 * @author Etienne Tremblay
 *
 */
public class CompositeListControl extends Composite implements ISelectionProvider, ISelectionListener {
	
	private ISelection selection = new StructuredSelection();
	
	private ArrayList items = new ArrayList();
	
	/**
	 * List of selection change listeners (element type: <code>ISelectionChangedListener</code>).
	 *
	 * @see #selectionChanged(CompositeListItem)
	 */
	private ListenerList selectListeners = new ListenerList(3);
	/**
	 * @param parent
	 * @param style
	 */
	public CompositeListControl(Composite parent, int style) {
		super(parent, style | SWT.V_SCROLL);
		initialize();
	}

	private void initialize() {
		this.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e) {
				setSelection(new StructuredSelection());
			}
		});
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.verticalSpacing = 2;
		layout.horizontalSpacing = 0;
		this.setLayout(layout);
		this.setBackground(org.eclipse.swt.widgets.Display.getDefault().getSystemColor(org.eclipse.swt.SWT.COLOR_WHITE));
	}
	
	public void add(CompositeListItem item){
		items.add(item);
		item.addSelectionListener(this);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		item.setLayoutData(data);
	}
	
	public Object[] getItems(){
		return items.toArray();
	}
	
	public void removeAll(){
		for (Iterator i = items.iterator(); i.hasNext();) {
			RespListItem item = (RespListItem) i.next();
			item.dispose();
		}
		items.clear();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectListeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectListeners.remove(listener);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return selection;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
		
		StructuredSelection s = (StructuredSelection)selection;
		Object[] array = s.toArray();
		
		ArrayList selected = new ArrayList();
		for (int i = 0; i < array.length; i++) {
			CompositeListItem item = (CompositeListItem)array[i];
			if(item != null) {
				selected.add(array[i]);
				if(!item.isSelected())
					item.select();
			}
		}
		
		for (Iterator i = items.iterator(); i.hasNext();) {
			CompositeListItem item = (CompositeListItem) i.next();
			if(!selected.contains(item) && item.isSelected())
				item.unselect();
		}
	}
	
	public void deselectAll() {
		for (Iterator i = items.iterator(); i.hasNext();) {
			CompositeListItem item = (CompositeListItem) i.next();
			item.unselect();
		}
	}
	
	protected void fireSelected(){
		
		StructuredSelection sel = (StructuredSelection)getSelection();
		
		Object[] listeners = selectListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			ISelectionChangedListener listener = (ISelectionChangedListener)listeners[i];
			listener.selectionChanged(new SelectionChangedEvent(this, sel));
		}
	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.views.ISelectionInListListener#selectionChanged(seg.jUCMNav.views.CompositeListItem)
	 */
	public void selectionChanged(Object selected) {
		setSelection(new StructuredSelection(selected));
		fireSelected();
	}
  }  //  @jve:decl-index=0:visual-constraint="10,10"
