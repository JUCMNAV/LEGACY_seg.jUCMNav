package seg.jUCMNav.actions.concerns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.aourn.composer.AspectMarkerMappings;
import seg.jUCMNav.aourn.composer.UCMAspectComposer;
import seg.jUCMNav.aourn.composer.exceptions.CompositionNotRequired;
import seg.jUCMNav.aourn.composer.exceptions.MalformedAspectMap;
import seg.jUCMNav.aourn.matcher.Mapping;
import seg.jUCMNav.aourn.matcher.Match;
import seg.jUCMNav.aourn.matcher.MatchList;
import seg.jUCMNav.aourn.matcher.MatchableElementFactory;
import seg.jUCMNav.aourn.matcher.PointcutMatcher;
import seg.jUCMNav.aourn.matcher.exceptions.MatchingFailedException;
import seg.jUCMNav.model.commands.concerns.AddAspectStubsCommand;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.PointcutKind;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Action related to applying a concern to the model
 * 
 * @author gunterm
 * 
 */
public class ApplyConcernAction extends URNSelectionAction {

    public static final String APPLYCONCERN = "seg.jUCMNav.actions.concerns.ApplyConcernAction"; //$NON-NLS-1$
    // this is either an aspect map, a pointcut graph, or a concern 
    private URNmodelElement element;
    private URNspec urn;
    
    /**
     * @param part
     *            the workbench part we are working with
     */
    public ApplyConcernAction(IWorkbenchPart part) {
        super(part);
        setId(APPLYCONCERN);
        // TODO create a new icon for Apply Concern
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Concern16.gif")); //$NON-NLS-1$
    }

    /**
     * Returns true if a map, a grl graph, or a concern is selected; false otherwise
     * 
     * @see seg.jUCMNav.actions.URNSelectionAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        if(!DisplayPreferences.getInstance().isAdvancedControlEnabled() || !DisplayPreferences.getInstance().isShowAspect())
            return false;
        
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        element = sel.getURNmodelElement();
        switch (sel.getSelectionType()) {
        case SelectionHelper.MAP:
        // TODO GRL and concerns not yet supported 
        // case SelectionHelper.GRLGRAPH:
        // case SelectionHelper.CONCERN:
            urn = sel.getUrnspec();
            return true;
        default:
            return false;
        }
    }

    /**
     * @return a {@link AddAspectStubsCommand} 
     */
    protected Command getCommand() {
    	boolean showInfoMessages = false;
    	// TODO only works for UCM right now
       	HashSet<UCMmap> pointcutMaps = getPointcutExpression(element);
       	UCMmap pointcutMap = null;
        // TODO externalize strings
        if (!pointcutMaps.isEmpty()) {
       		MatchableElementFactory.clearCache();
       		List<PathNode> joinpoints = MatchableElementFactory.createMatchableElements(urn, element, pointcutMaps);
        	List<MatchList> matchResultList = new ArrayList<MatchList>();
            MatchList matchResult = new MatchList();
            String capture = ""; //$NON-NLS-1$
            int size = 0;
            for (Iterator iterator = pointcutMaps.iterator(); iterator.hasNext();) {
				pointcutMap = (UCMmap) iterator.next();
	       		if (showInfoMessages) 
	       			MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Result", "Found pointcut expression: " + pointcutMap.getName() + " [" + pointcutMap.getId() + Messages.getString("ApplyConcernAction.CloseBracketJoinPoints") + joinpoints.size() + Messages.getString("ApplyConcernAction.SpacePathNodes")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	            try {
	            	matchResult = PointcutMatcher.match(pointcutMap, joinpoints);
	            	matchResultList.add(matchResult);
	    	        if (matchResult.getMatchList().size() < 20)
	    	        	capture = capture + capture(matchResult);
	    	        size = size + matchResult.getMatchList().size();
		        } catch (MatchingFailedException e) {
		        	// add an empty list of matches, so that there is the same number of entries in the results array as there are pointcut maps
		        	matchResultList.add(new MatchList());
				}
			}
            if (showInfoMessages)
            	MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Result", "Found " + size + Messages.getString("ApplyConcernAction.SpaceMatches") + capture); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            else
            	MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Result", "Found " + size + "matches!"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            // go through all the pointcut maps and compose their match results
            int i = 0;
            UCMAspectComposer.resetCounter();
        	List<List<AspectMarkerMappings>> composeResultList = new ArrayList<List<AspectMarkerMappings>>();
        	boolean compositionSuccessful = false;
            for (Iterator iterator = pointcutMaps.iterator(); iterator.hasNext();) {
            	pointcutMap = (UCMmap) iterator.next();
            	MatchList mResult = matchResultList.get(i++);
            	// if there are some matches, continue with the composition
            	if (mResult.getMatchList().size() > 0) {
            		List<AspectMarkerMappings> composeResult = new ArrayList<AspectMarkerMappings>();
            		capture = ""; //$NON-NLS-1$
            		try {
            			composeResult = UCMAspectComposer.compose((UCMmap) element, pointcutMap, mResult);
            			composeResultList.add(composeResult);
            			if (composeResult.size() < 20)
            				capture = capture + capture(composeResult);
            			compositionSuccessful = true;
            		} catch (MalformedAspectMap e) {
            			MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Malformed aspect map!"); //$NON-NLS-1$ //$NON-NLS-2$
            		} catch (CompositionNotRequired e) {
            			MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Nothing to compose!"); //$NON-NLS-1$ //$NON-NLS-2$
            		}
            	}
			}
    		if (compositionSuccessful) {
    			size = 0;
    			for (int j = 0; j < composeResultList.size(); j++) {
					size = size + composeResultList.get(j).size();
				}
    			if (showInfoMessages)
    				MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Result", "Aspect markers to apply: " + size + "     " + capture); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    			if (size < 250) {
    				// perform the commands to update the model - one for each aspect marker that is being added
    				CompoundCommand cmd = new AddAspectStubsCommand(urn, composeResultList);
    				// dispose of the the temporary matching and composition data
    				disposeMatchingCompositionInfo();
    				return cmd;
    			} else {
    				MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Info", "Too much work to add so many aspect markers!"); //$NON-NLS-1$ //$NON-NLS-2$
    			}
    		}
    	} else {
            MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "This map does not contain one or more pointcut stubs with the same pointcut maps and with in/out-bindings for all in/out-paths!"); //$NON-NLS-1$ //$NON-NLS-2$
    	}
       	return UnexecutableCommand.INSTANCE;
    }

	private HashSet<UCMmap> getPointcutExpression(URNmodelElement element) {
		// TODO only works for UCM right now
    	if (element instanceof UCMmap) {
			// find all pointcut stubs
    		List<Stub> pointcutStubs = new ArrayList<Stub>();
    		for (Iterator iter = ((UCMmap) element).getNodes().iterator(); iter.hasNext();) {
    			PathNode pathnode = (PathNode) iter.next();
    			if (pathnode instanceof Stub && !((Stub) pathnode).getAopointcut().equals(PointcutKind.NONE_LITERAL)) {
    				pointcutStubs.add((Stub) pathnode);
    			}
    		}
    		// check that all pointcuts stubs have the same number of plug-in maps and at least one plug-in map (i.e., pointcut map)
    		HashSet<UCMmap> pointcutMaps = new HashSet<UCMmap>();    		
    		int numberOfPlugins = -1;
    		for (int i = 0; i < pointcutStubs.size(); i++) {
    			Stub stub = pointcutStubs.get(i);
    			int size = stub.getBindings().size();
    			if (numberOfPlugins == -1)
    				numberOfPlugins = size;
    			else if (size != numberOfPlugins) {
    				numberOfPlugins = -1;
    				break;
    			}
    			for (Iterator iterator = stub.getBindings().iterator(); iterator.hasNext();) {
					UCMmap pcMap = ((PluginBinding) iterator.next()).getPlugin();
					pointcutMaps.add(pcMap);					
				}
    		}
    		// return empty set if the number of plug-in maps is not the same or is 0 (i.e., it was set to -1 in the previous for loop)
    		// also check that all plug-in maps are the same for each pointcut stub (i.e., the number of all plug-in maps is the same as numberOfPlugins) 
    		if (numberOfPlugins == -1 || numberOfPlugins != pointcutMaps.size())
    			return new HashSet<UCMmap>();
    		// furthermore, all in/out-paths of the pointcut stubs need to have an in/outBinding for all its pointcut maps
    		for (int i = 0; i < pointcutStubs.size(); i++) {
    			Stub pcStub = pointcutStubs.get(i);
				for (int j = 0; j < pcStub.getBindings().size(); j++) {
    				List<NodeConnection> ncList = new ArrayList<NodeConnection>();
    				ncList.addAll(pcStub.getPred());
    				ncList.addAll(pcStub.getSucc());
    				PluginBinding pl = (PluginBinding) pcStub.getBindings().get(j);
    				// look through all the in/outBindings and remove the corresponding in/out-path from the list of in/out-paths
    				for (int k = 0; k < pl.getIn().size(); k++) {
						InBinding in = (InBinding) pl.getIn().get(k);
						ncList.remove(in.getStubEntry());
					}
    				for (int k = 0; k < pl.getOut().size(); k++) {
						OutBinding out = (OutBinding) pl.getOut().get(k);
						ncList.remove(out.getStubExit());
					}
    				// the remaining list of in/out-paths has to be empty if all in/out-paths have bindings
    				if (!ncList.isEmpty()) {
    					// return empty set if some in/out-paths do not have bindings
    					return new HashSet<UCMmap>();
    				}
				}
    		}
    		return pointcutMaps;
    	}
    	return new HashSet<UCMmap>();
    }
    
	private void disposeMatchingCompositionInfo() {
		UCMAspectComposer.dispose();
		PointcutMatcher.dispose();
	}

	private String capture(MatchList result) {
		String capture = ""; //$NON-NLS-1$
		if (result != null) {
			for (Iterator iter = result.getMatchList().iterator(); iter.hasNext();) {
				Match mappings = (Match) iter.next();
				for (Iterator iterator = mappings.getMatch().iterator(); iterator.hasNext();) {
					Mapping mapping = (Mapping) iterator.next();
					if (mapping.getPointcutElement().getElement() == null)
						capture += "null" + " --> " + mapping.getJoinpoint().getName() + "[" + mapping.getJoinpoint().getId() + "]     "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					else
						capture += mapping.getPointcutElement().getName() + "[" + mapping.getPointcutElement().getId() + "] --> " + mapping.getJoinpoint().getName() + "[" + mapping.getJoinpoint().getId() + "]     "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				}
				capture += "-----     "; //$NON-NLS-1$
			}
		}
		else
			capture += Messages.getString("ApplyConcernAction.NoMatch"); //$NON-NLS-1$
		return capture;
	}

	private static String capture(List<AspectMarkerMappings> result) {
		String capture = ""; //$NON-NLS-1$
		for (int i = 0; i < result.size(); i++) {
			PathNode joinpoint = (PathNode) result.get(i).getFirstMapping().get(0);
			NodeConnection insertionPoint = (NodeConnection) result.get(i).getInsertionPoint();
			capture += joinpoint.getName() + "[" + joinpoint.getId() + Messages.getString("ApplyConcernAction.CloseBracketInsertionPoint") + ((PathNode) insertionPoint.getSource()).getName() + "[" + ((PathNode) insertionPoint.getSource()).getId() + "]<-->" + ((PathNode) insertionPoint.getTarget()).getName() + "[" + ((PathNode) insertionPoint.getTarget()).getId() + "]     -----     "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		}
		return capture;
	}

}