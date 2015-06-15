package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.User;
import org.bukkit.entity.Player;

/**
 * A wrapper for Bukkit players.
 */
public class BukkitUser implements User
{
    private final Player handle;

	private String lastSaid;

	public BukkitUser(Player player, String lastSaid)
	{
		this.handle = player;
		this.lastSaid = lastSaid;
	}

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
}
