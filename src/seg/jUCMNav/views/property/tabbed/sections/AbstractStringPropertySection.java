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

/**
 * An abstract implementation of a section for a field with a String property value.
 * 
 * @author Anthony Hunter
 */
public abstract class AbstractStringPropertySection extends AbstractTextPropertySection {

    /**
     * @see seg.jUCMNav.views.property.tabbed.sections.examples.views.properties.tabbed.hockeyleague.ui.properties.sections.AbstractTextPropertySection#isEqual(java.lang.String)
     */
    protected boolean isEqual(String newText) {
        return getFeatureAsText().equals(newText);
    }

    /**
     * @see seg.jUCMNav.views.property.tabbed.sections.examples.views.properties.tabbed.hockeyleague.ui.properties.sections.AbstractTextPropertySection#getFeatureAsText()
     */
    protected String getFeatureAsText() {
        if (eObject == null)
            return ""; //$NON-NLS-1$
        String string = (String) eObject.eGet(getFeature());
        if (string == null) {
            return "";//$NON-NLS-1$
        }
        return string;
    }

    /**
     * @see seg.jUCMNav.views.property.tabbed.sections.examples.views.properties.tabbed.hockeyleague.ui.properties.sections.AbstractTextPropertySection#getFeatureValue(java.lang.String)
     */
    protected Object getFeatureValue(String newText) {
        return newText;
    }
}