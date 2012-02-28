package seg.jUCMNav.views.compositeList;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import seg.jUCMNav.views.elements.ElementListItem;

/**
 * A composite which is a list of CompositeListItems.
 * 
 * @author Etienne Tremblay
 * 
 */
public class CompositeListControl extends Composite implements ISelectionProvider, ISelectionListener {

    private ISelection selection = new StructuredSelection();
    private ArrayList items = new ArrayList();
    private ScrolledComposite scrollComp;

    /**
     * List of selection change listeners (element type: <code>ISelectionChangedListener</code>).
     * 
     * @see #selectionChanged(Object)
     */
    private ListenerList selectListeners = new ListenerList(ListenerList.IDENTITY);

    /**
     * @param parent
     *            the parent composite
     * @param style
     *            the style to use
     */
    public CompositeListControl(Composite parent, int style) {
        super(new ScrolledComposite(parent, SWT.V_SCROLL | SWT.BORDER), style);
        scrollComp = (ScrolledComposite) this.getParent();
        initialize();
    }

    /**
     * init code
     * 
     */
    private void initialize() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseDown(MouseEvent e) {
                setSelection(new StructuredSelection());
            }
        });
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.verticalSpacing = 1;
        layout.horizontalSpacing = 0;
        this.setLayout(layout);
        this.setBackground(org.eclipse.swt.widgets.Display.getDefault().getSystemColor(org.eclipse.swt.SWT.COLOR_WHITE));
        scrollComp.setBackground(org.eclipse.swt.widgets.Display.getDefault().getSystemColor(org.eclipse.swt.SWT.COLOR_WHITE));

        scrollComp.setContent(this);
        // this.layout();

        scrollComp.setAlwaysShowScrollBars(true);
        scrollComp.setExpandHorizontal(true);
        scrollComp.setExpandVertical(false);

        Point size = this.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        this.setSize(size);
    }

    /**
     * @param item
     *            to be added to the list.
     */
    public void add(CompositeListItem item) {
        items.add(item);
        item.addSelectionListener(this);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        item.setLayoutData(data);

        Point size = this.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        this.setSize(size);
    }

    /**
     * @return an array of CompositeListItems
     */
    public Object[] getItems() {
        return items.toArray();
    }

    /**
     * clears the list
     * 
     */
    public void removeAll() {
        for (Iterator i = items.iterator(); i.hasNext();) {
            ElementListItem item = (ElementListItem) i.next();
            item.dispose();
        }
        items.clear();
    }

    /**
     * 
     * @param item
     *            the item to be removed from the list.
     */
    public void remove(CompositeListItem item) {
        items.remove(item);
        item.dispose();
    }

    /**
     * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
     */
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        selectListeners.add(listener);
    }

    /**
     * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
     */
    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        selectListeners.remove(listener);
    }

    /**
     * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
     */
    public ISelection getSelection() {
        return selection;
    }

    /**
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    public void setSelection(ISelection selection) {
        this.selection = selection;

        StructuredSelection s = (StructuredSelection) selection;
        Object[] array = s.toArray();

        ArrayList selected = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            CompositeListItem item = (CompositeListItem) array[i];
            if (item != null) {
                selected.add(array[i]);
                if (!item.isSelected())
                    item.select();
            }
        }

        for (Iterator i = items.iterator(); i.hasNext();) {
            CompositeListItem item = (CompositeListItem) i.next();
            if (!selected.contains(item) && item.isSelected())
                item.unselect();
        }
    }

    /**
     * Deselects all the elements in the list.
     */
    public void deselectAll() {
        for (Iterator i = items.iterator(); i.hasNext();) {
            CompositeListItem item = (CompositeListItem) i.next();
            item.unselect();
        }
    }

    /**
     * Notifies listeners.
     * 
     */
    protected void fireSelected() {

        StructuredSelection sel = (StructuredSelection) getSelection();

        Object[] listeners = selectListeners.getListeners();
        for (int i = 0; i < listeners.length; i++) {
            ISelectionChangedListener listener = (ISelectionChangedListener) listeners[i];
            listener.selectionChanged(new SelectionChangedEvent(this, sel));
        }
    }

    /**
     * @see seg.jUCMNav.views.compositeList.ISelectionListener#selectionChanged(java.lang.Object)
     * 
     */
    public void selectionChanged(Object selected) {
        setSelection(new StructuredSelection(selected));
        fireSelected();
    }
} // @jve:decl-index=0:visual-constraint="10,10"
