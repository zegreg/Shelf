package User;

import java.util.Iterator;

import afterSOLIDrevisionEHL.model.AbstractElement;
import Database.DatabaseElements;



/**
 * @author amiguinhos do Maia
 *
 */
public interface UserInterface extends DatabaseElements
{
	/**
	 * @return the username
	 */
	public String getLoginName();

	public String getLoginPassword();

	boolean equals(UserInterface user);

	String getEmail();

	String getFullName();
	

}
