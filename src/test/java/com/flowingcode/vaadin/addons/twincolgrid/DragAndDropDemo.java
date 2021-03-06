package com.flowingcode.vaadin.addons.twincolgrid;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class DragAndDropDemo extends VerticalLayout {
	public DragAndDropDemo() {
		final Set<Book> selectedBooks = new HashSet<>();
		selectedBooks.add(new Book("1478375108", "Vaadin Recipes"));
		selectedBooks.add(new Book("9789526800677", "Book of Vaadin: Volume 2 "));

		final List<Book> availableBooks = new ArrayList<>();
		availableBooks.add(new Book("1478375108", "Vaadin Recipes"));
		availableBooks.add(new Book("9781849515221", "Learning Vaadin"));
		availableBooks.add(new Book("9781782162261", "Vaadin 7 UI Design By Example: Beginner’s Guide"));
		availableBooks.add(new Book("9781849518802", "Vaadin 7 Cookbook"));
		availableBooks.add(new Book("9526800605", "Book of Vaadin: 7th Edition, 1st Revision"));
		availableBooks.add(new Book("9789526800677", "Book of Vaadin: Volume 2 "));
		availableBooks.add(new Book("9529267533", "Book of Vaadin"));
		availableBooks.add(new Book("1782169776", "Learning Vaadin 7, Second Edition"));

		// Drag and drop
		final TwinColGrid<Book> twinColGrid = new TwinColGrid<>(availableBooks,
				"TwinColGrid demo with drag and drop support")
						.addSortableColumn(Book::getIsbn, Comparator.comparing(Book::getIsbn), "ISBN")
						.addSortableColumn(Book::getTitle, Comparator.comparing(Book::getTitle), "Title")
						.withLeftColumnCaption("Available books").withRightColumnCaption("Added books")
						.withoutAddAllButton().withSizeFull().withDragAndDropSupport();
		twinColGrid.setValue(selectedBooks);

		final Label countLabel = new Label("Selected items in left grid: 0");
		twinColGrid.addLeftGridSelectionListener(
				e -> countLabel.setText("Selected items in left grid: " + e.getAllSelectedItems().size()));
		twinColGrid.addValueChangeListener(e -> countLabel.setText("Selected items in left grid: 0"));

		add(twinColGrid, countLabel);
		setSizeFull();
	}
}
