package main.java.FHJ.shelf.model;

import java.util.concurrent.atomic.AtomicInteger;

import main.java.FHJ.shelf.model.repos.DatabaseElements;

public abstract class AbstractShelf implements DatabaseElements {

	private static AtomicInteger uniqueId = new AtomicInteger();
	private long id;

	public AbstractShelf() {

		this.id = uniqueId.getAndIncrement();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public abstract String details();

}
