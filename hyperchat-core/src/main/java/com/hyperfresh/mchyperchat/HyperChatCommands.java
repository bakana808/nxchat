package com.hyperfresh.mchyperchat;

import com.hyperfresh.mchyperchat.command.Command;
import com.hyperfresh.mchyperchat.command.Optional;
import com.hyperfresh.mchyperchat.theme.Theme;

public class HyperChatCommands
{
	HyperChat hyperChat;

	public HyperChatCommands(HyperChat hyperChat) {
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
			user.setTheme(theme);
			user.sendMessage("Theme set to " + theme.getName() + ".");
		} else {
			user.sendMessage("Sorry, a theme with an ID \"" + themeID + "\" does not exist.");
		}
	}
}
