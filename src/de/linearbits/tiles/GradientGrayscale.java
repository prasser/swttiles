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
import org.eclipse.swt.widgets.Display;

/**
 * Implements a grayscale gradient
 * 
 * @author Fabian Prasser
 */
public class GradientGrayscale extends Gradient{

    /**
     * Returns the according colors
     * @param tiles
     * @return
     */
	private static final Color[] getColors(Tiles<?> tiles){
	    Display device = tiles.getDisplay();
	    return new Color[]{	new Color(device, 0, 0, 0), 
	    					new Color(device, 128, 128, 128),
	    					new Color(device, 255, 255, 255)};
	}
	
	/**
	 * Creates a new instance
	 * @param tiles
	 */
	public GradientGrayscale(Tiles<?> tiles) {
		super(tiles, getColors(tiles));
	}
}
