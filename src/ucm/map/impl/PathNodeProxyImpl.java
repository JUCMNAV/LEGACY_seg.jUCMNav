/*
 * Created on Mar 29, 2005
 */
package ucm.map.impl;

import org.eclipse.emf.common.util.EList;

import ucm.map.PathNode;
import ucm.map.PathNodeProxy;

/**
 * @author Jordan
 */
public class PathNodeProxyImpl implements PathNodeProxy {
    PathNode node;
    
    public PathNodeProxyImpl(PathNode node) {
        this.node = node;
    }
    
    public int getNodeX() {
        return node.getX();
    }
    
    public int getNodeY() {
        return node.getY();
    }
    
    //If these coordinates are relative, convert them and return absolute coordinates.
    public int getLabelX() {
        return node.getLabelX();
    }
    
    public void setLabelX(int x) {
        node.setLabelX(x);
    }
    
    public int getLabelY() {
        return node.getLabelY();
    }
    
    public void setLabelY(int y) {
        node.setLabelY(y);
    }
    
    public String getLabelText() {
        return node.getName();
    }
    
    public void setLabelText(String text) {
        node.setName(text);
    }
    
    public EList eAdapters() {
        return node.eAdapters();
    }
}
