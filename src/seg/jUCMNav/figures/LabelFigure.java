package seg.jUCMNav.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Figure for UCM Labels; uses an EditableLabel.
 * 
 * @author Jordan, Jean-François Roy
 */
public class LabelFigure extends Label implements LabelElementFigure {
    //Mode of the label
    public final static int LINK_NOLINK = 0;
    public final static int LINK_UNKNOWN = 1;
    public final static int LINK_SATISFICED = 2;
    public final static int LINK_WEAKSATISFICED = 3;
    public final static int LINK_DENIED = 4;
    public final static int LINK_WEAKDENIED = 5;
    
    public int currentMode;
    
    private Image modeImg;
    
    private boolean selected;
    
    /** The inner TextFlow **/
   // protected TextFlow textFlow;
    
    public LabelFigure() {
        currentMode = LINK_NOLINK;
//        FlowPage flowPage = new FlowPage();
//
//        textFlow = new TextFlow();
//        textFlow.setLayoutManager(new ParagraphTextLayout(textFlow,
//                ParagraphTextLayout.WORD_WRAP_SOFT));
//
//        flowPage.add(textFlow);
//
//        setLayoutManager(new StackLayout());
//        add(flowPage);
    }

    public void dispose(){
        if (modeImg != null) {
            modeImg.dispose();
            modeImg = null;
        }
    }
    
    private Rectangle getSelectionRectangle() {
        Rectangle bounds = getBounds().getCopy();//textFlow.getBounds().getCopy();
        bounds.expand(new Insets(2, 2, 0, 0));
        translateToParent(bounds);
        bounds.intersect(getBounds());
        return bounds;
    }
    
//    /* (non-Javadoc)
//     * @see seg.jUCMNav.figures.LabelElementFigure#getText()
//     */
//    public String getText() {
//        return textFlow.getText();
//    }
    
    /**
     * paints figure differently depends on the whether the figure has focus or is selected
     */
    protected void paintFigure(Graphics graphics) {
        
        if (selected) {
            graphics.pushState();
            graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
            graphics.fillRectangle(getSelectionRectangle());
            graphics.popState();
            graphics.setForegroundColor(ColorConstants.white);
            
        }
        
        super.paintFigure(graphics);
    }

    public void setMode(int mode){
        if (currentMode != mode){
            currentMode = mode;
            //Set the icon
            if (modeImg != null) {
                modeImg.dispose();
                modeImg = null;
            }

            if (currentMode == LINK_UNKNOWN){
                modeImg = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/urnlink.gif")).createImage();    
            } else if (currentMode == LINK_SATISFICED){
                modeImg = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/satisficedUCM.gif")).createImage();    
            } else if (currentMode == LINK_WEAKSATISFICED){
                modeImg = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/wsatisficedUCM.gif")).createImage();    
            } else if (currentMode == LINK_DENIED){
                modeImg = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/deniedUCM.gif")).createImage();    
            } else if (currentMode == LINK_WEAKDENIED){
                modeImg = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/wdeniedUCM.gif")).createImage();    
            } 
            this.setIcon(modeImg);

            repaint();
        }
    }
    /**
     * Sets the selection state of this SimpleActivityLabel
     * 
     * @param b
     *            true will cause the label to appear selected
     */
    public void setSelected(boolean b) {
        selected = b;
        repaint();
    }
//    /* (non-Javadoc)
//     * @see seg.jUCMNav.figures.LabelElementFigure#setText(java.lang.String)
//     */
//    public void setText(String newText) {
//        textFlow.setText(newText);
//    }

}
