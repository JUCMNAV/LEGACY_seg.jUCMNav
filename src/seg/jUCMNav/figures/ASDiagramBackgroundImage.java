package seg.jUCMNav.figures;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**Create the Class BackgroundImage which Extends FreeformLayer

Declare the variable for Image.

Generate getter and setter for the variable.

In The Constructer class Add the Following:**/

public class ASDiagramBackgroundImage  extends FreeformLayer
{
static Image img;
public static int toolx;
public static int tooly;


public void setImage(Image im)
{
	img=im;
}

public Image getImage()
{
	return (Image)img;
}

public ASDiagramBackgroundImage (String file) {
super();

try
{
	File fb= new File(file);
	//System.out.println(fb.exists());
	 img= new Image(PlatformUI.getWorkbench().getDisplay().getDefault(),new FileInputStream(fb));
	ImageFigure image= new ImageFigure(img);
}
catch (Exception e)
{
System.out.println(e);
setImage(null);
}

}

public  int getX()
{
	try{
		
	if(img==null)
	{
		/*File currentDirFile= new File(".");
		String helper=currentDirFile.getAbsolutePath();
		File fb= new File(helper+"\\src\\seg\\juCMNav\\icons\\ASDiagram.gif");*/
	//File fb= new File(System.getProperty("user.dir")+ "\\src\\seg\\juCMNav\\icons\\ASDiagram.gif");
	//File fb= new File(this.getClass().getClassLoader().getResource("\\icons\\ASDiagram.gif").getFile());
	//System.out.println(fb.exists());
		File fb= new File(System.getenv("bb")+"\\ASDiagram.gif");
	 img= new Image(PlatformUI.getWorkbench().getDisplay().getDefault(),new FileInputStream(fb));
	}
	Rectangle targetRect = getBounds().getCopy();
	org.eclipse.swt.graphics.Rectangle imgBox = getImage().getBounds();
	
	 toolx= (imgBox.width/2)+90;
	
	
	 
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return toolx;
}

public  int getY()
{
	try{
		
	if(img==null)
	{
	/*	File currentDirFile= new File(".");
		String helper=currentDirFile.getAbsolutePath();
		System.out.println(helper+"\\src\\seg\\juCMNav\\icons\\ASDiagram.gif");
		File fb= new File(helper+"\\src\\seg\\juCMNav\\icons\\ASDiagram.gif");*/
		//File fb= new File(System.getProperty("user.dir")+ "\\src\\seg\\juCMNav\\icons\\ASDiagram.gif");
		//File fb= new File(this.getClass().getClassLoader().getResource("\\icons\\ASDiagram.gif").getFile());
	//System.out.println(fb.exists());
		File fb= new File(System.getenv("bb")+"\\ASDiagram.gif");
	 img= new Image(PlatformUI.getWorkbench().getDisplay().getDefault(),new FileInputStream(fb));
	}
	Rectangle targetRect = getBounds().getCopy();
	org.eclipse.swt.graphics.Rectangle imgBox = getImage().getBounds();
	tooly=135;
	
	
	 
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return tooly;
}

//Override paintFigure( ) Method and Include the Following lines of Code

protected void paintFigure(Graphics graphics) {
if (getImage() != null)
{
Rectangle targetRect = getBounds().getCopy();
org.eclipse.swt.graphics.Rectangle imgBox = getImage().getBounds();

/*graphics.drawImage(getImage(), 0, 0, imgBox.width,imgBox.height,  targetRect.x, targetRect.y, targetRect.width, targetRect.height);
*/
graphics.drawImage(getImage(), 0, 0, imgBox.width,imgBox.height, 0, 0, imgBox.width,imgBox.height);

toolx=(imgBox.width/2)+100;
tooly=(imgBox.height/2)-230;
graphics.setBackgroundPattern(new Pattern(Display.getDefault(),getImage()));
}
super.paintFigure(graphics);
}
}