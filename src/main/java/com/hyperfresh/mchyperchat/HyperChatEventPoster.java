package com.hyperfresh.mchyperchat;

import java.util.Collection;

/**
 * Event entry methods for HyperChat.
 *
 * When implementing a server API, redirect all their events to this class.
 */
public class HyperChatEventPoster
{
	public static User lastSpoke = null;

	/**
	 * Executes when a player has said something.
	 *
	 * @param spoke
	 * @param said
	 */
	public static void onPlayerChat(Player spoke, String said)
	{
		Collection<Player> players = HyperChat.getPlayers();

		spoke.setLastMessage(said);

		if(spoke != lastSpoke)
		{
			if(lastSpoke != null) //print the last player's footer
			{
				players.stream().forEach
				(
					p ->
					{
						String footerFormat = p.getTheme().getChatFooterFormat();
						if(footerFormat != null)
						{
							p.sendMessage(HyperChat.processDynamicFields(footerFormat, lastSpoke));
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
						p.sendMessage(HyperChat.processDynamicFields(headerFormat, spoke));
					}
				}
			);
		}

		//print this player's message
		players.stream().forEach
		(
			p ->
			{
				String messageFormat = spoke.getTheme().getChatMessageFormat();
				if(messageFormat != null)
				{
					p.sendMessage(HyperChat.processDynamicFields(messageFormat, spoke));
				}
			}
		);

		lastSpoke = spoke;
	}

	/**
	 * Executes only when a console has run the "say" command.
	 * Everything <b>after</b> "say" should be included in the {@code said} argument.
	 *
	 * @param said what the console said.
	 */
	public static void onConsoleChat(String said)
	{
		HyperChat.getConsole().setLastMessage(said);
	}
}