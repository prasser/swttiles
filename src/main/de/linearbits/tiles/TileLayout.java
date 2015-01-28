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
 * A layout for the tiles
 * @author Fabian Prasser
 */
public abstract class TileLayout {
	
    /** Margin*/
    private final int marginX;
	/** Margin*/
    private final int marginY;
	
	/**
	 * Creates a new instance
	 * @param marginX
	 * @param marginY
	 */
	public TileLayout(int marginX, int marginY){
		this.marginX = marginX;
		this.marginY = marginY;
	}
	
	/**
	 * Returns the height of tiles
	 * @param tiles
	 * @return
	 */
	public abstract int getHeight(Tiles<?> tiles);
	
	/**
	 * @return the marginX
	 */
	public int getMarginX() {
		return marginX;
	}

	/**
	 * @return the marginY
	 */
	public int getMarginY() {
		return marginY;
	}

	/**
	 * Returns the width of tiles
	 * @param tiles
	 * @return
	 */
	public abstract int getWidth(Tiles<?> tiles);
}
