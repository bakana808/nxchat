package com.hyperfresh.mchyperchat;

/**
 * Event entry methods for HyperChat.
 *
 * When implementing a server API, please redirect all their events to this class.
 */
public class HyperChatEventPoster
{
	/**
	 * Executes when a player has said something.
	 *
	 * @param id
	 * @param said
	 */
	public static void onPlayerChat(User user, String said)
	{

	}

	/**
	 * Executes only when a console has run the "say" command.
	 * Everything <b>after</b> "say" should be included in the {@code said} argument.
	 *
	 * @param said what the console said.
	 */
	public static void onConsoleChat(String said)
	{

	}
}
