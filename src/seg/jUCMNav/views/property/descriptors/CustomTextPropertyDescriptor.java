/*
 * Created on 13-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.views.property.descriptors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * @author TremblaE
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomTextPropertyDescriptor extends TextPropertyDescriptor {
	
	protected CellEditor editor;
	protected boolean readOnly;

	/**
	 * @param id
	 * @param displayName
	 */
	public CustomTextPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}
	
	public CellEditor createPropertyEditor(Composite parent) {
		editor = new TextCellEditor(parent);
		if (getValidator() != null)
			editor.setValidator(getValidator());
		setReadOnly(readOnly);
		return editor;
	}
	
	public void setReadOnly(boolean readOnly){
		this.readOnly = readOnly;
		if(editor != null)
			editor.getControl().setEnabled(!readOnly);
	}
}
