/*
 * Created on 31-May-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore.queries;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;

/**
 * @author jpdaigle
 *  
 */
public class EndPointFinder extends AbstractQueryProcessor implements QueryProcessorChain {

    public EndPointFinder() {
        this._answerQueryTypes = new String [] {QueryObject.FINDREACHABLEENDPOINTS};
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


}
