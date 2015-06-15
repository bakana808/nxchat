package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.Player;

import java.util.UUID;

/**
 * A wrapper for Bukkit players.
 */
public class BukkitUser implements Player
{
    private final org.bukkit.entity.Player handle;

	private String lastSaid;

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
}
