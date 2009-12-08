package seg.jUCMNav.model.commands;

import urncore.IURNDiagram;

/**
 * 
 * These commands should be executed in the global command stack - not the individual editor stacks. 
 * @author jkealey
 *
 */
public interface IGlobalStackCommand {

    public IURNDiagram getAffectedDiagram();
    
}
