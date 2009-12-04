package seg.jUCMNav.model.util.modelexplore;

/**
 * Interface used by QueryProcessorChains.
 * 
 * @author jpdaigle
 * 
 */
public interface IQueryProcessorChain {

    /**
     * Attach another chain to this one.
     * 
     * @param c
     *            the chain to attach.
     */
    public void addChain(IQueryProcessorChain c);

    /**
     * Submit a request to be processed and await a QueryResponse.
     * 
     * @param q
     *            the QueryRequest to be processed
     * @return the request's response.
     */
    public QueryResponse sendToChain(QueryRequest q);

    /**
     * 
     * @return the chain
     */
    public IQueryProcessorChain getChain();

}