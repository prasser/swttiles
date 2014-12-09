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
 * Interface for filters. Implement this to filter sets of elements to be displayed by the widget.
 * 
 * @author Fabian Prasser
 *
 * @param <T>
 */
public interface Filter<T> {

    /**
     * Returns whether the element is accepted
     * @param t
     * @return
     */
    public boolean accepts(T t);
}
