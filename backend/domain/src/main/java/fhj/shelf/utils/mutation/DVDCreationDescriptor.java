package fhj.shelf.utils.mutation;

import fhj.shelf.utils.DVD;

public class DVDCreationDescriptor extends
		ElementCreationDescriptor<DVDCreationDescriptor> {

	private int dvdDuration;

	public DVDCreationDescriptor(String title, int dvdDuration) {
		super(title);
		this.dvdDuration = dvdDuration;
	}

	public DVDCreationDescriptor() {
	}

	public DVDCreationDescriptor dvdDuration(int dvdDuration) {
		this.dvdDuration = dvdDuration;
		return this;
	}

	@Override
	public DVD build(long newID) {
		return new DVD(newID, title, dvdDuration);
	}

	@Override
	protected DVDCreationDescriptor self() {
		return this;
	}
}
