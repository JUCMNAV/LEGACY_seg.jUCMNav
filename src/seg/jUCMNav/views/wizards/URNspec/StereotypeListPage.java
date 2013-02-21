package seg.jUCMNav.views.wizards.URNspec;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Button;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.wizards.metadata.MetadataEditorPage;
import seg.jUCMNav.views.wizards.metadata.MetadataEntryDialog;
import seg.jUCMNav.views.wizards.metadata.StereotypeEntryDialog;
import urn.URNspec;
import urncore.Metadata;

/**
 * The page actually containing the metadata editor for urn model elements.
 * 
 * @author Etienne
 */
public class StereotypeListPage extends MetadataEditorPage {
    
    protected Button btImport;
    protected Button btExport;

    public StereotypeListPage(ISelection selection, EObject defaultSelected, EObject ref) {
        super(selection, defaultSelected, ref);
        
        
    }

    @Override
    protected Metadata[] setMetadataArray(Object obj) {
        Metadata[] metadataArray;
        EList metadataList = null;
        if(obj instanceof URNspec)
            metadataList = ((URNspec)obj).getMetadata();
        
        // We just want the StereotypeDef in there for now
        Vector filtered = new Vector();
        for (Iterator i = metadataList.iterator(); i.hasNext();) {
            Metadata meta = (Metadata) i.next();
            
            if(meta.getName().equalsIgnoreCase("StereotypeDef")) //$NON-NLS-1$
                filtered.add(meta);
        }
        
        metadataArray = (Metadata[]) filtered.toArray(new Metadata[0]);
        
        return metadataArray;
    }

    @Override
    protected MetadataEntryDialog createMetadataEntryDialog(String title) {
        MetadataEntryDialog dialog = new StereotypeEntryDialog(shell);
        dialog.setTitle(title);
        dialog.setLabels(new String[] { Messages.getString("StereotypeListPage_Name"), Messages.getString("StereotypeListPage_Value") }); //$NON-NLS-1$ //$NON-NLS-2$
        
        return dialog;
    }

    @Override
    protected void setNewEntryDefaults(MetadataEntryDialog dialog) {
       String[] vals = new String[2];
       vals[0] = "StereotypeDef"; //$NON-NLS-1$
       vals[1] = ""; //$NON-NLS-1$
       dialog.setValues(vals);
    }
    
    
}
