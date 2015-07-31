package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.theme.ChatHandler;
import com.hyperfresh.mchyperchat.User;
import org.bukkit.command.ConsoleCommandSender;

/**
 * A wrapper for the Bukkit ConsoleCommandSender.
 */
public class BukkitConsole implements User
{
	private final ConsoleCommandSender handle;

	private ChatHandler chatHandler;
	private String lastSaid = null;

	public BukkitConsole(HyperChat hyperChat, ConsoleCommandSender console)
	{
		this.handle = console;
		this.chatHandler = hyperChat.getThemeManager().getDefaultTheme();
	}

	@Override
	public String getName()
	{
		return handle.getName();
	}

	@Override
	public String getLastSaid()
	{
		return lastSaid;
	}

	@Override
	public void setLastSaid(String said)
	{
		lastSaid = said;
	}

	@Override
	public void sendMessage(String message)
	{
		handle.sendMessage(message);
	}

	@Override
	public boolean hasPermission(String permission)
	{
		return handle.hasPermission(permission);
	}

	@Override
	public ChatHandler getChatHandler()
	{
		return chatHandler;
	}

	@Override
	public void setChatHandler(ChatHandler theme)
	{
		this.chatHandler = theme;
	}
}
