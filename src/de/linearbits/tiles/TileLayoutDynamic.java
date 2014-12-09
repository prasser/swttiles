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
 * A dynamic layout
 * 
 * @author Fabian Prasser
 */
public class TileLayoutDynamic extends TileLayout {
	
    /** Width*/
	private final int width;
	/** Height*/
	private final int height;

	/**
	 * Creates a new instance
	 * @param width
	 * @param height
	 * @param marginX
	 * @param marginY
	 */
	public TileLayoutDynamic(int width, int height, int marginX, int marginY){
		super(marginX, marginY);
		this.width = width;
		this.height = height;
	}

	@Override
	public int getHeight(Tiles<?> tiles) {
		return tiles.getSize().y / height;
	}

	@Override
	public int getWidth(Tiles<?> tiles) {
		return tiles.getSize().x / width;
	}
}
