package seg.jUCMNav.views;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
/**
 * @author Etienne Tremblay
 *
 */
public class RespListItem extends Composite {
	
	private ListenerList mouseListeners = new ListenerList(3);

	private Label lblDesc = null;
	
	private Composite composite = null;
	private Label lblIcon = null;
	private Label lblName = null;
	
	private boolean selected = false;
	
	private Composite line = null;
	/**
	 * @param parent
	 * @param style
	 */
	public RespListItem(Composite parent, int style) {
		super(parent, style);
		if(parent instanceof RespListControl){
			RespListControl c = (RespListControl)parent;
			c.add(this);
		}
		initialize();
	}

	private void initialize() {
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.makeColumnsEqualWidth = false;
		this.setLayout(layout);
		createComposite();
		final GridData data = new GridData(SWT.FILL, SWT.FILL, true, false, 1,1);
		data.verticalSpan = 2;
		lblDesc = new Label(this, SWT.WRAP);
		createComposite1();
		lblDesc.setLayoutData(data);
		lblDesc.setText("");
		lblDesc.setFont(new org.eclipse.swt.graphics.Font(org.eclipse.swt.widgets.Display.getDefault(), "Tahoma", 8, org.eclipse.swt.SWT.ITALIC));
		lblDesc.setBackground(org.eclipse.swt.widgets.Display.getDefault().getSystemColor(org.eclipse.swt.SWT.COLOR_WHITE));
		this.setBackground(org.eclipse.swt.widgets.Display.getDefault().getSystemColor(org.eclipse.swt.SWT.COLOR_WHITE));
		layout.marginWidth = 3;
		layout.marginHeight = 3;
		layout.verticalSpacing = 0;
		layout.horizontalSpacing = 0;
		setSize(new org.eclipse.swt.graphics.Point(270,52));
		lblDesc.addMouseListener(new org.eclipse.swt.events.MouseAdapter() { 
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {    
				fireClicked(e);
			}
		});
	}
	
	public void addMouseListener(MouseListener listener) {
		mouseListeners.add(listener);
	}
	
	public void removeMouseListener(MouseListener listener) {
		mouseListeners.remove(listener);
	}
	
	public void select(){
		selected = true;
		line.setVisible(false);
		this.setBackground(new Color(null, 0, 0, 0));
		lblDesc.setBackground(new Color(null, 145, 185, 255));
		lblName.setBackground(new Color(null, 145, 185, 255));
		composite.setBackground(new Color(null, 145, 185, 255));
		lblIcon.setBackground(new Color(null, 145, 185, 255));
	}
	
	public void unselect(){
		selected = false;
		line.setVisible(true);
		this.setBackground(new Color(null, 255, 255, 255));
		lblDesc.setBackground(new Color(null, 255, 255, 255));
		lblName.setBackground(new Color(null, 255, 255, 255));
		composite.setBackground(new Color(null, 255, 255, 255));
		lblIcon.setBackground(new Color(null, 255, 255, 255));
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	protected void fireClicked(MouseEvent e){
		select();
		Object[] listeners = mouseListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			MouseListener listener = (MouseListener)listeners[i];
			e.widget = this;
			listener.mouseDown(e);
		}
	}
	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return lblDesc.getText();
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.lblDesc.setText(description);
	}
	/**
	 * @return Returns the name.
	 */
	public String getRespName() {
		return lblName.getText();
	}
	/**
	 * @param name The name to set.
	 */
	public void setRespName(String name) {
		this.lblName.setText(name);
	}
	
	/**
	 * This method initializes composite	
	 *
	 */    
	private void createComposite() {
		GridData gridData3 = new GridData();
		GridLayout gridLayout2 = new GridLayout();
		GridData gridData1 = new GridData();
		composite = new Composite(this, SWT.NONE);		   
		lblIcon = new Label(composite, SWT.NONE);
		lblName = new Label(composite, SWT.NONE);
		lblName.setBackground(org.eclipse.swt.widgets.Display.getDefault().getSystemColor(org.eclipse.swt.SWT.COLOR_WHITE));
		lblIcon.setText("");
		lblIcon.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/seg/jUCMNav/icons/Resp16.gif")));
		lblName.setText("");
		lblName.setLayoutData(gridData3);
		lblName.setFont(new org.eclipse.swt.graphics.Font(org.eclipse.swt.widgets.Display.getDefault(), "Tahoma", 8, org.eclipse.swt.SWT.BOLD));
		composite.setBackground(org.eclipse.swt.widgets.Display.getDefault().getSystemColor(org.eclipse.swt.SWT.COLOR_WHITE));
		composite.setLayoutData(gridData1);
		composite.setLayout(gridLayout2);
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.verticalSpan = 2;
		gridLayout2.numColumns = 2;
		gridLayout2.horizontalSpacing = 3;
		gridLayout2.marginHeight = 0;
		gridLayout2.marginWidth = 0;
		gridLayout2.verticalSpacing = 0;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData3.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		lblName.addMouseListener(new org.eclipse.swt.events.MouseAdapter() { 
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {    
				fireClicked(e);
			}
		});
		lblIcon.addMouseListener(new org.eclipse.swt.events.MouseAdapter() { 
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {    
				fireClicked(e);
			}
		});
	}
	/**
	 * This method initializes composite1	
	 *
	 */    
	private void createComposite1() {
		GridData gridData5 = new GridData();
		line = new Composite(this, SWT.NONE);		   
		line.setLayout(new FillLayout());
		line.setLayoutData(gridData5);
		line.setBackground(new org.eclipse.swt.graphics.Color(org.eclipse.swt.widgets.Display.getDefault(), 72, 141, 255));
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		gridData5.heightHint = 1;
		gridData5.widthHint = 80;
	}
 }  //  @jve:decl-index=0:visual-constraint="10,10"
