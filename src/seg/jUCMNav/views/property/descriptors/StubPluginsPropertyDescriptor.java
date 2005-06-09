package seg.jUCMNav.views.property.descriptors;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.Messages;
import ucm.map.Stub;

/**
 * Created 2005-06-05
 * 
 * @author Etienne Tremblay
 */
public class StubPluginsPropertyDescriptor extends PropertyDescriptor {
    private Stub stub;
    private CommandStack cmdStack;

    /**
     * @param id
     * @param display
     *  
     */
    public StubPluginsPropertyDescriptor(Object id, Stub stub, CommandStack cmdStack) {
        super(id, Messages.getString("StubPluginsPropertyDescriptor.plugins")); //$NON-NLS-1$
        this.stub = stub;
        this.cmdStack = cmdStack;
        setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return ""; //$NON-NLS-1$
            }
        });
    }

    public CellEditor createPropertyEditor(Composite parent) {
        StubPluginsCellEditor editor = new StubPluginsCellEditor(parent, cmdStack);
        editor.setStub(stub);
        return editor;
    }
}