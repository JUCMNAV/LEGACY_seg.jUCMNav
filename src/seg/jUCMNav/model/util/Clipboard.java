package seg.jUCMNav.model.util;

import java.util.List;

import org.eclipse.gef.dnd.SimpleObjectTransfer;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.ImageData;

import urn.URNspec;

/**
 * A GEF clipboard for cut/copy/paste actions between GEF editors. It exists mainly for convenience and allows clients to add graphical objects to the system
 * clipboard. It will not work between two instances of the workbench (but will work between multiple windows belonging to a single instance of the workbench).
 * Setting the contents of the clipboard will erase the previous contents of the clipboard.
 * 
 * Based on the original org.eclipse.gef.ui.actions.Clipboard, but with additional transfer types. Additions by jkealey.
 * 
 * @author Eric Bordeau
 * @author Pratik Shah
 * @author jkealey
 */
public class Clipboard {

    private static Clipboard defaultClipboard = new Clipboard();
    private static final SimpleObjectTransfer TRANSFER = new SimpleObjectTransfer() {
        private final String TYPE_NAME = "seg.JUCMNav.clipboard.transfer"; //$NON-NLS-1$
        private final int TYPE_ID = registerType(TYPE_NAME);

        protected int[] getTypeIds() {
            return new int[] { TYPE_ID };
        }

        protected String[] getTypeNames() {
            return new String[] { TYPE_NAME };
        }
    };

    /**
     * Returns the default clipboard. (singleton)
     * 
     * @return the default clipboard
     */
    public static Clipboard getInstance() {
        return defaultClipboard;
    }

    /**
     * Constructs a new Clipboard object.
     */
    private Clipboard() {
    }

    /**
     * Returns the current contents of the clipboard.
     * 
     * @return contents of the clipboard
     */
    protected Object getContents() {
        org.eclipse.swt.dnd.Clipboard cb = new org.eclipse.swt.dnd.Clipboard(null);
        Object contents = cb.getContents(TRANSFER);
        cb.dispose();
        return contents;
    }

    public URNspec getOriginalURNspec() {
        Object o = getContents();
        if (o != null && o instanceof Object[]) {
            Object[] oarray = (Object[]) o;
            if (oarray.length == 3 && oarray[0] instanceof URNspec) {
                return (URNspec) oarray[0];
            }
        }

        return null;
    }

    public List getSelectedIds() {
        Object o = getContents();
        if (o != null && o instanceof Object[]) {
            Object[] oarray = (Object[]) o;
            if (oarray.length == 3 && oarray[1] instanceof List) {
                return (List) oarray[1];
            }
        }
        return null;
    }

    public List getSelection() {
        Object o = getContents();
        if (o != null && o instanceof Object[]) {
            Object[] oarray = (Object[]) o;
            if (oarray.length == 3 && oarray[2] instanceof List) {
                return (List) oarray[2];
            }
        }
        return null;
    }

    /**
     * Returns the clipboard contents in textual format.
     * 
     * @return the clipboard contents in textual format
     */
    public String getTextualContents() {
        org.eclipse.swt.dnd.Clipboard cb = new org.eclipse.swt.dnd.Clipboard(null);
        Object contents = cb.getContents(TextTransfer.getInstance());
        cb.dispose();

        if (contents != null)
            return contents.toString();
        else
            return null;
    }

    /**
     * Sets the contents of the clipboard. This will erase the previous contents of this as well as the system clipboard. The provided contents will not be
     * garbage-collected until some other contents are set using this method.
     * 
     * @param contents
     *            the new contents
     */
    public void setContents(Object contents, ImageData image) {
        org.eclipse.swt.dnd.Clipboard cb = new org.eclipse.swt.dnd.Clipboard(null);
        String contentString = null;

        // TODO: Improve it so that it supports a better textual representation, if we feel that is important.
        if (contents != null && contents instanceof Object[]) {
            Object[] arr = (Object[]) contents;
            if (arr.length == 4 && arr[2] != null) {
                contentString = arr[2].toString();
            } else
                contentString = contents.toString();
        }

        // if have text in here, it has higher priority than image in ms word.

        // disabled image transfer until CruiseControl runs on 3.4+
        if (image != null)
            cb.setContents(new Object[] { contents, image }, new Transfer[] { TRANSFER, ImageTransfer.getInstance() });
        else
            cb.setContents(new Object[] { contents, contentString }, new Transfer[] { TRANSFER, TextTransfer.getInstance() });
        cb.dispose();
    }

}