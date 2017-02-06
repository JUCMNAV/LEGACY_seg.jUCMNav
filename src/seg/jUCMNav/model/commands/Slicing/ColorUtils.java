package seg.jUCMNav.model.commands.Slicing;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import urncore.IURNDiagram;

public class ColorUtils {
  static SliceColorList currentList=null;
	public static boolean sliceON=false;
	static UCMNavMultiPageEditor editor;
	public ColorUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static void doColor()
	{
		
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		   editor=(UCMNavMultiPageEditor) page.getActiveEditor();
		   for(SliceColorList element:SlicingAlg.colorList)
		   {
			   if(editor !=null && editor.equals(element.editor))
			   {
				   currentList=element;
				   //color the green ones
				   if(!element.green.isEmpty())
					   for(PathNode node:element.green)
						   colorElement(node,ColorConstants.green);
					//color the red ones
				   if(!element.red.isEmpty())
					   for(PathNode node:element.red)
						   colorElement(node,ColorConstants.red);
				   //color the gray ones
				   if(!element.gray.isEmpty())
					   for(PathNode node:element.gray)
						   colorElement(node,ColorConstants.gray);
				   
					
					
					   
				   }
				  // break;
			   //color the criterion node connection
			   NodeConnection criterionNC=element.getNodeConn_criterion();
			   if(criterionNC!=null && editor!=null)
			   {
				   for(int i=0; i<editor.getPageCount();i++)
					{
				   //how to get the editpart of the model element
					UrnEditor pageEditor=(UrnEditor) editor.getEditor(i);
					//compare the diagrams to get the right editor
					  if(pageEditor.getModel().equals(criterionNC.getDiagram()))
					  {
						  EditPart  modelEditPart = (EditPart) pageEditor.getGraphicalViewer().getEditPartRegistry().get(criterionNC);
						  if(modelEditPart !=null)
						   {
							   ( (GraphicalEditPart) modelEditPart).getFigure().setForegroundColor(ColorConstants.green);
							   ( (GraphicalEditPart) modelEditPart).getFigure().setBackgroundColor(ColorConstants.green);
							   break;
						   }
					  }//endof if
					} 
			   }
		   
		   }
		   
		  
		   
	}

	public static void colorElement(PathNode element, Color color)
	{
		NodeConnection nc;
		EditPart modelEditPart;
		for(int i=0; i<editor.getPageCount();i++)
		{
			//how to get the editpart of the model element
			UrnEditor pageEditor=(UrnEditor) editor.getEditor(i);
			//compare the diagrams to get the right editor
			  if(pageEditor.getModel().equals(((PathNode) element).getDiagram()))
			  {
				   modelEditPart = (EditPart) pageEditor.getGraphicalViewer().getEditPartRegistry().get(element);
				   if(modelEditPart !=null)
				   {
					   ( (GraphicalEditPart) modelEditPart).getFigure().setForegroundColor(color);
					   ( (GraphicalEditPart) modelEditPart).getFigure().setBackgroundColor(color);
					   //now we color the links
					   EList connections=element.getPred();
				    	if(connections!=null)
				    	for(Object nodeConn:connections)
				    	{
				    		nc=(NodeConnection) nodeConn;
				    		 modelEditPart = (EditPart) pageEditor.getGraphicalViewer().getEditPartRegistry().get(nc);
				    		 if(modelEditPart!=null)
				    			 if(!color.equals(ColorConstants.black) && !currentList.blackLinks.contains(nc))
				    			 {
				    		( (GraphicalEditPart) modelEditPart).getFigure().setForegroundColor(ColorConstants.green);
				    		
				    			 }
				    			 else
				    				 ( (GraphicalEditPart) modelEditPart).getFigure().setForegroundColor(ColorConstants.black);	 
				    	}
				   }
				  break;
			  }
		}
	}
	
	public static void unColor(SliceColorList element)
	{
		editor=element.editor;
		
		   {
			   //color the green ones
			   if(!element.green.isEmpty())
				   for(PathNode node:element.green)
					   colorElement(node,ColorConstants.black);
				//color the red ones
			   if(!element.red.isEmpty())
				   for(PathNode node:element.red)
					   colorElement(node,ColorConstants.black);
			   //color the gray ones
			   if(!element.gray.isEmpty())
				   for(PathNode node:element.gray)
					   colorElement(node,ColorConstants.black);
			   
				//***********
			 //color the criterion node connection
			   NodeConnection criterionNC=element.getNodeConn_criterion();
			   if(criterionNC!=null)
			   {
				   for(int i=0; i<editor.getPageCount();i++)
					{
				   //how to get the editpart of the model element
					UrnEditor pageEditor=(UrnEditor) editor.getEditor(i);
					//compare the diagrams to get the right editor
					  if(pageEditor.getModel().equals(criterionNC.getDiagram()))
					  {
						  EditPart  modelEditPart = (EditPart) pageEditor.getGraphicalViewer().getEditPartRegistry().get(criterionNC);
						  if(modelEditPart !=null)
						   {
							   ( (GraphicalEditPart) modelEditPart).getFigure().setForegroundColor(ColorConstants.black);
							   ( (GraphicalEditPart) modelEditPart).getFigure().setBackgroundColor(ColorConstants.black);
							   break;
						   }
					  }//endof if
					} 
			   }
			//******	   
			   }
	}
	
	public static void colorConcurrencyLinks()
	{
		EditPart modelEditPart;
	   if(!SlicingAlg.criterionExcludedNC.isEmpty())
		{
		   NodeConnection nc=SlicingAlg.criterionExcludedNC.get(0);
		for(int i=0; i<editor.getPageCount();i++)
		{
			//how to get the editpart of the model element
			UrnEditor pageEditor=(UrnEditor) editor.getEditor(i);
			//compare the diagrams to get the right editor
			  if(pageEditor.getModel().equals(nc.getDiagram()))
			  {
				  for(NodeConnection nodeconnection:SlicingAlg.criterionExcludedNC)
				  {
					  modelEditPart = (EditPart) pageEditor.getGraphicalViewer().getEditPartRegistry().get(nodeconnection);  
				  }
				   
				  break;
			  }
		}
	}
	}
}
