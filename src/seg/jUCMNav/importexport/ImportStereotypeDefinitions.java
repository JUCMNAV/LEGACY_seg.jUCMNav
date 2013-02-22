package seg.jUCMNav.importexport;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.extensionpoints.IURNImport;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.URNNamingHelper;
import urn.URNspec;
import urncore.Metadata;

public class ImportStereotypeDefinitions implements IURNImport {
    
    private Vector<String> warnings = new Vector<String>();

    @Override
    public URNspec importURN(FileInputStream fis, URNspec urn, Vector autolayoutDiagrams) throws InvocationTargetException {
        URNspec urnSpec = (URNspec) EcoreUtil.copy(urn);
        warnings.clear();
        
        InputStreamReader r = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(r);
        
        Vector allMeta = MetadataHelper.getAllMetaData(urnSpec, "StereotypeDef"); //$NON-NLS-1$
        
        try {
            String line = reader.readLine();
            while(line != null && line.length() > 0) {
                
                if(validateLine(allMeta, line)) {
                    Metadata data = (Metadata) ModelCreationFactory.getNewObject(urnSpec, Metadata.class);
                    data.setName("StereotypeDef"); //$NON-NLS-1$
                    data.setValue(line);
                    
                    urnSpec.getMetadata().add(data);
                }
                    
                line = reader.readLine();
            }
            
            String msg = ""; //$NON-NLS-1$
            for (String warning : warnings) {
                msg += warning + System.getProperty("line.separator"); //$NON-NLS-1$
            }

            if(warnings.size() > 0)
                displayMessage(Messages.getString("ImportStereotypeDefinitions.ImportErrors"), msg); //$NON-NLS-1$

            // Sanitize urnspec to resolve naming conflict
            URNNamingHelper.sanitizeURNspec(urnSpec);
            
            return urnSpec;
        } catch (Exception e) {
            throw new InvocationTargetException(e);
        }
        finally {
            try {
                reader.close();
                r.close();
                fis.close();
            } catch (IOException e) {
                throw new InvocationTargetException(e);
            }
        }
    }
    
    protected boolean validateLine(Vector<String> allMeta, String line) {
        String[] values = line.split(","); //$NON-NLS-1$
        
        if(values.length != 3) {
            warnings.add(Messages.getString("ImportStereotypeDefinitions.InvalidLineFormat") + line); //$NON-NLS-1$
            return false;
        }

        for (String meta : allMeta) {
            if(meta.equalsIgnoreCase(line))
            {
                warnings.add(Messages.getString("ImportStereotypeDefinitions.DuplicatedLine") + line); //$NON-NLS-1$
                return false;
            }
        }
        
        return true;
    }
    
    protected void displayMessage(final String title, final String message) {
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                MessageDialog md = new MessageDialog( shell, title, null, message, MessageDialog.WARNING, new String[] { "OK" }, 0 ); //$NON-NLS-1$
                md.create();
                md.open();
            }
        });
    }

    @Override
    public URNspec importURN(FileInputStream fis, Vector autolayoutDiagrams) throws InvocationTargetException {
        // Not used
        return null;
    }

    @Override
    public URNspec importURN(String filename, Vector autolayoutDiagrams) throws InvocationTargetException {
        // Not used
        return null;
    }

    @Override
    public URNspec importURN(String filename, URNspec urn, Vector autolayoutDiagrams) throws InvocationTargetException {
        // not used
        return null;
    }

}
