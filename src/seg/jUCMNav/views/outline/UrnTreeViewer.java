package seg.jUCMNav.views.outline;

import org.eclipse.gef.ui.parts.TreeViewer;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

public class UrnTreeViewer extends TreeViewer
{
	protected UCMNavMultiPageEditor editor;
	public UrnTreeViewer(UCMNavMultiPageEditor editor)
	{
		this.editor=editor;
	}
	public UCMNavMultiPageEditor getEditor()
	{
		return editor;
	}
	public void setEditor(UCMNavMultiPageEditor editor)
	{
		this.editor = editor;
	}

}
