package seg.jUCMNav.figures.kpi;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GroupBoxBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.kpiViewEditparts.KPIViewObject;
import seg.jUCMNav.figures.ColorManager;

/**
 * The figure that represent a single KPI in KPIView
 * 
 * @author pchen
 * 
 */
public class KPIViewObjectFigure extends Figure {
    protected final static int DEFAULT_TEXT_WIDTH = 100;
    protected final static int DEFAULT_TEXT_HEIGHT = 30;

    protected final static int DEFAULT_BAR_WIDTH = 300;
    protected final static int DEFAULT_BAR_HEIGHT = 20;

    protected final static int LABEL_PADDING_X = 0;
    protected final static int LABEL_PADDING_Y = 5;

    protected final static int BORDER_SHIFT_X = 16;
    protected final static int BORDER_SHIFT_Y = 21;

    private KPIViewObject kpiViewObject;

    protected XYLayout xylayout;

    protected FlowPage groupsFlowPage;
    protected TextFlow groupsTextFlow;

    protected FlowPage intElemsFlowPage;
    protected TextFlow intElemsTextFlow;

    protected FlowPage kpiInformationFlowPage;
    protected TextFlow kpiInformationTextFlow;

    protected FlowPage descFlowPage;
    protected TextFlow descTextFlow;

    protected FlowPage unitFlowPage;
    protected TextFlow unitTextFlow;

    protected FlowPage targetValueFlowPage;
    protected TextFlow targetValueTextFlow;

    protected FlowPage thresholdValueFlowPage;
    protected TextFlow thresholdValueTextFlow;

    protected FlowPage worstValueFlowPage;
    protected TextFlow worstValueTextFlow;

    protected FlowPage evalValueFlowPage;
    protected TextFlow evalValueTextFlow;

    private Rectangle kpiBar = new Rectangle();;
    private Rectangle evalBar = new Rectangle();
    private int thresholdLine_x1 = 0;
    private int thresholdLine_y1 = 0;
    private int thresholdLine_x2 = 0;
    private int thresholdLine_y2 = 0;

    private int evalLine_x1 = 0;
    private int evalLine_y1 = 0;
    private int evalLine_x2 = 0;
    private int evalLine_y2 = 0;

    private String evalColorStr = "0,0,0"; //$NON-NLS-1$

    public KPIViewObjectFigure(KPIViewObject object) {
        this.kpiViewObject = object;

        initializeFigure();
    }

    /**
     * 
     * @return Returns the default dimension.
     * @see seg.jUCMNav.figures.util.JUCMNavFigure
     */
    public Dimension getDefaultDimension() {
        return new Dimension(kpiViewObject.getWidth(), kpiViewObject.getHeight());
    }

    /**
     * 
     * @return Returns the default text dimension.
     * @see seg.jUCMNav.figures.util.JUCMNavFigure
     */
    public static Dimension getDefaultTextDimension() {
        return new Dimension(DEFAULT_TEXT_WIDTH, DEFAULT_TEXT_HEIGHT);
    }

    private void initializeFigure() {
        xylayout = new XYLayout();
        this.setLayoutManager(xylayout);

        this.setBackgroundColor(ColorManager.WHITE);
        this.setSize(getDefaultDimension());
        this.setOpaque(true);
        this.setForegroundColor(ColorManager.BLACK);

        GroupBoxBorder border = new GroupBoxBorder();
        border.setLabel(kpiViewObject.getName() + " (" + kpiViewObject.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        border.setTextColor(ColorManager.BLUE);
        this.setBorder(border);

        groupsFlowPage = new FlowPage();
        intElemsFlowPage = new FlowPage();
        kpiInformationFlowPage = new FlowPage();
        descFlowPage = new FlowPage();
        unitFlowPage = new FlowPage();
        targetValueFlowPage = new FlowPage();
        thresholdValueFlowPage = new FlowPage();
        worstValueFlowPage = new FlowPage();
        evalValueFlowPage = new FlowPage();

        groupsTextFlow = new TextFlow();
        intElemsTextFlow = new TextFlow();
        kpiInformationTextFlow = new TextFlow();
        descTextFlow = new TextFlow();
        unitTextFlow = new TextFlow();
        targetValueTextFlow = new TextFlow();
        thresholdValueTextFlow = new TextFlow();
        worstValueTextFlow = new TextFlow();
        evalValueTextFlow = new TextFlow();

        groupsTextFlow.setLayoutManager(new ParagraphTextLayout(groupsTextFlow, ParagraphTextLayout.WORD_WRAP_HARD));
        intElemsTextFlow.setLayoutManager(new ParagraphTextLayout(intElemsTextFlow, ParagraphTextLayout.WORD_WRAP_HARD));
        kpiInformationTextFlow.setLayoutManager(new ParagraphTextLayout(kpiInformationTextFlow, ParagraphTextLayout.WORD_WRAP_HARD));
        descTextFlow.setLayoutManager(new ParagraphTextLayout(descTextFlow, ParagraphTextLayout.WORD_WRAP_HARD));
        unitTextFlow.setLayoutManager(new ParagraphTextLayout(unitTextFlow, ParagraphTextLayout.WORD_WRAP_HARD));
        targetValueTextFlow.setLayoutManager(new ParagraphTextLayout(targetValueTextFlow, ParagraphTextLayout.WORD_WRAP_HARD));
        thresholdValueTextFlow.setLayoutManager(new ParagraphTextLayout(thresholdValueTextFlow, ParagraphTextLayout.WORD_WRAP_HARD));
        worstValueTextFlow.setLayoutManager(new ParagraphTextLayout(worstValueTextFlow, ParagraphTextLayout.WORD_WRAP_HARD));
        evalValueTextFlow.setLayoutManager(new ParagraphTextLayout(evalValueTextFlow, ParagraphTextLayout.WORD_WRAP_HARD));

        groupsFlowPage.add(groupsTextFlow);
        intElemsFlowPage.add(intElemsTextFlow);
        kpiInformationFlowPage.add(kpiInformationTextFlow);
        descFlowPage.add(descTextFlow);
        unitFlowPage.add(unitTextFlow);
        targetValueFlowPage.add(targetValueTextFlow);
        thresholdValueFlowPage.add(thresholdValueTextFlow);
        worstValueFlowPage.add(worstValueTextFlow);
        evalValueFlowPage.add(evalValueTextFlow);

        add(groupsFlowPage);
        add(intElemsFlowPage);
        add(kpiInformationFlowPage);
        add(descFlowPage);
        add(unitFlowPage);
        add(targetValueFlowPage);
        add(thresholdValueFlowPage);
        add(worstValueFlowPage);
        add(evalValueFlowPage);
    }

    protected void paintFigure(Graphics graphics) {
        drawKPIBar(graphics);
    }

    private void drawKPIBar(Graphics graphics) {
        // draw the whole KPI bar
        Rectangle kpiBarCopy = kpiBar.getCopy();
        kpiBarCopy.x = kpiBarCopy.x + getBounds().x;
        kpiBarCopy.y = kpiBarCopy.y + getBounds().y;

        graphics.setForegroundColor(ColorManager.LIGHTBLUE);
        graphics.setLineWidth(2);
        graphics.drawRectangle(kpiBarCopy);

        // draw the evaluation bar
        Rectangle evalBarCopy = evalBar.getCopy();
        evalBarCopy.x = evalBarCopy.x + getBounds().x;
        evalBarCopy.y = evalBarCopy.y + getBounds().y;

        Color evalColor = new Color(Display.getCurrent(), StringConverter.asRGB(evalColorStr));
        graphics.setBackgroundColor(evalColor);
        graphics.fillRectangle(evalBarCopy);

        // draw evaluation line
        graphics.setForegroundColor(evalColor);
        graphics.setLineWidth(3);
        graphics.drawLine(evalLine_x1 + getBounds().x, evalLine_y1 + getBounds().y, evalLine_x2 + getBounds().x, evalLine_y2 + getBounds().y);

        // draw threshold line
        graphics.setForegroundColor(ColorManager.BLUE);
        graphics.setLineWidth(3);
        graphics.drawLine(thresholdLine_x1 + getBounds().x, thresholdLine_y1 + getBounds().y, thresholdLine_x2 + getBounds().x, thresholdLine_y2
                + getBounds().y);
    }

    public void setupFigure() {
        setSubjectText();
        calculateFigures();
        setKPIBarText();
    }

    public void calculateFigures() {
        // calculate whole KPI bar
        kpiBar = new Rectangle();

        kpiBar.height = DEFAULT_BAR_HEIGHT;
        kpiBar.width = DEFAULT_BAR_WIDTH;
        kpiBar.x = (getBounds().width - kpiBar.width) / 2;
        kpiBar.y = BORDER_SHIFT_Y + unitFlowPage.getBounds().y + unitTextFlow.getPreferredSize().height + LABEL_PADDING_Y * 5;

        // calculate threshold line
        int thresholdPoint_x = 0;
        if (kpiViewObject.getTargetValue() == kpiViewObject.getWorstValue()) {
            thresholdPoint_x = (int) (kpiBar.width * 0.5);
        } else {
            thresholdPoint_x = (int) ((Math.abs(kpiViewObject.getThresholdValue() - kpiViewObject.getWorstValue()) / Math.abs(kpiViewObject.getTargetValue()
                    - kpiViewObject.getWorstValue())) * kpiBar.width);
        }

        thresholdLine_x1 = kpiBar.x + thresholdPoint_x;
        thresholdLine_y1 = kpiBar.y;
        thresholdLine_x2 = thresholdLine_x1;
        thresholdLine_y2 = thresholdLine_y1 + kpiBar.height + 5;

        // calculate the evaluation bar
        evalBar = new Rectangle();
        evalLine_x1 = 0;
        evalLine_y1 = kpiBar.y - 5;
        evalLine_x2 = 0;
        evalLine_y2 = thresholdLine_y1 + kpiBar.height;

        evalBar.height = kpiBar.height;
        evalBar.y = kpiBar.y;

        if (kpiViewObject.getTargetValue() == kpiViewObject.getWorstValue()) {
            if (kpiViewObject.getEvaluationLevel() >= 0) {
                evalBar.x = thresholdLine_x1;
                evalBar.width = (int) (kpiBar.width * 0.5);
                evalLine_x1 = evalBar.x + evalBar.width;
            } else {
                evalBar.x = kpiBar.x;
                evalBar.width = (int) (kpiBar.width * 0.5);
                evalLine_x1 = evalBar.x;
            }
        } else {
            if (kpiViewObject.getEvaluationLevel() >= 0) {
                int evalPoint_x = (int) (((double) kpiViewObject.getEvaluationLevel()) / 100 * (kpiBar.width - thresholdPoint_x)) + thresholdPoint_x;

                evalBar.x = thresholdLine_x1;
                evalBar.width = evalPoint_x - thresholdPoint_x;
                evalLine_x1 = evalBar.x + evalBar.width;
            } else {
                int evalPoint_x = thresholdPoint_x - (int) (((double) Math.abs(kpiViewObject.getEvaluationLevel())) / 100 * thresholdPoint_x);

                evalBar.x = kpiBar.x + evalPoint_x;
                evalBar.width = thresholdPoint_x - evalPoint_x;
                evalLine_x1 = evalBar.x;
            }
        }
        evalLine_x2 = evalLine_x1;

        if (kpiViewObject.getEvaluationLevel() == 0) {
            evalColorStr = "255,255,0"; //$NON-NLS-1$
        } else {
            int partial = (Math.abs((Math.abs(kpiViewObject.getEvaluationLevel()) - 100)) * 255 / 100);
            if (kpiViewObject.getEvaluationLevel() < 0) {
                evalColorStr = "255," + partial + ",0"; //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                evalColorStr = partial + ",255,0"; //$NON-NLS-1$
            }
        }
    }

    public void setSubjectText() {
        // Setup groups text
        String groupsTitle = Messages.getString("KPIViewObjectFigure.Groups"); //$NON-NLS-1$
        String groupsText = "["; //$NON-NLS-1$
        String[] groups = kpiViewObject.getIndicatorGroupNames();
        for (int i = 0; i < groups.length; i++) {
            groupsText += groups[i] + ", "; //$NON-NLS-1$
        }
        groupsText = groupsText.length() > 1 ? groupsText.substring(0, groupsText.length() - 2) + "]" : ""; //$NON-NLS-1$ //$NON-NLS-2$
        groupsTextFlow.setText(groupsTitle + groupsText);

        // Setup intentionalElements text
        String intElemsTitle = Messages.getString("KPIViewObjectFigure.EvaluationOf"); //$NON-NLS-1$
        String intElemsText = "["; //$NON-NLS-1$
        String[] intElems = kpiViewObject.getIntentionalElementNames();
        for (int i = 0; i < intElems.length; i++) {
            intElemsText += intElems[i] + ", "; //$NON-NLS-1$
        }
        intElemsText = intElemsText.length() > 1 ? intElemsText.substring(0, intElemsText.length() - 2) + "]" : ""; //$NON-NLS-1$ //$NON-NLS-2$
        intElemsTextFlow.setText(intElemsTitle + intElemsText);

        // Setup kpi information text
        String kpiInformationTitle = Messages.getString("KPIViewObjectFigure.Dimensions"); //$NON-NLS-1$
        String kpiInformationText = ""; //$NON-NLS-1$
        Map kpiInfoMap = kpiViewObject.getKpiInformationMap();
        Iterator kpiInfoNames = kpiInfoMap.keySet().iterator();
        while (kpiInfoNames.hasNext()) {
            String kpiInfoName = (String) kpiInfoNames.next();
            String kpiInfoSetting = (String) kpiInfoMap.get(kpiInfoName);
            kpiInformationText += "[ " + kpiInfoName + " : " + kpiInfoSetting + " ]  "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        kpiInformationText = kpiInformationText.length() > 0 ? kpiInformationText.substring(0, kpiInformationText.length() - 2) : ""; //$NON-NLS-1$
        kpiInformationTextFlow.setText(kpiInformationTitle + kpiInformationText);

        // Setup description text
        String descTitle = Messages.getString("KPIViewObjectFigure.Description"); //$NON-NLS-1$
        descTextFlow.setText(descTitle + kpiViewObject.getDescription());

        // Setup unit text
        String unitTitle = Messages.getString("KPIViewObjectFigure.Unit"); //$NON-NLS-1$
        unitTextFlow.setText(unitTitle + kpiViewObject.getUnit());

        // set constraints
        setSubjectFlowPageConstraint(groupsFlowPage, LABEL_PADDING_Y);
        setSubjectFlowPageConstraint(intElemsFlowPage, groupsFlowPage.getBounds().y + groupsTextFlow.getPreferredSize().height + LABEL_PADDING_Y);
        setSubjectFlowPageConstraint(kpiInformationFlowPage, intElemsFlowPage.getBounds().y + intElemsTextFlow.getPreferredSize().height + LABEL_PADDING_Y);
        setSubjectFlowPageConstraint(descFlowPage, kpiInformationFlowPage.getBounds().y + kpiInformationTextFlow.getPreferredSize().height + LABEL_PADDING_Y);
        setSubjectFlowPageConstraint(unitFlowPage, descFlowPage.getBounds().y + descTextFlow.getPreferredSize().height + LABEL_PADDING_Y);
    }

    private void setSubjectFlowPageConstraint(FlowPage flowPage, int y) {
        flowPage.setFont(JFaceResources.getFontRegistry().get(JFaceResources.DEFAULT_FONT));
        Dimension dimEditableLabel = flowPage.getPreferredSize().getCopy();

        // Calculate the size of the label and of the figure
        // Max size available for the label
        int width = getDefaultDimension().width - 2 * LABEL_PADDING_X - 50;
        int height = dimEditableLabel.height;

        int minWidth = flowPage.getPreferredSize(width, 1).width;

        // Loop until we have good dimension for the labels to fit in the node
        while (dimEditableLabel.width > (width * Math.floor((height - 1) / dimEditableLabel.height)) || width < minWidth) {
            height = height + dimEditableLabel.height;
            width = width + 20;

        }
        if (height < (getDefaultDimension().height - 2 * LABEL_PADDING_Y)) {
            height = getDefaultDimension().height - 2 * LABEL_PADDING_Y;
        }

        Rectangle r = new Rectangle();
        r.x = LABEL_PADDING_X;
        r.y = y;
        r.width = width;
        r.height = height;

        setConstraint(flowPage, r);
        flowPage.setLocation(new Point(r.x, r.y));
    }

    public void setKPIBarText() {
        // draw text boxes
        targetValueTextFlow.setText(Messages.getString("KPIViewObjectFigure.TargetValue") + String.valueOf(kpiViewObject.getTargetValue()) + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        thresholdValueTextFlow.setText(Messages.getString("KPIViewObjectFigure.ThresholdValue") + String.valueOf(kpiViewObject.getThresholdValue()) + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        worstValueTextFlow.setText(Messages.getString("KPIViewObjectFigure.WorstValue") + String.valueOf(kpiViewObject.getWorstValue()) + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        evalValueTextFlow
                .setText(Messages.getString("KPIViewObjectFigure.EvaluationValue") + String.valueOf(kpiViewObject.getEvaluationValue()) + Messages.getString("KPIViewObjectFigure.EvaluationLevel") //$NON-NLS-1$ //$NON-NLS-2$
                        + String.valueOf(kpiViewObject.getEvaluationLevel()) + ")"); //$NON-NLS-1$

        setKPITextFlowPageConstraint(targetValueFlowPage, kpiBar.x + kpiBar.width + 10 - BORDER_SHIFT_X, kpiBar.y - BORDER_SHIFT_Y);
        setKPITextFlowPageConstraint(thresholdValueFlowPage, thresholdLine_x2 - getDefaultTextDimension().width / 2 - BORDER_SHIFT_X, thresholdLine_y2 + 5
                - BORDER_SHIFT_Y);
        setKPITextFlowPageConstraint(worstValueFlowPage, kpiBar.x - getDefaultTextDimension().width + 35 - BORDER_SHIFT_X, kpiBar.y - BORDER_SHIFT_Y);
        setKPITextFlowPageConstraint(evalValueFlowPage, evalLine_x1 - getDefaultTextDimension().width / 2 - BORDER_SHIFT_X, evalLine_y1
                - getDefaultTextDimension().height + 6 - BORDER_SHIFT_Y);
    }

    private void setKPITextFlowPageConstraint(FlowPage flowPage, int x, int y) {
        flowPage.setFont(JFaceResources.getFontRegistry().get(JFaceResources.DEFAULT_FONT));
        Dimension dimEditableLabel = flowPage.getPreferredSize().getCopy();

        // Calculate the size of the label and of the figure
        // Max size available for the label
        int width = getDefaultTextDimension().width;
        int height = dimEditableLabel.height;

        int minWidth = flowPage.getPreferredSize(width, 1).width;

        // Loop until we have good dimension for the labels to fit in the node
        while (dimEditableLabel.width > (width * Math.floor((height - 1) / dimEditableLabel.height)) || width < minWidth) {
            height = height + dimEditableLabel.height;
            width = width + 20;

        }
        if (height < (getDefaultTextDimension().height)) {
            height = getDefaultTextDimension().height;
        }

        Rectangle r = new Rectangle();
        r.x = x;
        r.y = y;
        r.width = width;
        r.height = height;

        setConstraint(flowPage, r);
        flowPage.setLocation(new Point(r.x, r.y));
    }

    public int getPreferredHeight() {
        return thresholdLine_y2 + DEFAULT_TEXT_HEIGHT + 10;
    }

}
