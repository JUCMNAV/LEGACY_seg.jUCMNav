package seg.jUCMNav.views.compositeList;


import org.eclipse.core.runtime.ListenerList;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

/**
 * A composite to be added in a CompositeListControl
 * 
 * @author Etienne Tremblay
 * 
 */
public abstract class CompositeListItem extends Composite {

    private ListenerList selectListeners = new ListenerList(ListenerList.IDENTITY);
    private boolean selected = false;

    /**
     * @param parent
     *            the parent composite
     * @param style
     *            the style to be used
     */
    public CompositeListItem(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * 
     * @param listener
     *            to selection listener to add
     */
    public void addSelectionListener(ISelectionListener listener) {
        selectListeners.add(listener);
    }

    /**
     * 
     * @param listener
     *            the selection listenr to remove
     */
    public void removeSelectionListener(ISelectionListener listener) {
        selectListeners.remove(listener);
    }

    /**
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    public void dispose() {
        selectListeners.clear();
        super.dispose();
    }

    /**
     * Inform listeners of selection
     * 
     * @param e
     *            event
     */
    protected void fireSelected(MouseEvent e) {
        select();
        Object[] listeners = selectListeners.getListeners();
        for (int i = 0; i < listeners.length; i++) {
            ISelectionListener listener = (ISelectionListener) listeners[i];
            listener.selectionChanged(this);
        }
    }

    /**
     * mark as selected
     */
    public void select() {
        selected = true;
    }

    /**
     * deselect
     * 
     */
    public void unselect() {
        selected = false;
    }

    /**
     * 
     * @return are we selected?
     */
    public boolean isSelected() {
        return selected;
    }
}