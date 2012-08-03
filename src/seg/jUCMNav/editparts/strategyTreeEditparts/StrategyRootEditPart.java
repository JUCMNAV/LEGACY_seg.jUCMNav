package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.TreeEditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.treeEditparts.UrnAbstractTreeEditPart;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import ucm.UCMspec;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;

/**
 * This class is simply here because the root of our strategy (GRLspec) / scenario (UCMspec) is already used.
 * 
 * Why not use a URNspec? I'm not sure, I think we may be using it elsewhere, but can't confirm.
 * 
 * @author jfroy, jkealey
 * 
 */
public class StrategyRootEditPart extends UrnAbstractTreeEditPart {

    /**
     * 
     * @param editor
     */
    public StrategyRootEditPart(UCMNavMultiPageEditor editor) {
        super(editor);
    }

    /**
     * Return the root URNSpec
     */
    protected List getModelChildren() {
        ArrayList l = new ArrayList();

        if (!DisplayPreferences.getInstance().isGlobalFilterEnabled() || DisplayPreferences.getInstance().getShowUCMS())
            l.add(((UCMNavMultiPageEditor) getModel()).getModel().getUcmspec());

        if (!DisplayPreferences.getInstance().isGlobalFilterEnabled() || DisplayPreferences.getInstance().getShowGRLS()) {
            l.add(((UCMNavMultiPageEditor) getModel()).getModel().getGrlspec());
            l.add(((UCMNavMultiPageEditor) getModel()).getModel().getGrlspec().getContributionGroups());
        }

        if (!DisplayPreferences.getInstance().isGlobalFilterEnabled() || DisplayPreferences.getInstance().isShowKPI())
            l.add(((UCMNavMultiPageEditor) getModel()).getModel().getGrlspec().getKPIConversion());

        if (!DisplayPreferences.getInstance().isGlobalFilterEnabled() || DisplayPreferences.getInstance().getShowUCMS()) {
            l.add(((UCMNavMultiPageEditor) getModel()).getModel().getUcmspec().getEnumerationTypes());
            l.add(((UCMNavMultiPageEditor) getModel()).getModel().getUcmspec().getVariables());
        }
        return l;
    }

    /**
     * @see org.eclipse.gef.EditPart#getRoot()
     */
    public RootEditPart getRoot() {
        if (getParent() == null)
            return null;
        else
            return getParent().getRoot();
    }

    /**
     * Refreshes the scenarios.
     * 
     * @param ucmspec
     */
    public void refreshScenarioTreeView(UCMspec ucmspec) {
        for (Iterator iter = ucmspec.getScenarioGroups().iterator(); iter.hasNext();) {
            ScenarioGroup group = (ScenarioGroup) iter.next();
            for (Iterator iterator = group.getScenarios().iterator(); iterator.hasNext();) {
                ScenarioDef scenario = (ScenarioDef) iterator.next();
                StrategyUrnModelElementTreeEditPart part = (StrategyUrnModelElementTreeEditPart) getViewer().getEditPartRegistry().get(scenario);
                if (part != null) {
                    // all children of a strategy
                    for (Iterator iter2 = part.getChildren().iterator(); iter2.hasNext();) {
                        TreeEditPart element = (TreeEditPart) iter2.next();
                        element.refresh();
                    }
                }
            }
        }
    }
}