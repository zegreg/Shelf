package fhj.shelf.utils.mutation;

import fhj.shelf.utils.Book;

public class BookCreationDescriptor extends
		ElementCreationDescriptor<BookCreationDescriptor> {

	private String author;

	public BookCreationDescriptor(String title, String author) {
		super(title);
		this.author = author;
	}

	public BookCreationDescriptor() {
	}

	public BookCreationDescriptor author(String author) {
		this.author = author;
		return this;
	}

	@Override
	public Book build(long newID) {
		return new Book(newID, title, author);
	}

	@Override
	protected BookCreationDescriptor self() {
		return this;
	}
}
