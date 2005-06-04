/*
 * Created on 31-May-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore;

import seg.jUCMNav.model.util.modelexplore.queries.EndPointFinder;
import seg.jUCMNav.model.util.modelexplore.queries.StartPointFinder;

/**
 * @author jpdaigle
 * 
 * Singleton used to send queries for exploring graphs: finding startpoints, endpoints, etc
 * 
 * Design intended to scale up to a large chain of objects implementing the QueryProcessorChain interface
 *  
 */
public class GraphExplorer {
    private static GraphExplorer _instance;

    private static StartPointFinder _spFinder;

    private static EndPointFinder _epFinder;

    private GraphExplorer() {
        // instantiate
        _spFinder = new StartPointFinder();
        _epFinder = new EndPointFinder();

        // setup query processing chain
        _spFinder.addChain(_epFinder);
        _epFinder.addChain(null);
    }

    public static GraphExplorer getInstance() {
        if (_instance == null)
            _instance = new GraphExplorer();

        return _instance;
    }
}
