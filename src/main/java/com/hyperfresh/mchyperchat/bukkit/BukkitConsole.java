package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.User;
import org.bukkit.command.ConsoleCommandSender;

/**
 * A wrapper for the Bukkit ConsoleCommandSender.
 */
public class BukkitConsole implements User
{
	private final ConsoleCommandSender handle;

	private String lastSaid;

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
}
