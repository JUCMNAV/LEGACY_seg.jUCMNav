/**
 * 
 */
package seg.jUCMNav.views.urnlinks;

import grl.Actor;
import grl.IntentionalElement;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddUrnLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteURNlinkCommand;
import ucm.map.UCMmap;
import urn.URNlink;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

/**
 * @author Jean-François Roy
 *
 */
public class URNLinksDialog {

    //List of element type where links are available (to fill combo box)
    private static final String COMPONENT = Messages.getString("URNLinksDialog.component"); //$NON-NLS-1$
    private static final String RESPONSIBILITY = Messages.getString("URNLinksDialog.responsibility"); //$NON-NLS-1$
    private static final String MAP = Messages.getString("URNLinksDialog.map"); //$NON-NLS-1$
    
    private Shell sURNLinks = null;  //  @jve:decl-index=0:visual-constraint="10,10"
    private Group group = null;
    private CLabel lblName = null;
    private CLabel lblType = null;
    private CLabel lblDescription = null;
    private CLabel cLabelGrlName = null;
    private CLabel cLabelGrlDescription = null;
    private CLabel cLabelGrlType = null;
    private Group groupCurrentLink = null;
    private Group groupAddLink = null;
    private List listLinks = null;
    private Button buttonDeleteLink = null;
    private CLabel cLabelUcmType = null;
    private Combo comboUcmType = null;
    private CLabel cLabelUcmElement = null;
    private Combo comboUcmElement = null;
    private Button buttonAddLink = null;
    private Button buttonClose = null;
    
    // The commandstack from the editor where the dialog was opened.
    private CommandStack cmdStack;

    // The GRLmodelElement used to create the links 
    private URNmodelElement fromElement;
    //The urnspec of the current model
    private URNspec urn;
    
    //Vector used to keep current element in the UCMElement combo box
    private Vector ucmelements;
    private String currentElementType;
    private URNmodelElement toElement;
    
    private Vector urnlinks;
    
    private URNlink linktoremove;
    
    public URNLinksDialog(CommandStack cmdStack, URNmodelElement from) {
        this.cmdStack = cmdStack;
        init(from);
    }
    
    /**
     * This method initialize the Window
     *
     */
    private void init(URNmodelElement element){
        createSShell();
        sURNLinks.setVisible(true);
        setFromElement(element);
        refresh();
    }
    
    /**
     * This method initializes sShell
     */
    private void createSShell() {
        sURNLinks = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        sURNLinks.setText("URN Links"); //$NON-NLS-1$
        sURNLinks.setSize(new org.eclipse.swt.graphics.Point(434,349));
        createGroup();
        createGroupCurrentLink();
        createGroupAddLink();
        buttonClose = new Button(sURNLinks, SWT.NONE);
        buttonClose.setBounds(new org.eclipse.swt.graphics.Rectangle(302,275,121,40));
        buttonClose.setText(Messages.getString("URNLinksDialog.close")); //$NON-NLS-1$
        buttonClose.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                sURNLinks.close();
            }
        });
    }
    /**
     * This method initializes group	
     *
     */
    private void createGroup() {
        group = new Group(sURNLinks, SWT.NONE);
        group.setText(Messages.getString("URNLinksDialog.grlElement")); //$NON-NLS-1$
        group.setBounds(new org.eclipse.swt.graphics.Rectangle(3,2,419,75));
        lblName = new CLabel(group, SWT.NONE);
        lblName.setText(Messages.getString("URNLinksDialog.name")); //$NON-NLS-1$
        lblName.setFont(new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD)); //$NON-NLS-1$
        lblName.setBounds(new org.eclipse.swt.graphics.Rectangle(8,15,50,22));
        lblType = new CLabel(group, SWT.NONE);
        lblType.setText(Messages.getString("URNLinksDialog.type")); //$NON-NLS-1$
        lblType.setFont(new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD)); //$NON-NLS-1$
        lblType.setBounds(new org.eclipse.swt.graphics.Rectangle(263,14,37,19));
        lblDescription = new CLabel(group, SWT.NONE);
        lblDescription.setFont(new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD)); //$NON-NLS-1$
        lblDescription.setLocation(new org.eclipse.swt.graphics.Point(8,41));
        lblDescription.setText(Messages.getString("URNLinksDialog.description")); //$NON-NLS-1$
        lblDescription.setSize(new org.eclipse.swt.graphics.Point(80,22));
        cLabelGrlName = new CLabel(group, SWT.SHADOW_IN);
        cLabelGrlName.setText(""); //$NON-NLS-1$
        cLabelGrlName.setLocation(new org.eclipse.swt.graphics.Point(66,15));
        cLabelGrlName.setSize(new org.eclipse.swt.graphics.Point(189,23));
        cLabelGrlDescription = new CLabel(group, SWT.SHADOW_IN);
        cLabelGrlDescription.setText(""); //$NON-NLS-1$
        cLabelGrlDescription.setLocation(new org.eclipse.swt.graphics.Point(95,41));
        cLabelGrlDescription.setSize(new org.eclipse.swt.graphics.Point(315,23));
        cLabelGrlType = new CLabel(group, SWT.SHADOW_IN);
        cLabelGrlType.setText(""); //$NON-NLS-1$
        cLabelGrlType.setLocation(new org.eclipse.swt.graphics.Point(306,14));
        cLabelGrlType.setSize(new org.eclipse.swt.graphics.Point(103,23));
    }
    /**
     * This method initializes groupCurrentLink	
     *
     */
    private void createGroupCurrentLink() {
        groupCurrentLink = new Group(sURNLinks, SWT.NONE);
        groupCurrentLink.setText(Messages.getString("URNLinksDialog.urnLinks")); //$NON-NLS-1$
        groupCurrentLink.setBounds(new org.eclipse.swt.graphics.Rectangle(5,86,175,233));
        listLinks = new List(groupCurrentLink, SWT.V_SCROLL | SWT.H_SCROLL);
        listLinks.setBounds(new org.eclipse.swt.graphics.Rectangle(5,16,163,170));    
        listLinks.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
               if (listLinks.getSelectionIndex() >= 0){
                   setLinkToRemove(listLinks.getSelectionIndex());
               }
            }
        });
        buttonDeleteLink = new Button(groupCurrentLink, SWT.NONE);
        buttonDeleteLink.setBounds(new org.eclipse.swt.graphics.Rectangle(5,192,79,30));
        buttonDeleteLink.setEnabled(false);
        buttonDeleteLink.setText(Messages.getString("URNLinksDialog.deleteLink")); //$NON-NLS-1$
        buttonDeleteLink.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                deleteLink(); 
            }
        });
    }
    /**
     * This method initializes groupAddLink	
     *
     */
    private void createGroupAddLink() {
        groupAddLink = new Group(sURNLinks, SWT.NONE);
        groupAddLink.setText(Messages.getString("URNLinksDialog.addLinkToUcm")); //$NON-NLS-1$
        groupAddLink.setBounds(new org.eclipse.swt.graphics.Rectangle(183,85,241,173));
        cLabelUcmType = new CLabel(groupAddLink, SWT.NONE);
        cLabelUcmType.setText(Messages.getString("URNLinksDialog.elementType")); //$NON-NLS-1$
        cLabelUcmType.setBounds(new org.eclipse.swt.graphics.Rectangle(9,15,81,24));
        createComboUcmType();
        cLabelUcmElement = new CLabel(groupAddLink, SWT.NONE);
        cLabelUcmElement.setText(Messages.getString("URNLinksDialog.ucmElement")); //$NON-NLS-1$
        cLabelUcmElement.setLocation(new org.eclipse.swt.graphics.Point(9,66));
        cLabelUcmElement.setSize(new org.eclipse.swt.graphics.Point(75,19));
        createComboUcmElement();
        buttonAddLink = new Button(groupAddLink, SWT.NONE);
        buttonAddLink.setBounds(new org.eclipse.swt.graphics.Rectangle(10,127,88,33));
        buttonAddLink.setEnabled(false);
        buttonAddLink.setText(Messages.getString("URNLinksDialog.addLink")); //$NON-NLS-1$
        buttonAddLink.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                createLink();
            }
        });
    }
    /**
     * This method initializes comboUcmType	
     *
     */
    private void createComboUcmType() {
        comboUcmType = new Combo(groupAddLink, SWT.DROP_DOWN);
        comboUcmType.setBounds(new org.eclipse.swt.graphics.Rectangle(9,41,148,21));
        comboUcmType.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                comboUcmElement.removeAll();
                comboUcmElement.setEnabled(false);
                if (comboUcmType.getSelectionIndex() >= 0){
                    setUcmElementType(comboUcmType.getItem(comboUcmType.getSelectionIndex()));
                }
            }
        });
        comboUcmType.add(RESPONSIBILITY);
        comboUcmType.add(COMPONENT);
        comboUcmType.add(MAP);        
    }
    /**
     * This method initializes combo	
     *
     */
    private void createComboUcmElement() {
        comboUcmElement = new Combo(groupAddLink, SWT.NONE);
        comboUcmElement.setEnabled(false);
        comboUcmElement.setLocation(new org.eclipse.swt.graphics.Point(9,90));
        comboUcmElement.setSize(new org.eclipse.swt.graphics.Point(221,21));
        comboUcmElement.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
               buttonAddLink.setEnabled(false);
               toElement = null;
                if (comboUcmElement.getSelectionIndex() >= 0){
                    setUcmElement(comboUcmElement.getSelectionIndex());
                }            
            }
        });
    }

    /**
     * This method used the command stack to create a link between the grl element and 
     * the selected ucm element
     */
    private void createLink(){
        if (toElement != null){
            AddUrnLinkCommand cmd = new AddUrnLinkCommand(urn, 
                    (URNlink)ModelCreationFactory.getNewObject(urn, URNlink.class),
                    fromElement, toElement);
            if (cmd.canExecute()){
                execute(cmd);
            }
        }
    }
    
    private void deleteLink(){
        if (linktoremove != null){
            DeleteURNlinkCommand cmd = new DeleteURNlinkCommand(linktoremove);
            if (cmd.canExecute()){
                execute(cmd);
            }
            refresh();
        }
    }
    /**
     * Set the URNmodelElement associate with the instance
     * @param element
     */
    private void setFromElement(URNmodelElement element){
        this.fromElement = element;
        cLabelGrlName.setText(fromElement.getName());
        cLabelGrlDescription.setText(fromElement.getDescription());
        if (fromElement instanceof IntentionalElement){
            cLabelGrlType.setText(((IntentionalElement)fromElement).getType().getName());
            urn = ((IntentionalElement)fromElement).getGrlspec().getUrnspec();
        } else if (fromElement instanceof Actor){
            cLabelGrlType.setText(Messages.getString("URNLinksDialog.actor")); //$NON-NLS-1$
            urn = ((Actor)fromElement).getGrlspec().getUrnspec();
        } 
    }
    
    /**
     * This method set the elements in the UCM Element combobox
     * @param type String corresponding to the selected element type
     */
    private void setUcmElementType(String type){
        ucmelements = new Vector();
        currentElementType = type;
        if (type.equals(COMPONENT)){
            for (Iterator it = urn.getUrndef().getComponents().iterator(); it.hasNext();){
                ComponentElement comp = (ComponentElement)it.next();
                if (comp.getToLinks().size() == 0){
                    comboUcmElement.add(comp.getName() + " (" + comp.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
                    ucmelements.add(comp);
                }
            }
            
        } else if (type.equals(RESPONSIBILITY)){
            for (Iterator it = urn.getUrndef().getResponsibilities().iterator(); it.hasNext();){
                Responsibility resp = (Responsibility)it.next();
                if (resp.getToLinks().size() == 0){
                    comboUcmElement.add(resp.getName() + " (" + resp.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
                    ucmelements.add(resp);
                }
            }            
        } else if (type.equals(MAP)){
            for (Iterator it = urn.getUrndef().getSpecDiagrams().iterator(); it.hasNext();){
                IURNDiagram map = (IURNDiagram)it.next();
                if (map instanceof UCMmap && (((UCMmap)map).getToLinks().size() == 0)){
                    comboUcmElement.add(((UCMmap)map).getName()  + " (" + ((UCMmap)map).getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
                    ucmelements.add(map);
                }
            }                 
        }
        comboUcmElement.setEnabled(true);
    }
    
    /**
     * Set the element with who create a link
     * @param index of the element to add a link
     */
    private void setUcmElement(int index){
        toElement = (UCMmodelElement)ucmelements.get(index);
        buttonAddLink.setEnabled(true);
    }
    
    /**
     * Take a command and execute it in the command stack of the editor.
     * 
     * @param command
     *            The command we want to execute.
     */
    protected void execute(Command command) {
        if (command == null || !command.canExecute())
            return;
        getCommandStack().execute(command); // Execute the command
        refresh();
    }

    protected void undo() {
        getCommandStack().undo(); // Undo the last command
        refresh();
    }

    protected void redo() {
        getCommandStack().redo(); // Redo the last command
        refresh();
    }

    /**
     * @return The command stack of the editor.
     */
    private CommandStack getCommandStack() {
        return cmdStack;
    }
    
    /**
     * Refresh all the component of the window
     *
     */
    private void refresh(){
        comboUcmElement.setText(""); //$NON-NLS-1$
        comboUcmType.setText(""); //$NON-NLS-1$
        refreshListLink();
    }
    
    /**
     * Refresh the linksList
     *
     */
    private void refreshListLink(){
        //Clean the list
        listLinks.removeAll();
        urnlinks = new Vector();
        //Set the URNLink associate with the grl element
        for (Iterator it = fromElement.getFromLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            listLinks.add(link.getToElem().getName() + 
                    " (" + link.getToElem().getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
            urnlinks.add(link);
        }
        buttonDeleteLink.setEnabled(false);
    }
    
    /**
     * Set the URNlink to delete
     * @param index of the link to remove
     */
    private void setLinkToRemove(int index){
        if (index >= 0){
            linktoremove = (URNlink)urnlinks.get(index);
            buttonDeleteLink.setEnabled(true);
        }
    }
}
