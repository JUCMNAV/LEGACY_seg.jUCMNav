package seg.jUCMNav.model.commands.transformations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.IDelayedBuildCompoundCommand;
import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.changeConstraints.MoveNodeCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.delete.DeletePathNodeCommand;
import seg.jUCMNav.model.commands.delete.DeleteUselessStartNCEndCommand;
import seg.jUCMNav.model.commands.delete.DisconnectCommand;
import seg.jUCMNav.model.commands.transformations.internal.AttachNewExtremitiesToStubCommand;
import seg.jUCMNav.model.commands.transformations.internal.CutAnyPathIfStillExistsCommand;
import seg.jUCMNav.model.commands.transformations.internal.DuplicatePathCommand;
import seg.jUCMNav.model.util.ICreateElementCommand;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

public class RefactorIntoStubCommand extends CompoundCommand implements ICreateElementCommand, IGlobalStackCommand {

    private Vector startingPoints;
    private URNspec urn;
    private UCMmap addedMap;
    private Stub addedStub;

    public RefactorIntoStubCommand(URNspec urn, Vector startingPoints) {

        this.startingPoints = startingPoints;
        this.urn = urn;
        build();
    }

    protected void build() {
        if (startingPoints == null || startingPoints.size() == 0)
            return;

        CreateMapCommand cmd = new CreateMapCommand(urn);
        setAddedMap(cmd.getMap());
        getAddedMap().setName(Messages.getString("RefactorIntoStubCommand_ExtractedUCM")); //$NON-NLS-1$
        add(cmd);

        URNspec clonedUrn = (URNspec) EcoreUtil.copy(this.urn);
        Vector clonedStartingPoints = new Vector();
        Vector compRefs = new Vector();
        Vector startingNodes = new Vector();
        for (Iterator iterator = startingPoints.iterator(); iterator.hasNext();) {

            Object o = (Object) iterator.next();
            if (o instanceof PathNode) {
                PathNode pn = (PathNode) o;
                UCMmap clonedMap = (UCMmap) URNElementFinder.findMap(clonedUrn, ((UCMmap) pn.getDiagram()).getId());
                PathNode clonedPn = URNElementFinder.findPathNode(clonedMap, pn.getId());
                clonedStartingPoints.add(clonedPn);
                startingNodes.add(pn);
            } else if (o instanceof ComponentRef) {
                ComponentRef cr = (ComponentRef) o;
                compRefs.add(cr);
            }
        }

        DuplicatePathCommand cmd2 = new DuplicatePathCommand(this.urn, getAddedMap(), clonedStartingPoints, 0, 0);
        add(cmd2);

        HashMap duplicatedNodes = cmd2.getDuplicatedNodes();
        if (duplicatedNodes != null) {
            for (Iterator iterator = duplicatedNodes.values().iterator(); iterator.hasNext();) {
                Object object = (Object) iterator.next();
                if (object instanceof PathNode) {
                    PathNode pn = (PathNode) object;
                    add(new MoveNodeCommand(pn, pn.getX(), pn.getY()));
                }
            }
        }

        for (Iterator iterator = compRefs.iterator(); iterator.hasNext();) {
            ComponentRef cr = (ComponentRef) iterator.next();
            ComponentRef clonedCr = (ComponentRef) EcoreUtil.copy(cr);
            resetCloneId(clonedCr);
            clonedCr.setContDef(cr.getContDef()); // command will undo this.
            add(new AddContainerRefCommand(getAddedMap(), clonedCr));
        }

        Vector toReAttach = new Vector();
        for (Iterator iterator = startingNodes.iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();
            if (!(pn instanceof StartPoint || pn instanceof EndPoint || pn instanceof Connect)) {

                // if always true, we can get tiny loops out of stubs.
                boolean replaceWithEmpty = pn.getSucc().size() == 1 && pn.getPred().size() == 1;
                // boolean replaceWithEmpty = true;
                // if (pn instanceof WaitingPlace && pn.getPred().size() > 1)
                // replaceWithEmpty = false;

                if (startingNodes.size() == 1)
                    replaceWithEmpty = false;

                // TODO: when we have bindings made, we should know which extra paths to get rid of.

                // when false, we get the cleanest model, but hardest to read because of overlapping paths
                // when true, we get easier to read, but sometimes we get outside loops that are useless.
                // finding the right balance is hard.

                // assume we wanted to delete now, we just want to find what connects will be broken.
                DeletePathNodeCommand preDelete = new DeletePathNodeCommand(pn, null, replaceWithEmpty);
                findDisconnectedBranches(startingNodes, toReAttach, preDelete);

                // add a normal one, not yet built - to be built during execution.
                DeletePathNodeCommand delete = new DeletePathNodeCommand(pn, null, replaceWithEmpty);
                add(delete);
            }
        }

        PathNode first = ((PathNode) startingNodes.get(0));
        int x = 0;
        int y = 0;

        for (Iterator iterator = startingNodes.iterator(); iterator.hasNext();) {
            PathNode o = (PathNode) iterator.next();
            x += o.getX();
            y += o.getY();
        }

        x = x / startingNodes.size() - 100; // so that stub is in middle
        y = y / startingNodes.size();

        UCMmap oldMap = (UCMmap) first.getDiagram();
        // CreatePathCommand cmd3 = new CreatePathCommand(oldMap, first.getX() - 100, first.getY());
        CreatePathCommand cmd3 = new CreatePathCommand(oldMap, x, y);
        cmd3.createElements();
        add(cmd3);

        // System.out.println("Created: " + cmd3.getStart().getId() + ", " + cmd3.getEnd().getId());

        EmptyPoint empty = (EmptyPoint) cmd3.getNewMiddleNode();

        setAddedStub((Stub) ModelCreationFactory.getNewObject(urn, Stub.class));
        getAddedStub().setName(Messages.getString("RefactorIntoStubCommand_ExtractedUCM")); //$NON-NLS-1$
        add(new ReplaceEmptyPointCommand(empty, getAddedStub()));
        add(new AddPluginCommand(getAddedStub(), getAddedMap()));

        Vector pathsToCut = new Vector();
        // TODO: enhance this algorithm.
        for (Iterator iterator = first.getPred().iterator(); iterator.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator.next();
            pathsToCut.add(nc);
        }

        add(new CutAnyPathIfStillExistsCommand(oldMap, pathsToCut));
        add(new DeleteUselessStartNCEndCommand(oldMap, null));
        add(new AttachNewExtremitiesToStubCommand(oldMap, getAddedStub(), toReAttach, true));
        add(new RefactorIntoStubBindingsCommand(getAddedStub()));

        HashMap stubs = cmd2.getBindingsThatMustBeCreatedAfterExecution();
        if (stubs != null && stubs.size() > 0) {
            for (Iterator iterator = stubs.keySet().iterator(); iterator.hasNext();) {
                Stub oldStub = (Stub) iterator.next();
                CopyStubPluginUtils util = new CopyStubPluginUtils();
                Vector cmds = util.copyStubPlugins(urn, addedMap, (Stub) stubs.get(oldStub), oldStub);
                for (Iterator iterator2 = cmds.iterator(); iterator2.hasNext();) {
                    add((Command) iterator2.next());
                }
            }
        }
    }

    private void findDisconnectedBranches(Vector startingNodes, Vector toReAttach, CompoundCommand delete) {
        if (delete instanceof IDelayedBuildCompoundCommand)
            ((IDelayedBuildCompoundCommand) delete).build();

        for (int i = 0; i < delete.getChildren().length; i++) {
            if (delete.getChildren()[i] instanceof DisconnectCommand) {
                DisconnectCommand disconnectCommand = (DisconnectCommand) delete.getChildren()[i];
                if (disconnectCommand.getRight() instanceof StartPoint && startingNodes.contains(disconnectCommand.getRight())) {
                    toReAttach.add(disconnectCommand.getRight());
                }
                if (disconnectCommand.getLeft() instanceof EndPoint && startingNodes.contains(disconnectCommand.getLeft())) {
                    toReAttach.add(disconnectCommand.getLeft());
                }
            } else if (delete.getChildren()[i] instanceof CompoundCommand) {
                // recurse deeper
                findDisconnectedBranches(startingNodes, toReAttach, (CompoundCommand) delete.getChildren()[i]);
            }
        }
    }

    private void resetCloneId(URNmodelElement clone) {
        String name = clone.getName();
        clone.setId(""); //$NON-NLS-1$
        URNNamingHelper.setElementNameAndID(urn, clone);
        clone.setName(name);
    }

    public void setAddedMap(UCMmap addedMap) {
        this.addedMap = addedMap;
    }

    public UCMmap getAddedMap() {
        return addedMap;
    }

    public void setAddedStub(Stub addedStub) {
        this.addedStub = addedStub;
    }

    public Stub getAddedStub() {
        return addedStub;
    }

    public Object getNewModelElement() {
        return getAddedStub();
    }

    public IURNDiagram getAffectedDiagram() {
        return getAddedMap();
    }
}
