package model.fhj.shelf.model.mutations;

import model.fhj.shelf.model.Shelf;

public class ShelfCreationDescriptor {

	private int capacity;

	public ShelfCreationDescriptor(int capacity) {
		this.capacity = capacity;
	}

	public ShelfCreationDescriptor() {
	}

	public ShelfCreationDescriptor capacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Shelf build(long newID) {
		return new Shelf(newID, capacity);
	}

	protected ShelfCreationDescriptor self() {
		return this;
	}
}
