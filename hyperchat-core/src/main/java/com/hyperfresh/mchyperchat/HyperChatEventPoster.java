package com.hyperfresh.mchyperchat;

import java.util.Collection;
import java.util.regex.Matcher;

/**
 * Event entry methods for HyperChat.
 *
 * When implementing a server API, redirect all their events to this class.
 */
public class HyperChatEventPoster
{
	private HyperChat hyperChat;

	public HyperChatEventPoster(HyperChat hyperChat)
	{
		this.hyperChat = hyperChat;
	}

	/**
	 * The amount of time in MS between messages from the same player
	 * until their header is printed again.
	 */
	private static final long REPRINT_HEADER_TIMEOUT = 10000;

	private User lastSpoke = null;    // the last player that spoke
	private long lastSpokeTime = 0L;  // the last time a player spoke
	private long lastHeaderTime = 0L; // the last time a header was printed

	private void printElementAs(ThemeElement element, User as, Collection<User> users)
	{
		users.stream().forEach
		(
			user ->
			{
				String format = user.getTheme().getElement(element);
				if(format != null)
				{
					user.sendMessage(hyperChat.processDynamicFields(format, as));
				}
			}
		);
	}

	/**
	 * Executes when a player has said something.
	 *
	 * @param spoke
	 * @param said
	 */
	public void chatAs(User spoke, String said)
	{
		said = Matcher.quoteReplacement(said);

		said = hyperChat.processInlineFields(said, spoke); //replace fields they may have typed in here

		Collection<User> users = hyperChat.getPlugin().getUsers();

		spoke.setLastSaid(said);

		long currentTime = System.currentTimeMillis();

		// print the previous player's footer and this player's header if either:
		//  - the previous player is NOT this player or
		//  - the time between messages from the same player is equal or greater than
		//    REPRINT_HEADER_TIMEOUT
		if(spoke != lastSpoke || (currentTime - lastHeaderTime) >= REPRINT_HEADER_TIMEOUT)
		{
			//print the last player's footer
			if(lastSpoke != null)
			{
				printElementAs(ThemeElement.CHAT_FOOTER_FORMAT, lastSpoke, users);
			}

			//print this player's header
			printElementAs(ThemeElement.CHAT_HEADER_FORMAT, spoke, users);
			lastHeaderTime = currentTime;
		}

		//print this player's message
		printElementAs(ThemeElement.CHAT_MESSAGE_FORMAT, spoke, users);

		lastSpoke = spoke;
		lastSpokeTime = currentTime;
	}
}