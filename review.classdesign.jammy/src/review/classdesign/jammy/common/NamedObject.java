package review.classdesign.jammy.common;

import com.google.common.base.Function;
import com.google.gson.annotations.SerializedName;

/**
 * Class that represents a object which
 * could be identified by a name.
 * 
 * @author fv
 */
public class NamedObject {

	/** Name of this object. **/
	@SerializedName("name")
	private String name;

	/**
	 * Default constructor.
	 */
	protected NamedObject() {
	}
	
	/**
	 * Constructor with name parameter
	 * that should be used when such object
	 * are created without using JSON serialization.
	 * 
	 * @param name Name of this object.
	 */
	protected NamedObject(final String name) {
		this.name = name;
	}

	/**
	 * Getter for the name of this object.
	 * 
	 * @return Name of this object.
	 * @see #name
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * Static method that acts as a {@link Function} object.
	 * 
	 * @param object Object to return name from.
	 * @return Name of the given <tt>object</tt> if instance of {@link NamedObject}, <tt>null</tt> otherwise.
	 */
	public static String getName(final Object object) {
		if (object instanceof NamedObject) {
			return ((NamedObject) object).getName();
		}
		return null;
	}

}