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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.linearbits.swt.tiles.DecoratorColor;
import de.linearbits.swt.tiles.DecoratorColorGradient;
import de.linearbits.swt.tiles.DecoratorInteger;
import de.linearbits.swt.tiles.DecoratorListener;
import de.linearbits.swt.tiles.DecoratorString;
import de.linearbits.swt.tiles.Filter;
import de.linearbits.swt.tiles.Gradient;
import de.linearbits.swt.tiles.GradientHeatscale;
import de.linearbits.swt.tiles.TileLayoutDynamic;
import de.linearbits.swt.tiles.Tiles;

/**
 * Example for using the Tiles SWT Widget
 * 
 * @author Fabian Prasser
 */
public class Example4 {

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
		
		// Order and filter
		tiles.setComparator(getComparator(500));
		tiles.setFilter(getFilter1());
		
		// Decorators
		tiles.setDecoratorBackgroundColor(getDecoratorBackgroundColor(tiles));
		tiles.setDecoratorForegroundColor(getDecoratorForegroundColor(tiles));
		tiles.setDecoratorLabel(getDecoratorLabel(tiles));
		tiles.setDecoratorLineColor(getDecoratorLineColor(tiles));
		tiles.setDecoratorLineStyle(getDecoratorLineStyle(tiles));
		tiles.setDecoratorLineWidth(getDecoratorLineWidth(tiles));
		tiles.setDecoratorTooltip(getDecoratorTooltip(tiles));
		tiles.update();
		
        // Change items periodically
        Runnable switcher = new Runnable() {

            public void run() {
                tiles.setComparator(getComparator(500));
                tiles.update();
                display.timerExec(3000, this);
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
	private static Comparator<Integer> getComparator(int size) {
	    
	    List<Integer> elements = new ArrayList<Integer>();
	    for (int i=0; i<=size; i++) {
	        elements.add(i);
	    }
	    Collections.shuffle(elements);
	    
	    final Map<Integer, Integer> order = new HashMap<Integer, Integer>();
	    for (int i=0; i<=500; i++) {
	        order.put(i, elements.get(i));
	    }
	    
		return new Comparator<Integer>(){
			@Override
			public int compare(Integer t1, Integer t2) {
				return order.get(t1).compareTo(order.get(t2));
			}
		};
	}

	/**
	 * Returns the background decorator
	 * @param tiles
	 * @return
	 */
	private static DecoratorColor<Integer> getDecoratorBackgroundColor(Tiles<Integer> tiles) {
		
		final Gradient gradient = new GradientHeatscale(tiles);
		
		DecoratorColor<Integer> decorator = new DecoratorColorGradient<Integer>(gradient){
			@Override
			protected double getValue(Integer element) {
				double result = element / 140d;
				return result <= 1.0d ? result : 1.0d;
			}
		};
		
		decorator.addDecoratorListener(new DecoratorListener(){
			@Override
			public void disposed() {
				gradient.dispose();
			}
		});
		
		return decorator;
	}

	/**
	 * Returns the foreground decorator
	 * @param tiles
	 * @return
	 */
	private static DecoratorColor<Integer> getDecoratorForegroundColor(Tiles<Integer> tiles) {
		
		final Color black = new Color(tiles.getDisplay(), 0, 0, 0);
		
		DecoratorColor<Integer> decorator = new DecoratorColor<Integer>() {
			@Override
			public Color decorate(Integer t) {
				return black;
			}
		};
		
		decorator.addDecoratorListener(new DecoratorListener(){
			@Override
			public void disposed() {
				black.dispose();
			}
		});
		
		return decorator;
	}

	/**
	 * Returns the label decorator
	 * @param tiles
	 * @return
	 */
	private static DecoratorString<Integer> getDecoratorLabel(Tiles<Integer> tiles) {
		return new DecoratorString<Integer>(){
			@Override
			public String decorate(Integer t) {
				return t.toString();
			}
		};
	}

	/**
	 * Returns the line color decorator
	 * @param tiles
	 * @return
	 */
	private static DecoratorColor<Integer> getDecoratorLineColor(final Tiles<Integer> tiles) {
		
		final Color black = new Color(tiles.getDisplay(), 0, 0, 0);
		final Color blue = new Color(tiles.getDisplay(), 0, 0, 255);
		
		DecoratorColor<Integer> decorator = new DecoratorColor<Integer>() {
			@Override
			public Color decorate(Integer t) {
				if (isPrime(t)) return black;
				else return t % 2 == 0 ? blue : black;
			}
		};
		
		decorator.addDecoratorListener(new DecoratorListener(){
			@Override
			public void disposed() {
				black.dispose();
				blue.dispose();
			}
		});
		
		return decorator;
	}

	/**
	 * Returns the line style decorator
	 * @param tiles
	 * @return
	 */
	private static DecoratorInteger<Integer> getDecoratorLineStyle(Tiles<Integer> tiles) {
		return new DecoratorInteger<Integer>() {

			@Override
			public Integer decorate(Integer t) {
				if (isPrime(t)) return SWT.LINE_DASH;
				else return SWT.LINE_SOLID;
			}
		};
	}

	/**
	 * Returns the line width decorator
	 * @param tiles
	 * @return
	 */
	private static DecoratorInteger<Integer> getDecoratorLineWidth(Tiles<Integer> tiles) {
		return new DecoratorInteger<Integer>() {
			@Override
			public Integer decorate(Integer t) {
				if (isPrime(t)) return 3;
				else return t % 2 == 0 ? 3 : 1;
			}
		};
	}

	/**
	 * Returns the tooltip decorator
	 * @param tiles
	 * @return
	 */
	private static DecoratorString<Integer> getDecoratorTooltip(Tiles<Integer> tiles) {
		return new DecoratorString<Integer>() {
			@Override
			public String decorate(Integer t) {
				return "Item: " + t+"\nAgain: "+t;
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
	 * Simple prime test
	 * @param number
	 * @return
	 */
	private static boolean isPrime(int number){
		if (number<=1) return false;
		for (int i=2; i<500; i++){
			if (i!=number && number%i==0){
				return false;
			}
		}
		return true;
	}
}
