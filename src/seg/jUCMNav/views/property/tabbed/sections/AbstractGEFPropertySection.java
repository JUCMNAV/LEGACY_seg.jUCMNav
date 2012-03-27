/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package seg.jUCMNav.views.property.tabbed.sections;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import seg.jUCMNav.views.property.tabbed.GEFTabbedPropertySheetPage;

/**
 * An abstract implementation of a section in a tab in the tabbed property sheet page for the hockey league example.
 * 
 * @author Anthony Hunter
 */
public abstract class AbstractGEFPropertySection extends AbstractPropertySection {

    /**
     * the property sheet page for this section.
     */
    protected GEFTabbedPropertySheetPage propertySheetPage;

    /**
     * The current selected object or the first object in the selection when multiple objects are selected.
     */
    protected EObject eObject;

    /**
     * The list of current selected objects.
     */
    protected List eObjectList;

    private Notifier target;

    protected ISelection selection;

    /**
     * Get the standard label width when labels for sections line up on the left hand side of the composite. We line up to a fixed position, but if a string is
     * wider than the fixed position, then we use that widest string.
     * 
     * @param parent
     *            The parent composite used to create a GC.
     * @param labels
     *            The list of labels.
     * @return the standard label width.
     */
    protected int getStandardLabelWidth(Composite parent, String[] labels) {
        return propertySheetPage.getLabelWidth(this, parent, labels);
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        this.propertySheetPage = (GEFTabbedPropertySheetPage) aTabbedPropertySheetPage;
        this.propertySheetPage.addSectionToRefresh(this);
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
     */
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);

        this.selection = selection;

        if (!(selection instanceof IStructuredSelection)) {
            return;
        }
        eObjectList = buildObjectList(selection);
        if (eObjectList.size() > 0) {
            eObject = (EObject) eObjectList.get(0);
        } else
            eObject = null;

    }
    
    protected Vector buildObjectList(ISelection selection, boolean resolve) {
        Vector result = new Vector();

        Iterator it = ((IStructuredSelection) selection).toList().iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof EditPart) {
                EObject e = (EObject) ((EditPart) obj).getModel();
                obj = resolve ? getDataForSection(e) : e;

                if (obj != null)
                    result.add(obj);
            } else if (obj instanceof EObject) {
                obj = resolve ? getDataForSection(obj) : obj;
                if (obj != null)
                    result.add(obj);
            }
        }
        
        return result;
    }

    protected Vector buildObjectList(ISelection selection) {
        return buildObjectList(selection, true);
    }

    public abstract String getLabelText();

    protected Object getDataForSection(Object obj) {
        return propertySheetPage.getDataResolver().getData(obj);
    }
}