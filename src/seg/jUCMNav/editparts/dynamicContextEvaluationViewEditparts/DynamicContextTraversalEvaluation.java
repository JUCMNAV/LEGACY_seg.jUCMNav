package seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.model.TraversalResult;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.FailureKind;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitKind;
import ucm.map.WaitingPlace;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;
import urncore.Component;
import urncore.ComponentKind;
import urncore.IURNDiagram;
import urncore.Metadata;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class DynamicContextTraversalEvaluation {

	// store TraversalCount at all the timepoints of each URNModelElement
	public static List<Entry<URNmodelElement, Float>> traversalCountListUrnModelElement = new ArrayList<Entry<URNmodelElement, Float>>();
	Map<URNmodelElement, Float> traversalCountUrnModelElement = new HashMap<URNmodelElement, Float>();

	// store TraversalCount at all the timepoints of each Node Connection
	public static List<Entry<NodeConnection, Float>> traversalCountListNodeConnection  = new ArrayList<Entry<NodeConnection, Float>>();
	Map<NodeConnection, Float> traversalCountNodeConnection = new HashMap<NodeConnection, Float>();

	// store HitCount of each URNModelElement
	public static List<Entry<URNmodelElement, Float>> hitCountListUrnModelElement = new ArrayList<Entry<URNmodelElement, Float>>();
	Map<URNmodelElement, Float> hitCountUrnModelElement = new HashMap<URNmodelElement, Float>();

	// store HitCount of each Node Connection
	public static List<Entry<NodeConnection, Float>> hitCountListNodeConnection = new ArrayList<Entry<NodeConnection, Float>>();
	Map<NodeConnection, Float> hitCountNodeConnection = new HashMap<NodeConnection, Float>();

	public static boolean timePointGroupSelected;

	public static int numberOfTimepoints;

	public DynamicContextTraversalEvaluation (DynamicContext dyn, TimepointGroup tpgroup, UCMmap ucm) {
		try {

			List<Timepoint> timepoints = tpgroup.getTimepoints();
			numberOfTimepoints = timepoints.size();

			IURNDiagram diagram = null;

			if (dyn != null && tpgroup != null && numberOfTimepoints > 0 && ScenarioTraversalPreferences.getIsTimedUcmEnabled()) {

				this.timePointGroupSelected = true;

				for (Iterator iter = dyn.getUrnspec().getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
					Responsibility resp = (Responsibility) iter.next();
					List<RespRef> respRefs = resp.getRespRefs();
					for (RespRef r : respRefs) {
						traversalCountUrnModelElement.put(r, (float) 0);
						hitCountUrnModelElement.put(r, (float) 0);
					}
				}

				for (Iterator iter = dyn.getUrnspec().getUrndef().getComponents().iterator(); iter.hasNext();) {
					Component comp = (Component) iter.next();
					traversalCountUrnModelElement.put(comp, (float) 0);
					hitCountUrnModelElement.put(comp, (float) 0);
					List<ComponentRef> includedComponentReferences = ScenarioUtils.getComponentReferencesRecursively(comp);
					for (ComponentRef cr : includedComponentReferences) {
						traversalCountUrnModelElement.put((Component) cr.getContDef(), (float) 0);
						hitCountUrnModelElement.put((Component) cr.getContDef(), (float) 0);
						List<PathNode> nodes = (List<PathNode>) cr.getNodes();
						for (PathNode p : nodes) {
							if (p instanceof RespRef) {
								traversalCountUrnModelElement.put(p, (float) 0);
								hitCountUrnModelElement.put(p, (float) 0);
							}	
						}
					}
				}

				for (Iterator iter = dyn.getUrnspec().getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
					diagram = (IURNDiagram) iter.next();
					if (diagram instanceof UCMmap) {
						for (Iterator iterIn = diagram.getNodes().iterator(); iterIn.hasNext();) {
							PathNode pn = (PathNode) iterIn.next();
							traversalCountUrnModelElement.put(pn,(float) 0);
							hitCountUrnModelElement.put(pn, (float) 0);
							List<NodeConnection> ncsSucc = pn.getSucc();
							for (NodeConnection n : ncsSucc)
							{
								hitCountNodeConnection.put(n, (float) 0);
								traversalCountNodeConnection.put(n, (float) 0);
							}
						}
					}
				}

				for (Timepoint t : timepoints) {
					ScenarioUtils.setTp(t);
					ScenarioUtils.setActiveScenario(dyn.getScenario());

					for (Iterator iter = dyn.getUrnspec().getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
						Responsibility resp = (Responsibility) iter.next();
						List<RespRef> respRefs = resp.getRespRefs();
						for (RespRef r : respRefs) {
							TraversalResult obj = ScenarioUtils.getTraversalResults(r);
							if (obj != null) {
								String deactStatus = MetadataHelper.getMetaData(r.getRespDef(), EvaluationStrategyManager.METADATA_DEACTSTATUS);
								String deactStatusRef = MetadataHelper.getMetaData(r, EvaluationStrategyManager.METADATA_DEACTSTATUS);
								if (!((deactStatus != null && deactStatus.equalsIgnoreCase("true")) || (deactStatusRef != null && deactStatusRef.equalsIgnoreCase("true")))) {
									hitCountUrnModelElement.put(r, hitCountUrnModelElement.get(r) + (float) obj.getHitCount());
									traversalCountUrnModelElement.put(r, traversalCountUrnModelElement.get(r) + (float) 1);
								}
							}
							String respExpression = MetadataHelper.getMetaData(r.getRespDef(), ScenarioUtils.METADATA_ORIGRESPEXPRESSION);
							if (respExpression != null) {
								r.getRespDef().setExpression(respExpression);
							}
						}
					}

					for (Iterator iter = dyn.getUrnspec().getUrndef().getComponents().iterator(); iter.hasNext();) {
						Component comp = (Component) iter.next();
						TraversalResult obj = ScenarioUtils.getTraversalResults(comp);
						if (obj != null) {
							String deactStatus1 = MetadataHelper.getMetaData(comp, EvaluationStrategyManager.METADATA_DEACTSTATUS);
							if (!(deactStatus1 != null && deactStatus1.equalsIgnoreCase("true") && ScenarioTraversalPreferences.getIsTimedUcmEnabled())) {
								List<ComponentRef> includedComponentReferences = ScenarioUtils.getComponentReferencesRecursively(comp);
								for (ComponentRef cr : includedComponentReferences) {
									String deactStatus2 = MetadataHelper.getMetaData((Component) cr.getContDef(), EvaluationStrategyManager.METADATA_DEACTSTATUS);
									if (!(deactStatus2 != null && deactStatus2.equalsIgnoreCase("true") && ScenarioTraversalPreferences.getIsTimedUcmEnabled())) {
										List<PathNode> nodes = (List<PathNode>) cr.getNodes();
										for (PathNode p : nodes) {
											if (p instanceof RespRef) {
												TraversalResult obj1 = ScenarioUtils.getTraversalResults(p);
												if (obj1 != null) {
													String deactStatus3 = MetadataHelper.getMetaData(p, EvaluationStrategyManager.METADATA_DEACTSTATUS);
													if (!(deactStatus3 != null && deactStatus3.equalsIgnoreCase("true") && ScenarioTraversalPreferences.getIsTimedUcmEnabled())) {
														traversalCountUrnModelElement.put(p, traversalCountUrnModelElement.get(p) + (float) 1);
														hitCountUrnModelElement.put(p, hitCountUrnModelElement.get(p) + (float) obj1.getHitCount());
													}
												}
												String respExpression = MetadataHelper.getMetaData(((RespRef) p).getRespDef(), ScenarioUtils.METADATA_ORIGRESPEXPRESSION);
												if (respExpression != null) {
													((RespRef) p).getRespDef().setExpression(respExpression);
												}
											}
										}
									}

									ComponentKind origCompKind = ((Component) cr.getContDef()).getKind();
									if (origCompKind != null) {
										String compKindChild = MetadataHelper.getMetaData((Component) cr.getContDef(), ScenarioUtils.METADATA_ORIGCOMPKIND);
										if (compKindChild != null) {
											if (compKindChild.equals("TEAM"))
												((Component) cr.getContDef()).setKind(ComponentKind.TEAM_LITERAL);
											else if (compKindChild.equals("OBJECT"))
												((Component) cr.getContDef()).setKind(ComponentKind.OBJECT_LITERAL);
											else if (compKindChild.equals("PROCESS"))
												((Component) cr.getContDef()).setKind(ComponentKind.PROCESS_LITERAL);
											else if (compKindChild.equals("AGENT"))
												((Component) cr.getContDef()).setKind(ComponentKind.AGENT_LITERAL);
											else if (compKindChild.equals("ACTOR"))
												((Component) cr.getContDef()).setKind(ComponentKind.ACTOR_LITERAL);
											else if (compKindChild.equals("OTHER"))
												((Component) cr.getContDef()).setKind(ComponentKind.OTHER_LITERAL);
										}	
									}

									String compProtectChild = MetadataHelper.getMetaData((Component) cr.getContDef(), ScenarioUtils.METADATA_ORIGCOMPONENT + ".compProtect");
									if (compProtectChild != null)
										((Component) cr.getContDef()).setProtected(Boolean.valueOf(compProtectChild));

									String compContextChild = MetadataHelper.getMetaData((Component) cr.getContDef(), ScenarioUtils.METADATA_ORIGCOMPONENT + ".compContext");
									if (compContextChild != null)
										((Component) cr.getContDef()).setContext(Boolean.valueOf(compContextChild));
								}
							}
						}

						ComponentKind origCompKind = comp.getKind();
						if (origCompKind != null) {
							String compKindChild = MetadataHelper.getMetaData(comp, ScenarioUtils.METADATA_ORIGCOMPKIND);
							if (compKindChild != null) {
								if (compKindChild.equals("TEAM"))
									comp.setKind(ComponentKind.TEAM_LITERAL);
								else if (compKindChild.equals("OBJECT"))
									comp.setKind(ComponentKind.OBJECT_LITERAL);
								else if (compKindChild.equals("PROCESS"))
									comp.setKind(ComponentKind.PROCESS_LITERAL);
								else if (compKindChild.equals("AGENT"))
									comp.setKind(ComponentKind.AGENT_LITERAL);
								else if (compKindChild.equals("ACTOR"))
									comp.setKind(ComponentKind.ACTOR_LITERAL);
								else if (compKindChild.equals("OTHER"))
									comp.setKind(ComponentKind.OTHER_LITERAL);
							}	
						}

						String compProtectChild = MetadataHelper.getMetaData(comp, ScenarioUtils.METADATA_ORIGCOMPONENT + ".compProtect");
						if (compProtectChild != null)
							comp.setProtected(Boolean.valueOf(compProtectChild));

						String compContextChild = MetadataHelper.getMetaData(comp, ScenarioUtils.METADATA_ORIGCOMPONENT + ".compContext");
						if (compContextChild != null)
							comp.setContext(Boolean.valueOf(compContextChild));
					}

					for (Iterator iter = dyn.getUrnspec().getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
						diagram = (IURNDiagram) iter.next();
						if (diagram instanceof UCMmap) {
							for (Iterator iterIn = diagram.getNodes().iterator(); iterIn.hasNext();) {
								PathNode pn = (PathNode) iterIn.next();
								List<NodeConnection> ncs = pn.getSucc();
								for (NodeConnection n : ncs)
								{
									TraversalResult o = ScenarioUtils.getTraversalResults(n);
									if (o != null && o.getHitCount() > 0) {
										hitCountNodeConnection.put(n, hitCountNodeConnection.get(n) + (float) o.getHitCount());
										traversalCountNodeConnection.put(n, traversalCountNodeConnection.get(n) + (float) 1);
									}
								}
								if (pn instanceof StartPoint || pn instanceof EndPoint || pn instanceof Stub || pn instanceof AndFork
										|| pn instanceof AndJoin || pn instanceof OrFork || pn instanceof OrJoin || pn instanceof Timer
										|| pn instanceof WaitingPlace || pn instanceof FailurePoint || pn instanceof DirectionArrow || pn instanceof EmptyPoint) {
									TraversalResult obj = ScenarioUtils.getTraversalResults(pn);
									if (obj != null) {
										if (pn instanceof Stub) {
											Set<String> deactStatusElements = new HashSet<String>();
											List<String> bindingIDs = new ArrayList<String>();
											List<PluginBinding> bindings = ((Stub) pn).getBindings();
											String deactStatus = MetadataHelper.getMetaData((Stub) pn, EvaluationStrategyManager.METADATA_DEACTSTATUS);
											if (deactStatus != null) {
												deactStatus = deactStatus.replace("PluginMap IDs: ","");
												String[] deactStatusPlugins = deactStatus.split(",");
												deactStatusElements = new HashSet<String>(Arrays.asList(deactStatusPlugins));
											}

											for (PluginBinding binding : bindings) {
												String pluginID = binding.getPlugin().getId();
												bindingIDs.add(pluginID);
											}
											Set<String> bindingIDElements = new HashSet<String>(bindingIDs);

											if (!(deactStatus != null && deactStatusElements.equals(bindingIDElements) && ScenarioTraversalPreferences.getIsTimedUcmEnabled())) {
												traversalCountUrnModelElement.put((Stub) pn, traversalCountUrnModelElement.get((Stub) pn) + (float) 1);
												hitCountUrnModelElement.put((Stub) pn, hitCountUrnModelElement.get((Stub) pn) + (float) obj.getHitCount());
											}
										}
										else {
											String deactStatus = MetadataHelper.getMetaData(pn, EvaluationStrategyManager.METADATA_DEACTSTATUS);
											if (!(deactStatus != null && deactStatus.equalsIgnoreCase("true") && ScenarioTraversalPreferences.getIsTimedUcmEnabled())) {
												traversalCountUrnModelElement.put(pn, traversalCountUrnModelElement.get(pn) + (float) 1);
												hitCountUrnModelElement.put(pn, hitCountUrnModelElement.get(pn) + (float) obj.getHitCount());
											}
										} 
									}

									if (pn instanceof Stub) {
										List<PluginBinding> bindings = ((Stub) pn).getBindings();
										String dynStub = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGSTUB + ".dynStub");
										String syncStub = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGSTUB + ".syncStub");
										String blockStub = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGSTUB + ".blockStub");
										if (dynStub != null)
											((Stub) pn).setDynamic(Boolean.valueOf(dynStub));
										if (syncStub != null)
											((Stub) pn).setDynamic(Boolean.valueOf(syncStub));
										if (blockStub != null)
											((Stub) pn).setDynamic(Boolean.valueOf(blockStub));

										for (PluginBinding p: bindings) {
											String repFactor = MetadataHelper.getMetaData(p.getStub(), ScenarioUtils.METADATA_ORIGREPFACTOR + "." + p.getPlugin().getName());
											String preCondition = MetadataHelper.getMetaData(p.getStub(), ScenarioUtils.METADATA_ORIGPLUGINCONDITION + "." + p.getPlugin().getName());
											if (repFactor != null)
												p.setReplicationFactor(Integer.parseInt(repFactor));
											if (preCondition != null)
												p.getPrecondition().setExpression(preCondition);
										}	
									} else if (pn instanceof StartPoint) {
										FailureKind origFailureKind = ((StartPoint) pn).getFailureKind();
										if (origFailureKind != null) {
											String startPtFailureKind = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGFAILUREKIND);
											if (startPtFailureKind != null) {
												if (startPtFailureKind.equals("FAILURE"))
													((StartPoint) pn).setFailureKind(FailureKind.FAILURE_LITERAL);
												else if (startPtFailureKind.equals("ABORT"))
													((StartPoint) pn).setFailureKind(FailureKind.ABORT_LITERAL);
												else if (startPtFailureKind.equals("NONE"))
													((StartPoint) pn).setFailureKind(FailureKind.NONE_LITERAL);
											}
										}

										String startPtFailureList = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGFAILURELIST);
										if (startPtFailureList != null) {
											((StartPoint) pn).getPrecondition().setExpression(startPtFailureList);
										}
									} else if (pn instanceof Timer || pn instanceof WaitingPlace) {
										WaitKind origWaitKind = ((WaitingPlace) pn).getWaitType();
										if (origWaitKind != null) {
											String waitingPlaceTimerWaitKind = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGWAITKIND);
											if (waitingPlaceTimerWaitKind != null) {
												if (waitingPlaceTimerWaitKind.equals("TRANSIENT"))
													((WaitingPlace) pn).setWaitType(WaitKind.TRANSIENT_LITERAL);
												else if (waitingPlaceTimerWaitKind.equals("PERSISTENT"))
													((WaitingPlace) pn).setWaitType(WaitKind.PERSISTENT_LITERAL);
											}
										}
									} else if (pn instanceof EndPoint) {
										String endPtPostCond = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGENDPTPOSTCOND);
										if (endPtPostCond != null)
											((EndPoint) pn).getPostcondition().setExpression(endPtPostCond);
									} else if (pn instanceof FailurePoint) {
										String failurePtCond = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGFAILUREPTCOND);
										String failurePtExp = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGFAILUREEXPRESSION);
										if (failurePtCond != null) {
											List<NodeConnection> ncs1 = ((FailurePoint) pn).getDiagram().getConnections();
											for (NodeConnection n : ncs1) {
												Metadata metaOrigFailurePtCond = MetadataHelper.getMetaDataObj(pn, ScenarioUtils.METADATA_ORIGFAILUREPTCOND);
												if (metaOrigFailurePtCond != null) {
													String origFailurePtCond = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGFAILUREPTCOND);
													if (n.getSource() instanceof FailurePoint) {
														n.getCondition().setExpression(origFailurePtCond);
													}
												}
											}
										}
										if (failurePtExp != null) {
											((FailurePoint) pn).setExpression(failurePtExp);
										}
									} else if (pn instanceof OrFork) {
										List<NodeConnection> ncs1 = ((OrFork) pn).getDiagram().getConnections();
										for (NodeConnection n : ncs1) {
											if (n.getSource() instanceof OrFork) {
												String orForkID = ((OrFork) n.getSource()).getId();
												String branchName = "Branch: " + (n.getSource().getSucc().indexOf(n) + 1);
												Metadata metaOrigORForkExpression = MetadataHelper.getMetaDataObj(pn, ScenarioUtils.METADATA_ORIGORFORKEXP + " (" + orForkID + ") <->" + branchName);
												if (metaOrigORForkExpression != null) {
													String origORForkExpression = MetadataHelper.getMetaData(pn, ScenarioUtils.METADATA_ORIGORFORKEXP + " (" + orForkID + ") <->" + branchName);
													if (origORForkExpression != null) {
														n.getCondition().setExpression(origORForkExpression);
													}
												}
											}
										}
									} 
								}
							}
						}
					}
				}

				// Getting the factor of hitCount of URNmodelElements over traversalCount
				Set<Entry<URNmodelElement, Float>> entrySetHitCount = hitCountUrnModelElement.entrySet();
				Set<Entry<URNmodelElement, Float>> entrySetTraversalCount = traversalCountUrnModelElement.entrySet();

				for (Entry entryHitCount : entrySetHitCount) {
					for (Entry entryTraversalCount : entrySetTraversalCount) {
						if (((URNmodelElement) entryHitCount.getKey()).getId().equals(((URNmodelElement) entryTraversalCount.getKey()).getId())) {
							float entryHitCountValue = (float) entryHitCount.getValue();
							float entryTraversalCountValue = (float) entryTraversalCount.getValue();
							float avgHitCount = entryHitCountValue / entryTraversalCountValue;
							hitCountUrnModelElement.put((URNmodelElement) entryHitCount.getKey(), avgHitCount);
						}
					}
				}

				// Sort the hitCount map of URNmodelElements
				Set<Entry<URNmodelElement, Float>> hitCountSet = hitCountUrnModelElement.entrySet();
				hitCountListUrnModelElement = new ArrayList<Entry<URNmodelElement, Float>>(hitCountSet);
				Collections.sort(hitCountListUrnModelElement, new Comparator<Map.Entry<URNmodelElement, Float>>()
				{
					@Override
					public int compare(Entry<URNmodelElement, Float> o1, Entry<URNmodelElement, Float> o2) {
						return (o2.getValue()).compareTo(o1.getValue());
					}	
				});


				// Getting the factor of hitCount of NodeConnection over traversalCount
				Set<Entry<NodeConnection, Float>> entrySetHitCountNodeConnection = hitCountNodeConnection.entrySet();
				Set<Entry<NodeConnection, Float>> entrySetTraversalCountNodeConnection = traversalCountNodeConnection.entrySet();

				for (Entry entryHitCountNC : entrySetHitCountNodeConnection) {
					for (Entry entryTraversalCountNC : entrySetTraversalCountNodeConnection) {
						String idModelSourceHitCount = ((URNmodelElement) ((NodeConnection) entryHitCountNC.getKey()).getSource()).getId();
						String idModelTargetHitCount = ((URNmodelElement) ((NodeConnection) entryHitCountNC.getKey()).getTarget()).getId();
						String idModelSourceTraversalCount = ((URNmodelElement) ((NodeConnection) entryTraversalCountNC.getKey()).getSource()).getId();
						String idModelTargetTraversalCount = ((URNmodelElement) ((NodeConnection) entryTraversalCountNC.getKey()).getTarget()).getId();
						if (idModelSourceHitCount.equals(idModelSourceTraversalCount) && idModelTargetHitCount.equals(idModelTargetTraversalCount)) {
							float entryHitCountValueNC = (float) entryHitCountNC.getValue();
							float entryTraversalCountValueNC = (float) entryTraversalCountNC.getValue();
							float avgHitCountNC = entryHitCountValueNC / entryTraversalCountValueNC;
							hitCountNodeConnection.put((NodeConnection) entryHitCountNC.getKey(), avgHitCountNC);
						}
					}
				}

				// Sort the hitCount map of NodeConnections
				Set<Entry<NodeConnection, Float>> hitCountSetNC = hitCountNodeConnection.entrySet();
				hitCountListNodeConnection = new ArrayList<Entry<NodeConnection, Float>>(hitCountSetNC);
				Collections.sort(hitCountListNodeConnection, new Comparator<Map.Entry<NodeConnection, Float>>()
				{
					@Override
					public int compare(Entry<NodeConnection, Float> o1, Entry<NodeConnection, Float> o2) {
						return (o2.getValue()).compareTo(o1.getValue());
					}	
				});

				// Getting the average of traversalCounts of URNmodelElements over total number of timepoints
				Set<Entry<URNmodelElement, Float>> entrySetTraversalUrnModelElements = traversalCountUrnModelElement.entrySet();
				for (Entry entry : entrySetTraversalUrnModelElements) {
					Float entryValueFloat = (Float) entry.getValue();
					traversalCountUrnModelElement.put((URNmodelElement) entry.getKey(), ((entryValueFloat) / numberOfTimepoints));	
				}

				// Sort the traversalCount map of URNmodelElements
				Set<Entry<URNmodelElement, Float>> set = traversalCountUrnModelElement.entrySet();
				traversalCountListUrnModelElement = new ArrayList<Entry<URNmodelElement, Float>>(set);
				Collections.sort(traversalCountListUrnModelElement, new Comparator<Map.Entry<URNmodelElement, Float>>()
				{
					@Override
					public int compare(Entry<URNmodelElement, Float> o1, Entry<URNmodelElement, Float> o2) {
						return (o2.getValue()).compareTo(o1.getValue());
					}	
				});

				// Getting the average of traversalCounts of node connections over total number of timepoints
				Set<Entry<NodeConnection, Float>> entrySetTraversalNodeConnectionLinks = traversalCountNodeConnection.entrySet();
				for (Entry entry : entrySetTraversalNodeConnectionLinks) {
					Float entryValueFloat = (Float) entry.getValue();
					traversalCountNodeConnection.put((NodeConnection) entry.getKey(), ((entryValueFloat) / numberOfTimepoints));	
				}

				// Sort the traversalCount map of Node Connection
				Set<Entry<NodeConnection, Float>> set1 = traversalCountNodeConnection.entrySet();
				traversalCountListNodeConnection = new ArrayList<Entry<NodeConnection, Float>>(set1);
				Collections.sort(traversalCountListNodeConnection, new Comparator<Map.Entry<NodeConnection, Float>>()
				{
					@Override
					public int compare(Entry<NodeConnection, Float> o1, Entry<NodeConnection, Float> o2) {
						return (o2.getValue()).compareTo(o1.getValue());
					}	
				});
			}
		} catch (ClassCastException e) {

		} catch (NullPointerException e) {

		}
	}
}