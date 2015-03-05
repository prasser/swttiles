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
package de.linearbits.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.linearbits.swt.tiles.Filter;
import de.linearbits.swt.tiles.TileLayoutDynamic;
import de.linearbits.swt.tiles.Tiles;

/**
 * Example for using the Tiles SWT Widget
 * 
 * @author Fabian Prasser
 */
public class Example2 {

    /**
     * Main entry point
     * @param args
     */
	public static void main(String[] args) {
		
		// Create shell
		final Display display = new Display ();
		Shell shell = new Shell(display);
		shell.setText("SWT Tiles Example");
		shell.setLayout(new FillLayout());
	
		// Create tiles
		final Tiles<Integer> tiles = new Tiles<Integer>(shell, SWT.NONE);
		
		// Add items
		List<Integer> items = new ArrayList<Integer>();
		for (int i=0; i < 500; i++){
			items.add(i);
		}
		tiles.setItems(items);
		
		// Set layout
		tiles.setTileLayout(new TileLayoutDynamic(10, 10, 5, 5));
		
		// Order
		tiles.setComparator(getComparator());
		
		// Change filter periodically
		final Filter<Integer> filter1 = getFilter1();
		final Filter<Integer> filter2 = getFilter2();
		Runnable switcher = new Runnable() {
			public void run() {
				if (tiles.getFilter()!=filter2){
					tiles.setFilter(filter2);
				} else {
					tiles.setFilter(filter1);
				}
				tiles.update();
				display.timerExec(5000, this);
			}
		};
		switcher.run();
		
		// Pack and show
		shell.pack();
		shell.open ();
		shell.setSize(800, 600);
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

	/**
	 * Returns the comparator
	 * @return
	 */
	private static Comparator<Integer> getComparator() {
		return new Comparator<Integer>(){
			@Override
			public int compare(Integer t1, Integer t2) {
				return t1.compareTo(t2);
			}
		};
	}

	/**
	 * Returns the first filter
	 * @return
	 */
	private static Filter<Integer> getFilter1() {
		return new Filter<Integer>(){
			@Override
			public boolean accepts(Integer t) {
				return t < 90;
			}
		};
	}

	/**
	 * Returns the second filter
	 * @return
	 */
	private static Filter<Integer> getFilter2() {
		return new Filter<Integer>(){
			@Override
			public boolean accepts(Integer t) {
				return t >= 40 && t < 130;
			}
		};
	}
}
