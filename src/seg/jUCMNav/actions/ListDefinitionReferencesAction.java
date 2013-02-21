package seg.jUCMNav.actions;

import grl.ElementLink;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.views.outline.UrnOutlinePage;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import ucm.map.RespRef;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class ListDefinitionReferencesAction extends URNSelectionAction {

    public static final String LISTREFERENCES = "List Definition References";
    private EList references;

    private Menu fCreatedMenu;

    // the list of actions that are contained within this action
    private IAction[] actions;

    public ListDefinitionReferencesAction(IWorkbenchPart part) {
        super(part);
        setId(LISTREFERENCES);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/outline16.gif")); //$NON-NLS-1$

        // the secondary menu logic
        setMenuCreator(new IMenuCreator() {

            public Menu getMenu(Control parent) {
                return null;
            }

            public Menu getCreatedMenu() {
                return fCreatedMenu;
            }

            public void setCreatedMenu(Menu createdMenu) {
                fCreatedMenu = createdMenu;
            }

            public Menu getMenu(Menu parent) {
//                if (getCreatedMenu() != null) {
//                    getCreatedMenu().setEnabled(false);
//                    getCreatedMenu().dispose();
//                }

                // create dynamic submenu
                final Menu menu = new Menu(parent);

                menu.addMenuListener(new MenuListener() {

                    @Override
                    public void menuHidden(MenuEvent arg0) {
                        MenuItem[] items = menu.getItems();

                        // Don't dispose items here or they won't fire selection events.
                        for (int i = 0; i < items.length; i++) {
                            items[i].setData("toRemove", "true"); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                    }

                    @Override
                    public void menuShown(MenuEvent arg0) {

                        MenuItem[] items = menu.getItems();

                        // dispose unused items. This way we simulate a dynamic sub-menu.
                        for (int i = 0; i < items.length; i++) {
                            if (items[i].getData("toRemove").equals("true")) //$NON-NLS-1$ //$NON-NLS-2$
                                items[i].dispose();
                        }
                        

                        int i = 0;
                        for (Iterator it = references.iterator(); it.hasNext();) {
                            EObject obj = (EObject)it.next();

                            IURNDiagram diagram = getDiagram(obj);

                            // create the submenu item
                            MenuItem item = new MenuItem(menu, SWT.NONE);

                            // memorize the index
                            item.setData(new Integer(i));

                            // identify it
                         // Set the text depending on the outline preferences
                            if (DisplayPreferences.getInstance().getShowNodeNumber()) {
                                // This class return the ID between () at the element name
                                item.setText(EObjectClassNameComparator.getSortableElementName(diagram));
                            } else {
                                item.setText(URNNamingHelper.getName((URNmodelElement)diagram));
                            }

                            // inform us when something is selected.
                            item.addSelectionListener(new SelectionListener() {
                                @Override
                                public void widgetDefaultSelected(SelectionEvent arg0) {
                                }

                                @Override
                                public void widgetSelected(SelectionEvent e) {
                                    int index = ((Integer) (((MenuItem) (e.getSource())).getData())).intValue();
                                    EObject o = (EObject)references.get(index);
                                    IURNDiagram diagram = getDiagram(o);

                                    UrnOutlinePage outline = (UrnOutlinePage) getEditor().getAdapter(IContentOutlinePage.class);
                                    
                                    EditPart part = (EditPart) outline.getViewer().getEditPartRegistry().get(o);

                                    if (part != null)
                                        getEditor().selectInOutline(part);
                                    else {
                                        getEditor().selectInDiagram(o, diagram);
                                    }
                                }
                            });
                            i++;
                        }
                    }

                    protected IURNDiagram getDiagram(EObject obj) {
                        IURNDiagram diagram = null;
                        
                        if(obj instanceof IURNContainerRef)
                            diagram = ((IURNContainerRef)obj).getDiagram();
                        else if(obj instanceof RespRef)
                            diagram = ((RespRef)obj).getDiagram();
                        else if(obj instanceof IntentionalElementRef)
                            diagram = ((IntentionalElementRef)obj).getDiagram();
                        else if(obj instanceof LinkRef)
                            diagram = ((LinkRef)obj).getDiagram();
                        return diagram;
                    }

                });

                setCreatedMenu(menu);

                return menu;
            }

            public void dispose() {
                if (getCreatedMenu() != null) {
                    getCreatedMenu().setEnabled(false);
                    getCreatedMenu().dispose();
                }
            }
        });
    }

    /**
     * 
     */
    protected boolean calculateEnabled() {
        if(getSelectedObjects().size() > 0 && getSelectedObjects().get(0) instanceof EditPart) {
            EditPart ep = (EditPart) getSelectedObjects().get(0);
            
            if (ep.getModel() instanceof EObject) {
                EObject model = (EObject)ep.getModel();
            
                if (model instanceof IURNContainer) {
                    references = ((IURNContainer)model).getContRefs();
                }
                else if(model instanceof Responsibility) {
                    references = ((Responsibility)model).getRespRefs();
                }
                else if(model instanceof IntentionalElement)
                {
                    references = ((IntentionalElement)model).getRefs();
                }
                else if(model instanceof ElementLink)
                {
                    references = ((ElementLink)model).getRefs();
                }
                else if(model instanceof IURNContainerRef) {
                    references = ((IURNContainerRef)model).getContDef().getContRefs();
                }
                else if(model instanceof RespRef && ((RespRef)model).getRespDef() != null) {
                    references = ((RespRef)model).getRespDef().getRespRefs();
                }
                else if(model instanceof IntentionalElementRef) {
                    references = ((IntentionalElementRef)model).getDef().getRefs();
                }
                else if(model instanceof LinkRef) {
                    references = ((LinkRef)model).getLink().getRefs();
                }
                else
                    return false;
                
                return true;
            }
        }
        
        return false;
    }

    /**
     * @return
     */
    protected Command getCommand() {
        return null;
    }
}
