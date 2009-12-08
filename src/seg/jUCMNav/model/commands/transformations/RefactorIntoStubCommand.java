package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.delete.DeletePathNodeCommand;
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

public class RefactorIntoStubCommand extends CompoundCommand implements ICreateElementCommand, IGlobalStackCommand{

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
        getAddedMap().setName("Extracted UCM");
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

        for (Iterator iterator = compRefs.iterator(); iterator.hasNext();) {
            ComponentRef cr = (ComponentRef) iterator.next();
            ComponentRef clonedCr = (ComponentRef) EcoreUtil.copy(cr);
            resetCloneId(clonedCr);
            clonedCr.setContDef(cr.getContDef()); // command will undo this.
            add(new AddContainerRefCommand(getAddedMap(), clonedCr));
        }

        for (Iterator iterator = startingNodes.iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();
            if (!(pn instanceof StartPoint || pn instanceof EndPoint || pn instanceof Connect))
                add(new DeletePathNodeCommand(pn, null));
        }

        PathNode first = ((PathNode) startingNodes.get(0));
        UCMmap oldMap = (UCMmap) first.getDiagram();
        CreatePathCommand cmd3 = new CreatePathCommand(oldMap, first.getX() - 100, first.getY());
        cmd3.createElements();
        add(cmd3);

        EmptyPoint empty = (EmptyPoint) cmd3.getNewMiddleNode();

        setAddedStub((Stub) ModelCreationFactory.getNewObject(urn, Stub.class));
        getAddedStub().setName("Extracted UCM");
        add(new ReplaceEmptyPointCommand(empty, getAddedStub()));
        add(new AddPluginCommand(getAddedStub(), getAddedMap()));

        Vector pathsToCut = new Vector();
        // TODO: enhance this algorithm.
        for (Iterator iterator = first.getPred().iterator(); iterator.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator.next();
            pathsToCut.add(nc);
        }

        add(new CutAnyPathIfStillExistsCommand(oldMap, pathsToCut));
        add(new AttachNewExtremitiesToStubCommand(oldMap, getAddedStub(), true));

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
