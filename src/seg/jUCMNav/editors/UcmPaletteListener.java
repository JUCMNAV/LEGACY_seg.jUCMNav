/*
 * Created on 2005-02-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editors;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.palette.PaletteListener;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.ui.palette.PaletteViewer;

/**
 * Created 2005-02-25
 * 
 * This class can we used to change the palette depending on the selected tool.
 * 
 * @author Etienne Tremblay
 */
public class UcmPaletteListener implements PaletteListener {

	/*
	 * This code will change the default tool to the active tool.  So we can continue to use our tool without
	 * having to select it again.
	 *  (non-Javadoc)
	 * @see org.eclipse.gef.palette.PaletteListener#activeToolChanged(org.eclipse.gef.ui.palette.PaletteViewer, org.eclipse.gef.palette.ToolEntry)
	 */
	public void activeToolChanged(PaletteViewer palette, ToolEntry tool) {
		EditDomain domain = palette.getEditDomain();
		domain.getDefaultTool();
		AbstractTool active = (AbstractTool)domain.getActiveTool();
		palette.getPaletteRoot().setDefaultEntry(tool);		
	}

}
