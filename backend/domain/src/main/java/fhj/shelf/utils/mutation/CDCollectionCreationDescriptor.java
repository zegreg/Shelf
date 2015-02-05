package fhj.shelf.utils.mutation;

import fhj.shelf.utils.CDCollection;

public class CDCollectionCreationDescriptor extends
		ElementCreationDescriptor<CDCollectionCreationDescriptor> {

	public CDCollectionCreationDescriptor(String title) {
		super(title);
	}

	public CDCollectionCreationDescriptor() {
	}

	@Override
	public CDCollection build(long newID) {
		return new CDCollection(newID, title);
	}

	@Override
	protected CDCollectionCreationDescriptor self() {
		return this;
	}

}
