package seg.jUCMNav.views.elements;

import fm.Feature;
import grl.GRLGraph;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.compositeList.CompositeListControl;
import seg.jUCMNav.views.compositeList.CompositeListItem;
import ucm.UcmPackage;
import ucm.map.RespRef;
import ucm.map.UCMmap;
import urncore.IURNNode;
import urncore.Responsibility;

/**
 * A structured viewer for the responsibility reference and intentional element and KPIInformationElement reference list in the element view.
 * 
 * @author Etienne Tremblay, pchen
 * 
 */
public class ElementListViewer extends StructuredViewer implements Adapter, ISelectionChangedListener {

    private CompositeListControl list;

    public ElementListViewer(Composite parent, int style) {
        super();
        list = new CompositeListControl(parent, style);
        list.addSelectionChangedListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.Viewer#getControl()
     */
    public Control getControl() {
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
     */
    public ISelection getSelection() {
        return new StructuredSelection(new Object());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
     */
    public void setSelection(ISelection selection, boolean reveal) {
        StructuredSelection sel = (StructuredSelection) selection;
        ArrayList items = new ArrayList();
        for (Iterator i = sel.iterator(); i.hasNext();) {
            IURNNode node = (IURNNode) i.next();
            if ((node instanceof RespRef) || (node instanceof IntentionalElementRef) || (node instanceof KPIInformationElementRef)) {
                CompositeListItem item = (CompositeListItem) doFindItem(node);
                items.add(item);
            }
        }
        sel = new StructuredSelection(items);

        list.setSelection(sel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#doFindInputItem(java.lang.Object)
     */
    protected Widget doFindInputItem(Object element) {
        if (equals(element, getRoot()))
            return list;

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#doFindItem(java.lang.Object)
     */
    protected Widget doFindItem(Object element) {
        Object[] children = list.getItems();
        for (int i = 0; i < children.length; i++) {
            ElementListItem item = (ElementListItem) children[i];
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
            ElementListItem item = (ElementListItem) children[i];
            IURNNode data = (IURNNode) item.getData();
            if (data != null) {
                if (data instanceof RespRef && equals(((RespRef) data).getRespDef(), element)) {
                    items.add(item);
                } else if (data instanceof IntentionalElementRef && equals(((IntentionalElementRef) data).getDef(), element)) {
                    items.add(item);
                } else if (data instanceof KPIInformationElementRef && equals(((KPIInformationElementRef) data).getDef(), element)) {
                    items.add(item);
                }
            }

        }
        return items;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#doUpdateItem(org.eclipse.swt.widgets.Widget, java.lang.Object, boolean)
     */
    protected void doUpdateItem(Widget item, Object element, boolean fullMap) {
        // remember element we are showing
        if (fullMap) {
            associate(element, item);
        } else {
            item.setData(element);
            mapElement(element, item);
        }

        ElementListItem respItem = (ElementListItem) item;

        ITableLabelProvider provider = (ITableLabelProvider) getLabelProvider();

        respItem.setElementName(provider.getColumnText(element, 0));
        respItem.setDescription(provider.getColumnText(element, 1));

        // Set the icon figure
        if (element instanceof IntentionalElementRef) {
            IntentionalElementRef intentional = (IntentionalElementRef) element;
            if (intentional.getDef().getType().getValue() == IntentionalElementType.SOFTGOAL) {
                respItem.setElementImg("/seg/jUCMNav/icons/Softgoal16.gif"); //$NON-NLS-1$
            } else if (intentional.getDef().getType().getValue() == IntentionalElementType.GOAL) {
                respItem.setElementImg("/seg/jUCMNav/icons/Goal16.gif"); //$NON-NLS-1$
            } else if (intentional.getDef().getType().getValue() == IntentionalElementType.RESSOURCE) {
                respItem.setElementImg("/seg/jUCMNav/icons/Resource16.gif"); //$NON-NLS-1$
            } else if (intentional.getDef().getType().getValue() == IntentionalElementType.TASK) {
            	if (intentional.getDef() instanceof Feature)
            		respItem.setElementImg("/seg/jUCMNav/icons/Feature16.gif"); //$NON-NLS-1$
            	else
            		respItem.setElementImg("/seg/jUCMNav/icons/Task16.gif"); //$NON-NLS-1$
            } else if (intentional.getDef().getType().getValue() == IntentionalElementType.INDICATOR) {
                respItem.setElementImg("/seg/jUCMNav/icons/Indicator16.gif"); //$NON-NLS-1$
            }
        } else if (element instanceof KPIInformationElementRef) {
            KPIInformationElementRef intentional = (KPIInformationElementRef) element;
            respItem.setElementImg("/seg/jUCMNav/icons/Dimension16.gif"); //$NON-NLS-1$
        } else if (element instanceof RespRef) {
            respItem.setElementImg("/seg/jUCMNav/icons/Resp16.gif"); //$NON-NLS-1$
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#getSelectionFromWidget()
     */
    protected List getSelectionFromWidget() {
        // StructuredSelection sel = (StructuredSelection) list.getSelection();
        ArrayList li = new ArrayList();
        // for (Iterator i = sel.iterator(); i.hasNext();) {
        // Widget item = (Widget) i.next();
        // }
        return li;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#internalRefresh(java.lang.Object)
     */
    protected void internalRefresh(Object element) {
        // bug 405
        if (list == null || list.isDisposed())
            return;

        Object[] children = getSortedChildren(getRoot());
        Object[] items = list.getItems();

        int min = Math.min(children.length, items.length);

        for (int i = 0; i < children.length; i++) {
            IURNNode resp = (IURNNode) children[i];

            if (i < items.length) {
                ElementListItem item = (ElementListItem) items[i];
                if (equals(item.getData(), resp))
                    updateItem(item, resp);
                else {
                    updateItem(item, resp);
                }
            } else {
                ElementListItem newItem = new ElementListItem(list, SWT.NONE);
                updateItem(newItem, resp);
            }
        }

        if (min == children.length) {
            for (int i = children.length; i < items.length; i++) {
                ElementListItem item = (ElementListItem) items[i];
                list.remove(item);
            }
        }

        list.layout();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#reveal(java.lang.Object)
     */
    public void reveal(Object element) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#setSelectionToWidget(java.util.List, boolean)
     */
    protected void setSelectionToWidget(List l, boolean reveal) {
        if (l == null) { // problem found by inforce.
            list.deselectAll();
            return;
        }

        ArrayList items = new ArrayList();

        for (Iterator i = l.iterator(); i.hasNext();) {
            Object ref = i.next();
            ElementListItem item = (ElementListItem) findItem(ref);
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
        // Clear the map before we clear the data
        unmapElement(element, item);
        item.setData(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
     */
    protected void inputChanged(Object input, Object oldInput) {
        List list = (List) input;

        if (oldInput != null) {
            List oldList = (List) oldInput;
            if (oldList.size() > 0) {
                IURNNode node = (IURNNode) oldList.get(0);
                node.getDiagram().eAdapters().remove(this);
            }

            for (Iterator i = oldList.iterator(); i.hasNext();) {
                IURNNode node = (IURNNode) i.next();
                if (node instanceof RespRef) {
                    RespRef ref = (RespRef) node;
                    if (!list.contains(node)) {
                        ref.eAdapters().remove(this);
                        if (ref.getRespDef() != null)
                            ref.getRespDef().eAdapters().remove(this);
                    }
                } else if (node instanceof IntentionalElementRef) {
                    IntentionalElementRef ref = (IntentionalElementRef) node;
                    if (!list.contains(node)) {
                        ref.eAdapters().remove(this);
                        if (ref.getDef() != null)
                            ref.getDef().eAdapters().remove(this);
                    }
                } else if (node instanceof KPIInformationElementRef) {
                    KPIInformationElementRef ref = (KPIInformationElementRef) node;
                    if (!list.contains(node)) {
                        ref.eAdapters().remove(this);
                        if (ref.getDef() != null)
                            ref.getDef().eAdapters().remove(this);
                    }
                }
            }
        }

        if (input != null) {
            List newList = (List) input;

            for (Iterator i = list.iterator(); i.hasNext();) {
                IURNNode node = (IURNNode) i.next();
                if (node instanceof RespRef) {
                    RespRef ref = (RespRef) node;

                    ref.eAdapters().add(this);
                    ref.getRespDef().eAdapters().add(this);
                } else if (node instanceof IntentionalElementRef) {
                    IntentionalElementRef ref = (IntentionalElementRef) node;

                    ref.eAdapters().add(this);
                    ref.getDef().eAdapters().add(this);
                } else if (node instanceof KPIInformationElementRef) {
                    KPIInformationElementRef ref = (KPIInformationElementRef) node;

                    ref.eAdapters().add(this);
                    ref.getDef().eAdapters().add(this);
                }
            }

            if (list.size() > 0) {
                IURNNode node = (IURNNode) list.get(0);
                node.getDiagram().eAdapters().add(this);
            }
        }
        refresh();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        EObject notifier = (EObject) notification.getNotifier();
        if (list.isDisposed()) {
            notifier.eAdapters().remove(this);
            return;
        }

        if (notifier instanceof RespRef) {
            RespRef resp = (RespRef) notifier;

            int type = notification.getEventType();
            switch (type) {
            case Notification.SET:
                if (notification.getNewValue() instanceof Responsibility) {
                    if (notification.getOldValue() != null)
                        ((Responsibility) notification.getOldValue()).eAdapters().remove(this);

                    ((Responsibility) notification.getNewValue()).eAdapters().add(this);
                }
                break;
            }

            // if (doFindItem(resp) != null) {
            // ElementListItem item = (ElementListItem) doFindItem(resp);
            // if (resp.getRespDef() != null) {
            // item.setRespName(resp.getRespDef().getName());
            // if (resp.getDescription() != null)
            // item.setDescription(resp.getDescription());
            // }
            // }
        } else if (notifier instanceof Responsibility) {
            Responsibility resp = (Responsibility) notifier;

            ArrayList items = doFindItems(resp);

            for (Iterator i = items.iterator(); i.hasNext();) {
                ElementListItem item = (ElementListItem) i.next();
                item.setElementName(resp.getName());
                if (resp.getDescription() != null)
                    item.setDescription(resp.getDescription());
            }
        } else if (notifier instanceof UCMmap) {
            int type = notification.getEventType();
            int featureId = notification.getFeatureID(UcmPackage.class);
            switch (type) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                if (notification.getNewValue() instanceof RespRef)
                    refresh();
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                if (notification.getOldValue() instanceof RespRef)
                    refresh();
                break;
            }
        } else if (notifier instanceof IntentionalElementRef) {
            int type = notification.getEventType();
            switch (type) {
            case Notification.SET:
                if (notification.getNewValue() instanceof IntentionalElement) {
                    if (notification.getOldValue() != null)
                        ((IntentionalElement) notification.getOldValue()).eAdapters().remove(this);

                    ((IntentionalElement) notification.getNewValue()).eAdapters().add(this);
                }
                break;
            }
        } else if (notifier instanceof IntentionalElement) {
            IntentionalElement intentional = (IntentionalElement) notifier;

            ArrayList items = doFindItems(intentional);

            for (Iterator i = items.iterator(); i.hasNext();) {
                ElementListItem item = (ElementListItem) i.next();
                item.setElementName(intentional.getName());
                if (intentional.getDescription() != null) {
                    item.setDescription(intentional.getDescription());
                }
            }
        } else if (notifier instanceof KPIInformationElementRef) {
            int type = notification.getEventType();
            switch (type) {
            case Notification.SET:
                if (notification.getNewValue() instanceof KPIInformationElement) {
                    if (notification.getOldValue() != null)
                        ((KPIInformationElement) notification.getOldValue()).eAdapters().remove(this);

                    ((KPIInformationElement) notification.getNewValue()).eAdapters().add(this);
                }
                break;
            }
        } else if (notifier instanceof KPIInformationElement) {
            KPIInformationElement kpiInformationElement = (KPIInformationElement) notifier;

            ArrayList items = doFindItems(kpiInformationElement);

            for (Iterator i = items.iterator(); i.hasNext();) {
                ElementListItem item = (ElementListItem) i.next();
                item.setElementName(kpiInformationElement.getName());
                if (kpiInformationElement.getDescription() != null) {
                    item.setDescription(kpiInformationElement.getDescription());
                }
            }
        } else if (notifier instanceof GRLGraph) {
            int type = notification.getEventType();
            int featureId = notification.getFeatureID(GrlPackage.class);
            switch (type) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                if (notification.getNewValue() instanceof IntentionalElementRef || notification.getNewValue() instanceof KPIInformationElementRef)
                    refresh();
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                if (notification.getOldValue() instanceof IntentionalElementRef || notification.getOldValue() instanceof KPIInformationElementRef)
                    refresh();
                break;
            }
        }
        // bug 392
        list.pack();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        if (list.isDisposed()) {
            list.removeSelectionChangedListener(this);
            return;
        }

        StructuredSelection sel = (StructuredSelection) event.getSelection();

        ArrayList items = new ArrayList();
        for (Iterator i = sel.iterator(); i.hasNext();) {
            CompositeListItem item = (CompositeListItem) i.next();
            IURNNode ref = (IURNNode) item.getData();
            items.add(ref);
        }
        sel = new StructuredSelection(items);

        fireSelectionChanged(new SelectionChangedEvent(this, sel));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        // TODO Auto-generated method stub
        return false;
    }
}