package seg.jUCMNav.importexport.html;

import urncore.IURNDiagram;

/**
 * The value object of menu items in the XML menu bar
 * 
 * @author pchen
 * 
 */
public class HTMLMenuItem {
    public static final String TYPE_UCM = "UCM"; //$NON-NLS-1$
    public static final String TYPE_GRL = "GRL"; //$NON-NLS-1$
    public static final String TYPE_UCM_DEF = "UCM Definitions"; //$NON-NLS-1$
    public static final String TYPE_UCM_SCEN = "UCM Scenarios"; //$NON-NLS-1$
    public static final String TYPE_GRL_DEF = "GRL Definitions"; //$NON-NLS-1$
    public static final String TYPE_MSC = "MSC"; //$NON-NLS-1$

    private static String diagramName = ""; //$NON-NLS-1$
    private static String type = ""; //$NON-NLS-1$
    private static String leafText = ""; //$NON-NLS-1$
    private static String link = ""; //$NON-NLS-1$
    private static int baseX = 0;
    private static int baseY = 0;

    private IURNDiagram diagram = null;

    public void reset() {
        diagramName = ""; //$NON-NLS-1$
        type = ""; //$NON-NLS-1$
        leafText = ""; //$NON-NLS-1$
        link = ""; //$NON-NLS-1$
        baseX = 0;
        baseY = 0;

        this.diagram = null;
    }

    /**
     * 
     * @return baseX
     */
    public int getBaseX() {
        return baseX;
    }

    /**
     * @param _baseX
     */
    public void setBaseX(int _baseX) {
        baseX = _baseX;
    }

    /**
     * @return baseY
     */
    public int getBaseY() {
        return baseY;
    }

    /**
     * @param _baseY
     */
    public void setBaseY(int _baseY) {
        baseY = _baseY;
    }

    /**
     * @return leafText
     */
    public String getLeafText() {
        return leafText;
    }

    /**
     * @param _leafText
     */
    public void setLeafText(String _leafText) {
        leafText = _leafText.trim();
    }

    /**
     * @return link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param _link
     */
    public void setLink(String _link) {
        link = _link;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param _type
     */
    public void setType(String _type) {
        type = _type;
    }

    /**
     * @return diagram
     */
    public IURNDiagram getDiagram() {
        return diagram;
    }

    /**
     * @param diagram
     */
    public void setDiagram(IURNDiagram diagram) {
        this.diagram = diagram;
    }

    /**
     * @return the diagramName
     */
    public String getDiagramName() {
        return diagramName;
    }

    /**
     * @param _diagramName
     *            the diagramName to set
     */
    public void setDiagramName(String _diagramName) {
        diagramName = _diagramName;
    }
}
