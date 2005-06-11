/*
 * Created on 31-May-2005
 *
 * JPDaigle: This structure was sort of an experiment - a toy to try out an idea.
 * In the end, because chain users need to refer to the actual query implementations to
 * get Request / Response object types, the whole idea of a chain is sort of lost.
 * 
 * Perhaps this will be improved upon... 
 * 
 */
package seg.jUCMNav.model.util.modelexplore;

/**
 * @author jpdaigle
 *  
 */
public abstract class QueryObject {
    // Fields
    public static final String FINDREACHABLEENDPOINTS = "FINDREACHABLEENDPOINTS"; //$NON-NLS-1$

    public static final String FINDREACHABLESTARTPOINTS = "FINDREACHABLESTARTPOINTS"; //$NON-NLS-1$

    public static final String FINDREACHABLENODES = "FINDREACHABLENODES"; //$NON-NLS-1$

    public String _queryType;

    public String getQueryType() {
        return _queryType;
    }
}
