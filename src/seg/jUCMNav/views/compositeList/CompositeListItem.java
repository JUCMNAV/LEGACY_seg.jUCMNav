package seg.jUCMNav.views.compositeList;

import org.eclipse.jface.util.ListenerList;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;


/**
 * @author Etienne Tremblay
 *
 */
public abstract class CompositeListItem extends Composite {
	
	private ListenerList selectListeners = new ListenerList(3);
	
	private boolean selected = false;

	/**
	 * @param parent
	 * @param style
	 */
	public CompositeListItem(Composite parent, int style) {
		super(parent, style);
	}
	
	public void addSelectionListener(ISelectionListener listener) {
		selectListeners.add(listener);
	}
	
	public void removeSelectionListener(ISelectionListener listener) {
		selectListeners.remove(listener);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Widget#dispose()
	 */
	public void dispose() {
		selectListeners.clear();
		super.dispose();
	}
	
	protected void fireSelected(MouseEvent e){
		select();
		Object[] listeners = selectListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			ISelectionListener listener = (ISelectionListener)listeners[i];
			listener.selectionChanged(this);
		}
	}
	
	public void select() {
		selected = true;
	}
	
	public void unselect() {
		selected = false;
	}
	
	public boolean isSelected() {
		return selected;
	}
}
