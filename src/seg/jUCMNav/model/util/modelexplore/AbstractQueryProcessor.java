/*
 * Created on 1-Jun-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore;

/**
 * @author jpdaigle
 *  
 */
public abstract class AbstractQueryProcessor implements QueryProcessorChain {
    protected QueryProcessorChain _nextInChain;

    protected String [] _answerQueryTypes;

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.QueryProcessorChain#addChain(seg.jUCMNav.model.util.modelexplore.QueryProcessorChain)
     */
    public void addChain(QueryProcessorChain c) {
        if (_nextInChain == null)
            _nextInChain = c;
        else
            throw new IllegalArgumentException("Already have a next in chain");
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.QueryProcessorChain#sendToChain(seg.jUCMNav.model.util.modelexplore.QueryObject)
     */
    public abstract QueryResponse sendToChain(QueryObject q);

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.QueryProcessorChain#getChain()
     */
    public QueryProcessorChain getChain() {
        return _nextInChain;
    }
    
    
}
