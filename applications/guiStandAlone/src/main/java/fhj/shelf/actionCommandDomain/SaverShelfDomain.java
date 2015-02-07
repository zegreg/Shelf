package fhj.shelf.actionCommandDomain;

import java.util.Map;

import fhj.shelf.commandsDomain.CreateShelf;
import fhj.shelf.repos.ShelfRepository;

import fhj.shelf.utils.mutation.ShelfCreationDescriptor;

public class SaverShelfDomain {

	public static String PostShelfInformation(ShelfRepository shelfRepository,
			Map<String, String> params) throws Exception {

		return new CreateShelf(shelfRepository, new ShelfCreationDescriptor(
				Integer.valueOf(params.get("nbElements")))).call();
	}

}