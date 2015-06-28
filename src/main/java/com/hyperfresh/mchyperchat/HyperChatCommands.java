package com.hyperfresh.mchyperchat;

import com.hyperfresh.mchyperchat.command.Command;

public class HyperChatCommands
{
	HyperChat hyperChat;

	public HyperChatCommands(HyperChat hyperChat)
	{
		this.hyperChat = hyperChat;
	}

	@Command(permission = "hyperchat.theme.change")
	public void theme(User user, String themeID)
	{
		Theme theme = hyperChat.getThemeManager().get(themeID);

		if(theme != null)
		{
			user.setTheme(theme);
			user.sendMessage("Theme set to " + theme.getName() + ".");
		}
		else
		{
			user.sendMessage("Sorry, a theme with an ID \"" + themeID + "\" does not exist.");
		}
	}
}
