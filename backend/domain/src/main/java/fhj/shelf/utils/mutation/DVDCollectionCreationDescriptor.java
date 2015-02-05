package fhj.shelf.utils.mutation;

import fhj.shelf.utils.DVDCollection;

public class DVDCollectionCreationDescriptor extends
		ElementCreationDescriptor<DVDCollectionCreationDescriptor> {

	public DVDCollectionCreationDescriptor(String title) {
		super(title);
	}

	public DVDCollectionCreationDescriptor() {
	}

	@Override
	public DVDCollection build(long newID) {
		return new DVDCollection(newID, title);
	}

	@Override
	protected DVDCollectionCreationDescriptor self() {
		return this;
	}
}
