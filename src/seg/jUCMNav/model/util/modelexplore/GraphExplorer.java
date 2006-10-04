package seg.jUCMNav.model.util.modelexplore;

import seg.jUCMNav.model.util.modelexplore.queries.ConnectionSplineFinder;
import seg.jUCMNav.model.util.modelexplore.queries.DefaultScenarioTraversal;
import seg.jUCMNav.model.util.modelexplore.queries.DeletionPathFinder;
import seg.jUCMNav.model.util.modelexplore.queries.EndPointFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.StartPointFinder;

/**
 * 
 * Singleton used to send queries for exploring graphs: finding startpoints, endpoints, etc
 * 
 * Design intended to scale up to a large chain of objects implementing the IQueryProcessorChain interface
 * 
 * If implementing a new query, add it at the end of the chain.
 * 
 * @author jpdaigle
 *  
 */
public class GraphExplorer {
    private static GraphExplorer _instance;

    private static StartPointFinder _spFinder;

    private static EndPointFinder _epFinder;

    private static ReachableNodeFinder _rnFinder;

    private static AbstractQueryProcessor _chainHead;

    private static ConnectionSplineFinder _splineFinder;

    private static DeletionPathFinder _deletionPathFinder;
    
    private static DefaultScenarioTraversal _defaultScenarioTraversal;

    /**
     * Singleton pattern.
     *  
     */
    private GraphExplorer() {
        // instantiate
        _spFinder = new StartPointFinder();
        _epFinder = new EndPointFinder();
        _rnFinder = new ReachableNodeFinder();
        _splineFinder = new ConnectionSplineFinder();
        _deletionPathFinder = new DeletionPathFinder();
        _defaultScenarioTraversal = new DefaultScenarioTraversal();

        // setup query processing chain
        _chainHead = _spFinder;
        _spFinder.addChain(_epFinder);
        _epFinder.addChain(_rnFinder);
        _rnFinder.addChain(_splineFinder);
        _splineFinder.addChain(_deletionPathFinder);
        _deletionPathFinder.addChain(_defaultScenarioTraversal);
        _defaultScenarioTraversal.addChain(null);
    }

    /**
     * @return the sole instance of GraphExplorer
     */
    public static GraphExplorer getInstance() {
        if (_instance == null)
            _instance = new GraphExplorer();

        return _instance;
    }

    /**
     * Passes through the static chain of query processors and tries to execute the given request.
     * 
     * @param qr
     *            the query
     * @return its response
     */
    public QueryResponse run(QueryRequest qr) {
        return _chainHead.sendToChain(qr);
    }
}