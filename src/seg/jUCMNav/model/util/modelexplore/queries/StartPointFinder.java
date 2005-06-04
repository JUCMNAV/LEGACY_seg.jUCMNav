/*
 * Created on 31-May-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore.queries;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.PathNode;

/**
 * @author jpdaigle
 *  
 */
public class StartPointFinder extends AbstractQueryProcessor implements QueryProcessorChain {

    public StartPointFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDREACHABLESTARTPOINTS };
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.QueryProcessorChain#sendToChain(seg.jUCMNav.model.util.modelexplore.QueryObject)
     */
    public QueryResponse sendToChain(QueryObject q) {
        // TODO Auto-generated method stub
        return null;
    }

    public class QFindReachableStartPoints extends QueryRequest {
        // Finds reachable start points from a PathNode
        PathNode _StartPathNode;

        public QFindReachableStartPoints() {
            this._queryType = QueryObject.FINDREACHABLESTARTPOINTS;
        }
    }

}
