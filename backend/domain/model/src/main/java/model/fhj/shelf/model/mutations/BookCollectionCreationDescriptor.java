package model.fhj.shelf.model.mutations;

import model.fhj.shelf.model.BookCollection;


public class BookCollectionCreationDescriptor extends
		ElementCreationDescriptor<BookCollectionCreationDescriptor> {

	public BookCollectionCreationDescriptor(String title) {
		super(title);
	}

	public BookCollectionCreationDescriptor() {
	}

	@Override
	public BookCollection build(long newID) {
		return new BookCollection(newID, title);
	}

	@Override
	protected BookCollectionCreationDescriptor self() {
		return this;
	}
}
