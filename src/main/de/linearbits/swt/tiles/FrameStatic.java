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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;

/**
 * This class implements a static frame
 * @author Fabian Prasser
 *
 * @param <T>
 */
class FrameStatic<T> extends Frame<T> implements Cloneable  {

    /** Elements */
    private List<T>             elements    = new ArrayList<T>();

    /** Filter*/
    private Filter<T>           filter;
    /** Comparator*/
    private Comparator<T>       comparator;

    /** Rendered elements*/
    private List<Tile<T>>       rendered    = new ArrayList<Tile<T>>();
    /** The current background color */
    private Color               background;
    /** Layout*/
    private int                 width       = 0;
    /** Layout*/
    private int                 height      = 0;

    /** Layout*/
    private int                 tileWidth   = 0;
    /** Layout*/
    private int                 tileHeight  = 0;
    /** Layout*/
    private int                 tileMarginX = 0;
    /** Layout*/
    private int                 tileMarginY = 0;

    /** Decorator */
    private DecoratorInteger<T> lineWidthDecorator;
    /** Decorator */
    private DecoratorInteger<T> lineStyleDecorator;
    /** Decorator */
    private DecoratorColor<T>   foregroundColorDecorator;
    /** Decorator */
    private DecoratorColor<T>   backgroundColorDecorator;
    /** Decorator */
    private DecoratorColor<T>   lineColorDecorator;
    /** Decorator */
    private DecoratorString<T>  labelDecorator;

    /** Tiles*/
    private Tiles<T>            tiles;

	/**
	 * Clone constructor
	 * @param backgroundColor
	 * @param foregroundColor
	 * @param lineColor
	 * @param elements
	 * @param filter
	 * @param Comparator
	 * @param background
	 * @param width
	 * @param height
	 * @param tileWidth
	 * @param tileHeight
	 * @param tileMarginX
	 * @param tileMarginY
	 * @param lineWidthDecorator
	 * @param lineStyleDecorator
	 * @param foregroundColorDecorator
	 * @param backgroundColorDecorator
	 * @param lineColorDecorator
	 * @param labelDecorator
	 * @param tiles
	 */
	FrameStatic(List<T> elements, Filter<T> filter, Comparator<T> Comparator,
			Color background, int width, int height, int tileWidth,
			int tileHeight, int tileMarginX, int tileMarginY,
			DecoratorInteger<T> lineWidthDecorator,
			DecoratorInteger<T> lineStyleDecorator,
			DecoratorColor<T> foregroundColorDecorator,
			DecoratorColor<T> backgroundColorDecorator,
			DecoratorColor<T> lineColorDecorator,
			DecoratorString<T> labelDecorator, Tiles<T> tiles) {
	    
		super(tiles);
		
        // Prepare resources
		this.elements = elements;
		this.filter = filter;
		this.comparator = Comparator;
		this.background = background;
		this.width = width;
		this.height = height;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileMarginX = tileMarginX;
		this.tileMarginY = tileMarginY;
		this.lineWidthDecorator = lineWidthDecorator;
		this.lineStyleDecorator = lineStyleDecorator;
		this.foregroundColorDecorator = foregroundColorDecorator;
		this.backgroundColorDecorator = backgroundColorDecorator;
		this.lineColorDecorator = lineColorDecorator;
		this.labelDecorator = labelDecorator;
		this.tiles = tiles;
		
		this.lineWidthDecorator.use();
		this.lineStyleDecorator.use();
		this.foregroundColorDecorator.use();
		this.backgroundColorDecorator.use();
		this.lineColorDecorator.use();
		this.labelDecorator.use();
	}
	
	/**
	 * Creates a new instance
	 * @param tiles
	 */
    FrameStatic(final Tiles<T> tiles){
        
    	super(tiles);
    	
    	// Store
    	this.tiles = tiles;

        // Prepare resources
    	final Color backgroundColor = new Color(tiles.getDisplay(), 255, 255, 255);
    	final Color lineColor = new Color(tiles.getDisplay(), 0, 0, 0);
    	final Color foregroundColor = new Color(tiles.getDisplay(), 0, 0, 0);
    	
    	// Initial settings
    	this.background = tiles.getBackground();
    	this.lineWidthDecorator = new DecoratorIntegerConstant<T>(1);
    	this.lineStyleDecorator = new DecoratorIntegerConstant<T>(SWT.LINE_SOLID);
    	this.foregroundColorDecorator = new DecoratorColorConstant<T>(foregroundColor);
    	this.foregroundColorDecorator.addDecoratorListener(new DecoratorListener(){
			@Override public void disposed() {
				foregroundColor.dispose();
			}
    	});
    	this.backgroundColorDecorator = new DecoratorColorConstant<T>(backgroundColor);
    	this.backgroundColorDecorator.addDecoratorListener(new DecoratorListener(){
			@Override public void disposed() {
				backgroundColor.dispose();
			}
    	});
    	this.lineColorDecorator = new DecoratorColorConstant<T>(lineColor);
    	this.lineColorDecorator.addDecoratorListener(new DecoratorListener(){
			@Override public void disposed() {
				lineColor.dispose();
			}
    	});
    	this.labelDecorator = new DecoratorStringToString<T>();
    	this.filter = new Filter<T>(){
    		@Override public boolean accepts(T t) { return true; }
    	};
    	this.comparator = new Comparator<T>(){
			@Override public int compare(T t1, T t2) {return 0; }
    	};

        // Control size
        Point p = tiles.getSize();
        this.width = p.x;
        this.height = p.y;

		this.lineWidthDecorator.use();
		this.lineStyleDecorator.use();
		this.foregroundColorDecorator.use();
		this.backgroundColorDecorator.use();
		this.lineColorDecorator.use();
		this.labelDecorator.use();
    }

	@Override
	public FrameStatic<T> clone(){
		return new FrameStatic<T>(elements, filter, comparator, background, width,
				height, tileWidth, tileHeight, tileMarginX, tileMarginY,
				lineWidthDecorator, lineStyleDecorator,
				foregroundColorDecorator, backgroundColorDecorator,
				lineColorDecorator, labelDecorator, tiles);
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        FrameStatic<?> other = (FrameStatic<?>) obj;
        if (background == null) {
            if (other.background != null) return false;
        } else if (!background.equals(other.background)) return false;
        if (backgroundColorDecorator == null) {
            if (other.backgroundColorDecorator != null) return false;
        } else if (!backgroundColorDecorator.equals(other.backgroundColorDecorator)) return false;
        if (comparator == null) {
            if (other.comparator != null) return false;
        } else if (!comparator.equals(other.comparator)) return false;
        if (elements == null) {
            if (other.elements != null) return false;
        } else if (!elements.equals(other.elements)) return false;
        if (filter == null) {
            if (other.filter != null) return false;
        } else if (!filter.equals(other.filter)) return false;
        if (foregroundColorDecorator == null) {
            if (other.foregroundColorDecorator != null) return false;
        } else if (!foregroundColorDecorator.equals(other.foregroundColorDecorator)) return false;
        if (height != other.height) return false;
        if (labelDecorator == null) {
            if (other.labelDecorator != null) return false;
        } else if (!labelDecorator.equals(other.labelDecorator)) return false;
        if (lineColorDecorator == null) {
            if (other.lineColorDecorator != null) return false;
        } else if (!lineColorDecorator.equals(other.lineColorDecorator)) return false;
        if (lineStyleDecorator == null) {
            if (other.lineStyleDecorator != null) return false;
        } else if (!lineStyleDecorator.equals(other.lineStyleDecorator)) return false;
        if (lineWidthDecorator == null) {
            if (other.lineWidthDecorator != null) return false;
        } else if (!lineWidthDecorator.equals(other.lineWidthDecorator)) return false;
        if (tileHeight != other.tileHeight) return false;
        if (tileMarginX != other.tileMarginX) return false;
        if (tileMarginY != other.tileMarginY) return false;
        if (tileWidth != other.tileWidth) return false;
        if (tiles == null) {
            if (other.tiles != null) return false;
        } else if (!tiles.equals(other.tiles)) return false;
        if (width != other.width) return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((background == null) ? 0 : background.hashCode());
        result = prime * result + ((backgroundColorDecorator == null) ? 0 : backgroundColorDecorator.hashCode());
        result = prime * result + ((comparator == null) ? 0 : comparator.hashCode());
        result = prime * result + ((elements == null) ? 0 : elements.hashCode());
        result = prime * result + ((filter == null) ? 0 : filter.hashCode());
        result = prime * result + ((foregroundColorDecorator == null) ? 0 : foregroundColorDecorator.hashCode());
        result = prime * result + height;
        result = prime * result + ((labelDecorator == null) ? 0 : labelDecorator.hashCode());
        result = prime * result + ((lineColorDecorator == null) ? 0 : lineColorDecorator.hashCode());
        result = prime * result + ((lineStyleDecorator == null) ? 0 : lineStyleDecorator.hashCode());
        result = prime * result + ((lineWidthDecorator == null) ? 0 : lineWidthDecorator.hashCode());
        result = prime * result + tileHeight;
        result = prime * result + tileMarginX;
        result = prime * result + tileMarginY;
        result = prime * result + tileWidth;
        result = prime * result + ((tiles == null) ? 0 : tiles.hashCode());
        result = prime * result + width;
        return result;
    }

    @Override
    public String toString() {
        return "FrameStatic [elements=" + elements + ", filter=" + filter + ", comparator=" + comparator + ", background=" + background + ", width=" + width + ", height=" + height + ", tileWidth=" +
               tileWidth + ", tileHeight=" + tileHeight + ", tileMarginX=" + tileMarginX + ", tileMarginY=" + tileMarginY + ", lineWidthDecorator=" + lineWidthDecorator + ", lineStyleDecorator=" +
               lineStyleDecorator + ", foregroundColorDecorator=" + foregroundColorDecorator + ", backgroundColorDecorator=" + backgroundColorDecorator + ", lineColorDecorator=" + lineColorDecorator +
               ", labelDecorator=" + labelDecorator + ", tiles=" + tiles + "]";
    }

    /**
     * Free resources
     */
	protected void dispose() {
		
		this.lineWidthDecorator.free();
		this.lineStyleDecorator.free();
		this.foregroundColorDecorator.free();
		this.backgroundColorDecorator.free();
		this.lineColorDecorator.free();
		this.labelDecorator.free();
	}

    @Override
    protected Color getBackground() {
        return background;
    }

    @Override
    protected Comparator<T> getComparator() {
    	return this.comparator;
    }

    @Override
    protected DecoratorColor<T> getDecoratorBackgroundColor(){
    	return this.backgroundColorDecorator;
    }

    @Override
    protected DecoratorColor<T> getDecoratorForegroundColor(){
    	return this.foregroundColorDecorator;
    }

	@Override
    protected DecoratorString<T> getDecoratorLabel(){
    	return this.labelDecorator;
    }

	@Override
    protected DecoratorColor<T> getDecoratorLineColor(){
    	return this.lineColorDecorator;
    }

	@Override
    protected DecoratorInteger<T> getDecoratorLineStyle(){
    	return this.lineStyleDecorator;
    }

	@Override
    protected DecoratorInteger<T> getDecoratorLineWidth(){
    	return this.lineWidthDecorator;
    }

    @Override
    protected Filter<T> getFilter() {
    	return this.filter;
    }

	@Override
	protected List<T> getItems() {
		return new ArrayList<T>(this.elements);
	}
	
    /**
	 * @return the tileHeight
	 */
	protected int getTileHeight() {
		return tileHeight;
	}
	
	@Override
    protected List<Tile<T>> getTiles() {
        return rendered;
    }
	
	/**
	 * @return the tileWidth
	 */
	protected int getTileWidth() {
		return tileWidth;
	}

    /**
     * Setter
     * @param arg0
     */
    protected void setBackground(Color arg0) {
        this.background = arg0;
    }

    /**
     * Sets an Comparator
     * 
     * @param comparator
     */
    protected void setComparator(Comparator<T> comparator) {
    	this.comparator = comparator;
    }

    /**
     * Setter
     * @param decorator
     */
    protected void setDecoratorBackgroundColor(DecoratorColor<T> decorator){
    	this.backgroundColorDecorator.free();
        this.backgroundColorDecorator = decorator;
        this.backgroundColorDecorator.use();
    }
    
    /**
     * Setter
     * @param decorator
     */
    protected void setDecoratorForegroundColor(DecoratorColor<T> decorator){
    	this.foregroundColorDecorator.free();
        this.foregroundColorDecorator = decorator;
        this.foregroundColorDecorator.use();
    }

    /**
     * Setter
     * @param decorator
     */
    protected void setDecoratorLabel(DecoratorString<T> decorator){
    	this.labelDecorator.free();
        this.labelDecorator = decorator;
        this.labelDecorator.use();
    }


    /**
     * Setter
     * @param decorator
     */
    protected void setDecoratorLineColor(DecoratorColor<T> decorator){
    	this.lineColorDecorator.free();
        this.lineColorDecorator = decorator;
        this.lineColorDecorator.use();
    }


    /**
     * Setter
     * @param decorator
     */
    protected void setDecoratorLineStyle(DecoratorInteger<T> decorator){
    	this.lineStyleDecorator.free();
        this.lineStyleDecorator = decorator;
        this.lineStyleDecorator.use();
    }

	/**
     * Setter
     * @param decorator
     */
    protected void setDecoratorLineWidth(DecoratorInteger<T> decorator){
    	this.lineWidthDecorator.free();
        this.lineWidthDecorator = decorator;
        this.lineWidthDecorator.use();
    }


    /**
     * Sets a filter
     * 
     * @param filter
     */
    protected void setFilter(Filter<T> filter) {
    	this.filter = filter;
    }


    /**
	 * Sets the height
	 * @param height
	 */
	protected void setHeight(int height){
		this.height = height;
	}


    /**
	 * Sets the items
	 * @param items
	 */
    protected void setItems(List<T> items){
		this.elements.clear();
		this.elements.addAll(items);
	}


    /**
     * Sets the tile width
     * @param width
     */
    protected void setTileHeight(int height) {
    	this.tileHeight = height;
    }


    /**
     * Sets the tile width
     * @param width
     */
    protected void setTileMarginX(int margin) {
    	this.tileMarginX = margin;
    }

	/**
     * Sets the tile width
     * @param width
     */
    protected void setTileMarginY(int margin) {
    	this.tileMarginY = margin;
    }

    /**
     * Sets the tile width
     * @param width
     */
    protected void setTileWidth(int width) {
    	this.tileWidth = width;
    }

    /**
	 * Sets the width
	 * @param width
	 */
	protected void setWidth(int width){
		this.width = width;
	}

    @Override
    protected void update(){
	
    	// Clear
    	this.rendered.clear();

    	// Tile location
    	int x = tileMarginX;
    	int y = tileMarginY;
    	
    	// Prepare
    	List<T> active = new ArrayList<T>();
    	for (T element : this.elements) {
    		if (filter.accepts(element)) active.add(element);
    	}
    	Collections.sort(active, comparator);
    	for (T element : active) {
    		
    		// Decorate
        	int lineWidth = lineWidthDecorator.decorate(element);
        	int lineStyle = lineStyleDecorator.decorate(element);
        	Color lineColor = lineColorDecorator.decorate(element);
        	Color foregroundColor = foregroundColorDecorator.decorate(element);
        	Color backgroundColor = backgroundColorDecorator.decorate(element);
        	String label = labelDecorator.decorate(element);
        	
        	// Store
        	rendered.add(new Tile<T>(element, x, y, tileWidth, tileHeight, 
        							 label, lineWidth, lineStyle, lineColor, foregroundColor, backgroundColor));
        	
        	// Layout
			x += tileWidth + tileMarginX;
			if (x + tileWidth + tileMarginX > width) {
				y += tileHeight + tileMarginY;
				x = tileMarginX;
			}
			
			// Stop if no more space
			if (y + tileHeight + tileMarginY > height) {
				break;
			}
    	}
    }
}
