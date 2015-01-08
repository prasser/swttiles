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
 * A decorator that uses a color gradient
 * @author Fabian Prasser
 *
 * @param <T>
 */
public abstract class DecoratorColorGradient<T> extends DecoratorColor<T>{
	
	/** The gradient*/
	protected final Gradient gradient;
	
	/**
	 * Creates a new instance
	 * @param tiles
	 */
	public DecoratorColorGradient(final Gradient gradient){
		this.gradient = gradient;
		this.addDecoratorListener(new DecoratorListener(){
			@Override
			public void disposed() {
				gradient.dispose();
			}
		});
	}

	@Override
	public Color decorate(T element){
		return gradient.getColor(getValue(element));
	}
	
	/**
	 * Returns a value in [0, 1]
	 * @param value
	 * @return
	 */
	protected abstract double getValue(T element);
}
