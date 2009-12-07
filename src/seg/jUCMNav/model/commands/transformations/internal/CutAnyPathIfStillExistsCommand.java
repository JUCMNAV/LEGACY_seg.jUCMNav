package seg.jUCMNav.model.commands.transformations.internal;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.transformations.CutAnyPathCommand;
import ucm.map.NodeConnection;
import ucm.map.UCMmap;

public class CutAnyPathIfStillExistsCommand extends CompoundCommand {

    protected UCMmap map;
    protected boolean built = false;
    protected Vector pathsToCut;

    /**
     * Delayed CutAnyPathCommand
     * 
     */
    public CutAnyPathIfStillExistsCommand(UCMmap map, Vector pathsToCut) {

        this.map = map;
        this.pathsToCut = pathsToCut;
    }

    public boolean canExecute() {
        return true;
    }

    public void execute() {
        if (!built) {

            if (pathsToCut != null) {
                for (Iterator iterator = pathsToCut.iterator(); iterator.hasNext();) {
                    NodeConnection nc = (NodeConnection) iterator.next();
                    if (map != null && map.getUrndefinition() != null && nc.getDiagram() != null && nc.getSource() != null && nc.getTarget() != null) {
                        add(new CutAnyPathCommand(map, nc, nc.getSource().getX() + 20, nc.getSource().getY() + 20));
                    }
                }
            }
            built = true;
        }
        super.execute();
    }
}
