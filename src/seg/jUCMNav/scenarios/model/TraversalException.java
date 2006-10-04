package seg.jUCMNav.scenarios.model;

public class TraversalException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TraversalException(String msg) {
		super(msg);
	}

	public TraversalException(String msg, Exception cause) 
	{
		super(msg, cause);
	}
}
