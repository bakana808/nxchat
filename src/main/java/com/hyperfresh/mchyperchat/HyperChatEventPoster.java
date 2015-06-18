package com.hyperfresh.mchyperchat;

import java.util.Collection;

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

	/**
	 * Executes when a player has said something.
	 *
	 * @param spoke
	 * @param said
	 */
	public void onPlayerChat(Player spoke, String said)
	{
		hyperChat.processInlineFields(said); //replace fields they may have typed in here

		Collection<Player> players = hyperChat.getPlayers();

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
				players.stream().forEach
				(
					p ->
					{
						String footerFormat = p.getTheme().getChatFooterFormat();
						if(footerFormat != null)
						{
							p.sendMessage(hyperChat.processDynamicFields(footerFormat, lastSpoke));
						}
					}
				);
			}

			//print this player's header
			players.stream().forEach
			(
				p ->
				{
					String headerFormat = spoke.getTheme().getChatHeaderFormat();
					if(headerFormat != null)
					{
						p.sendMessage(hyperChat.processDynamicFields(headerFormat, spoke));
					}
				}
			);

			lastHeaderTime = currentTime;
		}

		//print this player's message
		players.stream().forEach
		(
			p ->
			{
				String messageFormat = spoke.getTheme().getChatMessageFormat();
				if(messageFormat != null)
				{
					p.sendMessage(hyperChat.processDynamicFields(messageFormat, spoke));
				}
			}
		);

		lastSpoke = spoke;
		lastSpokeTime = currentTime;
	}

	/**
	 * Executes only when a console has run the "say" command.
	 * Everything <b>after</b> "say" should be included in the {@code said} argument.
	 *
	 * @param said what the console said.
	 */
	public void onConsoleChat(String said)
	{
		hyperChat.getConsole().setLastSaid(said);
	}
}