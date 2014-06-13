package seg.jUCMNav.views.property.tabbed.sections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.metadata.ChangeMetadataCommand;
import seg.jUCMNav.views.property.MetadataRefResolver;
import seg.jUCMNav.views.property.StackHelper;
import seg.jUCMNav.views.wizards.metadata.IMetadataListener;
import seg.jUCMNav.views.wizards.metadata.MetadataEditorPage;
import urncore.Metadata;

public class MetadataPropertySection extends AbstractWizardPropertySection {

    protected MetadataRefResolver resolver = new MetadataRefResolver();
    private EObject currentObj;
    private EObject initialObject;

    protected IWizardPage createPage(Composite parent) {
        IStructuredSelection sel = resolver.adjustSelection(propertySheetPage.getSelection(), eObject);
        EObject obj = resolver.getRealObject(eObject);
        initialObject = getUnresolvedSelection(propertySheetPage.getSelection());
        
        final MetadataEditorPage page = createMetadataEditorPage(sel, obj, initialObject);
        page.setInProperties(true);
        page.addMetadataListener(new IMetadataListener() {
            public void metadataChanged() {
                MetadataPropertySection.this.metadataChanged(page);
            }
        });

        page.createControl(parent);

        return page;
    }

    protected MetadataEditorPage createMetadataEditorPage(IStructuredSelection sel, EObject obj, EObject initialObject) {    	
        return new MetadataEditorPage(sel, obj, initialObject != obj ? initialObject : null);
    }
    
    protected void metadataChanged(final MetadataEditorPage page) {
        final HashMap metadataMap = ((MetadataEditorPage) page).getAllMetadata();
        if (metadataMap.keySet().size() > 0) {
            CommandStack cs = StackHelper.getStack(propertySheetPage);
            if (cs != null) {
                CompoundCommand cmd = new CompoundCommand();
                for (Iterator iter = metadataMap.entrySet().iterator(); iter.hasNext();) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    cmd.add(new ChangeMetadataCommand((EObject) entry.getKey(), (Metadata[]) entry.getValue(), null));
                }

                if (cmd.canExecute()) {
                    cs.execute(cmd);
                }
            }
        }
    }

    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);

        currentObj = resolver.getRealObject(eObject);
        initialObject = getUnresolvedSelection(selection);

        
        ((MetadataEditorPage) page).setData(resolver.adjustSelection(null, eObject), currentObj, initialObject != currentObj ? initialObject : null);
        ((MetadataEditorPage) page).updateUI(true);
    }

    public void refresh() {
        super.refresh();

        ((MetadataEditorPage) page).setData(resolver.adjustSelection(null, eObject), currentObj, initialObject != currentObj ? initialObject : null);
        ((MetadataEditorPage) page).updateUI(false);
    }

    public String getLabelText() {
        return ""; //$NON-NLS-1$
    }
    
    protected EObject getUnresolvedSelection(ISelection selection) {
        if(selection == null)
            return null;
        
        Vector list = new Vector();
        list = buildObjectList(selection, false);
        if (list.size() > 0) {
            return (EObject) list.get(0);
        } else
            return null;
    }
}
