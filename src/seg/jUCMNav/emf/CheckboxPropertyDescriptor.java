/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.emf;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * A property descriptor for boolean values that uses the CheckboxCellEditor
 * @author ddean
 *
 */
public class CheckboxPropertyDescriptor extends PropertyDescriptor {
	/**
	 * @param id
	 * @param displayName
	 */
	public CheckboxPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new CheckboxCellEditor(parent);
		if (getValidator() != null)
			editor.setValidator(getValidator());
		return editor;
	}

}

