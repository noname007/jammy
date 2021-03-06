package fr.faylixe.jammy.core.listener;

import fr.faylixe.googlecodejam.client.webservice.ContestInfo;

/** 
 * Listener that is used for notifying
 * contest selection change.
 * 
 * @author fv
 */
public interface IContestSelectionListener {

	/**
	 * Notifies that the current contextual {@link ContestInfo}
	 * instance has changed.
	 * 
	 * @param contest Newly selected contest instance.
	 */
	void contestSelected(ContestInfo contest);

}
