package review.classdesign.jammy.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import review.classdesign.jammy.Jammy;

/**
 * Enumeration of all available template file.
 * 
 * @author fv
 */
public enum Template implements Supplier<String> {

	/**
	 * Template for problem solution solver.
	 */
	SOLVER("/templates/solution.template.java"),
	
	/**
	 * Template for jam problem description.
	 */
	DESCRIPTION("/templates/problem.template.html")
	
	;
	
	/** Normalization pattern used for creating project and file name. **/
	private static final String PATTERN = "[^A-Za-z0-9]";
	
	/**
	 * Normalizes the given <tt>name</tt> by removing
	 * all non alphanumeric character.
	 * 
	 * @param name Name to normalize.
	 * @return Normalized name.
	 */
	public static String normalize(final String name) {
		return name.replaceAll(PATTERN, "");
	}
	
	/** Content to be retrieved. **/
	private final String content;

	/**
	 * Default constructor.
	 * Reads template content using the given file <tt>path</tt>.
	 * 
	 * @param path Bundle relative path of the template file to use.
	 */
	private Template(final String path) {
		final Class<?> loader = Jammy.class;
		final InputStream stream = loader.getResourceAsStream(path);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		this.content = reader.lines().collect(Collectors.joining("\n"));
	}

	/** {@inheritDoc} **/
	@Override
	public String get() {
		return content;
	}

}