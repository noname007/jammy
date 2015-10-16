package review.classdesign.jammy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import review.classdesign.jammy.listener.ProblemSelectionListener;
import review.classdesign.jammy.listener.ContestSelectionListener;
import review.classdesign.jammy.model.ContestInfo;
import review.classdesign.jammy.model.Problem;
import review.classdesign.jammy.model.Round;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author fv
 */
public class Jammy extends AbstractUIPlugin {

	/** Plug-in ID. **/
	public static final String PLUGIN_ID = "review.classdesign.jammy"; //$NON-NLS-1$

	/** Plug-in instance. **/
	private static Jammy plugin;

	/**
	 * Empty object array in order to avoid empty array allocation duplication.
	 * This objects is also used a root element for both contest and round selection view.
	 */
	public static final Object [] CHILDLESS = new Object[0];

	/** **/
	private ContestInfo currentContest;
	
	/** **/
	private Problem currentProblem;

	/** **/
	private final List<ContestSelectionListener> contestListeners;

	/** **/
	private final List<ProblemSelectionListener> problemListeners;

	/**
	 * The constructor
	 */
	public Jammy() {
		this.contestListeners = new ArrayList<>();
		this.problemListeners = new ArrayList<>();
	}
	
	/**
	 * Adds the given {@link ContestSelectionListener} to the listener list.
	 * 
	 * @param listener Listener instance to register.
	 */
	public void addContestSelectionListener(final ContestSelectionListener listener) {
		contestListeners.add(listener);
	}

	/**
	 * Removes the given {@link ContestSelectionListener} of the listener list.
	 * 
	 * @param listener Listener instance to unregister
	 */
	public void removeContestSelectionListener(final ContestSelectionListener listener) {
		contestListeners.remove(listener);
	}

	/**
	 * Adds the given {@link ProblemSelectionListener} to the listener list.
	 * 
	 * @param listener Listener instance to register.
	 */
	public void addProblemSelectionListener(final ProblemSelectionListener listener) {
		problemListeners.add(listener);
	}
	
	/**
	 * Removes the given {@link ProblemSelectionListener} of the listener list.
	 * 
	 * @param listener Listener instance to unregister
	 */
	public void removeProblemSelectionListener(final ProblemSelectionListener listener) {
		problemListeners.remove(listener);
	}

	/**
	 * Notifies all {@link ContestSelectionListener} instance
	 * registered that the current round has changed.
	 */
	private void fireContestSelectionChanged() {
		for (final ContestSelectionListener listener : contestListeners) {
			listener.contestSelected(currentContest);
		}
	}
	
	/**
	 * Notifies all {@link ProblemSelectionListener} instance
	 * registered that the current problem has changed.
	 */
	private void fireProblemSelectionChanged() {
		for (final ProblemSelectionListener listener : problemListeners) {
			listener.problemSelected(currentProblem);
		}
	}

	/**
	 * 
	 * @param round
	 */
	public void setCurrentRound(final Round round) {
		try {
			currentContest = ContestInfo.get(round);
		}
		catch (final IOException e) {
			// TODO : Log error.
		}
		fireContestSelectionChanged();
	}

	/**
	 * 
	 * @param problem
	 */
	public void setCurrentProblem(final Problem problem) {
		currentProblem = Objects.requireNonNull(problem);
		fireProblemSelectionChanged();
	}

	/**
	 * 
	 * @return
	 */
	public Optional<ContestInfo> getCurrentContest() {
		return Optional.ofNullable(currentContest);
	}
	
	/**
	 * 
	 * @return
	 */
	public Optional<Problem> getCurrentProblem() {
		return Optional.ofNullable(currentProblem);
	}

	/** {@inheritDoc} **/
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		final IPreferenceStore store = getPreferenceStore();
		JammyPreferences.load(store);
		store.addPropertyChangeListener(JammyPreferences::propertyChange);
		plugin = this;
	}

	/** {@inheritDoc} **/
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Jammy getDefault() {
		return plugin;
	}

}
