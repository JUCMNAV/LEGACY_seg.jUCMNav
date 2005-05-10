/*
 * Created on 8-May-2005
 *
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.AndFork;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;

/**
 * @author jpdaigle
 *  
 */
public class AddAndForkCommand extends Command implements JUCMNavCommand {
	// Where to create the Fork
	PathGraph _pg;

	EmptyPoint _originEp;

	// New items
	AndFork _newFork;

	EmptyPoint _newEmptyPoint;

	EndPoint _newEndPoint;

	NodeConnection _newLink1, _newLink2;

	/**
	 *  
	 */
	public AddAndForkCommand() {
		super();
		this.setLabel("Add AND-Fork");
	}

	public AddAndForkCommand(PathGraph pg, EmptyPoint ep) {
		_pg = pg;
		_originEp = ep;
		this.setLabel("Add AND-Fork");
	}

	/**
	 * @param label
	 */
	public AddAndForkCommand(String label) {
		super(label);
		this.setLabel(label);
		// TODO Auto-generated constructor stub
	}

	public void execute() {
		if (_originEp != null && _pg != null) {
			int x, y;
			x = _originEp.getX();
			y = _originEp.getY();

			assert (_originEp.getPred().size() > 0);
			assert (_originEp.getSucc().size() > 0);

			NodeConnection ncPred, ncTarg;
			ncPred = (NodeConnection) _originEp.getPred().get(0);
			ncTarg = (NodeConnection) _originEp.getSucc().get(0);

			// OrFork -- EmptyPoint -- EndPoint
			_newFork = (AndFork) ModelCreationFactory
					.getNewObject(AndFork.class);
			_newFork.setX(x);
			_newFork.setY(y);

			ncPred.setTarget(_newFork);
			ncTarg.setSource(_newFork);

			_newEmptyPoint = (EmptyPoint) ModelCreationFactory
					.getNewObject(EmptyPoint.class);
			_newEmptyPoint.setX(x + 25);
			_newEmptyPoint.setY(y - 20);

			_newLink1 = (NodeConnection) ModelCreationFactory
					.getNewObject(NodeConnection.class);
			_newLink1.setSource(_newFork);
			_newLink1.setTarget(_newEmptyPoint);

			_newEndPoint = (EndPoint) ModelCreationFactory
					.getNewObject(EndPoint.class);
			_newEndPoint.setX(x + 100);
			_newEndPoint.setY(y - 50);

			_newLink2 = (NodeConnection) ModelCreationFactory
					.getNewObject(NodeConnection.class);
			_newLink2.setSource(_newEmptyPoint);
			_newLink2.setTarget(_newEndPoint);

			// TODO Add an empty point *ON* the connection going towards the
			// EndPoint

			redo();
		}
	}

	public void redo() {
		// Add the new objects to the graph
		testPreConditions();
		_pg.getNodeConnections().add(_newLink1);
		_pg.getNodeConnections().add(_newLink2);

		_pg.getPathNodes().add(_newFork);
		_pg.getPathNodes().add(_newEmptyPoint);
		_pg.getPathNodes().add(_newEndPoint);

		// Delete old node
		_pg.getPathNodes().remove(_originEp);
		//_originEp = null;

		testPostConditions();
	}

	public void undo() {
		testPreConditions();
		testPostConditions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		// TODO Auto-generated method stub

	}

}