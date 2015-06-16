package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.Theme;
import com.hyperfresh.mchyperchat.User;
import org.bukkit.command.ConsoleCommandSender;

/**
 * A wrapper for the Bukkit ConsoleCommandSender.
 */
public class BukkitConsole implements User
{
	private final ConsoleCommandSender handle;

	private String lastSaid;

	private Theme theme = HyperChat.getThemeManager().getDefaultTheme();

	public BukkitConsole(ConsoleCommandSender console, String lastSaid)
	{
		this.handle = console;
		this.lastSaid = lastSaid;
	}

	@Override
	public String getName()
	{
		return handle.getName();
	}

	@Override
	public String getLastMessage()
	{
		return lastSaid;
	}

	@Override
	public void setLastMessage(String said)
	{
		lastSaid = said;
	}

	@Override
	public void sendMessage(String message)
	{
		handle.sendMessage(message);
	}

	@Override
	public Theme getTheme()
	{
		return theme;
	}

	@Override
	public void setTheme(Theme theme)
	{
		this.theme = theme;
	}
}
