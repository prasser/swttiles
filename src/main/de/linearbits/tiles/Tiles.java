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

import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TypedListener;

/**
 * 
 * @author Fabian Prasser
 *
 * @param <T>
 */
public class Tiles<T> extends Canvas {

    /** Current frame*/
    private Frame<T>              current           = null;

    /** Next frame*/
    private FrameStatic<T>        next              = new FrameStatic<T>(this);

    /** Default layout*/
    private TileLayout            layout            = new TileLayoutDynamic(10, 10, 5, 5);

    /** Tooltip decorator*/
    private DecoratorString<T>    tooltipDecorator  = new DecoratorStringToString<T>();

    /** Listener*/
    private PaintListener         paintListener     = getPaintListener();

    /** Listener*/
    private ResizeListener        resizeListener    = getResizeListener();

    /** Listener*/
    private MouseListener         mouseListener     = getMouseListener();

    /** Listener*/
    private MouseMoveListener     mouseMoveListener = getMouseMoveListener();

    /** Listener*/
    private DisposeListener       disposeListener   = getDisposeListener();

    /** Animation*/
    private Runnable              animation         = getAnimation();

    /** Animation settings*/
    private TileAnimationSettings settings          = new TileAnimationSettings(30, 3000);

    /** Selected item*/
    private T                     selectedItem      = null;

    /** Active item*/
    private T                     activeItem        = null;

    /** Color*/
    private Color                 black;

    /**
     * Creates a new instance
     * @param parent
     * @param style
     */
    public Tiles(Composite parent, int style) {
        super(parent, style | SWT.DOUBLE_BUFFERED);
        this.addPaintListener(paintListener); 
        this.addControlListener(resizeListener);
        this.addDisposeListener(disposeListener);
        this.addMouseListener(mouseListener);
        this.addMouseMoveListener(mouseMoveListener);
        this.addControlListener(resizeListener);
        this.black = new Color(getDisplay(), 0, 0, 0);
        getDisplay().timerExec(settings.getDelta(), animation);
    }

    /**
     * Adds a selection listener
     * @param listener
     */
	public void addSelectionListener(SelectionListener listener) {
	    super.checkWidget();
		addListener(SWT.Selection, new TypedListener(listener));
	}

	/**
     * Returns the settings
     * @return
     */
    public TileAnimationSettings getAnimationSettings(){
        super.checkWidget();
        return this.settings;
    }

    /**
     * Returns the comparator
     * @return
     */
	public Comparator<T> getComparator() {
        super.checkWidget();
    	if (current == null) return null;
        else return current.getComparator();
    }

	/**
	 * Returns a decorator
	 * @return
	 */
    public DecoratorColor<T> getDecoratorBackgroundColor(){
        super.checkWidget();
    	if (current == null) return null;
        else return current.getDecoratorBackgroundColor();
    }

    /**
     * Returns a decorator
     * @return
     */
    public DecoratorColor<T> getDecoratorForegroundColor(){
        super.checkWidget();
    	if (current == null) return null;
        else return current.getDecoratorForegroundColor();
    }

    /**
     * Returns a decorator
     * @return
     */
	public DecoratorString<T> getDecoratorLabel(){
        super.checkWidget();
    	if (current == null) return null;
        else return current.getDecoratorLabel();
    }

	/**
	 * Returns a decorator
	 * @return
	 */
	public DecoratorColor<T> getDecoratorLineColor(){
        super.checkWidget();
    	if (current == null) return null;
        else return current.getDecoratorLineColor();
    }

	/**
	 * Returns a decorator
	 * @return
	 */
	public DecoratorInteger<T> getDecoratorLineStyle(){
        super.checkWidget();
    	if (current == null) return null;
        else return current.getDecoratorLineStyle();
    }

	/**
	 * Returns a decorator
	 * @return
	 */
	public DecoratorInteger<T> getDecoratorLineWidth(){
        super.checkWidget();
    	if (current == null) return null;
        else return current.getDecoratorLineWidth();
    }

	/**
	 * Returns the filter
	 * @return
	 */
	public Filter<T> getFilter() {
        super.checkWidget();
    	if (current == null) return null;
        else return current.getFilter();
    }

	/**
	 * Returns the items displayed by this widget
	 * @return
	 */
	public List<T> getItems() {
	    super.checkWidget();
		if (current == null) return null;
        else return current.getItems();
	}

	/**
	 * Returns the selected item
	 * @return
	 */
	public T getSelectedItem() {
	    super.checkWidget();
		return selectedItem;
	}

	/**
	 * Removes a selection listener
	 * @param listener
	 */
	public void removeSelectionListener(SelectionListener listener) {
	    super.checkWidget();
		removeListener(SWT.Selection, listener);
	}

	/**
     * Sets the settings
     * @return
     */
    public void setAnimationSettings(TileAnimationSettings settings){
        super.checkWidget();
        this.settings = settings;
    }
	
	@Override
	public void setBackground(Color arg0) {
	    super.checkWidget();
		super.setBackground(arg0);
		this.addFrame();
		this.next.setBackground(arg0);
	}

	/**
	 * Sets an Comparator
	 * 
	 * @param Comparator
	 */
	public void setComparator(Comparator<T> Comparator) {
	    super.checkWidget();
		this.addFrame();
		this.next.setComparator(Comparator);
	}

	/**
	 * Sets a decorator
	 * 
	 * @param decorator
	 */
	public void setDecoratorBackgroundColor(DecoratorColor<T> decorator) {
	    super.checkWidget();
		this.addFrame();
		this.next.setDecoratorBackgroundColor(decorator);
	}

	/**
	 * Sets a decorator
	 * 
	 * @param decorator
	 */
	public void setDecoratorForegroundColor(DecoratorColor<T> decorator) {
	    super.checkWidget();
		this.addFrame();
		this.next.setDecoratorForegroundColor(decorator);
	}

	/**
	 * Sets a decorator
	 * 
	 * @param decorator
	 */
	public void setDecoratorLabel(DecoratorString<T> decorator) {
	    super.checkWidget();
		this.addFrame();
		this.next.setDecoratorLabel(decorator);
	}

    /**
	 * Sets a decorator
	 * 
	 * @param decorator
	 */
	public void setDecoratorLineColor(DecoratorColor<T> decorator) {
	    super.checkWidget();
		this.addFrame();
		this.next.setDecoratorLineColor(decorator);
	}

	/**
	 * Sets a decorator
	 * 
	 * @param decorator
	 */
	public void setDecoratorLineStyle(DecoratorInteger<T> decorator) {
	    super.checkWidget();
		this.addFrame();
		this.next.setDecoratorLineStyle(decorator);
	}
    
	/**
	 * Sets a decorator
	 * 
	 * @param decorator
	 */
	public void setDecoratorLineWidth(DecoratorInteger<T> decorator) {
	    super.checkWidget();
		this.addFrame();
		this.next.setDecoratorLineWidth(decorator);
	}
    
    /**
	 * Sets a decorator
	 * 
	 * @param decorator
	 */
	public void setDecoratorTooltip(DecoratorString<T> decorator) {
	    super.checkWidget();
		this.tooltipDecorator = decorator;
	}
    
    /**
	 * Sets a filter
	 * 
	 * @param filter
	 */
	public void setFilter(Filter<T> filter) {
	    super.checkWidget();
		this.addFrame();
		this.next.setFilter(filter);
	}
	
    /**
     * Sets the selected item
     * @return
     */
    public void setSelectedItem(T item) {
        super.checkWidget();
        this.selectedItem = item;
        this.redraw();
    }
    
    /**
	 * Adds an item
	 * 
	 * @param t
	 */
	public void setItems(List<T> items) {
	    super.checkWidget();
		this.addFrame();
		this.next.setItems(items);
	}
    
    /**
	 * Sets the tile layout
	 * 
	 * @param width
	 */
	public void setTileLayout(TileLayout layout) {
	    super.checkWidget();
		this.layout = layout;
		this.addFrame();
		this.next.setTileHeight(this.layout.getHeight(Tiles.this));
		this.next.setTileWidth(this.layout.getWidth(Tiles.this));
	}

	/**
	 * Updates the tiles. 
	 * TODO: Good to override?
	 */
	@Override
	public void update() {
		
	    super.checkWidget();
		super.update();

		if (current == null){
			this.current = next.clone();
			this.current.update();
		} else if (current instanceof FrameStatic) {
			FrameStatic<T> target = next.clone();
			target.update();
			current.update();
			this.current = new FrameDynamic<T>(this, (FrameStatic<T>)current, target);
			this.current.update();
		}
	}

    /**
	 * Creates a new frame
	 */
	private void addFrame(){
		FrameStatic<T> current = this.next;
		this.next = this.next.clone();
		current.dispose();
	} 

    /**
     * Fires a selection event
     * @param tile
     */
    private void fireSelectionEvent(Tile<T> tile){
    	Event event = new Event();
    	event.data = tile.item;
    	event.display = getDisplay();
    	event.widget = this;
    	event.x = tile.x;
    	event.y = tile.y;
    	event.width = tile.width;
    	event.height = tile.height;
    	notifyListeners(SWT.Selection, event);
    }

	/**
     * Returns the animation thread
     * @return
     */
	private Runnable getAnimation() {
		return new Runnable() {
			public void run() {
				Frame<T> frame = current;
				if (frame != null && frame instanceof FrameDynamic) {
					((FrameDynamic<T>)frame).tick();
					frame.update();
					redraw();
				}
				getDisplay().timerExec(settings.getDelta(), this);
			}
		};
	}

	/**
	 * Creates a listener
	 * @return
	 */
    private DisposeListener getDisposeListener() {
		return new DisposeListener(){
            @Override
            public void widgetDisposed(DisposeEvent arg0) {
            	getDisplay().timerExec(-1, animation);
            	if (current != null) current.dispose();
            	if (next != null) next.dispose();
            	black.dispose();
                removePaintListener(paintListener);
                removeControlListener(resizeListener);
                removeMouseListener(mouseListener);
                removeMouseMoveListener(mouseMoveListener);
                removeDisposeListener(this);
            }
        };
	}

    /**
	 * Returns the mouse listener
	 * @return
	 */
    private MouseListener getMouseListener() {
		return new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent arg0) {
		        Tile<T> tile = getTileAt(arg0.x, arg0.y);
		        if (tile != null && tile.item != selectedItem) {
		        	selectedItem = tile.item;
		        	redraw();
			        fireSelectionEvent(tile);
		        } 
			}
        };
	}

    /**
	 * Returns the mouse motion listener
	 * @return
	 */
    private MouseMoveListener getMouseMoveListener() {
		return new MouseMoveListener(){
			@Override
			public void mouseMove(MouseEvent arg0) {
				
				// Check
		        Tile<T> tile = getTileAt(arg0.x, arg0.y);
		        T item = tile == null ? null : tile.item;
		        
		        // Break
		        if (item == activeItem)return;
		        
		        // Set
		        activeItem = item;
		        redraw();
		        String tooltip = activeItem != null ? tooltipDecorator.decorate(activeItem) : null;
		        Tiles.this.setToolTipText(tooltip);
			}
        };
	}

    /**
     * Creates a listener
     * @return
     */
    private PaintListener getPaintListener() {
		return new PaintListener(){
            @Override
            public void paintControl(PaintEvent e){
            	Point size = getSize();
            	paintFrame(e.gc, current, size.x, size.y);
            }
        };
	}

    /**
     * Creates a listener
     * @return
     */
    private ResizeListener getResizeListener() {
    	return new ResizeListener(this){
			@Override
			protected void controlResized() {
                addFrame();
                Point p = Tiles.this.getSize();
                next.setWidth(p.x);
                next.setHeight(p.y);
                next.setTileHeight(layout.getHeight(Tiles.this));
                next.setTileWidth(layout.getWidth(Tiles.this));
                next.setTileMarginX(layout.getMarginX());
                next.setTileMarginY(layout.getMarginY());
                update();
			}
    	};
	}

    /**
     * Returns the tile at the given location, null if there is none
     * @param x
     * @param y
     * @return
     */
    private Tile<T> getTileAt(int x, int y){
    	if (current == null) return null;
		for (Tile<T> tile : current.getTiles()) {
			if (x >= tile.x && y >= tile.y && x <= tile.x + tile.width
					&& y <= tile.y + tile.height) {
				return tile;
			}
		}
		return null;
    }

    /**
     * Paints a frame
     * @param height 
     * @param width 
     * @param decorator
     */
    private void paintFrame(GC gc, Frame<T> frame, int width, int height) {
        
        if (frame == null) {
            return; 
        }

        // Background
        gc.setBackground(frame.getBackground());
        gc.fillRectangle(0, 0, width, height);
        
        // Render each tile
        for (Tile<T> tile : frame.getTiles()) {

			// Background
			gc.setClipping(0, 0, width, height);
			gc.setBackground(tile.backgroundColor);
			gc.fillRectangle(tile.x, tile.y, tile.width, tile.height);

			// Border
			gc.setForeground(tile.lineColor);
			gc.setLineStyle(tile.lineStyle);
			gc.setLineWidth(tile.lineWidth);
			gc.drawRectangle(tile.x, tile.y, tile.width, tile.height);

			// Text
			gc.setForeground(tile.foregroundColor);
			gc.setClipping(tile.x, tile.y, tile.width, tile.height);
			paintString(gc, tile.label, tile.x, tile.y, tile.width, tile.height);
			
			if (tile.item == activeItem) {

	        	gc.setClipping(0, 0, width, height);
	        	gc.setAlpha(70);
	            gc.setBackground(black);
	            gc.fillRectangle(tile.x, tile.y, tile.width, tile.height);
	            gc.setAlpha(255);
			} 
			if (tile.item == selectedItem) {
	        	gc.setClipping(0, 0, width, height);
	        	gc.setAlpha(100);
	            gc.setBackground(black);
	            gc.fillRectangle(tile.x, tile.y, tile.width, tile.height);
	            gc.setAlpha(255);
			}
        }
    }

    /**
     * Paints the given string within the given rectangle
     * 
     * @param gc
     * @param string
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void paintString(GC gc, String string, int x, int y, int width, int height) {
        Point extent = gc.textExtent(string);
        int xx = x + (width - extent.x) / 2;
        int yy = y + (height - extent.y) / 2;
        
        if (extent.x <= width * 0.9f) {
            gc.drawText(string, xx, yy, true);
        } else {

            // Enable anti-aliasing
            gc.setTextAntialias(SWT.ON);
            gc.setAntialias(SWT.ON);
            
            // Compute position and factor
            float factor1 = width * 0.9f / (float)extent.x;
            float factor2 = height * 0.9f / (float)extent.y;
            float factor = Math.min(factor1, factor2);
            int positionX = x + (int)(((float)width - (float)extent.x * factor) / 2f); 
            int positionY = y + (int)(((float)height - (float)extent.y * factor) / 2f);
            
            // Initialize transformation
            Transform transform = new Transform(gc.getDevice());
            transform.identity();
            transform.translate(positionX, positionY);
            transform.scale(factor, factor);
            gc.setTransform(transform);
            
            // Prepare
            Path path = new Path(this.getDisplay());
            path.addString(string, 0, 0, gc.getFont());
            
            // Draw and reset
            Color back = gc.getBackground();
            gc.setBackground(gc.getForeground());
            gc.fillPath(path);
            gc.setTransform(null);
            gc.setTextAntialias(SWT.OFF);
            gc.setAntialias(SWT.OFF);
            gc.setBackground(back);
            path.dispose();
        }
    }

	/**
	 * Removes the current frame
	 */
	protected void removeFrame() {

		if (current instanceof FrameStatic) {
			if (!next.equals(current)) {
				update();
			} else {
			    current.update();
				redraw();
			}
		} else {
			Frame<T> old = ((FrameDynamic<T>) current);
			FrameStatic<T> source = ((FrameDynamic<T>) current).getTarget();
			current = source.clone();
			old.dispose();
			if (!next.equals(current)) {
				update();
			} else {
			    current.update();
				redraw();
			}
		}
	}
}
