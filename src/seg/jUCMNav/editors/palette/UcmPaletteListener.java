package seg.jUCMNav.editors.palette;

import org.eclipse.gef.palette.PaletteListener;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.PaletteViewer;

/**
 * Created 2005-02-25
 * 
 * This class can we used to change the palette depending on the selected tool.
 * 
 * @author Etienne Tremblay
 */
public class UcmPaletteListener implements PaletteListener {

	/**
	 * This code will change the default tool to the active tool.  So we can continue to use our tool without
	 * having to select it again.
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.palette.PaletteListener#activeToolChanged(org.eclipse.gef.ui.palette.PaletteViewer, org.eclipse.gef.palette.ToolEntry)
	 */
	public void activeToolChanged(PaletteViewer palette, ToolEntry tool) {
		UcmPaletteRoot root = (UcmPaletteRoot)palette.getPaletteRoot();
		
		ToolEntry active = palette.getActiveTool();
		if(active.getId() == "EmptyPoint" || active.getId() == "Responsibility") //$NON-NLS-1$ //$NON-NLS-2$
			root.setDefaultEntry(active);
		else if(active.getId() == "StartPoint" || active.getId() == "EndPoint") //$NON-NLS-1$ //$NON-NLS-2$
		{
			ToolEntry end = root.getEndPointTool();
			root.setDefaultEntry(end);
		}
		else
		{
			root.setDefaultEntry(active);
		}
	}

}
