package seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import grl.Actor;
import grl.ActorRef;
import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.figures.dynamicContexts.DynamicContextEvaluationViewObjectFigure;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;
import urncore.URNmodelElement;

/**
 * 
 * The view object used to store the overall evaluation for 
 * a TimepointGroup to be showed in the DynamicContextEvaluationView.
 *  
 * @author aprajita
 * 
 */
public class DynamicContextEvaluationViewObject {

	// Dynamic Context selected
    private DynamicContext dyn;
    
    //TimepointGroup selected
    private TimepointGroup group;

    // Timepoints
    private String[] timepointNames = new String[0];

    // Intentional elements and their links
    private ArrayList<IntentionalElement> intElts = new ArrayList<IntentionalElement>();
    private ArrayList<ArrayList<Contribution>> contriLinks = new ArrayList<ArrayList<Contribution>>();
    private ArrayList<ArrayList<Dependency>> depLinks = new ArrayList<ArrayList<Dependency>>();
    private ArrayList<ArrayList<Decomposition>> decompLinks = new ArrayList<ArrayList<Decomposition>>();
    
    //Actors
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    
    //ArrayList that will contain all the elements in proper hierarchy
    private ArrayList<URNmodelElement> allElts = new ArrayList<URNmodelElement>();
    
    //ArrayList of String containing hierarchical informatiom
    private ArrayList<String> hierarchyInfo = new ArrayList<String>();
    
    private int indEltIndex = 0;
    
    // Evaluation and importance values at different timepoints for different elements and actors.
    //First row is for system evaluation
    private double[][] evaluationLevels = null;
    private double[][] importanceValues = null;
    private double[][] overallDecompEval = null;
    
    
    // src evaluation values combined with link properties at different timepoints for different links.
    private Map<Integer, ArrayList<ArrayList<Double>>> contriLinkValuesMap = new HashMap<Integer, ArrayList<ArrayList<Double>>>();
    private Map<Integer, ArrayList<ArrayList<Double>>> decompLinkValuesMap = new HashMap<Integer, ArrayList<ArrayList<Double>>>();
    private Map<Integer, ArrayList<ArrayList<Double>>>depLinkValuesMap = new HashMap<Integer, ArrayList<ArrayList<Double>>>();
    
    private Date firstTimepoint;
    private Date lastTimepoint;
    
    // Store colors
    private Color[][] colors = null;
    

    // Location information
    public final static int DEFAULT_WIDTH = 500;
    public final static int DEFAULT_HEIGHT = 250;
    public final static int SHIFT_X = 20;
    public final static int SHIFT_Y = 10;

    private int x = SHIFT_X;
    private int y = SHIFT_Y;
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public DynamicContextEvaluationViewObject(DynamicContext dyn, TimepointGroup group) {
        this.dyn = dyn;
        this.group = group;
        
        if (dyn == null || group == null)
        	return;
        
        //Collect all the actors in the diagram
        List listOfActors = dyn.getUrnspec().getGrlspec().getActors();
        List elements = dyn.getUrnspec().getGrlspec().getIntElements();
        ArrayList<IntentionalElementRef> elementRefs = new ArrayList<IntentionalElementRef>();
        for (Iterator iter = elements.iterator(); iter.hasNext();) {
        	IntentionalElement elt = (IntentionalElement) iter.next();
        	elementRefs.addAll(elt.getRefs());
        }
        
        //boolean array to check whether an element ref already has been included as a child of an actor or not
        boolean[] included = new boolean[elementRefs.size()];
        
        //Null space as first element to specify system
        allElts.add(null);
        hierarchyInfo.add(null);
        
        if (listOfActors != null) {
        	for (int i = 0; i < listOfActors.size(); i++) {
        		Actor actor = (Actor) listOfActors.get(i);
        		included = getActorsAndChildren(allElts, included, elementRefs, actor);
        	}
        }
        
        //Index from where intentional elements with no actor parents exist
        indEltIndex = allElts.size();
        
        boolean[] elementsIncl = new boolean[elements.size()];
        //Variable to count if any element ref is present without any actor
        int countRef = 0;
        for (int i = 0; i < elementRefs.size(); i++) {
        	if (!included[i]) {
        		if (countRef == 0) {
        			//Add null space to show undefined actor, which contain all the element references which are without any actor parent
        	        allElts.add(null);
        	        hierarchyInfo.add(null);
        	    }
        		countRef += 1;
        		
        		//Add the element to the list if not included before
        		IntentionalElement elmToIncl = ((IntentionalElementRef) elementRefs.get(i)).getDef();
        		if (!elementsIncl[elements.indexOf(elmToIncl)]) {
        			allElts.add(elmToIncl);
        			hierarchyInfo.add(null);
        			elementsIncl[elements.indexOf(elmToIncl)] = true;
        		}
        		
        	}
        }
        
        //Collect all the IntentionalElements' links in the diagram
        if (elements != null) {
        	for (int i = 0; i < allElts.size(); i++) {
        		ArrayList<Contribution> eltContriLinks = new ArrayList<Contribution>();
	    		ArrayList<Dependency> eltDepLinks = new ArrayList<Dependency>();
	    		ArrayList<Decomposition> eltDecompLinks = new ArrayList<Decomposition>();
	    		
	    		if (allElts.get(i) instanceof IntentionalElement) {
	        		IntentionalElement element = (IntentionalElement) allElts.get(i);
	        		
	        		for (Iterator iter = element.getLinksDest().iterator(); iter.hasNext();) {
			    		ElementLink link = (ElementLink) iter.next();
			    		if (link instanceof Contribution)
			    			eltContriLinks.add((Contribution) link);
			    		else if (link instanceof Dependency)
			    			eltDepLinks.add((Dependency) link);
			    		else if (link instanceof Decomposition)
			    			eltDecompLinks.add((Decomposition) link);
	        		}
	        	}
	    		this.contriLinks.add(eltContriLinks);
        		this.depLinks.add(eltDepLinks);
        		this.decompLinks.add(eltDecompLinks);
        	}
        }
        
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        
        //Collect all the timepoints in the group
        ArrayList timepoints = new ArrayList();
        timepoints.addAll(group.getTimepoints());
        Collections.sort(timepoints, new Comparator<Timepoint>() {
        	@Override
        	public int compare(Timepoint t1, Timepoint t2) {
                return t1.getTimepoint().compareTo(t2.getTimepoint());
            }
		});
        Timepoint first = (Timepoint) timepoints.get(0);
        firstTimepoint = first.getTimepoint();
        Timepoint last = (Timepoint) timepoints.get(timepoints.size()-1);
        lastTimepoint = last.getTimepoint();
		long diff = last.getTimepoint().getTime() - first.getTimepoint().getTime();
	    int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	    Calendar c = Calendar.getInstance(); 
		c.setTime(first.getTimepoint());
		if (timepoints != null) {
        	timepointNames = new String[days+1];
        	int j = 0;
        	for (int i = 0; i < days+1; i++) {
        		Calendar c2 = Calendar.getInstance();
        		c2.setTime(((Timepoint) timepoints.get(j)).getTimepoint());
        		c2.set(Calendar.MILLISECOND, 0);
        		if (c2.compareTo(c) == 0) {
        			Timepoint tp = (Timepoint) timepoints.get(j);
            		timepointNames[i] = df.format(tp.getTimepoint());
            		j += 1;
        		} else {
        			timepointNames[i] = "";
        		}
        		c.add(Calendar.DATE, 1);
        		c.set(Calendar.MILLISECOND, 0);
        	}
        }
        
        EvaluationStrategyManager sm = EvaluationStrategyManager.getInstance();
        sm.setDynamicContext(dyn);
        if (dyn != null && group != null) {
			
        	int count = 0;
			c.setTime(first.getTimepoint());
			if (elements != null) {
				colors = new Color[allElts.size()][(int)days+1];
				evaluationLevels = new double[colors.length][colors[0].length];
				importanceValues = new double[colors.length][colors[0].length];
			}
			if (decompLinks.size() != 0)
				overallDecompEval = new double[colors.length][colors[0].length];
			
			while (count <= days) {
				Timepoint tNew;
				if (count == 0)
					tNew = first;
				else if (count == days)
					tNew = last;
				else {
					tNew = (Timepoint) ModelCreationFactory.getNewObject(group.getUrnspec(), Timepoint.class);
					tNew.setTimepoint(c.getTime());
				}
				
				//Two dimensional arraylist to store evaluation values for links related to each element
				ArrayList<ArrayList<Double>> contriLinkValues = new ArrayList<ArrayList<Double>>();
				ArrayList<ArrayList<Double>> depLinkValues = new ArrayList<ArrayList<Double>>();
				ArrayList<ArrayList<Double>> decompLinkValues = new ArrayList<ArrayList<Double>>();
				
				sm.setTimepoint(tNew);
	        	sm.setStrategy(dyn.getStrategy());
	        	if (allElts != null) {
	        		double totProd = 0;
	        		double totImp = 0;
		        	for (int i = 0; i < allElts.size(); i++) {
		        		
		        		//ArrayLists to store values contributed by different links to this element
		            	ArrayList<Double> eltContriLinkValues = new ArrayList<Double>();
		            	ArrayList<Double> eltDepLinkValues = new ArrayList<Double>();
		            	ArrayList<Double> eltDecompLinkValues = new ArrayList<Double>();
		            	
		        		if (allElts.get(i) instanceof Actor) {
		        			Actor actor = (Actor) allElts.get(i);
							boolean ignored = false;
						    /*Metadata metaDeactStatus = MetadataHelper.getMetaDataObj(actor, EvaluationStrategyManager.METADATA_DEACTSTATUS);
							  if (metaDeactStatus != null) {
								String deactStatus = MetadataHelper.getMetaData(actor, EvaluationStrategyManager.METADATA_DEACTSTATUS);
								if (deactStatus.equalsIgnoreCase("true"))
									ignored = true;
							}*/
							ignored = sm.isActorIgnored(actor);
							colors[i][count] = getActualColor(sm.getActorEvaluation(actor), ignored);
				            if (ignored) {
				            	evaluationLevels[i][count] = 0;
					            importanceValues[i][count] = 0;
					            
				            }	else {
				            	evaluationLevels[i][count] = (double) sm.getActorEvaluation(actor);
				            	importanceValues[i][count] = (double) actor.getImportanceQuantitative();
				            	if (hierarchyInfo.get(i).equals("parent")) {
					            	totProd += (double) sm.getActorEvaluation(actor) * actor.getImportanceQuantitative();
					            	totImp += (double) actor.getImportanceQuantitative();
				            	}
				            }
				            
		        		} else if (allElts.get(i) instanceof IntentionalElement){
			            	IntentionalElement element = (IntentionalElement) allElts.get(i);
			            	ArrayList<Contribution> eltContriLinks = contriLinks.get(i);
			            	ArrayList<Dependency> eltDepLinks = depLinks.get(i);
			            	ArrayList<Decomposition> eltDecompLinks = decompLinks.get(i);
			        		boolean ignored = EvaluationStrategyManager.getInstance().isIgnored(element);
			            	colors[i][count] = getActualColor(sm.getEvaluation(element), ignored);
			            	if (ignored) {
			            		evaluationLevels[i][count] = 0;
				            	importanceValues[i][count] = 0;
			            	}	else {
				            	evaluationLevels[i][count] = (double) sm.getEvaluation(element);
				            	importanceValues[i][count] = (double) element.getImportanceQuantitative();
				            	
				            	//For elements not included in any actor
				            	if (i >= indEltIndex) {
				            		totProd += (double) sm.getEvaluation(element) * element.getImportanceQuantitative();
					            	totImp += (double) element.getImportanceQuantitative();
				            	}
			            	}
			            	
			            	for (Iterator iter = eltContriLinks.iterator(); iter.hasNext();) {
			            		Contribution link = (Contribution) iter.next();
		
			            		//Storing value for ignored as -120(an impossible value to achieve in these cases as they are bound by [-100,100])
			            		if (ignored) 
			            			eltContriLinkValues.add(Double.valueOf(-120));
			            		else {
			            			IntentionalElement srcElt = (IntentionalElement) link.getSrc();
		
			            			//If the source of the link is deactivated, link is also deactivated
			            			if (EvaluationStrategyManager.getInstance().isIgnored(srcElt))
			            				eltContriLinkValues.add(Double.valueOf(-120));
			            			else {
			            				Double contriValue = Double.valueOf(link.getQuantitativeContribution());
			            				Double contriLinkValue = (Double.valueOf((double) sm.getEvaluation(srcElt)) * contriValue)/100;
			            				eltContriLinkValues.add(Double.valueOf(Math.round(contriLinkValue)));
			            			}
			            		}
			            	}
			            	
			            	for (Iterator iter = eltDepLinks.iterator(); iter.hasNext();) {
			            		Dependency link = (Dependency) iter.next();
		
			            		//Storing value for ignored as -120(an impossible value to achieve in these cases as they are bound by [-100,100])
			            		if (ignored) 
			            			eltDepLinkValues.add(Double.valueOf(-120));
			            		else {
			            			IntentionalElement srcElt = (IntentionalElement) link.getSrc();
		
			            			//If the source of the link is deactivated, link is also deactivated
			            			if (EvaluationStrategyManager.getInstance().isIgnored(srcElt))
			            				eltDepLinkValues.add(Double.valueOf(-120));
			            			else {
			            				eltDepLinkValues.add(Double.valueOf((double) sm.getEvaluation(srcElt)));
			            			}
			            		}
			            	}
			            	
			            	for (Iterator iter = eltDecompLinks.iterator(); iter.hasNext();) {
			            		Decomposition link = (Decomposition) iter.next();
		
			            		//Storing value for ignored as -120(an impossible value to achieve in these cases as they are bound by [-100,100])
			            		if (ignored) 
			            			eltDecompLinkValues.add(Double.valueOf(-120));
			            		else {
			            			IntentionalElement srcElt = (IntentionalElement) link.getSrc();
		
			            			//If the source of the link is deactivated, link is also deactivated
			            			if (EvaluationStrategyManager.getInstance().isIgnored(srcElt))
			            				eltDecompLinkValues.add(Double.valueOf(-120));
			            			else {
			            				eltDecompLinkValues.add(Double.valueOf((double) sm.getEvaluation(srcElt)));
			            			}
			            		}
			            	}
			            	
			            	
			            	//Calculate overall evaluation for the element due to decomposition links, depending on the type
			            	if (decompLinks.get(i).size() != 0) {
			            		ArrayList<Double> toSort = (ArrayList<Double>) eltDecompLinkValues.clone();
			            		Collections.sort(toSort);
			            		if (element.getDecompositionType().getValue() == DecompositionType.AND) {
			            			int j = 0;
			            			boolean found = false;
			            			while (!found) {
			            				if (toSort.get(j).equals(Double.valueOf(-120)))
			            					j += 1;
			            				else {
			            					overallDecompEval[i][count] = toSort.get(j);
			            					found = true;
			            				}
			            				if (j == toSort.size() && !found) {
			            					overallDecompEval[i][count] = toSort.get(toSort.size()-1);
			            					found = true;
			            				}
			            			}
			            		} else if (element.getDecompositionType().getValue() == DecompositionType.OR || 
			            				element.getDecompositionType().getValue() == DecompositionType.XOR) {
			            			overallDecompEval[i][count] = toSort.get(toSort.size()-1);
			            			
			            		}
			            	}
			            	
			        	}
		        		contriLinkValues.add(eltContriLinkValues);
		        		depLinkValues.add(eltDepLinkValues);
		        		decompLinkValues.add(eltDecompLinkValues);
			        }
		        	//System evaluation for this timepoint
		        	int sysEval = (int) (totProd/totImp);
		        	evaluationLevels[0][count] = sysEval;
		        	colors[0][count] = getActualColor(sysEval, false);
		        	
		        	contriLinkValuesMap.put(Integer.valueOf(count), contriLinkValues);
		        	depLinkValuesMap.put(Integer.valueOf(count), depLinkValues);
		        	decompLinkValuesMap.put(Integer.valueOf(count), decompLinkValues);
		        	count += 1;
		        	c.add(Calendar.DATE, 1);
	        	}
			}
        }
		
        // calculate the preferred height
        DynamicContextEvaluationViewObjectFigure figure = new DynamicContextEvaluationViewObjectFigure(this);
        figure.setLocation(new Point(0, 0));
        figure.setupFigure();
        
        height = figure.getPreferredHeight();
    }
    
    private Color getActualColor(int eval, boolean ignored) {
    	String color = null;
    	Color actualColor = null;
    	if (ignored) { // set to light gray color
		    color = "169,169,169"; //$NON-NLS-1$
		} else if (eval == IGRLStrategyAlgorithm.CONFLICT) {
		    color = "0,255,255"; //$NON-NLS-1$
		} else if (eval == IGRLStrategyAlgorithm.UNDECIDED) {
		    color = "192,192,192"; //$NON-NLS-1$
		} else {
		    int evalValue = eval;

		    // if 0,100, convert back to -100,100 to have the right color.
		    evalValue = StrategyEvaluationPreferences.getEquivalentValueInFullRangeIfApplicable(dyn.getUrnspec(), evalValue);
		    
		    if (EvaluationStrategyManager.getInstance().displayDifferenceMode()
		            && !StrategyEvaluationPreferences.getVisualizeAsPositiveRange(dyn.getUrnspec())) {
		        evalValue /= 2;
		    }
		    int partial = (Math.abs((Math.abs(evalValue) - IGRLStrategyAlgorithm.SATISFICED)) * 160 / IGRLStrategyAlgorithm.SATISFICED) + 96;
		    
		    if (partial < 0) {
		    	partial = 0;
	        } else if (partial > 255) {
	        	partial = 255;
	        }

		    if (evalValue < IGRLStrategyAlgorithm.NONE) {
		        color = "255," + partial + ",96"; //$NON-NLS-1$ //$NON-NLS-2$
		    } else {
		        color = partial + ",255,96"; //$NON-NLS-1$
		    }
		}
    	actualColor = new Color(Display.getCurrent(), StringConverter.asRGB(color));
    	return actualColor;
    }
    
    private boolean[] getActorsAndChildren(ArrayList<URNmodelElement> allElts, boolean[] included, ArrayList<IntentionalElementRef> elementRefs, Actor actor) {
    	boolean alreadyIncl[] = included;
    	boolean addedActor = false;
    	
    	//Keep a list of elements added as children for this actor
    	ArrayList<URNmodelElement> addedElts = new ArrayList<URNmodelElement>();
		for (Iterator iter = actor.getContRefs().iterator(); iter.hasNext();) {
    		ActorRef ref = (ActorRef) iter.next();
    		
    		//If the actor doesn't have any parent, add it and its children to the main list
        	if (ref.getParent() == null) {
        		
        		//If the actor isn't added already
        		if (!addedActor) {
	        		allElts.add((Actor) ref.getContDef());
	        		hierarchyInfo.add("parent");
	        		addedActor = true;
        		}
        		
        		//Collect all the children IntentionalElements
        		for (Iterator iter1 = ref.getNodes().iterator(); iter1.hasNext();) {
        			IntentionalElementRef eltRef = (IntentionalElementRef) iter1.next();
        			IntentionalElement elt = eltRef.getDef();
        			
        			//Skip this element if already added because of more than one references,else add it to the list
        			if (addedElts.contains(elt))
        				continue;
        			else {
        				hierarchyInfo.add(null);
        				alreadyIncl[elementRefs.indexOf(eltRef)] = true;
        				allElts.add(elt);
        				addedElts.add(elt);
        			}
        		}
        		
        		int childActorsCount = 0;
        		//Collect all the children Actors and their children elements
        		for (Iterator iter1 = ref.getChildren().iterator(); iter1.hasNext();) {
        			ActorRef childActRef = (ActorRef) iter1.next();
        			Actor childAct = (Actor) childActRef.getContDef();
        			
        			//Skip this actor if already added because of more than one references,else add it to the list
        			if (addedElts.contains(childAct))
        				continue;
        			else {
	        			childActorsCount += 1;
	        			String name = "child" + childActorsCount;
	        			alreadyIncl = getChildren(allElts, alreadyIncl, elementRefs, childActRef, name);
	        			addedElts.add(childAct);
        			}
        		}
        	}
		}
    	
    	return alreadyIncl;
    	
    }
    
    //To collect all the children of an ActorRef
    private boolean[] getChildren(ArrayList<URNmodelElement> allElts, boolean[] included, ArrayList<IntentionalElementRef> elementRefs, ActorRef ref, String name) {
    	boolean alreadyIncl[] = included;
    	Actor act = (Actor) ref.getContDef();
    	allElts.add(act);
    	hierarchyInfo.add(name);
    	
    	//Keep a list of elements added as children for this actor
    	ArrayList<URNmodelElement> addedElts = new ArrayList<URNmodelElement>();
    	
    	for (Iterator iter = act.getContRefs().iterator(); iter.hasNext();) {
    		ActorRef actRef = (ActorRef) iter.next();
    		
    		//If the actor has the same parent as the incoming actor reference, add its children to the main list
        	if (actRef.getParent() != null && 
        			((ActorRef) actRef.getParent()).getContDef() == ((ActorRef) ref.getParent()).getContDef()) {
		    	for (Iterator iter1 = actRef.getNodes().iterator(); iter1.hasNext();) {
					IntentionalElementRef eltRef = (IntentionalElementRef) iter1.next();
					IntentionalElement element = eltRef.getDef();
					
					//Skip this element if already added because of more than one references,else add it to the list
        			if (addedElts.contains(element))
        				continue;
        			else {
						allElts.add(element);
						hierarchyInfo.add(null);
						alreadyIncl[elementRefs.indexOf(eltRef)] = true;
						addedElts.add(element);
        			}
				}
		    	
		    	int childActorCount = 0;
		    	for (Iterator iter1 = actRef.getChildren().iterator(); iter1.hasNext();) {
					ActorRef childActRef = (ActorRef) iter1.next();
					Actor childAct = (Actor) childActRef.getContDef();
					
					//Skip this element if already added because of more than one references,else add it to the list
        			if (addedElts.contains(childAct))
        				continue;
        			else {
						childActorCount += 1;
						String nameHere = name + "_" + childActorCount;
						alreadyIncl = getChildren(allElts, alreadyIncl, elementRefs, childActRef, nameHere);
						addedElts.add(childAct);
        			}
				}
        	}
    	}
    	return alreadyIncl;
    }
    
    public ArrayList<Contribution> getContributionLinks(int i) {
    	ArrayList<Contribution> reqLinks = new ArrayList<Contribution>();
    	reqLinks = contriLinks.get(i);
    	return reqLinks;
    }
    
    public ArrayList<Dependency> getDependencyLinks(int i) {
    	ArrayList<Dependency> reqLinks = new ArrayList<Dependency>();
    	reqLinks = depLinks.get(i);
    	return reqLinks;
    }
    
    public ArrayList<Decomposition> getDecompLinks(int i) {
    	ArrayList<Decomposition> reqLinks = new ArrayList<Decomposition>();
    	reqLinks = decompLinks.get(i);
    	return reqLinks;
    }
    
    public Map<Integer, ArrayList<ArrayList<Double>>> getContriLinkValues() {
    	return contriLinkValuesMap;
    }
    
    public Map<Integer, ArrayList<ArrayList<Double>>> getDepLinkValues() {
    	return depLinkValuesMap;
    }
    
    public Map<Integer, ArrayList<ArrayList<Double>>> getDecompLinkValues() {
    	return decompLinkValuesMap;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String[] getTimepointNames() {
        return timepointNames;
    }

    public ArrayList<IntentionalElement> getIntentionalElements() {
        return intElts;
    }
    
    public ArrayList<Actor> getActors() {
        return actors;
    }
    
    public ArrayList<URNmodelElement> getAllElements() {
        return allElts;
    }
    
    public ArrayList<String> getHierarchyInfo() {
    	return hierarchyInfo;
    }
    
    public double[][] getEvaluationValues() {
    	return evaluationLevels;
    }
    
    public double[][] getImportanceValues() {
    	return importanceValues;
    }
    
    public double[][] getOverallDecompValues() {
    	return overallDecompEval;
    }
    
    public int getIndependentIndex() {
    	return indEltIndex;
    }
    
    public Color[][] getColors() {
    	return colors;
    }
    
    public Date startRange() {
    	return firstTimepoint;
    }
    
    public Date endRange() {
    	return lastTimepoint;
    }

}
