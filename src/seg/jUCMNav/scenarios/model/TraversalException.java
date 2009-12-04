package seg.jUCMNav.scenarios.model;

/**
 * Exception thrown in unique situations during the traversal mechanism.
 * 
 * They should either be handled. They can bubble up and cause an error dialog in the UI.
 * 
 * @author jkealey
 * 
 */
public class TraversalException extends Exception {

    private static final long serialVersionUID = 1L;

    public TraversalException(String msg) {
        super(msg);
    }

    public TraversalException(String msg, Exception cause) {
        super(msg, cause);
    }
}
