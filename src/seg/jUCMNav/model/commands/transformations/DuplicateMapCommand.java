package seg.jUCMNav.model.commands.transformations;

import grl.GRLGraph;
import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.ComponentBinding;
import ucm.map.InBinding;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.ResponsibilityBinding;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;

/**
 * This command duplicates a map or grl graph.
 * 
 * @author jkealey, pchen
 */
public class DuplicateMapCommand extends Command implements JUCMNavCommand, IGlobalStackCommand {

    private UCMmap oldDiagram; // The UCM diagram
    private GRLGraph oldGraph; // The GRL diagram
    private UCMmap newDiagram; // The UCM diagram
    private GRLGraph newGraph; // The GRL diagram
    private URNspec urn;

    public DuplicateMapCommand(UCMmap diagram) {
        this.oldDiagram = diagram;

        init();

    }

    public DuplicateMapCommand(GRLGraph diagram) {
        this.oldGraph = diagram;

        init();

    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        redo();
    }

    /**
     * Do init before execute because our command stacks need to call getNewDiagram() before it is executed.
     * 
     */
    private void init() {
        setLabel(Messages.getString("DuplicateMapCommand.DuplicateDiagram")); //$NON-NLS-1$

        if (oldDiagram != null)
            urn = oldDiagram.getUrndefinition().getUrnspec();
        else if (oldGraph != null)
            urn = oldGraph.getUrndefinition().getUrnspec();

        if (urn == null)
            return;

        // create a map to steal its id
        UCMmap tmp = (UCMmap) ModelCreationFactory.getNewObject(urn, UCMmap.class);

        if (oldDiagram != null) {
            newDiagram = (UCMmap) EcoreUtil.copy(oldDiagram);
            newDiagram.setId(tmp.getId());
        } else {
            newGraph = (GRLGraph) EcoreUtil.copy(oldGraph);
            newGraph.setId(tmp.getId());
        }
    }

    public IURNDiagram getNewDiagram() {
        if (newDiagram != null)
            return newDiagram;
        else
            return newGraph;

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (urn == null)
            return;
        testPreConditions();

        if (oldDiagram != null) {
            int index = urn.getUrndef().getSpecDiagrams().indexOf(oldDiagram);
            urn.getUrndef().getSpecDiagrams().add(index + 1, newDiagram);
            for (int i = 0; i < oldDiagram.getContRefs().size(); i++) {
                IURNContainerRef srcref = (IURNContainerRef) oldDiagram.getContRefs().get(i);
                IURNContainerRef destref = (IURNContainerRef) newDiagram.getContRefs().get(i);
                destref.setContDef(srcref.getContDef());
            }
            for (int i = 0; i < oldDiagram.getNodes().size(); i++) {
                IURNNode src = (IURNNode) oldDiagram.getNodes().get(i);
                IURNNode dest = (IURNNode) newDiagram.getNodes().get(i);

                if (src instanceof RespRef && dest instanceof RespRef) {
                    RespRef srcref = (RespRef) src;
                    RespRef destref = (RespRef) dest;

                    destref.setRespDef(srcref.getRespDef());
                } else if (src instanceof Stub && dest instanceof Stub) {
                    Stub srcstub = (Stub) src;
                    Stub deststub = (Stub) dest;

                    for (int j = 0; j < srcstub.getBindings().size(); j++) {
                        PluginBinding srcpb = (PluginBinding) srcstub.getBindings().get(j);
                        PluginBinding destpb = (PluginBinding) deststub.getBindings().get(j);

                        destpb.setPlugin(srcpb.getPlugin());
                        for (int k = 0; k < srcpb.getIn().size(); k++) {
                            InBinding srcib = (InBinding) srcpb.getIn().get(k);
                            InBinding destib = (InBinding) destpb.getIn().get(k);
                            destib.setStartPoint(srcib.getStartPoint());
                        }
                        for (int k = 0; k < srcpb.getOut().size(); k++) {
                            OutBinding srcob = (OutBinding) srcpb.getOut().get(k);
                            OutBinding destob = (OutBinding) destpb.getOut().get(k);
                            destob.setEndPoint(srcob.getEndPoint());
                        }
                        for (int k = 0; k < srcpb.getComponents().size(); k++) {
                            ComponentBinding srccb = (ComponentBinding) srcpb.getComponents().get(k);
                            ComponentBinding destcb = (ComponentBinding) destpb.getComponents().get(k);
                            destcb.setParentComponent(srccb.getParentComponent());
                            destcb.setPluginComponent(srccb.getPluginComponent());
                        }
                        for (int k = 0; k < srcpb.getResponsibilities().size(); k++) {
                            ResponsibilityBinding srccb = (ResponsibilityBinding) srcpb.getResponsibilities().get(k);
                            ResponsibilityBinding destcb = (ResponsibilityBinding) destpb.getResponsibilities().get(k);
                            destcb.setParentResp(srccb.getParentResp());
                            destcb.setPluginResp(srccb.getPluginResp());
                        }
                    }

                }
            }
        } else {
            int index = urn.getUrndef().getSpecDiagrams().indexOf(oldGraph);
            urn.getUrndef().getSpecDiagrams().add(index + 1, newGraph);

            for (int i = 0; i < oldGraph.getContRefs().size(); i++) {
                IURNContainerRef srcref = (IURNContainerRef) oldGraph.getContRefs().get(i);
                IURNContainerRef destref = (IURNContainerRef) newGraph.getContRefs().get(i);
                destref.setContDef(srcref.getContDef());
            }
            for (int i = 0; i < oldGraph.getNodes().size(); i++) {
                IURNNode src = (IURNNode) oldGraph.getNodes().get(i);
                IURNNode dest = (IURNNode) newGraph.getNodes().get(i);

                if (src instanceof IntentionalElementRef && dest instanceof IntentionalElementRef) {
                    IntentionalElementRef srcref = (IntentionalElementRef) src;
                    IntentionalElementRef destref = (IntentionalElementRef) dest;

                    destref.setDef(srcref.getDef());
                } else if (src instanceof KPIInformationElementRef && dest instanceof KPIInformationElementRef) {
                    KPIInformationElementRef srcref = (KPIInformationElementRef) src;
                    KPIInformationElementRef destref = (KPIInformationElementRef) dest;

                    destref.setDef(srcref.getDef());
                }
            }
            for (int i = 0; i < oldGraph.getConnections().size(); i++) {
                IURNConnection src = (IURNConnection) oldGraph.getConnections().get(i);
                IURNConnection dest = (IURNConnection) newGraph.getConnections().get(i);

                if (src instanceof LinkRef && dest instanceof LinkRef) {
                    LinkRef srcref = (LinkRef) src;
                    LinkRef destref = (LinkRef) dest;

                    destref.setLink(srcref.getLink());
                }

                if (src instanceof KPIModelLinkRef && dest instanceof KPIModelLinkRef) {
                    KPIModelLinkRef srcref = (KPIModelLinkRef) src;
                    KPIModelLinkRef destref = (KPIModelLinkRef) dest;

                    destref.setLink(srcref.getLink());
                }
            }

        }

        // clean ID problems
        URNNamingHelper.sanitizeURNspec(urn);

        testPostConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null;
        assert getNewDiagram() != null && getNewDiagram().getUrndefinition() != null;

        for (int i = 0; i < getNewDiagram().getContRefs().size(); i++) {
            IURNContainerRef destref = (IURNContainerRef) getNewDiagram().getContRefs().get(i);
            assert destref.getContDef() != null;
        }

        for (int i = 0; i < getNewDiagram().getNodes().size(); i++) {
            IURNNode dest = (IURNNode) getNewDiagram().getNodes().get(i);

            if (dest instanceof RespRef) {
                RespRef destref = (RespRef) dest;
                assert destref.getRespDef() != null;
            } else if (dest instanceof Stub) {
                Stub deststub = (Stub) dest;

                for (int j = 0; j < deststub.getBindings().size(); j++) {
                    PluginBinding destpb = (PluginBinding) deststub.getBindings().get(j);

                    assert destpb.getPlugin() != null;

                    for (int k = 0; k < destpb.getIn().size(); k++) {
                        InBinding destib = (InBinding) destpb.getIn().get(k);
                        assert destib.getStartPoint() != null;
                    }
                    for (int k = 0; k < destpb.getOut().size(); k++) {
                        OutBinding destob = (OutBinding) destpb.getOut().get(k);
                        assert destob.getEndPoint() != null;
                    }
                }

            } else if (dest instanceof IntentionalElementRef) {
                IntentionalElementRef destref = (IntentionalElementRef) dest;
                assert destref.getDef() != null;
            } else if (dest instanceof KPIInformationElementRef) {
                KPIInformationElementRef destref = (KPIInformationElementRef) dest;
                assert destref.getDef() != null;
            }
        }

        for (int i = 0; i < getNewDiagram().getConnections().size(); i++) {
            IURNConnection dest = (IURNConnection) getNewDiagram().getConnections().get(i);

            if (dest instanceof LinkRef) {
                LinkRef destref = (LinkRef) dest;
                assert destref.getLink() != null;
            } else if (dest instanceof KPIModelLinkRef) {
                KPIModelLinkRef destref = (KPIModelLinkRef) dest;
                assert destref.getLink() != null;
            }
        }

    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        assert urn != null;
        assert getNewDiagram() != null && getNewDiagram().getUrndefinition() == null;

        for (int i = 0; i < getNewDiagram().getContRefs().size(); i++) {
            IURNContainerRef destref = (IURNContainerRef) getNewDiagram().getContRefs().get(i);
            assert destref.getContDef() == null;
        }

        for (int i = 0; i < getNewDiagram().getNodes().size(); i++) {
            IURNNode dest = (IURNNode) getNewDiagram().getNodes().get(i);

            if (dest instanceof RespRef) {
                RespRef destref = (RespRef) dest;
                assert destref.getRespDef() == null;
            } else if (dest instanceof Stub) {
                Stub deststub = (Stub) dest;

                for (int j = 0; j < deststub.getBindings().size(); j++) {
                    PluginBinding destpb = (PluginBinding) deststub.getBindings().get(j);

                    assert destpb.getPlugin() == null;

                    for (int k = 0; k < destpb.getIn().size(); k++) {
                        InBinding destib = (InBinding) destpb.getIn().get(k);
                        assert destib.getStartPoint() == null;
                    }
                    for (int k = 0; k < destpb.getOut().size(); k++) {
                        OutBinding destob = (OutBinding) destpb.getOut().get(k);
                        assert destob.getEndPoint() == null;
                    }
                }

            } else if (dest instanceof IntentionalElementRef) {
                IntentionalElementRef destref = (IntentionalElementRef) dest;
                assert destref.getDef() == null;
            } else if (dest instanceof KPIInformationElementRef) {
                KPIInformationElementRef destref = (KPIInformationElementRef) dest;
                assert destref.getDef() == null;
            }
        }

        for (int i = 0; i < getNewDiagram().getConnections().size(); i++) {
            IURNConnection dest = (IURNConnection) getNewDiagram().getConnections().get(i);

            if (dest instanceof LinkRef) {
                LinkRef destref = (LinkRef) dest;
                assert destref.getLink() == null;
            } else if (dest instanceof KPIModelLinkRef) {
                KPIModelLinkRef destref = (KPIModelLinkRef) dest;
                assert destref.getLink() == null;
            }
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (urn == null)
            return;
        testPostConditions();

        if (oldDiagram != null) {
            newDiagram.setUrndefinition(null);
            for (int i = 0; i < newDiagram.getContRefs().size(); i++) {
                IURNContainerRef destref = (IURNContainerRef) newDiagram.getContRefs().get(i);
                destref.setContDef(null);
            }

            for (int i = 0; i < newDiagram.getNodes().size(); i++) {
                IURNNode dest = (IURNNode) newDiagram.getNodes().get(i);

                if (dest instanceof RespRef) {
                    RespRef destref = (RespRef) dest;

                    destref.setRespDef(null);
                } else if (dest instanceof Stub) {
                    Stub deststub = (Stub) dest;

                    for (int j = 0; j < deststub.getBindings().size(); j++) {
                        PluginBinding destpb = (PluginBinding) deststub.getBindings().get(j);

                        destpb.setPlugin(null);

                        for (int k = 0; k < destpb.getIn().size(); k++) {
                            InBinding destib = (InBinding) destpb.getIn().get(k);
                            destib.setStartPoint(null);
                        }
                        for (int k = 0; k < destpb.getOut().size(); k++) {
                            OutBinding destob = (OutBinding) destpb.getOut().get(k);
                            destob.setEndPoint(null);
                        }
                        for (int k = 0; k < destpb.getComponents().size(); k++) {
                            ComponentBinding destcb = (ComponentBinding) destpb.getComponents().get(k);
                            destcb.setParentComponent(null);
                            destcb.setPluginComponent(null);
                        }
                        for (int k = 0; k < destpb.getResponsibilities().size(); k++) {
                            ResponsibilityBinding destcb = (ResponsibilityBinding) destpb.getResponsibilities().get(k);
                            destcb.setParentResp(null);
                            destcb.setPluginResp(null);
                        }
                    }
                }
            }
        } else {
            newGraph.setUrndefinition(null);
            for (int i = 0; i < newGraph.getContRefs().size(); i++) {
                IURNContainerRef destref = (IURNContainerRef) newGraph.getContRefs().get(i);
                destref.setContDef(null);
            }

            for (int i = 0; i < newGraph.getNodes().size(); i++) {
                IURNNode dest = (IURNNode) newGraph.getNodes().get(i);

                if (dest instanceof IntentionalElementRef) {
                    IntentionalElementRef destref = (IntentionalElementRef) dest;
                    destref.setDef(null);
                } else if (dest instanceof KPIInformationElementRef) {
                    KPIInformationElementRef destref = (KPIInformationElementRef) dest;
                    destref.setDef(null);
                }
            }
            for (int i = 0; i < newGraph.getConnections().size(); i++) {
                IURNConnection dest = (IURNConnection) newGraph.getConnections().get(i);

                if (dest instanceof LinkRef) {
                    LinkRef destref = (LinkRef) dest;
                    destref.setLink(null);
                } else if (dest instanceof KPIModelLinkRef) {
                    KPIModelLinkRef destref = (KPIModelLinkRef) dest;
                    destref.setLink(null);
                }
            }
        }

        testPreConditions();
    }

    public IURNDiagram getAffectedDiagram() {
        return getNewDiagram();
    }
}