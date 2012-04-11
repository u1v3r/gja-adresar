package abook.others;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

public class SortedListModel extends AbstractListModel {
	
	private SortedSet<Object> sortedSet;
	
	public SortedListModel(){
		sortedSet = new TreeSet<Object>();
	}	
	
	@Override
	public int getSize() {
		return sortedSet.size();
	}

	@Override
	public Object getElementAt(int index) {
		return sortedSet.toArray()[index];
	}

	public void addElement(Object element) {
		if (sortedSet.add(element)) {
			fireContentsChanged(this, 0, getSize());
		}
	}
	public void addAll(Object elements[]) {
		Collection<Object> c = Arrays.asList(elements);
		sortedSet.addAll(c);
		fireContentsChanged(this, 0, getSize());
	}

	public void clear() {
		sortedSet.clear();
		fireContentsChanged(this, 0, getSize());
	}

	public boolean contains(Object element) {
		return sortedSet.contains(element);
	}

	public Object firstElement() {
		return sortedSet.first();
	}

	public Iterator<Object> iterator() {
		return sortedSet.iterator();
	}

	public Object lastElement() {
		return sortedSet.last();
	}

	public boolean removeElement(Object element) {
		
		boolean removed = sortedSet.remove(element);
		
		if (removed) {
			fireContentsChanged(this, 0, getSize());
		}
		return removed;
	}

}
