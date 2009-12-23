package seg.jUCMNav.editors.palette;

import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.palette.tools.BaseConnectionCreationToolEntry;
import seg.jUCMNav.editors.palette.tools.URNElementCreationEntry;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import urncore.Comment;

/**
 * This is the GRLEditor palette.
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class GrlPaletteRoot extends UcmPaletteRoot {

    /**
     * Creates a new GrlPaletteRoot.
     */
    public GrlPaletteRoot(UCMNavMultiPageEditor parent) {
        super(parent);
    }

    /**
     * Builds the palette entries.
     */
    protected void buildPalette() {
        keyboardMapping = new HashMap();
        // a group of default control tools
        PaletteGroup controls = new PaletteGroup(Messages.getString("GrlPaletteRoot.controls")); //$NON-NLS-1$
        add(controls);

        // the selection tool; default tool
        ToolEntry tool = new SelectionToolEntry();
        controls.add(tool);
        setDefaultEntry(tool);

        ToolEntry entry = new URNElementCreationEntry(
                "Comment", Messages.getString("GrlPaletteRoot.CreateAComment"), Comment.class, new ModelCreationFactory(getURNspec(), Comment.class), JUCMNavPlugin.getImageDescriptor("icons/Comment16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                                JUCMNavPlugin.class, "icons/Comment24.gif")); //$NON-NLS-1$
        controls.add(entry);
        keyboardMapping.put("q", entry); //$NON-NLS-1$

        PaletteDrawer linksDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.links")); //$NON-NLS-1$

        // Add a decomposition
        entry = new BaseConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.decomposition"), Messages.getString("GrlPaletteRoot.createDecomposition"), new ModelCreationFactory(getURNspec(), Decomposition.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/Decomposition16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Decomposition24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);
        keyboardMapping.put("d", entry); //$NON-NLS-1$

        // Add a contribution
        entry = new BaseConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.contribution"), Messages.getString("GrlPaletteRoot.createContribution"), new ModelCreationFactory(getURNspec(), Contribution.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/Contribution16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Contribution24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);
        keyboardMapping.put("c", entry); //$NON-NLS-1$

        // Add a dependency
        entry = new BaseConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.dependency"), Messages.getString("GrlPaletteRoot.createDependency"), new ModelCreationFactory(getURNspec(), Dependency.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/Dependency16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Dependency24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);
        keyboardMapping.put("p", entry); //$NON-NLS-1$

        // Add belief link
        entry = new BaseConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.beliefLink"), Messages.getString("GrlPaletteRoot.createBeliefLink"), new ModelCreationFactory(getURNspec(), BeliefLink.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/BeliefLink16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/BeliefLink24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);
        keyboardMapping.put("l", entry); //$NON-NLS-1$

        add(linksDrawer);

        PaletteDrawer componentsDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.components")); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.actor"), Messages.getString("GrlPaletteRoot.createActor"), ActorRef.class, new ModelCreationFactory(getURNspec(), ActorRef.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/GRLActor16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/GRLActor24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        add(componentsDrawer);
        keyboardMapping.put("a", entry); //$NON-NLS-1$

        PaletteDrawer elementsDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.elements")); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.softgoal"), Messages.getString("GrlPaletteRoot.createSoftgoal"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.SOFTGOAL), JUCMNavPlugin.getImageDescriptor("icons/Softgoal16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Softgoal24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);
        keyboardMapping.put("s", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.goal"), Messages.getString("GrlPaletteRoot.createGoal"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.GOAL), JUCMNavPlugin.getImageDescriptor("icons/Goal16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Goal24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);
        keyboardMapping.put("g", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.task"), Messages.getString("GrlPaletteRoot.createTask"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.TASK), JUCMNavPlugin.getImageDescriptor("icons/Task16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Task24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);
        keyboardMapping.put("t", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.resource"), Messages.getString("GrlPaletteRoot.createResource"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.RESSOURCE), JUCMNavPlugin.getImageDescriptor("icons/Resource16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Resource24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);
        keyboardMapping.put("r", entry); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.belief"), Messages.getString("GrlPaletteRoot.createBelief"), Belief.class, new ModelCreationFactory(getURNspec(), Belief.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/Belief16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Belief24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);
        keyboardMapping.put("b", entry); //$NON-NLS-1$

        add(elementsDrawer);

        // Only add KPI to the palette if we show advanced options
        if (DisplayPreferences.getInstance().isAdvancedControlEnabled() || !DisplayPreferences.getInstance().isShowKPI())
            addKpi();
    }

    /**
     * Add KPI entries to the palette
     */
    public void addKpi() {
        if (!contains(Messages.getString("GrlPaletteRoot.kpiModel"))) { //$NON-NLS-1$
            ToolEntry entry;
            // Add KPI model elements and links
            PaletteDrawer kpiModelDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.kpiModel")); //$NON-NLS-1$

            // Add Indicator
            entry = new URNElementCreationEntry(
                    Messages.getString("GrlPaletteRoot.indicator"), Messages.getString("GrlPaletteRoot.createIndicator"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                            IntentionalElementType.INDICATOR), JUCMNavPlugin.getImageDescriptor("icons/Indicator16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                            JUCMNavPlugin.class, "icons/Indicator24.gif")); //$NON-NLS-1$
            kpiModelDrawer.add(entry);
            keyboardMapping.put("i", entry); //$NON-NLS-1$

            // Add Dimension
            entry = new URNElementCreationEntry(Messages.getString("GrlPaletteRoot.dimension"), Messages.getString("GrlPaletteRoot.createDimension"), //$NON-NLS-1$ //$NON-NLS-2$
                    KPIInformationElementRef.class, new ModelCreationFactory(getURNspec(), KPIInformationElementRef.class), JUCMNavPlugin
                            .getImageDescriptor("icons/Dimension16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                            JUCMNavPlugin.class, "icons/Dimension24.gif")); //$NON-NLS-1$
            kpiModelDrawer.add(entry);
            keyboardMapping.put("j", entry); //$NON-NLS-1$

            // Add KPIModelLink
            entry = new BaseConnectionCreationToolEntry(
                    Messages.getString("GrlPaletteRoot.kpiModelLink"), Messages.getString("GrlPaletteRoot.createKPIModelLink"), new ModelCreationFactory(getURNspec(), KPIModelLink.class), //$NON-NLS-1$ //$NON-NLS-2$
                    JUCMNavPlugin.getImageDescriptor("icons/KPIModelLink16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                            JUCMNavPlugin.class, "icons/KPIModelLink24.gif")); //$NON-NLS-1$
            kpiModelDrawer.add(entry);
            keyboardMapping.put("k", entry); //$NON-NLS-1$

            add(kpiModelDrawer);
        }
    }

    /**
     * Remove KPI entries from the palette if there's already one present
     */
    public void removeKpi() {
        PaletteEntry kpi = getEntry(Messages.getString("GrlPaletteRoot.kpiModel")); //$NON-NLS-1$
        if (kpi != null)
            remove(kpi);
    }

    /**
     * Does a palette entry exists with this label
     * 
     * @param name
     * @return
     */
    protected boolean contains(String name) {
        return getEntry(name) != null;
    }

    /**
     * Find a palette entry from a label name
     * 
     * @param label
     * @return
     */
    protected PaletteEntry getEntry(String label) {
        for (Iterator iterator = getChildren().iterator(); iterator.hasNext();) {
            Object o = (Object) iterator.next();
            if (o instanceof PaletteDrawer) {
                if (((PaletteDrawer) o).getLabel().equals(label))
                    return (PaletteDrawer) o;
            }
        }
        return null;
    }
}
