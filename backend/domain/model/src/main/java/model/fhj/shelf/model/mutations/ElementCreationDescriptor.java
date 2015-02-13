package model.fhj.shelf.model.mutations;

import model.fhj.shelf.model.Element;

public abstract class ElementCreationDescriptor<T extends ElementCreationDescriptor<T>> {

	/**
	 * Holds the title of the element
	 */
	protected String title;

	protected abstract T self();

	protected ElementCreationDescriptor() {
	}

	protected ElementCreationDescriptor(String title) {
		this.title = title;
	}

	public T title(String title) {
		this.title = title;
		return self();
	}

	public abstract Element build(long newID);
}
