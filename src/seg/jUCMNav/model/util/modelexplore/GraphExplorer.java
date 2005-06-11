/*
 * Created on 31-May-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore;

import seg.jUCMNav.model.util.modelexplore.queries.EndPointFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.StartPointFinder;

/**
 * @author jpdaigle
 * 
 * Singleton used to send queries for exploring graphs: finding startpoints, endpoints, etc
 * 
 * Design intended to scale up to a large chain of objects implementing the IQueryProcessorChain interface
 *  
 */
public class GraphExplorer {
    private static GraphExplorer _instance;

    private static StartPointFinder _spFinder;

    private static EndPointFinder _epFinder;
    
    private static ReachableNodeFinder _rnFinder;

    private static AbstractQueryProcessor _chainHead;
    
    private GraphExplorer() {
        // instantiate
        _spFinder = new StartPointFinder();
        _epFinder = new EndPointFinder();
        _rnFinder = new ReachableNodeFinder();

        // setup query processing chain
        _chainHead = _spFinder;
        _spFinder.addChain(_epFinder);
        _epFinder.addChain(_rnFinder);
        _rnFinder.addChain(null);
    }

    public static GraphExplorer getInstance() {
        if (_instance == null)
            _instance = new GraphExplorer();

        return _instance;
    }
    
    public QueryResponse run(QueryRequest qr) {
        return _chainHead.sendToChain(qr);
    }
}
