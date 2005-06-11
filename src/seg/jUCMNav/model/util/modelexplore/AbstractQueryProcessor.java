/*
 * Created on 1-Jun-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore;

import seg.jUCMNav.Messages;

/**
 * @author jpdaigle
 *  
 */
public abstract class AbstractQueryProcessor implements IQueryProcessorChain {
    protected IQueryProcessorChain _nextInChain;

    protected String[] _answerQueryTypes;

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain#addChain(seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain)
     */
    public void addChain(IQueryProcessorChain c) {
        if (_nextInChain == null)
            _nextInChain = c;
        else
            throw new IllegalArgumentException(Messages.getString("AbstractQueryProcessor.alreadyHaveNextInChain")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain#sendToChain(seg.jUCMNav.model.util.modelexplore.QueryObject)
     */
    public QueryResponse sendToChain(QueryRequest q) {
        // See if we handle queries like q
        for (int i = 0; i < _answerQueryTypes.length; i++) {
            if ((_answerQueryTypes[i] != null) && (_answerQueryTypes[i].equals(q.getQueryType())))
                return runImpl(q);
        }

        // If we make it here we don't answer this query type!
        if (_nextInChain != null) {
            return _nextInChain.sendToChain(q);
        } else {
            // We are the last element of the chain and can't process this
            return null;
        }
    }

    public abstract QueryResponse runImpl(QueryRequest q);

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain#getChain()
     */
    public IQueryProcessorChain getChain() {
        return _nextInChain;
    }

}
