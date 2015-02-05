package fhj.shelf.utils.mutation;

import fhj.shelf.utils.BookCollection;

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
