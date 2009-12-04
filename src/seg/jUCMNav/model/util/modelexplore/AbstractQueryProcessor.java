package seg.jUCMNav.model.util.modelexplore;

import seg.jUCMNav.Messages;

/**
 * Basic implementation for a query processor that verifies if it can run a certain QueryRequest and, if not, sends to chained QueryProcessors.
 * 
 * @author jpdaigle
 * 
 */
public abstract class AbstractQueryProcessor implements IQueryProcessorChain {
    protected IQueryProcessorChain _nextInChain;

    protected String[] _answerQueryTypes;

    /**
     * Adds a chain after this one. Must not be an existing successor chain.
     * 
     * @see seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain#addChain(seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain)
     */
    public void addChain(IQueryProcessorChain c) {
        if (_nextInChain == null)
            _nextInChain = c;
        else
            throw new IllegalArgumentException(Messages.getString("AbstractQueryProcessor.alreadyHaveNextInChain")); //$NON-NLS-1$
    }

    /**
     * If the request is in the _answerqueryTypes, process it using runImpl(QueryRequest). Otherwise, pass onto the chain.
     * 
     * @see seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain#sendToChain(seg.jUCMNav.model.util.modelexplore.QueryRequest)
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

    /**
     * Process the given request. Assumption: the q.getQueryType() is in _answerQueryTypes.
     * 
     * @param q
     *            the request
     * @return its response
     */
    public abstract QueryResponse runImpl(QueryRequest q);

    /**
     * @return the chained QueryProcessor
     * 
     * @see seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain#getChain()
     */
    public IQueryProcessorChain getChain() {
        return _nextInChain;
    }

}