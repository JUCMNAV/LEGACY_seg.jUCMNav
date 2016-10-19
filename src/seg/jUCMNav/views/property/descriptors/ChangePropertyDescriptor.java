package seg.jUCMNav.views.property.descriptors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.Messages;
import urn.dyncontext.Change;
import urn.dyncontext.ConstantChange;
import urn.dyncontext.EnumChange;
import urn.dyncontext.FormulaChange;
import urn.dyncontext.LinearChange;
import urn.dyncontext.QuadraticChange;

/**
 * A property descriptor that opens the ChangeCellEditor.
 * 
 * @author aprajita
 * @see seg.jUCMNav.views.property.descriptors.ChangeCellEditor
 */
public class ChangePropertyDescriptor extends PropertyDescriptor {
	
	private Change change;
	private String name, valueToChange;

    /**
     * Property descriptor for Change
     * 
     * @param id
     * @param urnelem
     */
    public ChangePropertyDescriptor(Object id, Change change, String valueToChange) {
    	
        super(id, valueToChange); //$NON-NLS-1$
        this.change = change;
        this.valueToChange = valueToChange;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        if (valueToChange.equals("startDate")) {
        	name = df.format(change.getStart());
        } else if (valueToChange.equals("endDate")) {
        	name = df.format(change.getEnd());
        } else if (valueToChange.equals("newValue")) {
        	if (change instanceof EnumChange)
        		name = ((EnumChange) change).getNewValue();
        	else if (change instanceof ConstantChange)
        		name = Integer.toString(((ConstantChange) change).getNewValue());
        	else if (change instanceof LinearChange)
        		name = Integer.toString(((LinearChange) change).getNewValue());
        } else if (valueToChange.equals("quadraticCoefficient")) {
        	name = Float.toString(((QuadraticChange) change).getQuadraticCoefficient());
        } else if (valueToChange.equals("linearCoefficient")) {
        	name = Float.toString(((QuadraticChange) change).getLinearCoefficient());
        } else if (valueToChange.equals("constant")) {
        	name = Float.toString(((QuadraticChange) change).getConstant());
        } else if (valueToChange.equals("formula")) {
        	name = ((FormulaChange) change).getFormula();
        } else
        	name = "";
        
        setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return name + Messages.getString("MetadataPropertyDescriptor.ClickToEdit"); //$NON-NLS-1$
            }
        });
    }

    /**
     * Creates the cell editor.
     */
    public CellEditor createPropertyEditor(Composite parent) {
    	ChangeCellEditor editor = new ChangeCellEditor(parent, change, valueToChange);
        if (change != null)
            editor.setChange(change);

        return editor;
    }

}
