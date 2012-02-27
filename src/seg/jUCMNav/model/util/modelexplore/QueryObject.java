package seg.jUCMNav.model.util.modelexplore;

/**
 * BaseClass for QueryRequest and QueryResponse. Contains a static list of query identifiers.
 * 
 * JPDaigle: This structure was sort of an experiment - a toy to try out an idea. In the end, because chain users need to refer to the actual query
 * implementations to get Request / Response object types, the whole idea of a chain is sort of lost.
 * 
 * Perhaps this will be improved upon...
 * 
 * 
 * @author jpdaigle
 * 
 */
public abstract class QueryObject {
    // Fields
    public static final String FINDREACHABLEENDPOINTS = "FINDREACHABLEENDPOINTS"; //$NON-NLS-1$

    public static final String FINDRESPONSIBILITIES = "FINDRESPONSIBILITIES"; //$NON-NLS-1$

    public static final String FINDREACHABLESTARTPOINTS = "FINDREACHABLESTARTPOINTS"; //$NON-NLS-1$

    public static final String FINDREACHABLENODES = "FINDREACHABLENODES"; //$NON-NLS-1$

    public static final String FINDSPLINE = "FINDSPLINE"; //$NON-NLS-1$

    public static final String FINDDELETIONPATH = "FINDDELETIONPATH"; //$NON-NLS-1$

    public static final String DEFAULTSCENARIOTRAVERSAL = "DEFAULTSCENARIOTRAVERSAL"; //$NON-NLS-1$
    
    public static final String FINDREACHABLEGRLNODES = "FINDREACHABLEGRLNODES"; //$NON-NLS-1$

    // one of the above public static final strings.
    public String _queryType;

    /**
     * @return the query type. one of the public static final strings
     */
    public String getQueryType() {
        return _queryType;
    }
}