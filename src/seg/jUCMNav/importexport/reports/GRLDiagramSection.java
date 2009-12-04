package seg.jUCMNav.importexport.reports;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.Iterator;

import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import urn.URNlink;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

import com.lowagie.text.Document;

/**
 * 
 * @author Andrew Miga
 */

public class GRLDiagramSection extends PDFReportDiagram {

    public GRLDiagramSection() {

    }

    public void createGRLDiagramDescription(Document document, URNmodelElement element, IURNDiagram diagram) {
        try {
            insertDiagramMetadata(document, element);
            outputGRLIntentionalElements(document, diagram);
            outputGRLBeliefs(document, diagram);
            outputIntentionalElementURNLinks(document, diagram);
            outputActorURNlinks(document, diagram);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void outputGRLBeliefs(Document document, IURNDiagram diagram) {
        boolean hasData = false;

        if (!ReportGeneratorPreferences.getGRLShowBeliefs())
            return;

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
            URNmodelElement currentElement = (URNmodelElement) iter.next();
            if (currentElement instanceof Belief)
                hasData = hasGRLBeliefData((Belief) currentElement);
        }

        if (!hasData)
            return;

        insertDiagramSectionHeader(document, tableParams, "Beliefs");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            URNmodelElement currentElement = (URNmodelElement) iter.next();
            if (currentElement instanceof Belief) {
                Belief currentBelief = (Belief) currentElement;
                if (hasGRLBeliefData(currentBelief)) {
                    ReportUtils.writeLineWithSeparator(document, currentBelief.getName(), ": ", notNull(currentBelief.getDescription()), descriptionFont, true);
                    insertMetadata(document, currentBelief.getMetadata());
                }
            }
        }
    }

    private boolean hasGRLBeliefData(Belief belief) {
        return (ReportUtils.notEmpty(belief.getDescription()) || !belief.getMetadata().isEmpty());
    }

    private void outputGRLIntentionalElements(Document document, IURNDiagram diagram) {
        boolean hasData = false;

        if (!ReportGeneratorPreferences.getGRLShowIntentionalElements())
            return;

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
            URNmodelElement currentElement = (URNmodelElement) iter.next();
            if (currentElement instanceof IntentionalElementRef) {
                IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();
                hasData = hasGRLIntentionalElementData(ie);
            }
        }

        if (!hasData)
            return;

        insertDiagramSectionHeader(document, tableParams, "Intentional Elements");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            URNmodelElement currentElement = (URNmodelElement) iter.next();
            if (currentElement instanceof IntentionalElementRef) {
                IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();
                if (hasGRLIntentionalElementData(ie)) {
                    ReportUtils.writeLineWithSeparator(document, ie.getName(), ": ", notNull(ie.getDescription()), descriptionFont, true);
                    insertMetadata(document, ie.getMetadata());
                }
            }
        }
    }

    private boolean hasGRLIntentionalElementData(IntentionalElement ie) {
        return (ReportUtils.notEmpty(ie.getDescription()) || !ie.getMetadata().isEmpty());
    }

    private void outputIntentionalElementURNLinks(Document document, IURNDiagram diagram) {
        boolean hasData = false;

        if (!ReportGeneratorPreferences.getShowURNLinks())
            return;

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
            URNmodelElement currentElement = (URNmodelElement) iter.next();
            if (currentElement instanceof IntentionalElementRef) {
                IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();
                if (!ie.getFromLinks().isEmpty() || !ie.getToLinks().isEmpty())
                    hasData = true;
            }
        }

        if (!hasData)
            return;

        insertDiagramSectionHeader(document, tableParams, "Intentional Element URN Links");

        for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
            URNmodelElement currentElement = (URNmodelElement) iter.next();
            if (currentElement instanceof IntentionalElementRef) {
                IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();

                for (Iterator iter1 = ie.getFromLinks().iterator(); iter1.hasNext();) {

                    URNlink link = (URNlink) iter1.next();
                    String elementType = link.getToElem().getClass().getName();
                    elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                    ReportUtils.writeLineWithSeparator(document, ie.getName(), " ==> (", elementType + ")" + link.getToElem().getName(), descriptionFont, true);
                    if (ReportUtils.notEmpty(link.getType())) {
                        ReportUtils.writeLineWithSeparator(document, "     Link Type", ": ", link.getType(), descriptionFont, true);
                    }
                    insertMetadata(document, link.getMetadata());
                }

                for (Iterator iter1 = ie.getToLinks().iterator(); iter1.hasNext();) {

                    URNlink link = (URNlink) iter1.next();
                    String elementType = link.getFromElem().getClass().getName();
                    elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                    ReportUtils.writeLineWithSeparator(document, ie.getName(), " <== (", elementType + ")" + link.getFromElem().getName(), descriptionFont,
                            true);
                    if (ReportUtils.notEmpty(link.getType())) {
                        ReportUtils.writeLineWithSeparator(document, "     Link Type", ": ", link.getType(), descriptionFont, true);
                    }
                    insertMetadata(document, link.getMetadata());
                }
            }
        }
    }

    private void outputActorURNlinks(Document document, IURNDiagram diagram) {
        ActorRef currentActorRef;
        Actor currentActor;
        boolean hasURNlinks = false;

        if (!ReportGeneratorPreferences.getShowURNLinks())
            return;

        for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext() && !hasURNlinks;) {
            currentActorRef = (ActorRef) iter.next();
            currentActor = (Actor) currentActorRef.getContDef();
            if (!currentActor.getFromLinks().isEmpty() || !currentActor.getToLinks().isEmpty())
                hasURNlinks = true;
        }

        if (!hasURNlinks)
            return;

        insertDiagramSectionHeader(document, tableParams, "Actor URN Links");

        for (Iterator iter = diagram.getContRefs().iterator(); iter.hasNext();) {
            currentActorRef = (ActorRef) iter.next();
            currentActor = (Actor) currentActorRef.getContDef();

            for (Iterator iter1 = currentActor.getFromLinks().iterator(); iter1.hasNext();) {

                URNlink link = (URNlink) iter1.next();
                String elementType = link.getToElem().getClass().getName();
                elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                ReportUtils.writeLineWithSeparator(document, currentActor.getName(), " ==> (", elementType + ") " + link.getToElem().getName(),
                        descriptionFont, true);
                if (ReportUtils.notEmpty(link.getType()))
                    ReportUtils.writeLineWithSeparator(document, "     Link Type", ": ", link.getType(), descriptionFont, true);

                insertMetadata(document, link.getMetadata());
            }

            for (Iterator iter1 = currentActor.getToLinks().iterator(); iter1.hasNext();) {

                URNlink link = (URNlink) iter1.next();
                String elementType = link.getFromElem().getClass().getName();
                elementType = elementType.substring(elementType.lastIndexOf('.') + 1, elementType.length() - 4);

                ReportUtils.writeLineWithSeparator(document, currentActor.getName(), " <== (", elementType + ") " + link.getFromElem().getName(),
                        descriptionFont, true);
                if (ReportUtils.notEmpty(link.getType()))
                    ReportUtils.writeLineWithSeparator(document, "     Link Type", ": ", link.getType(), descriptionFont, true);

                insertMetadata(document, link.getMetadata());
            }
        }
    }

}