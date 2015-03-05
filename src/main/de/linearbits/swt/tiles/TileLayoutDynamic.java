/* ******************************************************************************
 * Copyright (c) 2014 - 2015 Fabian Prasser.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Fabian Prasser - initial API and implementation
 * ****************************************************************************
 */
package de.linearbits.swt.tiles;

/**
 * A dynamic layout
 * 
 * @author Fabian Prasser
 */
public class TileLayoutDynamic extends TileLayout {

    /** Number of columns */
    private final int columns;
    /** Number of rows */
    private final int rows;

    /**
     * Creates a new instance
     * @param columns
     * @param rows
     * @param marginX
     * @param marginY
     */
    public TileLayoutDynamic(int columns, int rows, int marginX, int marginY) {
        super(marginX, marginY);
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public int getHeight(Tiles<?> tiles) {
        return (tiles.getSize().y - (rows + 1) * getMarginY()) / rows;
    }

    @Override
    public int getWidth(Tiles<?> tiles) {
        return (tiles.getSize().x - (columns + 1) * getMarginX()) / columns;
    }
}
