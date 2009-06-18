package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.swt.events.KeyEvent;

import seg.jUCMNav.editors.palette.UcmPaletteRoot;

/**
 * Base creation tool. Forwards keystrokes when palette is selected. 
 * 
 * 
 * @author jkealey
 *
 */
public class BaseCreationTool extends CreationTool
{
	
	public BaseCreationTool() { }
	
    public BaseCreationTool(CreationFactory factory) {
        super(factory);
    }
        
    protected boolean handleKeyDown(KeyEvent e)
    {
    	boolean s = super.handleKeyDown(e);
    	
    	if (getDomain().getPaletteViewer().getPaletteRoot() instanceof UcmPaletteRoot) {
    		UcmPaletteRoot root = (UcmPaletteRoot)getDomain().getPaletteViewer().getPaletteRoot();
    		ToolEntry entry = root.getAssociatedTool(("" + e.character).toLowerCase());
    		if (entry!=null) { 
    			getDomain().getPaletteViewer().setActiveTool(entry);
    			return true;
    		}
    	}
    	return false;
    }
    

}
