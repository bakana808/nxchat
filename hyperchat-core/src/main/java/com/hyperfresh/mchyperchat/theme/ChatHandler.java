package com.hyperfresh.mchyperchat.theme;

import com.hyperfresh.mchyperchat.User;

public interface ChatHandler
{
	/**
	 * Returns the name of this chat processor.
	 *
	 * @return
	 */
	default String getName() {return "Unknown Name";}

	/**
	 * Returns the author of this chat processor.
	 *
	 * @return
	 */
	default String getAuthor() {return "Unknown Author";}

	/**
	 * Processes a message sent by the {@code speaker},
	 * intended to be seen only by the {@code listener}.
	 *
	 * @param listener the listener of the message
	 * @param speaker the speaker of the message
	 * @param message the message
	 *
	 * @return a list of strings
	 */
	void onChat(User listener, User speaker, String message);
}
