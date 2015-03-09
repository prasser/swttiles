SWT Tiles
====

Tiles is a widget for the Standard Widget Toolkit (SWT) that displays elements as a set of tiles.
Elements can be filtered, sorted and decorated. When decorators, the filter or the sort order are
changed, the widget shows a smooth transition between the previous and the current state.

Overview
------

Assume you want to display an overview of a set of items to the user. Additionally, the user should
be able to sort or filter items and some properties should be presented to the user in an intuitive
manner. This can be implemented with SWT Tiles. The following screen cast shows an example in which
the numbers 0 - 120 are displayed. The order of items is indicated by applying a gradient
to their background color. Even numbers have a thick blue line while prime numbers have a black
dotted line around them. The view either shows the numbers 0 to 80 or the numbers 40 to 120 from the overall
range. The filter is changed when the user selects the item 80:

![Screencast loading...](https://raw.github.com/prasser/tiles/master/media/example.gif)
Features
------

1. Generic component: `Tiles<T>` can display any element of type T.
2. Decorators: Subclasses of `Decorator<T>` for background colors (supports gradients), line colors, border widths, border styles and labels.
3. Filters: `Filter<T>` can be used to select arbitrary subsets to display with the widget.
4. Order: `Comparator<T>` can be used to impose an arbitrary order on the items displayed.

Example
------	

This basic example displays the first 80 elements from the set of numbers in range 0 to 499 in their
natural order:

```Java
// Create tiles
Tiles<Integer> tiles = new Tiles<Integer>(shell, SWT.NONE);

// Add items
List<Integer> items = new ArrayList<Integer>();
for (int i=0; i < 500; i++){
	items.add(i);
}
tiles.setItems(items);
		
// Set layout
tiles.setTileLayout(new TileLayoutDynamic(10, 10, 5, 5));

// Set filter
tiles.setFilter(new Filter<Integer>(){
	public boolean accepts(Integer t) {return t < 80; }
});

// Set comparator
tiles.setComparator(new Comparator<Integer>(){
	public int compare(Integer t1, Integer t2) { return t1.compareTo(t2); }
});
```

Download
------
A binary version (JAR file) is available for download [here](https://rawgithub.com/prasser/swttiles/master/jars/swttiles-0.0.1.jar).

The according Javadoc is available for download [here](https://rawgithub.com/prasser/swttiles/master/jars/swttiles-0.0.1-doc.jar). 

Documentation
------
Online documentation can be found [here](https://rawgithub.com/prasser/swttiles/master/doc/index.html).

SWT Tiles in action
------
Too see SWT Tiles in action, take a look at [ARX](https://github.com/arx-deidentifier/arx).

License
------
EPL 1.0
