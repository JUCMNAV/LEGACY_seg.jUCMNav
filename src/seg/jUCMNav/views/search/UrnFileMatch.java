package seg.jUCMNav.views.search;

import org.eclipse.core.resources.IFile;
import org.eclipse.search.internal.ui.text.FileMatch;
import org.eclipse.search.internal.ui.text.LineElement;

import seg.jUCMNav.model.util.URNNamingHelper;
import urncore.URNmodelElement;

/***
 * This class is used to indicate that we have found an element with a search string inside of a jUCMNav file.
 * 
 * @author jkealey
 * 
 */
public class UrnFileMatch extends FileMatch {

    private static String getDescription(IFile file, URNmodelElement modelElement) {
        String type = URNNamingHelper.getPrefix(modelElement.getClass());
        if (type.equals("RespRef"))
            type = "Responsibility Reference";
        if (type.endsWith("Ref"))
            type = type.substring(0, type.length() - 3) + " Reference";
        if (type.endsWith("Def"))
            type = type.substring(0, type.length() - 3) + " Definition";
        return /* "(" + file.getName() + ") " + */"name=\"" + URNNamingHelper.getName(modelElement) + "\", type=\"" + type + "\"";
    }

    protected URNmodelElement modelElement;
    protected String search;

    public UrnFileMatch(IFile file, String search, URNmodelElement modelElement) {
        // super(file);
        super(file, Integer.parseInt(modelElement.getId()), 1000, new LineElement(file, Integer.parseInt(modelElement.getId()), 0, getDescription(file,
                modelElement)));
        this.modelElement = modelElement;
        this.search = search;
    }

    public Object getElement() {
        // LineElement is a particular match inside the File. If we don't do this, the viewers group the items per file.
        if (getLineElement() != null)
            return getLineElement();
        else
            return super.getElement();
    }

    public Object getFileElement() {
        return super.getElement();
    }

    public String getId() {
        if (modelElement != null)
            return modelElement.getId();
        return null;
    }
}
