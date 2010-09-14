package seg.jUCMNav.aourn.matcher;

public class Mapping {
	
	private PointcutElement pointcutElement;
	private Joinpoint joinpoint;
	// the next two booleans indicate whether the mapping is the first/last in a series of mappings for the anything pointcut element 
	private boolean first;
	private boolean last;
	
	// TODO @@@ needs to know whether is an alternative or not
	public Mapping(PointcutElement pointcutElement, Joinpoint joinpoint) {
		this.pointcutElement = pointcutElement;
		this.joinpoint = joinpoint;
		this.first = false;
		this.last = false;
	}
	
	public Joinpoint getJoinpoint() {
		return joinpoint;
	}
	
	public PointcutElement getPointcutElement() {
		return pointcutElement;
	}
	
	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

}
