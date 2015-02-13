package fhj.shelf.commandsDomain;

import java.lang.reflect.Method;

import model.fhj.shelf.model.mutations.BookCollectionCreationDescriptor;
import model.fhj.shelf.model.mutations.BookCreationDescriptor;
import model.fhj.shelf.model.mutations.CDCollectionCreationDescriptor;
import model.fhj.shelf.model.mutations.CDCreationDescriptor;
import model.fhj.shelf.model.mutations.DVDCollectionCreationDescriptor;
import model.fhj.shelf.model.mutations.DVDCreationDescriptor;
import model.fhj.shelf.model.mutations.ElementCreationDescriptor;
import fhj.shelf.exceptions.CommandDomainException;


/**
 * Class whose instances assist the commands {@link PostElement} and
 * {@link PostShelfCollectionElement} in the creation of
 * ElementsCreationsDescriptors using reflection
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class ElementCreationDescriptorWizard {

	/**
	 * Element name
	 */
	private String name;

	/**
	 * Element type
	 */
	private String elementType;

	/**
	 * Book author
	 */
	private String author;

	/**
	 * CD tracks number
	 */
	private int tracksNumber;

	/**
	 * DVD duration
	 */
	private int duration;

	public ElementCreationDescriptorWizard(String elementType, String name,
			String author, int tracksNumber, int duration) {

		this.elementType = elementType;
		this.name = name;
		this.author = author;
		this.tracksNumber = tracksNumber;
		this.duration = duration;
	}

	public ElementCreationDescriptor<?> create()
			throws  CommandDomainException{

		try {
			ElementCreationDescriptor<?> creationDescriptor = null;

			String methodName = "create" + elementType + "Descriptor";

			Class<? extends ElementCreationDescriptorWizard> c = this
					.getClass();

			Method creatorMethod;

			creatorMethod = c.getDeclaredMethod(methodName, String.class);
			creationDescriptor = (ElementCreationDescriptor<?>) creatorMethod
					.invoke(this, name);

			return creationDescriptor;

		} catch (Exception e) {
			throw new CommandDomainException(
					"Invalid Parameter unable to create " + elementType, e);
		}

	}

	/**
	 * Creates a CD
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createCDDescriptor(String name) {

		return new CDCreationDescriptor(name, tracksNumber);
	}

	/**
	 * Creates a DVD
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createDVDDescriptor(String name) {

		return new DVDCreationDescriptor(name, duration);
	}

	/**
	 * Creates a Book
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createBookDescriptor(String name) {

		return new BookCreationDescriptor(name, author);
	}

	/**
	 * Creates a CDCollection
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createCDCollectionDescriptor(String name) {
		return new CDCollectionCreationDescriptor(name);
	}

	/**
	 * Creates a DVDCollection
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createDVDCollectionDescriptor(String name) {
		return new DVDCollectionCreationDescriptor(name);
	}

	/**
	 * Creates a BookCollection
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createBookCollectionDescriptor(String name) {
		return new BookCollectionCreationDescriptor(name);
	}
}
