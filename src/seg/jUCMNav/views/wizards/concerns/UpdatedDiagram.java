package seg.jUCMNav.views.wizards.concerns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import urncore.IURNDiagram;
import urncore.URNmodelElement;

/**
 * an UpdatedDiagram wraps a IURNDiagram in order to keeps track of the changes as needed for the {@link ConcernsManagerPage}; does not change anything in the
 * URN model (i.e. the original is not altered)
 * 
 * @author gunterm
 */
public class UpdatedDiagram {

    // holds the list of all diagrams
    private static List updatedDiagrams;
    // the original diagram (cannot be null)
    private final IURNDiagram original;
    // if the updated variable is the same as the original, then no change occurred for that attribute
    // (cannot use the same approach as for UpdatedConcern because the original can be null)
    private UpdatedConcern updatedConcern;

    /**
     * @param original
     *            diagram to be wrapped (the diagram must already exist)
     */
    public UpdatedDiagram(IURNDiagram original) {
        Assert.isNotNull(original, "Cannot wrap null into an UpdatedDiagram"); //$NON-NLS-1$
        this.original = original;
        this.updatedConcern = UpdatedConcern.findUpdatedConcern(original.getConcern());
        if (updatedDiagrams == null)
            updatedDiagrams = new ArrayList();
        updatedDiagrams.add(this);
    }

    /**
     * @return the original diagram
     */
    public IURNDiagram getOriginal() {
        return original;
    }

    /**
     * @return the UpdatedConcern assigned to this diagram (the concern may be the same as the original or it may have changed)
     */
    public UpdatedConcern getUpdatedConcern() {
        return updatedConcern;
    }

    /**
     * @return the original name of the diagram
     */
    public String getName() {
        return ((URNmodelElement) original).getName();
    }

    /**
     * @return the original Id of the diagram
     */
    public String getId() {
        return ((URNmodelElement) original).getId();
    }

    /**
     * assign the concern to the diagram and also keeps the UpdatedConcern synchronized with the change
     * 
     * @param updatedConcern
     *            to be assigned to this diagram
     * @return true if successful, false if the concern was not assigned to the diagram
     */
    public boolean assignUpdatedConcern(UpdatedConcern updatedConcern) {
        // only make a change if updatedConcern is not the same as the current updatedConcern
        if (updatedConcern != null && this.updatedConcern != updatedConcern) {
            // remember the old value
            UpdatedConcern oldConcern = this.updatedConcern;
            // assign concern
            this.updatedConcern = updatedConcern;
            // add also to the diagram list of the concern
            updatedConcern.addSpecDiagram(this);
            // if an old concern did exist, remove from the diagram list of the old concern
            if (oldConcern != null)
                oldConcern.removeSpecDiagram(this);
            return true;
        }
        return false;
    }

    /**
     * removes the concern from the diagram and optionally keeps the UpdatedConcern synchronized with the change
     * 
     * @param sync
     *            controls synchronization of UpdatedConcerns and UpdatedDiagrams; if true, synchronization occurs; if false, synchronization does not occur
     * @return true if successful, false if the concern was not removed from the diagram
     */
    public boolean removeUpdatedConcern(boolean sync) {
        if (updatedConcern != null) {
            // remove from diagram list of concern
            if (sync)
                updatedConcern.removeSpecDiagram(this);
            // remove concern
            updatedConcern = null;
            return true;
        }
        return false;
    }

    /**
     * @param diagrams
     *            list containing the diagram from which to remove the concern
     * @return true if at least one diagram was updated, false if the concern was not removed for all diagrams in the list
     */
    public static boolean removeUpdatedConcern(List diagrams) {
        boolean result = false;
        for (Iterator iter = diagrams.iterator(); iter.hasNext();) {
            Object element = (Object) iter.next();
            // remove the concern of each diagram
            if (element instanceof IURNDiagram) {
                UpdatedDiagram diagram = findUpdatedDiagram(((IURNDiagram) element));
                if (diagram != null)
                    // result is true if at least one concern was removed successfully
                    result = diagram.removeUpdatedConcern(true) || result;
            }
        }
        return result;
    }

    /**
     * @return list of all UpdatedDiagrams regardless of whether a change was made to the diagram or not
     */
    public static List getUpdatedDiagrams() {
        if (updatedDiagrams == null)
            updatedDiagrams = new ArrayList();
        return updatedDiagrams;
    }

    /**
     * @return list of all UpdatedDiagrams that have changed (ie. the original is different)
     */
    public static List getChangedDiagrams() {
        List changedDiagrams = new ArrayList();
        if (updatedDiagrams != null) {
            for (Iterator iter = updatedDiagrams.iterator(); iter.hasNext();) {
                UpdatedDiagram uDiagram = (UpdatedDiagram) iter.next();
                // only add UpdatedDiagrams that have changed
                if (uDiagram.hasChanged())
                    changedDiagrams.add(uDiagram);
            }
        }
        return changedDiagrams;

    }

    /**
     * @return true if UpdatedDiagram has changed, false otherwise
     */
    private boolean hasChanged() {
        // in order for an UpdatedDiagram to have changed the original has to exist and
        // the concern of the original must be different than the updated concern
        return (original != null && original.getConcern() != updatedConcern);
    }

    /**
     * @param diagram
     *            for which to find its wrapper object (UpdatedDiagram)
     * @return UpdatedDiagram associated with the given diagram or null if the given diagram was not found
     */
    public static UpdatedDiagram findUpdatedDiagram(IURNDiagram diagram) {
        if (updatedDiagrams != null) {
            for (Iterator iter = updatedDiagrams.iterator(); iter.hasNext();) {
                UpdatedDiagram uDiagram = (UpdatedDiagram) iter.next();
                if (uDiagram.getOriginal() == diagram)
                    return uDiagram;
            }
        }
        // diagram not found
        return null;
    }

    /**
     * disposes of all UpdatedDiagrams
     */
    public static void disposeDiagrams() {
        if (updatedDiagrams != null)
            updatedDiagrams.clear();
        updatedDiagrams = null;
    }

}
