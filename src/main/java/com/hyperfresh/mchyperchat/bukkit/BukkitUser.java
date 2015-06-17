package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.Player;
import com.hyperfresh.mchyperchat.Theme;

import java.util.UUID;

/**
 * A wrapper for Bukkit players.
 */
public class BukkitUser implements Player
{
	private final org.bukkit.entity.Player handle;

	private String lastSaid;

	private Theme theme = HyperChat.getThemeManager().getDefaultTheme();

	public BukkitUser(org.bukkit.entity.Player player, String lastSaid)
	{
		this.handle = player;
		this.lastSaid = lastSaid;
	}

	public String getName()
	{
		return handle.getName();
	}

	@Override
	public UUID getUUID()
	{
		return handle.getUniqueId();
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
