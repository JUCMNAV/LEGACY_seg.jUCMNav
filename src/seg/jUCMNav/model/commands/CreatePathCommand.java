/*
 * Created on 2005-02-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Link;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.StartPoint;
import seg.jUCMNav.model.ucm.UcmDiagram;
import seg.jUCMNav.model.ucm.UcmFactory;

/**
 * Created 2005-02-21
 * 
 * @author Etienne Tremblay
 */
public class CreatePathCommand extends Command {
	
	private UcmDiagram diagram;
	private Point position;
	private Path path;
	private StartPoint start;
	private Node node;
	private EndPoint end;
	private Link link1;
	private Link link2;

	/**
	 * 
	 */
	public CreatePathCommand() {
		super();
	}

	
	public void execute() {
		UcmFactory factory = UcmFactory.eINSTANCE;
		// start-----node-----end
		start.setX(position.x);
		start.setY(position.y);
		
		node = factory.createNode();
		node.setX(position.x+50);
		node.setY(position.y);
		
		link1 = factory.createLink();
		link1.setSource(start);
		link1.setTarget(node);
		
		end = factory.createEndPoint();
		end.setX(position.x+100);
		end.setY(position.y);
		
		link2 = factory.createLink();
		link2.setSource(node);
		link2.setTarget(end);
		
		path = factory.createPath();
		path.setStartpoint(start);
		path.setEndpoint(end);
		path.getNodes().add(node);
		
		redo();
	}
	
	public void redo() {
		diagram.getPaths().add(path);
		
		diagram.getNodes().add(start);
		diagram.getNodes().add(node);
		diagram.getNodes().add(end);
		
		diagram.getLinks().add(link1);
		diagram.getLinks().add(link2);
	}
	
	public void undo() {
		diagram.getPaths().remove(path);
		
		diagram.getNodes().remove(start);
		diagram.getNodes().remove(node);
		diagram.getNodes().remove(end);
		
		diagram.getLinks().remove(link2);
		diagram.getLinks().remove(link1);
	}
	
	public UcmDiagram getDiagram() {
		return diagram;
	}
	
	public void setDiagram(UcmDiagram diagram) {
		this.diagram = diagram;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	/**
	 * @return Returns the start.
	 */
	public StartPoint getStart() {
		return start;
	}
	/**
	 * @param start The start to set.
	 */
	public void setStart(StartPoint start) {
		this.start = start;
	}
}
