package seg.jUCMNav.model.commands.Slicing;

import java.util.ArrayList;

import ucm.map.NodeConnection;
import ucm.map.RespRef;

public class UnrelatedRespTree {

	public ArrayList<RespRef> unrelatedResp=new ArrayList<RespRef>();
	public ArrayList<UnrelatedRespTree> ChildrednPaths=new ArrayList<UnrelatedRespTree>();
	public ArrayList<NodeConnection> childrenConcurrencyPaths=new ArrayList<NodeConnection>();
	public UnrelatedRespTree() {
		
		unrelatedResp.clear();
		ChildrednPaths.clear();
		childrenConcurrencyPaths.clear();
		
	}

}
