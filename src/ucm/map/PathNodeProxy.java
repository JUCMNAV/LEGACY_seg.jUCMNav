/*
 * Created on Mar 29, 2005
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

/**
 * @author Jordan
 */
public interface PathNodeProxy {
    public int getNodeX();
    public int getNodeY();
    
    public int getLabelX();
    public void setLabelX(int x);
    
    public int getLabelY();
    public void setLabelY(int y);
    
    public String getLabelText();
    public void setLabelText(String text);
    
    public EList eAdapters();
}
