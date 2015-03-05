/* ******************************************************************************
 * Copyright (c) 2014 - 2015 Fabian Prasser.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Fabian Prasser - initial API and implementation
 ******************************************************************************/
package de.linearbits.swt.tiles;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;

/**
 * Reacts to a resize with a delay. Inspired by
 * http://stackoverflow.com/questions/2074966/detecting-when-a-user-is-finished-resizing-swt-shell
 * 
 * @author Fabian Prasser
 */
abstract class ResizeListener extends ControlAdapter implements Runnable {

    /** Time offset*/
    private static final int OFFSET    = 500;
    /** Timestamp*/
    private long             timestamp = 0;
    /** Tiles*/
    private Tiles<?>         tiles;
    
    /**
     * Constructor
     * @param tiles
     */
    ResizeListener(Tiles<?> tiles){
    	this.tiles = tiles;
    }

    /**
     * Resize
     */
    public void controlResized(ControlEvent e) {
        timestamp = System.currentTimeMillis();
        tiles.getDisplay().timerExec(OFFSET, this);
    }

    /**
     * Run
     */
    public void run() {
        if ((timestamp + OFFSET) < System.currentTimeMillis()) {
        	tiles.getDisplay().timerExec(-1, this);
        	controlResized();
        } else {
        	tiles.getDisplay().timerExec(500, this);
        }
    }
    
    /**
     * Implement this to listen for resize events
     */
    protected abstract void controlResized();
}