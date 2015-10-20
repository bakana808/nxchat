package com.hyperfresh.nxchat.theme;

import com.hyperfresh.nxchat.HyperChat;
import com.hyperfresh.nxchat.User;

public abstract class Theme implements ChatHandler
{
	/**
	 * The amount of time in MS between messages from the same player
	 * until their header is printed again.
	 */
	private static final long REPRINT_HEADER_TIMEOUT = 10000;

	private static User lastSpoke = null;    // the last player that spoke
	private static long lastHeaderTime = 0L; // the last time a header was printed

	HyperChat hyperChat;

	public Theme(HyperChat hyperChat)
	{
		this.hyperChat = hyperChat;
	}

	public abstract String getHeaderFormat();

	public abstract String getBodyFormat();

	public abstract String getFooterFormat();

	private String processFields(User user, String format)
	{
		return hyperChat.processDynamicFields(format, user);
	}

	@Override
	public void onChat(User listener, User speaker, String message)
	{
		long currentTime = System.currentTimeMillis();

		// print the previous player's footer and this player's header if either:
		//  - the previous player is NOT this player or
		//  - the time between messages from the same player is equal or greater than
		//    REPRINT_HEADER_TIMEOUT
		if(speaker != lastSpoke || (currentTime - lastHeaderTime) >= REPRINT_HEADER_TIMEOUT)
		{
			//print the last player's footer (if the last person that spoke isn't this player)
			if(lastSpoke != null && getFooterFormat() != null)
			{
				listener.sendMessage(processFields(lastSpoke, getFooterFormat()));
			}
			//print this player's header
			if(getHeaderFormat() != null)
			{
				listener.sendMessage(processFields(speaker, getHeaderFormat()));
			}
			lastHeaderTime = currentTime;
		}
		//print this player's body
		if(getBodyFormat() != null)
		{
			listener.sendMessage(processFields(speaker, getBodyFormat()));
		}
		lastSpoke = speaker;
	}
}
