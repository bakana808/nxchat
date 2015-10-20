package com.hyperfresh.nxchat.command;

import com.hyperfresh.nxchat.HyperChat;
import com.hyperfresh.nxchat.User;
import com.hyperfresh.nxchat.util.command.Command;
import com.hyperfresh.nxchat.util.command.Optional;
import com.hyperfresh.nxchat.theme.Theme;

public class ThemeCommands
{
	HyperChat hyperChat;

	public ThemeCommands(HyperChat hyperChat) {
		this.hyperChat = hyperChat;
	}

	@Command(
		permission = "hyperchat.theme.change",
		description = "Changes your current chat theme."
	)
	public void theme(User user, @Optional String themeID) {
		if(themeID == null) {
			user.sendMessage("Avaliable themes:");
			for(String ID: hyperChat.getThemeManager().getThemes().keySet()) {
				user.sendMessage("  " + ID);
			}
			return;
		}
		Theme theme = hyperChat.getThemeManager().get(themeID);
		if(theme != null) {
			user.setChatHandler(theme);
			user.sendMessage("Theme set to " + theme.getName() + ".");
		} else {
			user.sendMessage("Sorry, a theme with an ID \"" + themeID + "\" does not exist.");
		}
	}
}
