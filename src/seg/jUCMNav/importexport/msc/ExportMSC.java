package seg.jUCMNav.importexport.msc;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.extensionpoints.IURNExportPrePostHooks;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.importexport.scenariosTools.ExportScenarios;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.views.preferences.ScenarioExportPreferences;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;

public class ExportMSC extends ExportScenarios implements IURNExport, IURNExportPrePostHooks {


    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {

        // filename always ends with jucmscenarios
        if (!ScenarioExportPreferences.getExportType().equalsIgnoreCase("0")) //$NON-NLS-1$
            filename = filename.substring(0, filename.length() - "jucmscenarios".length()) + "jucm"; //$NON-NLS-1$ //$NON-NLS-2$

        if (mapDiagrams.values().size() == 0)
            return;

        if (!scenarioDefExists(urn)) { // No scenario definition. Avoid Invalid thread access exception.
            jUCMNavErrorDialog warningMessage = new jUCMNavErrorDialog(Messages.getString("ExportMSC.NoScenarioDefined")); //$NON-NLS-1$
            return;
        }
        this.newFilename = filename;

        Vector v = new Vector();
        // TODO: find original filename
        v.add(new MscTraversalListener(this.oldFilename, this.newFilename, ScenarioExportPreferences.getExportType()));

        if (ScenarioExportPreferences.getExportAll().equalsIgnoreCase("all")) //$NON-NLS-1$
            ScenarioUtils.setActiveScenario(urn.getUcmspec(), v);
        else {
            EObject eo = ScenarioUtils.getActiveScenario(urn);

            if (eo instanceof ScenarioDef)
                ScenarioUtils.setActiveScenario((ScenarioDef) eo, v);
            else if (eo instanceof ScenarioGroup)
                ScenarioUtils.setActiveScenario((ScenarioGroup) eo, v);
            else
                ScenarioUtils.setActiveScenario(urn.getUcmspec(), v);
        }
    }

}
