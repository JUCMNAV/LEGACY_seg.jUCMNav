package seg.jUCMNav.aourn.composer;

import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathNode;

public class InoutInfo {
	
	private NodeConnection inoutPath;
	private InBinding inBinding;
	private OutBinding outBinding;
	// indicates whether this in/out-path belongs to a replacement pointcut stub
	private boolean replacement;

	public InoutInfo(NodeConnection inout, InBinding inBinding, OutBinding outBinding, boolean replacement) {
		this.inoutPath = inout;
		this.inBinding = inBinding;
		this.outBinding = outBinding;
		assert (inBinding != null && outBinding == null) || (inBinding == null && outBinding != null) : "inBinding and outBinding cannot be both set"; //$NON-NLS-1$
		this.replacement = replacement;
	}
	
	// this InoutInfo may be either for an in-path of the pointcut stub or for an out-path of the pointcut stub (inoutPath)
	// the in/out-path are differentiated by the existence/non-existence of inBinding and outBinding to the pointcut map
	// if an inBinding exists, then inoutPath must be an in-path of the stub; if an outBinding exists, then it's an out-path
	public NodeConnection getInPath() {
		if (inBinding != null)
			return inoutPath;
		else
			return null;
	}
	
	// see getInPath
	public NodeConnection getOutPath() {
		if (outBinding != null)
			return inoutPath;
		else
			return null;
	}
	
	public PathNode getPointcutElement() {
		if (inBinding != null) {
			return inBinding.getStartPoint();
		} else {
			return outBinding.getEndPoint();
		}
	}

	// returns true for forward and false for backward;
	// forward scan is required for an out-path of the pointcut stub - backward scan for in-path
	public boolean getScanDirection() {
		if (getInPath() != null)
			return false;
		else
			return true;
	}
	
	public NodeConnection getInoutPath() {
		return inoutPath;
	}

	public boolean isReplacement() {
		return replacement;
	}

}
