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

/**
 * A decorator listener. Implement this and attach it to decorators, to dispose the
 * associated resources if requested and required.
 * 
 * @author Fabian Prasser
 */
public interface DecoratorListener {

    /**
     * Called when the decorator is disposed
     */
	public abstract void disposed();
}
