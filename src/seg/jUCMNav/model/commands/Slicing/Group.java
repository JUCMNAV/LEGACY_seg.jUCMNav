package seg.jUCMNav.model.commands.Slicing;
import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.model.util.*;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.StrategiesGroup;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIConversion;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart;
import seg.jUCMNav.figures.PathNodeFigure;
import ucm.UCMspec;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Anything;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;
import urncore.Comment;
import urncore.Component;
import urncore.ComponentLabel;
import urncore.Concern;
import urncore.Condition;
import urncore.GRLmodelElement;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.NodeLabel;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;












// color packages
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
/*import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RoundedRectangleBorder;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;*/
import org.eclipse.swt.graphics.Color;

public class Group {

	public ArrayList<NodeConnection> elements=new  ArrayList<NodeConnection>();
	public NodeConnection Parent;
	public Group() {
		
		
	
	}

}
