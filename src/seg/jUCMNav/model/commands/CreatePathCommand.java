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

	/**
	 * 
	 */
	public CreatePathCommand() {
		super();
	}

	
	public void execute() {
		UcmFactory factory = UcmFactory.eINSTANCE;
		// start-----node-----end
		start = factory.createStartPoint();
		start.setX(position.x);
		start.setY(position.y);
		
		node = factory.createNode();
		node.setX(position.x+25);
		node.setY(position.y);
		
		end = factory.createEndPoint();
		end.setX(position.x+50);
		end.setX(position.y);
		
		path.setStartpoint(start);
		path.setEndpoint(end);
		path.getNodes().add(node);
		
		redo();
	}
	
	public void redo() {
		diagram.getPaths().add(path);
	}
	
	public void undo() {
		diagram.getPaths().remove(path);
	}
	
	public UcmDiagram getDiagram() {
		return diagram;
	}
	
	public void setDiagram(UcmDiagram diagram) {
		this.diagram = diagram;
	}
	
	public Path getPath() {
		return path;
	}
	
	public void setPath(Path path) {
		this.path = path;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
}
