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
 * A decorator that calls toString() on the element wrapped by a tile.
 * @author Fabian Prasser
 *
 * @param <T>
 */
public class DecoratorStringToString<T> extends DecoratorString<T> {

	@Override
	public String decorate(T t) {
		return t.toString();
	}
}
