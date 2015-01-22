package fhj.shelf.utils;

import java.util.concurrent.atomic.AtomicInteger;
import fhj.shelf.utils.repos.DatabaseElements;

public abstract class AbstractElement implements DatabaseElements {

	private static AtomicInteger uniqueId = new AtomicInteger();
	private long eid;

	public AbstractElement() {

		this.eid = uniqueId.getAndIncrement();
	}

	public long getId() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

}
