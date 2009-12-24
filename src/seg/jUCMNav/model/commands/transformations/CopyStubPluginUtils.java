package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;

import seg.jUCMNav.model.commands.create.AddComponentBindingCommand;
import seg.jUCMNav.model.commands.create.AddInBindingCommand;
import seg.jUCMNav.model.commands.create.AddOutBindingCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.create.AddRespRefBindingCommand;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.ComponentBinding;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.ResponsibilityBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Condition;
import urncore.IURNDiagram;
import urncore.Responsibility;

public class CopyStubPluginUtils {
    public Vector copyStubPlugins(URNspec targetUrn, UCMmap targetMap, Stub newStub, Stub oldStub) {
        Vector commands = new Vector();
        for (int i = 0; i < oldStub.getBindings().size(); i++) {
            PluginBinding binding = (PluginBinding) oldStub.getBindings().get(i);
            UCMmap oldMap = binding.getPlugin();
            IURNDiagram diag = (IURNDiagram) URNElementFinder.findMap(targetUrn, oldMap.getId());
            UCMmap map = null;
            if (diag == null || !(diag instanceof UCMmap)) {
                diag = (IURNDiagram) URNElementFinder.findMapByName(targetUrn, oldMap.getName());
                if (diag instanceof UCMmap)
                    map = (UCMmap) diag;
            } else
                map = (UCMmap) diag;

            if (map != null && map != targetMap) // don't allow plugin to self.
            {
                Condition condition = (Condition) EcoreUtil.copy(binding.getPrecondition());
                AddPluginCommand addPluginCommand = new AddPluginCommand(newStub, map, condition);
                addPluginCommand.build(); // so we can access getPlugin.
                commands.add(addPluginCommand);
                
                // copy other fields. 
                addPluginCommand.getPlugin().setProbability(binding.getProbability());
                addPluginCommand.getPlugin().setReplicationFactor(binding.getReplicationFactor());
                addPluginCommand.getPlugin().setTransaction(binding.isTransaction());
                
                // copy component bindings
                for (Iterator iterator = binding.getComponents().iterator(); iterator.hasNext();) {
                    ComponentBinding compBinding = (ComponentBinding) iterator.next();
                    ComponentRef parentComp = compBinding.getParentComponent();
                    ComponentRef childComp = compBinding.getPluginComponent();

                    ComponentRef tgtParentComp = URNElementFinder.findComponentRefByName(targetMap, URNNamingHelper.getName(parentComp));
                    if (tgtParentComp != null) {
                        ComponentRef tgtChildComp = URNElementFinder.findComponentRefByName(map, URNNamingHelper.getName(childComp));
                        if (tgtChildComp != null) {
                            commands.add(new AddComponentBindingCommand(addPluginCommand.getPlugin(), tgtParentComp, tgtChildComp));
                        }
                    }
                }

             // copy responsibility bindings
                for (Iterator iterator = binding.getResponsibilities().iterator(); iterator.hasNext();) {
                    ResponsibilityBinding respBinding = (ResponsibilityBinding) iterator.next();
                    Responsibility parentResp = respBinding.getParentResp();
                    RespRef childResp = respBinding.getPluginResp();

                    Responsibility tgtParentResp = URNElementFinder.findResponsibilityByName(targetUrn, URNNamingHelper.getName(parentResp));
                    if (tgtParentResp != null) {
                        PathNode tgtChildResp = URNElementFinder.findPathNode(map, childResp.getId());
                        if (tgtChildResp != null && tgtChildResp instanceof RespRef
                                && URNNamingHelper.getName(tgtChildResp).equals(URNNamingHelper.getName(childResp))) {
                            commands.add(new AddRespRefBindingCommand(addPluginCommand.getPlugin(), tgtParentResp, (RespRef) tgtChildResp));
                        }
                    }
                }

                // copy in bindings
                for (int j = 0; j < binding.getIn().size(); j++) {
                    InBinding bind = (InBinding) binding.getIn().get(j);
                    int connectionIndex = oldStub.getPred().indexOf(bind.getStubEntry());
                    StartPoint child = bind.getStartPoint();
                    PathNode tgtChild = URNElementFinder.findPathNode(map, child.getId());
                    if (tgtChild != null && tgtChild instanceof StartPoint) {
                        commands.add(new AddInBindingCommand(addPluginCommand.getPlugin(), (StartPoint) tgtChild, connectionIndex));
                    }
                }

                // copy out bindings
                for (int j = 0; j < binding.getOut().size(); j++) {
                    OutBinding bind = (OutBinding) binding.getOut().get(j);
                    int connectionIndex = oldStub.getSucc().indexOf(bind.getStubExit());
                    EndPoint child = bind.getEndPoint();
                    PathNode tgtChild = URNElementFinder.findPathNode(map, child.getId());
                    if (tgtChild != null && tgtChild instanceof EndPoint) {
                        commands.add(new AddOutBindingCommand(addPluginCommand.getPlugin(), (EndPoint) tgtChild, connectionIndex));
                    }
                }
            }
        }
        
        return commands;
    }
}
