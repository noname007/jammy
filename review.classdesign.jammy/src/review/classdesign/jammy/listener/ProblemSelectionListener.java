package review.classdesign.jammy.listener;

import review.classdesign.jammy.model.Problem;

/**
 * Listener that is used for notifying
 * problem selection change.
 * 
 * @author fv
 */
public interface ProblemSelectionListener {

	/**
	 * Notifies that the current contextual {@link Problem}
	 * instance has changed.
	 *  
	 * @param problem Newly selected problem instance.
	 */
	void problemSelected(Problem problem);

}