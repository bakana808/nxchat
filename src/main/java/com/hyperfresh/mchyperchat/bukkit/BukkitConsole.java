package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.User;
import org.bukkit.command.ConsoleCommandSender;

public class BukkitConsole implements User
{
	ConsoleCommandSender handle;

	String lastSaid;

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
}
