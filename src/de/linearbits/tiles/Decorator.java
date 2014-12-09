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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWTException;

/**
 * This abstract base class represents a decorator and handles reference counting.
 * 
 * @author Fabian Prasser
 *
 * @param <T>
 * @param <U>
 */
public abstract class Decorator<T, U> {

    /** The attached listeners*/
    private List<DecoratorListener> listeners = new ArrayList<DecoratorListener>();
    /** The current reference count*/
    private int                     refcount  = 0;
    /** Has this decorator already been disposed*/
    private boolean                 disposed  = false;
	
    /**
     * Adds a decorator listener
     * @param listener
     */
	public final void addDecoratorListener(DecoratorListener listener){
		this.listeners.add(listener);
	}
	
	/**
	 * Implement this to decorate elements
	 * @param t
	 * @return
	 */
	public abstract U decorate(T t);
	
	/**
	 * Removes a decorator listener
	 * @param listener
	 */
	public final void removeDecoratorListener(DecoratorListener listener){
		this.listeners.remove(listener);
	}
	
	/**
	 * Decreases the reference count and potentially disposes the element
	 */
	protected final void free() {
	    
		if (disposed) {
			throw new SWTException("Already disposed");
		}
		
		refcount--;
		if (refcount == 0) {
			disposed = true;
			for (DecoratorListener listener : listeners){
				listener.disposed();
			}
		} 
	}
	
	/**
	 * Increases the reference count
	 */
	protected final void use(){
	    
		if (disposed) {
			throw new SWTException("Already disposed");
		}
		
		refcount++;
	}
}
