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

import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.palette.tools.URNElementCreationEntry;
import seg.jUCMNav.model.ModelCreationFactory;

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
        // a group of default control tools
        PaletteGroup controls = new PaletteGroup(Messages.getString("GrlPaletteRoot.controls")); //$NON-NLS-1$
        add(controls);

        // the selection tool; default tool
        ToolEntry tool = new SelectionToolEntry();
        controls.add(tool);
        setDefaultEntry(tool);

        ToolEntry entry;

        PaletteDrawer linksDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.links")); //$NON-NLS-1$

        // Add a decomposition
        entry = new ConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.decomposition"), Messages.getString("GrlPaletteRoot.createDecomposition"), new ModelCreationFactory(getURNspec(), Decomposition.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/Decomposition16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Decomposition24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);

        // Add a contribution
        entry = new ConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.contribution"), Messages.getString("GrlPaletteRoot.createContribution"), new ModelCreationFactory(getURNspec(), Contribution.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/Contribution16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Softgoal24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);

        // Add a dependency
        entry = new ConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.dependency"), Messages.getString("GrlPaletteRoot.createDependency"), new ModelCreationFactory(getURNspec(), Dependency.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/Dependency16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Dependency24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);

        // Add belief link
        entry = new ConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.beliefLink"), Messages.getString("GrlPaletteRoot.createBeliefLink"), new ModelCreationFactory(getURNspec(), BeliefLink.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/BeliefLink16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/BeliefLink24.gif")); //$NON-NLS-1$
        linksDrawer.add(entry);

        add(linksDrawer);

        PaletteDrawer componentsDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.components")); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.actor"), Messages.getString("GrlPaletteRoot.createActor"), ActorRef.class, new ModelCreationFactory(getURNspec(), ActorRef.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/GRLActor16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/GRLActor24.gif")); //$NON-NLS-1$
        componentsDrawer.add(entry);

        add(componentsDrawer);

        PaletteDrawer elementsDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.elements")); //$NON-NLS-1$

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.softgoal"), Messages.getString("GrlPaletteRoot.createSoftgoal"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.SOFTGOAL), JUCMNavPlugin.getImageDescriptor("icons/Softgoal16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Softgoal24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.goal"), Messages.getString("GrlPaletteRoot.createGoal"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.GOAL), JUCMNavPlugin.getImageDescriptor("icons/Goal16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Goal24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.task"), Messages.getString("GrlPaletteRoot.createTask"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.TASK), JUCMNavPlugin.getImageDescriptor("icons/Task16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Task24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.resource"), Messages.getString("GrlPaletteRoot.createResource"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.RESSOURCE), JUCMNavPlugin.getImageDescriptor("icons/Resource16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Resource24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);

        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.belief"), Messages.getString("GrlPaletteRoot.createBelief"), Belief.class, new ModelCreationFactory(getURNspec(), Belief.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/Belief16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Belief24.gif")); //$NON-NLS-1$
        elementsDrawer.add(entry);

        add(elementsDrawer);

        // Add KPI model elements and links
        PaletteDrawer kpiModelDrawer = new PaletteDrawer(Messages.getString("GrlPaletteRoot.kpiModel")); //$NON-NLS-1$

        // Add Indicator
        entry = new URNElementCreationEntry(
                Messages.getString("GrlPaletteRoot.indicator"), Messages.getString("GrlPaletteRoot.createIndicator"), IntentionalElementRef.class, new ModelCreationFactory(getURNspec(), IntentionalElementRef.class, //$NON-NLS-1$ //$NON-NLS-2$
                        IntentionalElementType.INDICATOR), JUCMNavPlugin.getImageDescriptor("icons/Indicator16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Indicator24.gif")); //$NON-NLS-1$
        kpiModelDrawer.add(entry);

        // Add Dimension
        entry = new URNElementCreationEntry(Messages.getString("GrlPaletteRoot.dimension"), Messages.getString("GrlPaletteRoot.createDimension"),
                KPIInformationElementRef.class, new ModelCreationFactory(getURNspec(), KPIInformationElementRef.class), JUCMNavPlugin
                        .getImageDescriptor("icons/Dimension16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/Dimension24.gif")); //$NON-NLS-1$
        kpiModelDrawer.add(entry);

        // Add KPIModelLink
        entry = new ConnectionCreationToolEntry(
                Messages.getString("GrlPaletteRoot.kpiModelLink"), Messages.getString("GrlPaletteRoot.createKPIModelLink"), new ModelCreationFactory(getURNspec(), KPIModelLink.class), //$NON-NLS-1$ //$NON-NLS-2$
                JUCMNavPlugin.getImageDescriptor("icons/KPIModelLink16.gif"), ImageDescriptor.createFromFile( //$NON-NLS-1$
                        JUCMNavPlugin.class, "icons/KPIModelLink24.gif")); //$NON-NLS-1$
        kpiModelDrawer.add(entry);

        add(kpiModelDrawer);

    }

}
