package main.java.FHJ.shelf.model;


/**
 * Interface that imposes operations of checking an setting the availability of
 * elements that may be requested.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public interface Requestable
{
	
	
	/**
	 * Returns this element's availability.
	 * 
	 * @return {@code true} if {@code this} is available for requesting;<br>
	 *         {@code false} if it is unavailable.
	 */
	public abstract boolean isAvailable();
	
	/**
	 * Sets this element's status concerning on whether it is or is not
	 * available. If {@code b} is {@code true}, this element status changes to
	 * available; if {@code b} is {@code false}, this element is now
	 * unavailable.
	 *
	 * @param b
	 *            The new availability status of {@code this}.
	 */
	public abstract void setAvailability( boolean b );
	
	/**
	 * Returns this element's status regarding if it was requested or not from a
	 * shelf.
	 * 
	 * @return {@code true} if {@code this} is requested;<br>
	 *         {@code false} if it's not.
	 */
	public abstract boolean isRequested();
	
	/**
	 * Sets this element's status concerning on whether it is or is not
	 * requested. If {@code b} is {@code true}, this element status changes to
	 * requested; if {@code b} is {@code false}, this element was now returned.
	 *
	 * @param b
	 *            The new availability status of {@code this}.
	 */
	public abstract void isRequested( boolean b );
}
