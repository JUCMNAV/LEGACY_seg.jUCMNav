package seg.jUCMNav.views.wizards.concerns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import urncore.Concern;
import urncore.IURNDiagram;

/**
 * an UpdatedConcern wraps a Concern in order to keeps track of the changes as needed for the {@link ConcernsManagerPage}; does not change anything in the URN
 * model (i.e. the original is not altered)
 * 
 * @author gunterm
 */
public class UpdatedConcern {

    // holds all existing and newly added concerns
    private static List updatedConcerns;
    // holds all existing concerns that were deleted
    private static List deletedConcerns;

    // original is null if it's a newly added concern
    private final Concern original;
    // if an updated variable is null, then no update occurred for that attribute; all methods have
    // to ensure that original cannot be null if any of the updated variables is null
    private String updatedName;
    private String updatedDescription;
    private List updatedSpecDiagrams; // contains IURNDiagrams (not UpdatedDiagrams)

    /**
     * this is a new concern (original does not exist)
     */
    public UpdatedConcern() {
        this.original = null;
        this.updatedName = "Concern"; //$NON-NLS-1$
        this.updatedDescription = ""; //$NON-NLS-1$
        this.updatedSpecDiagrams = new ArrayList();
        
        // for some reason, when the Add button is pressed first thing after the Concern Dialogue is displayed, the SelectionEvent is fired twice and therefore
        // two new UpdatedConcerns are added. hence, before adding this new UpdatedConcern to the list of updatedConcerns, check whether another new 
        // UpdatedConcern exists (that has not been changed at all). if one exists, delete it and add this new one to the list (keeping the old one and not adding the
        // new one does not work, because the old one would not be selected in the combo box. therefore, the new one is kept and will be selected in the combo box.
    	Iterator it = getNewConcerns().iterator();
    	while (it.hasNext()) {
    		UpdatedConcern uc = (UpdatedConcern) it.next();
    		if (uc.getDescription().equals("") && uc.getName().equals("Concern") && uc.getSpecDiagrams().size()==0) {
    			uc.delete();
    			break;
    		}
    	}
        addToUpdatedConcerns();    		
    }

    /**
     * @param original
     *            concern to be wrapped (the concern must already exist)
     */
    public UpdatedConcern(Concern original) {
        Assert.isNotNull(original, "Cannot wrap null into an UpdatedConcern"); //$NON-NLS-1$
        this.original = original;
        this.updatedName = null;
        this.updatedDescription = null;
        this.updatedSpecDiagrams = null;
        addToUpdatedConcerns();
    }

    /**
     * adds this UpdatedConcern to the list of existing/new concerns
     */
    private void addToUpdatedConcerns() {
        if (updatedConcerns == null)
            updatedConcerns = new ArrayList();
        updatedConcerns.add(this);
    }

    /**
     * @return the original concern (ie. the existing concern or null if it's a new concern)
     */
    public Concern getOriginal() {
        return original;
    }

    /**
     * @return the updated list of the concern's SpecDiagrams if there has been a change, otherwise a copy of the original list
     */
    public List getSpecDiagrams() {
        if (updatedSpecDiagrams != null)
            return updatedSpecDiagrams;
        else {
            // only return a copy since the list may be sorted by the caller and we don't
            // want to make any changes to the original
            List list = new ArrayList();
            for (Iterator iter = original.getSpecDiagrams().iterator(); iter.hasNext();) {
                list.add(iter.next());
            }
            return list;
        }
    }

    /**
     * @return the updated description of the concern if there has been a change, otherwise the original description
     */
    public String getDescription() {
        if (updatedDescription != null)
            return updatedDescription;
        else
            return original.getDescription();
    }

    /**
     * @return the updated name of the concern if there has been a change, otherwise the original name
     */
    public String getName() {
        if (updatedName != null)
            return updatedName;
        else
            return original.getName();
    }

    /**
     * @param description
     *            with which to update the concern
     */
    public void setDescription(String description) {
        if (description != null || original != null)
            updatedDescription = description;
        // if the new value is the same as the original value, reset to null
        if (original != null && updatedDescription != null && updatedDescription.equals(original.getDescription()))
            updatedDescription = null;
    }

    /**
     * @param name
     *            with which to update the concern
     */
    public void setName(String name) {
        if (name != null || original != null)
            updatedName = name;
        // if the new value is the same as the original value, reset to null
        if (original != null && updatedName != null && updatedName.equals(original.getName()))
            updatedName = null;
    }

    /**
     * @return the original Id of the concern or "new" in case of a new concern
     */
    public String getId() {
        if (original != null)
            return original.getId();
        else
            return "new"; //$NON-NLS-1$
    }

    /**
     * deletes the concern
     * 
     * @return true if delete was successful, false if it was not successful
     */
    public boolean delete() {
        if (updatedConcerns != null && updatedConcerns.contains(this)) {
            // delete
            updatedConcerns.remove(this);
            if (deletedConcerns == null)
                deletedConcerns = new ArrayList();
            // only add to list of deleted concerns if the concern existed before the wizard was started
            // (i.e. if this is a concern that was just added, then we must not remember it as we are only
            // interested in the difference to before the wizard was started)
            if (!deletedConcerns.contains(this) && this.original != null)
                deletedConcerns.add(this);
            // also remove concern from the diagrams
            for (Iterator iter = getSpecDiagrams().iterator(); iter.hasNext();) {
                UpdatedDiagram uDiagram = UpdatedDiagram.findUpdatedDiagram((IURNDiagram) iter.next());
                uDiagram.removeUpdatedConcern(false);
            }
            return true;
        }
        return false;
    }

    /**
     * this should only be called from UpdatedDiagram once a concern was assigned to the diagram (keeps the MODEL synchronized)
     * 
     * @param updatedDiagram
     *            to which this concern is to be assigned
     * @return true if successful, false if the concern was not assigned
     */
    public boolean addSpecDiagram(UpdatedDiagram updatedDiagram) {
        boolean result = false;
        // ensure that updatedSpecDiagrams list is initialized
        checkInitWithOriginalSpecDiagrams();
        // only add updatedDiagram if it's not already on the list
        if (updatedDiagram != null && !updatedSpecDiagrams.contains(updatedDiagram.getOriginal())) {
            updatedSpecDiagrams.add(updatedDiagram.getOriginal());
            result = true;
        }
        // check if the updatedSpecDiagrams are the same as the original specDiagrams
        checkResetSpecDiagrams();
        return result;
    }

    /**
     * this should only be called from UpdatedDiagram once a concern was removed from the diagram (keeps the MODEL synchronized)
     * 
     * @param updatedDiagram
     *            from which the concern is to be removed
     * @return true if successful, false if the concern was not removed
     */
    public boolean removeSpecDiagram(UpdatedDiagram updatedDiagram) {
        boolean result = false;
        // ensure that updatedSpecDiagrams list is initialized
        checkInitWithOriginalSpecDiagrams();
        // only remove updatedDiagram if it is on the list
        if (updatedDiagram != null && updatedSpecDiagrams != null && updatedSpecDiagrams.contains(updatedDiagram.getOriginal())) {
            result = updatedSpecDiagrams.remove(updatedDiagram.getOriginal());
        }
        // check if the updatedSpecDiagrams are the same as the original specDiagrams
        checkResetSpecDiagrams();
        return result;
    }

    /**
     * checks if the updatedSpecDiagrams list has already been initialized; if not, initializes the updatedSpecDiagrams list with the list from the original
     */
    private void checkInitWithOriginalSpecDiagrams() {
        // if there has not been a change to the list yet, copy the original list so that the list can be changed
        if (updatedSpecDiagrams == null) {
            updatedSpecDiagrams = new ArrayList();
            for (Iterator iter = original.getSpecDiagrams().iterator(); iter.hasNext();) {
                updatedSpecDiagrams.add(iter.next());
            }
        }
    }

    /**
     * checks if the updatedSpecDiagrams list is exactly the same as the original list (ordering does not matter) if it is, resets updatedSpecDiagrams to null
     * (which indicates that the original list did not change)
     */
    private void checkResetSpecDiagrams() {
        // the new and old lists can only be the same if they have the same size
        if (original != null && updatedSpecDiagrams != null && updatedSpecDiagrams.size() == original.getSpecDiagrams().size()) {
            // the new and old lists can only be the same if each item in the new list matches an item in the old list
            int matched = 0;
            for (Iterator iter = updatedSpecDiagrams.iterator(); iter.hasNext();) {
                IURNDiagram uDiagram = (IURNDiagram) iter.next();
                if (original.getSpecDiagrams().contains(uDiagram))
                    matched++;
            }
            if (updatedSpecDiagrams.size() == matched)
                updatedSpecDiagrams = null;
        }
    }

    /**
     * @return list of all UpdatedConcerns (existing and new concerns but not deleted concerns) regardless of whether a change was made to a concern or not
     */
    public static List getUpdatedConcerns() {
        if (updatedConcerns == null)
            updatedConcerns = new ArrayList();
        return updatedConcerns;
    }

    /**
     * @return list of UpdatedConcerns (new concerns only, no existing and deleted concerns)
     */
    public static List getNewConcerns() {
        ArrayList newConcerns = new ArrayList();
        if (updatedConcerns != null) {
            for (Iterator iter = updatedConcerns.iterator(); iter.hasNext();) {
                UpdatedConcern uConcern = (UpdatedConcern) iter.next();
                if (uConcern.original == null)
                    newConcerns.add(uConcern);
            }
        }
        return newConcerns;
    }

    /**
     * @return list of UpdatedConcerns (deleted concerns only, no existing and new concerns)
     */
    public static List getDeletedConcerns() {
        if (deletedConcerns == null)
            deletedConcerns = new ArrayList();
        return deletedConcerns;
    }

    /**
     * @return list of UpdatedConcerns (existing concerns only, no new and deleted concerns) that have changed (ie. the original is different)
     */
    public static List getChangedConcerns() {
        ArrayList changedConcerns = new ArrayList();
        if (updatedConcerns != null) {
            for (Iterator iter = updatedConcerns.iterator(); iter.hasNext();) {
                UpdatedConcern uConcern = (UpdatedConcern) iter.next();
                // only add UpdatedConcerns that have changed
                if (uConcern.hasChanged())
                    changedConcerns.add(uConcern);
            }
        }
        return changedConcerns;
    }

    /**
     * @return true if UpdatedConcern has changed, false otherwise
     */
    private boolean hasChanged() {
        // in order for an UpdatedConcern to have changed it cannot be a newly added concern and
        // one of name/description/specDiagrams must have changed
        return (original != null && (updatedDescription != null || updatedName != null || updatedSpecDiagrams != null));
    }

    /**
     * @param concern
     *            for which to find its wrapper object (UpdatedConcern)
     * @return UpdatedConcern associated with the given concern or null if the given concern was not found
     */
    public static UpdatedConcern findUpdatedConcern(Concern concern) {
        if (updatedConcerns != null) {
            for (Iterator iter = updatedConcerns.iterator(); iter.hasNext();) {
                UpdatedConcern uConcern = (UpdatedConcern) iter.next();
                if (uConcern.getOriginal() == concern)
                    return uConcern;
            }
        }
        // concern not found
        return null;
    }

    /**
     * disposes of all UpdatedConcerns (existing, new, and deleted concerns)
     */
    public static void disposeConcerns() {
        if (updatedConcerns != null)
            updatedConcerns.clear();
        updatedConcerns = null;
        if (deletedConcerns != null)
            deletedConcerns.clear();
        deletedConcerns = null;
    }

}
