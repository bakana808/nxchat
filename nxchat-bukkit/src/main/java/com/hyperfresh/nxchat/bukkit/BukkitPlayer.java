package com.hyperfresh.nxchat.bukkit;

import com.hyperfresh.nxchat.HyperChat;
import com.hyperfresh.nxchat.Player;
import com.hyperfresh.nxchat.theme.ChatHandler;

import java.util.UUID;

/**
 * A wrapper for Bukkit players.
 */
public class BukkitPlayer implements Player
{
	private final org.bukkit.entity.Player handle;

	private ChatHandler chatHandler;
	private String lastSaid = null;

	public BukkitPlayer(HyperChat hyperChat, org.bukkit.entity.Player player)
	{
		this.handle = player;
		this.chatHandler = hyperChat.getThemeManager().getDefaultTheme();
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
