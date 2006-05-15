package seg.jUCMNav.views.dnd;

import org.eclipse.gef.dnd.SimpleObjectTransfer;

/**
 * Largely inspired from TemplateTransfer in GEF code.
 * 
 * Singleton class to handle copy/paste and drag&drop from the outline.
 * 
 * @author jkealey@shade.ca
 */
public class UrnTemplateTransfer extends SimpleObjectTransfer {

    private static final UrnTemplateTransfer INSTANCE = new UrnTemplateTransfer();
    private static final String TYPE_NAME = "Ucm Template transfer"//$NON-NLS-1$
            + System.currentTimeMillis() + ":" + INSTANCE.hashCode();//$NON-NLS-1$
    private static final int TYPEID = registerType(TYPE_NAME);

    private UrnTemplateTransfer() {
    }

    /**
     * Returns the singleton instance
     * 
     * @return the singleton
     */
    public static UrnTemplateTransfer getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the <i>template</i> object.
     * 
     * @return the template
     */
    public Object getTemplate() {
        return getObject();
    }

    /**
     * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
     */
    protected int[] getTypeIds() {
        return new int[] { TYPEID };
    }

    /**
     * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
     */
    protected String[] getTypeNames() {
        return new String[] { TYPE_NAME };
    }

    /**
     * Sets the <i>template</i> Object.
     * 
     * @param template
     *            the template
     */
    public void setTemplate(Object template) {
        setObject(template);
    }

}
