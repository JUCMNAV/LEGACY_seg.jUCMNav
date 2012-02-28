/**
 * Eclipse GEF redbook sample application
 * $Source$
 * $Revision$
 * 
 * (c) Copyright IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Gunnar Wagenknecht - initial contribution
 * 
 */
package seg.jUCMNav.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;

import seg.jUCMNav.editparts.URNRootEditPart;

/**
 * A delegating ZoomManager.
 * 
 * @author Gunnar Wagenknecht
 */
public class DelegatingZoomManager extends ZoomManager implements ZoomListener {
    /** the current ZoomManager all work is delegated to */
    private ZoomManager currentZoomManager;

    /** listeners */
    private ListenerList zoomListeners = new ListenerList(ListenerList.IDENTITY);

    private UCMNavMultiPageEditor editor;

    /**
     * Creates a new DelegatingZoomManager instance.
     */
    public DelegatingZoomManager(UCMNavMultiPageEditor editor) {
        super((ScalableFigure) null, (Viewport) null);

        setEditor(editor);

        List zoomLevels = new ArrayList(3);
        zoomLevels.add(ZoomManager.FIT_ALL);
        zoomLevels.add(ZoomManager.FIT_WIDTH);
        zoomLevels.add(ZoomManager.FIT_HEIGHT);
        setZoomLevelContributions(zoomLevels);

    }

    public UCMNavMultiPageEditor getEditor() {
        return editor;
    }

    public void setEditor(UCMNavMultiPageEditor editor) {
        this.editor = editor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomListener#zoomChanged(double)
     */
    public void zoomChanged(double zoom) {
        Object[] listeners = zoomListeners.getListeners();
        for (int i = 0; i < listeners.length; ++i) {
            ((ZoomListener) listeners[i]).zoomChanged(zoom);
        }

        // TODO: refresh all if global
        if (getEditor() != null && getEditor().getCurrentPage() != null) {
            // force a refresh
            URNRootEditPart rootEditPart = (URNRootEditPart) getEditor().getCurrentPage().getGraphicalViewer().getRootEditPart();
            rootEditPart.setMode(rootEditPart.getMode());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#addZoomListener(org.eclipse.gef.editparts.ZoomListener)
     */
    public void addZoomListener(ZoomListener listener) {
        zoomListeners.add(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#removeZoomListener(org.eclipse.gef.editparts.ZoomListener)
     */
    public void removeZoomListener(ZoomListener listener) {
        zoomListeners.remove(listener);
    }

    /**
     * Sets the ZoomManager all work should be delegated to.
     * 
     * @param zoomManager
     */
    public void setCurrentZoomManager(ZoomManager zoomManager) {
        if (null != currentZoomManager)
            currentZoomManager.removeZoomListener(this);

        currentZoomManager = zoomManager;
        if (null != currentZoomManager) {
            currentZoomManager.addZoomListener(this);
            zoomChanged(currentZoomManager.getZoom());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#canZoomIn()
     */
    public boolean canZoomIn() {
        if (null == currentZoomManager)
            return false;

        return currentZoomManager.canZoomIn();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#canZoomOut()
     */
    public boolean canZoomOut() {
        if (null == currentZoomManager)
            return false;

        return currentZoomManager.canZoomOut();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getMaxZoom()
     */
    public double getMaxZoom() {
        if (null == currentZoomManager)
            return 1;

        return currentZoomManager.getMaxZoom();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getMinZoom()
     */
    public double getMinZoom() {
        if (null == currentZoomManager)
            return 1;

        return currentZoomManager.getMinZoom();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getNextZoomLevel()
     */
    public double getNextZoomLevel() {
        if (null == currentZoomManager)
            return 1;

        return currentZoomManager.getNextZoomLevel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getPreviousZoomLevel()
     */
    public double getPreviousZoomLevel() {
        if (null == currentZoomManager)
            return 1;

        return currentZoomManager.getPreviousZoomLevel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getScalableFigure()
     */
    public ScalableFigure getScalableFigure() {
        if (null == currentZoomManager)
            return null;

        return currentZoomManager.getScalableFigure();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getUIMultiplier()
     */
    public double getUIMultiplier() {
        if (null == currentZoomManager)
            return 1;

        return currentZoomManager.getUIMultiplier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getViewport()
     */
    public Viewport getViewport() {
        if (null == currentZoomManager)
            return null;

        return currentZoomManager.getViewport();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getZoom()
     */
    public double getZoom() {
        if (null == currentZoomManager)
            return 1;

        return currentZoomManager.getZoom();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getZoomAsText()
     */
    public String getZoomAsText() {
        if (null == currentZoomManager)
            return " 100%"; //$NON-NLS-1$

        return currentZoomManager.getZoomAsText();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getZoomLevels()
     */
    public double[] getZoomLevels() {
        if (null == currentZoomManager)
            return new double[] { 1 };

        return currentZoomManager.getZoomLevels();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#getZoomLevelsAsText()
     */
    public String[] getZoomLevelsAsText() {
        if (null == currentZoomManager)
            return new String[] { " 100%" }; //$NON-NLS-1$

        return currentZoomManager.getZoomLevelsAsText();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#setUIMultiplier(double)
     */
    public void setUIMultiplier(double multiplier) {
        if (null == currentZoomManager)
            return;

        currentZoomManager.setUIMultiplier(multiplier);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#setViewLocation(org.eclipse.draw2d.geometry.Point)
     */
    public void setViewLocation(Point p) {
        if (null == currentZoomManager)
            return;

        currentZoomManager.setViewLocation(p);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#setZoom(double)
     */
    public void setZoom(double zoom) {
        if (null == currentZoomManager)
            return;

        currentZoomManager.setZoom(zoom);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#setZoomAnimationStyle(int)
     */
    public void setZoomAnimationStyle(int style) {
        if (null == currentZoomManager)
            return;

        currentZoomManager.setZoomAnimationStyle(style);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#setZoomAsText(java.lang.String)
     */
    public void setZoomAsText(String zoomString) {
        if (null == currentZoomManager)
            return;

        currentZoomManager.setZoomAsText(zoomString);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#setZoomLevels(double[])
     */
    public void setZoomLevels(double[] zoomLevels) {
        if (null == currentZoomManager)
            return;

        currentZoomManager.setZoomLevels(zoomLevels);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#zoomIn()
     */
    public void zoomIn() {
        if (null == currentZoomManager)
            return;

        currentZoomManager.zoomIn();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#zoomOut()
     */
    public void zoomOut() {
        if (null == currentZoomManager)
            return;

        currentZoomManager.zoomOut();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.ZoomManager#zoomTo(org.eclipse.draw2d.geometry.Rectangle)
     */
    public void zoomTo(Rectangle rect) {
        if (null == currentZoomManager)
            return;

        currentZoomManager.zoomTo(rect);
    }

}
