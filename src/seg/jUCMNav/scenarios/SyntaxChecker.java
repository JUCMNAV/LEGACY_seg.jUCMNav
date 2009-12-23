package seg.jUCMNav.scenarios;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;
import urncore.Condition;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * Verifies the syntax of all conditions / responsibilities. Also manages refreshing the problems view.
 * 
 * @author jkealey
 * 
 */
public class SyntaxChecker {

    /**
     * Verifies a condition's syntax.
     * 
     * @param urn
     *            the urnspec
     * @param errors
     *            where should errors be appended.
     * @param location
     *            the location where the condition is valuated.. to produce useful traversal warnings.
     * @param expr
     *            the condition's expression.
     * 
     */
    private static void verifyCondition(URNspec urn, Vector errors, EObject location, String expr) {
        Object o = ScenarioUtils.parse(expr, ScenarioUtils.getEnvironment(urn), false);
        if (!(o instanceof SimpleNode)) {
            TraversalWarning warning = new TraversalWarning((String) o, location, IMarker.SEVERITY_ERROR);
            warning.setExpression(expr);
            errors.add(warning);
        }
    }

    /**
     * Verifies all conditions associated to path nodes or node connections in all maps.
     * 
     * @param urn
     *            the urnspec
     * @param errors
     *            where should errors be appended.
     */
    private static void verifyMapConditions(URNspec urn, Vector errors) {
        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram diag = (IURNDiagram) iter.next();
            if (diag instanceof UCMmap) {
                for (Iterator iterator = ((UCMmap) diag).getNodes().iterator(); iterator.hasNext();) {
                    PathNode node = (PathNode) iterator.next();
                    if (node instanceof StartPoint) {
                        if (((StartPoint) node).getPrecondition() != null) {
                            verifyCondition(urn, errors, node, ((StartPoint) node).getPrecondition().getExpression());
                        }
                    } else if (node instanceof EndPoint) {
                        if (((EndPoint) node).getPostcondition() != null) {
                            verifyCondition(urn, errors, node, ((EndPoint) node).getPostcondition().getExpression());
                        }
                    } else if (node instanceof OrFork || node instanceof WaitingPlace || node instanceof FailurePoint) {
                        for (Iterator it2 = node.getSucc().iterator(); it2.hasNext();) {
                            NodeConnection nc = (NodeConnection) it2.next();
                            if (nc.getCondition() != null) {
                                verifyCondition(urn, errors, node, nc.getCondition().getExpression());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Verifies the syntax of every plugin binding in every map.
     * 
     * @param urn
     *            the urnspec
     * @param errors
     *            where should errors be appended.
     */
    private static void verifyPluginBindingSyntax(URNspec urn, Vector errors) {
        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram diag = (IURNDiagram) iter.next();
            if (diag instanceof UCMmap) {
                for (Iterator iterator = ((UCMmap) diag).getParentStub().iterator(); iterator.hasNext();) {
                    PluginBinding binding = (PluginBinding) iterator.next();
                    if (binding.getPrecondition() != null) {
                        String expr = binding.getPrecondition().getExpression();
                        verifyCondition(urn, errors, binding.getStub(), expr);
                    }
                }
            }
        }
    }

    /**
     * Verify the code associated to all responsibilities
     * 
     * @param urn
     *            the urnspec
     * @param errors
     *            where should errors be appended.
     */
    private static void verifyResponsibilitySyntax(URNspec urn, Vector errors) {
        for (Iterator iter = urn.getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
            Responsibility resp = (Responsibility) iter.next();
            if (!ScenarioUtils.isEmptyResponsibility(resp)) {
                Object o = ScenarioUtils.parse(resp.getExpression(), ScenarioUtils.getEnvironment(resp), true);
                if (!(o instanceof SimpleNode)) {
                    if (resp.getRespRefs().size() > 0)
                        errors.add(new TraversalWarning((String) o, (EObject) resp.getRespRefs().get(0), IMarker.SEVERITY_ERROR));
                    else
                        errors.add(new TraversalWarning((String) o, resp, IMarker.SEVERITY_ERROR));

                    ((TraversalWarning) errors.get(errors.size() - 1)).setExpression(resp.getExpression());
                }
            }
        }
    }

    /**
     * Verify the code associated to all failure poitns
     * 
     * @param urn
     *            the urnspec
     * @param errors
     *            where should errors be appended.
     */
    private static void verifyFailurePointSyntax(URNspec urn, Vector errors) {
        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram diag = (IURNDiagram) iter.next();
            if (diag instanceof UCMmap) {
                for (Iterator iterator = diag.getNodes().iterator(); iterator.hasNext();) {
                    PathNode pn = (PathNode) iterator.next();
                    if (pn instanceof FailurePoint) {
                        FailurePoint fail = (FailurePoint) pn;
                        Object o = ScenarioUtils.parse(fail.getExpression(), ScenarioUtils.getEnvironment(fail), true);
                        if (!(o instanceof SimpleNode)) {
                            errors.add(new TraversalWarning((String) o, fail, IMarker.SEVERITY_ERROR));
                            ((TraversalWarning) errors.get(errors.size() - 1)).setExpression(fail.getExpression());
                        }
                    }
                }
            }
        }
    }

    /**
     * Verifies the syntax of all scenario pre/post conditions.
     * 
     * @param urn
     *            the urnspec
     * @param errors
     *            where should errors be appended.
     */
    private static void verifyScenarioPrePostConditions(URNspec urn, Vector errors) {
        for (Iterator iter = urn.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
            ScenarioGroup group = (ScenarioGroup) iter.next();
            for (Iterator iterator = group.getScenarios().iterator(); iterator.hasNext();) {
                ScenarioDef scenario = (ScenarioDef) iterator.next();
                for (Iterator it2 = scenario.getPreconditions().iterator(); it2.hasNext();) {
                    Condition cond = (Condition) it2.next();
                    verifyCondition(urn, errors, scenario, cond.getExpression());
                }
                for (Iterator it2 = scenario.getPostconditions().iterator(); it2.hasNext();) {
                    Condition cond = (Condition) it2.next();
                    verifyCondition(urn, errors, scenario, cond.getExpression());
                }
            }
        }
    }

    /**
     * Returns a vector of TraversalWarnings for all the elements that do not have a valid syntax.
     * 
     * @param urn
     *            the urnspec to be analyzed
     * @return vector of TraversalWarnings for all the elements that do not have a valid syntax.
     */
    public static Vector verifySyntax(URNspec urn) {
        Vector errors = new Vector();
        verifyResponsibilitySyntax(urn, errors);
        verifyFailurePointSyntax(urn, errors);
        verifyPluginBindingSyntax(urn, errors);
        verifyMapConditions(urn, errors);
        verifyScenarioPrePostConditions(urn, errors);
        if (JUCMNavPlugin.isInDebug())
            verifyUniqueIDs(urn, errors);
        return errors;

    }

    public static void verifyUniqueIDs(URNspec urn, Vector errors) {
        UrnModelManager manager = new UrnModelManager();
        try {
            Vector duplicates = manager.getDuplicateIDs(urn);

            for (Iterator iterator = duplicates.iterator(); iterator.hasNext();) {
                URNmodelElement o = (URNmodelElement) iterator.next();
                errors.add(new TraversalWarning(Messages.getString("SyntaxChecker_ElementAsADuplicateID") + o.getId(), o, IMarker.SEVERITY_ERROR)); //$NON-NLS-1$

            }
        } catch (IOException ex) {
            errors.add(new TraversalWarning(Messages.getString("SyntaxChecker_UnableToCheckForDuplicateIDs") + ex.getMessage(), IMarker.SEVERITY_ERROR)); //$NON-NLS-1$
        }

    }

    /**
     * Clears the warnings associated to this file and replaces them with those supplied in the vector.
     * 
     * @param warnings
     *            a vector of {@link TraversalWarning}s to be pushed to the problems view.
     */
    public static void refreshProblemsView(Vector warnings) {
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            IFile resource = ((FileEditorInput) editor.getEditorInput()).getFile();
            try {

                IMarker[] existingMarkers = resource.findMarkers(IMarker.PROBLEM, true, 3);
                for (int i = 0; i < existingMarkers.length; i++) {
                    IMarker marker = existingMarkers[i];
                    marker.delete();
                }
            } catch (CoreException ex) {
                System.out.println(ex);
            }

            if (warnings.size() > 0) {

                for (Iterator iter = warnings.iterator(); iter.hasNext();) {
                    TraversalWarning o = (TraversalWarning) iter.next();

                    try {
                        IMarker marker = resource.createMarker(IMarker.PROBLEM);
                        marker.setAttribute(IMarker.SEVERITY, o.getSeverity());
                        marker.setAttribute(IMarker.MESSAGE, o.toString());
                        if (o.getLocation() instanceof URNmodelElement) {
                            URNmodelElement elem = (URNmodelElement) o.getLocation();
                            marker.setAttribute(IMarker.LOCATION, URNNamingHelper.getName(elem));
                            marker.setAttribute("EObject", ((URNmodelElement) o.getLocation()).getId()); //$NON-NLS-1$
                        } else if (o.getLocation() != null) {
                            marker.setAttribute(IMarker.LOCATION, o.getLocation().toString());
                        }

                        if (o.getCondition() != null && o.getCondition().eContainer() != null) {
                            if (o.getCondition().eContainer() instanceof StartPoint) {
                                StartPoint start = (StartPoint) o.getCondition().eContainer();
                                marker.setAttribute("NodePreCondition", start.getId()); //$NON-NLS-1$
                            } else if (o.getCondition().eContainer() instanceof EndPoint) {
                                EndPoint end = (EndPoint) o.getCondition().eContainer();
                                marker.setAttribute("NodePostCondition", end.getId()); //$NON-NLS-1$
                            } else if (o.getCondition().eContainer() instanceof NodeConnection) {
                                NodeConnection ncx = (NodeConnection) o.getCondition().eContainer();
                                PathNode pn = (PathNode) ncx.getSource();
                                marker.setAttribute("Condition", pn.getId()); //$NON-NLS-1$
                                for (int i = 0; i < pn.getSucc().size(); i++) {
                                    NodeConnection nc = (NodeConnection) pn.getSucc().get(i);
                                    if (nc.getCondition() == o.getCondition()) {
                                        marker.setAttribute("ConditionIndex", i); //$NON-NLS-1$
                                    }
                                }
                            } else if (o.getCondition().eContainer() instanceof ScenarioDef) {
                                ScenarioDef scenario = (ScenarioDef) o.getCondition().eContainer();
                                marker.setAttribute("Scenario", scenario.getId()); //$NON-NLS-1$
                                marker.setAttribute("ScenarioPreConditionIndex", scenario.getPreconditions().indexOf(o.getCondition())); //$NON-NLS-1$
                                marker.setAttribute("ScenarioPostConditionIndex", scenario.getPostconditions().indexOf(o.getCondition())); //$NON-NLS-1$
                            }
                        } else if (o.getLocation() instanceof OrFork || o.getLocation() instanceof WaitingPlace || o.getLocation() instanceof FailurePoint) {
                            PathNode pn = (PathNode) o.getLocation();
                            marker.setAttribute("Condition", pn.getId()); //$NON-NLS-1$							
                        }
                        resource.findMarkers("seg.jUCMNav.WarningMarker", true, 1); //$NON-NLS-1$
                    } catch (CoreException ex) {
                        // System.out.println(ex);
                    }

                }
                // throw new TraversalException(b.toString());

            }
        }
    }

}
