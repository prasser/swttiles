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
 * Decorator that returns a constant color
 * 
 * @author Fabian Prasser
 *
 * @param <T>
 */
public class DecoratorColorConstant<T> extends DecoratorColor<T> {
	
    /** The color*/
	private final Color color;
	
	/**
	 * Constructor
	 * @param color
	 */
	public DecoratorColorConstant(final Color color){
		this.color = color;
	}
	
	@Override
	public Color decorate(T t) {
		return color;
	}
}
