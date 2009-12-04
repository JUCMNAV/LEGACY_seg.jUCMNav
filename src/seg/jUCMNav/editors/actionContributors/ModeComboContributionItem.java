package seg.jUCMNav.editors.actionContributors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.URNRootEditPart;

/**
 * Creates a combo box to be used in the toolbars to select a view mode for a use case map editor. Complete rip of ZoomComboContributionItem, with a few
 * changes.
 * 
 * @author jkealey
 */
public class ModeComboContributionItem extends ContributionItem {

    private Combo combo;
    private String[] initStrings;
    private ToolItem toolitem;
    private IWorkbenchPage service;
    private IPartListener partListener;

    // should mode be local to current editor only?
    private static boolean local = false;

    // when local to editor
    // private URNRootEditPart part;

    // when global to all editors
    // private UCMNavMultiPageEditor editor;

    /**
     * Constructor for ComboToolItem.
     * 
     * @param partService
     *            used to add a PartListener
     * @param initStrings
     *            the initial string displayed in the combo
     */
    public ModeComboContributionItem(IWorkbenchPage partService, String[] initStrings) {
        super("seg.jUCMNav.ModeComboContributionItem"); //$NON-NLS-1$
        this.initStrings = initStrings;
        // this.part = part;
        service = partService;
        Assert.isNotNull(partService);
        partService.addSelectionListener(new ISelectionListener() {
            public void selectionChanged(IWorkbenchPart part, ISelection selection) {
                if (part instanceof UCMNavMultiPageEditor && ((UCMNavMultiPageEditor) part).getActivePage() >= 0) {
                    refresh(true, (UCMNavMultiPageEditor) part);
                }
            }
        });

        partService.addPartListener(partListener = new IPartListener() {
            public void partActivated(IWorkbenchPart part) {
                if (part instanceof UCMNavMultiPageEditor && ((UCMNavMultiPageEditor) part).getActivePage() >= 0) {
                    refresh(true, (UCMNavMultiPageEditor) part);
                }
            }

            public void partBroughtToTop(IWorkbenchPart p) {
            }

            public void partClosed(IWorkbenchPart p) {
            }

            public void partDeactivated(IWorkbenchPart p) {
            }

            public void partOpened(IWorkbenchPart p) {
            }
        });
    }

    void refresh(boolean repopulateCombo) {
        UCMNavMultiPageEditor editor = getActiveEditor();
        refresh(repopulateCombo, editor);
    }

    private UCMNavMultiPageEditor getActiveEditor() {
        UCMNavMultiPageEditor editor = null;
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        }
        return editor;
    }

    /**
     * Refreshes the combo box.
     * 
     * @param repopulateCombo
     *            should the combo box be rebuild from scratch
     */
    void refresh(boolean repopulateCombo, UCMNavMultiPageEditor editor) {
        if (combo == null || combo.isDisposed())
            return;
        // $TODO GTK workaround
        try {
            if (getRootEditpart(editor) == null) {
                combo.setEnabled(false);
                combo.setText(""); //$NON-NLS-1$
            } else {
                if (repopulateCombo) {
                    combo.setItems(initStrings);
                }
                int index = getRootEditpart(editor).getMode();
                if (index >= 0 && index <= combo.getItemCount())
                    combo.select(index);
                else
                    combo.setText(""); //$NON-NLS-1$
                combo.setEnabled(true);
            }
        } catch (SWTException exception) {
            if (!SWT.getPlatform().equals("gtk")) //$NON-NLS-1$
                throw exception;
        }
    }

    public URNRootEditPart getRootEditpart(UCMNavMultiPageEditor editor) {
        if (editor == null)
            return null;
        return (URNRootEditPart) editor.getCurrentPage().getGraphicalViewer().getRootEditPart();
    }

    /**
     * Computes the width required by control
     * 
     * @param control
     *            The control to compute width
     * @return int The width required
     */
    protected int computeWidth(Control control) {
        int width = control.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x;
        // $TODO: Windows workaround - Fixed in Eclipse 3.0
        // Combo is not wide enough to show all text - add enough space for
        // another character
        if (SWT.getPlatform().equals("win32")) //$NON-NLS-1$
            width += FigureUtilities.getTextWidth("8", control.getFont()); //$NON-NLS-1$
        return width;
    }

    /**
     * Creates and returns the control for this contribution item under the given parent composite.
     * 
     * @param parent
     *            the parent composite
     * @return the new control
     */
    protected Control createControl(Composite parent) {
        combo = new Combo(parent, SWT.DROP_DOWN);
        combo.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                handleWidgetSelected(e);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                handleWidgetDefaultSelected(e);
            }
        });
        combo.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                // do nothing
            }

            public void focusLost(FocusEvent e) {
                refresh(false);
            }
        });

        // Initialize width of combo
        combo.setItems(initStrings);
        toolitem.setWidth(computeWidth(combo));
        refresh(true, null);
        return combo;
    }

    /**
     * @see org.eclipse.jface.action.ContributionItem#dispose()
     */
    public void dispose() {
        if (partListener == null)
            return;
        service.removePartListener(partListener);
        combo = null;
        partListener = null;
    }

    /**
     * The control item implementation of this <code>IContributionItem</code> method calls the <code>createControl</code> framework method. Subclasses must
     * implement <code>createControl</code> rather than overriding this method.
     * 
     * @param parent
     *            The parent of the control to fill
     */
    public final void fill(Composite parent) {
        createControl(parent);
    }

    /**
     * The control item implementation of this <code>IContributionItem</code> method throws an exception since controls cannot be added to menus.
     * 
     * @param parent
     *            The menu
     * @param index
     *            Menu index
     */
    public final void fill(Menu parent, int index) {
        Assert.isTrue(false, "Can't add a control to a menu");//$NON-NLS-1$
    }

    /**
     * The control item implementation of this <code>IContributionItem</code> method calls the <code>createControl</code> framework method to create a control
     * under the given parent, and then creates a new tool item to hold it. Subclasses must implement <code>createControl</code> rather than overriding this
     * method.
     * 
     * @param parent
     *            The ToolBar to add the new control to
     * @param index
     *            Index
     */
    public void fill(ToolBar parent, int index) {
        toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
        Control control = createControl(parent);
        toolitem.setControl(control);
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
     */
    private void handleWidgetDefaultSelected(SelectionEvent event) {
        handleWidgetSelected(event);
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
     */
    private void handleWidgetSelected(SelectionEvent event) {
        refreshEditor(getActiveEditor());

        /*
         * There are several cases where invoking setZoomAsText (above) will not result in zoomChanged being fired (the method below), such as when the user
         * types "asdf" as the zoom level and hits enter, or when they type in 1%, which is below the minimum limit, and the current zoom is already at the
         * minimum level. Hence, there is no guarantee that refresh() will always be invoked. But we need to invoke it to clear out the invalid text and show
         * the current zoom level. Hence, an (often redundant) invocation to refresh() is made below.
         */
        refresh(false);
    }

    /**
     * Refreshes the editor given the current state of the combo box.
     */
    public void refreshEditor(UCMNavMultiPageEditor editor) {
        URNRootEditPart part = getRootEditpart(editor);
        // TODO: GTK Workaround (fixed in 3.0) - see SWT bug #44345
        if (part != null)
            if (combo.getSelectionIndex() >= 0) {
                if (isLocal()) {
                    part.setMode(combo.getSelectionIndex());
                } else {
                    UrnEditor u;
                    // for all editors,
                    for (int i = 0; i < editor.getPageCount(); i++) {
                        u = (UrnEditor) editor.getEditor(i);
                        ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setMode(combo.getSelectionIndex());
                    }
                }
            }
    }

    /**
     * @return do changes impact only the currently opened editor or does it affect all of them?
     */
    public static boolean isLocal() {
        return local;
    }

    /**
     * 
     * @param local
     *            do changes impact only the currently opened editor or does it affect all of them?
     */
    public static void setLocal(boolean local) {
        ModeComboContributionItem.local = local;
    }
}