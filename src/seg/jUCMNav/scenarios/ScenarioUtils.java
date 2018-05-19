package seg.jUCMNav.scenarios;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.scenarios.algorithmInterfaces.IScenarioTraversalAlgorithm;
import seg.jUCMNav.scenarios.algorithmInterfaces.ITraversalListener;
import seg.jUCMNav.scenarios.evaluator.UcmExpressionEvaluator;
import seg.jUCMNav.scenarios.evaluator.UcmExpressionValue;
import seg.jUCMNav.scenarios.model.TraversalException;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import seg.jUCMNav.scenarios.model.jUCMNavType;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.scenarios.parser.jUCMNavParser;
import seg.jUCMNav.scenarios.parser.jUCMNavTypeChecker;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import ucm.UCMspec;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.FailureKind;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitKind;
import ucm.map.WaitingPlace;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urn.URNspec;
import urn.dyncontext.BooleanChange;
import urn.dyncontext.Change;
import urn.dyncontext.DeactivationChange;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.EnumChange;
import urn.dyncontext.PropertyChange;
import urn.dyncontext.TextChange;
import urn.dyncontext.Timepoint;
import urncore.Component;
import urncore.ComponentKind;
import urncore.Condition;
import urncore.IURNDiagram;
import urncore.Metadata;
import urncore.Responsibility;

/**
 * Utility class for UCM Scenarios.
 * 
 * @author jkealey, sluthra
 * 
 */
public class ScenarioUtils {
    private static HashMap activeScenario = new HashMap();
    private static HashMap environments;
    private static DynamicContext dynContext;
    private static Timepoint tp = null;
    // static reference to jUCMNavParser. can't have more than one reference to
    // the parser in the whole application.
    public static final jUCMNavParser parser = new jUCMNavParser(new StringReader("true")); //$NON-NLS-1$
    public static final String sTypeBoolean = "boolean"; //$NON-NLS-1$
    public static final String sTypeEnumeration = "enumeration"; //$NON-NLS-1$
    public static final String sTypeInteger = "integer"; //$NON-NLS-1$

    public static boolean IS_ELSE_CONDITION_ALLOWED = true;

	/**
	 * Metadata name used to store original boolean value of a Stub
	 */
	public static String METADATA_ORIGSTUB = "_origStub"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original boolean value of a Component
	 */
	public static String METADATA_ORIGCOMPONENT = "_origComponent"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Failure Kind of a Start Point
	 */
	public static final String METADATA_ORIGFAILUREKIND = "_origFailureKind"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Failure List/Start Point Pre-Condition of a Start Point
	 */
	public static final String METADATA_ORIGFAILURELIST = "_origFailureList"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Wait Kind of a Timer and a Waiting Place
	 */    
	public static final String METADATA_ORIGWAITKIND = "_origWaitKind"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Component Kind of a Component
	 */    
	public static final String METADATA_ORIGCOMPKIND = "_origCompKind"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Replication Factor of a PluginBinding
	 */    
	public static final String METADATA_ORIGREPFACTOR = "_repFactor"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Post Condition Value of an End Point
	 */    
	public static final String METADATA_ORIGENDPTPOSTCOND = "_origEndPtPostCond"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Condition Value of a PluginBinding
	 */    
	public static final String METADATA_ORIGPLUGINCONDITION = "_origPluginCond"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Expression of a Responsibility
	 */    
	public static final String METADATA_ORIGRESPEXPRESSION = "_origRespExp"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Expression of an OR-Fork
	 */    
	public static final String METADATA_ORIGORFORKEXP = "_origOrForkExp"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Condition of a FailurePoint
	 */    
	public static final String METADATA_ORIGFAILUREPTCOND = "_origFailurePtCond"; //$NON-NLS-1$

	/**
	 * Metadata name used to store original Expression of a FailurePoint
	 */    
	public static final String METADATA_ORIGFAILUREEXPRESSION = "_origFailurePtExp"; //$NON-NLS-1$

    private static HashMap traversals = new HashMap();

	public static DynamicContext getDynContext() {
        return dynContext;
    }

    public static void setDynContext(DynamicContext dynContext) {
        ScenarioUtils.dynContext = dynContext;
    }
    
    public static Timepoint getTp() {
        return tp;
    }

	public static void setTp(Timepoint tp) {
		ScenarioUtils.tp = tp;
	}

    /**
     * Flush any traversal results associated with this element.
     * 
     * @param obj
     *            an object in a URNspec
     */
    public static void clearActiveScenario(EObject obj) {
        UcmEnvironment initial = getEnvironment(obj);
		if (activeScenario.containsKey(initial)) {
			restoreOriginalValue(initial.getUrn().getUcmspec());
            clearTraversalResults(initial);
		}

        URNspec urn = initial.getUrn();
        MetadataHelper.cleanTimedUCMMetadata(urn);
        activeScenario.remove(initial);        

    }

    /**
	 * Restore original values for elements
	 */
	private static void restoreOriginalValue(UCMspec ucm) {

		for (Iterator iter = ucm.getUrnspec().getUrndef().getComponents().iterator(); iter.hasNext();) {
			Component comp = (Component) iter.next();

			Metadata metaOrigCompKind = MetadataHelper.getMetaDataObj(comp, METADATA_ORIGCOMPKIND);
			if(metaOrigCompKind != null) {
				String origCompKind = MetadataHelper.getMetaData(comp, METADATA_ORIGCOMPKIND);
				ComponentKind compKind = ComponentKind.get(origCompKind);
				comp.setKind(compKind);
			}

			Metadata metaOrigCompProtect = MetadataHelper.getMetaDataObj(comp, METADATA_ORIGCOMPONENT + ".compProtect");
			if(metaOrigCompProtect != null) {
				String origCompProtect = MetadataHelper.getMetaData(comp, METADATA_ORIGCOMPONENT + ".compProtect");
				comp.setProtected(Boolean.valueOf(origCompProtect));	
			}

			Metadata metaOrigCompContext = MetadataHelper.getMetaDataObj(comp, METADATA_ORIGCOMPONENT + ".compContext");
			if(metaOrigCompContext != null) {
				String origCompContext = MetadataHelper.getMetaData(comp, METADATA_ORIGCOMPONENT + ".compContext");
				comp.setContext(Boolean.valueOf(origCompContext));
			}
		}

		for (Iterator iter = ucm.getUrnspec().getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
			Responsibility resp = (Responsibility) iter.next();

			Metadata metaOrigRespExpression = MetadataHelper.getMetaDataObj(resp, METADATA_ORIGRESPEXPRESSION);
			if (metaOrigRespExpression != null) {
				String origRespExpression = MetadataHelper.getMetaData(resp, METADATA_ORIGRESPEXPRESSION);
				((Responsibility) resp).setExpression(origRespExpression);
			}

		}


		for (Iterator iter1 = ucm.getUrnspec().getUrndef().getSpecDiagrams().iterator(); iter1.hasNext();) {
			IURNDiagram diagram = (IURNDiagram) iter1.next();
			if (diagram instanceof UCMmap) {
				for (Iterator iterIn = diagram.getNodes().iterator(); iterIn.hasNext();) {
					PathNode pn = (PathNode) iterIn.next();

					if (pn instanceof StartPoint) {   			
						Metadata metaOrigFailureKind = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGFAILUREKIND);
						if (metaOrigFailureKind != null) {
							String origFailureKind = MetadataHelper.getMetaData(pn, METADATA_ORIGFAILUREKIND);
							FailureKind failureKind = FailureKind.get(origFailureKind);
							((StartPoint) pn).setFailureKind(failureKind);
						}

						Metadata metaOrigFailureList = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGFAILURELIST);
						if (metaOrigFailureList != null) {
							String origFailureList = MetadataHelper.getMetaData(pn, METADATA_ORIGFAILURELIST);

							if (((StartPoint) pn).getPrecondition() != null)
								((StartPoint) pn).getPrecondition().setExpression(origFailureList);
						}
					}

					else if (pn instanceof WaitingPlace) {        		
						Metadata metaOrigWaitKind = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGWAITKIND);
						if (metaOrigWaitKind != null) {
							String origWaitKind = MetadataHelper.getMetaData(pn, METADATA_ORIGWAITKIND);
							WaitKind waitKind = WaitKind.get(origWaitKind);
							((WaitingPlace) pn).setWaitType(waitKind);
						}
					}

					else if (pn instanceof Stub) {        		
						Metadata metaOrigStaticStub = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGSTUB + ".staticStub");
						Metadata metaOrigDynStub = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGSTUB + ".dynStub");
						Metadata metaOrigSyncStub = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGSTUB + ".syncStub");
						Metadata metaOrigBlockStub = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGSTUB + ".blockStub");

						if (metaOrigStaticStub != null || metaOrigDynStub != null || metaOrigSyncStub != null || metaOrigBlockStub != null) {

							String origDynStub = MetadataHelper.getMetaData(pn, METADATA_ORIGSTUB + ".dynStub");
							((Stub) pn).setDynamic(Boolean.valueOf(origDynStub));

							String origSyncStub = MetadataHelper.getMetaData(pn, METADATA_ORIGSTUB + ".syncStub");
							((Stub) pn).setSynchronization(Boolean.valueOf(origSyncStub));

							String origBlockStub = MetadataHelper.getMetaData(pn, METADATA_ORIGSTUB + ".blockStub");
							((Stub) pn).setBlocking(Boolean.valueOf(origBlockStub));
						}

						List <PluginBinding> bindings = ((Stub) pn).getBindings();
						for (PluginBinding p : bindings) {
							Metadata metaOrigRepFactor = MetadataHelper.getMetaDataObj(p.getStub(), METADATA_ORIGREPFACTOR + "." + p.getPlugin().getName());
							if (metaOrigRepFactor != null) {
								String origRepFactor = MetadataHelper.getMetaData(p.getStub(), METADATA_ORIGREPFACTOR + "." + p.getPlugin().getName());
								p.setReplicationFactor(Integer.parseInt(origRepFactor));
							}

							Metadata metaOrigPluginPreCondition = MetadataHelper.getMetaDataObj(p.getPrecondition().getPluginBinding().getStub(), METADATA_ORIGPLUGINCONDITION + "." + p.getPlugin().getName());
							if (metaOrigPluginPreCondition != null) {
								String origPluginPreCondition = MetadataHelper.getMetaData(p.getPrecondition().getPluginBinding().getStub(), METADATA_ORIGPLUGINCONDITION + "." + p.getPlugin().getName());

								if (p.getPrecondition() != null)
									p.getPrecondition().setExpression(origPluginPreCondition);
							}
						}
					} else if (pn instanceof EndPoint) {
						Metadata metaOrigEndPtPostCond = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGENDPTPOSTCOND);
						if (metaOrigEndPtPostCond != null) {
							String origEndPtPostCond = MetadataHelper.getMetaData(pn, METADATA_ORIGENDPTPOSTCOND);

							if (((EndPoint) pn).getPostcondition() != null)
								((EndPoint) pn).getPostcondition().setExpression(origEndPtPostCond);
						}
					} else if (pn instanceof OrFork) {
						List<NodeConnection> ncs = ((OrFork) pn).getDiagram().getConnections();
						for (NodeConnection n : ncs) {
							if (n.getSource() instanceof OrFork) {
								String orForkID = ((OrFork) n.getSource()).getId();
								String branchName = "Branch: " + (n.getSource().getSucc().indexOf(n) + 1);
								Metadata metaOrigORForkExpression = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGORFORKEXP + " (" + orForkID + ") <->" + branchName);
								if (metaOrigORForkExpression != null) {
									String origORForkExpression = MetadataHelper.getMetaData(pn, METADATA_ORIGORFORKEXP + " (" + orForkID + ") <->" + branchName);
									if (origORForkExpression != null) {
										n.getCondition().setExpression(origORForkExpression);
									}
								}
							}
						}
					} else if (pn instanceof FailurePoint) {
						List<NodeConnection> ncs = ((FailurePoint) pn).getDiagram().getConnections();
						for (NodeConnection n : ncs) {
							Metadata metaOrigFailurePtCond = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGFAILUREPTCOND);
							if (metaOrigFailurePtCond != null) {
								String origFailurePtCond = MetadataHelper.getMetaData(pn, METADATA_ORIGFAILUREPTCOND);
								if (n.getSource() instanceof FailurePoint) {
									n.getCondition().setExpression(origFailurePtCond);
								}
							}
						}

						Metadata metaOrigFailurePtExpression = MetadataHelper.getMetaDataObj(pn, METADATA_ORIGFAILUREEXPRESSION);
						if (metaOrigFailurePtExpression != null) {
							String origFailurePtExpression = MetadataHelper.getMetaData(pn, METADATA_ORIGFAILUREEXPRESSION);
							((FailurePoint) pn).setExpression(origFailurePtExpression);
						}
					}
				}
			}
		}
	}


	/**
     * Flush any traversal results associated with this environment.
     * 
     * @param env
     *            the environment
     */
    public static void clearTraversalResults(UcmEnvironment env) {
        if (traversals.containsKey(env)) {
            IScenarioTraversalAlgorithm results = (IScenarioTraversalAlgorithm) traversals.get(env);
            results.clearTraversalResults();
        }
        if (activeScenario.containsKey(env)) {
            activeScenario.remove(env);
        }

    }

    /**
     * Evaluate this condition in an environment. Can throw an {@link IllegalArgumentException}.
     * 
     * @param cond
     *            the condition
     * @param env
     *            the environment
     * @return the boolean result.
     */
    public static Object evaluate(Condition cond, UcmEnvironment env) {
        if (isEmptyCondition(cond)) {
            return Boolean.TRUE;
        } else {
            String toEvaluate = cond.getExpression();
            if (isElseCondition(toEvaluate)) {
                toEvaluate = replaceElseFromCondition(cond);
            }

            Object result = evaluate(toEvaluate, env, false);
            if (result != null) {
                if (result instanceof UcmExpressionValue)   {
                    if (((UcmExpressionValue) result).getBaseValue() instanceof Boolean) {
                        return (Boolean) ((UcmExpressionValue) result).getBaseValue();
                    }
                    else
                        throw new IllegalArgumentException(Messages.getString("ScenarioUtils.ConditionDidNotEvaluateToBoolean")); //$NON-NLS-1$
                }
            }
            return result;
        }
    }

    /**
     * Assuming cond is "else", look at the Condition's parent to figure out what "else" means in this context.
     * 
     * @param cond
     * @return
     */
    private static String replaceElseFromCondition(Condition cond) {
        String result = null;
        if (cond.eContainer() instanceof NodeConnection) {
            NodeConnection nodeConnection = (NodeConnection) cond.eContainer();
            if (nodeConnection.getSource() instanceof OrFork) {
                OrFork orFork = (OrFork) nodeConnection.getSource();
                Vector conditions = new Vector();
                for (Iterator iterator = orFork.getSucc().iterator(); iterator.hasNext();) {
                    NodeConnection nc = (NodeConnection) iterator.next();
                    if (!isEmptyCondition(nc.getCondition()) && !isElseCondition(nc.getCondition().getExpression())) {
                        conditions.add(nc.getCondition().getExpression());
                    }
                }
                result = concatenateConditions(conditions);
            }
        } else if (cond.eContainer() instanceof PluginBinding && cond.eContainer().eContainer() instanceof Stub) {
            Stub stub = (Stub) cond.eContainer().eContainer();
            Vector conditions = new Vector();
            for (Iterator iterator = stub.getBindings().iterator(); iterator.hasNext();) {
                PluginBinding binding = (PluginBinding) iterator.next();
                if (!isEmptyCondition(binding.getPrecondition()) && !isElseCondition(binding.getPrecondition().getExpression())) {
                    conditions.add(binding.getPrecondition().getExpression());
                }
            }
            result = concatenateConditions(conditions);
        } /*
           * added here just in case, but it makes no sense to enable this since all must be true. else if (cond.eContainer() instanceof ScenarioDef) {
           * ScenarioDef def = (ScenarioDef) cond.eContainer(); EList list = null; if (def.getPreconditions().contains(cond)) list = def.getPreconditions();
           * else list = def.getPostconditions(); Vector conditions = new Vector(); for (Iterator iterator = list.iterator(); iterator.hasNext();) { Condition c
           * = (Condition) iterator.next(); if (!isEmptyCondition(c) && !isElseCondition(c.getExpression())) { conditions.add(c.getExpression()); } } }
           */

        // assuming else is true when its the only thing there.
        if (result == null)
            result = "true"; //$NON-NLS-1$
        return result;
    }

    /**
     * Concatenates the other conditions and negates them.
     * 
     * @param conditions
     * @return
     */
    private static String concatenateConditions(Vector conditions) {
        String result = null;
        for (Iterator iterator = conditions.iterator(); iterator.hasNext();) {
            String c = (String) iterator.next();
            if (result == null)
                result = "!(" + c + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            else
                result += " && !(" + c + ") "; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return result;
    }

    /**
     * Is this text an "else" to be used in conditions.
     * 
     * @param expression
     * @return true if this is else and else is enabled in jucmnav.
     */
    public static boolean isElseCondition(String expression) {
        if (expression == null)
            return false;
        return IS_ELSE_CONDITION_ALLOWED && "else".equalsIgnoreCase(expression.trim()); //$NON-NLS-1$
    }

    /**
     * Evaluate this responsibility in an environment. Can throw an {@link IllegalArgumentException}.
     * 
     * @param resp
     *            the responsibility
     * @param env
     *            the environment
     * @return the result of the valuation, should be {@link jUCMNavType#VOID}.
     */
    public static Object evaluate(RespRef resp, UcmEnvironment env) {
        if (isEmptyResponsibility(resp)) {
            return null;
        } else
            return evaluate(resp.getRespDef().getExpression(), env, true);
    }

    /**
     * Evaluate this code in an environment. Can throw an {@link IllegalArgumentException}.
     * 
     * @param code
     *            the code
     * @param env
     *            the environment
     * @param isResponsibility
     *            is it a responsibility (true) or a condition (false)
     * @return the result of the evaluation
     */
    public static Object evaluate(String code, UcmEnvironment env, boolean isResponsibility) {
        Object res = parse(code, env, isResponsibility);
        if (res instanceof String)
            throw new IllegalArgumentException(res.toString());
        else if (res instanceof SimpleNode)
            return UcmExpressionEvaluator.evaluate((SimpleNode) res, env);
        else
            return res;
    }

    /**
     * Returns the last run {@link ScenarioDef}, {@link ScenarioGroup} or {@link UCMspec} in the jucm file containing obj.
     * 
     * @param obj
     *            an object for which an environment is associated.
     * @return the last run element.
     */
    public static EObject getActiveScenario(EObject obj) {
        UcmEnvironment initial = getEnvironment(obj);
        if (activeScenario.containsKey(initial))
            return (EObject) activeScenario.get(initial); // can be ScenarioDef, ScenarioGroup or UCMspec
        else
            return null;
    }

    /**
     * Returns a list of all ScenarioDefs in all groups.
     * 
     * @param urn
     *            the root urnspec
     * @return the list of scenarios
     */
    public static List getAllScenarios(URNspec urn) {
        ArrayList list = new ArrayList();
        for (Iterator iter = urn.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
            ScenarioGroup group = (ScenarioGroup) iter.next();

            for (Iterator iterator = group.getScenarios().iterator(); iterator.hasNext();) {
                ScenarioDef scenario = (ScenarioDef) iterator.next();
                list.add(scenario);
            }
        }
        return list;
    }

    /**
     * Get all {@link ScenarioEndPoint} (recursively) that are related this scenario (inherited first, then defined).
     * 
     * @param def
     *            the scenario
     * @return a List of {@link ScenarioEndPoint}
     */
    public static Vector getDefinedEndPoints(ScenarioDef def) {
        Vector endPoints = new Vector();
        getDefinedEndPoints(def, endPoints);
        return endPoints;
    }

    /**
     * Get all {@link ScenarioEndPoint} (recursively) that are related to this scenario(inherited first, then defined).
     * 
     * @param def
     *            the scenario
     * @param endPoints
     *            where to insert the found {@link ScenarioEndPoint}
     */
    private static void getDefinedEndPoints(ScenarioDef def, Vector endPoints) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedEndPoints(scenario, endPoints);
        }

        for (Iterator iter = def.getEndPoints().iterator(); iter.hasNext();) {
            ScenarioEndPoint pt = (ScenarioEndPoint) iter.next();
            if (!endPoints.contains(pt))
                endPoints.add(pt);
        }
    }

    /**
     * Get all the included scenarios (recursively)that are related to this scenario
     * 
     * @param def
     *            the scenario
     * @return the list of {@link ScenarioDef}
     */
    public static Vector getDefinedIncludedScenarios(ScenarioDef def) {
        Vector scenarios = new Vector();
        getDefinedIncludedScenarios(def, scenarios);
        return scenarios;
    }
    
    /**
     * For each ScenarioDef which is an explicit child of def, we return the index inside the vector returned by getDefinedIncludedScenarios
     * 
     * @param def the scenario
     * @return the list of indexes in the getDefinedIncludedScenarios list. 
     */
    public static Vector getIndexesOfPrimaryDefinedIncludedScenarios(ScenarioDef def) {
        Vector all = getDefinedIncludedScenarios(def);
        Vector indexes = new Vector();
        for (int i=0;i<def.getIncludedScenarios().size();i++)
        {
            // add the index of the scenario in this list. 
            // given how we merge included scenarios (to avoid duplication), this list is non-obvious  
            indexes.add(new Integer(all.indexOf(def.getIncludedScenarios().get(i))));
        }
        return indexes;
    }
    
    

    /**
     * Get all the included scenarios (recursively)that are related to this scenario
     * 
     * @param def
     *            the scenario
     * @param scenarios
     *            where to insert the found {@link ScenarioDef}s
     */
    private static void getDefinedIncludedScenarios(ScenarioDef def, Vector scenarios) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedIncludedScenarios(scenario, scenarios);
            if (!scenarios.contains(scenario))
                scenarios.add(scenario);
        }
    }

    /**
     * Gets all the related variable {@link Initialization}s (recursively). Filters out any inherited duplicates to show only the last one.
     * 
     * @param def
     *            the scenario
     * @return the list of {@link Initialization}s
     */
    public static Vector getDefinedInitializations(ScenarioDef def) {

        Vector initializations = new Vector();
        if (def.getGroup() == null)
            return initializations;
        getDefinedInitializations(def, initializations);

        Vector uniqueSubsetInitializations = new Vector();
        for (Iterator iter = def.getGroup().getUcmspec().getVariables().iterator(); iter.hasNext();) {
            Variable var = (Variable) iter.next();
            // only add last occurrence
            for (int i = initializations.size() - 1; i >= 0; i--) {
                Initialization init = (Initialization) initializations.get(i);
                if (init.getVariable() == var) {
                    uniqueSubsetInitializations.add(init);
                    break;
                }
            }

        }
        return uniqueSubsetInitializations;
    }

    /**
     * Gets all the related variable {@link Initialization}s (recursively). Filters out any inherited duplicates to show only the last one.
     * 
     * @param def
     *            the scenario param initializations where to store the found {@link Initialization}s
     */
    private static void getDefinedInitializations(ScenarioDef def, Vector initializations) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedInitializations(scenario, initializations);
        }

        for (Iterator iter = def.getInitializations().iterator(); iter.hasNext();) {
            Initialization cond = (Initialization) iter.next();
            if (!initializations.contains(cond))
                initializations.add(cond);
        }
    }

    /**
     * Gets all the related scenario post conditions (recursively).
     * 
     * @param def
     *            the scenario
     * @return a list of {@link Condition}s
     */
    public static Vector getDefinedPostconditions(ScenarioDef def) {
        Vector postconditions = new Vector();
        getDefinedPostconditions(def, postconditions);
        return postconditions;
    }

    /**
     * Gets all the related scenario post conditions (recursively).
     * 
     * @param def
     *            the scenario param postconditions where to add the found {@link Condition}s
     */
    private static void getDefinedPostconditions(ScenarioDef def, Vector postconditions) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedPostconditions(scenario, postconditions);
        }

        for (Iterator iter = def.getPostconditions().iterator(); iter.hasNext();) {
            Condition cond = (Condition) iter.next();
            if (!postconditions.contains(cond))
                postconditions.add(cond);
        }
    }

    /**
     * Gets all the related scenario pre conditions (recursively).
     * 
     * @param def
     *            the scenario
     * @return a list of {@link Condition}s
     */
    public static Vector getDefinedPreconditions(ScenarioDef def) {
        Vector preconditions = new Vector();
        getDefinedPreconditions(def, preconditions);
        return preconditions;
    }

    /**
     * Gets all the related scenario pre conditions (recursively).
     * 
     * @param def
     *            the scenario param postconditions where to add the found {@link Condition}s
     */
    private static void getDefinedPreconditions(ScenarioDef def, Vector preconditions) {
        for (Iterator iter = def.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedPreconditions(scenario, preconditions);
        }

        for (Iterator iter = def.getPreconditions().iterator(); iter.hasNext();) {
            Condition cond = (Condition) iter.next();
            if (!preconditions.contains(cond))
                preconditions.add(cond);
        }
    }

    /**
     * Get all {@link ScenarioStartPoint} (recursively) that are related this scenario (inherited first, then defined).
     * 
     * @param def
     *            the scenario
     * @return a List of {@link ScenarioStartPoint}
     */
    public static Vector getDefinedStartPoints(ScenarioDef def) {
        Vector startPoints = new Vector();
        getDefinedStartPoints(def, startPoints);
        return startPoints;
    }

    /**
     * Get all {@link ScenarioStartPoint} (recursively) that are related to this scenario (inherited first, then defined).
     * 
     * @param def
     *            the scenario
     * @param startPoints
     *            where to insert the found {@link ScenarioStartPoint}
     */
    private static void getDefinedStartPoints(ScenarioDef def, Vector startPoints) {
        for (Iterator iter = getDefinedIncludedScenarios(def).iterator(); iter.hasNext();) {
            ScenarioDef scenario = (ScenarioDef) iter.next();
            getDefinedStartPoints(scenario, startPoints);
        }

        for (Iterator iter = def.getStartPoints().iterator(); iter.hasNext();) {
            ScenarioStartPoint pt = (ScenarioStartPoint) iter.next();
            if (!startPoints.contains(pt))
                startPoints.add(pt);
        }
    }

    /**
     * Return the UcmEnvironment associated with this object from the global cache.
     * 
     * @param object
     *            an EObject from which we can reach the URNspec via object.eContainer()
     * 
     * @return null if can't reach the URNspec, a UcmEnvironment (newly created or retrieved from cache) otherwise.
     */
    public static synchronized UcmEnvironment getEnvironment(EObject object) {
        if (object == null)
            return null;

        if (object instanceof URNspec) {
            if (!getEnvironments().containsKey(object)) {
                getEnvironments().put(object, new UcmEnvironment((URNspec) object));
            }
            return (UcmEnvironment) getEnvironments().get(object);
        } else
            return getEnvironment(object.eContainer());

    }

    /**
     * Global UcmEnvironment cache.
     * 
     * @return a HashMap of URNspec -> UcmEnvironment
     */
    private static synchronized HashMap getEnvironments() {
        if (environments == null)
            environments = new HashMap();

        return environments;
    }

    /**
     * Returns all scenarios that we may include into the given parent. Will not cause any circular references.
     * 
     * @param parent
     *            the parent scenario definition
     * @return the list of possible children.
     */
    public static List getPossibleIncludedScenarios(ScenarioDef parent) {
        List list = getPossibleIncludedScenariosNonRecursive(parent);

        ArrayList toRemove = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ScenarioDef child = (ScenarioDef) iter.next();
            if (!getPossibleIncludedScenariosNonRecursive(child).contains(parent))
                toRemove.add(child);
        }
        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            ScenarioDef element = (ScenarioDef) iter.next();
            if (list.contains(element))
                list.remove(element);
        }
        return list;
    }

    /**
     * Gets the list of scenarios that it would be possible to include, without recursing. Used in a context where recursion would cause an infinite loop.
     * 
     * @param parent
     *            the scenario
     * @return the list of possible {@link ScenarioDef}
     */
    private static List getPossibleIncludedScenariosNonRecursive(ScenarioDef parent) {
        if (parent.getGroup() == null)
            return new ArrayList();
        URNspec urn = parent.getGroup().getUcmspec().getUrnspec();
        List list = getAllScenarios(urn);

        removeIncludedScenarios(list, parent);
        return list;

    }

    /**
     * 
     * @param obj
     *            an object
     * @return the number of times the traversal engine passed through it.
     */
    public static int getTraversalHitCount(EObject obj) {
        TraversalResult res = getTraversalResults(obj);
        if (res != null)
            return res.getExternalHitCount();
        else
            return 0;
    }

    /**
     * 
     * @param obj
     *            an object
     * @return the {@link TraversalResult} of a certain object.
     */
	public static TraversalResult getTraversalResults(EObject obj) {
        UcmEnvironment env = getEnvironment(obj);
        if (traversals.containsKey(env)) {
            IScenarioTraversalAlgorithm results = (IScenarioTraversalAlgorithm) traversals.get(env);
            return results.getTraversalResults(obj);
        } else
            return null;
    }

    /**
     * Is this condition null, empty or a default "true"
     * 
     * @param cond
     *            the condition
     * @return true if it is.
     */
    public static boolean isEmptyCondition(Condition cond) {
        // "true" is the default for most conditions. don't want to load the big infrastructure.
        return cond == null || cond.getExpression() == null || cond.getExpression().length() == 0 || "true".equals(cond.getExpression()); //$NON-NLS-1$
    }

    /**
     * Is this condition null, empty or a default "true"
     * 
     * @param cond
     *            the condition
     * @return true if it is.
     */
    public static boolean isEmptyCondition(String cond) {
        // "true" is the default for most conditions. don't want to load the big infrastructure.
        return cond == null || cond.length() == 0 || "true".equals(cond); //$NON-NLS-1$
    }

    /**
     * Is this responsibilitiy null or empty?
     * 
     * @param resp
     *            the responsibility
     * @return true if it is.
     */
    public static boolean isEmptyResponsibility(Responsibility resp) {
        if (resp == null)
            return true;
        else
            return isEmptyResponsibility(resp.getExpression());
    }

    /**
     * Is this responsibilitiy null or empty?
     * 
     * @param resp
     *            the responsibility
     * @return true if it is.
     */
    public static boolean isEmptyResponsibility(RespRef resp) {
        if (resp == null)
            return true;
        else
            return isEmptyResponsibility(resp.getRespDef());
    }

    /**
     * Is this responsibilitiy null or empty?
     * 
     * @param code
     *            the code
     * @return true if it is.
     */
    public static boolean isEmptyResponsibility(String code) {
        return code == null || code.length() == 0 || code.replace("\n", "").replace("\r", "").trim().length() == 0; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    /**
     * Parses a string and returns an error message if is not valid (as a string) or a SimpleNode AST of the code if it is. Does both syntax checking and type
     * checking.
     * 
     * @param code
     *            the code to be parsed
     * @param env
     *            the environment in which it should be parsed
     * @param isResponsibility
     *            is this a responsibility or a condition?
     * @return a SimpleNode instance if valid, a String otherwise.
     */

    public static Object parse(String code, UcmEnvironment env, boolean isResponsibility) {
        return parse(code, env, isResponsibility, isResponsibility ? null : jUCMNavType.BOOLEAN);
    }

    public static Object parseInteger(String code, UcmEnvironment env) {
        return parse(code, env, false, jUCMNavType.INTEGER);
    }

    /**
     * Parses a string and returns an error message if is not valid (as a string) or a SimpleNode AST of the code if it is. Does both syntax checking and type
     * checking.
     * 
     * @param code
     *            the code to be parsed
     * @param env
     *            the environment in which it should be parsed
     * @param isResponsibility
     *            is this a responsibility or a condition?
     * @param expectedType
     *            the expected type
     * @return a SimpleNode instance if valid, a String otherwise.
     */

    private static Object parse(String code, UcmEnvironment env, boolean isResponsibility, jUCMNavType expectedType) {
        SimpleNode n = null;

        // syntax checking
        try {

            jUCMNavParser.ReInit(new StringReader(code));
            if (isResponsibility)
                n = jUCMNavParser.StartResponsibility();
            else
                n = jUCMNavParser.Start();

        } catch (Throwable t) {
            return Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
        }

        // type checking
        try {
            jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);
            if (isResponsibility) {
                if (!checker.isValidResponsibility()) {
                    return Messages.getString("ScenarioUtils.IsNotAValidResponsibility"); //$NON-NLS-1$
                }
            } else {
                if (expectedType == jUCMNavType.INTEGER) {
                    if (!checker.isValidInteger()) {
                        return Messages.getString("ScenarioUtils.IsNotAValidInteger"); //$NON-NLS-1$
                    }
                } else if (!checker.isValidCondition()) {
                    return Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
                }
            }
        } catch (IllegalArgumentException ex) {
            return Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
        }

        // return the object.
        return n;
    }

    /**
     * Removes the UcmEnvironment associated with the object from the global cache.
     * 
     * @param object
     */
    public static synchronized void releaseEnvironment(EObject object) {
        if (object == null)
            return;

        if (object instanceof URNspec) {
            getEnvironments().remove(object);
        } else
            releaseEnvironment(object.eContainer());
    }

    /**
     * Recursively removes all the included scenarios from the given list.
     * 
     * @param list
     *            list of ScenarioDefs
     * @param parent
     *            the root scenariodef from which we remove the children. we also remove the parent from the list.
     */
    private static void removeIncludedScenarios(List list, ScenarioDef parent) {
        for (Iterator iter = parent.getIncludedScenarios().iterator(); iter.hasNext();) {
            ScenarioDef child = (ScenarioDef) iter.next();
            removeIncludedScenarios(list, child);
        }

        if (list.contains(parent))
            list.remove(parent);
    }

    /**
     * Set the active scenario, no extra listeners.
     * 
     * @param scenario
     *            the scenario
     */
    public static void setActiveScenario(ScenarioDef scenario) {
        traverse(scenario, null);
    }

    /**
     * Set the active scenario, with the extra listeners passed.
     * 
     * @param scenario
     *            the scenario
     * @param listeners
     *            the extra {@link ITraversalListener}
     */
    public static void setActiveScenario(ScenarioDef scenario, Vector listeners) {
        traverse(scenario, listeners);
    }

    /**
     * Set the active scenario group, no extra listeners.
     * 
     * @param group
     *            the scenario group
     */
    public static void setActiveScenario(ScenarioGroup group) {
        traverse(group, null);
    }

    /**
     * Set the active scenario group, with the extra listeners passed.
     * 
     * @param group
     *            the scenario group
     * @param listeners
     *            the extra {@link ITraversalListener}
     */
    public static void setActiveScenario(ScenarioGroup group, Vector listeners) {
        traverse(group, listeners);
    }

    /**
     * Set the active ucmspec, no extra listeners.
     * 
     * @param ucm
     *            the ucmspec
     */
    public static void setActiveScenario(UCMspec ucm) {
        traverse(ucm, null);
    }

    /**
     * Set the active ucmspec, with the extra listeners passed.
     * 
     * @param ucm
     *            the ucmspec
     * @param listeners
     *            the extra {@link ITraversalListener}
     */
    public static void setActiveScenario(UCMspec ucm, Vector listeners) {
        traverse(ucm, listeners);
    }

    /**
     * Traverse the active secenario.
     * 
     * @param scenario
     *            the {@link ScenarioDef}, {@link ScenarioGroup}, or {@link UCMspec}
     * @param listeners
     *            an optional list of extra {@link ITraversalListener} to add.
     */
    protected static void traverse(EObject scenario, Vector listeners) {
        try {
            if (scenario == null) {
                System.out.println("Use clearActiveScenario instead"); //$NON-NLS-1$
                return;
            }
            UcmEnvironment initial = getEnvironment(scenario);
            
            if (initial ==null) {
                clearActiveScenario(scenario);
                return; 
            }
            
            URNspec urn = initial.getUrn();
            
            if(urn!= null)
            {
				restoreOriginalValue(urn.getUcmspec());
              MetadataHelper.cleanTimedUCMMetadata(urn);
                
                if(ScenarioTraversalPreferences.getIsTimedUcmEnabled())
                {
                    UCMspec ucm = urn.getUcmspec();
                    ucm = preProcessTimedUCM(ucm ,dynContext,tp);
                }         
            }
            
            if (activeScenario.containsKey(initial))
                clearTraversalResults(initial);
            activeScenario.put(initial, scenario);
            UcmEnvironment forTraversal = (UcmEnvironment) initial.clone();

            IScenarioTraversalAlgorithm algo = new ScenarioTraversalAlgorithm();
            if (scenario instanceof ScenarioDef)
                algo.init(forTraversal, (ScenarioDef) scenario);
            else if (scenario instanceof ScenarioGroup)
                algo.init(forTraversal, (ScenarioGroup) scenario);
            else if (scenario instanceof UCMspec)
                algo.init(forTraversal, (UCMspec) scenario);
            else
                System.out.println("undefined initialization"); //$NON-NLS-1$

            traversals.put(initial, algo);

            // TODO: add listeners from extension point

            if (listeners != null)
                algo.addListeners(listeners);

            algo.traverse();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (TraversalException e) {
            MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", e.getMessage()); //$NON-NLS-1$
        }
    }
    
	public static UCMspec applychange(UCMspec ucm, Change change, Timepoint tp) {
		if (change instanceof DeactivationChange) {
    		if (change.getElement() instanceof Responsibility)
				MetadataHelper.addMetaData(ucm.getUrnspec(), (Responsibility) change.getElement(), EvaluationStrategyManager.METADATA_DEACTSTATUS, "true");
			else if (change.getElement() instanceof FailurePoint)
				MetadataHelper.addMetaData(ucm.getUrnspec(), (FailurePoint) change.getElement(), EvaluationStrategyManager.METADATA_DEACTSTATUS, "true");
			else if (change.getElement() instanceof Component) {
				MetadataHelper.addMetaData(ucm.getUrnspec(), (Component) change.getElement(), EvaluationStrategyManager.METADATA_DEACTSTATUS, "true");
    			
				List<ComponentRef> includedComponentReferences = getComponentReferencesRecursively((Component) change.getElement());
    			for (ComponentRef cr : includedComponentReferences) {
  					MetadataHelper.addMetaData(ucm.getUrnspec(), (Component) cr.getContDef(), EvaluationStrategyManager.METADATA_DEACTSTATUS, "true");
    				
  					List<PathNode> nodes = (List<PathNode>) cr.getNodes();
    				for (PathNode p : nodes) {
						if (p instanceof RespRef) {
							String deactStatus = MetadataHelper.getMetaData(((RespRef) p).getRespDef(), EvaluationStrategyManager.METADATA_DEACTSTATUS);
							if (!(deactStatus != null && deactStatus.equalsIgnoreCase("true")) && ScenarioTraversalPreferences.getIsTimedUcmEnabled())
								MetadataHelper.addMetaData(ucm.getUrnspec(), ((RespRef) p).getRespDef(), EvaluationStrategyManager.METADATA_DEACTSTATUS, "true");
    					}
    			}
				}
			}
			else if (change.getElement() instanceof PluginBinding) {
				String pluginID = null;
				Metadata s = MetadataHelper.getMetaDataObj(((PluginBinding) change.getElement()).getStub(), EvaluationStrategyManager.METADATA_DEACTSTATUS);
				if (s != null)
					pluginID = s.getValue();
				else
					pluginID = "PluginMap IDs: ";

				String bindingID = ((PluginBinding) (change.getElement())).getPlugin().getId();
				if (pluginID.equals("PluginMap IDs: "))
					MetadataHelper.addMetaData(ucm.getUrnspec(), ((PluginBinding) change.getElement()).getStub(), EvaluationStrategyManager.METADATA_DEACTSTATUS, pluginID + bindingID);
				else
					MetadataHelper.addMetaData(ucm.getUrnspec(), ((PluginBinding) change.getElement()).getStub(), EvaluationStrategyManager.METADATA_DEACTSTATUS, pluginID + "," + bindingID);
			}       		
		} else if (change instanceof EnumChange) {
			EnumChange enumChange = (EnumChange) change;

			if (change.getElement() instanceof StartPoint) {
				StartPoint element = (StartPoint) change.getElement();
				FailureKind origFailureKind = element.getFailureKind();
				MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGFAILUREKIND, origFailureKind.getLiteral());

				if (enumChange.getNewValue().equals("FAILURE"))
					element.setFailureKind(FailureKind.FAILURE_LITERAL);
				else if (enumChange.getNewValue().equals("ABORT"))
					element.setFailureKind(FailureKind.ABORT_LITERAL);
				else if (enumChange.getNewValue().equals("NONE"))
					element.setFailureKind(FailureKind.NONE_LITERAL);
			} else if (change.getElement() instanceof WaitingPlace) {
				WaitingPlace element = (WaitingPlace) change.getElement();
				WaitKind origWaitKind = element.getWaitType();
				MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGWAITKIND, origWaitKind.getLiteral());

				if (enumChange.getNewValue().equals("TRANSIENT"))
					element.setWaitType(WaitKind.TRANSIENT_LITERAL);
				else if (enumChange.getNewValue().equals("PERSISTENT"))
					element.setWaitType(WaitKind.PERSISTENT_LITERAL);    			
			} else if (change.getElement() instanceof Component) {
				Component element = (Component) change.getElement();
				ComponentKind origCompKind = element.getKind();
				MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGCOMPKIND, origCompKind.getLiteral());

				if (enumChange.getNewValue().equals("TEAM"))
					element.setKind(ComponentKind.TEAM_LITERAL);
				else if (enumChange.getNewValue().equals("OBJECT"))
					element.setKind(ComponentKind.OBJECT_LITERAL);
				else if (enumChange.getNewValue().equals("PROCESS"))
					element.setKind(ComponentKind.PROCESS_LITERAL);
				else if (enumChange.getNewValue().equals("AGENT"))
					element.setKind(ComponentKind.AGENT_LITERAL);
				else if (enumChange.getNewValue().equals("ACTOR"))
					element.setKind(ComponentKind.ACTOR_LITERAL);
				else if (enumChange.getNewValue().equals("OTHER"))
					element.setKind(ComponentKind.OTHER_LITERAL);
			}   		
		} else if (change instanceof BooleanChange) {
			BooleanChange booleanChange = (BooleanChange) change;

			if (change.getElement() instanceof Stub) {
				Stub element = (Stub) change.getElement();
				boolean dynStub = element.isDynamic();
				boolean syncStub = element.isSynchronization();
				boolean blockStub = element.isBlocking();
				boolean staticStub = !(element.isDynamic() || element.isSynchronization() || element.isBlocking());

				if (!(element.isDynamic() || element.isSynchronization() || element.isBlocking()))
					MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGSTUB + ".staticStub", String.valueOf(staticStub));
				else if (element.isDynamic())
					MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGSTUB + ".dynStub", String.valueOf(dynStub));
				else if (element.isSynchronization())
					MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGSTUB + ".syncStub", String.valueOf(syncStub));
				else if (element.isBlocking())
					MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGSTUB + ".blockStub", String.valueOf(blockStub));

				if (booleanChange.getAffectedProperty().equals("Dynamic Stub"))
					element.setDynamic(((BooleanChange) change).isNewValue());
				else if (booleanChange.getAffectedProperty().equals("Synchronizing Stub"))
					element.setSynchronization(((BooleanChange) change).isNewValue());
				else if (booleanChange.getAffectedProperty().equals("Blocking Stub"))
					element.setBlocking(((BooleanChange) change).isNewValue());
			} else if (change.getElement() instanceof Component) {
				Component element = (Component) change.getElement();

				if (booleanChange.getAffectedProperty().equals("Component Protection")) {
					boolean compProtect = element.isProtected();
					if (element.isProtected())
						MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGCOMPONENT + ".compProtect", String.valueOf(compProtect));
					else
						MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGCOMPONENT + ".compProtect", String.valueOf(compProtect));
				}

				if (booleanChange.getAffectedProperty().equals("Component Context")) {
					boolean compContext = element.isContext();
					if (element.getKind().getLiteral().equals("Team") && element.isContext() && !(element.isProtected()))
						MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGCOMPONENT + ".compContext", String.valueOf(compContext));
					else
						MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGCOMPONENT + ".compContext", String.valueOf(compContext));
				}

				if (booleanChange.getAffectedProperty().equals("Component Protection"))
					element.setProtected(((BooleanChange) change).isNewValue());
				if (booleanChange.getAffectedProperty().equals("Component Context"))
					element.setContext(((BooleanChange) change).isNewValue());

			}
		} else if (change instanceof TextChange) {
			TextChange textChange = (TextChange) change;

			if (change.getElement() instanceof PluginBinding) {
				PluginBinding element = (PluginBinding) change.getElement();

				if (textChange.getAffectedProperty().contains("Replication factor"))
					MetadataHelper.addMetaData(ucm.getUrnspec(), element.getStub(), METADATA_ORIGREPFACTOR + "." + element.getPlugin().getName(), String.valueOf(element.getReplicationFactor()));

				if (textChange.getAffectedProperty().contains("Replication factor"))
					element.setReplicationFactor(Integer.parseInt(((TextChange) change).getNewValue()));
			} else if (change.getElement() instanceof urncore.Condition) {
				urncore.Condition element = (Condition) change.getElement();

				if (textChange.getAffectedProperty().startsWith("Pre-Condition")) {
					if (element.getExpression() != null)
						MetadataHelper.addMetaData(ucm.getUrnspec(), element.getPluginBinding().getStub(), METADATA_ORIGPLUGINCONDITION + "." + element.getPluginBinding().getPlugin().getName(), element.getExpression());
					else
						MetadataHelper.addMetaData(ucm.getUrnspec(), element.getPluginBinding().getStub(), METADATA_ORIGPLUGINCONDITION + "." + element.getPluginBinding().getPlugin().getName(), "true");
				}
				if (textChange.getAffectedProperty().startsWith("Pre-Condition"))
					element.setExpression(((TextChange) change).getNewValue());


				if (textChange.getAffectedProperty().equals("Failure List/Start Point Pre-Condition")) {
					if (element.getStartPoint().getPrecondition().getExpression() != null)
						MetadataHelper.addMetaData(ucm.getUrnspec(), element.getStartPoint(), METADATA_ORIGFAILURELIST, element.getStartPoint().getPrecondition().getExpression());
					else
						MetadataHelper.addMetaData(ucm.getUrnspec(), element.getStartPoint(), METADATA_ORIGFAILURELIST, "true");
				}
				if (textChange.getAffectedProperty().equals("Failure List/Start Point Pre-Condition"))
					element.getStartPoint().getPrecondition().setExpression(((TextChange) change).getNewValue());

				if (textChange.getAffectedProperty().equals("Post-Condition")) {
					if (element.getEndPoint().getPostcondition().getExpression() != null)
						MetadataHelper.addMetaData(ucm.getUrnspec(), element.getEndPoint(), METADATA_ORIGENDPTPOSTCOND, element.getEndPoint().getPostcondition().getExpression());
					else
						MetadataHelper.addMetaData(ucm.getUrnspec(), element.getEndPoint(), METADATA_ORIGENDPTPOSTCOND, "true");
				}
				if (textChange.getAffectedProperty().equals("Post-Condition"))
					element.getEndPoint().getPostcondition().setExpression(((TextChange) change).getNewValue());

				if (textChange.getAffectedProperty().startsWith("Expression")) {
					NodeConnection ncs = element.getNodeConnection();


					if (ncs.getSource() instanceof OrFork && ncs.getCondition().getExpression() != null) {
						String branchName = "Branch: " + (ncs.getSource().getSucc().indexOf(ncs) + 1);
						String orForkID = ((OrFork) ncs.getSource()).getId();
						MetadataHelper.addMetaData(ucm.getUrnspec(), (OrFork) ncs.getSource(), METADATA_ORIGORFORKEXP + " (" + orForkID + ") <->" + branchName, ncs.getCondition().getExpression());
					}
					else
						MetadataHelper.addMetaData(ucm.getUrnspec(), (OrFork) ncs.getSource(), METADATA_ORIGORFORKEXP, "true");

				}	
				if (textChange.getAffectedProperty().startsWith("Expression"))
					element.getNodeConnection().getCondition().setExpression(((TextChange) change).getNewValue());

				if (textChange.getAffectedProperty().equals("Failure Condition")) {
					NodeConnection ncs = element.getNodeConnection();

					if (ncs.getSource() instanceof FailurePoint && ncs.getCondition().getExpression() != null)
						MetadataHelper.addMetaData(ucm.getUrnspec(), (FailurePoint) ncs.getSource(), METADATA_ORIGFAILUREPTCOND, element.getNodeConnection().getCondition().getExpression());
					else
						MetadataHelper.addMetaData(ucm.getUrnspec(), (FailurePoint) ncs.getSource(), METADATA_ORIGFAILUREPTCOND, "true");
				}
				if (textChange.getAffectedProperty().equals("Failure Condition"))
					element.getNodeConnection().getCondition().setExpression(((TextChange) change).getNewValue());

			} else if (change.getElement() instanceof FailurePoint) {
				FailurePoint element = (FailurePoint) change.getElement();

				if (textChange.getAffectedProperty().equals("Failure Expression")) {
					if (element.getExpression() != null)
						MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGFAILUREEXPRESSION, element.getExpression());
					else
						MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGFAILUREEXPRESSION, "");
				}

				if (textChange.getAffectedProperty().equals("Failure Expression"))
					element.setExpression(((TextChange) change).getNewValue());
			} else if (change.getElement() instanceof Responsibility) {
				Responsibility element = (Responsibility) change.getElement();

				if (textChange.getAffectedProperty().equals("Responsibility Expression")) {
					if (element.getExpression() != null)
						MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGRESPEXPRESSION, element.getExpression());
					else
						MetadataHelper.addMetaData(ucm.getUrnspec(), element, METADATA_ORIGRESPEXPRESSION, "");
				}

				if (textChange.getAffectedProperty().equals("Responsibility Expression"))
					element.setExpression(((TextChange) change).getNewValue());
			}
    	}
    
    	return ucm;
    }     	
    
    protected static List<Change> collectChanges (DynamicContext dynContext, Timepoint tp, List affected) {
    	List<Change> changes = new ArrayList<Change>();
    	try {
    		if(dynContext!= null){
    			for (Iterator j = dynContext.getChanges().iterator(); j.hasNext();){
    				Change change = (Change) j.next();
    				String affectedProp = change.getElement().toString();
    				if ((tp.getTimepoint().after(change.getStart()) || tp.getTimepoint().equals(change.getStart())) && tp.getTimepoint().before(change.getEnd())) {
    					  					
    					if (change instanceof DeactivationChange) {
    						affectedProp = affectedProp + ".deactivate";
    					} 
    					if (!affected.contains(affectedProp)) {
    						changes.add(change);
    						affected.add(affectedProp);
    					}
    				} else if (tp.getTimepoint().equals(change.getEnd())) {
    					if (change instanceof DeactivationChange) {
    						affectedProp = affectedProp + ".deactivate";
    					} else if (change instanceof PropertyChange) {
    		    			PropertyChange propChange = (PropertyChange) change;
    		    			boolean consecutiveChangeExists = false;
        					Change consecutiveChange = null;
        					//Check if there is a consecutive change (The change directly following takes preference)
        					for (Iterator j2 = dynContext.getChanges().iterator(); j2.hasNext();){
        						Change change1 = (Change) j2.next();
        						if (change1.getStart().equals(change.getEnd()) && change1.getElement() == change.getElement()) {
        							consecutiveChangeExists = true;
        							consecutiveChange = change1;
        							break;
        						}	        		
        					}
    		    			if (consecutiveChangeExists && consecutiveChange instanceof PropertyChange && 
    		    					((PropertyChange) consecutiveChange).getAffectedProperty().equals(propChange.getAffectedProperty()))
    		    				continue;
    		    			else
    			    			affectedProp = affectedProp + propChange.getAffectedProperty();
    		    		}
    					if (!affected.contains(affectedProp)) {
    						changes.add(change);
    						affected.add(affectedProp);
    					}
    				}
    			}
  
    			// handle included contexts
    			for (Iterator j1 = dynContext.getIncludedContexts().iterator(); j1.hasNext();){
    				DynamicContext incDynContext = (DynamicContext) j1.next();
    				changes.addAll(collectChanges(incDynContext, tp, affected));
    			}
    		}
		} catch (NullPointerException e) {
    	}
    	
    	return changes;
    }
    
    protected static UCMspec preProcessTimedUCM(UCMspec ucm, DynamicContext dynContext, Timepoint tp) {
    	UCMspec updatedUCMmodel = ucm;
    	List<String> affected = new ArrayList<String>();
    	
    	List<Change> changes = collectChanges(dynContext, tp, affected);
       	    
    	if (changes.size() != 0) {
			List<Change> ucmElementsChanges = new ArrayList<Change>();
    		for (Iterator j = changes.iterator(); j.hasNext();){ 
    			Change change = (Change) j.next();
				if (change.getElement() instanceof Responsibility || change.getElement() instanceof Component
						|| change.getElement() instanceof FailurePoint || change.getElement() instanceof Timer
						|| change.getElement() instanceof WaitingPlace || change.getElement() instanceof StartPoint
						|| change.getElement() instanceof PluginBinding || change.getElement() instanceof Stub
						|| change.getElement() instanceof EndPoint || change.getElement() instanceof OrFork
						|| change.getElement() instanceof urncore.Condition)
					ucmElementsChanges.add(change);
    		}
			if (ucmElementsChanges.size() != 0) {
				for (Iterator j = ucmElementsChanges.iterator(); j.hasNext();){ 
    				Change change = (Change) j.next();
    				updatedUCMmodel = applychange(updatedUCMmodel, change, tp);
    			}
    		}
    	}
    
    	return updatedUCMmodel;
    }
    
	public static List<ComponentRef> getComponentReferencesRecursively(Component c) {
    	List<ComponentRef> componentRefs = c.getContRefs();
    	List<ComponentRef> finalComponentReferences = new ArrayList<ComponentRef>();
    	finalComponentReferences.addAll(componentRefs);
    	List<ComponentRef> includedComponentReferences = new ArrayList<ComponentRef>();
    	
    	for (ComponentRef componentReference : componentRefs){
    		List<ComponentRef> candidates = componentReference.getChildren();
    		for(ComponentRef candidate: candidates){
    			if(!(includedComponentReferences.contains(candidate)))
        			includedComponentReferences.add(candidate);
    		}    		
    	}
    	
    	for (ComponentRef componentRef: includedComponentReferences){
    		List<ComponentRef> candidates = getComponentReferencesRecursively((Component) componentRef.getContDef());
    		for(ComponentRef candidate: candidates){
    			if(!(finalComponentReferences.contains(candidate)))
        			finalComponentReferences.add(candidate);
    		}
    	}

    	return finalComponentReferences;
    }
        
    public static Vector traverseWarn(EObject scenario, Vector listeners) {//**************
        try {
            if (scenario == null) {
                System.out.println("Use clearActiveScenario instead"); //$NON-NLS-1$
                return (Vector)null;//***********************
            }
            UcmEnvironment initial = getEnvironment(scenario);
            
            if (initial ==null) {
                clearActiveScenario(scenario);
                return (Vector)null; //***********
            }
            if (activeScenario.containsKey(initial))
                clearTraversalResults(initial);
            activeScenario.put(initial, scenario);
            UcmEnvironment forTraversal = (UcmEnvironment) initial.clone();

            IScenarioTraversalAlgorithm algo = new ScenarioTraversalAlgorithm();
            if (scenario instanceof ScenarioDef)
                algo.init(forTraversal, (ScenarioDef) scenario);
            else if (scenario instanceof ScenarioGroup)
                algo.init(forTraversal, (ScenarioGroup) scenario);
            else if (scenario instanceof UCMspec)
                algo.init(forTraversal, (UCMspec) scenario);
            else
                System.out.println("undefined initialization"); //$NON-NLS-1$

            traversals.put(initial, algo);

            // TODO: add listeners from extension point

            if (listeners != null)
                algo.addListeners(listeners);

            algo.traverse();
            return algo.getWarnings(); //********

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return (Vector)null;
        } catch (TraversalException e) {
            MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", e.getMessage()); //$NON-NLS-1$
            return (Vector)null;
        }
    }

}
