/*
 * Created on 31-May-2005
 */
package seg.jUCMNav.model.util.modelexplore;

/**
 * @author jpdaigle
 *  
 */
public interface QueryProcessorChain {
    public void addChain(QueryProcessorChain c);

    public QueryResponse sendToChain(QueryObject q);

    public QueryProcessorChain getChain();

}
