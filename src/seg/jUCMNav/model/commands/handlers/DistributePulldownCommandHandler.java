package seg.jUCMNav.model.commands.handlers;

import grl.ActorRef;
import grl.IntentionalElementRef;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.editparts.RespRefEditPart;
import seg.jUCMNav.editparts.StubEditPart;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urn.URNspec;
import urncore.URNmodelElement;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class DistributePulldownCommandHandler extends AbstractHandler implements IHandler {

    
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {	
		return null;
	}
	
	@Override
	public void setEnabled(Object evaluationContext) {	
	}
	
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
