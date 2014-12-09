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
package de.linearbits.tiles;

/**
 * This class encapsulates settings for the animation
 * 
 * @author Fabian Prasser
 */
public class TileAnimationSettings {

    /** FPS*/
    private final int fps;
    /** Duration in milliseconds*/
    private final int duration;
    /** Duration per frame in milliseconds*/
    private final int delta;
    
    /**
     * Creates a new instance
     * @param fps
     * @param duration
     */
    public TileAnimationSettings(int fps, int duration){
        this.fps = fps;
        this.duration = duration;
        this.delta = (int)(1000d / (double)fps);
    }

    /**
     * Returns the duration in milliseconds
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns the frames per second
     * @return
     */
    public int getFPS() {
        return fps;
    }

    /**
     * Returns the milliseconds per frame
     * @return
     */
    protected int getDelta() {
        return delta;
    }
}
