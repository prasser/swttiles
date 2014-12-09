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

import org.eclipse.swt.graphics.Color;

/**
 * A rendered tile
 * 
 * @author Fabian Prasser
 *
 * @param <T>
 */
class Tile<T> {

    /** Element*/
    protected final T      item;

    /** Style*/
    protected final String label;
    /** Style*/
    protected final int    lineWidth;
    /** Style*/
    protected final int    lineStyle;
    /** Style*/
    protected final Color  foregroundColor;
    /** Style*/
    protected final Color  backgroundColor;
    /** Style*/
    protected final Color  lineColor;

    /** Location*/
    protected final int    x;
    /** Location*/
    protected final int    y;
    /** Location*/
    protected final int    width;
    /** Location*/
    protected final int    height;

    /**
     * Creates a new instance
     * @param element
     * @param x
     * @param y
     * @param width
     * @param height
     * @param label
     * @param lineWidth
     * @param lineStyle
     * @param lineColor
     * @param foregroundColor
     * @param backgroundColor
     */
	Tile(T element, int x, int y, int width, int height, String label, int lineWidth,
			int lineStyle, Color lineColor, Color foregroundColor,
			Color backgroundColor) {
		
		this.item = element;
		
		this.label = label;
		this.lineWidth = lineWidth;
		this.lineStyle = lineStyle;
		this.lineColor = lineColor;
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tile [x=" + x + ", y=" + y + ", width=" + width
				+ ", height=" + height + "]";
	}
}
