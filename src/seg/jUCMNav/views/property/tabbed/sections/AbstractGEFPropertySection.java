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

import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import seg.jUCMNav.views.property.tabbed.GEFTabbedPropertySheetPage;

/**
 * An abstract implementation of a section in a tab in the tabbed property sheet
 * page for the hockey league example.
 * 
 * @author Anthony Hunter
 */
public abstract class AbstractGEFPropertySection
	extends AbstractPropertySection {
	
	/**
	 * the property sheet page for this section.
	 */
	protected GEFTabbedPropertySheetPage propertySheetPage;

	/**
	 * The current selected object or the first object in the selection when
	 * multiple objects are selected.
	 */
	protected EObject eObject;

	/**
	 * The list of current selected objects.
	 */
	protected List eObjectList;
	
    private Notifier target;

	/**
	 * Get the standard label width when labels for sections line up on the left
	 * hand side of the composite. We line up to a fixed position, but if a
	 * string is wider than the fixed position, then we use that widest string.
	 * 
	 * @param parent
	 *            The parent composite used to create a GC.
	 * @param labels
	 *            The list of labels.
	 * @return the standard label width.
	 */
	protected int getStandardLabelWidth(Composite parent, String[] labels) {
		int standardLabelWidth = STANDARD_LABEL_WIDTH;
		GC gc = new GC(parent);
		int indent = gc.textExtent("XXX").x; //$NON-NLS-1$
		for (int i = 0; i < labels.length; i++) {
			int width = gc.textExtent(labels[i]).x;
			if (width + indent > standardLabelWidth) {
				standardLabelWidth = width + indent;
			}
		}
		gc.dispose();
		return standardLabelWidth;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		this.propertySheetPage = (GEFTabbedPropertySheetPage) aTabbedPropertySheetPage;
		
		CommandStackListener commandStackListener = new CommandStackListener() {
			public void commandStackChanged(EventObject e) {
				refresh();
			}
		};
		
		propertySheetPage.getEditor().getCommandStack().addCommandStackListener(commandStackListener);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if (!(selection instanceof IStructuredSelection)) {
			return;
		}
		buildObjectList(selection);
		if (eObjectList.size()>0)
		{
			eObject = (EObject) eObjectList.get(0); 
		}
		else
			eObject=null;

	}

	private void buildObjectList(ISelection selection)
	{
		eObjectList = new Vector();
		
		Iterator it = ((IStructuredSelection) selection).toList().iterator();
		while(it.hasNext())
		{
			Object obj = it.next();
			if(obj instanceof EditPart)
			{
				EObject e = (EObject)((EditPart)obj).getModel();
				obj = getDataForSection(e);

				if (obj!=null)
					eObjectList.add(obj);
			}
			else if (obj instanceof EObject)
			{
				obj = getDataForSection(obj);
				if (obj!=null)
					eObjectList.add(obj);
			}
		}
	}
	
	protected Object getDataForSection(Object obj)
	{
		return propertySheetPage.getDataResolver().getData(obj);
	}
}