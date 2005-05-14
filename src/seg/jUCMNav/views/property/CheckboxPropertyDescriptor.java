/*
 * Created on 13-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.views.property;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * @author TremblaE
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CheckboxPropertyDescriptor extends PropertyDescriptor {

	/** Creates an property descriptor with the given id and display name.
	 * 
	 * @param id the id of the property
	 * @param displayName the name to display for the property
	 */
	public CheckboxPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}
	
	public CellEditor createPropertyEditor(Composite parent) {
		CheckboxButtonCellEditor editor = new CheckboxButtonCellEditor(parent);
		if (getValidator() != null)
			editor.setValidator(getValidator());
		return editor;
	}

}
