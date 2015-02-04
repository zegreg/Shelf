package fhj.shelf.utils.repos;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractElement implements DatabaseElements {

	private static AtomicInteger uniqueId = new AtomicInteger();
	private long eid;

	public AbstractElement() {

		this.eid = eid;
	}

	public long getId() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

}
