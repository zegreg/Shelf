package model.fhj.shelf.model.mutations;

import model.fhj.shelf.model.CD;

public class CDCreationDescriptor extends
		ElementCreationDescriptor<CDCreationDescriptor> {

	private int tracksNumber;

	public CDCreationDescriptor(String title, int tracksNumber) {
		super(title);
		this.tracksNumber = tracksNumber;
	}

	public CDCreationDescriptor() {
	}

	public CDCreationDescriptor tracksNumber(int tracksNumber) {
		this.tracksNumber = tracksNumber;
		return this;
	}

	@Override
	public CD build(long newID) {
		return new CD(newID, title, tracksNumber);
	}

	@Override
	protected CDCreationDescriptor self() {
		return this;
	}
}
