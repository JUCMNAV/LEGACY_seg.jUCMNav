package seg.jUCMNav.editors;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.Disposable;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.SimpleRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.internal.PopupMenuExtender;
import org.eclipse.ui.part.MultiPageEditorSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectDefaultPaletteToolAction;
import seg.jUCMNav.actions.SetNumericalContributionAction;
import seg.jUCMNav.actions.SetNumericalEvaluationAction;
import seg.jUCMNav.actions.SetNumericalImportanceAction;
import seg.jUCMNav.actions.SetQualitativeContributionAction;
import seg.jUCMNav.actions.SetQualitativeEvaluationAction;
import seg.jUCMNav.actions.SetQualitativeImportanceAction;
import seg.jUCMNav.actions.palette.SelectPaletteEntryAction;
import seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider;
import seg.jUCMNav.editors.palette.UrnDropTargetListener;
import seg.jUCMNav.editors.palette.UrnPaletteViewerProvider;
import seg.jUCMNav.editparts.GraphicalEditPartFactory;
import seg.jUCMNav.editparts.UCMMapEditPart;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.views.dnd.UrnTemplateTransferDropTargetListener;
import seg.jUCMNav.views.outline.UrnOutlinePage;
import urncore.IURNDiagram;

/**
 * This is an abstract class for any urn editor used in UCMNavMultiPageEditor
 * 
 * @author Jean-Francois Roy, jkealey TODO Remove extends to GraphicalEditorWithFlyoutPalette and copy code in this class
 */
public abstract class UrnEditor extends GraphicalEditorWithFlyoutPalette implements ITabbedPropertySheetPageContributor {

    /** the parent containing the action registry */
    protected UCMNavMultiPageEditor parent;

    /** Cache save-request status. */
    private boolean saveAlreadyRequested;

    /** KeyHandler with common bindings for both the Outline View and the Editor. */
    private KeyHandler sharedKeyHandler;

    // our outline page.
    private UrnOutlinePage outline;

    protected URNRootEditPart root = null;

    /** The palette root used to display the palette. */
    protected PaletteRoot paletteRoot;

    public static final String keybindingExcludes = "hnxz"; //$NON-NLS-1$

    protected UrnDropTargetListener dropListener;
    protected UrnTemplateTransferDropTargetListener urnDropListener;
    protected ArrayList menuExtenders;

    /** Create a new UrnEditor instance. */
    public UrnEditor(UCMNavMultiPageEditor parent) {
        this.parent = parent;
        setEditDomain(new UrnEditDomain(this));
    }

    /**
     * Handle events to know when a command was executed. So we can know when we can or cannot save the file.
     */
    public void commandStackChanged(EventObject event) {
        // Note: some actions go directly to this command stack and others go to our parents.

        super.commandStackChanged(event);
        if (isDirty() && !saveAlreadyRequested) {
            saveAlreadyRequested = true;
            firePropertyChange(IEditorPart.PROP_DIRTY);
        } else {
            saveAlreadyRequested = false;
            firePropertyChange(IEditorPart.PROP_DIRTY);
        }
    }

    /**
     * Create all the actions in the action registry.
     * 
     * Now done in multipage.
     * 
     * DO NOT DELETE: SEE SUPERCLASS
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
     */
    protected void createActions() {
        // now done in MultiPage
    }

    /**
     * Disposes the editor.
     */
    public void dispose() {
        // for some reason not in framework.
        getSelectionSynchronizer().removeViewer(getGraphicalViewer());
        getCommandStack().removeCommandStackListener(this);

        disposePalette();

        ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getGraphicalViewer();
        if (viewer != null) {
            Object p = viewer.getRootEditPart();
            if (p instanceof URNRootEditPart) {
                for (Iterator iterator = ((URNRootEditPart) p).getChildren().iterator(); iterator.hasNext();) {
                    EditPart type = (EditPart) iterator.next();
                    if (type instanceof UCMMapEditPart) {
                        UCMMapEditPart part = (UCMMapEditPart) type;
                        part.dispose();
                    }

                }

                ((URNRootEditPart) p).setModel(null);
                ((URNRootEditPart) p).getChildren().clear();
                ((URNRootEditPart) p).refreshChildren();
                ((URNRootEditPart) p).setMultiPageEditor(null);
                ((URNRootEditPart) p).setContents(null);
            }

            if (getGraphicalViewer().getContextMenu() != null) {
                //getGraphicalViewer().getContextMenu().dispose();
                getGraphicalViewer().setContextMenu(null);
                
            }
            if (getGraphicalViewer().getEditDomain() instanceof UrnEditDomain) {
                UrnEditDomain domain = (UrnEditDomain) getGraphicalViewer().getEditDomain();
                domain.dispose();
            }

            if (getGraphicalViewer().getEditPartFactory() instanceof GraphicalEditPartFactory) {
                GraphicalEditPartFactory f = (GraphicalEditPartFactory) getGraphicalViewer().getEditPartFactory();
                f.setRoot(null);
            }
            getGraphicalViewer().setEditPartFactory(null);
            getGraphicalViewer().getEditPartRegistry().clear();

            getEditDomain().removeViewer(getGraphicalViewer());

        }

        // setGraphicalViewer(null);

        if (root != null)
            root.setModel(null);
        root = null;

        outline = null;
        sharedKeyHandler = null;
        dropListener = null;
        urnDropListener = null;

        for (Iterator i = menuExtenders.iterator(); i.hasNext();) {
            Object o = (Object) i.next();
            if (o instanceof PopupMenuExtender) {
                PopupMenuExtender menu = (PopupMenuExtender) menuExtenders.get(0);
                menu.getManager().dispose();
                menu.dispose();
            }
        }

        /*
         * try { if (getEditDomain().getCommandStack()==null) { setEditDomain(new UrnEditDomain(this));
         * 
         * disposePalette(); }
         * 
         * super.dispose(); } catch (NullPointerException ex) {
         * 
         * }
         */
        parent = null;

        if (getSite() instanceof MultiPageEditorSite) {
            MultiPageEditorSite site = (MultiPageEditorSite) getSite();
            site.dispose();
        }
        viewer.setRootEditPart(new SimpleRootEditPart());

        setGraphicalViewer(new ScrollingGraphicalViewer());

    }

    private void disposePalette() {
        if (getEditDomain().getPaletteViewer() != null) {

            if (getPaletteViewerProvider() instanceof UrnPaletteViewerProvider) {
                UrnPaletteViewerProvider p = (UrnPaletteViewerProvider) getPaletteViewerProvider();
                p.unconfigurePaletteViewer(getEditDomain().getPaletteViewer());

            }
            getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);

            getEditDomain().getPaletteViewer().getEditPartRegistry().clear();
            getEditDomain().getPaletteViewer().setKeyHandler(null);
            getEditDomain().getPaletteViewer().setControl(null);
            getEditDomain().getPaletteViewer().setPaletteRoot(null);

            // getEditDomain().setPaletteViewer(new PaletteViewer());
        }

        getGraphicalViewer().setKeyHandler(null);
        getEditorSite().setSelectionProvider(null);

        if (paletteRoot instanceof Disposable) {
            ((Disposable) paletteRoot).dispose();
        }

        paletteRoot = null;
    }

    /**
     * Used when UcmEditor was the root model element.
     * 
     * @deprecated
     */
    public void doSave(IProgressMonitor monitor) {
        System.out.println(Messages.getString("UcmEditor.oldSave")); //$NON-NLS-1$
    }

    /**
     * Used when UcmEditor was the root model element.
     * 
     * @deprecated
     */
    public void doSaveAs() {
        System.out.println(Messages.getString("UcmEditor.oldSaveAs")); //$NON-NLS-1$
    }

    /**
     * Execute a command in the parent's command stack.
     * 
     * @param cmd
     */
    public void execute(Command cmd) {
        parent.getDelegatingCommandStack().execute(cmd);
    }

    /**
     * Returns a reference to the multi page action registry.
     */
    protected ActionRegistry getActionRegistry() {
        // one action registry for all editors
        return parent.getActionRegistry();
    }

    /**
     * Returns the adapter for the specified key. Such as the property sheet, the outline view etc.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class type) {
        if (type == ZoomManager.class)
            return getGraphicalViewer().getProperty(ZoomManager.class.toString());
        else if (type == ActionRegistry.class)
            return getActionRegistry();
        else if (type == IContentOutlinePage.class)
            return getOutlinePage();
        else if (type == org.eclipse.ui.views.properties.IPropertySheetPage.class) {
            return new TabbedPropertySheetPage(this);
            // page.setRootEntry(new UndoablePropertySheetEntry(getParent().getDelegatingCommandStack()));
            // return page;
        }

        return super.getAdapter(type);
    }

    /**
     * Returns the KeyHandler with common bindings for both the Outline and Graphical Views. For example, delete is a common action.
     */
    KeyHandler getCommonKeyHandler() {
        char character;

        if (sharedKeyHandler == null) {
            sharedKeyHandler = new KeyHandler();

            // Add key and action pairs to sharedKeyHandler
            sharedKeyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));

            sharedKeyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));

            sharedKeyHandler.put(KeyStroke.getPressed((char) 1, 'a', SWT.CTRL), getActionRegistry().getAction(ActionFactory.SELECT_ALL.getId()));

            sharedKeyHandler.put(KeyStroke.getReleased(SWT.ESC, SWT.ESC, 0), getActionRegistry()
                    .getAction(SelectDefaultPaletteToolAction.SETDEFAULTPALETTETOOL));

            for (int letter = (int) 'a'; letter <= (int) 'z'; letter++) {
                if (keybindingExcludes.indexOf((char) letter) != -1) // reserve some keys for other uses
                    continue;

                sharedKeyHandler.put(KeyStroke.getReleased((char) letter, (char) letter, 0), getActionRegistry().getAction(
                        SelectPaletteEntryAction.getId((char) letter)));
                sharedKeyHandler.put(KeyStroke.getReleased((char) (letter - 32), (char) letter, SWT.SHIFT), getActionRegistry().getAction(
                        SelectPaletteEntryAction.getId((char) letter)));

                // doesn't seem to work - not important. (caps)
                sharedKeyHandler.put(KeyStroke.getReleased((char) (letter - 32), (char) letter, SWT.CAPS_LOCK), getActionRegistry().getAction(
                        SelectPaletteEntryAction.getId((char) letter)));
            }
            
            for (int letter = (int) '0'; letter <= (int) '9'; letter++) {
                if (keybindingExcludes.indexOf((char) letter) != -1) // reserve some keys for other uses
                    continue;

                sharedKeyHandler.put(KeyStroke.getReleased((char) letter, (char) letter, 0), getActionRegistry().getAction(
                        SelectPaletteEntryAction.getId((char) letter)));
            }            

            character = '>';
            sharedKeyHandler.put(KeyStroke.getReleased(character, '.', SWT.SHIFT), getActionRegistry().getAction(SelectPaletteEntryAction.getId(character)));
            character = ' ';
            sharedKeyHandler.put(KeyStroke.getReleased(character, character, 0), getActionRegistry().getAction(SelectPaletteEntryAction.getId(character)));
            character = '-';
            sharedKeyHandler.put(KeyStroke.getReleased(character, character, 0), getActionRegistry().getAction(SelectPaletteEntryAction.getId(character)));
            
            character = 'h'; // increase Qualitative Evaluation with key binding
            sharedKeyHandler.put(KeyStroke.getReleased(character, character, 0), getActionRegistry()
                    .getAction(SetQualitativeEvaluationAction.getId("Increase"))); //$NON-NLS-1$
            character = 'n'; // decrease Qualitative Evaluation with key binding
            sharedKeyHandler.put(KeyStroke.getReleased(character, character, 0), getActionRegistry()
                    .getAction(SetQualitativeEvaluationAction.getId("Decrease"))); //$NON-NLS-1$

            int letter = (int) 'h'; // increase Numerical Evaluation with key binding
            sharedKeyHandler.put(KeyStroke.getReleased((char) (letter - 32), (char) letter, SWT.SHIFT), getActionRegistry().getAction(
                    SetNumericalEvaluationAction.getId("Increase"))); //$NON-NLS-1$
            letter = (int) 'n'; // decrease Numerical Evaluation with key binding
            sharedKeyHandler.put(KeyStroke.getReleased((char) (letter - 32), (char) letter, SWT.SHIFT), getActionRegistry().getAction(
                    SetNumericalEvaluationAction.getId("Decrease"))); //$NON-NLS-1$

            character = 'x'; // increase Qualitative Importance with key binding
            sharedKeyHandler.put(KeyStroke.getReleased(character, character, 0), getActionRegistry()
                    .getAction(SetQualitativeImportanceAction.getId("Increase"))); //$NON-NLS-1$
            character = 'z'; // decrease Qualitative Importance with key binding
            sharedKeyHandler.put(KeyStroke.getReleased(character, character, 0), getActionRegistry()
                    .getAction(SetQualitativeImportanceAction.getId("Decrease"))); //$NON-NLS-1$

            letter = (int) 'x'; // increase Numerical Importance with key binding
            sharedKeyHandler.put(KeyStroke.getReleased((char) (letter - 32), (char) letter, SWT.SHIFT), getActionRegistry().getAction(
                    SetNumericalImportanceAction.getId("Increase"))); //$NON-NLS-1$
            letter = (int) 'z'; // decrease Numerical Importance with key binding
            sharedKeyHandler.put(KeyStroke.getReleased((char) (letter - 32), (char) letter, SWT.SHIFT), getActionRegistry().getAction(
                    SetNumericalImportanceAction.getId("Decrease"))); //$NON-NLS-1$

            character = 'y'; // increase Qualitative Contribution with key binding
            sharedKeyHandler.put(KeyStroke.getReleased(character, character, 0), getActionRegistry()
                    .getAction(SetQualitativeContributionAction.getId("Increase"))); //$NON-NLS-1$
            character = 'u'; // decrease Qualitative Contribution with key binding
            sharedKeyHandler.put(KeyStroke.getReleased(character, character, 0), getActionRegistry()
                    .getAction(SetQualitativeContributionAction.getId("Decrease"))); //$NON-NLS-1$

            letter = (int) 'y'; // increase Numerical Contribution with key binding
            sharedKeyHandler.put(KeyStroke.getReleased((char) (letter - 32), (char) letter, SWT.SHIFT), getActionRegistry().getAction(
                    SetNumericalContributionAction.getId("Increase"))); //$NON-NLS-1$
            letter = (int) 'u'; // decrease Numerical Contribution with key binding
            sharedKeyHandler.put(KeyStroke.getReleased((char) (letter - 32), (char) letter, SWT.SHIFT), getActionRegistry().getAction(
                    SetNumericalContributionAction.getId("Decrease"))); //$NON-NLS-1$

        }
        return sharedKeyHandler;
    }

    /**
     * Overridden to change to public visibility.
     */
    public abstract CommandStack getCommandStack();

    /**
     * Overriden to allow external access.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#getEditDomain()
     */
    public DefaultEditDomain getEditDomain() {
        return super.getEditDomain();
    }

    /**
     * Overriden to allow external access.
     */
    public GraphicalViewer getGraphicalViewer() {
        return super.getGraphicalViewer();
    }

    /**
     * Return the model of this editor
     * 
     * @return The model of this editor
     */
    public abstract IURNDiagram getModel();

    /**
     * Set the model of this editor
     * 
     */

    /**
     * @return the outline associated with this editor
     */
    public UrnOutlinePage getOutlinePage() {
        if (outline == null)
            outline = new UrnOutlinePage(getParent(), new TreeViewer());
        return outline;
    }

    public abstract void setModel(IURNDiagram m);

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPalettePreferences()
     */
    protected abstract FlyoutPreferences getPalettePreferences();

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
     */
    public abstract PaletteRoot getPaletteRoot();

    /**
     * Returns this editor's container.
     * 
     * @return parent multi-page editor
     */
    public UCMNavMultiPageEditor getParent() {
        return parent;
    }

    /**
     * Returns the selection syncronizer object. The synchronizer can be used to sync the selection of 2 or more EditPartViewers.
     * 
     * @return the syncrhonizer
     */
    protected SelectionSynchronizer getSelectionSynchronizer() {
        return parent.getSelectionSynchronizer();
    }

    /**
     * Return true if the editor contains any changes.
     * 
     * @see org.eclipse.ui.ISaveablePart#isDirty()
     */
    public boolean isDirty() {
        return getCommandStack().isDirty();
    }

    /**
     * Save is always allowed, even though it won't happen here.
     * 
     * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
     */
    public boolean isSaveAsAllowed() {
        return true;
    }

    protected void registerContextMenuProvider(ScrollingGraphicalViewer viewer) {
        ContextMenuProvider provider = new UrnContextMenuProvider(viewer, getActionRegistry());
        viewer.setContextMenu(provider);

        // Bug 381: 3.1: remove extra items from contextual menus
        // getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider", provider, viewer); //$NON-NLS-1$
        menuExtenders = new ArrayList(1);
        
        PartSite.registerContextMenu("seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider", provider, viewer, true, //$NON-NLS-1$
                	// DB: Fix for internal API removed from E4.2.
                this, ( (PartSite)( (MultiPageEditorSite) getSite() ).getMultiPageEditor().getSite() ).getContext(), menuExtenders);
        // bug 531
        if (menuExtenders.get(0) != null) {
            provider.removeMenuListener((IMenuListener) menuExtenders.get(0));

        }
    }

    /**
     * Redo's the command at the top of the parent's redo stack.
     * 
     */
    public void redo() {
        parent.getDelegatingCommandStack().redo();
    }

    /**
     * Undo's the command at the top of the parent's undo stack.
     * 
     */
    public void undo() {
        parent.getDelegatingCommandStack().undo();
    }

    /**
     * Allows dragging from the palette into the editor.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPaletteViewerProvider()
     */
    protected PaletteViewerProvider createPaletteViewerProvider() {
        return new UrnPaletteViewerProvider(getEditDomain());
    }

    /**
     * Create a transfer drop target listener. When using a CombinedTemplateCreationEntry tool in the palette, this will enable model element creation by
     * dragging from the palette.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPaletteViewerProvider()
     */
    protected TransferDropTargetListener getTransferDropTargetListener() {
        if (dropListener == null)
            dropListener = new UrnDropTargetListener(getGraphicalViewer(), getModel().getUrndefinition().getUrnspec());
        return dropListener;
    }

    protected UrnTemplateTransferDropTargetListener getUrnTransferDropTargetListener() {
        if (urnDropListener == null)
            urnDropListener = new UrnTemplateTransferDropTargetListener(this);
        return urnDropListener;
    }

    public String getContributorId() {
        return getSite().getId();
    }
}
