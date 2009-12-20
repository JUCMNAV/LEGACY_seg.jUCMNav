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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import seg.jUCMNav.model.commands.change.SetCommand;
import seg.jUCMNav.views.property.StackHelper;
import seg.jUCMNav.views.property.tabbed.TextChangeHelper;

/**
 * An abstract implementation of a section with a text field.
 * 
 * @author Anthony Hunter
 */
public abstract class AbstractTextPropertySection extends AbstractGEFPropertySection {

    /**
     * the text control for the section.
     */
    protected Text text;
    protected Composite parentComposite;

    /**
     * A helper to listen for events that indicate that a text field has been changed.
     */
    protected TextChangeHelper listener;

    protected boolean fireChangeOnEnter() {
        return true;
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        parentComposite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;

        text = createText(parentComposite, ""); //$NON-NLS-1$

        CLabel nameLabel = getWidgetFactory().createCLabel(parentComposite, getLabelText());
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(text, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(text, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        listener = new TextChangeHelper() {

            public void textChanged(Control control) {
                handleTextModified();
            }
        };
        listener.startListeningTo(text);
        if (fireChangeOnEnter())
            listener.startListeningForEnter(text);
    }

    /**
     * Handle the text modified event.
     */
    protected void handleTextModified() {
        String newText = text.getText();
        boolean equals = isEqual(newText);
        boolean isTextValid = isTextValid(newText);
        if (!equals && isTextValid) {
            CommandStack stack = StackHelper.getDelegatingStack(getPart());
            if (stack != null) {
                Object value = getFeatureValue(newText);
                if (eObjectList.size() == 1) {
                    /* apply the property change to single selected object */
                    stack.execute(new SetCommand(eObject, getFeature(), value));
                } else {
                    CompoundCommand compoundCommand = new CompoundCommand();
                    /* apply the property change to all selected elements */
                    for (Iterator i = eObjectList.iterator(); i.hasNext();) {
                        EObject nextObject = (EObject) i.next();
                        compoundCommand.add(new SetCommand(nextObject, getFeature(), value));
                    }
                    stack.execute(compoundCommand);
                }
            }
        } else if (!isTextValid) {
            setText(getFeatureAsText());
        }
    }

    protected boolean isTextValid(String text) {
        return true;
    }

    protected Text createText(Composite composite, String value) {
        Text text = getWidgetFactory().createText(composite, value);

        FormData data = new FormData();
        data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[] { getLabelText() }));
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        text.setLayoutData(data);

        return text;
    }

    public void dispose() {
        // causes exceptions because text is disposed...
        // listener.stopListeningTo(text);

        super.dispose();
    }

    /*
     * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
     */
    public void refresh() {
        if (text != null) {
            if (!text.isDisposed())
                setText(getFeatureAsText());
        }
    }

    /**
     * Determine if the provided string value is an equal representation of the current setting of the text property.
     * 
     * @param newText
     *            the new string value.
     * @return <code>true</code> if the new string value is equal to the current property setting.
     */
    protected abstract boolean isEqual(String newText);

    /**
     * Get the feature for the text field for the section.
     * 
     * @return the feature for the text.
     */
    protected abstract EAttribute getFeature();

    /**
     * Get the value of the feature as text for the text field for the section.
     * 
     * @return the value of the feature as text.
     */
    protected abstract String getFeatureAsText();

    /**
     * Get the new value of the feature for the text field for the section.
     * 
     * @param newText
     *            the new value of the feature as a string.
     * @return the new value of the feature.
     */
    protected abstract Object getFeatureValue(String newText);

    /**
     * Get the label for the text field for the section.
     * 
     * @return the label for the text field.
     */
    public abstract String getLabelText();
    
    public void setText(String newText) {
        text.setText(newText);
        text.setSelection(newText.length());
    }
}