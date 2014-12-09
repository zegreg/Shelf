package Database;



import afterSOLIDrevisionEHL.model.Shelf;


/**
 * Class that implements an in-memory product repository (i.e. an ephemeral
 * TravelAgency catalog implementation)
 */
public class InMemoryShelfRepository extends InMemoryRepo<Shelf> implements
		ShelfRepository
{
	/**
	 * 
	 */
	@Override
	public Shelf getProductById(long id)
	{
		for (Shelf shelf : super.getDatabaseElements())
			if (shelf.getId() == id)
				return shelf;

		return null;
	}
	// /**
	// * Holds the product catalog.
	// */
	// private final Collection<Product> catalog = new ArrayList<>();
	//
	// /**
	// * {@see ProductRepository#getProducts()}
	// */
	// @Override
	// public Iterable<Product> getDatabaseElements()
	// {
	// return Collections.unmodifiableCollection(catalog);
	// }
	//
	// /**
	// *
	// * @param criteria
	// * @return
	// */
	// public Iterable<Product> getDatabaseElements(Predicate<Product> criteria)
	// {
	// ArrayList<Product> results = new ArrayList<>();
	// for(Product product : catalog)
	// if(criteria.test(product))
	// results.add(product);
	//
	// return results;
	// }
	//
	//
	// /**
	// * Adds the given product to the catalog
	// *
	// * @param product The product to add to the catalog
	// * @throws IllegalArgumentException if the received product is {@code
	// null}
	// */
	// @Override
	// public void insert(Product product)
	// {
	// if(product == null)
	// throw new IllegalArgumentException();
	//
	// catalog.add(product);
	// }
}
