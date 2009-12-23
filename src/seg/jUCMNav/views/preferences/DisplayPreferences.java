/**
 * 
 */
package seg.jUCMNav.views.preferences;

import grl.GRLGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.concerns.ManageConcernsAction;
import seg.jUCMNav.editors.GrlEditor;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UcmEditor;
import seg.jUCMNav.editors.palette.GrlPaletteRoot;
import seg.jUCMNav.editors.palette.UcmPaletteRoot;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.views.JUCMNavRefreshableView;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urncore.URNmodelElement;

/**
 * Keep the preferences for the outline view.
 * 
 * @author jfroy, jkealey
 * 
 */
public class DisplayPreferences {

    private static DisplayPreferences instance;
    public static final String PREF_OUTLINE_FILTER = "PREF_OUTLINE_FILTER"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWCONCERNS = "PREF_OUTLINE_SHOWCONCERNS"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWEMPTY = "PREF_OUTLINE_SHOWEMPTY"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWFORKJOIN = "PREF_OUTLINE_SHOWFORKJOIN"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWGRLS = "PREF_OUTLINE_SHOWGRLS"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWNODENUMBER = "PREF_OUTLINE_SHOWNODENUMBER"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWSTARTEND = "PREF_OUTLINE_SHOWSTARTEND"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SHOWUCMS = "PREF_OUTLINE_SHOWUCMS"; //$NON-NLS-1$
    public static final String PREF_OUTLINE_SORT = "PREF_OUTLINE_SORT"; //$NON-NLS-1$

    public static final String PREF_ADVANCEDUCM = "PREF_ADVANCEDJUCMNAV"; //$NON-NLS-1$

    public static final String SORT_DEFAULT = "SORTDEF"; //$NON-NLS-1$
    public static final String SORT_ID = "SORTID"; //$NON-NLS-1$
    public static final String SORT_NAME = "SORTNAME"; //$NON-NLS-1$
    public static final String PREF_ADVANCEDUCMKPI = "PREF_ADVANCEDKPI"; //$NON-NLS-1$
    public static final String PREF_ADVANCEDUCMASPECTS = "PREF_ADVANCEDASPECTS"; //$NON-NLS-1$
    public static final String PREF_ADVANCEDUCMPERF = "PREF_ADVANCEDPERF"; //$NON-NLS-1$

    public Vector<String> advancedActions = new Vector<String>();

    /**
     * Sets the default values in the preference store.
     */
    public static void createPreferences() {

        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWNODENUMBER, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWEMPTY, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWFORKJOIN, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWSTARTEND, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWUCMS, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWGRLS, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SHOWCONCERNS, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_SORT, SORT_DEFAULT);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_OUTLINE_FILTER, ""); //$NON-NLS-1$

        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_ADVANCEDUCM, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_ADVANCEDUCMKPI, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_ADVANCEDUCMASPECTS, true);
        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(PREF_ADVANCEDUCMPERF, true);

        getInstance(); // Create the instance
    }

    public static DisplayPreferences getInstance() {
        if (instance == null) {
            instance = new DisplayPreferences();
        }
        return instance;
    }

    private boolean globalFilterEnabled;

    private List<JUCMNavRefreshableView> listenerViews;

    /**
     * Constructor
     */
    private DisplayPreferences() {
        listenerViews = new ArrayList<JUCMNavRefreshableView>();
        setGlobalFilterEnabled(false);

        advancedActions.add(ManageConcernsAction.MANAGECONCERNS);

        // Listen to preference for changes
        JUCMNavPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getProperty().equals(PREF_ADVANCEDUCM) || event.getProperty().equals(PREF_ADVANCEDUCMASPECTS)
                        || event.getProperty().equals(PREF_ADVANCEDUCMKPI) || event.getProperty().equals(PREF_ADVANCEDUCMPERF)) {
                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

                    // The user changed the option to show or hide advanced feature in JUCMNav
                    // Show or hide various informations in the UI.
                    if (DisplayPreferences.getInstance().isAdvancedControlEnabled()) {
                        if (isShowKPI()) {
                            showView(page, "seg.jUCMNav.views.KPIListView"); //$NON-NLS-1$
                            showView(page, "seg.jUCMNav.views.KPIView"); //$NON-NLS-1$
                            showView(page, IPageLayout.ID_PROP_SHEET);
                            showView(page, "seg.jUCMNav.views.StrategiesView"); //$NON-NLS-1$

                            addKpi(page);
                        } else if (!isShowKPI() && event.getProperty().equals(PREF_ADVANCEDUCMKPI)) {
                            hideView(page, "seg.jUCMNav.views.KPIListView"); //$NON-NLS-1$
                            hideView(page, "seg.jUCMNav.views.KPIView"); //$NON-NLS-1$
                            removeKpi(page);
                        }

                        if (isShowAspect())
                            addStubs(page);
                        else if (!isShowAspect() && event.getProperty().equals(PREF_ADVANCEDUCMASPECTS))
                            removeStubs(page);
                    } else {
                        hideView(page, "seg.jUCMNav.views.KPIListView"); //$NON-NLS-1$
                        hideView(page, "seg.jUCMNav.views.KPIView"); //$NON-NLS-1$
                        removeKpi(page);

                        removeStubs(page);
                    }

                    refreshViews();
                }
            }
        });
    }

    /**
     * Add Advanced Stubs entries to all the palettes of all UCM editors.
     * 
     * @param page
     */
    private void addStubs(IWorkbenchPage page) {
        Vector<UcmPaletteRoot> pal = getUcmPalettes(page);
        for (Iterator<UcmPaletteRoot> iterator = pal.iterator(); iterator.hasNext();) {
            UcmPaletteRoot palette = iterator.next();
            palette.showAdvancedStubs(true);
        }
    }

    /**
     * Remove Advanced Stubs entries from all the palettes of all UCM editors.
     * 
     * @param page
     */
    private void removeStubs(IWorkbenchPage page) {
        Vector<UcmPaletteRoot> pal = getUcmPalettes(page);
        for (Iterator<UcmPaletteRoot> iterator = pal.iterator(); iterator.hasNext();) {
            UcmPaletteRoot palette = iterator.next();
            palette.showAdvancedStubs(false);
        }
    }

    /**
     * Add KPI entries to all the palettes of all GRL editors.
     * 
     * @param page
     */
    private void addKpi(IWorkbenchPage page) {
        Vector<GrlPaletteRoot> pal = getGrlPalettes(page);
        for (Iterator<GrlPaletteRoot> iterator = pal.iterator(); iterator.hasNext();) {
            GrlPaletteRoot palette = iterator.next();
            palette.addKpi();
        }
    }

    /**
     * Remove KPI entries from all the palettes of all GRL editors.
     * 
     * @param page
     */
    private void removeKpi(IWorkbenchPage page) {
        Vector<GrlPaletteRoot> pal = getGrlPalettes(page);
        for (Iterator<GrlPaletteRoot> iterator = pal.iterator(); iterator.hasNext();) {
            GrlPaletteRoot palette = iterator.next();
            palette.removeKpi();
        }
    }

    /**
     * Utility method to find all GRLPalette in use in eclipse right now.
     * 
     * @param page
     * @return
     */
    private Vector<GrlPaletteRoot> getGrlPalettes(IWorkbenchPage page) {
        Vector<GrlPaletteRoot> result = new Vector<GrlPaletteRoot>();

        Vector<IEditorPart> ref = getEditors(page, "seg.jUCMNav.MainEditor"); //$NON-NLS-1$

        for (Iterator<IEditorPart> it = ref.iterator(); it.hasNext();) {
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) it.next();

            if (editor != null) {
                for (int j = 0; j < editor.getPageCount(); j++) {
                    IEditorPart part = editor.getEditor(j);
                    if (part instanceof GrlEditor)
                        result.add((GrlPaletteRoot) ((GrlEditor) part).getPaletteRoot());
                }
            }
        }

        return result;
    }

    private Vector<UcmPaletteRoot> getUcmPalettes(IWorkbenchPage page) {
        Vector<UcmPaletteRoot> result = new Vector<UcmPaletteRoot>();

        Vector<IEditorPart> ref = getEditors(page, "seg.jUCMNav.MainEditor"); //$NON-NLS-1$

        for (Iterator<IEditorPart> it = ref.iterator(); it.hasNext();) {
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) it.next();

            if (editor != null) {
                for (int j = 0; j < editor.getPageCount(); j++) {
                    IEditorPart part = editor.getEditor(j);
                    if (part instanceof UcmEditor)
                        result.add((UcmPaletteRoot) ((UcmEditor) part).getPaletteRoot());
                }
            }
        }

        return result;
    }

    /**
     * Show a specific view
     * 
     * @param page
     * @param name
     */
    private void showView(IWorkbenchPage page, String name) {
        try {
            page.showView(name);

        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hide a specific view
     * 
     * @param page
     * @param name
     */
    private void hideView(IWorkbenchPage page, String name) {
        IViewReference part = getView(page, name);
        if (part != null)
            page.hideView(part);
    }

    /**
     * Return a reference from a view
     * 
     * @param page
     * @param name
     * @return
     */
    private IViewReference getView(IWorkbenchPage page, String name) {
        for (int i = 0; i < page.getViewReferences().length; i++) {
            IWorkbenchPartReference ref = page.getViewReferences()[i];
            if (ref.getId().equals(name))
                return (IViewReference) ref;
        }

        return null;
    }

    /**
     * Return all editors corresponding to name in the running eclipse instance.
     * 
     * @param page
     * @param name
     * @return
     */
    private Vector<IEditorPart> getEditors(IWorkbenchPage page, String name) {
        Vector<IEditorPart> result = new Vector<IEditorPart>();

        for (int i = 0; i < page.getEditorReferences().length; i++) {
            IWorkbenchPartReference ref = page.getEditorReferences()[i];
            if (ref.getId().equals(name))
                result.add(((IEditorReference) ref).getEditor(false));
        }

        return result;
    }

    /**
     * Return true if the empty point must be shown in the outline.
     * 
     * @return boolean
     */
    public String getFilter() {
        if (getPreferenceStore().getString(PREF_OUTLINE_FILTER) == null)
            return ""; //$NON-NLS-1$
        else
            return getPreferenceStore().getString(PREF_OUTLINE_FILTER);
    }

    public IPreferenceStore getPreferenceStore() {
        return JUCMNavPlugin.getDefault().getPreferenceStore();
    }

    /**
     * Return true if the concerns must be shown in the outline.
     * 
     * @return boolean
     */
    public boolean getShowConcerns() {
        return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWCONCERNS);
    }

    /**
     * Return true if the empty point must be shown in the outline.
     * 
     * @return boolean
     */
    public boolean getShowEmptyPoint() {
        return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWEMPTY);
    }

    /**
     * Return true if forks & joins must be shown in the outline.
     * 
     * @return boolean
     */
    public boolean getShowForkJoin() {
        return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWFORKJOIN);
    }

    /**
     * Return true if the grl elements must be shown in the outline.
     * 
     * @return boolean
     */
    public boolean getShowGRLS() {
        return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWGRLS);
    }

    /**
     * Return true if the node number must be shown in the outline.
     * 
     * @return boolean
     */
    public boolean getShowNodeNumber() {
        return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWNODENUMBER);
    }

    /**
     * Return true if the start/end must be shown in the outline.
     * 
     * @return boolean
     */
    public boolean getShowStartEnd() {
        return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWSTARTEND);
    }

    /**
     * Return true if the ucm elements must be shown in the outline.
     * 
     * @return boolean
     */
    public boolean getShowUCMS() {
        return getPreferenceStore().getBoolean(PREF_OUTLINE_SHOWUCMS);
    }

    /**
     * Return true if the empty point must be shown in the outline.
     * 
     * @return boolean
     */
    public String getSort() {
        if (getPreferenceStore().getString(PREF_OUTLINE_SORT) == null)
            return SORT_DEFAULT;
        else
            return getPreferenceStore().getString(PREF_OUTLINE_SORT);
    }

    public boolean isGlobalFilterEnabled() {
        return globalFilterEnabled;
    }

    public void unregisterListener(JUCMNavRefreshableView view) {
        listenerViews.remove(view);
    }

    public void refreshViews() {
        for (Iterator<JUCMNavRefreshableView> it = listenerViews.iterator(); it.hasNext();) {
            JUCMNavRefreshableView currentView = it.next();
            currentView.refreshView();
        }
    }

    public void registerListener(JUCMNavRefreshableView view) {
        if (!listenerViews.contains(view))
            listenerViews.add(view);
    }

    public void setGlobalFilterEnabled(boolean globalFilterEnabled) {
        this.globalFilterEnabled = globalFilterEnabled;
        refreshViews();
    }

    /**
     * Set whether empty point must been shown in the outline.
     */
    public void setShowEmptyPoint(boolean value) {
        getPreferenceStore().setValue(PREF_OUTLINE_SHOWEMPTY, value);
        refreshViews();
    }

    /**
     * Should we show GRL elements in the outline.
     * 
     * @param value
     *            show them?
     */
    public void setShowGRLS(boolean value) {
        getPreferenceStore().setValue(PREF_OUTLINE_SHOWGRLS, value);
        // refreshViews();
    }

    /**
     * Set whether node number must been shown in the outline.
     */
    public void setShowNodeNumber(boolean value) {
        getPreferenceStore().setValue(PREF_OUTLINE_SHOWNODENUMBER, value);
        refreshViews();

    }

    /**
     * Should we show UCM elements in the outline.
     * 
     * @param value
     *            show them?
     */
    public void setShowUCMS(boolean value) {
        getPreferenceStore().setValue(PREF_OUTLINE_SHOWUCMS, value);
        // refreshViews();
    }

    public boolean isAdvancedControlEnabled() {
        return getPreferenceStore().getBoolean(PREF_ADVANCEDUCM);
    }

    public void setAdvancedControlEnabled(boolean value) {
        getPreferenceStore().setValue(PREF_ADVANCEDUCM, value);
        refreshViews();
    }

    public boolean isShowKPI() {
        return getPreferenceStore().getBoolean(PREF_ADVANCEDUCMKPI);
    }

    public void setShowKPI(boolean value) {
        getPreferenceStore().setValue(PREF_ADVANCEDUCMKPI, value);
        refreshViews();
    }

    public boolean isShowPerformance() {
        return getPreferenceStore().getBoolean(PREF_ADVANCEDUCMPERF);
    }

    public void setShowPerformance(boolean value) {
        getPreferenceStore().setValue(PREF_ADVANCEDUCMPERF, value);
        refreshViews();
    }

    public boolean isShowAspect() {
        return getPreferenceStore().getBoolean(PREF_ADVANCEDUCMASPECTS);
    }

    public void setShowAspect(boolean value) {
        getPreferenceStore().setValue(PREF_ADVANCEDUCMASPECTS, value);
        refreshViews();
    }

    public boolean isElementFiltered(URNmodelElement element) {
        return isElementFiltered(element, true);
    }

    public boolean isElementFiltered(URNmodelElement element, boolean tryName) {

        if (!isGlobalFilterEnabled())
            return false;

        if (element instanceof UCMmap && !getShowUCMS())
            return true;

        if (element instanceof GRLGraph && !getShowGRLS())
            return true;

        if ((element instanceof StartPoint || element instanceof EndPoint) && !getShowStartEnd())
            return true;

        if ((element instanceof EmptyPoint || element instanceof DirectionArrow) && !getShowEmptyPoint())
            return true;

        if ((element instanceof AndFork || element instanceof OrFork || element instanceof AndJoin || element instanceof OrJoin) && !getShowForkJoin())
            return true;

        if (tryName && !URNElementFinder.doesElementMatchPattern(getFilter(), element))
            return true;

        return false;
    }
}
