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
 * 
 * A decorator that returns a constant integer
 * 
 * @author Fabian Prasser
 *
 * @param <T>
 */
public class DecoratorIntegerConstant<T> extends DecoratorInteger<T> {
	
	private final int value;
	
	public DecoratorIntegerConstant(int value){
		this.value = value;
	}
	
	@Override
	public Integer decorate(T t) {
		return value;
	}
}
