package seg.jUCMNav.actions.concerns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.aourn.composer.AspectMarkerMappings;
import seg.jUCMNav.aourn.composer.UCMAspectComposer;
import seg.jUCMNav.aourn.composer.exceptions.CompositionNotRequired;
import seg.jUCMNav.aourn.composer.exceptions.MalformedAspectMap;
import seg.jUCMNav.aourn.matcher.Mapping;
import seg.jUCMNav.aourn.matcher.Match;
import seg.jUCMNav.aourn.matcher.MatchList;
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
    	// TODO only works for UCM right now
       	UCMmap pointcutMap = getPointcutExpression(element);
        // TODO externalize strings
       	if (pointcutMap != null) {
       		List<PathNode> joinpoints = getJoinpoints(element, pointcutMap);
            MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Result", "Found pointcut expression: " + pointcutMap.getName() + " [" + pointcutMap.getId() + "]     joinpoints: " + joinpoints.size() + " path nodes"); //$NON-NLS-1$ //$NON-NLS-2$
            MatchList matchResult = new MatchList();
            String capture = "";
            try {
            	matchResult = PointcutMatcher.match(pointcutMap, joinpoints);
    	        if (matchResult.getMatchList().size() < 20)
    	        	capture = capture(matchResult);
	        } catch (MatchingFailedException e) {
	        	// nothing to do
			}
        	MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Result", "Found " + matchResult.getMatchList().size() + " matches: " + capture); //$NON-NLS-1$ //$NON-NLS-2$
            // if there are some matches, continue with the composition
            if (matchResult.getMatchList().size() > 0) {
                List<AspectMarkerMappings> composeResult = new ArrayList<AspectMarkerMappings>();
                capture = "";
                try {
    				composeResult = UCMAspectComposer.compose((UCMmap) element, pointcutMap, matchResult);
    				if (composeResult.size() < 20)
    					capture = capture(composeResult);
    		        MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Result", "Aspect markers to apply: " + composeResult.size() + "     " + capture); //$NON-NLS-1$ //$NON-NLS-2$
    		        if (composeResult.size() < 250) {
    		        	// perform the commands to update the model - one for each aspect marker that is being added
    		        	CompoundCommand cmd = new AddAspectStubsCommand(urn, composeResult);
        		        // dispose of the the temporary matching and composition data
    		        	disposeMatchingCompositionInfo();
        		        return cmd;
    		        } else {
        		        MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Info", "Too much work to add so many aspect markers!"); //$NON-NLS-1$ //$NON-NLS-2$
    		        }
    			} catch (MalformedAspectMap e) {
    				MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Malformed aspect map!"); //$NON-NLS-1$ //$NON-NLS-2$
    			} catch (CompositionNotRequired e) {
    				MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Nothing to compose!"); //$NON-NLS-1$ //$NON-NLS-2$
    			}
            }
    	} else {
            MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "This map does not contain one or more pointcut stubs with the same pointcut map and with in/out-bindings for all in/out-paths!"); //$NON-NLS-1$ //$NON-NLS-2$
    	}
       	return UnexecutableCommand.INSTANCE;
    }

	private UCMmap getPointcutExpression(URNmodelElement element) {
    	if (element instanceof UCMmap) {
    		UCMmap pointcutMap = null;
    		// TODO considers only the first pointcut map of a pointcut stub on the aspect map
    		for (Iterator iter = ((UCMmap) element).getNodes().iterator(); iter.hasNext();) {
    			PathNode pathnode = (PathNode) iter.next();
    			// needs to be a pointcut stub with at least one plug-in map (i.e., pointcut map)
    			if (pathnode instanceof Stub && !((Stub) pathnode).getAopointcut().equals(PointcutKind.NONE_LITERAL) && ((Stub) pathnode).getBindings().size() > 0) {
    				// furthermore, all in/out-paths of the pointcut stub need to have an in/outBinding with the pointcut map
    				List<NodeConnection> ncList = new ArrayList<NodeConnection>();
    				ncList.addAll(((Stub) pathnode).getPred());
    				ncList.addAll(((Stub) pathnode).getSucc());
    				PluginBinding pl = ((PluginBinding) ((Stub) pathnode).getBindings().get(0));
    				for (int i = 0; i < pl.getIn().size(); i++) {
						InBinding in = (InBinding) pl.getIn().get(i);
						ncList.remove(in.getStubEntry());
					}
    				for (int i = 0; i < pl.getOut().size(); i++) {
						OutBinding out = (OutBinding) pl.getOut().get(i);
						ncList.remove(out.getStubExit());
					}
    				if (ncList.isEmpty()) {
    					if (pointcutMap == null) {
            				// found the pointcut map
        					pointcutMap = pl.getPlugin();    						
    					} else {
    						// check that it is the same pointcut map
    						if (!pointcutMap.equals(pl.getPlugin())) {
    							return null;
    						}
    					}
    				} else {
    					// some in/out-paths do not have bindings
    					return null;
    				}
				}
    		}
    		return pointcutMap;
    	}
    	return null;
    }
    
    private List<PathNode> getJoinpoints(URNmodelElement aspectMap, URNmodelElement pointcutMap) {
    	List<PathNode> joinpoints = new ArrayList();
   		// TODO assumes that there is only one aspect map for this aspect in the model
    	List diagrams = urn.getUrndef().getSpecDiagrams();
    	for (Iterator iter = diagrams.iterator(); iter.hasNext();) {
			URNmodelElement diagram = (URNmodelElement) iter.next();
			if (diagram instanceof UCMmap && !diagram.equals(aspectMap) && !diagram.equals(pointcutMap)) {
				// don't include the map if it is a plug-in of only pointcut stubs
				boolean include = false;
				for (int i = 0; i < ((UCMmap) diagram).getParentStub().size(); i++) {
					Stub stub = ((PluginBinding) ((UCMmap) diagram).getParentStub().get(i)).getStub();
					if (stub.getAopointcut() == PointcutKind.NONE_LITERAL) {
						// it's not a pointcut stub, so let's include the plugin
						include = true;
					}
				}
				// also include any root map
				if (include || ((UCMmap) diagram).getParentStub().size() == 0) {
					joinpoints.addAll(((UCMmap) diagram).getNodes());					
				}
			}
		} 
    	return joinpoints;
    }
    
	private void disposeMatchingCompositionInfo() {
		UCMAspectComposer.dispose();
		PointcutMatcher.dispose();
	}

	private String capture(MatchList result) {
		String capture = "";
		if (result != null) {
			for (Iterator iter = result.getMatchList().iterator(); iter.hasNext();) {
				Match mappings = (Match) iter.next();
				for (Iterator iterator = mappings.getMatch().iterator(); iterator.hasNext();) {
					Mapping mapping = (Mapping) iterator.next();
					if (mapping.getPointcutElement().getElement() == null)
						capture += "null" + " --> " + mapping.getJoinpoint().getName() + "[" + mapping.getJoinpoint().getId() + "]     ";
					else
						capture += mapping.getPointcutElement().getName() + "[" + mapping.getPointcutElement().getId() + "] --> " + mapping.getJoinpoint().getName() + "[" + mapping.getJoinpoint().getId() + "]     ";
				}
				capture += "-----     ";
			}
		}
		else
			capture += "no match";
		return capture;
	}

	private static String capture(List<AspectMarkerMappings> result) {
		String capture = "";
		for (int i = 0; i < result.size(); i++) {
			PathNode joinpoint = (PathNode) result.get(i).getFirstMapping().get(0);
			NodeConnection insertionPoint = (NodeConnection) result.get(i).getInsertionPoint();
			capture += joinpoint.getName() + "[" + joinpoint.getId() + "] (insertion point: " + ((PathNode) insertionPoint.getSource()).getName() + "[" + ((PathNode) insertionPoint.getSource()).getId() + "]<-->" + ((PathNode) insertionPoint.getTarget()).getName() + "[" + ((PathNode) insertionPoint.getTarget()).getId() + "]     -----     ";
		}
		return capture;
	}

}