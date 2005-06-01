/*
 * Created on 31-May-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore;

/**
 * @author jpdaigle
 *  
 */
public class EndPointFinder implements QueryProcessorChain {
    private QueryProcessorChain _nextInChain;

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.QueryProcessorChain#addChain(seg.jUCMNav.model.util.modelexplore.QueryProcessorChain)
     */
    public void addChain(QueryProcessorChain c) {
        // TODO Auto-generated method stub

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

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.QueryProcessorChain#getChain()
     */
    public QueryProcessorChain getChain() {
        // TODO Auto-generated method stub
        return null;
    }

}
