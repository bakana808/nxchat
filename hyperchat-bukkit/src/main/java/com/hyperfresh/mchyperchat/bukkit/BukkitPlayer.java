package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.Player;
import com.hyperfresh.mchyperchat.theme.Theme;

import java.util.UUID;

/**
 * A wrapper for Bukkit players.
 */
public class BukkitPlayer implements Player
{
	private final org.bukkit.entity.Player handle;

	private Theme theme;
	private String lastSaid = null;

	public BukkitPlayer(HyperChat hyperChat, org.bukkit.entity.Player player)
	{
		this.handle = player;
		this.theme = hyperChat.getThemeManager().getDefaultTheme();
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
