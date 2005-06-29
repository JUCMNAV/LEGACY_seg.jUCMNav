package seg.jUCMNav.views;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.util.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
/**
 * @author Etienne Tremblay
 *
 */
public class RespListControl extends Composite implements ISelectionProvider, MouseListener {
	
	private ISelection selection = new StructuredSelection();
	
	private ArrayList items = new ArrayList();
	
	/**
	 * List of selection change listeners (element type: <code>ISelectionChangedListener</code>).
	 *
	 * @see #fireSelectionChanged
	 */
	private ListenerList selectionChangedListeners = new ListenerList(3);
	/**
	 * @param parent
	 * @param style
	 */
	public RespListControl(Composite parent, int style) {
		super(parent, style | SWT.H_SCROLL);
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
//		rowLayout6.type = org.eclipse.swt.SWT.VERTICAL;
//		rowLayout6.fill = true;
//		rowLayout6.pack = false;
//		rowLayout6.wrap = true;
//		setSize(new org.eclipse.swt.graphics.Point(300,260));
	}
	
	void add(RespListItem item){
		items.add(item);
		item.addMouseListener(this);
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
		selectionChangedListeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
	 */
	public void mouseDoubleClick(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
	 */
	public void mouseDown(MouseEvent e) {
		setSelection(new StructuredSelection(e.widget));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
	 */
	public void mouseUp(MouseEvent e) {}

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
		
		ArrayList resp = new ArrayList();
		for (int i = 0; i < array.length; i++) {
			resp.add(array[i]);
		}
		
		for (Iterator i = items.iterator(); i.hasNext();) {
			RespListItem item = (RespListItem) i.next();
			if(!resp.contains(item) && item.isSelected())
				item.unselect();
		}
	}
  }  //  @jve:decl-index=0:visual-constraint="10,10"
