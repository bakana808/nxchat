package com.hyperfresh.mchyperchat;

import java.util.Collection;
import java.util.regex.Matcher;

/**
 * Event entry methods for HyperChat.
 *
 * When implementing a server API, redirect all their events to this class.
 */
public class ChatManager
{
	private HyperChat hyperChat;

	public ChatManager(HyperChat hyperChat)
	{
		this.hyperChat = hyperChat;
	}

	/**
	 * Executes when a player has said something.
	 *
	 * @param user
	 * @param message
	 */
	public void chatAs(final User user, String message)
	{
		message = Matcher.quoteReplacement(message);
		message = hyperChat.processInlineFields(message, user); //replace fields they may have typed in here

		user.setLastSaid(message);

		final String fixedMessage = message;

		Collection<User> listeners = hyperChat.getPlugin().getUsers();

		listeners.stream().forEach(listener ->
			listener.getChatHandler().onChat(listener, user, fixedMessage)
		);
	}
}