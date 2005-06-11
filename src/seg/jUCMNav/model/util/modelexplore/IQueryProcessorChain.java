/*
 * Created on 31-May-2005
 */
package seg.jUCMNav.model.util.modelexplore;

/**
 * @author jpdaigle
 *  
 */
public interface IQueryProcessorChain {
    public void addChain(IQueryProcessorChain c);

    public QueryResponse sendToChain(QueryRequest q);

    public IQueryProcessorChain getChain();

}
